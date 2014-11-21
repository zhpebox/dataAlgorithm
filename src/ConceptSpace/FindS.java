package ConceptSpace;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
public class FindS {
	private Map<Integer, ArrayList<String>> datasets; //���ݼ�
	private int attrNum; //���Ը���
	
	/**
	 * ���캯��
	 */
	public FindS() {
		datasets = null;
	}
	
	/**
	 * ����Ҫʱ�и����ھ�����ݼ�
	 * @param datasets�����ݼ�
	 */
	public void setDatasets(Map<Integer, ArrayList<String>> datasets){
		this.datasets = datasets;
	}
	
	/**
	 * ���ú�ѡ���Ը������������Ժͱ�ʶ���Գ��⣩
	 * @param num����ѡ���Ը���
	 */
	public void setAttrNum(int num){
		this.attrNum = num;
	}
	
	/**
	 * ִ�и����ھ�
	 * @return set�����
	 */
	public String[] execute(){
		if(this.datasets == null || this.datasets.size() == 0){ //û�����ݣ�����null
			System.out.println("No data to process!");
			return null;
		}
		String[] strConcept = new String[attrNum - 1]; //�洢��ʱ����
		for(int i = 0; i < this.attrNum - 1; i++){ //��ʼ����ʱ����ռ����ػ�����0��ʾ����ռ�ĳ����ֵ������
			strConcept[i] = "0";
		}
		ArrayList<String> instance; //��ǰѵ��Ԫ��ʵ��
		for(int j = 0; j < this.datasets.size(); j++){ //��ÿ��ʵ��������С�̶ȷ�������ռ�
			instance = datasets.get(j + 1);
			if (instance.get(attrNum - 1).toLowerCase().equals("yes")) { //��������Ԫ��
				for(int m = 0; m < attrNum - 1; m++){
					if(!strConcept[m].toString().equals(instance.get(m).toString())){
						if (strConcept[m].equals("0")) {//0���0ֵ����С�������Ǹ÷�0ֵ
							strConcept[m] = instance.get(m);
						}else {//��0ֵ����һ����ͬ�ķ�0ֵ����С�������ñ�ʾͨ��ģ�������ʾ
							strConcept[m] = "?";
						}
						
					}
				}
			}
		}
		return strConcept; //���ظ���ռ�
	}
	
	/**
	 * �ӿ���̨��ȡҪ���и����ھ��ѵ������
	 * @param data����������
	 * @return map:ѵ����
	 * @throws IOException
	 */
	public Map<Integer, ArrayList<String>> readData(Map<Integer, ArrayList<String>> data) throws IOException{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String str = "";
		while(!(str = reader.readLine()).equals("")){
			StringTokenizer tokenizer = new StringTokenizer(str);
			String key = tokenizer.nextToken();
			ArrayList<String> s = new ArrayList<String>();
			while(tokenizer.hasMoreTokens()){
				s.add(tokenizer.nextToken());
			}
			data.put(Integer.parseInt(key), s);
		}
		return data;
	}
	
	public static void main(String[] args) {
		Map<Integer, ArrayList<String>> data = new HashMap<Integer, ArrayList<String>>();
		FindS findS = new FindS();
		try {
			findS.readData(data);
		} catch (IOException e) {
			System.out.println("IOException occurs when reading data!");
		}
		findS.setAttrNum(6);
		findS.setDatasets(data);
		String[] concept = findS.execute();
		for(int i = 0; i< concept.length; i++){
			System.out.print(concept[i].toString() + " ");
		}
		System.out.println();
	}
}
//�����㷨��
//ѵ��Ԫ�飺
//Date Sky   AirTemp  Humidity  Wind   Water Swimming 
//1    Sunny Warm     High      Strong Warm2 Yes
//2    Sunny Warm     Normal    Strong Warm2 No
//3    Rainy Cold     High      Strong Warm2 No
//4    Sunny Warm     High      Strong Cool2 Yes
//����ռ��ھ���
//Sunny Warm High Strong ? 
//���ھ�����֪����������Sunny������������Warm��ʪ��ΪHigh������ΪStrong��ˮ��Ϊ����ֵ�ǣ�������Ϊyes������ȥ��Ӿ����֮�����ϸ���ռ��Ԫ�����ΪNo
