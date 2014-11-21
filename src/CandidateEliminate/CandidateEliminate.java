package CandidateEliminate;
import java.util.ArrayList;
import java.util.Map;
/**
 * 候选消除算法
 * @author Rowen
 * @qq 443773264
 * @mail luowen3405@163.com
 */
public class CandidateEliminate {
	private int nCandAttr; //候选属性个数
	private Map<Integer, ArrayList<String>> datas; //待训练的数据集
	private ArrayList<ArrayList<String>> GH; //泛化概念集
	private ArrayList<ArrayList<String>> SH; //特化概念集
	public CandidateEliminate(int nCandAttr, Map<Integer, ArrayList<String>> datas) {
		this.nCandAttr = nCandAttr;
		this.datas = datas;
		GH = new ArrayList<ArrayList<String>>();
		SH = new ArrayList<ArrayList<String>>();
		initGHSH();
	}
	public Map<Integer, ArrayList<String>> getDatas() {
		return datas;
	}
	public void setDatas(Map<Integer, ArrayList<String>> datas) {
		this.datas = datas;
	}
	public int getNCandAttr() {
		return nCandAttr;
	}
	public void setNCandAttr(int candAttr) {
		nCandAttr = candAttr;
	}
	/**
	 * 初始化泛化概念集GH和特化概念集SH
	 */
	public void initGHSH() {
		ArrayList<String> maxSH = new ArrayList<String>();
		for (int i = 0; i < nCandAttr; i++) {
			maxSH.add("-1"); //-1表示最特化
		}
		ArrayList<String> maxGH = new ArrayList<String>();
		for (int i = 0; i < nCandAttr; i++) {
			maxGH.add("?"); //?表示最泛化
		}
		SH.add(maxSH);
		GH.add(maxGH);
	}
	/**
	 * 判断一个概念是否能够覆盖另一个概念或训练元组
	 * @param h1 概念
	 * @param h2 概念或训练元组
	 * @return 能够覆盖则返回true，反之则返回false
	 */
	public boolean include(ArrayList<String> h1, ArrayList<String> h2){
		boolean isInclude = true;
		String e1 = "";
		String e2 = "";
		for (int i = 0; i < nCandAttr; i++) {
			e1 = h1.get(i);
			e2 = h2.get(i);
			if(!e1.equals(e2) && !e1.equals("?")){
				isInclude = false;
				break;
			}
		}
		return isInclude;
	}
	
	/**
	 * 最小泛化一个概念
	 * @param sH 待泛化的概念
	 * @param data 当前训练元组
	 * @return 最小泛化后的概念
	 */
	public ArrayList<String> minGeneralize(ArrayList<String> sH, ArrayList<String> data){
		String h = "";
		String e = "";
		for (int i = 0; i < nCandAttr; i++) {
			h = sH.get(i);
			e = data.get(i);
			if (!h.equals(e)) {
				h = (h.equals("-1")) ? e : "?";
				sH.set(i, h);
			}
		}
		return sH;
	}
	
	/**
	 * 最小特化一个概念
	 * @param gH 待特化的概念
	 * @param data 当前训练元组
	 * @return 最小特化后的概念
	 */
	public ArrayList<ArrayList<String>> minSpecialize(ArrayList<String> gH, ArrayList<String> data){
		ArrayList<ArrayList<String>> minS = new ArrayList<ArrayList<String>>();
		String h = "";
		String e = "";
		ArrayList<String> tempgH = null;
		ArrayList<String> dv = new ArrayList<String>();
		for (int i = 0; i < nCandAttr; i++) {
			tempgH = new ArrayList<String>(gH);
			h = tempgH.get(i);
			e = data.get(i);
			if (!h.equals(e)) {
				if (h.equals("?")) {
					dv = attrDiffValues(i);
					for (int j = 0; j < dv.size(); j++) {
						if(!dv.get(j).equals(e)){
							tempgH.set(i, dv.get(j));
							minS.add(tempgH);
						}
					}
				}
			}
		}
		return minS;
	}
	
	/**
	 * 获取某一指定属性列上的不同值（用于对泛化概念进行最小特化）
	 * @param nAttrIndex 指定的属性列索引
	 * @return 指定属性列上的不同值集合
	 */
	public ArrayList<String> attrDiffValues(int nAttrIndex){
		ArrayList<String> dv = new ArrayList<String>();
		String e = "";
		for (int i = 0; i < datas.size(); i++) {
			e = datas.get(i).get(nAttrIndex);
			if (!dv.contains(e)) {
				dv.add(e);
			}
		}
		return dv;
	}
	
	/**
	 * 检查最小泛化后的特化概念的泛化程度是否小于泛化集中的所有泛化概念
	 * @param sH 最小泛化后的特化概念
	 * @param tempGH 泛化概念集
	 * @return 是则返回true，否则返回false
	 */
	public boolean checkGeneralize(ArrayList<String> sH, ArrayList<ArrayList<String>> tempGH){
		boolean isRight = true;
		for (int i = 0; i < tempGH.size(); i++) {
			ArrayList<String> gH = tempGH.get(i);
			if(!include(gH, sH)){
				isRight = false;
			}
		}
		return isRight;
	}
	
	/**
	 * 检查最小特化后的泛化概念的特化程度是否小于特化集中的所有特化概念
	 * @param gH 最小特化后的泛化概念
	 * @param tempSH 特化概念集
	 * @return 是则返回true，否则返回false
	 */
	public boolean checkSpecialize(ArrayList<String> gH, ArrayList<ArrayList<String>> tempSH){
		boolean isRight = true;
		for (int i = 0; i < tempSH.size(); i++) {
			ArrayList<String> sH = tempSH.get(i);
			if(!include(gH, sH)){
				isRight = false;
			}
		}
		return isRight;
	}
	/**
	 * 对所有的训练元组挖掘概念集
	 * @return 所有挖掘到的特化概念集和泛化概念集的集合
	 */
	public ArrayList<ArrayList<String>> miningConcepts(){
		ArrayList<ArrayList<String>> total = new ArrayList<ArrayList<String>>();
		ArrayList<String> data = null;
		for (int i = 0; i < datas.size(); i++) {
			data = datas.get(i);
			if (data.get(nCandAttr).toLowerCase().equals("yes")) {
				for (int j = 0; j < GH.size(); j++) {
					if (!include(GH.get(j), data)) {
						GH.remove(j);
					}
				}
				for (int j = 0; j < SH.size(); j++) {
					if(!include(SH.get(j), data)){
						ArrayList<String> rm = SH.remove(j);
						ArrayList<String> mg = minGeneralize(rm, data);
						if (checkGeneralize(mg, GH)) {
							SH.add(mg);
						}
					}
				}
			} else {
				for (int j = 0; j < SH.size(); j++) {
					if (include(SH.get(j), data)) {
						SH.remove(j);
					}
				}
				for (int j = 0; j < GH.size(); j++) {
					if (include(GH.get(j), data)) {
						ArrayList<String> rm = GH.remove(j);
						ArrayList<ArrayList<String>> ms = minSpecialize(rm, data);
						for (int k = 0; k < ms.size(); k++) {
							if(checkSpecialize(ms.get(k), SH)){
								GH.add(ms.get(k));
							}
						}
					}
				}
			}
		}
		total.addAll(SH);
		total.addAll(GH);
		return total;
	}
}