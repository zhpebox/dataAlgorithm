package ConceptSpace;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
public class FindS {
	private Map<Integer, ArrayList<String>> datasets; //数据集
	private int attrNum; //属性个数
	
	/**
	 * 构造函数
	 */
	public FindS() {
		datasets = null;
	}
	
	/**
	 * 设置要时行概念挖掘的数据集
	 * @param datasets：数据集
	 */
	public void setDatasets(Map<Integer, ArrayList<String>> datasets){
		this.datasets = datasets;
	}
	
	/**
	 * 设置候选属性个数（分类属性和标识属性除外）
	 * @param num：候选属性个数
	 */
	public void setAttrNum(int num){
		this.attrNum = num;
	}
	
	/**
	 * 执行概念挖掘
	 * @return set：概念集
	 */
	public String[] execute(){
		if(this.datasets == null || this.datasets.size() == 0){ //没有数据，返回null
			System.out.println("No data to process!");
			return null;
		}
		String[] strConcept = new String[attrNum - 1]; //存储临时概念
		for(int i = 0; i < this.attrNum - 1; i++){ //初始将临时概念空间最特化，用0表示概念空间某属性值的最特
			strConcept[i] = "0";
		}
		ArrayList<String> instance; //当前训练元组实例
		for(int j = 0; j < this.datasets.size(); j++){ //对每个实例，逐步最小程度泛化概念空间
			instance = datasets.get(j + 1);
			if (instance.get(attrNum - 1).toLowerCase().equals("yes")) { //仅考虑正元组
				for(int m = 0; m < attrNum - 1; m++){
					if(!strConcept[m].toString().equals(instance.get(m).toString())){
						if (strConcept[m].equals("0")) {//0与非0值的最小泛化就是该非0值
							strConcept[m] = instance.get(m);
						}else {//非0值与另一个不同的非0值的最小泛化就用表示通配的？号来表示
							strConcept[m] = "?";
						}
						
					}
				}
			}
		}
		return strConcept; //返回概念空间
	}
	
	/**
	 * 从控制台读取要进行概念挖掘的训练数据
	 * @param data：数据容器
	 * @return map:训练集
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
//测试算法：
//训练元组：
//Date Sky   AirTemp  Humidity  Wind   Water Swimming 
//1    Sunny Warm     High      Strong Warm2 Yes
//2    Sunny Warm     Normal    Strong Warm2 No
//3    Rainy Cold     High      Strong Warm2 No
//4    Sunny Warm     High      Strong Cool2 Yes
//概念空间挖掘结果
//Sunny Warm High Strong ? 
//由挖掘结果可知，当天气是Sunny，空气质量是Warm，湿度为High，风力为Strong，水温为任意值是，分类结果为yes，即会去游泳；反之不符合概念空间的元组分类为No
