package DecisionTree;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
//import javax.smartcardio.ATR;
/**
 * 决策树构造类
 * @author Rowen
 * @qq 443773264
 * @mail luowen3405@163.com
 * @blog blog.csdn.net/luowen3405
 * @data 2011.03.15
 */
public class DecisionTree { 
	private Integer attrSelMode;  //最佳分裂属性选择模式，1表示以信息增益度量，2表示以信息增益率度量。暂未实现2
	public DecisionTree(){
		this.attrSelMode = 1;
	}
	
	public DecisionTree(int attrSelMode) {
		this.attrSelMode = attrSelMode;
	}
	public void setAttrSelMode(Integer attrSelMode) {
		this.attrSelMode = attrSelMode;
	}
	/**
	 * 获取指定数据集中的类别及其计数
	 * @param datas 指定的数据集
	 * @return 类别及其计数的map
	 */
	public Map<String, Integer> classOfDatas(ArrayList<ArrayList<String>> datas){
		Map<String, Integer> classes = new HashMap<String, Integer>();
		String c = "";
		ArrayList<String> tuple = null;
		for (int i = 0; i < datas.size(); i++) {
			tuple = datas.get(i);
			c = tuple.get(tuple.size() - 1);
			if (classes.containsKey(c)) {
				classes.put(c, classes.get(c) + 1);
			} else {
				classes.put(c, 1);
			}
		}
		return classes;
	}
	
	/**
	 * 获取具有最大计数的类名，即求多数类
	 * @param classes 类的键值集合
	 * @return 多数类的类名
	 */
	public String maxClass(Map<String, Integer> classes){
		String maxC = "";
		int max = -1;
		Iterator iter = classes.entrySet().iterator();
		for(int i = 0; iter.hasNext(); i++)
		{
			Map.Entry entry = (Map.Entry) iter.next(); 
			String key = (String)entry.getKey();
			Integer val = (Integer) entry.getValue(); 
			if(val > max){
				max = val;
				maxC = key;
			}
		}
		return maxC;
	}
	
	/**
	 * 构造决策树
	 * @param datas 训练元组集合
	 * @param attrList 候选属性集合
	 * @return 决策树根结点
	 */
	public TreeNode buildTree(ArrayList<ArrayList<String>> datas, ArrayList<String> attrList){
//		System.out.print("候选属性列表： ");
//		for (int i = 0; i < attrList.size(); i++) {
//			System.out.print(" " + attrList.get(i) + " ");
//		}
		System.out.println();
		TreeNode node = new TreeNode();
		node.setDatas(datas);
		node.setCandAttr(attrList);
		Map<String, Integer> classes = classOfDatas(datas);
		String maxC = maxClass(classes);
		if (classes.size() == 1 || attrList.size() == 0) {
			node.setName(maxC);
			return node;
		}
		Gain gain = new Gain(datas, attrList);
		int bestAttrIndex = gain.bestGainAttrIndex();
		ArrayList<String> rules = gain.getValues(datas, bestAttrIndex);
		node.setRule(rules);
		node.setName(attrList.get(bestAttrIndex));
		if(rules.size() > 2){ //?此处有待商榷
			attrList.remove(bestAttrIndex);
		}
		for (int i = 0; i < rules.size(); i++) {
			String rule = rules.get(i);
			ArrayList<ArrayList<String>> di = gain.datasOfValue(bestAttrIndex, rule);
			for (int j = 0; j < di.size(); j++) {
				di.get(j).remove(bestAttrIndex);
			}
			if (di.size() == 0) {
				TreeNode leafNode = new TreeNode();
				leafNode.setName(maxC);
				leafNode.setDatas(di);
				leafNode.setCandAttr(attrList);
				node.getChild().add(leafNode);
			} else {
				TreeNode newNode = buildTree(di, attrList);
				node.getChild().add(newNode);
			}
			
		}
		return node;
	}
}