package com.yang;

import java.util.*;

public class Apriori {

	private double minsup = 0.2;// ��С֧�ֶ�
	private double minconf = 0.2;// ��С���Ŷ�

	// ע��ʹ��IdentityHashMap���������ڹ�������������ڼ�ֵ��ͬ�Ļ���ָ���
	private IdentityHashMap ruleMap = new IdentityHashMap();

	//private String[] transSet = { "abc", "abc", "acde", "bcdf", "abcd", "abcdf" };// ���񼯺�
																					// ��
																					// ���Ը�����Ҫ�ӹ��캯���ﴫ��
	private String[] transSet = { "abe", "bd", "bc", "abd", "ac", "bc","ac","abce","abc" };// ���񼯺�
	private int itemCounts = 0;// ��ѡ1��Ŀ����С,����ĸ�ĸ���
	private TreeSet[] frequencySet = new TreeSet[40];// Ƶ������飬[0]:����1Ƶ����...��TreeSet����ʹ��Ԫ�ص���Ȼ˳���Ԫ�ؽ�������
	private TreeSet maxFrequency = new TreeSet();// ���Ƶ����
	private TreeSet candidate = new TreeSet();
	private TreeSet candidateSet[] = new TreeSet[40];// ��ѡ������[0]:����1��ѡ��
	private int frequencyIndex;

	public Apriori() {

		maxFrequency = new TreeSet();
		itemCounts = counts();// ��ʼ��1��ѡ���Ĵ�С6��

		// ��ʼ����������
		for (int i = 0; i < itemCounts; i++) {
			frequencySet[i] = new TreeSet();//��ʼ��Ƶ�������
			candidateSet[i] = new TreeSet();//��ʼ����ѡ������
		}
		candidateSet[0] = candidate;// 1��ѡ��
	}

	//���������
	public static void main(String[] args) {
		Apriori ap = new Apriori();
		ap.run();
	}
	
	//��������
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
	//��¼ÿ�������е�Ԫ�س��ִ���
	public double count_sup(String x) {
		int temp = 0;
		for (int i = 0; i < transSet.length; i++) {
			for (int j = 0; j < x.length(); j++) {
				if (transSet[i].indexOf(x.charAt(j)) == -1)//����ָ���ַ��ڴ��ַ����е�һ�γ��ִ����������������Ϊһ���ַ���������-1
					break;
				else if (j == (x.length() - 1))
					temp++;
			}
		}
		return temp;
	}
	
	//ͳ��1��ѡ���ĸ���a,b,c,d,e,f,returnֵΪ6
	public int counts() {

		String temp1 = null;
		char temp2 = 'a';
		// ������������String ���뼯�ϣ�set�Զ�ȥ����
		for (int i = 0; i < transSet.length; i++) {
			temp1 = transSet[i];
			for (int j = 0; j < temp1.length(); j++) {
				temp2 = temp1.charAt(j);//����λ��Ϊj��temp1��ֵa
				candidate.add(String.valueOf(temp2));//treeSet��ӻ�ȥ���ظ���ֵ
			}
		}
		return candidate.size();//��Ԫ�ظ������ظ����ҵ�������
	}

	//��1Ƶ����
	public void item1_gen() {
		String temp1 = "";
		double m = 0;

		Iterator temp = candidateSet[0].iterator();//ʹ�÷���iterator()Ҫ����������һ��Iterator��
		while (temp.hasNext()) {//����temp��1��ѡ����
			temp1 = (String) temp.next();
			m = count_sup(temp1);//��������ķ�����ͳ��1��ѡ����ÿ��Ԫ�ظ���������֧�ֶ�ʱ���ô�m/transSet.length

			// ���������ļ��� 1��ѡ��
			if (m >= minsup * transSet.length) {//minsup * transSet.length��ֵΪ��¼ÿ�������е�Ԫ�س��ִ���,�ж��Ƿ�1Ƶ����
				frequencySet[0].add(temp1);//1Ƶ��������Ƶ������飬�Զ���ȥ�ظ��ļ���
			}
		}
	}
	//��K��ѡ��
	public void canditate_gen(int k) {
		String y = "", z = "", m = "";
		char c1 ,c2 ;

		Iterator temp1 = frequencySet[k - 2].iterator();//iterator�������������������
		Iterator temp2 = frequencySet[0].iterator();//����Ƶ������飬[0]:����1Ƶ����
		TreeSet h = new TreeSet();

		while (temp1.hasNext()) {
			y = (String) temp1.next();//
			c1 = y.charAt(y.length() - 1);//����ָ��y.length() - 1����������һ������charֵ

			while (temp2.hasNext()) {
				z = (String) temp2.next();

				c2 = z.charAt(0);//c2=a,b,c,d,e,f
				if (c1 >= c2)
					continue;
				else {
					m = y + z;//mΪ�ַ������yz
					h.add(m);//m����TreeSet
				}
			}
			temp2 = frequencySet[0].iterator();
		}
		candidateSet[k - 1] = h;
	}

	// k��ѡ��=>kƵ����
	public void frequent_gen(int k) {
		String s1 = "";

		Iterator ix = candidateSet[k - 1].iterator();//����K��ѡ��ix
		while (ix.hasNext()) {
			s1 = (String) ix.next();//ix�е�ֵs1
			if (count_sup(s1) >= (minsup * transSet.length)) {//s1�֧�ֶȴ�����С֧�ֶ�
				frequencySet[k - 1].add(s1);//s1����KƵ������
			}
		}
	}
    //�ж�Ƶ����Ϊ��
	public boolean is_frequent_empty(int k) {
		if (frequencySet[k - 1].isEmpty())
			return true;
		else
			return false;
	}
	//��ӡ��ѡ�� Ƶ����
	public void print_canditate() {

		for (int i = 0; i < frequencySet[0].size(); i++) {
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

    //��������
	public void maxfrequent_gen() {
		int i;
		for (i = 1; i < frequencyIndex; i++) {
			maxFrequency.addAll(frequencySet[i]);
		}
	}
    //��ӡƵ���
	public void print_maxfrequent() {
		Iterator iterator = maxFrequency.iterator();
		System.out.print("Ƶ�����");
		while (iterator.hasNext()) {
			System.out.print(((String) iterator.next()) + "\t");
		}
		System.out.println();
		System.out.println();
	}
	//���������
	public void ruleGen() {
		String s;
		Iterator iterator = maxFrequency.iterator();
		while (iterator.hasNext()) {
			s = (String) iterator.next();
			subGen(s);
		}
	}

    //���������
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
			}
			x = "";
			y = "";

		}
	}


	//��ӡ��������
	public void rulePrint() {
		String x, y;
		float temp = 0;

		Set hs = ruleMap.keySet();//������ֻ����getȡkey��Set�������ظ�Ԫ�ص�collection
		Iterator iterator = hs.iterator();
		System.out.println("��������");
		while (iterator.hasNext()) {
			x = (String) iterator.next();

			y = (String) ruleMap.get(x);

			temp = (float) (count_sup(x + y) / count_sup(x));

			System.out.println(x + (x.length() < 5 ? "\t" : "") + "-->" + y+ "\t" + "���Ŷ�: " + temp);

		}
	}




}

