package Tests;

import org.junit.Test;

import gui.ButtonHandler;
import gui.SubmitButtonHandler;

import static org.junit.Assert.assertTrue;

public class ButtonHandlerTests {
	//hgvjh
	
	String input00 = "1 2; 3 4;";
	int[][] expected00={{1,2},
				   		{3,4}};
	String input01 = "1 2; 3 4; 5 6;";
	int[][] expected01={{1,2},
				   		{3,4},
				   		{5,6}};
	String input02 = "1 2 3; 4 5 6;";
	int[][] expected02={{1,2,3},
				   		{4,5,6}};
	String input03 = "1 2 3 4; 5 6 7 8;";
	int[][] expected03={{1,2,3,4},
				   		{5,6,7,8}};
	
	String toEncrypt05 = "WOTT is OPSD"; 		 
	int[][] expected05 ={{1204,1691,1805,1104}, 	//{87,79,84,84,
						 {697,978,1043,636}, 		// 32,105,115,32,
						 {1291,1770,1889,1188}};	// 79,80,83,68}
	int[][] key05 = 	{{4,7,8},
						 {2,4,5},
						 {5,7,8}};
	
	
	String toEncrypt06 = "Fuck Writing Tests"; 
	int[][] expected06 ={{3756,4994,3865,4273,2613}, 	//{70,117,99,107,32,
						 {7439,11514,9657,9994,3590},	// 87,114,105,116,105
						 {157,231,204,223,137}, 		// 110,103,32,84,101,
						 {70,117,99,107,32}};			// 115,116,115,32,32}
	int[][] key06 = 	{{23,8,9,4},						
						 {86,2,4,7}};
	
	String toEncrypt07 = "Testing others )&$^";	//{84,101,115,116,
	int[][] expected07 ={{1273,1309,1231,807}, // 105,110,103,32,
						 {3533,3699,3471,2417},// 111,116,104,101,
						 {300,327,322,249},	// 114,115,32,41, 
						 {189,211,218,148},	// 38,36,94,32}
						 {84,101,115,116}};
	int[][] key07 = 	{{1,2,3,4,5},						
						 {6,7,8,9,10}};
	
	String toEncrypt08 = "Hello."; 	
	int[][] expected08 ={{399,363}, 	//{72,101,
						 {648,735}, 	// 108,108,
						 {1008,1153}}; // 111,46}
	int[][] key08 = 	{{1,2},
						 {3,4},
						 {5,6}};
	
	String toEncrypt09 = "Hello."; 	
	int[][] expected09 ={{431,395},	//{72,101,
						 {759,781}, 	// 108,108,
						 {1008,1153},	// 111,46,
						 {1368,1571}}; // 32,32}	
	int[][] key09 = 	{{1,2},
						 {3,4},
						 {5,6},
						 {7,8}};


	private int[][] convertToMatrix(String toInterpret, int[][] expected){
		int[][] actual = ButtonHandler.convertToMatrix(toInterpret);
		//assertTrue("The expected response was "+MatriceEncryption.showArrays(null, actual,null, null, null, null)+" but "+actual+" was returned", Arrays.deepEquals(expected, actual));
		return actual;
	}
	
	@Test public void convertTo00(){ convertToMatrix(input00, expected00);	}
	@Test public void convertTo01(){ convertToMatrix(input01, expected01);	}
	@Test public void convertTo02(){ convertToMatrix(input02, expected02);	}
	@Test public void convertTo03(){ convertToMatrix(input03, expected03);	}
	
	
	private String convertFromMatrix(int[][] input, String expected){
		ButtonHandler bh = new ButtonHandler(null, null);
		String actual = bh.convertFromMatrix(input);
		//assertTrue("The expected answer was: "+expected+" but what was returned was: "+actual, expected.equals(actual));
		return actual;
	}
	
	@Test public void convertFrom00(){ convertFromMatrix(expected00, input00);}
	@Test public void convertFrom01(){ convertFromMatrix(expected01, input01);}
	@Test public void convertFrom02(){ convertFromMatrix(expected02, input02);}
	@Test public void convertFrom03(){ convertFromMatrix(expected03, input03);}
	
	private void encrypt(int[][] key, String toEncrypt, int[][] shouldBe){
		ButtonHandler bh = new ButtonHandler(null, null);
		String expected = ButtonHandler.convertFromMatrix(shouldBe);
		String actual = bh.encrypt(key, toEncrypt);
		assertTrue(expected+" was supposed to be returned but "+actual+" was returned", expected.equals(actual));
	}
	@Test public void encryptTest00(){ encrypt(key05,toEncrypt05,expected05); }
	@Test public void encryptTest01(){ encrypt(key06,toEncrypt06,expected06); }
	@Test public void encryptTest02(){ encrypt(key07,toEncrypt07,expected07); }
	@Test public void encryptTest03(){ encrypt(key08,toEncrypt08,expected08); }
	@Test public void encryptTest04(){ encrypt(key09,toEncrypt09,expected09); }
	
