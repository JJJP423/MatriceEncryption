package commandLine;

import java.util.ArrayList;

import code.Functionality;
import code.MatriceEncryption;

public class CMDFunctionality implements Runnable{
	
	public static final int NOT_ENOUGH_ARGS = 1000;
	public static final int FOLLOWING_C = 1001;
	public static final int FOLLOWING_E = 1002;
	public static final int FOLLOWING_D = 1003;
	public static final int FOLLOWING_K = 1004;
	public static final int FOLLOWING_T = 1005;
	public static final int FOLLOWING_oK = 1006;
	public static final int FOLLOWING_oT = 1007;
	public static final int AT_THE_END = 1008;
	
	
	ArrayList<String> allowed = new ArrayList<String>();
	ArrayList<String> arguments = new ArrayList<String>();
	ArrayList<String> parameters = new ArrayList<String>();
	String[] args;
	boolean outputKey;
	boolean outputText;
	boolean commandLine = false;
	boolean didCreateKey;
	boolean didCreateText;
	String keyCreated;
	String textCreated;
	String filenameKey;
	String filenameText;
	String toEncrypt;
	String encryptWith;
	String toDecrypt;
	String decryptWith;
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
	
	public CMDFunctionality(String[] args){
		this.args = args;
	}
	
	@Override
	public void run() {
		allowed.add(help); 		// Help
		allowed.add(cmd); 		// Command Line
		allowed.add(gui); 		// Gui
		allowed.add(encrypt);	// Encrypt
		allowed.add(decrypt);	// Decrypt
		allowed.add(importK);	// Import Key
		allowed.add(importT);	// Import Text
		allowed.add(exportK);	// Export Key
		allowed.add(exportT);	// Export Text
		if(args[pos].equals(help)){	h(); }
		if(args[pos].equals(cmd)) {	c(); }
		if(args[pos].equals(gui)) {	g(); }
		
		
	}

	private void h(){
			System.out.println("This is the list of the commands and how to use them");
			System.out.println("-h   Not used with any other arguments: Brings up the help page");
			System.out.println("      To run the program make sure java is installed on your");
			System.out.println("      computer and type java <path to the file>/<filename>");
			System.out.println("      followed by appropriately used arguments then hit enter");
			System.out.println("-c   Followed by other arguments: Tells program to run in the");
			System.out.println("      command line. Must be followed by -e or -d");
			System.out.println("-g   Followed by other arguments: Tells program to run as a GUI");
			System.out.println("      Can use any of the following arguments after this");
			System.out.println("-e   Followed by a key in the correct format: Tells the program");
			System.out.println("      to encrypt the supplied text with the supplied key. Example:");
			System.out.println("      -e '1,2,3;4,5,6; Hello_There");
			System.out.println("      Cannot come before or after -d");
			System.out.println("-d   Followed by a key in the correct format: Tells the program");
			System.out.println("      to decrypt the supplied text with the supplied key. Example:");
			System.out.println("      -d 1,2,3;4,5,6; 234,456,124;1002,457,892;");
			System.out.println("      Cannot come before or after -e");
			System.out.println("-K   Followed by the filename for a key in the format");
			System.out.println("      <path to file>/<filename> Ex. HelloThere");
			System.out.println("      Follows -d or -e inplace of a key");
			System.out.println("-T   Followed by the filename for some encrypted text in the");
			System.out.println("      format: <path to file>/<filename> Ex. HelloThere");
			System.out.println("      Follows -d <key> inplace of decryptable text");
			System.out.println("-oK  Followed by the filename for a key to be sent to in the format");
			System.out.println("      <path to file>/<filename> Ex. HelloThere");
			System.out.println("      Follows -d or -e inplace of a key");
			System.out.println("-oT  Followed by the filename for some the text to be sent to in the");
			System.out.println("      format: <path to file>/<filename> Ex. HelloThere");
			System.out.println("      Follows -d <key> inplace of decryptable text");
			System.out.println("     Valid input order:");
			System.out.println("     -c -e||-d -k <key>||-K <filename> -t <text>||-T <filename> -oK <filename>&|-oT <filename>");
			System.out.println("     or");
			System.out.println("     -g -k <key>||-K <filename> -t <text>||-T <filename>");
			System.exit(0);
	}
	private void c(){
		commandLine = true;
		didCreateKey = false;
		didCreateText = false;
		 System.out.println("Command Line Operation was chosen");
		if(args.length<4){
			System.out.println("Error code:"+NOT_ENOUGH_ARGS+" Not enough arguments to run the program");
			System.exit(NOT_ENOUGH_ARGS);
		}else{
			 System.out.println("Enough Arguments to continue");
			boolean eOrD = false;
			pos++; // Should be 1
			 System.out.println("Checking pos "+pos+" for a valid argument");
			if(args[pos].equals(encrypt)){ // Should be checking pos 1
				eOrD = true;
				 System.out.println("Encrypt was chosen");
				e();
			}
			if(!eOrD || args[pos].equals(decrypt)){ // Should be checking pos 1
				eOrD = true;
				 System.out.println("Decrypt was chosen");
				d();
			}
			if(!eOrD){
				System.out.println("Error code:"+FOLLOWING_C+" Invalid argument followed -c");
				System.exit(FOLLOWING_C);
			}
		}
		
	}
	
