package Bayes;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
/**
 * 贝叶斯算法测试类
 * @author Rowen
 * @qq 443773264
 * @mail luowen3405@163.com
 * @blog blog.csdn.net/luowen3405
 * @data 2011.03.15
 */
public class TestBayes {
	/**
	 * 读取测试元组
	 * @return 一条测试元组
	 * @throws IOException
	 */
	public ArrayList<String> readTestData() throws IOException{
		ArrayList<String> candAttr = new ArrayList<String>();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		//BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("")));
		
		String str = "";
		while (!(str = reader.readLine()).equals("")) {
			StringTokenizer tokenizer = new StringTokenizer(str);
			while (tokenizer.hasMoreTokens()) {
				candAttr.add(tokenizer.nextToken());
			}
		}
		return candAttr;
	}
	
	/**
	 * 读取训练元组
	 * @return 训练元组集合
	 * @throws IOException
	 */
	public ArrayList<ArrayList<String>> readData() throws IOException {
		ArrayList<ArrayList<String>> datas = new ArrayList<ArrayList<String>>();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		
		
		//BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("")));
		
		String str = "";
		while (!(str = reader.readLine()).equals("")) {
			StringTokenizer tokenizer = new StringTokenizer(str);
			ArrayList<String> s = new ArrayList<String>();
			while (tokenizer.hasMoreTokens()) {
				s.add(tokenizer.nextToken());
			}
			datas.add(s);
		}
		return datas;
	}
	
	public static void main(String[] args) {
		TestBayes tb = new TestBayes();
		ArrayList<ArrayList<String>> datas = null;
		ArrayList<String> testT = null;
		Bayes bayes = new Bayes();
		try {
			System.out.println("请输入训练数据");
			datas = tb.readData();
			while (true) {
				System.out.println("请输入测试元组");
				testT = tb.readTestData();
				String c = bayes.predictClass(datas, testT);
				System.out.println("The class is: " + c);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
