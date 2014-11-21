package kmeans;

import java.util.Random;

public class test {
	public static void main(String[] args) {
		Random random = new Random(System.currentTimeMillis());
		int num  = random.nextInt(100);
		int id = num%10;
		System.out.println(System.currentTimeMillis());
		System.out.println("num is "+num);
		System.out.println("random  = "+random);
		System.out.println("thie = "+id);
	}
}