	private void g(){
		
	}
	
	private void e(){
		pos++;  // Should be 2
		 System.out.println("Checking argument "+pos+" for a valid argument or parameter");
		encryptWith = evaluateInput(encryptWith,toEncrypt,decrypt, help, FOLLOWING_E, FOLLOWING_K, FOLLOWING_T, encrypt, importK, importT, pos, args);
		toEncrypt = evaluateInput(encryptWith,toEncrypt,decrypt, help, FOLLOWING_E, FOLLOWING_K, FOLLOWING_T, encrypt, importK, importT, pos, args);
		int[][] key = Functionality.convertToMatrix(encryptWith);
		int[][] encryptedMatrice = MatriceEncryption.XbyXencrypt(toEncrypt, key);
		String encryptedString = Functionality.convertFromMatrix(encryptedMatrice);
		 System.out.println(toEncrypt+" was encrypted with the key: "+encryptWith);
		 System.out.println("The result was: "+encryptedString);
		if(commandLine){
			export(pos,decryptWith,encryptedString,keyCreated,textCreated,didCreateKey,didCreateText,filenameKey, filenameText, args);
			if(didCreateKey){ System.out.println("Key File Created"); }
			if(didCreateText){ System.out.println("Text File Created"); }
		}
	}
	
	private void d(){
		pos++;  // Should be 2
		 System.out.println("Checking argument "+pos+" for a valid argument or parameter");
		decryptWith = evaluateInput(decryptWith,toDecrypt,encrypt, help, FOLLOWING_D, FOLLOWING_K, FOLLOWING_T, decrypt, importK, importT, pos, args);
		toDecrypt = evaluateInput(decryptWith,toDecrypt,encrypt, help, FOLLOWING_D, FOLLOWING_K, FOLLOWING_T, decrypt, importK, importT, pos, args);
		int[][] key = Functionality.convertToMatrix(decryptWith);
		int[][] encrypted = Functionality.convertToMatrix(toDecrypt);
		String decrypted = MatriceEncryption.XbyXdecrypt(encrypted, key);
		 System.out.println(toDecrypt+" was encrypted with the key: "+decryptWith);
		 System.out.println("The result was: "+decrypted);
		if(commandLine){
			export(pos,decryptWith,decrypted,keyCreated,textCreated,didCreateKey,didCreateText,filenameKey, filenameText, args);
			if(didCreateKey){ System.out.println("Key File Created"); }
			if(didCreateText){ System.out.println("Text File Created"); }
		}
	}
	// Need to implement input still
	private String iK(String filename){
		
		return "";
	}
	
	private String iT(String filename){
		
		return "";
	}
	
