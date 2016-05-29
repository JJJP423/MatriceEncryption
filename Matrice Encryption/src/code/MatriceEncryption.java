package code;

public class MatriceEncryption {
	
	public static String showArrays(int[] int1DArray, int[][] int2DArray,
							double[] double1DArray, double[][] double2DArray,
							char[] char1DArray, char[][] char2DArray){
		int which = 0;
		if(int1DArray != null){ 	which = 1; 	} if(int2DArray != null){ 	which = 2;	}
		if(double1DArray != null){ 	which = 3; 	} if(double2DArray != null){which = 4;	}
		if(char1DArray != null){ 	which = 5; 	} if(char2DArray != null){ 	which = 6;	}
		String toReturn =  "	{";
		int width;
		int height;
		switch(which){
		case 0:
			break;
		case 1:
			width = int1DArray.length;
			for(int i=0; i<width;i++){ toReturn = toReturn + " " + int1DArray[i]; System.out.println(toReturn);
				}
			toReturn = toReturn + "}"; break;
		case 2:
			width = int2DArray.length;
			for(int i=0; i<width;i++){ height = int2DArray[i].length; toReturn = "		{";
				for(int j=0; j<height; j++){ toReturn = toReturn + " " + int2DArray[i][j]; }
				toReturn = toReturn + "}";  System.out.println(toReturn); 
				} break;
		case 3:
			width = double1DArray.length;
			for(int i=0; i<width;i++){ toReturn = toReturn + " " + double1DArray[i]; System.out.println(toReturn);
			}
			toReturn = toReturn + "}"; break;
		case 4:
			width = double2DArray.length;
			for(int i=0; i<width;i++){ height = double2DArray[i].length; toReturn = "		{";
				for(int j=0; j<height; j++){ toReturn = toReturn + " " + double2DArray[i][j]; }
				toReturn = toReturn + "}"; System.out.println(toReturn);
				} break;
		case 5:
			width = char1DArray.length;
			for(int i=0; i<width;i++){ toReturn = toReturn + " " + char1DArray[i]; System.out.println(toReturn);
			}
		
			toReturn = toReturn + "}"; break;
		case 6:
			width = char2DArray.length;
			for(int i=0; i<width;i++){ height = char2DArray[i].length; toReturn = "		{";
				for(int j=0; j<height; j++){ toReturn = toReturn + " " + char2DArray[i][j]; }
				toReturn = toReturn + "}"; System.out.println(toReturn);
				} break;
		}
		return toReturn;
	}
	
	private static int[][] squareKey(int[][] key) {
		int keyRows = key.length;
		int keyColumns = key[0].length;
		int[][] temp;
		if(keyRows>keyColumns){
			temp = new int[keyRows][keyRows];
			for(int i=0; i<keyRows; i++){
				for(int j=0; j<keyRows-i; j++){
					temp[i][j] = 1;
				}
			}
		}
		else{
			temp = new int[keyColumns][keyColumns];
			for(int i=0; i<keyColumns; i++){
				for(int j=0; j<keyColumns-i; j++){
					temp[i][j] = 1;
				}
			}
		}
			for(int i=0; i<keyRows; i++){
				for(int j=0; j<keyColumns; j++){
					temp[i][j] = key[i][j];
				}
			}
		key = temp;
//		showArrays(null, temp, null, null, null, null);
		return key;
	}
	
