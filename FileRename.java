package rename;

import java.io.*;
import java.util.Scanner;

public class FileRename {
	
	/**
	 * FIX: Countries with two words in their name
	 * 
	 * IDEA 1: save files into another destination chosen by the user
	 */
	
	private static String[] type = {"Tourist", "Business", "Media", "Study", "eVisa"};
	private static File dest;
	
	public static void getFileName(String path) {
		
		boolean error = false;
		
		//Create file variable using path given by user
		File root = new File(path);
		
		//Find the files inside directory
		File[] file = root.listFiles();
		
		//Loop through each document in that file
		for(int i = 0; i < file.length; i++) {
			
			//If the current file is a file
			if(file[i].isFile() && !file[i].getName().contains("_")) {
				
				//Loop through each type of visa
				for(String s : type) {
					
					//If that word contains a visa type
					if(file[i].getName().contains(s)) {
						
						String country;
						
						//Get position of the visa type
						int countryPosition = file[i].getName().indexOf(s);
						
						//Assign substring from start until visa type to variable country
						if(countryPosition >= 0) {
							
							country = file[i].getName().substring(0, countryPosition);
							
						} else { country = null; }
						
						dest = new File(file[i].getParent() + "\\" + country.replace(" ", "").replaceAll("[^a-zA-Z0-9]+","").toLowerCase() + "_" + s.toLowerCase() + "_pack.pdf");
						
					}
				}
				
				file[i].renameTo(dest);
				
			} else {
				
				error = true;
				
			}
		}
		
		checkError(error);
		
	}
	
	public static void checkError(boolean error) {

		if(error == true) {
			
			System.err.println("Error. No valid files in file location. Please enter a valid file location.");
			
		} 
		
		if(error == false) {
			
			System.out.println("Success.");
			
		}
	}
	
	//C:\Users\James\Desktop\Testing Files\Files			C:\Users\James\Desktop\downloads
	
	//Antigua & Barbuda Tourist Visa Application Pack
	//Armenia Business Visa Application Pack
	//South Africa Media Visa Application Pack
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		String input;
		
		System.out.println("Please enter your file location: ");
		input = sc.nextLine();
		sc.close();

		getFileName(input);
		
	}

}
