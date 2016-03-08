package gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

public class Gui implements Runnable{
	
	JFrame window;
	GridLayout windowLayout;
	JPanel buttons;
	GridLayout buttonLayout;
	JPanel other;
	GridLayout otherLayout;
	JLabel instructions;
	JTextArea _key;
	JTextArea _text;
	MatteBorder border;
	ActionListener al;
	JButton encrypt;
	JButton decrypt;
	JButton importKey;
	JButton exportKey;
	JButton importText;
	JButton exportText;
	
	public Gui(String key, String text){
	window = new JFrame();
	windowLayout = new GridLayout(1,2);
	buttons = new JPanel();
	buttonLayout = new GridLayout(6,1);
	other = new JPanel();
	otherLayout = new GridLayout(3,1);
	instructions = new JLabel();
	border = BorderFactory.createMatteBorder(0, 0, 2, 0, Color.black);
	_key = new JTextArea();
	_key.setText(key);
	_key.setLineWrap(true);
	_key.setBorder(border);
	_text = new JTextArea();
	_text.setText(text);
	_text.setLineWrap(true);
	al = new ButtonHandler(_key,_text);
	encrypt = new JButton("Encrypt Message");
	decrypt = new JButton("Decrypt Message");
	importKey = new JButton("Import Key");
	exportKey = new JButton("Export Key");
	importText = new JButton("Import Text");
	exportText = new JButton("Export Text");
	}
	

	@Override
	public void run() {
		
		window.setLayout(windowLayout);
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.add(buttons);
		buttons.setLayout(buttonLayout);
		buttons.add(encrypt);
		encrypt.addActionListener(al);
		encrypt.setActionCommand("1");
		buttons.add(decrypt);
		decrypt.addActionListener(al);
		decrypt.setActionCommand("2");
		buttons.add(importKey);
		importKey.addActionListener(al);
		importKey.setActionCommand("3");
		buttons.add(exportKey);
		exportKey.addActionListener(al);
		exportKey.setActionCommand("4");
		buttons.add(importText);
		importText.addActionListener(al);
		importText.setActionCommand("5");
		buttons.add(exportText);
		exportText.addActionListener(al);
		exportText.setActionCommand("6");
		window.add(other);
		other.setLayout(otherLayout);
		other.add(instructions);
		instructions.setText("<html><p><center>"+
							 "Enter a numerical key<br>"+
							 "Separate the key lines using ';'<br>"+
							 "Separate the numbers with a comma ','<br>"+
							 "Each row of the key must be the same length<br>"+
							 "Next enter the message you want encrypted"+
							 "</center></html>");
		other.add(_key);
		other.add(_text);
		window.pack();
		window.setVisible(true);
	}
	
	public static void getInfo(String toDisplay, int typeCode, char kt, String aKey, String someText, JTextArea key, JTextArea text){
		JFrame getInfo = new JFrame();
		JLabel queryInfo = new JLabel(toDisplay);
		JTextField retrieveInfo = new JTextField();
		JButton submit = new JButton("Submit");
		GridLayout gl = new GridLayout(3,1);
		getInfo.setLayout(gl);
		getInfo.add(queryInfo);
		getInfo.add(retrieveInfo);
		getInfo.add(submit);
		ActionListener al = new SubmitButtonHandler(typeCode,kt,aKey,someText,getInfo,retrieveInfo,key,text);
		submit.addActionListener(al);
		getInfo.pack();
		getInfo.setVisible(true);			
}

}
