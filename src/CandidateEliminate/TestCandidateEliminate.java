package CandidateEliminate;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
/**
 * ��ѡ�����㷨������
 * @author Rowen
 * @qq 443773264
 * @mail luowen3405@163.com
 */
public class TestCandidateEliminate {
	/**
	 * �ӿ���̨��ȡҪ���и����ھ��ѵ������
	 * 
	 * @param data�����ݼ�����
	 * @return map: ѵ����
	 * @throws IOException
	 */
	public Map<Integer, ArrayList<String>> readData(
			Map<Integer, ArrayList<String>> data) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		String str = "";
		while (!(str = reader.readLine()).equals("")) {
			StringTokenizer tokenizer = new StringTokenizer(str);
			String key = tokenizer.nextToken();
			ArrayList<String> s = new ArrayList<String>();
			while (tokenizer.hasMoreTokens()) {
				s.add(tokenizer.nextToken());
			}
			data.put(Integer.parseInt(key), s);
		}
		return data;
	}
	/**
	 * ���Գ������
	 * @param args
	 */
	public static void main(String[] args) {
		Map<Integer, ArrayList<String>> datas = new HashMap<Integer, ArrayList<String>>();
		TestCandidateEliminate t = new TestCandidateEliminate();
		try {
			t.readData(datas);
		} catch (IOException e) {
			System.out.println("IOException occurs when reading data!");
		}
		CandidateEliminate ce = new CandidateEliminate(5, datas);
		ArrayList<ArrayList<String>> concepts = ce.miningConcepts();
		ArrayList<String> concept = null;
		for (int i = 0; i < concepts.size(); i++) {
			concept = concepts.get(i);
			for (int j = 0; j < concept.size(); j++) {
				System.out.print(concept.get(j) + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}