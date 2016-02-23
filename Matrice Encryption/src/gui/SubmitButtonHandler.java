package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import code.FileIO;

public class SubmitButtonHandler implements ActionListener{	
	
	private int typeCode;
	private char kt;
	private String aKey;
	private String someText;
	private String aName;
	private JFrame frameInfo;
	private JTextField textInfo;
	private JTextArea keyField;
	private JTextArea textField;
	
	public SubmitButtonHandler(int typeCode, char kt, String aKey, String someText, JFrame frameInfo, JTextField textInfo, JTextArea key, JTextArea text){
		this.typeCode = typeCode;
		this.kt = kt;
		this.aKey = aKey;
		this.someText = someText;
		this.frameInfo = frameInfo;
		this.textInfo = textInfo;
		keyField = key;
		textField = text;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		aName = textInfo.getText();
		/*
		System.out.println("Submit Pressed");
		System.out.print("The file to ");
		if(typeCode==0){ System.out.print("output");}
		if(typeCode==1){ System.out.print("input");}
		System.out.println(" is named "+aName);
		System.out.print("The type is a ");
		if(kt=='k'){ System.out.println("key whose value is: "+aKey);}
		if(kt=='t'){ System.out.println("text whose value is "+someText);}
		*/
		if(typeCode==0){ inputOutput(typeCode,kt,aKey,someText,aName); }
		if(typeCode==1){ 
			if(kt == 'k'){
				String inKey = inputOutput(typeCode,kt,aKey,someText,aName);
				keyField.setText(inKey);
			}
			if(kt == 't'){
				String inText = inputOutput(typeCode,kt,aKey,someText,aName);
				textField.setText(inText);
			}
		}
		frameInfo.dispose();
	}
	
	public static String inputOutput(int typeCode, char kt, String aKey, String someText, String aName){
		String worked = "false";
		Boolean did = false;
		String fileExtension = ".jjr";
		switch(typeCode){
		case 0: // Indicates output
			switch(kt){
				case 'k': // Export Key
					did = FileIO.writeStringToFile(aName+fileExtension, aKey);
					if(did){ worked = "true"; }
					return worked;
				case 't': // Export Text
					did = FileIO.writeStringToFile(aName+fileExtension, someText);
					if(did){ worked = "true"; }
					return worked;
			}break;
		case 1: //Indicates input
			switch(kt){
				case 'k': // Import Key
					worked = FileIO.readFileToString(aName+fileExtension);
					return worked;
				case 't': // Import Text
					worked = FileIO.readFileToString(aName+fileExtension);
					return worked;
				}break;
			}
		return worked;
		}

}
