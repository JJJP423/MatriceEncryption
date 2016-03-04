package commandLine;

import java.util.Scanner;

import code.FileIO;
import code.MatriceEncryption;
import gui.ButtonHandler;

public class ScannerFunctions implements Runnable{
	

	Scanner scan;
	String key;
	String text;
	String filename;
	String argument;
	String encryptedString;
	String decryptedString;
	String yesNo;
	int[][] encryptedMatrice;
	int[][] convertedKey;
	String encrypt 	= "-e";
	String decrypt 	= "-d";
	String useKey  	= "-k";
	String useText 	= "-t";
	String importK 	= "-K";
	String importT 	= "-T";
	String exportK 	= "-oK";
	String exportT 	= "-oT";
	String exportKT = "-oKT";

	@Override
	public void run() {
		scan = new Scanner(System.in);
		do{
		System.out.println("Please type -e to encrypt or -d to decrypt: ");
		argument = input();
		}while(!(argument.equals(encrypt)) && !(argument.equals(decrypt)));
		process(argument);
		scan.close();
	}
	
	private void process(String arg) {
		askForKey();
		askForText();
		if(arg.equals(encrypt)){
			convertedKey = ButtonHandler.convertToMatrix(key);
			encryptedMatrice = MatriceEncryption.XbyXencrypt(text, convertedKey);
			text = ButtonHandler.convertFromMatrix(encryptedMatrice);
		}
		if(arg.equals(decrypt)){
			convertedKey = ButtonHandler.convertToMatrix(key);
			encryptedMatrice = ButtonHandler.convertToMatrix(text);
			text = MatriceEncryption.XbyXdecrypt(encryptedMatrice, convertedKey);
		}
		do{
			System.out.println("Do you want to export the key and/or the text? ");
			yesNo = input();
		}while(!yesNo.equals("y") && !yesNo.equals("Y") && !yesNo.equals("n") && !yesNo.equals("N"));
		if(yesNo.equals("y") || yesNo.equals("Y")){
			export();
		}else{
			System.exit(0);
		}
	}
	
	private String input(){
		String contents = scan.nextLine();
		return contents;
	}
	
	private void askForKey(){
		do{
			System.out.println("Please enter -k to type the key or -K to import a key or 0 to exit: ");
			argument = input();
			if(argument.equals("0")){ System.exit(0); }
		}while(!(argument.equals(useKey)) && !(argument.equals(importK)));
		if(argument.equals(useKey)){
			System.out.println("Please enter the key or 0 to exit: ");
			key = input();
			if(key.equals("0")){ System.exit(0); }
		}else{
			System.out.println("Please enter the filename where the key is stored or 0 to exit: ");
			filename = input();
			if(key.equals("0")){ System.exit(0); }
			key = FileIO.readFileToString(filename);
		}
	}
	
	private void askForText(){
		do{
			System.out.println("Please enter -t to type the key or -T to import text or 0 to exit: ");
			argument = input();
			if(argument.equals("0")){ System.exit(0); }
		}while(!argument.equals(useText) && !argument.equals(importT));
		if(argument.equals(useText)){
			System.out.println("Please enter the text or 0 to exit: ");
			text = input();
			if(text.equals("0")){ System.exit(0); }
		}else{
			System.out.println("Please enter the filename where the text is stored or 0 to exit: ");
			filename = input();
			if(text.equals("0")){ System.exit(0); }
			text = FileIO.readFileToString(filename);
		}
	}
	
	private void exportKey(){
		boolean did = false;
		System.out.println("Please enter the filename where the key is to be stored or 0 to exit: ");
		filename = input();
		if(filename.equals("0")){ System.exit(0); }
		did = FileIO.writeStringToFile(filename+".jjr", text);
		System.out.println("The key was succesfully exported: "+did);
	}
	
	private void exportText(){
		boolean did = false;
		System.out.println("Please enter the filename where the text is to be stored or 0 to exit: ");
		filename = input();
		if(filename.equals("0")){ System.exit(0); }
		did = FileIO.writeStringToFile(filename+".jjr", text);
		System.out.println("The text was succesfully exported: "+did);
	}
	
	private void export(){
		System.out.println("Please enter -oK to export the key, -oT to export text, -oKT to export both, or 0 to exit: ");
		do{
			if(argument.equals("0")){ System.exit(0); }
			argument = input();
		}while(!argument.equals(exportK) && !argument.equals(exportT) && !argument.equals(exportKT));
		if(argument.equals(exportK)){
			exportKey();
		}
		if(argument.equals(exportT)){
			exportText();
		}
		if(argument.equals(exportKT)){
			exportKey();
			exportText();
		}
	}
}
