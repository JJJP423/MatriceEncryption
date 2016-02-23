package Tests;

import org.junit.Test;

import commandLine.CMDFunctionality;

import static org.junit.Assert.assertTrue;

public class CMDTests {
	
	String toEncrypt;
	String encryptWith;
	String toDecrypt;
	String decryptWith;
	String keyCreated = "false";
	String textCreated = "false";
	boolean didCreateKey = false;
	boolean didCreateText = false;
	String filename = "Test";
	String help = "-h";
	String cmd = "-c";
	String gui = "-g";
	String encrypt = "-e";
	String decrypt = "-d";
	String importK = "-K";
	String importT = "-T";
	String exportK = "-oK";
	String exportT = "-oT";
	int pos = 0;
	
	/*
	private void evaluateArguments(String toSet1, String toSet2, String cantBe1, String cantBe2, // Testing whether evaluate input can find the expected parameter in the array
									int errorCode1, int errorCode2, int errorCode3,
									String from, String checkFor1, String checkFor2,
									int pos, String[] args, String expected){
			CMDFunctionality cmd = new CMDFunctionality(args);
			String actual = cmd.evaluateInput(toSet1, toSet2, cantBe1, cantBe2, errorCode1, errorCode2, errorCode3, from,
												checkFor1, checkFor2, pos, args);
			assertTrue("The expected value was "+expected+" but what was returned was "+actual, expected.equals(actual));
	}
	@Test public void evaluateArgumentsTestEncrypt00(){
		String[] args = {"-c","-e",key05S,toEncrypt05}; // Find Key
		toEncrypt = "";
		encryptWith = "";
		evaluateArguments(toEncrypt,encryptWith,decrypt,help,
			CMDFunctionality.FOLLOWING_E,CMDFunctionality.FOLLOWING_K,CMDFunctionality.FOLLOWING_T,
			encrypt,importK,importT,2,args,args[2]);
		}
	@Test public void evaluateArgumentsTestDecrypt00(){
		String[] args = {"-c","-e",key05S,expected05S}; // FInd Text
		toDecrypt = "";
		decryptWith = "";
		evaluateArguments(toDecrypt,decryptWith,encrypt,help,
			CMDFunctionality.FOLLOWING_D,CMDFunctionality.FOLLOWING_K,CMDFunctionality.FOLLOWING_T,
			decrypt,importK,importT,3,args,args[3]);
		}
	@Test public void evaluateArgumentsTestEncrypt01(){
		String[] args = {"-c","-e",key06S,toEncrypt06}; // Find Text
		toEncrypt = "";
		encryptWith = "";
		evaluateArguments(toEncrypt,encryptWith,decrypt,help,
			CMDFunctionality.FOLLOWING_E,CMDFunctionality.FOLLOWING_K,CMDFunctionality.FOLLOWING_T,
			encrypt,importK,importT,3,args,args[3]);
		}
	@Test public void evaluateArgumentsTestDecrypt01(){
		String[] args = {"-c","-e",key06S,expected06S}; // Find Key
		toDecrypt = "";
		decryptWith = "";
		evaluateArguments(toDecrypt,decryptWith,encrypt,help,
			CMDFunctionality.FOLLOWING_D,CMDFunctionality.FOLLOWING_K,CMDFunctionality.FOLLOWING_T,
			decrypt,importK,importT,2,args,args[2]);
		}
	@Test public void evaluateArgumentsTestEncrypt02(){
		String[] args = {"-c","-e",key07S,toEncrypt07}; // Find Key
		toEncrypt = "";
		encryptWith = "";
		evaluateArguments(toEncrypt,encryptWith,decrypt,help,
			CMDFunctionality.FOLLOWING_E,CMDFunctionality.FOLLOWING_K,CMDFunctionality.FOLLOWING_T,
			encrypt,importK,importT,2,args,args[2]);
		}
	@Test public void evaluateArgumentsTestDecrypt02(){
		String[] args = {"-c","-e",key07S,expected07S}; // Find Text
		toDecrypt = "";
		decryptWith = "";
		evaluateArguments(toDecrypt,decryptWith,encrypt,help,
			CMDFunctionality.FOLLOWING_D,CMDFunctionality.FOLLOWING_K,CMDFunctionality.FOLLOWING_T,
			decrypt,importK,importT,3,args,args[3]);
		}
	@Test public void evaluateArgumentsTestEncrypt03(){
		String[] args = {"-c","-e",key08S,toEncrypt08}; // Find Text
		toEncrypt = "";
		encryptWith = "";
		evaluateArguments(toEncrypt,encryptWith,decrypt,help,
			CMDFunctionality.FOLLOWING_E,CMDFunctionality.FOLLOWING_K,CMDFunctionality.FOLLOWING_T,
			encrypt,importK,importT,3,args,args[3]);
		}
	@Test public void evaluateArgumentsTestDecrypt03(){
		String[] args = {"-c","-e",key08S,expected08S}; // Find Key
		toDecrypt = "";
		decryptWith = "";
		evaluateArguments(toDecrypt,decryptWith,encrypt,help,
			CMDFunctionality.FOLLOWING_D,CMDFunctionality.FOLLOWING_K,CMDFunctionality.FOLLOWING_T,
			decrypt,importK,importT,2,args,args[2]);
		}
	@Test public void evaluateArgumentsTestEncrypt04(){
		String[] args = {"-c","-e",key09S,toEncrypt09};
		toEncrypt = "";
		encryptWith = "";
		evaluateArguments(toEncrypt,encryptWith,decrypt,help,
			CMDFunctionality.FOLLOWING_E,CMDFunctionality.FOLLOWING_K,CMDFunctionality.FOLLOWING_T,
			encrypt,importK,importT,2,args,args[2]);
		}
	@Test public void evaluateArgumentsTestDecrypt04(){
		String[] args = {"-c","-e",key09S,expected09S};
		toDecrypt = "";
		decryptWith = "";
		evaluateArguments(toDecrypt,decryptWith,encrypt,help,
			CMDFunctionality.FOLLOWING_D,CMDFunctionality.FOLLOWING_K,CMDFunctionality.FOLLOWING_T,
			decrypt,importK,importT,3,args,args[3]);
		}
	
	private void outputKey(int pos, String key, String text, String keyCreated, String textCreated,
								boolean didCreateKey, boolean didCreateText, String filename, String[] args){
		boolean expected = true;
		CMDFunctionality cmd = new CMDFunctionality(args);
		cmd.export(pos, key, text, keyCreated, textCreated, didCreateKey, didCreateText, filename, args);
		boolean actual = didCreateKey;
		assertTrue("The expected value was "+expected+" but what was returned was "+actual, expected == actual);
	}
	private void outputText(int pos, String key, String text, String keyCreated, String textCreated,
								boolean didCreateKey, boolean didCreateText, String filename, String[] args){
		boolean expected = true;
		CMDFunctionality cmd = new CMDFunctionality(args);
		cmd.export(pos, key, text, keyCreated, textCreated, didCreateKey, didCreateText, filename, args);
		boolean actual = didCreateKey;
		assertTrue("The expected value was "+expected+" but what was returned was "+actual, expected == actual);
	}
	
	@Test public void outputKeyTest00(){
		String filename = this.filename+"Key00";
		String[] args = {cmd, encrypt, key05S, toEncrypt05, exportK, filename};
		outputKey(4,args[2],args[3],keyCreated,textCreated,didCreateKey,didCreateText,filename,args);
	}
	@Test public void outputTextTest00(){
		String filename = this.filename+"Text00";
		String[] args = {cmd, encrypt, key05S, toEncrypt05, exportT, filename};
		outputText(4,args[2],args[3],keyCreated,textCreated,didCreateKey,didCreateText,filename+"Text00",args);
	}
	@Test public void outputKeyTest01(){
		String filename = this.filename+"Key01";
		String[] args = {cmd, encrypt, key06S, toEncrypt06, exportK, filename};
		outputKey(4,args[2],args[3],keyCreated,textCreated,didCreateKey,didCreateText,filename,args);
	}
	@Test public void outputTextTest01(){
		String filename = this.filename+"Text01";
		String[] args = {cmd, encrypt, key06S, toEncrypt06, exportT, filename};
		outputText(4,args[2],args[3],keyCreated,textCreated,didCreateKey,didCreateText,filename+"Text01",args);
	}
	@Test public void outputKeyTest02(){
		String filename = this.filename+"Key02";
		String[] args = {cmd, encrypt, key07S, toEncrypt07, exportK, filename};
		outputKey(4,args[2],args[3],keyCreated,textCreated,didCreateKey,didCreateText,filename,args);
	}
	@Test public void outputTextTest02(){
		String filename = this.filename+"Text02";
		String[] args = {cmd, encrypt, key07S, toEncrypt07, exportT, filename};
		outputText(4,args[2],args[3],keyCreated,textCreated,didCreateKey,didCreateText,filename+"Text02",args);
	}
	@Test public void outputKeyTest03(){
		String filename = this.filename+"Key03";
		String[] args = {cmd, encrypt, key08S, toEncrypt08, exportK, filename};
		outputKey(4,args[2],args[3],keyCreated,textCreated,didCreateKey,didCreateText,filename,args);
	}
	@Test public void outputTextTest03(){
		String filename = this.filename+"Text03";
		String[] args = {cmd, encrypt, key08S, toEncrypt08, exportT, filename};
		outputText(4,args[2],args[3],keyCreated,textCreated,didCreateKey,didCreateText,filename+"Text03",args);
	}
	@Test public void outputKeyTest04(){
		String filename = this.filename+"Key04";
		String[] args = {cmd, encrypt, key09S, toEncrypt09, exportK, filename};
		outputKey(4,args[2],args[3],keyCreated,textCreated,didCreateKey,didCreateText,filename,args);
	}
	@Test public void outputTextTest04(){
		String filename = this.filename+"Text04";
		String[] args = {cmd, encrypt, key09S, toEncrypt09, exportT, filename};
		outputText(4,args[2],args[3],keyCreated,textCreated,didCreateKey,didCreateText,filename,args);
	}
	*/
	
