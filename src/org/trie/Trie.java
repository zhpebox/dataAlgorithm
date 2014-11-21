package org.trie;
/** 
 * trie���ӿ� 
 * @author Rowen 
 * @qq 443773264 
 * @mail luowen3405@163.com 
 * @blog blog.csdn.net/luowen3405 
 * @data 2011.03.31 
 */
public interface Trie {
	
	/**
	 * �ɴʵ��ļ�����trie��
	 * @param dictName
	 */
	public void build(String dictName);
	
	/**
	 * ��trie���в���һ������
	 * @param word
	 * @return 
	 */
	public boolean search(String word);
	
	/**
	 * ����һ�����ʵ�trie����
	 * @param word
	 * @return 
	 */
	public boolean insert(String word);
	
	/**
	 * ��trie����ɾ��һ������
	 * @param word
	 * @return 
	 */
	public boolean delete(String word);
	
	/**
	 * ��trie�����浽�ļ���
	 * @param path
	 */
	public void save(String path);
	
	/**
	 * ���ļ��ж�ȡtrie��
	 * @param path
	 */
	public void read(String path);
}