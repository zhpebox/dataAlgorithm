package com.yang;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.bean.AssociationBean;
import com.dao.AssociationDao;

public class Apriori1 {

	private double minsup = 0.1;// ��С֧�ֶ�
	private double minconf = 0.6;// ��С���Ŷ�

	private int itemCounts = 0;// ��ѡ1��Ŀ����С,����ĸ�ĸ���
	 private String[] transSet = { "abe", "bd", "bc", "abd", "ac",
	 "bc","ac","abce","abc"};// ���񼯺�
	// String transSet[]={"abc","abc","acde","bcdf","abcd","abcdf"};//����

	//private String[] transSet = { "abe", "bd", "bc", "abd", "ac",
	// "bc","ac","abce","abc","ghijkl","abcdefghijk","fghijklmnopqrstuvwxyz","cedfghijkl"};
	// private String[] transSet = { "abcdefghijklmn", "abcd", "efgh",
	// "klmn","rstu","vwxy"};
	//private String[] transSet = {"abcdefghi","abcdefhij","abcdefghij"};
	private TreeSet[] frequencySet = new TreeSet[40];// Ƶ������飬[0]:����1Ƶ����...��TreeSet����ʹ��Ԫ�ص���Ȼ˳���Ԫ�ؽ�������
	private TreeSet maxFrequency = new TreeSet();// ���Ƶ����
	private TreeSet candidate = new TreeSet();
	private TreeSet candidateSet[] = new TreeSet[40];// ��ѡ������[0]:����1��ѡ��
	private int frequencyIndex;

	// ע��ʹ��IdentityHashMap���������ڹ�������������ڼ�ֵ��ͬ�Ļ���ָ���
	private IdentityHashMap ruleMap = new IdentityHashMap();

	private int total = 0;
	private int delete = 0;

	TreeSet<String> product = new TreeSet<String>();// �ж�������Ʒ
	Map map = new HashMap(); // ÿ����Ʒ��һ����ĸ��ʾ,key ����Ʒ����

	// value ��һ����ĸ

	public Apriori1() {

		// ��ʼ���������ݿ�
		transSet = initTransSet();

		maxFrequency = new TreeSet();
		itemCounts = counts();// ��ʼ��1��ѡ���Ĵ�С6��

		// ��ʼ����������
		for (int i = 0; i < itemCounts; i++) {
			frequencySet[i] = new TreeSet();// ��ʼ��Ƶ�������
			candidateSet[i] = new TreeSet();// ��ʼ����ѡ������
		}
		candidateSet[0] = candidate;// 1��ѡ��
	}
	
