//package kmeans;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.Random;
// 
//public class KMeans {
// 
//    int k; // ָ�����ֵĴ���
//    double mu; // ������ֹ�����������������������������ƫ����С��muʱ��ֹ����
//    double[][] center; // ��һ�θ������ĵ�λ��
//    int repeat; // �ظ����д���
//    double[] crita; // ���ÿ�����е������
// 
//    public KMeans(int k, double mu, int repeat, int len) {
//        this.k = k;
//        this.mu = mu;
//        this.repeat = repeat;
//        center = new double[k][];
//        for (int i = 0; i < k; i++)
//            center[i] = new double[len];
//        crita = new double[repeat];
//    }
// 
//    // ��ʼ��k�����ģ�ÿ��������lenά��������ÿά����left--right֮��
//    public void initCenter(int len, ArrayList<DataObject> objects) {
//        Random random = new Random(System.currentTimeMillis());
//        int[] count = new int[k]; // ��¼ÿ�����ж��ٸ�Ԫ��
//        Iterator<DataObject> iter = objects.iterator();
//        while (iter.hasNext()) {
//            DataObject object = iter.next();
//            int id = random.nextInt(10000)%k;
//            count[id]++;
//            for (int i = 0; i < len; i++)
//                center[id][i] += object.getVector()[i];
//        }
//        for (int i = 0; i < k; i++) {
//            for (int j = 0; j < len; j++) {
//                center[i][j] /= count[i];
//            }
//        }
//    }
// 
//    // �����ݼ��е�ÿ����鵽����������Ǹ�����
//    public void classify(ArrayList<DataObject> objects) {
//        Iterator<DataObject> iter = objects.iterator();
//        while (iter.hasNext()) {
//            DataObject object = iter.next();
//            double[] vector = object.getVector();
//            int len = vector.length;
//            int index = 0;
//            double neardist = Double.MAX_VALUE;
//            for (int i = 0; i < k; i++) {
//                // double dist = Global.calEuraDist(vector, center[i], len);
//                // //ʹ��ŷ�Ͼ���
//                double dist = Global.calEditDist(vector, center[i], len); // ʹ�ñ༭����
//                if (dist < neardist) {
//                    neardist = dist;
//                    index = i;
//                }
//            }
//            object.setCid(index);
//        }
//    }
// 
//    // ���¼���ÿ���ص����ģ����ж���ֹ�����Ƿ����㣬�����������¸��ص�����,�������ͷ���true.len�����ݵ�ά��
//    public boolean calNewCenter(ArrayList<DataObject> objects, int len) {
//        boolean end = true;
//        int[] count = new int[k]; // ��¼ÿ�����ж��ٸ�Ԫ��
//        double[][] sum = new double[k][];
//        for (int i = 0; i < k; i++)
//            sum[i] = new double[len];
//        Iterator<DataObject> iter = objects.iterator();
//        while (iter.hasNext()) {
//            DataObject object = iter.next();
//            int id = object.getCid();
//            count[id]++;
//            for (int i = 0; i < len; i++)
//                sum[id][i] += object.getVector()[i];
//        }
//        for (int i = 0; i < k; i++) {
//            if (count[i] != 0) {
//                for (int j = 0; j < len; j++) {
//                    sum[i][j] /= count[i];
//                }
//            }
//            // ���в������κε�,��ʱ��������
//            else {
//                int a=(i+1)%k;
//                int b=(i+3)%k;
//                int c=(i+5)%k;
//                for (int j = 0; j < len; j++) {
//                    center[i][j] = (center[a][j]+center[b][j]+center[c][j])/3;
//                }
//            }
//        }
//        for (int i = 0; i < k; i++) {
//            // ֻҪ��һ��������Ҫ�ƶ��ľ��볬����mu���ͷ���false
//            // if (Global.calEuraDist(sum[i], center[i], len) >= mu) { //ʹ��ŷ�Ͼ���
//            if (Global.calEditDist(sum[i], center[i], len) >= mu) { // ʹ�ñ༭����
//                end = false;
//                break;
//            }
//        }
//        if (!end) {
//            for (int i = 0; i < k; i++) {
//                for (int j = 0; j < len; j++)
//                    center[i][j] = sum[i][j];
//            }
//        }
//        return end;
//    }
// 
//    // ������������ݺͷ���ļ�Ȩƽ�����ó����ξ���������.len�����ݵ�ά��
//    public double getSati(ArrayList<DataObject> objects, int len) {
//        double satisfy = 0.0;
//        int[] count = new int[k];
//        double[] ss = new double[k];
//        Iterator<DataObject> iter = objects.iterator();
//        while (iter.hasNext()) {
//            DataObject object = iter.next();
//            int id = object.getCid();
//            count[id]++;
//            for (int i = 0; i < len; i++)
//                ss[id] += Math.pow(object.getVector()[i] - center[id][i], 2.0);
//        }
//        for (int i = 0; i < k; i++) {
//            satisfy += count[i] * ss[i];
//        }
//        return satisfy;
//    }
// 
//    public double run(int round, DataSource datasource, int len) {
//        System.out.println("��" + round + "������");
//        initCenter(len,datasource.objects);
//        classify(datasource.objects);
//        while (!calNewCenter(datasource.objects, len)) {
//            classify(datasource.objects);
//        }
//        datasource.printResult(datasource.objects, k);
//        double ss = getSati(datasource.objects, len);
//        System.out.println("��Ȩ���" + ss);
//        return ss;
//    }
// 
//    public static void main(String[] args) {
//        DataSource datasource = new DataSource();
//        datasource.readMatrix(new File("/home/orisun/test/dot.mat"));
//        datasource.readRLabel(new File("/home/orisun/test/dot.rlabel"));
////      datasource.readMatrix(new File("/home/orisun/text.normalized.mat"));
////      datasource.readRLabel(new File("/home/orisun/text.rlabel"));
//        int len = datasource.col;
//        // ����Ϊ6���أ������ƶ�С��1E-8ʱ��ֹ�������ظ�����7��
//        KMeans km = new KMeans(4, 1E-10, 7, len);
//        int index = 0;
//        double minsa = Double.MAX_VALUE;
//        for (int i = 0; i < km.repeat; i++) {
//            double ss = km.run(i, datasource, len);
//            if (ss < minsa) {
//                minsa = ss;
//                index = i;
//            }
//        }
//        System.out.println("��õĽ���ǵ�" + index + "�Ρ�");
//    }
//}