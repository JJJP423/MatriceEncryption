package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import code.MatriceEncryption;

public class ButtonHandler implements ActionListener{

	static int[][] key;
	static String text;
	JTextArea keyField;
	JTextArea textField;
	
	public ButtonHandler(JTextArea k, JTextArea t) {
		keyField = k;
		textField = t;
	}

	public static int[][] convertToMatrix(String text) {
		// System.out.println(text+" is about to be converted to a matrix:");
		int[][] toReturn;
		ArrayList<ArrayList<Integer>> matrixToConvert = new ArrayList<ArrayList<Integer>>();
		int toAdd = 0;
		boolean encounteredNum = false;
		int count = 0;
		ArrayList<Integer> temp = new ArrayList<Integer>();
		String toConvert = "";
		for(int i=0; i<text.length(); i++){ // Add current row, set up new blank row
			int conLeng;
			char pos = text.charAt(i);
			if(pos == ';'){
				conLeng = toConvert.length();
				for(int j=conLeng-1; j>=0; j--){
					// System.out.print(toAdd+"+");
					int toFind = (int) ((toConvert.charAt(j)-'0')*Math.pow(10, count));
					toAdd = toAdd + toFind;
					count++;
					// System.out.println(toFind+"="+toAdd);
				}
				toConvert = "";
				temp.add(toAdd);
				toAdd = 0;
				count = 0;
				encounteredNum = false;
				matrixToConvert.add(temp);
				// System.out.println(temp);
				temp = new ArrayList<Integer>();
				// System.out.println("Adding new row");
				continue;
			}
			if(pos == ',' && encounteredNum){ // Space, add number, then continue to next position in string
				conLeng = toConvert.length();
				for(int j=conLeng-1; j>=0; j--){
					// System.out.print(toAdd+"+");
					int toFind = (int) ((toConvert.charAt(j)-'0')*Math.pow(10, count));
					toAdd = toAdd + toFind;
					count++;
					// System.out.println(toFind+"="+toAdd);
				}
				toConvert = "";
				temp.add(toAdd);
				toAdd = 0;
				encounteredNum = false;
				count = 0;
				continue;
			}
			if(pos >= '0' && pos <= '9'){ // There is part of a number to be interpreted
				encounteredNum = true;
				toConvert = toConvert+pos;
			}
		}
		int toReturnRows = matrixToConvert.size();
		int toReturnColumns = matrixToConvert.get(0).size();
		// System.out.println("Creating a matrix with "+toReturnRows+" rows and "+toReturnColumns+" columns");
		toReturn = new int[toReturnRows][toReturnColumns];
		for(int i=0; i<matrixToConvert.size(); i++){
			int[] tempInt = new int[matrixToConvert.get(i).size()];
			for(int j=0; j<matrixToConvert.get(i).size(); j++){
				int toPut = matrixToConvert.get(i).get(j).intValue();
				// System.out.println("Putting "+toPut+" in position "+j);
				tempInt[j] = toPut;
			}
			// MatriceEncryption.showArrays(tempInt, null, null, null, null, null);
			// MatriceEncryption.showArrays(null, toReturn, null, null, null, null);
			toReturn[i] = tempInt;
		}
		// MatriceEncryption.showArrays(null, toReturn, null, null, null, null);
		return toReturn;
	}
	
	public static String convertFromMatrix(int[][] encrypted) {
		String toReturn = "";
		for(int i=0; i<encrypted.length; i++){
			for(int j=0; j<encrypted[i].length; j++){
				toReturn = toReturn + String.valueOf(encrypted[i][j]);
				if(j!=encrypted[i].length-1){
					toReturn = toReturn + ",";
				}
			}
			toReturn = toReturn + ";";
			if(i!=encrypted.length-1){
				toReturn = toReturn + "";
			}
		}
		return toReturn;
	}
	
	private void getInfo(String toDisplay, int typeCode, char kt, String aKey, String someText){
			JFrame getInfo = new JFrame();
			JLabel queryInfo = new JLabel(toDisplay);
			JTextField retrieveInfo = new JTextField();
			JButton submit = new JButton("Submit");
			GridLayout gl = new GridLayout(3,1);
			getInfo.setLayout(gl);
			getInfo.add(queryInfo);
			getInfo.add(retrieveInfo);
			getInfo.add(submit);
			ActionListener al = new SubmitButtonHandler(typeCode,kt,aKey,someText,getInfo,retrieveInfo,keyField,textField);
			submit.addActionListener(al);
			getInfo.pack();
			getInfo.setVisible(true);			
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int which = Integer.parseInt(e.getActionCommand());
		String toDisplay;
		if(!(textField.getText().equals(""))){ text = textField.getText(); }
		if(!(keyField.getText().equals(""))){ key = convertToMatrix(keyField.getText()); }
		switch(which){
		case 1: // Encrypt text
			// System.out.println("Encrypt Message Pressed");
			encrypt(key,text);
			break;
		case 2: // Decrypt text
			// System.out.println("Decrypt Message Pressed");
			decrypt(key,text);
			break;
		case 3: // Input key
			// System.out.println("Input Key Pressed");
			toDisplay = "Please enter the name of the file to be retrieved";
			getInfo(toDisplay, 1,'k', "","");
			break;
		case 4: // Output key
			// System.out.println("Output Key Pressed");
			toDisplay = "Please enter a name for the file to be created";
			String _key = convertFromMatrix(key);
			// System.out.println("Key to output is: "+key);
			getInfo(toDisplay, 0,'k', _key,"");
			break;
		case 5: // Input text
			// System.out.println("Input Text Pressed");
			toDisplay = "Please enter the name of the file to be retrieved";
			getInfo(toDisplay, 1,'t', "","");
			break;
		case 6: // Output text
			// System.out.println("Output Text Pressed");
			toDisplay = "Please enter a name for the file to be created";
			// System.out.println("Text to output is: "+text);
			getInfo(toDisplay, 0,'t', "",text);
			break;
			}
		}

	public String encrypt(int[][] key2, String text2) {
		int[][] encryptedMatrix = MatriceEncryption.XbyXencrypt(text2, key2);
		String encryptedString = convertFromMatrix(encryptedMatrix);
		textField.setText(encryptedString);
		return encryptedString;
	}

	public String decrypt(int[][] key2, String text2) {
		// System.out.println(text2+" is to be decrypted");
		int[][] toDecrypt = convertToMatrix(text2);
		// System.out.println("The text converted to a matrix is:");
		// MatriceEncryption.showArrays(null, toDecrypt, null, null, null, null);
		// System.out.println("The key being used is:");
		// MatriceEncryption.showArrays(null, key2, null, null, null, null);
		String decrypted = MatriceEncryption.XbyXdecrypt(toDecrypt, key2);
		// System.out.println(decrypted+" was returned");
		textField.setText(decrypted);
		return decrypted;
	}

}
