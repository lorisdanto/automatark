package testBenchmarks;


import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;

import java.util.*;
import static org.junit.Assert.*;

import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Test;

import LTLparser.LTLNode;
import LTLparser.LTLParserProvider;

import RegexParser.RegexParserProvider;
import RegexParser.RegexNode;
import TreeAutomataParser.TreeParserProvider;

public class TestAllBenchmarks {

	
	public void testLTL() {
		
		boolean noFail = true;
		HashSet<String> set = new HashSet<>();
		ArrayList<String> wrongSet = new ArrayList<>();
		try {
			Files.walk(Paths.get("../LTL/")).forEach(filePath -> {
				if (Files.isRegularFile(filePath) && (filePath.toString().endsWith(".ltl"))) {
					try {
						FileReader file = new FileReader(filePath.toFile());
						List<LTLNode> nodes = LTLParserProvider.parse(file);
						for(LTLNode node: nodes){
							String cur = node.toString();
							if(set.contains(cur)){
								wrongSet.add(cur);
							}else{
								set.add(cur);
							}
						}
						System.out.println(filePath);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			noFail = false;
		}
		if(wrongSet.isEmpty()){
			System.out.println("No duplicate formulas");
		}else{
			System.err.println("Found duplicate formulas");
			for(String s: wrongSet){
				System.out.println(s);
			}
		}
		assertTrue(noFail);
	}
	
	
	@Test
	public void testRegex() {
		ByteArrayOutputStream errMsgs = new ByteArrayOutputStream();
		System.setErr(new PrintStream(errMsgs));
		
		boolean noFail = true;
		HashSet<String> set = new HashSet<>();
		ArrayList<String> wrongSet = new ArrayList<>();
		try {
			Files.walk(Paths.get("../regex/")).forEach(filePath -> {
				if (Files.isRegularFile(filePath) && (filePath.toString().endsWith(".re"))) {
					try {
						FileReader file = new FileReader(filePath.toFile());
						List<RegexNode> nodes = RegexParserProvider.parse(file);
						for(RegexNode node: nodes){
							String cur = node.toString();
							if(set.contains(cur)){
								wrongSet.add(cur);
							}else{
								set.add(cur);
							}
						}
						System.out.println(filePath);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			noFail = false;
		} finally{
			System.setErr(new PrintStream(new FileOutputStream(FileDescriptor.err)));
		}
		if(wrongSet.isEmpty()){
			System.out.println("No duplicate formulas");
		}else{
			System.err.println("Found duplicate formulas");
			for(String s: wrongSet){
				System.out.println(s);
			}
		}
		
		String errors = errMsgs.toString();
		System.err.println(errors);
		
		assertTrue(noFail);
	}
	
	@Test
	public void testNFA() {
		ByteArrayOutputStream errMsgs = new ByteArrayOutputStream();
		System.setErr(new PrintStream(errMsgs));
		
		boolean noFail = true;
		HashSet<String> set = new HashSet<>();
		ArrayList<String> wrongSet = new ArrayList<>();
		try {
			Files.walk(Paths.get("../NFA/")).forEach(filePath -> {
				if (Files.isRegularFile(filePath) && (filePath.toString().endsWith(".re"))) {
					try {
						FileReader file = new FileReader(filePath.toFile());
						List<RegexNode> nodes = RegexParserProvider.parse(file);
						for(RegexNode node: nodes){
							String cur = node.toString();
							if(set.contains(cur)){
								wrongSet.add(cur);
							}else{
								set.add(cur);
							}
						}
						System.out.println(filePath);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			noFail = false;
		}
		if(wrongSet.isEmpty()){
			System.out.println("No duplicate formulas");
		}else{
			System.err.println("Found duplicate formulas");
			for(String s: wrongSet){
				System.out.println(s);
			}
		}
		
		String errors = errMsgs.toString();
		System.out.println(errors);
		assertTrue(noFail);
	}
	
	
	

}
