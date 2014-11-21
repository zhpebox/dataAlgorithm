package org.trie.prefix;
import java.util.HashMap;
/** 
 * ǰ׺����֧����� 
 * @author Rowen 
 * @qq 443773264 
 * @mail luowen3405@163.com 
 * @blog blog.csdn.net/luowen3405 
 * @data 2011.03.31 
 */
public class BranchNode extends Node {
	public BranchNode(char c) {
		super.setC(c);
		super.setType(1);
		super.setChildren(new HashMap<String, Node>());
	}
}
