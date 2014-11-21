package com.test;

import java.util.HashMap;
import java.util.*;

public class NodeManage {

	// 0 表示未访问过 1 表示访问过
	private HashMap<Node, Integer> map = new HashMap<Node, Integer>();
	private HashSet<String> set = new HashSet<String>();
	private HashMap<String, Integer> map1 = new HashMap<String, Integer>();
	List<String> list1 = new ArrayList<String>();
	List<String> list2 = new ArrayList<String>();
	List<String> list3 = new ArrayList<String>();
	List<String> list4 = new ArrayList<String>();
	
	private int windowL;
	private int min_sup;
	
	public NodeManage(int windowL,int min_sup){
		
		this.windowL = windowL;
		this.min_sup = min_sup;
	}
	
	
	public NodeManage(){
		
		
	}
	
	

	public void fillMap(String str) {

		// System.out.println("map1:"+map1.get("xyz"));
		if (map1.get(str) == null) {

			map1.put(str, 1);

		} else {

			map1.put(str, map1.get(str) + 1);

		}

	}

	public void tranverseMap() {

		System.out.println("tranverseMap:");

		for (String key : map1.keySet()) {

			System.out.println(key + ":" + map1.get(key));

		}

	}
	
	
	public void readDataPerOne(List<String> list){
		
		for (String s : list) {

			fillMap(s);
			if (s.length() > 1) {

				for (int i = 0; i < s.length(); i++) {

					fillMap(s.charAt(i) + "");
				}

			}

		}
		
	}
	
	//通用读数据的方法
	public void readData(List<String> list1,List<String> list2,List<String> list3){
		
		
		for (String s : list1) {

			fillMap(s);
			if (s.length() > 1) {

				for (int i = 0; i < s.length(); i++) {

					fillMap(s.charAt(i) + "");
				}

			}

		}
		
		for (String s : list2) {

			fillMap(s);
			if (s.length() > 1) {

				for (int i = 0; i < s.length(); i++) {

					fillMap(s.charAt(i) + "");
				}

			}

		}
		
		for (String s : list3) {

			fillMap(s);
			if (s.length() > 1) {

				for (int i = 0; i < s.length(); i++) {

					fillMap(s.charAt(i) + "");
				}

			}

		}
		
		
		
		
	}

	public void readData1() {

		list1.add("a");
		list1.add("ad");
		list1.add("d");
		list1.add("ad");

		for (String s : list1) {

			fillMap(s);
			if (s.length() > 1) {

				for (int i = 0; i < s.length(); i++) {

					fillMap(s.charAt(i) + "");
				}

			}

		}

		list2.add("b");
		list2.add("bc");
		list2.add("e");
		list2.add("bc");

		for (String s : list2) {

			fillMap(s);
			if (s.length() > 1) {

				for (int i = 0; i < s.length(); i++) {

					fillMap(s.charAt(i) + "");
				}

			}

		}

		list3.add("ef");
		list3.add("d");
		list3.add("dc");
		list3.add("d");

		for (String s : list3) {

			fillMap(s);
			if (s.length() > 1) {

				for (int i = 0; i < s.length(); i++) {

					fillMap(s.charAt(i) + "");
				}

			}

		}

	}

	public void readData2() {

		list4.add("d");
		list4.add("ae");
		list4.add("g");
		list4.add("g");
		
		
		list1 = list2;
		list2 = list3;
		list3 = list4;
		
		
		for (String s : list3) {

			fillMap(s);
			if (s.length() > 1) {

				for (int i = 0; i < s.length(); i++) {

					fillMap(s.charAt(i) + "");
				}

			}

		}
		
		

	}

