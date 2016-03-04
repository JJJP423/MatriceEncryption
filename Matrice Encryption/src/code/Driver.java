package code;

import javax.swing.SwingUtilities;

import commandLine.CMDFunctionality;

public class Driver {
	public static void main(String[] args) {
		if(args.length == 0){ SwingUtilities.invokeLater(new gui.Gui("","")); }
		else{
			if(args[0].equals("-c")){
				SwingUtilities.invokeLater(new commandLine.ScannerFunctions());
			}else{
				SwingUtilities.invokeLater(new gui.Gui("",""));
			}
		}
	}
}

