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
         * Integer.parseInt(JOptionPane.showInputDialog("�������趨�Ļ������ڴ�С")); while
         * (window < 0) { JOptionPane.showMessageDialog(null, "�����봰�ڵĴ�С����0");
         * window =
         * Integer.parseInt(JOptionPane.showInputDialog("�������趨�Ļ������ڴ�С")); } int
         * L =
         * Integer.parseInt(JOptionPane.showInputDialog("�������趨�Ļ���������Ƭ�εĴ�С"));
         * while (L <= 0||(window%L)!=0) { JOptionPane.showMessageDialog(null,
         * "�������Ƭ�δ�СС�ڴ��ڵĴ�С�Ҵ���0,�����������ڵĴ�С"); L =
         * Integer.parseInt(JOptionPane.showInputDialog("�������趨�Ļ���������Ƭ�εĴ�С")); }
         *
         * int min_sup =
         * Integer.parseInt(JOptionPane.showInputDialog("�������趨��Ƭ����С֧�ֶȼ���"));
         * while (min_sup <= 0) { JOptionPane.showMessageDialog(null,
         * "�������Ƭ����С֧�ֶȴ���0"); min_sup =
         * Integer.parseInt(JOptionPane.showInputDialog("�������趨��Ƭ����С֧�ֶȼ���")); }
         * int MIN_SUP =
         * Integer.parseInt(JOptionPane.showInputDialog("�������趨�Ĵ�����С֧�ֶȼ���"));
         * while (MIN_SUP <= 0 ||MIN_SUP < min_sup) {
         * JOptionPane.showMessageDialog(null, "������Ĵ�����С֧�ֶȴ���0���Ҵ���Ƭ����С֧�ֶ�");
         * MIN_SUP =
         * Integer.parseInt(JOptionPane.showInputDialog("�������趨�Ĵ�����С֧�ֶȼ���")); }
         */
        //int L =20, window = 40, datalng = 80;
        int L = 1, window = 3, datalng = 20;
        double minsup = 0.5;
        int topk = 3;
        int min_sup = (int) (4 * minsup);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
        System.out.println("Begin-window                ��ӭʹ�ñ�������������ģʽ�㷨IMIGM_Stream,ʹ��ʱ��Ϊ��" + df.format(new Date()) + "                   Begin-window");
        System.out.println("********" + "�������趨�������ڵĴ�СΪ��" + window + "��ʱ���;" + "����������Ƭ�εĳ���Ϊ:" + L + "������;һ�����ں���" + window / L + "ʱ���****************************************************************");
        System.out.println("********" + "�������趨ʱ�����С֧�ֶ���ֵΪ��" + minsup + "��ʱ�����С֧�ֶȼ�����ֵ��" + min_sup * 1.0 / L * 1.0 + "********");
        sb.append("Begin-window                ��ӭʹ�ñ�������������ģʽ�㷨IMIGM_Stream,ʹ��ʱ��Ϊ��" + df.format(new Date()) + "                   Begin-window"+"\r\n");
        sb.append("********" + "�������趨�������ڵĴ�СΪ��" + window + "��ʱ���;" + "����������Ƭ�εĳ���Ϊ:" + L + "������;һ�����ں���" + window / L + "ʱ���****************************************************************"+"\r\n");
        sb.append("********" + "�������趨ʱ�����С֧�ֶ���ֵΪ��" + minsup + "��ʱ�����С֧�ֶȼ�����ֵ��" + min_sup * 1.0 / L * 1.0 + "********"+"\r\n");
        long fromtime = System.currentTimeMillis();
        Buildbatch.buildbatch(0, L, window, min_sup, topk, datalng);
        long endtime = System.currentTimeMillis();
        long time = endtime - fromtime;
        System.out.println("�㷨ִ��ʱ�䣺" + time + "����");
        sb.append("�㷨ִ��ʱ�䣺" + time + "����"+"\r\n");
        
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
