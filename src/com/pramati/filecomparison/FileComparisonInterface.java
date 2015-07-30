package com.pramati.filecomparison;

import java.util.List;
import java.util.Map;

interface FileComparisonInterface {
	
	Map<String, List<String>> matchByFullName(List<String> firstFileNameList, List<String> secondFileNameList);
	
	Map<String, List<String>> matchByFirstName(List<String> firstFileNameList, List<String> secondFileNameList);
	
	Map<String, List<String>> matchByLastName(List<String> firstFileNameList, List<String> secondFileNameList);
	
	Map<String, List<String>> matchByFirstNameAndLastName(List<String> firstFileNameList, List<String> secondFileNameList);
}
