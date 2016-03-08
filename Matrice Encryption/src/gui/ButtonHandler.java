package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextArea;
import code.Functionality;
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
	
	public String encrypt(int[][] key2, String text2) {
		int[][] encryptedMatrix = MatriceEncryption.XbyXencrypt(text2, key2);
		String encryptedString = Functionality.convertFromMatrix(encryptedMatrix);
		textField.setText(encryptedString);
		return encryptedString;
	}

	public String decrypt(int[][] key2, String text2) {
		// System.out.println(text2+" is to be decrypted");
		int[][] toDecrypt = Functionality.convertToMatrix(text2);
		// System.out.println("The text converted to a matrix is:");
		// MatriceEncryption.showArrays(null, toDecrypt, null, null, null, null);
		// System.out.println("The key being used is:");
		// MatriceEncryption.showArrays(null, key2, null, null, null, null);
		String decrypted = MatriceEncryption.XbyXdecrypt(toDecrypt, key2);
		// System.out.println(decrypted+" was returned");
		textField.setText(decrypted);
		return decrypted;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int which = Integer.parseInt(e.getActionCommand());
		String toDisplay;
		if(!(textField.getText().equals(""))){ text = textField.getText(); }
		if(!(keyField.getText().equals(""))){ key = Functionality.convertToMatrix(keyField.getText()); }
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
			Gui.getInfo(toDisplay, 1,'k', "","", keyField, textField);
			break;
		case 4: // Output key
			// System.out.println("Output Key Pressed");
			toDisplay = "Please enter a name for the file to be created";
			String _key = Functionality.convertFromMatrix(key);
			// System.out.println("Key to output is: "+key);
			Gui.getInfo(toDisplay, 0,'k', _key,"", keyField, textField);
			break;
		case 5: // Input text
			// System.out.println("Input Text Pressed");
			toDisplay = "Please enter the name of the file to be retrieved";
			Gui.getInfo(toDisplay, 1,'t', "","", keyField, textField);
			break;
		case 6: // Output text
			// System.out.println("Output Text Pressed");
			toDisplay = "Please enter a name for the file to be created";
			// System.out.println("Text to output is: "+text);
			Gui.getInfo(toDisplay, 0,'t', "",text, keyField, textField);
			break;
			}
		}
}