	public Node init() {

		// 第一次读数据
		// readData1();

		// for (String s : list1) {
		//
		// set.add(s);
		// for (int i = 0; i < s.length(); i++) {
		//
		// set.add(s.charAt(i) + "");
		//
		// }
		//
		// }
		//
		// for (String s : list2) {
		//
		// set.add(s);
		// for (int i = 0; i < s.length(); i++) {
		//
		// set.add(s.charAt(i) + "");
		//
		// }
		//
		// }
		//
		// for (String s : list3) {
		//
		// set.add(s);
		// for (int i = 0; i < s.length(); i++) {
		//
		// set.add(s.charAt(i) + "");
		//
		// }
		//
		// }
		//
		// for (String s : set) {
		//
		// System.out.println("set:" + s);
		// // map.put(s, 0);
		// }

		Node root = new Node();
		root.setName("root");
		root.setCount(0);

		// 每次初始化
		map = new HashMap<Node, Integer>();

		map.put(root, 0);

		//System.out.println("initTree:");

		for (String key : map1.keySet()) {

			//System.out.println(key + ":" + map1.get(key));

			Node node = new Node();
			node.setName(key);
			node.setCount(map1.get(key));
			if (key.length() == 1) {

				if (root.isExist(key) == false) {
					
					if(node.getCount() >= this.min_sup){
					
					  root.addNode(node);
					  map.put(node, 0);
					}
				}

			} else {

				for (int i = 0; i < key.length(); i++) {

					// System.out.println("长度大于1:"+key.charAt(i)+"");
					Node n = new Node();
					n.setName(key.charAt(i) + "");
					n.setCount(map1.get(key.charAt(i) + ""));
					// System.out.println("name:"+n.getName());
					// System.out.println("count:"+n.getCount());

					// System.out.println("是否在根节点已经存在:"+root.isExist(n.getName()));

					// 存在 例如B,C
					if (root.isExist(n.getName()) == true) {
						// 找到这个节点

						// 把新生成的节点加到B或C中
						root.findNodeByName(n.getName()).addNode(node);
						
						if(node.getCount()>=this.min_sup){
							
							  root.addNode(node);
							  map.put(node, 0);
						}
						
						

					} else {
						// 不存在
						// 添加到根节点
						
						if(n.getCount()>=this.min_sup){
							
							root.addNode(n);
							map.put(n, 0);
						}
						
                        if(node.getCount()>=this.min_sup){
							
                        	// 添加子节点
    						n.addNode(node);
    						
    						map.put(node, 0);
						}
						
						
						
						

					}
				}

			}
			// else{
			//        		
			// for(int i=0;i<key.length();i++){
			//        			
			// Node n = new Node();
			// n.setName(key.charAt(i)+"");
			// n.setCount(map1.get(key.charAt(i)+""));
			//        			
			// if(root.isExist(n.getName())==false){
			//        				
			// root.addNode(n);
			// map.put(n, 0);
			// }
			// if(n.isExist(node.getName())==false){
			// n.addNode(node);
			// map.put(node, 0);
			// }
			//        			
			// }
			//        		
			// }
			//			
		}

		// Node a = new Node();
		// a.setName("a");
		// a.setCount(3);
		// root.addNode(a);
		//
		// Node b = new Node();
		// b.setName("b");
		// b.setCount(3);
		// root.addNode(b);
		//
		// Node c = new Node();
		// c.setName("c");
		// c.setCount(1);
		// root.addNode(c);
		//
		// Node d = new Node();
		// d.setName("d");
		// d.setCount(6);
		// root.addNode(d);
		//
		// Node e = new Node();
		// e.setName("e");
		// e.setCount(2);
		// root.addNode(e);
		//
		// Node f = new Node();
		// f.setName("f");
		// f.setCount(1);
		// root.addNode(f);
		//
		// Node ad = new Node();
		// ad.setName("ad");
		// ad.setCount(2);
		// a.addNode(ad);
		// d.addNode(ad);
		//
		// Node cd = new Node();
		// cd.setName("cd");
		// cd.setCount(1);
		// c.addNode(cd);
		// d.addNode(cd);
		//
		// Node bc = new Node();
		// bc.setName("bc");
		// bc.setCount(2);
		// b.addNode(bc);
		// c.addNode(bc);
		//
		// Node ef = new Node();
		// ef.setName("ef");
		// ef.setCount(1);
		// e.addNode(ef);
		// f.addNode(ef);

		// map.put(root, 0);
		// map.put(a, 0);
		// map.put(b, 0);
		// map.put(c, 0);
		// map.put(d, 0);
		// map.put(e, 0);
		// map.put(f, 0);
		// map.put(ad, 0);
		// map.put(cd, 0);
		// map.put(bc, 0);
		// map.put(ef, 0);

		return root;

	}

	public void output() {

		Node root = init();

		System.out.println(root.showInfo());
		// System.out.println(map.get(root));
		// map.put(root, 1);
		// System.out.println(map.get(root));

		System.out.println("tranverse graph:");
		tranverser(root);

	}

	public void tranverser(Node father) {

		// System.out.println("tranverser:");
		List<Node> nodes = father.getList();
		for (Node node : nodes) {

			if (node != null) {
				
				
				
				if (map.get(node)!=null && map.get(node) == 0) {

					map.put(node, 1);// 访问过
					System.out.println(node.showInfo());
					tranverser(node); // 递归

				}
			}

		}

	}

	// 删除树
	public void deleteTree(List<String> list) {

		// 对map1进行操作
		for (String str : list) {

			// if(map1.get(str)

			// 例如a,b,c
			if (str.length() == 1) {

				// 只有一个
				if (map1.get(str) == 1) {

					map1.remove(str); // 删除
				} else {

					map1.put(str, map1.get(str) - 1);// 个数减1

				}

			} else {

				// 例如ab,bc等等

				if (map1.get(str) == 1) {

					map1.remove(str); // 删除
				} else {

					map1.put(str, map1.get(str) - 1);// 个数减1

				}
				
				
				for(int i=0;i<str.length();i++){
					
					if (map1.get(str.charAt(i)+"") == 1) {

						map1.remove(str.charAt(i)+""); // 删除
					} else {

						map1.put(str.charAt(i)+"", map1.get(str.charAt(i)+"") - 1);// 个数减1

					}
					
				}
				
			}

		}

	}

}
