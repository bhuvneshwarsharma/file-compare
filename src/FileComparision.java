import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class FileComparision {
	public static void main (String a[]) throws IOException {
		
		System.out.println("Press 1 : Match By Full Name .");
		System.out.println("Press 2 : Match By First Name .");
		System.out.println("Press 3 : Match By Last Name .");
		System.out.println("Press 4 : Match By First Name And Last Name .");
		System.out.println("Press 5 : Match By First Name Or Last Name .");
		
		Scanner sc;
		int choice = 0;
		boolean flag = true;
		while (flag ) {
			sc  = new Scanner(System.in);
			choice = sc.nextInt();
			if(choice>0 && choice<6) {
				flag = false;
				break;
			} else {
				System.out.println("Please enter valid choice .");
			}
		}
		
		String homePath = System.getProperty("user.home");
		FileReader fr1 = new FileReader(homePath+"/hootcode/java/file-compare/resources/File1.txt");
		BufferedReader br1 = new BufferedReader(fr1);
		
		ArrayList<String> lineAr1 = new ArrayList<String>();
		String line;
		while((line = br1.readLine()) != null)
			lineAr1.add(line);
		
		FileReader fr2 = new FileReader(homePath+"//hootcode/java/file-compare/resources/File2.txt");
		BufferedReader br2 = new BufferedReader(fr2);
		
		ArrayList<String> lineAr2 = new ArrayList<String>();
		while((line = br2.readLine()) != null)
			lineAr2.add(line);
		
		switch(choice) {
			case 1 : matchByFullName(lineAr1, lineAr2);
				break;
			case 2 : matchByFirstName(lineAr1, lineAr2);
				break;
			case 3 : matchByLastName(lineAr1, lineAr2);
				break;
			case 4 : matchByFirstNameAndLastName(lineAr1, lineAr2);
				break;
			case 5 : matchByFirstNameOrLastName(lineAr1, lineAr2);
				break;
			default : System.out.println("Please enter valid choice.");
		}
	}
	
	public static void matchByFullName(ArrayList<String> lineAr1, ArrayList<String> lineAr2){
		System.out.println("Matching with full name : ");
		for(String str1 : lineAr1) {
			for(String str2 : lineAr2) {
				if(str1.equalsIgnoreCase(str2))
					System.out.println(str1 + " in 1st File is matching to "+ str2 +" in 2nd File");
			}
		}
	}
	
	public static void matchByFirstName(ArrayList<String> lineAr1, ArrayList<String> lineAr2){
		System.out.println("Matching with first name : ");
		for(String str1 : lineAr1) {
			String[] strArr1 = str1.split(" ");
			for(String str2 : lineAr2) {
				String[] strArr2 = str2.split(" ");
				if(strArr1[0].equalsIgnoreCase(strArr2[0])) {
					System.out.println(str1 + " in 1st File is matching to "+ str2 +" in 2nd File");
				}
			}
		}
	}
	
	public static void matchByLastName(ArrayList<String> lineAr1, ArrayList<String> lineAr2){
		System.out.println("Matching with last name : ");
		for(String str1 : lineAr1) {
			String[] strArr1 = str1.split(" ");
			for(String str2 : lineAr2) { 
				String[] strArr2 = str2.split(" ");
				if(strArr1.length>1 && strArr1.length>1 && strArr1[strArr1.length-1].equalsIgnoreCase(strArr2[strArr2.length-1])) {
					System.out.println(str1 + " in 1st File is matching to "+ str2 +" in 2nd File");
				}
			}
		}
	}
	
	public static void matchByFirstNameAndLastName(ArrayList<String> lineAr1, ArrayList<String> lineAr2){
		System.out.println("Matching with first name and last name : ");
		for(String str1 : lineAr1) {
			String[] strArr1 = str1.split(" ");
			for(String str2 : lineAr2) {
				String[] strArr2 = str2.split(" ");
				if(strArr1.length>1 && strArr1.length>1) {
					if(strArr1[0].equalsIgnoreCase(strArr2[0]) &&
							strArr1[strArr1.length-1].equalsIgnoreCase(strArr2[strArr2.length-1])) {
							System.out.println(str1 + " in 1st File is matching to "+ str2 +" in 2nd File");
					} 
				}
			}
		}
	}
	
	public static void matchByFirstNameOrLastName(ArrayList<String> lineAr1, ArrayList<String> lineAr2){
		System.out.println("Matching with first name or last name : ");
		for(String str1 : lineAr1) {
			String[] strArr1 = str1.split(" ");
			for(String str2 : lineAr2) {
				String[] strArr2 = str2.split(" ");
				if(strArr1.length==1 || strArr1.length==1) {
					if(strArr1[0].equalsIgnoreCase(strArr2[0])) {
							System.out.println(str1 + " in 1st File is matching to "+ str2 +" in 2nd File");
					} else {
						if(strArr1[0].equalsIgnoreCase(strArr2[0]) 
								|| strArr1[strArr1.length-1].equalsIgnoreCase(strArr2[strArr2.length-1])) {
							System.out.println(str1 + " in 1st File is matching to "+ str2 +" in 2nd File");
						}
					}
				}
			}
		}
	}
	
}