package testBenchmarks;

import java.io.BufferedReader;
import java.math.BigInteger;
import java.util.*;

import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;



import LTLparser.LTLNode;
import LTLparser.LTLParserProvider;


public class CountBenchmarkStates {
	static BigInteger LTLStateSum = BigInteger.valueOf(0);
	static long LTLNodeCount = (long)0;
	static BigInteger LTLAverage = BigInteger.valueOf(0);
	
	static BigInteger RegexSizeSum = BigInteger.valueOf(0);
	static long RegexCount = (long)0;
	static BigInteger RegexAverage = BigInteger.valueOf(0);
	
	static BigInteger NFATransitionSum = BigInteger.valueOf(0);
	static long NFACount = (long)0;
	static BigInteger NFAAverage = BigInteger.valueOf(0);
	
	static BigInteger TreeAutTransitionSum = BigInteger.valueOf(0);
	static long TreeAutCount = (long)0;
	static BigInteger TreeAutAverage = BigInteger.valueOf(0);
	
	public static void main(String args[]){
		countLTL();
		countRegex();
		countNFA();
		countTreeAutomata();
		printStatesInfo();
		
	}
	
	
	public static void countLTL() {		
		try {
		Files.walk(Paths.get("../LTL/")).forEach(filePath -> {
			if (Files.isRegularFile(filePath) && (filePath.toString().endsWith(".ltl"))) {
				try {
					FileReader file = new FileReader(filePath.toFile());
					List<LTLNode> nodes = LTLParserProvider.parse(file);
					for (LTLNode node : nodes) {
						LTLNodeCount++;
						int state = LTLParserProvider.countStates(node);
						LTLStateSum = LTLStateSum.add(BigInteger.valueOf(state));
					}
					System.out.println(filePath);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		} catch (Exception e) {
			e.printStackTrace();	
		}
		
		LTLAverage = LTLStateSum.divide(BigInteger.valueOf(LTLNodeCount));
//		System.out.println("number of LTLs: "+ LTLNodeCount);
//		System.out.println("total number of LTL states: "+ LTLStateSum);
//		System.out.println("average state of LTLs: "+ LTLAverage);
}

	
	public static void countRegex() {
		try {
			Files.walk(Paths.get("../regex/")).forEach(filePath -> {
				if (Files.isRegularFile(filePath) && (filePath.toString().endsWith(".re"))) {
					try (BufferedReader br = new BufferedReader(new FileReader(filePath.toFile()))) {
					    String line;
					    System.out.println(filePath);
					    while ((line = br.readLine()) != null) {
					    	RegexCount++;
							int state = line.length();
							RegexSizeSum = RegexSizeSum.add(BigInteger.valueOf(state));
					    }
					}catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();	
		}
			
		RegexAverage = RegexSizeSum.divide(BigInteger.valueOf(RegexCount));
//		System.out.println("number of Regex: "+ RegexCount);
//		System.out.println("total Regex size: "+ RegexSizeSum);
//		System.out.println("average size of Regex: "+ RegexAverage);
	}

	
	public static void countNFA() {
		
		try {
			Files.walk(Paths.get("../NFA/")).forEach(filePath -> {
				if (Files.isRegularFile(filePath) && (filePath.toString().endsWith(".timbuk"))) {
					try (BufferedReader br = new BufferedReader(new FileReader(filePath.toFile()))) {
					    NFACount++;
					    String line;
					    System.out.println(filePath);
					    long curNFATransitions = (long)0;
					    while ((line = br.readLine()) != null) {
					    	if(line.contains("->")){
					    		curNFATransitions++;
					    	}
					    }
					    NFATransitionSum = NFATransitionSum.add(BigInteger.valueOf(curNFATransitions));
					}catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();	
		}
			
		NFAAverage = NFATransitionSum.divide(BigInteger.valueOf(NFACount));
//		System.out.println("number of NFAs: "+ NFACount);
//		System.out.println("total NFA transitions: "+ NFATransitionSum);
//		System.out.println("average transitions of NFA: "+ NFAAverage);
	}
	
	
	public static void countTreeAutomata() {
		
		try {
			Files.walk(Paths.get("../tree-automata/")).forEach(filePath -> {
				if (Files.isRegularFile(filePath) && (filePath.toString().endsWith(".timbuk"))) {
					try (BufferedReader br = new BufferedReader(new FileReader(filePath.toFile()))) {
						TreeAutCount++;
					    String line;
					    System.out.println(filePath);
					    long curTreeAutTransitions = (long)0;
					    while ((line = br.readLine()) != null) {
					    	if(line.contains("->")){
					    		curTreeAutTransitions++;
					    	}
					    }
					    TreeAutTransitionSum = TreeAutTransitionSum.add(BigInteger.valueOf(curTreeAutTransitions));
					}catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();	
		}
			
		TreeAutAverage = TreeAutTransitionSum.divide(BigInteger.valueOf(TreeAutCount));
//		System.out.println("number of TreeAutomata: "+ TreeAutCount);
//		System.out.println("total TreeAutomata transitions: "+ TreeAutTransitionSum);
//		System.out.println("average transitions of TreeAutomata: "+ TreeAutAverage);
	}
	
	public static void printStatesInfo(){
		System.out.println();
		System.out.println("number of LTLs: "+ LTLNodeCount);
		System.out.println("total number of LTL states: "+ LTLStateSum);
		System.out.println("average state of LTLs: "+ LTLAverage);
		System.out.println();
		System.out.println("number of Regex: "+ RegexCount);
		System.out.println("total Regex size: "+ RegexSizeSum);
		System.out.println("average size of Regex: "+ RegexAverage);
		System.out.println();
		System.out.println("number of NFAs: "+ NFACount);
		System.out.println("total NFA transitions: "+ NFATransitionSum);
		System.out.println("average transitions of NFA: "+ NFAAverage);
		System.out.println();
		System.out.println("number of TreeAutomata: "+ TreeAutCount);
		System.out.println("total TreeAutomata transitions: "+ TreeAutTransitionSum);
		System.out.println("average transitions of TreeAutomata: "+ TreeAutAverage);
	}
}
