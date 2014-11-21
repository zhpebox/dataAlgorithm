package gsp;   
   
import java.util.ArrayList;   
   
   
   
   
   
/**  
  
 * <title>Ԫ�أ���Ŀ������</title>  
  
 * Ԫ����Ϣ�Ͳ����࣬����Ŀ����Ϊ��ʵ��������  
  
 * �����װ�˶�����Ŀ���Ļ�������  
  
 * @author guangqingzhong  
  
 *  
  
 */   
   
public class Element {   
   
    private ArrayList<Integer> itemset;//��ʾ��Ԫ�ص���Ŀ�������ֵ�������    
   
       
   
    /**  
  
     * �޲������췽��  
  
     * ��ʼ����Ŀ��  
  
     *  
  
     */   
   
    public Element() {   
   
        this.itemset=new ArrayList<Integer>();   
   
    }   
   
       
   
    /**  
  
     * ���������췽��  
  
     * ��ʼ����Ŀ�������������е���Ŀ����������  
  
     * @param items         ��Ŀ��  
  
     */   
   
    public Element(int [] items){   
   
        this.itemset=new ArrayList<Integer>();   
   
        for(int i=0;i<items.length;i++){   
   
            this.addItem(items[i]);   
   
        }   
   
    }   
   
       
   
    /**  
  
     * �����Ŀ  
  
     * �����Ŀ����Ŀ����  
  
     * @param item         ����ӵ���Ŀ  
  
     */   
   
    public void addItem(int item){   
   
        int i;   
   
        for(i=0;i<itemset.size();i++){   
   
            if(item<itemset.get(i)){   
   
                break;   
   
            }   
   
        }   
   
        itemset.add(i,item);   
   
    }   
   
       
   
    /**  
  
     * �����Ŀ��  
  
     * @return     ��Ŀ��  
  
     */   
   
    public ArrayList<Integer> getItems(){   
   
        return this.itemset;   
   
    }   
   
       
   
    /**  
  
     * ��ȡ���һ��λ�õ���Ŀ  
  
     * @return         ��Ŀ  
  
     */   
   
    public int getLastItem(){   
   
        if(this.itemset.size()>0){   
   
            return itemset.get(itemset.size() - 1);   
   
        }   
   
        else{   
   
            System.err.println("��Ԫ�ش���Element.getLastItem()");   
   
            return 0;   
   
        }   
   
    }   
   
   
    
   
   
    /**  
  
     * �������жϱ�Ԫ���ǲ��ǰ�����Ԫ��e��  
  
     * @param e           Ԫ��  
  
     * @return            true--�� false--��  
  
     */   
   
    public boolean isContainIn(Element e){   
   
   
    
   
   
        if(this.itemset.size()>e.itemset.size()){//�������Ԫ�ش�С��ͬ����Ϊ�����    
   
            return false;   
   
        }   
   
        int i=0,j=0;   
   
        while(j<e.size() && i<this.itemset.size() ){   
   
            if(this.itemset.get(i).intValue() == e.itemset.get(j).intValue()){   
   
                i++;j++;   
   
            }else{   
   
                j++;   
   
            }   
   
        }   
   
        if(i==this.itemset.size()){   
   
            return true;   
   
        }else{   
   
            return false;   
   
        }   
   
    }   
   
       
   
    /**  
  
     * ��ȡȥ����һ����Ŀ���Ԫ��  
  
     * @return          Ԫ��  
  
     */   
   
    public Element getWithoutFistItem(){   
   
        Element e=new Element();   
   
        for(int i=1 ;i<this.itemset.size();i++){   
   
            e.addItem(this.itemset.get(i).intValue());   
   
        }   
   
        return e;   
   
    }   
   
       
   
    /**  
  
     * ��ȡȥ�����һ����Ŀ���Ԫ��  
  
     * @return            Ԫ��  
  
     */   
   