	private void outputKeyAndText(int pos, String[] args, String key, String text, String keyCreated, String textCreated,
									boolean didCreateKey, boolean didCreateText, String filenameKey, String filenameText){
		CMDFunctionality cmd = new CMDFunctionality(args);
		cmd.export(pos,args[2],args[3],keyCreated,textCreated,didCreateKey,didCreateText,filenameKey, filenameText, args);
		boolean expected = true;
		boolean actual = didCreateKey && didCreateText;
		assertTrue("The expected value was "+expected+" but what was returned was "+actual+". The key exported: "+didCreateKey+". The text exported: "+didCreateText, expected == actual);
		
	}
	
	String toEncrypt05 = "WOTT is OPSD"; 		 
	int[][] expected05 = {{1204,1691,1805,1104}, 	//{87,79,84,84,
						  {697,978,1043,636}, 		// 32,105,115,32,
						  {1291,1770,1889,1188}};	// 79,80,83,68}
	String expected05S = "1204,1691,1805,1104;697,978,1043,636;1291,1770,1889,1188;";
	int[][] key05 = {{4,7,8},
					 {2,4,5},
					 {5,7,8}};
	String key05S = "4,7,8;2,4,5;5,7,8;";

	@Test public void outputBoth00(){
		String filenameKey = this.filename+"Key00";
		String filenameText = this.filename+"Text00";
		String[] args = {cmd, encrypt, key05S, toEncrypt05, exportK, filenameKey, exportT, filenameText};
		outputKeyAndText(4,args,args[2],args[3],keyCreated,textCreated,didCreateKey,didCreateText,filenameKey, filenameText);
	}
	
