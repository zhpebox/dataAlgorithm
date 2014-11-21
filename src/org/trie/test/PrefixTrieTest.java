package org.trie.test;
import java.io.File;
import org.trie.Trie;
import org.trie.prefix.PrefixTrie;
/** 
 * ǰ׺�������� 
 * @author Rowen 
 * @qq 443773264 
 * @mail luowen3405@163.com 
 * @blog blog.csdn.net/luowen3405 
 * @data 2011.03.31 
 */
public class PrefixTrieTest {
	public static void main(String[] args) {
		String path = new File("").getAbsolutePath() + File.separator + "dict//CoreDict.txt";
		Trie trie = new PrefixTrie();
		trie.build(path);
		System.out.println(trie.search("�л����񹲺͹�$"));
		System.out.println(trie.delete("�й�$"));
		System.out.println(trie.search("�й���$"));
		System.out.println(trie.search("��$"));
		System.out.println(trie.search("�й�$"));
	}
}