    public Element getWithoutLastItem(){   
   
        Element e=new Element();   
   
        for(int i=0 ;i<this.itemset.size()-1;i++){   
   
            e.addItem(this.itemset.get(i).intValue());   
   
        }   
   
        return e;   
   
    }   
   
       
   
    /**  
  
     * ɾ����Ŀ  
  
     * ��Ŀλ��i�ϵ���Ŀ  
  
     * @param i        λ�����  
  
     * @return           
  
     */   
   
    public int removeItem(int i){   
   
        if(i<this.itemset.size()){   
   
           return this.itemset.remove(i).intValue();   
   
        }   
   
        System.err.println("��Ч��������");   
   
        return -1;   
   
    }   
   
       
   
    /**  
  
     * �Ƚ�����Ԫ�صĴ�С  
  
     * �����ݹ����Ĳ���o�뱾����Ƚ�  
  
     * @param o           ���Ƚϵ�Ԫ��  
  
     * @return            int -- -1 ��Ԫ��С�ڲ���  1 ��Ԫ�ش��ڲ���  
  
     */   
   
     public int compareTo(Object o){   
   
         Element e=(Element)o;   
   
         int r=0;   
   
         int i=0,j=0;   
   
         while(i<this.itemset.size() && j<e.itemset.size()){   
   
            if(this.itemset.get(i).intValue() < e.itemset.get(j).intValue()){   
   
                r=-1;//��elementС��e    
   
                break;   
   
            }else{   
   
                if(this.itemset.get(i).intValue() > e.itemset.get(j).intValue()){   
   
                    r=1;//��element����e    
   
                    break;   
   
                }   
   
            }   
   
            i++;j++;//��Ŀ��ͬ����ָ����һ����Ŀ    
   
         }   
   
         if(r==0){//���Ŀǰ��û�бȽϳ�˭��˭С�Ļ�    
   
             if(this.itemset.size()>e.itemset.size()){   
   
                 r=1;   
   
             }   
   
             if(this.itemset.size()<e.itemset.size()){   
   
                 r=-1;   
   
             }   
   
         }   
   
         return r;   
   
    }   
   
        
   
    /**  
  
     * ��ȡ��Ŀ���Ĵ�С  
  
     * @return      int--��С  
  
     */   
   
    public int size(){   
   
        return this.itemset.size();   
   
    }   
   
       
   
    /**  
  
     * Ԫ�ؿ�������  
  
     * ������Ŀ��  
  
     */   
   
    public Element clone(){   
   
        Element clone=new Element();   
   
        for(int i:this.itemset){   
   
            clone.addItem(i);   
   
        }   
   
        return clone;   
   
    }   
   
   
    
   
   
    /**  
  
     * ���ж�����Ԫ���Ƿ���ͬ  
  
     * @param o             
  
     * @return  true--��ͬ false--��ͬ  
  
     */   
   
    public boolean equalsTo(Object o){   
   
       boolean equal=true;   
   
       Element e=(Element)o;   
   
       if(this.itemset.size()!=e.itemset.size()){//�������Ԫ�ش�С��ͬ����Ϊ�����    
   
           equal=false;   
   
       }   
   
       for(int i=0;equal && i<this.itemset.size();i++){   
   
           if(this.itemset.get(i).intValue()!=e.itemset.get(i).intValue()){   
   
               equal=false;   
   
           }   
   
       }   
   
       return equal;   
   
   }   
   
   
    
   
   
    /**  
  
     * ��дtoString()  
  
     * �������ʱ���ַ�����  
  
     */   
   
    public String toString(){   
   
        StringBuffer s=new StringBuffer();   
   
        if(this.itemset.size()>1){   
   
            s.append("(");   
   
        }   
   
        for(int i=0;i<this.itemset.size();i++){   
   
            s.append(this.itemset.get(i).intValue());   
   
            if(i<this.itemset.size()-1){   
   
                s.append(",");   
   
            }   
   
        }   
   
        if(this.itemset.size()>1){   
   
            s.append(")");   
   
        }   
   
        return s.toString();   
   
    }   
   
   
    
   
   
       
   
}   