	String toEncrypt06 = "Fuck Writing Tests"; 
	int[][] expected06 = {{3756,4994,3865,4273,2613}, 	//{70,117,99,107,32,
						  {7439,11514,9657,9994,3590},	// 87,114,105,116,105
						  {157,231,204,223,137}, 		// 110,103,32,84,101,
						  {70,117,99,107,32}};			// 115,116,115,32,32}
	String expected06S = "3756,4994,3865,4273,2613;7439,11514,9657,9994,3590;157,231,204,223,137;70,117,99,107,32;";
	int[][] key06 = {{23,8,9,4},						
			 		 {86,2,4,7}};
	String key06S = "23,8,9,4;86,2,4,7;";
	
	@Test public void outputBoth01(){
		String filenameKey = this.filename+"Key00";
		String filenameText = this.filename+"Text00";
		String[] args = {cmd, encrypt, key06S, toEncrypt06, exportK, filenameKey, exportT, filenameText};
		outputKeyAndText(4,args,args[2],args[3],keyCreated,textCreated,didCreateKey,didCreateText,filenameKey, filenameText);
	}
	
	String toEncrypt07 = "Testing others )&$^";	//{84,101,115,116,
	int[][] expected07 = {{1273,1309,1231,807}, // 105,110,103,32,
						  {3533,3699,3471,2417},// 111,116,104,101,
						  {300,327,322,249},	// 114,115,32,41, 
						  {189,211,218,148},	// 38,36,94,32}
						  {84,101,115,116}};
	String expected07S = "1273,1309,1231,807;3533,3699,3471,2417;300,327,322,249;189,211,218,148;84,101,115,116;";
	int[][] key07 = {{1,2,3,4,5},						
			 		 {6,7,8,9,10}};
	String key07S = "1,2,3,4,5;6,7,8,9,10;";
	
