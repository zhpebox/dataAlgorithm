package gsp;   
   
import java.util.ArrayList;   
   
   
   
   
   
/**  
  
 * <title>���ݿ����м���</title>  
  
 * ���ڻ�ȡ�����ݿ���ɨ��������м�  
  
 * ���ﲢû�д����ݿ��ȡ���������õĳ�ʼ��  
  
 * @author guangqingzhong  
  
 *  
  
 */   
   
public class SeqDB {   
   
    private ArrayList<Sequence> seqs;  //���ж���    
   
       
   
    /**  
  
     * �޲������췽��  
  
     * ��ʼ�����м�  
  
     *  
  
     */   
   
    public SeqDB() {   
   
        this.seqs = new ArrayList<Sequence>();   
   
        Sequence s;   
   
        
   
        //<{1 5}{2}{3}{4}>    
   
        s = new Sequence();   
   
        s.addElement(new Element(new int[] {1, 5}));   
   
        s.addElement(new Element(new int[] {2}));   
   
        s.addElement(new Element(new int[] {3}));   
   
        s.addElement(new Element(new int[] {4}));   
   
        seqs.add(s);   
   
        //<{1}{3}{4}{3 5}>    
   
        s = new Sequence();   
   
        s.addElement(new Element(new int[] {1}));   
   
        s.addElement(new Element(new int[] {3}));   
   
        s.addElement(new Element(new int[] {4}));   
   
        s.addElement(new Element(new int[] {3, 5}));   
   
        seqs.add(s);   
   
        //<{1}{2}{3}{4}>    
   
        s = new Sequence();   
   
        s.addElement(new Element(new int[] {1}));   
   
        s.addElement(new Element(new int[] {2}));   
   
        s.addElement(new Element(new int[] {3}));   
   
        s.addElement(new Element(new int[] {4}));   
   
   
    
   
   
        seqs.add(s);   
   
        //<{1}{3}{5}>    
   
        s = new Sequence();   
   
        s.addElement(new Element(new int[] {1}));   
   
        s.addElement(new Element(new int[] {3}));   
   
        s.addElement(new Element(new int[] {5}));   
   
        seqs.add(s);   
   
        //<{4}{5}>    
   
        s = new Sequence();   
   
        s.addElement(new Element(new int[] {4}));   
   
       
   
        s.addElement(new Element(new int[] {4,5}));   
   
        seqs.add(s);   
   
    }   
   
       
   
    /**  
  
     * ��ȡ���м��Ĵ�С  
  
     * Ҳ���ǻ�ȡ�м�������  
  
     * @return         ���м���С  
  
     */   
   
    public int size(){   
   
        return this.seqs.size();   
   
    }   
   
       
   
    /**  
  
     * ��ȡ���м�  
  
     * @return               ���м�  
  
     */   
   
    public ArrayList<Sequence> getSeqs(){   
   
        return this.seqs;   
   
    }   
   
}   
