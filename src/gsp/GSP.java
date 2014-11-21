package gsp;   
   
import java.util.ArrayList;   
   
import java.util.Map;   
   
import java.util.HashMap;   
   
/**  
  
 * <title>GSP�㷨ʵ����</title>  
  
 * ����Ϊ�����࣬�ڱ�����ʵ����GSP�㷨  
  
 * @author guangqingzhong  
  
 *  
  
 */   
   
public class GSP {   
   
    private ArrayList<Sequence> c; //����Ϊi�ĺ�ѡ����ģʽ    
   
    private ArrayList<Sequence> l; //����Ϊi������ģʽ    
   
    private ArrayList<Sequence> result;   
   
    private SeqDB db;   
   
    private int support;   
   
       
   
   /**  
  
    * ���췽��  
  
    * ��ʵ����GSP����ʱ��ͬʱ��ֵ֧�ֶ�  
  
    * ����ȡ���м��ͳ�ʼ������ģʽ���  
  
    * @param support           ֧�ֶ�  
  
    */   
   
    public GSP(int support) {   
   
        this.support = support;                   //��ֵ֧�ֶ�    
   
        this.db = new SeqDB();                    //��SeqDB���л�ȡ���úõ����м�    
   
        this.result = new ArrayList<Sequence>();  //��ʼ������ģʽ�������    
   
    }   
   
       
   
    /**  
  
     * ��������ģʽ  
  
     * ���ķ������ڸ÷����е������Ӻͼ�֦��������������õ�����ģʽ�ŵ�result��  
  
     * @return     ����ģʽ  
  
     */   
   
    public ArrayList getSequences() {   
   
        long start = System.currentTimeMillis();   
   
        //���ó�ʼ������    
   
        initialize();   
   
        System.out.println("����ģʽL(1) Ϊ��" +l);   
   
        System.out.println(".................................................");   
   
        for (int i = 0; i < l.size(); i++) {   
   
               //�����������Ӳ�����ĺ�ѡ��    
   
               genCandidate();         
   
            if (!(c.size() > 0)) {   
   
                break;   
   
            }            
   
            System.out.println("��֦ǰ��ѡ���Ĵ�СΪ��"+c.size()+" ��ѡ��cΪ��"+c);   
   
            //���м�֦����    
   
            pruneC();   
   
            System.out.println("��֦���ѡ���Ĵ�СΪ��"+c.size()+" ��ѡ��cΪ��"+c);   
   
            //��������ģʽ    
   
            generateL();   
   
            System.out.println("����ģʽL(" + (i + 2) + ") Ϊ��" +l);   
   
            addToResult(l);   
   
            System.out.println(".................................................");   
   
        }   
   
        long end = System.currentTimeMillis();   
   
        System.out.println("���㻨��ʱ��" + (end - start) + "����!");   
   
        return this.result;   
   
    }   
   
   
    
   
   
    /*  
  
     * ��ʼ������  
  
     * ��ȡ���úõ����м�  
  
     */   
   
    private void initialize() {   
   
        Map<Integer, Integer> can = new HashMap<Integer, Integer>();   
   
        //�������м��е���������    
   
        for (Sequence s : db.getSeqs()) {   
   
               //���������е�������Ŀ��    
   
            for (Element e : s.getElements()) {   
   
                   //������Ŀ���е�������Ŀ    
   
                for (int i : e.getItems()) {   
   
                       //�Ƚ���Ŀ�ĳ��ִ�������������������֧�ֶȱȽ�    
   
                    if (can.containsKey(i)) {   
   
                        int count = can.get(i).intValue() + 1;   
   
                        can.put(i, count);   
   
                    } else {   
   
                        can.put(i, 1);   
   
                    }   
   
                }   
   
            }   
   
        }   
   
        this.l = new ArrayList<Sequence>();   
   
        //���ڲ����ĺ�ѡ�������֧�ֶȴ�����С֧�ֶ���ֵ������ӵ�����ģʽL��    
   
        for (int i : can.keySet()) {   
   
            if (can.get(i).intValue() >= support) {   
   
                Element e = new Element(new int[] {i});   
   
                Sequence seq = new Sequence();   
   
                seq.addElement(e);   
   
                this.l.add(seq);   
   
            }   
   
        }   
   
        //����һ��Ƶ������ģʽ����������    
   
        this.addToResult(l);   
   
          
   
    }   
   
       
   
    /*  
  
     * �����������Ӳ�����ĺ�ѡ��  
  
     *   
  
     */   
   
    private void genCandidate() {   
   
        this.c = new ArrayList<Sequence>();   
   
        //�������Ӽ�L�������Ӳ���    
   
        for (int i = 0; i < this.l.size(); i++) {   
   
            for (int j = i; j < this.l.size(); j++) {   
   
                this.joinAndInsert(l.get(i), l.get(j));   
   
                if (i != j) {   
   
                    this.joinAndInsert(l.get(j), l.get(i));   
   
                }   
   
            }   
   
        }   
   
    }   
   
   
    
   
   
   /*  
  
    * �����Ӽ��������Ӳ���  
  
    */   
   
