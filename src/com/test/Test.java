package com.test;

import java.util.ArrayList;
import java.util.List;

public class Test {

	List<String> list1 = new ArrayList<String>();
	List<String> list2 = new ArrayList<String>();
	List<String> list3 = new ArrayList<String>();
	List<String> list4 = new ArrayList<String>();
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		try{
			//时间窗口
			int windowL = 3; 
			//最小支持度
			int min_sup = 2;
			
			Test t = new Test();
			t.list1.add("a");
			t.list1.add("ad");
			t.list1.add("d");
			t.list1.add("ad");
			
			t.list2.add("b");
			t.list2.add("bc");
			t.list2.add("e");
			t.list2.add("bc");
			
			t.list3.add("ef");
			t.list3.add("d");
			t.list3.add("dc");
			t.list3.add("d");
			
			
			
			NodeManage nm = new NodeManage(windowL,min_sup);
			System.out.println("第一次读数据");
			//第一次读数据
			nm.readData(t.list1,t.list2,t.list3);
			nm.output();
			//nm.tranverseMap();
			
			//删除第一个时间窗
//			List<String> list = new ArrayList<String>();
//			list.add("a");
//			list.add("ad");
//			list.add("d");
//			list.add("ad");
			nm.deleteTree(t.list1);
			
			System.out.println("删除第一个时间窗");
			nm.output();
			
			t.list4.add("d");
			t.list4.add("ae");
			t.list4.add("g");
			t.list4.add("g");
			
			nm.readDataPerOne(t.list4);
			System.out.println("第二次读数据");
			nm.output();
			
			
			
			
		}catch(Exception ex){
			
			ex.printStackTrace();
			
		}
		
		

	}

}
