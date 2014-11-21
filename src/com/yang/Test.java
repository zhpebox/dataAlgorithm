package com.yang;

import java.util.*;
import java.io.File;
import com.dao.AssociationDao;
import com.bean.*;

public class Test {

	public void generateSubSet(){
		
		String test = "ab";
		int length = test.length();
		
		char numberArr[] = new char[length];
		
		for(int i=0;i<numberArr.length;i++){
			
			numberArr[i] = test.charAt(i);
			
		}
		
		int arr[][] = new int[length][length];
		
		for(int i=0;i<arr.length;i++){
			
			for(int j=0;j<arr[i].length;j++){
				
				if(i==j){
					
					arr[i][j] = 0;
				}else{
					arr[i][j] = 1;
				}
				
			}
			
		}
		
		String subSet[] = new String[length];
		
        for(int i=0;i<arr.length;i++){
			
        	String set = "";
        	        	
			for(int j=0;j<arr[i].length;j++){
				
				if(i!=j){
					
					set += numberArr[j]+"";
					
				}
				
				
				
			}
			
			subSet[i] = set;
			
			
			
        }
        
        
		for(int i=0;i<subSet.length;i++){
			
			System.out.println(subSet[i]);
			
		}
		
		
		//abc abd acd bcd 
		
		//2 - 4 - 8 - 16
		
		//abc  
		//ab ac bc
		
		//ab
		//a b
		
		//abcde
		//abcd abce abde acde bcde

		
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		System.out.println(System.getProperty("user.home"));//用户的主目录
//		System.out.println(System.getProperty("user.name"));//用户账户名
		//System.out.println(System.getProperty("user.dir"));//用户当前访问路径

//		String path = System.getProperty("user.dir");
//		String filePath = path +"//data//123.csv";
//		File file = new File(filePath);
		//System.out.println(file.exists());
		
		AssociationDao dao = new AssociationDao();
		List<AssociationBean> list = dao.getAll();
		//System.out.println("size:"+list.size());
		
		TreeSet<String> product = new TreeSet<String>();
		for(AssociationBean bean : list){
			product.add(bean.getProduct());
		}
		
		System.out.println("product size:"+product.size());
		char ch = 'a';
		Map map = new HashMap();
		for(String p : product){
			System.out.println(p);
			map.put(p,ch);
			ch++;
		}
		System.out.println("------------------");
		System.out.println("map:");
		Set set = map.keySet();
		Iterator iterator = set.iterator();
		
//		Collection collection = map.values();
//		Iterator iterator = collection.iterator();
		while(iterator.hasNext()){
			Object key = iterator.next();
		       System.out.println(key+":"+map.get(key));
		}
		
		//构建事物数据库
		//String[] transSet = { "abe", "bd", "bc", "abd", "ac", "bc","ac","abce","abc"};// 事务集合
		String[] transSet = new String[1001];
		for(int i=0;i<transSet.length;i++){
			String number = "";
			for(AssociationBean bean : list){
				
				if(i==bean.getCustomerId()){
					
					String key = bean.getProduct();
					String v = map.get(key).toString();
					number += v;
				}
				
			}
			
			transSet[i] = number;
			
		}
		System.out.println("------------");
        System.out.println("tranSet:");
		for(int i=0;i<transSet.length;i++){
			System.out.println(i+"+"+transSet[i]);
		}
		
		
	}

}




