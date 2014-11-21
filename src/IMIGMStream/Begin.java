package IMIGMStream;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.util.Date;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.SimpleDateFormat;

public class Begin {
	
	public static StringBuilder sb = new StringBuilder("");

    public static void main(String[] args) {
        //Build_MSPSP_graph testTree = new Build_MSPSP_graph();
        /*
         * int window =
         * Integer.parseInt(JOptionPane.showInputDialog("请输入设定的滑动窗口大小")); while
         * (window < 0) { JOptionPane.showMessageDialog(null, "请输入窗口的大小大于0");
         * window =
         * Integer.parseInt(JOptionPane.showInputDialog("请输入设定的滑动窗口大小")); } int
         * L =
         * Integer.parseInt(JOptionPane.showInputDialog("请输入设定的滑动窗口内片段的大小"));
         * while (L <= 0||(window%L)!=0) { JOptionPane.showMessageDialog(null,
         * "请输入的片段大小小于窗口的大小且大于0,且能整除窗口的大小"); L =
         * Integer.parseInt(JOptionPane.showInputDialog("请输入设定的滑动窗口内片段的大小")); }
         *
         * int min_sup =
         * Integer.parseInt(JOptionPane.showInputDialog("请输入设定的片段最小支持度计数"));
         * while (min_sup <= 0) { JOptionPane.showMessageDialog(null,
         * "请输入的片段最小支持度大于0"); min_sup =
         * Integer.parseInt(JOptionPane.showInputDialog("请输入设定的片段最小支持度计数")); }
         * int MIN_SUP =
         * Integer.parseInt(JOptionPane.showInputDialog("请输入设定的窗口最小支持度计数"));
         * while (MIN_SUP <= 0 ||MIN_SUP < min_sup) {
         * JOptionPane.showMessageDialog(null, "请输入的窗口最小支持度大于0，且大于片段最小支持度");
         * MIN_SUP =
         * Integer.parseInt(JOptionPane.showInputDialog("请输入设定的窗口最小支持度计数")); }
         */
        //int L =20, window = 40, datalng = 80;
        int L = 1, window = 3, datalng = 20;
        double minsup = 0.5;
        int topk = 3;
        int min_sup = (int) (4 * minsup);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        System.out.println("Begin-window                欢迎使用本多数据流序列模式算法IMIGM_Stream,使用时间为：" + df.format(new Date()) + "                   Begin-window");
        System.out.println("********" + "本程序设定滑动窗口的大小为：" + window + "个时间点;" + "滑动窗口内片段的长度为:" + L + "个序列;一个窗口含有" + window / L + "时间点****************************************************************");
        System.out.println("********" + "本程序设定时间点最小支持度阈值为：" + minsup + "，时间点最小支持度计数阈值：" + min_sup * 1.0 / L * 1.0 + "********");
        sb.append("Begin-window                欢迎使用本多数据流序列模式算法IMIGM_Stream,使用时间为：" + df.format(new Date()) + "                   Begin-window"+"\r\n");
        sb.append("********" + "本程序设定滑动窗口的大小为：" + window + "个时间点;" + "滑动窗口内片段的长度为:" + L + "个序列;一个窗口含有" + window / L + "时间点****************************************************************"+"\r\n");
        sb.append("********" + "本程序设定时间点最小支持度阈值为：" + minsup + "，时间点最小支持度计数阈值：" + min_sup * 1.0 / L * 1.0 + "********"+"\r\n");
        long fromtime = System.currentTimeMillis();
        Buildbatch.buildbatch(0, L, window, min_sup, topk, datalng);
        long endtime = System.currentTimeMillis();
        long time = endtime - fromtime;
        System.out.println("算法执行时间：" + time + "毫秒");
        sb.append("算法执行时间：" + time + "毫秒"+"\r\n");
        
        try{
            
        	String content = sb.toString();
        	BufferedWriter bw = new BufferedWriter(new FileWriter("result1.txt"));
        	bw.write(content);
        	bw.flush();
        	bw.close();
        	
        	
        }catch(Exception ex){
        	ex.printStackTrace();
        }
        
        
    }
}
