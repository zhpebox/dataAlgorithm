package com.dao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.bean.AssociationBean;

public class AssociationDao {
	
	public List<AssociationBean> getAll(){
		
		List<AssociationBean> list = new ArrayList<AssociationBean>();
		try{
			String path = System.getProperty("user.dir");
			String filePath = path +"//data//123.csv";
			FileReader fr = new FileReader(filePath);
			BufferedReader br = new BufferedReader(fr);
			String line = "";
			while((line=br.readLine())!=null){
				
				String info[] = line.split(",");
				//System.out.println("line:"+line);
				if(info!=null){
				AssociationBean bean = new AssociationBean();
				//System.out.println(info[0]);
//				System.out.println(info[1]);
//				System.out.println(info[2]);
//				System.out.println(info[3]);
				//bean.setId(Integer.parseInt(info[0]));
				bean.setCustomerId(Integer.parseInt(info[1]));
				bean.setTimeId(Integer.parseInt(info[2]));
				bean.setProduct(info[3]);
				list.add(bean);
				}
				
			}
			br.close();
			fr.close();
			
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	
		return list;
		
	}
	

}
