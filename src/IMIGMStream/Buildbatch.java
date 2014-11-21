package IMIGMStream;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class Buildbatch {

    public static List<TreeSet<String>> LP1 = new ArrayList<TreeSet<String>>();
    public static List<TreeSet<String>> LP2 = new ArrayList<TreeSet<String>>();
    public static List<TreeSet<String>> LP3 = new ArrayList<TreeSet<String>>();

    public static void buildbatch(int timepoint, int L, int window, int min_sup, int topk, int datalng)//片段号，片段长度，窗口大小
    {
        timepoint = 1;
        int i = 1;
        int j = 1;
        int windowid = 1;//记录窗口号
          String filename1 = "1.data";
        String filename2 = "2.data";
        String filename3 = "3.data";
        String filename4 = "4.data";
        List<List<String>> s = new ArrayList<List<String>>();
        Build_IMSPMIS testTree = new Build_IMSPMIS(min_sup, topk);
        while (i <= datalng) {

            s = FileReadByLine.readFile(filename1, filename2, filename3, filename4, windowid, timepoint);
            for (List<String> ls : s) {
                if (ls.size() == 0) {
                    System.out.println("数据集处理完毕！");
                    Begin.sb.append("数据集处理完毕！\r\n");
                    break;
                }
            }

            testTree.timepoint = timepoint;
            System.out.println("时间点" + timepoint + "的项集流：" + s);
            Begin.sb.append("时间点" + timepoint + "的项集流：" + s+"\r\n");
            testTree.calculate(s);
            testTree.PrintFrequent(testTree.PatternMap);
            for (Iterator<Map.Entry<TreeSet<String>, Integer>> iter = testTree.PatternMap.entrySet().iterator(); iter.hasNext();) {
                Map.Entry<TreeSet<String>, Integer> supEntry = (Map.Entry<TreeSet<String>, Integer>) iter.next();
                TreeSet TS = supEntry.getKey();
                if (j == 1) {
                    LP1.add(TS);
                }
                if (j == 2) {
                    LP2.add(TS);
                }
                if (j == 3) {
                    LP3.add(TS);
                }
                if (j == 4) {
                    LP3.add(TS);
                    j = 3;
                }
            }
            j++;
            testTree.PatternMap.clear();

            // testTree.storeinPFPS_Tree(s, testTree, tree,timepoint);


            if (i >= window) {//设置片段    
                if (i > 1 && i %3 == 0) {//当片段数里达到一个窗口大小时，删除旧片段中对应的模式，删、删除潜在频繁模式树中的旧模式

                    System.out.println("***************打印窗口"+windowid+"的short-" + topk + "项集序列模式*************************");
                    Begin.sb.append("***************打印窗口"+windowid+"的short-" + topk + "项集序列模式*************************\r\n");
                    Begin.sb.append("LP1:"+LP1+"\r\n");
                    Begin.sb.append("LP2:"+LP2+"\r\n");
                    Begin.sb.append("LP3:"+LP3+"\r\n");
                    //System.out.println(LP1);
                   // System.out.println(LP2);
                   // System.out.println(LP3);
                    int m=1;
                    for (TreeSet l1 : LP1) {
                        for (TreeSet l2 : LP2) {
                            for (TreeSet l3 : LP3) {
                               // System.out.println("第"+m+"个："+l1.toString() + l2.toString() + l3.toString());
                                m++;
                            }
                        }

                    }
                    LP1.clear();
                    for (TreeSet ts1 : LP2) {
                        LP1.add(ts1);
                    }
                    LP2.clear();
                    for (TreeSet ts2 : LP3) {
                        LP2.add(ts2);
                    }
                    LP3.clear();

                    windowid++;//记录窗口号
                }
            }
            i = i + L;//读下一片段数据,L是片段长度
            s.clear();
            timepoint++;
            //if(timepoint>10){break;}
        }
    }
}
