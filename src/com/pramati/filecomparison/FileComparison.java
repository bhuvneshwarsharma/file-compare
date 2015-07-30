package com.pramati.filecomparison;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;


public class FileComparison implements FileComparisonInterface {
	
	private FileComparison(){
	}
	
	List<String> collectedNamesList = null;
	Map<String, List<String>> nameMap = null;
	Map<String, List<String>> resultNameMap = null;
	
	public static void main (String a[]) throws IOException {
		
		FileComparison fileCompObj = new FileComparison();
		
		System.out.println("Press 1 : Match By Full Name .");
		System.out.println("Press 2 : Match By First Name .");
		System.out.println("Press 3 : Match By Last Name .");
		System.out.println("Press 4 : Match By First Name And Last Name .");
		
		Scanner scannerObj;
		int choice = 0;
		while (true ) {
			scannerObj  = new Scanner(System.in);
			choice = scannerObj.nextInt();
			if(choice>0 && choice<5) {
				fileCompObj.startFileCompareProcess(choice);
				break;
			} else {
				System.out.println("Please enter valid choice .");
			}
		}
	}
	
	public void startFileCompareProcess (int choice) throws IOException {
		
		Map<String, List<String>> resultNameEntryMap = null;
		
		String homePath = System.getProperty("user.home");
		FileReader firstFileReader = new FileReader(homePath+"/hootcode/java/file-compare/resources/File1.txt");
		BufferedReader firstFileBuffReader = new BufferedReader(firstFileReader);
		
		List<String> firstFileNameList = new ArrayList<String>();
		String line;
		while((line = firstFileBuffReader.readLine()) != null)
			firstFileNameList.add(line);
		
		FileReader secondFileReader = new FileReader(homePath+"//hootcode/java/file-compare/resources/File2.txt");
		BufferedReader secondFileBuffReader = new BufferedReader(secondFileReader);
		
		List<String> secondFileNameList = new ArrayList<String>();
		while((line = secondFileBuffReader.readLine()) != null)
			secondFileNameList.add(line);
		
		switch(choice) {
			case 1 : 
				resultNameEntryMap = matchByFullName(firstFileNameList, secondFileNameList);
				break;
			case 2 : 
				resultNameEntryMap = matchByFirstName(firstFileNameList, secondFileNameList);
				break;
			case 3 : 
				resultNameEntryMap = matchByLastName(firstFileNameList, secondFileNameList);
				break;
			case 4 : 
				resultNameEntryMap = matchByFirstNameAndLastName(firstFileNameList, secondFileNameList);
				break;
			default : System.out.println("Please enter valid choice.");
		}
		
		Iterator<Entry<String, List<String>>> it = resultNameEntryMap.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, List<String>> entryObj = it.next();
			System.out.println(entryObj.getKey() + " in 1st File is matching to "+ entryObj.getValue() +" in 2nd File");
		}
	}
	
	@Override
	public Map<String, List<String>> matchByFullName(List<String> firstFileNameList, List<String> secondFileNameList){
		System.out.println("Matching with full name : ");
		
		nameMap = new HashMap<String, List<String>>();
		resultNameMap = new HashMap<String, List<String>>();
		
		String secondFileNameEntryUpper = null;
		for(String secondFileNameEntry : secondFileNameList) {
			secondFileNameEntryUpper = secondFileNameEntry.toUpperCase();
			
			collectedNamesList = nameMap.get(secondFileNameEntryUpper) ==null ?new ArrayList<String>() : nameMap.get(secondFileNameEntryUpper);
			collectedNamesList.add(secondFileNameEntry);
			nameMap.put(secondFileNameEntryUpper, collectedNamesList);
		}
		
		String firstFileNameEntryUpper = null;
		for(String firstFileNameEntry : firstFileNameList) {
			firstFileNameEntryUpper = firstFileNameEntry.toUpperCase();
			if(nameMap.get(firstFileNameEntryUpper) != null){
				resultNameMap.put(firstFileNameEntry, nameMap.get(firstFileNameEntryUpper));
			}
		}
		return resultNameMap;
	}
	
	@Override
	public Map<String, List<String>> matchByFirstName(List<String> firstFileNameList, List<String> secondFileNameList){
		System.out.println("Matching with first name : ");
		
		nameMap = new HashMap<String, List<String>>();
		resultNameMap = new HashMap<String, List<String>>();
		
		for(String secondFileNameEntry : secondFileNameList) {
			String firstName = secondFileNameEntry.split(" ")[0].toUpperCase();
			
			collectedNamesList = nameMap.get(firstName) ==null ? new ArrayList<String>() : nameMap.get(firstName);
			collectedNamesList.add(secondFileNameEntry);
			nameMap.put(firstName, collectedNamesList);
		}
		
		
		for(String firstFileNameEntry : firstFileNameList) {
			String firstFileName = firstFileNameEntry.split(" ")[0].toUpperCase();
			if(nameMap.get(firstFileName) != null){
				resultNameMap.put(firstFileNameEntry, nameMap.get(firstFileName));
			}
		}
		return resultNameMap;
	}
	
	@Override
	public Map<String, List<String>> matchByLastName(List<String> firstFileNameList, List<String> secondFileNameList){
		System.out.println("Matching with last name : ");
		
		nameMap = new HashMap<String, List<String>>();
		resultNameMap = new HashMap<String, List<String>>();
		
		for(String secondFileNameEntry : secondFileNameList) {
			String[] secondFileNameEntryArr = secondFileNameEntry.split(" ");
			if(secondFileNameEntryArr.length>1) {
				String lastName = secondFileNameEntryArr[secondFileNameEntryArr.length-1].toUpperCase();
				
				collectedNamesList = nameMap.get(lastName) ==null ? new ArrayList<String>() : nameMap.get(lastName);
				collectedNamesList.add(secondFileNameEntry);
				nameMap.put(lastName, collectedNamesList);
			}
		}
		
		for(String firstFileNameEntry : firstFileNameList) {
			String[] firstFileNameEntryArr = firstFileNameEntry.split(" ");
			if(firstFileNameEntryArr.length>1) {
				String firstFileName = firstFileNameEntryArr[firstFileNameEntryArr.length-1].toUpperCase();
				if(nameMap.get(firstFileName) != null){
					resultNameMap.put(firstFileNameEntry, nameMap.get(firstFileName));
				}
			}
		}
		return resultNameMap;
	}
	
	@Override
	public Map<String, List<String>> matchByFirstNameAndLastName(List<String> firstFileNameList, List<String> secondFileNameList){
		System.out.println("Matching with first name and last name : ");
		
		nameMap = new HashMap<String, List<String>>();
		resultNameMap = new HashMap<String, List<String>>();
		
		for(String secondFileNameEntry : secondFileNameList) {
			String[] secondFileNameEntryArr = secondFileNameEntry.split(" ");
			if(secondFileNameEntryArr.length>1) {
				String keyName = (secondFileNameEntryArr[0]+secondFileNameEntryArr[secondFileNameEntryArr.length-1]).toUpperCase();
				
				collectedNamesList = nameMap.get(keyName) ==null ? new ArrayList<String>() : nameMap.get(keyName);
				collectedNamesList.add(secondFileNameEntry);
				nameMap.put(keyName, collectedNamesList);
			}
		}
		
		for(String firstFileNameEntry : firstFileNameList) {
			String[] firstFileNameEntryArr = firstFileNameEntry.split(" ");
			if(firstFileNameEntryArr.length>1) {
				String keyNameFirstFile = (firstFileNameEntryArr[0]+firstFileNameEntryArr[firstFileNameEntryArr.length-1]).toUpperCase();
				if(nameMap.get(keyNameFirstFile) != null){
					resultNameMap.put(firstFileNameEntry, nameMap.get(keyNameFirstFile));
				}
			}
		}
		return resultNameMap;
	}
	
}