	public static int[][] XbyXencrypt(String input, int[][] key) {
		int keyRows = key.length;
		int keyColumns = key[0].length;
		if(!(keyRows==keyColumns)){ key = squareKey(key); } // Need to make key square to be able to reverse 
		keyRows = key.length;
		keyColumns = key[0].length;
		int spacesToAdd = input.length()%keyColumns;
		if(!(spacesToAdd==0)){ spacesToAdd = keyColumns-(spacesToAdd); }
//		System.out.println("'"+input+"' is to be encrypted with the key:");
//		showArrays(null, key, null, null, null, null);
//		System.out.println(spacesToAdd+" spaces need to be added to the end of the string");
	for(int i=0; i<spacesToAdd; i++){ input = input + ' '; }
		int[] interpretedStep1 = new int[input.length()];
		int interpCols = input.length()/keyColumns;
		int[][] toReturn = new int[keyRows][interpCols];
		int[][] interpreted = new int[keyColumns][interpCols];
	for(int i=0; i<interpretedStep1.length; i++){ interpretedStep1[i] = (int) input.charAt(i); }
		int stringPos = 0;
		for(int i=0; i<keyColumns; i++){ 
			for(int j=0; j<interpCols; j++){
				interpreted[i][j] = interpretedStep1[stringPos];
				stringPos++;
			}
		}
//		System.out.println("'"+input+"' was interpreted into:");
//		showArrays(interpretedStep1, interpreted, null, null, null, null);
		for(int i=0; i<interpreted[0].length; i++){
			for(int j=0; j<keyRows; j++){
				int sum = 0;
				for(int k=0; keyColumns>k; k++){
//					System.out.print(key[j][k]+"*"+interpreted[k][i]+"+"+sum);
					sum = sum+(key[j][k]*interpreted[k][i]);
//					System.out.println("="+sum);
				}
				toReturn[j][i] = sum;
//				System.out.println("The array to be returned looks like:");
//				showArrays(null, toReturn, null, null, null, null);
			}
		}
		return toReturn;
	}

	public static String XbyXdecrypt(int[][] input, int[][] key) {
		String toReturn = "";
		int keyRows = key.length;
		int keyColumns = key[0].length;
		if(!(keyRows==keyColumns)){ key = squareKey(key); }
		keyRows = key.length;
		keyColumns = key[0].length;
		InverseMatrix inverse = new InverseMatrix();
		double[][] keyDouble = new double[keyRows][keyColumns];
		for(int i=0; i<keyRows; i++){
			for(int j=0; j<keyColumns; j++){
				keyDouble[i][j] = (double) key[i][j];
//				showArrays(null, key, null, null, null, null);
//				showArrays(null, null, null, keyDouble, null, null);
			}
		}
		double[][] keyInverse = inverse.invert(keyDouble);
		int[][] interpreted = new int[input.length][input[0].length];
//		System.out.println("The key is:");
//		showArrays(null, key, null, null, null, null);
//		System.out.println("The inverse of the key was found to be:");
//		showArrays(null, null, null, keyInverse, null, null);
//		System.out.println("It is to be multiplied by: ");
//		showArrays(null, input, null, null, null, null);
//		System.out.println("This should give the original interpreted matrix");
		for(int i=0; i<interpreted[0].length; i++){
			for(int j=0; j<keyRows; j++){
				double sum = 0;
				for(int k=0; keyColumns>k; k++){
//					System.out.print(keyInverse[j][k]+"*"+input[k][i]+"+"+sum);
					sum = sum+(keyInverse[j][k]*input[k][i]);
//					System.out.println("="+sum);
				}
				interpreted[j][i] = (int) Math.round(sum);
//				System.out.println("The array to be returned looks like:");
//				showArrays(null, interpreted, null, null, null, null);
			}
		}
		for(int i=0; i<interpreted.length; i++){
			for(int j=0; j<interpreted[i].length; j++){
				toReturn = toReturn + (char) interpreted[i][j];
//				System.out.println(toReturn);
			}
		}
		int toRemoveFrom = 0;
		for(int i=toReturn.length()-1; i>0; i--){
			if(toReturn.charAt(i) == ' '){
				toRemoveFrom++;
			}
			else{
				break;
			}
		}
		String temp = "";
		for(int i=0; i<toReturn.length()-toRemoveFrom; i++){
			temp = temp +toReturn.charAt(i);
		}
		toReturn = temp;
//		System.out.println("The final decryption is: "+toReturn);
		return toReturn;
	}

}
