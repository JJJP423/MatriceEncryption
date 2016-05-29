package code;

import java.util.ArrayList;

public class Functionality {

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
	
	public static int[][] convertToMatrix(String text) {
//		System.out.println(text+" is about to be converted to a matrix:");
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
//					System.out.print(toAdd+"+");
					int toFind = (int) ((toConvert.charAt(j)-'0')*Math.pow(10, count));
					toAdd = toAdd + toFind;
					count++;
//					System.out.println(toFind+"="+toAdd);
				}
				toConvert = "";
				temp.add(toAdd);
				toAdd = 0;
				count = 0;
				encounteredNum = false;
				matrixToConvert.add(temp);
//				System.out.println(temp);
				temp = new ArrayList<Integer>();
//				System.out.println("Adding new row");
				continue;
			}
			if(pos == ',' && encounteredNum){ // Space, add number, then continue to next position in string
				conLeng = toConvert.length();
				for(int j=conLeng-1; j>=0; j--){
//					System.out.print(toAdd+"+");
					int toFind = (int) ((toConvert.charAt(j)-'0')*Math.pow(10, count));
					toAdd = toAdd + toFind;
					count++;
//					System.out.println(toFind+"="+toAdd);
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
//		System.out.println("Creating a matrix with "+toReturnRows+" rows and "+toReturnColumns+" columns");
		toReturn = new int[toReturnRows][toReturnColumns];
		for(int i=0; i<matrixToConvert.size(); i++){
			int[] tempInt = new int[matrixToConvert.get(i).size()];
			for(int j=0; j<matrixToConvert.get(i).size(); j++){
				int toPut = matrixToConvert.get(i).get(j).intValue();
//				System.out.println("Putting "+toPut+" in position "+j);
				tempInt[j] = toPut;
			}
//			MatriceEncryption.showArrays(tempInt, null, null, null, null, null);
//			MatriceEncryption.showArrays(null, toReturn, null, null, null, null);
			toReturn[i] = tempInt;
		}
//		MatriceEncryption.showArrays(null, toReturn, null, null, null, null);
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
	
}