	public String evaluateInput(String toSet1, String toSet2,
								String cantBe1, String cantBe2,
								int errorCode1, int errorCode2, int errorCode3,
								String from, String checkFor1, String checkFor2,
								int pos, String[] args){
		 System.out.println("Checking if argument "+pos+" is invalid");
		if(args[pos].equals(cantBe1) || args[pos].equals(cantBe2)){ // cantBe1 and cantBe2 follow the argument. Checking pos
			System.out.println("Error code:"+errorCode1+" Invalid arguments followed "+from);
			System.exit(errorCode1);
		}
		pos++;														// Should be 3
		 System.out.println("Checking if argument "+pos+" is invalid");
		if(pos<args.length){
			if(args[pos].equals(cantBe1) || args[pos].equals(cantBe2)){	// cantBe1 and cantBe2 follow the argument. Checking pos
				 System.out.println("Error code:"+errorCode1+" Invalid arguments followed "+from);
				 System.out.println("Exiting Program");
				 System.exit(errorCode1);
			}else{														// Arguments directly after -e are assumed to be valid 
				pos--;													// Should be 2
				 System.out.println("No invalid argument, checking if argument "+pos+" is valid");
				 if(args[pos].equals(importK)){							// Supposed to input a key, check pos for key
					 pos++;												// Should be 3
					  System.out.println("Argument valid, checking "+pos+" for valid parameter");
					  if(allowed.contains(args[pos])){					// Invalid argument at pos
						   System.out.println("Error code:"+errorCode2+" Invalid parameter followed "+checkFor1);
						   System.out.println("Exiting Program");
						   System.exit(errorCode2);
					  }else{
						   System.out.println("Assuming parameter at "+pos+" is valid, importing it");
						   filenameKey = args[pos];						// Assumes input following -K is a valid file at pos
						   toSet1 = iK(filenameKey);					// Tries to input the file of the key to be used
						    System.out.println("Assuming parameter at "+pos+" is valid, importing it: "+toSet1);
						    return toSet1;
					  }
				 }
			}
		}else{															// Should be 4
			pos--;
			 System.out.println("Checking if argument "+pos+" is valid");
			if(args[pos].equals(importT)){						// Checking pos
				 System.out.println("Argument valid, checking argument "+pos+" for valid parameter");
				if(allowed.contains(args[pos])){				// invalid argument at pos
					System.out.println("Error code:"+errorCode3+" Invalid parameter followed "+checkFor2);
					System.out.println("Exiting Program");
					System.exit(errorCode3);
				}else{
					pos--;										// Should be 4
					filenameText = args[pos];						// Assumes input following -T is a valid file at pos
					toSet2 = iT(filenameText);						// Tries to import the file of the text to be used
					 System.out.println("Assuming parameter at "+pos+" is valid, importing it: "+toSet2);
					return toSet2;
				}
			}
		}
		return args[pos];
	}
	
	public void export(int pos, String key, String text, String keyCreated, String textCreated, boolean didCreateKey, boolean didCreateText, String filenameKey, String filenameText, String[] args){
		keyCreated = "false";
		textCreated = "false";
		 System.out.println("Checking if argument "+pos+" is valid");
		if(args[pos].equals(exportK)){							// -oK at pos, supposed to output a key
			pos++;												// pos + 1
			 System.out.println("Valid argument -oK accepted, checking argument "+pos+" for a valid parameter");
			if(allowed.contains(args[pos])){					// Invalid argument at pos
				System.out.println("Error code:"+FOLLOWING_oK+" Invalid arguments followed "+exportK);
				System.out.println("Exiting Program");
				System.exit(FOLLOWING_oK);
			}else{
				 System.out.println("Assumed that argument "+pos+" is a valid parameter in the correct format");
				filenameKey = args[pos];							// Assumes input following -oK is a valid file at pos
				keyCreated = Functionality.inputOutput(0, 'k', key, text, filenameKey);	// Tries to output the file of the key to be save
				 System.out.println("The key file was created: "+keyCreated);
			}
			pos++;												// 
			if(pos==args.length-2){
				if(args[pos].equals(exportT)){						// Checking pos
					pos++;											// 
					if(allowed.contains(args[pos])){				// invalid argument at pos
						System.out.println("Error code:"+FOLLOWING_oT+" Invalid arguments followed "+exportT);
						System.out.println("Exiting Program");
						System.exit(FOLLOWING_oT);
					}else{	
						filenameText = args[pos];						// Assumes output following -oT is a valid file at pos
						textCreated = Functionality.inputOutput(0, 't', key, text, filenameText);	// Tries to import the file of the text to be saved
						 System.out.println("The text file was created: "+textCreated);
					}
				}
			}
		}else{
			if(args[pos].equals(exportT)){						// Checking if text needs to be exported
				pos++;											// 
				 System.out.println("Valid argument -oT accepted, checking argument "+pos+" for a valid parameter");
				if(allowed.contains(args[pos])){				// Invalid argument at pos
					System.out.println("Error code:"+FOLLOWING_oT+" Invalid arguments followed "+exportT);
					System.out.println("Exiting Program");
					System.exit(FOLLOWING_oT);
				}else{
					 System.out.println("Assumed to be valid parameter in the correct format");
					filenameText = args[pos];  						// Assumes input following -T is a valid file at pos
					textCreated = Functionality.inputOutput(0, 't', key, text, filenameText);	// Tries to import the file of the text to be saved
					 System.out.println("The text file was created: "+textCreated);
				}
			}else{
				System.out.println("Error code:"+AT_THE_END+" Invalid arguments followed the last valid argument");
				System.out.println("Exiting Program");
				System.exit(FOLLOWING_oT);
			}
		}
		didCreateKey = false;
		didCreateText = false;
		if("true".equals(keyCreated)){ didCreateKey = true; }
		if("true".equals(textCreated)){ didCreateText = true; }
	}
	
}
