package IMIGMStream;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import javax.swing.JOptionPane;

public class Build_IMSPMIS {

    public int minsup;
    public int topk;
    public int timepoint;
    public Map<TreeSet<String>, Integer> PatternMap = new LinkedHashMap<TreeSet<String>, Integer>();
//    public List<NodeBean> ver = new ArrayList<NodeBean>();//顶点
    public List<String> edge = new ArrayList();

    //构造函数
    public Build_IMSPMIS(int minsup, int topk) {
        this.minsup = minsup;
        this.topk = topk;
    }
    public void calculate(List<List<String>> s) {
        for (List<String> itemset : s) {
            // ArrayList<TreeSet<String>> subset=combine(itemset);
            for (TreeSet<String> ts : combine(itemset)) {
                if (!ts.isEmpty()) {
                    if (PatternMap.containsKey(ts)) {
                        int count = PatternMap.get(ts);
                        PatternMap.put(ts, count + 1);
                    } else {
                        PatternMap.put(ts, 1);
                    }
                }
            }
        }
        System.out.println("+++++++++++++++++++时间点" + timepoint + "的子项集及计数如下+++++++++++++++++++");
        System.out.println(PatternMap);
        Begin.sb.append("+++++++++++++++++++时间点" + timepoint + "的子项集及计数如下+++++++++++++++++++\r\n");
        Begin.sb.append("PatternMap:"+PatternMap+"\r\n");
        
    }

    public Map<TreeSet<String>, Integer> PrintFrequent(Map<TreeSet<String>, Integer> PatternMap) {
        for (Iterator<Map.Entry<TreeSet<String>, Integer>> iter = PatternMap.entrySet().iterator(); iter.hasNext();) {
            Map.Entry<TreeSet<String>, Integer> supEntry = (Map.Entry<TreeSet<String>, Integer>) iter.next();
            int sup = supEntry.getValue();
            int top = supEntry.getKey().size();
            // System.out.println("长度"+top);
            if (sup < minsup || top > this.topk) {
                iter.remove();
                // itemList.remove(supEntry.getKey());
            }
        }
        System.out.println("+++++++++++++++++++时间点" + timepoint + "的top" + this.topk + "-short频繁项集如下" + "+++++++++++++++++++");
        System.out.println(PatternMap);
        Begin.sb.append("+++++++++++++++++++时间点" + timepoint + "的top" + this.topk + "-short频繁项集如下" + "+++++++++++++++++++\r\n");
        Begin.sb.append("PatternMap:"+PatternMap+"\r\n");
        
        return PatternMap;
        //PatternMap.clear();
    }

    public static ArrayList<TreeSet<String>> combine(List<String> s) {
        TreeSet<String> set = new TreeSet<String>();
        List cs = new ArrayList();
        for (int i = 0; i < s.size(); i++) {
            set.add(s.get(i));
        }
        ArrayList<TreeSet<String>> subset = getSubset(set);

        return subset;
    }

    public static String[] getBinaryValue(TreeSet<String> set) {
        int size = set.size();
        int m = (int) Math.pow(2, size) - 1;
        String[] result = new String[m + 1];
        for (int i = m; i > -1; i--) {
            StringBuffer sb = new StringBuffer(Integer.toBinaryString(i));
            int length = sb.length();
            if (length < size) {
                for (int j = 0; j < size - length; j++) {
                    sb.insert(0, "0");
                }
            }
            result[i] = sb.toString();
        }
        return result;
    }

    //根据二进制字符串生成子集
    public static ArrayList<TreeSet<String>> getSubset(TreeSet<String> set) {
        ArrayList<TreeSet<String>> result = new ArrayList<TreeSet<String>>();


        //把集合元素放入数组中，方便存取
        String[] items = new String[set.size()];
        int i = 0;
        for (String item : set) {
            items[i++] = item;
        }
        //调用二进制字符串生成函数
        String[] binaryValue = getBinaryValue(set);
        //根据二进制字符串取集合元素构成子集
        for (int j = 0; j < binaryValue.length; j++) {
            String value = binaryValue[j];
            TreeSet<String> subset = new TreeSet<String>();
            for (int k = 0; k < value.length(); k++) {
                if (value.charAt(k) == '1') {
                    subset.add(items[k]);
                }
            }
            result.add(subset);
        }
        return result;
    }
}
