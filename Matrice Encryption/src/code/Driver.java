package code;

import javax.swing.SwingUtilities;

import commandLine.CMDFunctionality;

public class Driver {
	public static void main(String[] args) {
		if(args.length == 0){ SwingUtilities.invokeLater(new gui.Gui("","")); }
		else{
			SwingUtilities.invokeLater(new CMDFunctionality(args));
		}
	}
}