	public String[] initTransSet1() {
		String path = System.getProperty("user.dir");
		String filePath = path + "//data//strdata.txt";
		String[] transSet = null;
		try {
			FileReader fr = new FileReader(filePath);
			BufferedReader br = new BufferedReader(fr);
			String line = "";
			ArrayList list = new ArrayList();
			int lineNumber = 0;
			while ((line = br.readLine()) != null) {
                 list.add(line);
                 lineNumber++;
			}
			
			
			
			br.close();
			fr.close();
			
			transSet = new String[lineNumber];
			System.out.println("transSet:"+transSet.length);
			System.out.println("lineNumber:"+lineNumber);
			for(int i=0;i<lineNumber;i++){
				//transSet[i] = list.get(i).toString();
				TreeSet set = new TreeSet();
				String info = list.get(i).toString();
				for(int j=0;j<info.length();j++){
				    set.add(info.charAt(j)+"");
				}
				String str = "";
				Iterator iterator = set.iterator();
				while(iterator.hasNext()){
					String s = iterator.next().toString();
					str += s;
				}
				transSet[i] = str;
				System.out.println("transSet[i]:"+transSet[i]);
				
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return transSet;
	}

	public String[] initTransSet() {

		AssociationDao dao = new AssociationDao();
		List<AssociationBean> list = dao.getAll();
		// System.out.println("size:"+list.size());

		// TreeSet<String> product = new TreeSet<String>();
		for (AssociationBean bean : list) {
			product.add(bean.getProduct());
		}

		// System.out.println("product size:"+product.size());
		char ch = 'a';

		for (String p : product) {

			map.put(p, ch);
			System.out.println(ch + ":" + p);
			ch++;
		}
		// System.out.println("************");
		// System.out.println("l:"+getProductNameByValue('l'));
		// System.out.println("n:"+getProductNameByValue('n'));
		// System.out.println("q:"+getProductNameByValue('q'));
		// System.out.println("************");

		// System.out.println("------------------");
		// System.out.println("map:");
		// Set set = map.keySet();
		// Iterator iterator = set.iterator();

		// Collection collection = map.values();
		// Iterator iterator = collection.iterator();
		// while(iterator.hasNext()){
		// Object key = iterator.next();
		// System.out.println(key+":"+map.get(key));
		// }

		// �����������ݿ�
		// String[] transSet = { "abe", "bd", "bc", "abd", "ac",
		// "bc","ac","abce","abc"};// ���񼯺�
		String[] transSet = new String[1001];
		for (int i = 0; i < transSet.length; i++) {
			String number = "";
			for (AssociationBean bean : list) {

				if (i == bean.getCustomerId()) {

					String key = bean.getProduct();
					String v = map.get(key).toString();
					number += v;
				}

			}

			transSet[i] = number;

		}
		// System.out.println("------------");
		// System.out.println("tranSet:");
		// for(int i=0;i<transSet.length;i++){
		// System.out.println(i+"+"+transSet[i]);
		// }

		return transSet;

	}

	public static void main(String[] args) {

		long t1 = System.currentTimeMillis();

		Apriori1 ap = new Apriori1();
		ap.run();

		long t2 = System.currentTimeMillis();

		System.out.println("time:" + (t2 - t1));

		System.out.println("total candidate itemset:" + ap.total);
		System.out.println("delete candidate itemset:" + ap.delete);

	}

	// ��������
	public void run() {
		int k = 1;
		item1_gen();

		do {
			k++;
			canditate_gen(k);
			frequent_gen(k);
		} while (!is_frequent_empty(k));
		frequencyIndex = k - 1;
		print_canditate();
		maxfrequent_gen();
		print_maxfrequent();
		ruleGen();
		rulePrint();
	}

	// ��¼ÿ�������е�Ԫ�س��ִ���
	public double count_sup(String x) {
		int temp = 0;
		for (int i = 0; i < transSet.length; i++) {
			for (int j = 0; j < x.length(); j++) {
				if (transSet[i].indexOf(x.charAt(j)) == -1)// ����ָ���ַ��ڴ��ַ����е�һ�γ��ִ����������������Ϊһ���ַ���������-1
					break;
				else if (j == (x.length() - 1))
					temp++;
			}
		}
		return temp;
	}

	public void item1_gen() {
		String temp1 = "";
		double m = 0;

		Iterator temp = candidateSet[0].iterator();// ʹ�÷���iterator()Ҫ����������һ��Iterator��
		while (temp.hasNext()) {// ����temp��1��ѡ����
			temp1 = (String) temp.next();
			m = count_sup(temp1);// ��������ķ�����ͳ��1��ѡ����ÿ��Ԫ�ظ���������֧�ֶ�ʱ���ô�m/transSet.length

			// ���������ļ��� 1��ѡ��
			if (m >= minsup * transSet.length) {// minsup *
												// transSet.length��ֵΪ��¼ÿ�������е�Ԫ�س��ִ���,�ж��Ƿ�1Ƶ����
				frequencySet[0].add(temp1);// 1Ƶ��������Ƶ������飬�Զ���ȥ�ظ��ļ���
			    //System.out.println("temp1:"+temp1);
			}
		}
	}

	// ��K��ѡ��
	public void canditate_gen(int k) {
		String y = "", z = "", m = "";
		char c1, c2;
		// �Ľ� ���Ӳ�
		Iterator temp1 = frequencySet[k - 2].iterator();
		Iterator temp2 = frequencySet[k - 2].iterator();
		TreeSet h = new TreeSet();
		while (temp1.hasNext()) {
			y = (String) temp1.next();//
			c1 = y.charAt(y.length() - 1);// ����ָ��y.length() - 1����������һ������charֵ
			// ȡ��yǰ��ģ����������һ��

			while (temp2.hasNext()) {
				z = (String) temp2.next();
				c2 = z.charAt(z.length() - 1);

				if (y.length() == 1 && z.length() == 1) {

					if (c1 < c2) {

						m = y + z;

						total++;

						h.add(m);// m����TreeSet

					}

				} else {

					String prefixY = y.substring(0, y.length() - 1);
					// System.out.println("prefixY:"+prefixY);
					String prefixZ = z.substring(0, z.length() - 1);
					// System.out.println("prefixZ:"+prefixZ);
					if (prefixY.equals(prefixZ)) {

						if (c1 < c2) {

							m = y + c2;
							String subs[] = createSubSet(m);
							// h.add(m);
							// ��֦���� ʹ��Apriori����ɾ�����з�Ƶ���Ӽ��ĺ�ѡ
							if (!has_infrequent_subset(subs,
									frequencySet[k - 2])) {

								// System.out.println("m:"+m);

								h.add(m);
							} else {
								delete++;
							}
							total++;
							// has_infrequent_subset()

							// h.add(m);//m����TreeSet
						}

					}

				}
			}

			// String mSets[] = createSubSet(m);

			temp2 = frequencySet[k - 2].iterator();
		}

		candidateSet[k - 1] = h;

		// String y = "", z = "", m = "";
		// char c1 ,c2 ;
		//
		// Iterator temp1 = frequencySet[k - 2].iterator();//iterator�������������������
		// Iterator temp2 = frequencySet[0].iterator();//����Ƶ������飬[0]:����1Ƶ����
		// TreeSet h = new TreeSet();
		//
		// while (temp1.hasNext()) {
		// y = (String) temp1.next();//
		// c1 = y.charAt(y.length() - 1);//����ָ��y.length() - 1����������һ������charֵ
		//
		// while (temp2.hasNext()) {
		// z = (String) temp2.next();
		//
		// c2 = z.charAt(0);//c2=a,b,c,d,e,f
		// if (c1 >= c2)
		// continue;
		// else {
		// m = y + z;//mΪ�ַ������yz
		// h.add(m);//m����TreeSet
		// }
		// }
		// temp2 = frequencySet[0].iterator();
		// }
		// candidateSet[k - 1] = h;
	}

	public String[] createSubSet(String test) {

		int length = test.length();

		char numberArr[] = new char[length];

		for (int i = 0; i < numberArr.length; i++) {

			numberArr[i] = test.charAt(i);

		}

		int arr[][] = new int[length][length];

		for (int i = 0; i < arr.length; i++) {

			for (int j = 0; j < arr[i].length; j++) {

				if (i == j) {

					arr[i][j] = 0;
				} else {
					arr[i][j] = 1;
				}

			}

		}

		String subSet[] = new String[length];

		for (int i = 0; i < arr.length; i++) {

			String set = "";

			for (int j = 0; j < arr[i].length; j++) {

				if (i != j) {

					set += numberArr[j] + "";

				}

			}

			subSet[i] = set;

		}

		return subSet;

		// for(int i=0;i<subSet.length;i++){
		//			
		// System.out.println(subSet[i]);
		//			
		// }

	}

	// �Ľ� ��ѡ���ļ�� ��֦��
	public boolean has_infrequent_subset(String subs[], TreeSet set) {

		for (String s : subs) {

			if (!set.contains(s)) {
				return true;
			}
		}

		return false;

	}

	// k��ѡ��=>kƵ����
	public void frequent_gen(int k) {
		String s1 = "";

		Iterator ix = candidateSet[k - 1].iterator();// ����K��ѡ��ix
		while (ix.hasNext()) {
			s1 = (String) ix.next();// ix�е�ֵs1
			if (count_sup(s1) >= (minsup * transSet.length)) {// s1�֧�ֶȴ�����С֧�ֶ�
				frequencySet[k - 1].add(s1);// s1����KƵ������
			}
		}
	}

	// �ж�Ƶ����Ϊ��
	public boolean is_frequent_empty(int k) {
		if (frequencySet[k - 1]==null || frequencySet[k - 1].isEmpty() )
			return true;
		else
			return false;
	}

	// ��ӡ��ѡ�� Ƶ����
	public void print_canditate() {

		// for (int i = 0; i < frequencySet[0].size(); i++) {
		for (int i = 0; i < frequencyIndex; i++) {
			Iterator ix = candidateSet[i].iterator();
			Iterator iy = frequencySet[i].iterator();
			System.out.print("��ѡ��" + (i + 1) + ":");
			while (ix.hasNext()) {
				System.out.print((String) ix.next() + "\t");
			}
			System.out.print("\n" + "Ƶ����" + (i + 1) + ":");
			while (iy.hasNext()) {
				System.out.print((String) iy.next() + "\t");
			}
			System.out.println();
		}
	}

	// ��ӡƵ���
	public void print_maxfrequent() {
		Iterator iterator = maxFrequency.iterator();
		System.out.print("Ƶ�����");
		while (iterator.hasNext()) {
			System.out.print(((String) iterator.next()) + "\t");
		}
		System.out.println();
		System.out.println("maxFrequency size:" + maxFrequency.size());

	}

	// ��������
	public void maxfrequent_gen() {
		int i;
		for (i = 1; i < frequencyIndex; i++) {
			maxFrequency.addAll(frequencySet[i]);
		}

	}

	// ͳ��1��ѡ���ĸ���a,b,c,d,e,f,returnֵΪ6
	public int counts() {

		String temp1 = null;
		char temp2 = 'a';
		// ������������String ���뼯�ϣ�set�Զ�ȥ����
		for (int i = 0; i < transSet.length; i++) {
			temp1 = transSet[i];
			for (int j = 0; j < temp1.length(); j++) {
				temp2 = temp1.charAt(j);// ����λ��Ϊj��temp1��ֵa
				candidate.add(String.valueOf(temp2));// treeSet��ӻ�ȥ���ظ���ֵ
			    //System.out.println("temp2:"+temp2);
			}
		}
		//System.out.println("candidate.size:"+candidate.size());
		return candidate.size();// ��Ԫ�ظ������ظ����ҵ�������
	}

	// ���������
	public void ruleGen() {
		String s;
		Iterator iterator = maxFrequency.iterator();
		while (iterator.hasNext()) {
			s = (String) iterator.next();
			subGen(s);
		}
	}

	// ���������
	public void subGen(String s) {
		String x = "", y = "";
		for (int i = 1; i < (1 << s.length()) - 1; i++) {
			for (int j = 0; j < s.length(); j++) {
				if (((1 << j) & i) != 0) {
					x += s.charAt(j);
				}
			}

			for (int j = 0; j < s.length(); j++) {
				if (((1 << j) & (~i)) != 0) {

					y += s.charAt(j);

				}
			}
			if (count_sup(x + y) / count_sup(x) >= minconf) {
				ruleMap.put(x, y);
				System.out.println("x:"+x);
				System.out.println("y:"+y);
			}
			x = "";
			y = "";
			
			

		}
	}

	// ��ӡ��������
	public void rulePrint() {
		String x, y;
		float temp = 0;

		Set hs = ruleMap.keySet();// ������ֻ����getȡkey��Set�������ظ�Ԫ�ص�collection
		Iterator iterator = hs.iterator();
		System.out.println("��������");
		while (iterator.hasNext()) {
			
			x = (String) iterator.next();

			y = (String) ruleMap.get(x);
			
			

			temp = (float) (count_sup(x + y) / count_sup(x));

			char charX[] = subCharArray(x);
			char charY[] = subCharArray(y);
			String strX = "";
			String strY = "";
			for (int i = 0; i < charX.length; i++) {
				// System.out.println("charX:"+charX[i]);
				strX += "(" + getProductNameByValue(charX[i]) + ")";
			}
			for (int i = 0; i < charY.length; i++) {
				// System.out.println("charY:"+charY[i]);
				strY += "(" + getProductNameByValue(charY[i]) + ")";
			}
			// System.out.println("x:"+x);
			// System.out.println("y:"+y);
			// System.out.println(x + (x.length() < 10 ? "\t" : "") + "-->" + y+
			// "\t" + "���Ŷ�: " + temp);
			System.out.println(strX + "-->" + strY + "\t" + "���Ŷ�: " + temp);
			// System.out.println("----------");

		}
	}

	public char[] subCharArray(String s) {

		char ch[] = new char[s.length()];
		for (int i = 0; i < s.length(); i++) {
			ch[i] = s.charAt(i);
		}

		return ch;
	}

	public String getProductNameByValue(char ch) {

		String productName = "";
		Set set = map.keySet();
		Iterator iterator = set.iterator();

		while (iterator.hasNext()) {
			Object key = iterator.next();
			// System.out.println(key+":"+map.get(key));
			if (map.get(key).toString().equals(ch + "")) {
				productName = key.toString();
				break;
			}
		}

		return productName;

	}

}
