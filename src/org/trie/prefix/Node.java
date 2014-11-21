package org.trie.prefix;
import java.util.Map;
/** 
 * trie ˜Ω·µ„¿‡ 
 * @author Rowen 
 * @qq 443773264 
 * @mail luowen3405@163.com 
 * @blog blog.csdn.net/luowen3405 
 * @data 2011.03.31 
 */
public class Node {
	private char c; // 
	private int type; // 
	private Map<String, Node> children; //
	public Node() {
		this.c = ' ';
		this.type = -1;
		this.children = null;
	}
	public char getC() {
		return c;
	}
	public void setC(char c) {
		this.c = c;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public void addChild(char c, Node child) {
		children.put(c + "", child);
	}
	public Map<String, Node> getChildren() {
		return children;
	}
	public void setChildren(Map<String, Node> children) {
		this.children = children;
	}
	public boolean contains(char c) {
		if (children.containsKey(c + "")) {
			return true;
		}
		return false;
	}
	public Node next(char c) {
		return children.get(c + "");
	}
	public int sizeOfChildren() {
		return children.size();
	}
	public void removeChild(char c) {
		children.remove(c + "");
	}
}