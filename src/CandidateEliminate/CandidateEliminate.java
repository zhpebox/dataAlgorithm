package CandidateEliminate;
import java.util.ArrayList;
import java.util.Map;
/**
 * ��ѡ�����㷨
 * @author Rowen
 * @qq 443773264
 * @mail luowen3405@163.com
 */
public class CandidateEliminate {
	private int nCandAttr; //��ѡ���Ը���
	private Map<Integer, ArrayList<String>> datas; //��ѵ�������ݼ�
	private ArrayList<ArrayList<String>> GH; //�������
	private ArrayList<ArrayList<String>> SH; //�ػ����
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
	 * ��ʼ���������GH���ػ����SH
	 */
	public void initGHSH() {
		ArrayList<String> maxSH = new ArrayList<String>();
		for (int i = 0; i < nCandAttr; i++) {
			maxSH.add("-1"); //-1��ʾ���ػ�
		}
		ArrayList<String> maxGH = new ArrayList<String>();
		for (int i = 0; i < nCandAttr; i++) {
			maxGH.add("?"); //?��ʾ���
		}
		SH.add(maxSH);
		GH.add(maxGH);
	}
	/**
	 * �ж�һ�������Ƿ��ܹ�������һ�������ѵ��Ԫ��
	 * @param h1 ����
	 * @param h2 �����ѵ��Ԫ��
	 * @return �ܹ������򷵻�true����֮�򷵻�false
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
	 * ��С����һ������
	 * @param sH �������ĸ���
	 * @param data ��ǰѵ��Ԫ��
	 * @return ��С������ĸ���
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
	 * ��С�ػ�һ������
	 * @param gH ���ػ��ĸ���
	 * @param data ��ǰѵ��Ԫ��
	 * @return ��С�ػ���ĸ���
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
	 * ��ȡĳһָ���������ϵĲ�ֵͬ�����ڶԷ������������С�ػ���
	 * @param nAttrIndex ָ��������������
	 * @return ָ���������ϵĲ�ֵͬ����
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
	 * �����С��������ػ�����ķ����̶��Ƿ�С�ڷ������е����з�������
	 * @param sH ��С��������ػ�����
	 * @param tempGH �������
	 * @return ���򷵻�true�����򷵻�false
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
	 * �����С�ػ���ķ���������ػ��̶��Ƿ�С���ػ����е������ػ�����
	 * @param gH ��С�ػ���ķ�������
	 * @param tempSH �ػ����
	 * @return ���򷵻�true�����򷵻�false
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
	 * �����е�ѵ��Ԫ���ھ���
	 * @return �����ھ򵽵��ػ�����ͷ�������ļ���
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