	@Test public void outputBoth02(){
		String filenameKey = this.filename+"Key00";
		String filenameText = this.filename+"Text00";
		String[] args = {cmd, encrypt, key07S, toEncrypt07, exportK, filenameKey, exportT, filenameText};
		outputKeyAndText(4,args,args[2],args[3],keyCreated,textCreated,didCreateKey,didCreateText,filenameKey, filenameText);
	}
	
	String toEncrypt08 = "Hello."; 	
	int[][] expected08 = {{399,363}, 	//{72,101,
						  {648,735}, 	// 108,108,
						  {1008,1153}}; // 111,46}
	String expected08S = "399,363;648,735;1008,1153;";
	int[][] key08 = {{1,2},
					 {3,4},
					 {5,6}};
	String key08S = "1,2;3,4;5,6;";
	
	@Test public void outputBoth03(){
		String filenameKey = this.filename+"Key00";
		String filenameText = this.filename+"Text00";
		String[] args = {cmd, encrypt, key08S, toEncrypt08, exportK, filenameKey, exportT, filenameText};
		outputKeyAndText(4,args,args[2],args[3],keyCreated,textCreated,didCreateKey,didCreateText,filenameKey, filenameText);
	}
	
	String toEncrypt09 = "Hello."; 	
	int[][] expected09 = {{431,395},	//{72,101,
						  {759,781}, 	// 108,108,
						  {1008,1153},	// 111,46,
						  {1368,1571}}; // 32,32}
	String expected09S = "431,395;759,781;1008,1153;1368,1571;";
	int[][] key09 = {{1,2},
					 {3,4},
					 {5,6},
					 {7,8}};
	String key09S = "1,2;3,4;5,6;7,8;";
	
	@Test public void outputBoth04(){
		String filenameKey = this.filename+"Key00";
		String filenameText = this.filename+"Text00";
		String[] args = {cmd, encrypt, key09S, toEncrypt09, exportK, filenameKey, exportT, filenameText};
		outputKeyAndText(4,args,args[2],args[3],keyCreated,textCreated,didCreateKey,didCreateText,filenameKey, filenameText);
	}
	
}