    private void joinAndInsert(Sequence s1, Sequence s2) {   
   
        Sequence s, st;   
   
        //ȥ����һ��Ԫ��    
   
        Element ef = s1.getElement(0).getWithoutFistItem();    
   
        //ȥ�����һ��Ԫ��    
   
        Element ee = s2.getElement(s2.size() - 1).getWithoutLastItem();   
   
        int i = 0, j = 0;   
   
        if (ef.size() == 0) {   
   
            i++;   
   
        }   
   
        for (; i < s1.size() && j < s2.size(); i++, j++) {   
   
            Element e1, e2;   
   
   
    
   
   
            if (i == 0) {   
   
                e1 = ef;   
   
            } else {   
   
                e1 = s1.getElement(i);   
   
            }   
   
            if (j == s2.size() - 1) {   
   
                e2 = ee;   
   
            } else {   
   
                e2 = s2.getElement(j);   
   
            }   
   
            if (!e1.equalsTo(e2)) {   
   
                return;   
   
            }   
   
        } //end of for    
   
   
    
   
   
        s = new Sequence(s1);   
   
        //��s2�����һ��Ԫ����ӵ�s��    
   
        (s.getElement(s.size() - 1)).addItem(s2.getElement(s2.size() - 1).   
   
                                            getLastItem());   
   
        //�����ѡ����û��s������ӵ���ѡ��    
   
        if (s.notInSeqs(c)) {   
   
            c.add(s);   
   
        }   
   
        st = new Sequence(s1);   
   
        //��s2�����һ��Ԫ����ӵ�st��    
   
        st.addElement(new Element(new int[] {s2.getElement(s2.size() - 1).   
   
                                  getLastItem()}));   
   
        //�����ѡ����û��st������ӵ���ѡ��    
   
        if (st.notInSeqs(c)) {   
   
            c.add(st);   
   
        }   
   
        return;   
   
    }   
   
   
    
   
   
   
    
   
   
   
    
   
   
    /*  
  
     * ��֦����  
  
     * ��ÿ����ѡ���е������������ǲ���Ƶ������  
  
     * �������ȡԪ�أ�ֻȥ����һ����Ŀ��Ȼ���ǲ�������Ӧ��Ƶ��������l�С�  
  
     * ���Ԫ��ֻ��һ����Ŀ����ȥ����Ԫ������Ӧ�жϡ�  
  
     */   
   
    private void pruneC() {   
   
        Sequence s;   
   
        //���������е�����Ԫ��    
   
        for (int i = 0; i < this.c.size();i++) {   
   
            s = c.get(i);   
   
            //����Ԫ���е�������Ŀ    
   
            for (int j = 0; j < s.size(); j++) {   
   
                Element ce = s.getElement(j);   
   
                boolean prune=false;   
   
                //ֻ��һ��Ԫ�ص����    
   
                if (ce.size() == 1) {   
   
                    s.removeElement(j);   
   
                    //��������в�������ģʽ�������Ӻ�ѡ����ģʽ��ɾ�����������    
   
                    if (s.notInSeqs(this.l)) {   
   
                        prune=true;   
   
                    }   
   
                    s.insertElement(j, ce);   
   
                } else {   
   
                    for (int k = 0; k < ce.size(); k++) {   
   
                        int item=ce.removeItem(k);   
   
                        //��������в�������ģʽ�������Ӻ�ѡ����ģʽ��ɾ�����������    
   
                        if (s.notInSeqs(this.l)) {   
   
                            prune=true;   
   
                        }   
   
                        ce.addItem(item);   
   
                    }   
   
                }   
   
                //�����֦���򽫸�����ɾ��    
   
                if(prune){   
   
                    c.remove(i);   
   
                    i--;   
   
                    break;   
   
                }   
   
            }   
   
        }    
   
    }   
   
   
    
   
   
    /*  
  
     * ��������ģʽL  
  
     * �����Ѿ��������Ӻͼ�֦������ĺ�ѡ���м�  
  
     */   
   
    private void generateL() {   
   
        this.l = new ArrayList<Sequence>();   
   
        for (Sequence s : db.getSeqs()) {   
   
            for (Sequence seq : this.c) {   
   
                if (seq.isSubsequenceOf(s)) {   
   
                       //֧�ֶȼ���    
   
                    seq.incrementSupport();   
   
                }   
   
            }   
   
        }   
   
        for (Sequence seq : this.c) {   
   
               //������С֧�ֶ���ֵ�ķŵ�����ģʽ��    
   
            if (seq.getSupport() >= this.support) {   
   
                this.l.add(seq);   
   
            }   
   
        }   
   
    }   
   
       
   
    /*  
  
     * ����Ƶ������ģʽ��������  
  
     */   
   
    private void addToResult(ArrayList<Sequence> l) {    
   
        for (int i = 0; i < l.size(); i++) {   
   
            this.result.add(l.get(i));   
   
        }   
   
   
    
   
   
    }   
   
       
   
    /**  
  
     * ��������������Ϣ  
  
     * �����Ҫ��������ģʽ�����������Լ���С֧�ֶȣ�������  
  
     */   
   
   public void outputInput() {   
   
          System.out.println("��С֧�ֶȼ���Ϊ��" + this.support);   
   
          System.out.println("��������м���Ϊ��");   
   
          System.out.println(db.getSeqs());   
   
          System.out.println();   
   
   }   
   
}   
   

