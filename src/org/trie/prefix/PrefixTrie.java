package org.trie.prefix;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import org.trie.Trie;
/** 
 * Ç°×ºÊ÷Àà 
 * @author Rowen 
 * @qq 443773264 
 * @mail luowen3405@163.com 
 * @blog blog.csdn.net/luowen3405 
 * @data 2011.03.31 
 */
public class PrefixTrie implements Trie {
	private Node root;
	public PrefixTrie() {
		root = new BranchNode(' ');
	}
	public void build(String dictName) {
		BufferedReader reader = null;
		int i = 0;
		try {
			reader = new BufferedReader(new FileReader(new File(dictName)));
			String word = reader.readLine();
			while (word != null && !word.trim().equals("")) {
				i++;
				word += "$";
				insert(word);
				word = reader.readLine();
			}
			System.out.println("total words: " + i);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	public boolean insert(String word) {
		if (search(word)) {
			System.out.println("The word has already been added into the trie!");
			return false;
		} else {
			char[] chars = word.toCharArray();
			Node currentNode = root;
			char c;
			for (int i = 0; i < chars.length; i++) {
				c = chars[i];
				if (c == 'p') {
					Node leaf = new LeafNode();
					currentNode.addChild('p', leaf);
				}else{
					if (currentNode.contains(c)) {
						currentNode = currentNode.next(c);
					} else {
						Node branch = new BranchNode(c);
						currentNode.addChild(c, branch);
						currentNode = branch;
					}
				}
			}
			return true;
		}
	}
	public boolean delete(String word) {
		if(!search(word)){
			System.out.println("No such word exists in the trie!");
			return false;
		}else {
			char[] chars = word.toCharArray();
			Node formerNode = root;
			Node currentNode = root;
			char c;
			for (int i = 0; i < chars.length; i++) {
				c = chars[i];
				if (currentNode.sizeOfChildren() == 1) {
					formerNode.removeChild(currentNode.getC());
				}else if (c == 'p') {
					currentNode.removeChild(c);
				}else {
					formerNode = currentNode;
					currentNode = currentNode.next(c);
				}
			}
			return true;
		}
	}
	public boolean search(String word) {
		char[] chars = word.toCharArray();
		Node currentNode = root;
		char c;
		for (int i = 0; i < chars.length; i++) {
			c = chars[i];
			if (c == 'p' && currentNode.contains(c)) {
				return true;
			}else {
				if (currentNode.contains(c)) {
					currentNode = currentNode.next(c);
				} else {
					return false;
				}
			}
		}
		return false;
	}
	public void read(String path) {
	}
	public void save(String path) {
	}
}