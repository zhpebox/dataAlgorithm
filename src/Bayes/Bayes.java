package Bayes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * ��Ҷ˹������
 * @author Rowen
 * @qq 443773264
 * @mail luowen3405@163.com
 * @blog blog.csdn.net/luowen3405
 * @data 2011.03.15
 */
public class Bayes {
	/**
	 * ��ԭѵ��Ԫ�鰴��𻮷�
	 * @param datas ѵ��Ԫ��
	 * @return Map<������ڸ�����ѵ��Ԫ��>
	 */
	Map<String, ArrayList<ArrayList<String>>> datasOfClass(ArrayList<ArrayList<String>> datas){
		Map<String, ArrayList<ArrayList<String>>> map = new HashMap<String, ArrayList<ArrayList<String>>>();
		ArrayList<String> t = null;
		String c = "";
		for (int i = 0; i < datas.size(); i++) {
			t = datas.get(i);
			c = t.get(t.size() - 1);
			if (map.containsKey(c)) {
				map.get(c).add(t);
			} else {
				ArrayList<ArrayList<String>> nt = new ArrayList<ArrayList<String>>();
				nt.add(t);
				map.put(c, nt);
			}
		}
		return map;
	}
	
	/**
	 * ��ѵ�����ݵĻ�����Ԥ�����Ԫ������
	 * @param datas ѵ��Ԫ��
	 * @param testT ����Ԫ��
	 * @return ����Ԫ������
	 */
	public String predictClass(ArrayList<ArrayList<String>> datas, ArrayList<String> testT) {
		Map<String, ArrayList<ArrayList<String>>> doc = this.datasOfClass(datas);
		Object classes[] = doc.keySet().toArray();
		double maxP = 0.00;
		int maxPIndex = -1;
		for (int i = 0; i < doc.size(); i++) {
			String c = classes[i].toString(); 
			ArrayList<ArrayList<String>> d = doc.get(c);
			double pOfC = DecimalCalculate.div(d.size(), datas.size(), 3);
			for (int j = 0; j < testT.size(); j++) {
				double pv = this.pOfV(d, testT.get(j), j);
				pOfC = DecimalCalculate.mul(pOfC, pv);
			}
			if(pOfC > maxP){
				maxP = pOfC;
				maxPIndex = i;
			}
		}
		return classes[maxPIndex].toString();
	}
	/**
	 * ����ָ����������ָ��ֵ���ֵĸ���
	 * @param d ����ĳһ���ѵ��Ԫ��
	 * @param value ��ֵ
	 * @param index ����������
	 * @return ����
	 */
	private double pOfV(ArrayList<ArrayList<String>> d, String value, int index) {
		double p = 0.00;
		int count = 0;
		int total = d.size();
		ArrayList<String> t = null;
		for (int i = 0; i < total; i++) {
			if(d.get(i).get(index).equals(value)){
				count++;
			}
		}
		p = DecimalCalculate.div(count, total, 3);
		return p;
	}
}
