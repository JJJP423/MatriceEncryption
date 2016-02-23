package Tests;

import java.util.Arrays;

import org.junit.Test;

import code.MatriceEncryption;

import static org.junit.Assert.assertTrue;

public class MatriceEncryptionTests {
	
	MatriceEncryption me = new MatriceEncryption();
	String toEncrypt00 = "Hello"; 		int key00 = 4;	int[] expected00 = {288,404,432,432,444};
	String toEncrypt01 = "WOTT";  		int key01 = 9;	int[] expected01 = {783,711,756,756};
	String toEncrypt02 = "Fuck Tests"; 	int key02 = 8; 	int[] expected02 = {560,936,792,856,256,672,808,920,928,920};
	String toEncrypt03 = "?,<+#"; 		int key03 = 5;	int[] expected03 = {315,220,300,215,175};
	
	private void _1byXMatriceEncryption(String input, int[] expected, int key){
	int[] actual = me._1byXencrypt(input,key);
	assertTrue("The expected return value was "+expected+" but "+actual+" was returned", Arrays.equals(expected, actual));
	}
	private void width1MatriceDecryption(String expected, int key, int[] input){
		String actual = me._1byXdecrypt(input,key);
		assertTrue("The expected return value was "+expected+" but "+actual+" was returned", expected.equals(actual));
	}
	
	@Test public void test00(){ _1byXMatriceEncryption(toEncrypt00, expected00, key00); }
	@Test public void test01(){ _1byXMatriceEncryption(toEncrypt01, expected01, key01); }
	@Test public void test02(){ _1byXMatriceEncryption(toEncrypt02, expected02, key02); }
	@Test public void test03(){ _1byXMatriceEncryption(toEncrypt03, expected03, key03); }
	
	@Test public void test04(){ width1MatriceDecryption(toEncrypt00,key00,expected00); }
	@Test public void test05(){ width1MatriceDecryption(toEncrypt01,key01,expected01); }
	@Test public void test06(){ width1MatriceDecryption(toEncrypt02,key02,expected02); }
	@Test public void test07(){ width1MatriceDecryption(toEncrypt03,key03,expected03); }
	
	String toEncrypt04 = "Hello There"; 	
	int[][] expected04 = {{564,722,721,786,727,224}, //{72,101,108,108,111,32,
						  {684,918,951,990,969,228}};// 84,104,101,114,101,32}
	int[][] key04 = {{2,5},
					 {6,3}};
	
	String toEncrypt05 = "WOTT is OPSD"; 		 
	int[][] expected05 = {{1204,1691,1805,1104}, 	//{87,79,84,84,
						  {697,978,1043,636}, 		// 32,105,115,32,
						  {1291,1770,1889,1188}};	// 79,80,83,68}
	int[][] key05 = {{4,7,8},
					 {2,4,5},
					 {5,7,8}};
	
	String toEncrypt06 = "Fuck Writing Tests"; 
	int[][] expected06 = {{3756,4994,3865,4273,2613}, 	//{70,117,99,107,32,
						  {7439,11514,9657,9994,3590},	// 87,114,105,116,105
						  {157,231,204,223,137}, 		// 110,103,32,84,101,
						  {70,117,99,107,32}};			// 115,116,115,32,32}
	int[][] key06 = {{23,8,9,4},						
			 		 {86,2,4,7}};					    								
	
	String toEncrypt07 = "Testing others )&$^";	//{84,101,115,116,
	int[][] expected07 = {{1273,1309,1231,807}, // 105,110,103,32,
						  {3533,3699,3471,2417},// 111,116,104,101,
						  {300,327,322,249},	// 114,115,32,41, 
						  {189,211,218,148},	// 38,36,94,32}
						  {84,101,115,116}};
	int[][] key07 = {{1,2,3,4,5},						
			 		 {6,7,8,9,10}};					   	
	
	String toEncrypt08 = "Hello."; 	
	int[][] expected08 = {{399,363}, 	//{72,101,
						  {648,735}, 	// 108,108,
						  {1008,1153}}; // 111,46}
	int[][] key08 = {{1,2},
					 {3,4},
					 {5,6}};
	
	String toEncrypt09 = "Hello."; 	
	int[][] expected09 = {{431,395},	//{72,101,
						  {759,781}, 	// 108,108,
						  {1008,1153},	// 111,46,
						  {1368,1571}}; // 32,32}	
	int[][] key09 = {{1,2},
					 {3,4},
					 {5,6},
					 {7,8}};
	
	private void XbyXencrypt(String input, int[][] expected, int[][] key){
		int[][] actual = me.XbyXencrypt(input,key);
		assertTrue("The expected return value was "+expected+" but "+actual+" was returned", Arrays.deepEquals(expected, actual));
	}
	
	@Test public void test08(){ 	XbyXencrypt(toEncrypt04,expected04,key04); };
	@Test public void test09(){ 	XbyXencrypt(toEncrypt05,expected05,key05); };
	@Test public void test10(){ 	XbyXencrypt(toEncrypt06,expected06,key06); };
	@Test public void test11(){		XbyXencrypt(toEncrypt07,expected07,key07); };
	@Test public void test12(){ 	XbyXencrypt(toEncrypt08,expected08,key08); };
	@Test public void test13(){ 	XbyXencrypt(toEncrypt09,expected09,key09); };

	@Test public void test14(){ 	XbyXdecrypt(expected04,key04,toEncrypt04); };
	@Test public void test15(){ 	XbyXdecrypt(expected05,key05,toEncrypt05); };
	@Test public void test16(){ 	XbyXdecrypt(expected06,key06,toEncrypt06); };
	@Test public void test17(){ 	XbyXdecrypt(expected07,key07,toEncrypt07); };
	@Test public void test18(){ 	XbyXdecrypt(expected08,key08,toEncrypt08); };
	@Test public void test19(){ 	XbyXdecrypt(expected09,key09,toEncrypt09); };
	
	private void XbyXdecrypt(int[][] input,int[][] key, String expected){
		String actual = me.XbyXdecrypt(input,key);
		assertTrue("The expected return value was "+expected+" but "+actual+" was returned", expected.equals(actual));
	}

	
}
