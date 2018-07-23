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
	
	private static Boolean error;
	
	public static void getFileName(String path) {
		
		//Create file variable using path given by user
		File root = new File(path);
		
		//Find the files inside directory
		File[] file = root.listFiles();
		
		//Loop through each document in that file
		for(int i = 0; i < file.length; i++) {
			
			//If the current file is a file
			if(file[i].isFile() && !file[i].getName().contains("_")) {
				
				for(String s : type) {
					
					String[] words = file[i].getName().split(" ");
					
					if(file[i].getName().contains(s)) {
						
						dest = new File(file[i].getParent() + "\\" + words[0].toLowerCase() + "_" + s.toLowerCase() + "_pack.pdf");
						
						error = false;
						
					}
				}
				
				file[i].renameTo(dest);
				System.out.println("Success.");
				
			} else {
				
				error = true;
				
			}
		}
		
		checkError(error);
		
	}
	
	public static void checkError(boolean error) {

		if(error == true) {
			
			System.err.println("Error. No valid files in file location. Please enter a valid file location.");
			
		} else if(error == false) {
			
			System.out.println("Success.");
			
		}
	}
	
	public static String renameFile(String filename, String extension) {
		
		String[] words = filename.split(" ");
		
		String filenameUpdated = words[0].toLowerCase() + extension;
		
		return filenameUpdated;
		
	}
	
	//C:\Users\James\Desktop\Testing Files\Files			C:\Users\James\Desktop\downloads
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		String input;
		
		System.out.println("Please enter your file location: ");
		input = sc.nextLine();
		sc.close();

		getFileName(input);
		
	}

}
