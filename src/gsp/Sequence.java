package gsp;   
   
import java.util.ArrayList;   
   
   
   
   
   
   
   
   
   
/**  
  
 * <title>������</title>  
  
 * ������Ϣ�Ͳ����࣬�ڱ����н������бȽϡ�֧�ֶȼ���  
  
 * �Լ����������е�Ԫ�أ���Ŀ����  
  
 * @author guangqingzhong  
  
 *  
  
 */   
   
public class Sequence {   
   
    private int support; //�����������ݿ��е�֧�ּ���    
   
    private ArrayList<Element> sequence; //���Ԫ������    
   
       
   
    /**  
  
     * ���������Ĺ��췽��  
  
     * ʵ����Sequence����ʱ��ͬʱ��ʼ���ö����֧�ּ�����sequence����  
  
     *  
  
     */   
   
    public Sequence() {   
   
        this.sequence = new ArrayList<Element>();   
   
        this.support = 0;   
   
    }   
   
       
   
    /**  
  
     * �������Ĺ��췽��  
  
     * �����������ж���s�е�����Ԫ�ص��������sequence������  
  
     * ����ʼ��֧�ּ���  
  
     * @param s                Sequence����  
  
     */   
   
    public Sequence(Sequence s) {   
   
        this.sequence = new ArrayList<Element>();   
   
        this.support = 0;   
   
        //����s�е�����Ԫ��    
   
        for (int i = 0; i < s.size(); i++) {   
   
            this.sequence.add(s.getElement(i).clone());   
   
        }   
   
    }   
   
       
   
    /**  
  
     * ����µ�Ԫ��  
  
     * ����������������µ�Ԫ��  
  
     * @param e           Element e -- ����ӵ�Ԫ��  
  
     */   
   
    public void addElement(Element e) {   
   
        this.sequence.add(e);   
   
    }   
   
       
   
    /**  
  
     * ����Ԫ��  
  
     * ��������λ��index�����µ�Ԫ��  
  
     * @param index          ��Ҫ�����Ԫ��λ��  
  
     * @param e              Element e -- �������Ԫ��  
  
     */   
   
    public void insertElement(int index,Element e){   
   
        this.sequence.add(index,e);   
   
    }   
   
       
   
    /**  
  
     * ɾ��Ԫ��  
  
     * ɾ��λ��index�ϵ�Ԫ��  
  
     * @param index           λ�����  
  
     * @return                ����ɾ�����sequence  
  
     */   
   
    public Element removeElement(int index){   
   
        if(index<this.sequence.size()){   
   
            return this.sequence.remove(index);   
   
        }else    
   
            return null;   
   
    }   
   
       
   
    /**  
  
     * ��ȡ���е�Ԫ��  
  
     * ��ȡ��index��Ԫ��  
  
     * @param index           Ԫ���������е�λ��  
  
     * @return                ���ظ�Ԫ��  
  
     */   
   
    public Element getElement(int index) {   
   
        if (index >= 0 && index < this.sequence.size()) {   
   
            return this.sequence.get(index);   
   
        } else {   
   
            System.err.println("index outof bound in Seuqence.getElement()");   
   
            return null;   
   
        }   
   
    }   
   
       
   
    /**  
  
     * ��ȡ����Ԫ��  
  
     * �������ж����sequence���ԣ�Ҳ��������Ԫ�صļ���  
  
     * @return         ArrayList -- ����Ԫ�طŵ�ArrayList��  
  
     */   
   
    public ArrayList<Element> getElements() {   
   
        return this.sequence;   
   
    }   
   
       
   
    /**  
  
     * ��ȡ���д�С  
  
     * @return              ���д�С  
  
     */   
   
    public int size() {   
   
        return this.sequence.size();   
   
    }   
   
   
    
   
   
   /**  
  
    * �Ƚ����м��Ԫ��  
  
    * �����ݵĲ������ж����뱾���ж���Ƚ�  
  
    * ���Ƿ�����ͬ��Ԫ��  
  
    * @param seqs                  ���Ƚϵ�����  
  
    * @return                      true--������ͬԪ�� false--��������ͬԪ��  
  
    */    
   
    public boolean notInSeqs(ArrayList<Sequence> seqs) {   
   
        Sequence s;   
   
        for (int i=0;i<seqs.size();i++) {   
   
            s=seqs.get(i);   
   
            //���÷���isSubsequenceOf()�Ƚ�    
   
            if (this.isSubsequenceOf(s) && s.isSubsequenceOf(this)){   
   
                return false;   
   
            }   
   
   
    
   
   
        }   
   
        return true;   
   
    }   
   
       
   
    /*  
  
     * �Ƚ��������Ƿ�����ͬ��Ԫ��  
  
     */   
   
    public boolean isSubsequenceOf(Sequence s) {   
   
        int i = 0, j = 0;   
   
        while (j < s.size() && i < this.sequence.size()) {   
   
            if (this.getElement(i).isContainIn(s.getElement(j))) {   
   
                i++;   
   
                j++;   
   
                if (i == this.sequence.size()) {   
   
                    return true;   
   
                }   
   
   
    
   
   
            } else {   
   
                j++;   
   
            }   
   
        }   
   
        return false;   
   
    }   
   
       
   
    /**  
  
     * ����֧�ּ���  
  
     *  
  
     */   
   
    public void incrementSupport() {   
   
        this.support++;   
   
    }   
   
       
   
    /**  
  
     * ��ȡ֧�ּ���  
  
     * @return  
  
     */   
   
    public int getSupport() {   
   
        return this.support;   
   
    }   
   
       
   
    /**  
  
     * ��дtoString()  
  
     * �������ʱ���ַ�����  
  
     *   
  
     */   
   
    public String toString() {   
   
        StringBuffer s = new StringBuffer();   
   
        s.append("<");   
   
        for (int i = 0; i < this.sequence.size(); i++) {   
   
            s.append(this.sequence.get(i));   
   
            if (i != this.sequence.size() - 1) {   
   
                s.append(" ");   
   
            }   
   
        }   
   
        s.append(">");   
   
        return s.toString();   
   
    }   
   
}   

