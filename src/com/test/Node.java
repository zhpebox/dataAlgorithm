package com.test;

import java.util.*;

public class Node {
	
	private String name;
	private int count;
	private List<Node> list = new ArrayList<Node>();
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List<Node> getList() {
		return list;
	}
	public void setList(List<Node> list) {
		this.list = list;
	}
	
	public void addNode(Node node){
		
		this.list.add(node);
		
	}
	
	public String showInfo(){
		
		
		return name+","+count;
	}
	
	
	//节点是否存在
	public boolean isExist(String name){
		
		boolean flag = false;
		for(Node node : list){
			if(node.getName().equals(name)){
				
				flag = true;
				break;
			}
		}
		
		return flag;
		
	}
	
	public Node findNodeByName(String name){
		
		Node node = new Node();
		for(Node n : list){
			if(n.getName().equals(name)){
				
				node = n;
				break;
			}
		}
		return node;
		
	}

}




