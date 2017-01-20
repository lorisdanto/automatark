package testBenchmarks;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.*;
import static org.junit.Assert.*;

import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Test;

import LTLparser.LTLNode;
import LTLparser.LTLParserProvider;

import RegexParser.RegexParserProvider;
import RegexParser.RegexNode;
import TreeAutomataParser.TreeNode;
import TreeAutomataParser.TreeParserProvider;


public class CountBenchmarkStates {
	BigInteger LTLStateSum = BigInteger.valueOf(0);
	long LTLNodeCount = (long)0;
	BigInteger LTLAverage = BigInteger.valueOf(0);
	
	BigInteger RegexSizeSum = BigInteger.valueOf(0);
	long RegexCount = (long)0;
	BigInteger RegexAverage = BigInteger.valueOf(0);
	
	BigInteger NFATransitionSum = BigInteger.valueOf(0);
	long NFACount = (long)0;
	BigInteger NFAAverage = BigInteger.valueOf(0);
	
	BigInteger TreeAutTransitionSum = BigInteger.valueOf(0);
	long TreeAutCount = (long)0;
	BigInteger TreeAutAverage = BigInteger.valueOf(0);
	
	@Test
	public void countLTL() {		
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
		System.out.println("number of LTLs: "+ LTLNodeCount);
		System.out.println("total number of LTL states: "+ LTLStateSum);
		System.out.println("average state of LTLs: "+ LTLAverage);
}

	@Test
	public void countRegex() {
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
		System.out.println("number of Regex: "+ RegexCount);
		System.out.println("total Regex size: "+ RegexSizeSum);
		System.out.println("average size of Regex: "+ RegexAverage);
	}

	@Test
	public void countNFA() {
		
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
		System.out.println("number of NFAs: "+ NFACount);
		System.out.println("total NFA transitions: "+ NFATransitionSum);
		System.out.println("average transitions of NFA: "+ NFAAverage);
	}
	
	@Test
	public void testTreeAutomata() {
		
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
		System.out.println("number of TreeAutomata: "+ TreeAutCount);
		System.out.println("total TreeAutomata transitions: "+ TreeAutTransitionSum);
		System.out.println("average transitions of TreeAutomata: "+ TreeAutAverage);
	}
}
