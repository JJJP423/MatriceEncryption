package code;

import javax.swing.SwingUtilities;

public class Driver {
	public static void main(String[] args) {
		if(args.length == 0){ SwingUtilities.invokeLater(new gui.Gui("","")); }
		else{
			if(args[0].equals("-c")){
				SwingUtilities.invokeLater(new commandLine.ScannerFunctions(false));
			}else{
				if(args[0].equals("-g")){
					SwingUtilities.invokeLater(new commandLine.ScannerFunctions(true));
				}else{
					SwingUtilities.invokeLater(new gui.Gui("",""));
				}
			}
		}
	}
}