	private void decrypt(int[][] key, int[][] toConvert, String expected){
		ButtonHandler bh = new ButtonHandler(null, null);
		String toDecrypt = ButtonHandler.convertFromMatrix(toConvert);
		String actual = bh.decrypt(key, toDecrypt);
		assertTrue(expected+" was supposed to be returned but "+actual+" was returned", expected.equals(actual));
	}
	@Test public void decryptTest00(){ decrypt(key05,expected05,toEncrypt05); }
	@Test public void decryptTest01(){ decrypt(key06,expected06,toEncrypt06); }
	@Test public void decryptTest02(){ decrypt(key07,expected07,toEncrypt07); }
	@Test public void decryptTest03(){ decrypt(key08,expected08,toEncrypt08); }
	@Test public void decryptTest04(){ decrypt(key09,expected09,toEncrypt09); }
	
	private void outputFile(int typeCode, char kt, int[][] key, int[][] encrypted, String aName){
		String aKey = "";
		String someText = "";
		if(key != null){ aKey = ButtonHandler.convertFromMatrix(key); }
		if(encrypted != null){ someText = ButtonHandler.convertFromMatrix(encrypted); }
		String actual = SubmitButtonHandler.inputOutput(typeCode, kt, aKey, someText, aName);
		String expected = "true";
		assertTrue(expected+" was supposed to be returned but "+actual+" was returned", expected.equals(actual));
	}
	
	private void inputFile(int typeCode, char kt, int[][] key, int[][] encrypted, String aName){
		String expected = "";
		if(key != null){ expected = ButtonHandler.convertFromMatrix(key); }
		if(encrypted != null){ expected = ButtonHandler.convertFromMatrix(encrypted); }
		String actual = SubmitButtonHandler.inputOutput(typeCode, kt, "", "", aName);
		assertTrue(expected+" was supposed to be returned but "+actual+" was returnedS", expected.equals(actual));
	}
	
	@Test public void _1outputKeyTest00(){ outputFile(0,'k',key05,null,"KeyExportTest00"); }
	@Test public void _1outputTextTest00(){outputFile(0,'t',null,expected05,"TextExportTest00"); }
	@Test public void _1outputKeyTest01(){ outputFile(0,'k',key06,null,"KeyExportTest01"); }
	@Test public void _1outputTextTest01(){outputFile(0,'t',null,expected06,"TextExportTest01"); }
	@Test public void _1outputKeyTest02(){ outputFile(0,'k',key07,null,"KeyExportTest02"); }
	@Test public void _1outputTextTest02(){outputFile(0,'t',null,expected07,"TextExportTest02"); }
	@Test public void _1outputKeyTest03(){ outputFile(0,'k',key08,null,"KeyExportTest03"); }
	@Test public void _1outputTextTest03(){outputFile(0,'t',null,expected08,"TextExportTest03"); }
	@Test public void _1outputKeyTest04(){ outputFile(0,'k',key09,null,"KeyExportTest04"); }
	@Test public void _1outputTextTest04(){outputFile(0,'t',null,expected09,"TextExportTest04"); }
	
	@Test public void _2inputKeyTest00(){ inputFile(1,'k',key05,null,"KeyExportTest00.jjr"); }
	@Test public void _2inputTextTest00(){inputFile(1,'t',null,expected05,"TextExportTest00.jjr"); }
	@Test public void _2inputKeyTest01(){ inputFile(1,'k',key06,null,"KeyExportTest01.jjr"); }
	@Test public void _2inputTextTest01(){inputFile(1,'t',null,expected06,"TextExportTest01.jjr"); }
	@Test public void _2inputKeyTest02(){ inputFile(1,'k',key07,null,"KeyExportTest02.jjr"); }
	@Test public void _2inputTextTest02(){inputFile(1,'t',null,expected07,"TextExportTest02.jjr"); }
	@Test public void _2inputKeyTest03(){ inputFile(1,'k',key08,null,"KeyExportTest03.jjr"); }
	@Test public void _2inputTextTest03(){inputFile(1,'t',null,expected08,"TextExportTest03.jjr"); }
	@Test public void _2inputKeyTest04(){ inputFile(1,'k',key09,null,"KeyExportTest04.jjr"); }
	@Test public void _2inputTextTest04(){inputFile(1,'t',null,expected09,"TextExportTest04.jjr"); }

}
