package com.simplilearn.phaseone;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class AppnPrototype {
	
	//To show initial details
	public static void showDetails()
	{
		int selection1;
		
		System.out.println("******** WELCOME **********");
		System.out.println("      LockedMe.com ");
		System.out.println("   Developed by Rahul");
		System.out.println("    Lockers Pvt. Ltd \n ");
		System.out.println("******** Main Menu *********  ");
		System.out.println(" 1 -> Show files in ascending order");
		System.out.println(" 2 -> Show more options");
		System.out.println(" 3 -> Exit \n");
		
		Scanner sc1= new Scanner(System.in);
		selection1 =sc1.nextInt();
		Scanner sc2= new Scanner(System.in);
		
		
		switch (selection1) {
		case 1:
				int flag=1;
				String address=null;
					while(flag==1) {
					System.out.println("Enter the path: \n");
					address=sc2.nextLine();
					File file = new File(address);
					if(!file.isDirectory())
					{
						System.out.println("Invalid path! \n");
					
					}
					else
					{
						flag=0;
					}
				}
	
				List<File> files= new ArrayList<File>();
				
				//Getting the file names recurcively and sorting
				List<File> fileprint=showFileNames(address, files);
				
				Collections.sort(fileprint);
				//Printing the file list
				System.out.println("The files are: ");
				for (File file2: fileprint) {
			    	System.out.println(file2.getName());
			    	
				}
			    
				
			break;
			
		case 2:
			userInterface();
			
			break;
			
		case 3:
			System.out.println("***** Application closed *****");
			System.exit(0);

		default:
			System.out.println("Incorrect input");
			break;
		}

	}
	
	
	public static void userInterface()
	{
		int selection3;

		System.out.println("\n***** Features *****");
		System.out.println(" 1-> Create a new file");
		System.out.println(" 2-> Delete a file");
		System.out.println(" 3-> Search a file");
		System.out.println(" 4-> Return to main menu ");
		Scanner sc3= new Scanner(System.in);
		selection3=sc3.nextInt();
	
		
		switch (selection3) {
		case 1:
			createFile();
			break;
			
		case 2:
			deleteFile();
			break;
			
		case 3:
			searchFile();
			break;
		case 4:
			showDetails();

		default:
			System.out.println("Error: Invalid input! \n");
			break;
		}
	}
	
	
	//Method to create new file
	public static void createFile()
	{
		String dir;
		String filename;
		Scanner sc4 = new Scanner(System.in);
		
		//Asking the root directory and file name
		System.out.println("Enter the Root directory for file creation: ");
		dir= sc4.nextLine();
		System.out.println("Enter the file name to be created: ");
		filename = sc4.nextLine();
		dir=dir.concat("\\"+filename);
		
		
		//Creating new file
		
		File file = new File(dir);
		
		try {
			if(file.createNewFile())
				System.out.println("Successfully created");
			else
				System.out.println("File not created \n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Incorrect file path!");
			
		}
		
	}
	
	
	
		//Method to delete file
		public static void deleteFile() {
			String dir;
			String filename;
			Scanner sc4 = new Scanner(System.in);
			
			//Asking the root directory and file name
			System.out.println("Enter the Root directory for file deletion: ");
			dir= sc4.nextLine();
			System.out.println("Enter the file name to be deleted: ");
			filename = sc4.nextLine();
			String dir2=dir.concat("\\"+filename);

			try {
				File files= new File(dir);
				int flag=0;
				File[] fileArray=files.listFiles();
				if(fileArray==null) {
					System.out.println("Invalid path");
					
				
					userInterface();
				}
				
				for(File f:fileArray) {
					if(f.getName().equals(filename)) {
						flag=1;
						break;
					}
				}
				if(flag==1) {
					
				Path path = Paths.get(dir2);
				Files.delete(path);
				System.out.println("Successfully deleted");
			}
	
				else {
					System.out.println("FNF (File not found)");
					userInterface();
				}
			}
				
				
				catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("FNF (File Not Found)! ");
				userInterface();
		
			}
			catch(InvalidPathException f) {
				
				System.out.println("Invalid path!!");
				userInterface();
			}

		}
		

		
		//Method  to search for a file
		public static void searchFile() {
			String dir;
			String filename;
			Scanner sc5 = new Scanner(System.in);
			
			//Asking the root directory and file name
			System.out.println("Enter the Root directory for file search: ");
			System.out.println("Example: D:\\Simplilearn \n");
			dir= sc5.nextLine();
			System.out.println("Enter the file name with extension: ");
			System.out.println("Example: sample.txt  \n");
			filename = sc5.nextLine();
			String dir2=dir.concat("\\"+filename);
			
			
			//Creating new file
			File file = new File(dir);
			File files = new File(dir2);
			int flag=0;
			File[] fileArray=file.listFiles();
			if(fileArray==null) {
				System.out.println("Invalid path!");
				userInterface();
			}
			
			for(File f:fileArray) {
				if(f.getName().equals(filename) && !files.isDirectory()) {
					flag=1;
					break;
				}
			}
			
			if(flag==1) {
				System.out.println("File found");			
			}
			
			else {
				System.out.println("File not found!!");
			}
		}
	

	
	//To show the files in acsending order
	public static List<File> showFileNames(String address, List<File> files)
	{
		
		
		File directory = new File(address);
		if(directory.exists()) {

	
		File[] fList = directory.listFiles();
		
		if(fList != null)
	        for (File file : fList) {      
	            if (file.isFile()) {
	                files.add(file);
	            } else if (file.isDirectory()) {
	                showFileNames(file.getAbsolutePath(), files);
	            }
	        }
		
		}
	
	 	Collections.sort(files);
	    
		return files;	
	    }
		


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		while(true) {
		
		showDetails();
		}
	}
		

}
