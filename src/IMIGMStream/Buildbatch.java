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

    public static void buildbatch(int timepoint, int L, int window, int min_sup, int topk, int datalng)//Ƭ�κţ�Ƭ�γ��ȣ����ڴ�С
    {
        timepoint = 1;
        int i = 1;
        int j = 1;
        int windowid = 1;//��¼���ں�
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
                    System.out.println("���ݼ�������ϣ�");
                    Begin.sb.append("���ݼ�������ϣ�\r\n");
                    break;
                }
            }

            testTree.timepoint = timepoint;
            System.out.println("ʱ���" + timepoint + "�������" + s);
            Begin.sb.append("ʱ���" + timepoint + "�������" + s+"\r\n");
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


            if (i >= window) {//����Ƭ��    
                if (i > 1 && i %3 == 0) {//��Ƭ������ﵽһ�����ڴ�Сʱ��ɾ����Ƭ���ж�Ӧ��ģʽ��ɾ��ɾ��Ǳ��Ƶ��ģʽ���еľ�ģʽ

                    System.out.println("***************��ӡ����"+windowid+"��short-" + topk + "�����ģʽ*************************");
                    Begin.sb.append("***************��ӡ����"+windowid+"��short-" + topk + "�����ģʽ*************************\r\n");
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
                               // System.out.println("��"+m+"����"+l1.toString() + l2.toString() + l3.toString());
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

                    windowid++;//��¼���ں�
                }
            }
            i = i + L;//����һƬ������,L��Ƭ�γ���
            s.clear();
            timepoint++;
            //if(timepoint>10){break;}
        }
    }
}
