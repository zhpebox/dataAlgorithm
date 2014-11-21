package org.trie.prefix;
/** 
 * 前缀树叶结点类 
 * @author Rowen 
 * @qq 443773264 
 * @mail luowen3405@163.com 
 * @blog blog.csdn.net/luowen3405 
 * @data 2011.03.31 
 */
public class LeafNode extends Node {
	public LeafNode() {
		super.setC('p');
		//super.setC("</p><p><br></p>");
		super.setType(1);
	}
}
