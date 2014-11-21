package org.trie;
/** 
 * trie树接口 
 * @author Rowen 
 * @qq 443773264 
 * @mail luowen3405@163.com 
 * @blog blog.csdn.net/luowen3405 
 * @data 2011.03.31 
 */
public interface Trie {
	
	/**
	 * 由词典文件构造trie树
	 * @param dictName
	 */
	public void build(String dictName);
	
	/**
	 * 在trie树中查找一个单词
	 * @param word
	 * @return 
	 */
	public boolean search(String word);
	
	/**
	 * 插入一个单词到trie树中
	 * @param word
	 * @return 
	 */
	public boolean insert(String word);
	
	/**
	 * 从trie树中删除一个单词
	 * @param word
	 * @return 
	 */
	public boolean delete(String word);
	
	/**
	 * 将trie树保存到文件中
	 * @param path
	 */
	public void save(String path);
	
	/**
	 * 从文件中读取trie树
	 * @param path
	 */
	public void read(String path);
}