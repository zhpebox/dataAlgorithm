package gsp;

import java.util.ArrayList;

/**
 * 
 * GSP的测试类
 * 
 * 使用JUnit测试
 * 
 * @author guangqingzhong
 * 
 * 
 */

public class GSPTest {

	public void testGSP() {

		GSP gsp = new GSP(2);

		int i = 1;

		gsp.outputInput();

		ArrayList<Sequence> result = gsp.getSequences();

	}
	
	public static void main(String []args){
		
		GSP gsp = new GSP(2);

		int i = 1;

		gsp.outputInput();

		ArrayList<Sequence> result = gsp.getSequences();
		
		
	}
	

}




