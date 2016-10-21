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
import TreeAutomataParser.TreeNode;
import TreeAutomataParser.TreeParserProvider;

public class TestAllBenchmarks {

	public void testLTL() {
		//redirect and gather error message to String for easier debugging
		ByteArrayOutputStream errMsgs = new ByteArrayOutputStream();
		System.setErr(new PrintStream(errMsgs));

		boolean noFail = true;
		HashSet<String> set = new HashSet<>();
		
		//this array will store duplicate LTLs
		ArrayList<String> dupFile = new ArrayList<>();
		try {
			Files.walk(Paths.get("../LTL/")).forEach(filePath -> {
				if (Files.isRegularFile(filePath) && (filePath.toString().endsWith(".ltl"))) {
					try {
						FileReader file = new FileReader(filePath.toFile());
						List<LTLNode> nodes = LTLParserProvider.parse(file);
						for (LTLNode node : nodes) {
							String cur = node.toString();
							if (set.contains(cur)) {
								dupFile.add(cur);
							} else {
								set.add(cur);
							}
						}
						System.out.println(filePath);
					} catch (Exception e1) {
						
						e1.printStackTrace();
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			noFail = false;
		}finally {
			//redirect system.err back
			System.setErr(new PrintStream(new FileOutputStream(FileDescriptor.err)));
		}
		if (dupFile.isEmpty()) {
			System.out.println("No duplicate formulas");
		} else {
			System.err.println("Found duplicate formulas");
			for (String s : dupFile) {
				System.out.println(s);
			}
		}
		assertTrue(noFail);
	}

	
	public void testRegex() {
		ByteArrayOutputStream errMsgs = new ByteArrayOutputStream();
		System.setErr(new PrintStream(errMsgs));

		boolean noFail = true;
		HashSet<String> set = new HashSet<>();
		ArrayList<String> dupFile = new ArrayList<>();
		try {
			Files.walk(Paths.get("../regex/")).forEach(filePath -> {
				if (Files.isRegularFile(filePath) && (filePath.toString().endsWith(".re"))) {
					try {
						FileReader file = new FileReader(filePath.toFile());
						List<RegexNode> nodes = RegexParserProvider.parse(file);
						for (RegexNode node : nodes) {
							String cur = node.toString();
							if (set.contains(cur)) {
								dupFile.add(cur);
							} else {
								set.add(cur);
							}
						}
						System.out.println(filePath);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});
		} catch (Exception e) {
			
			e.printStackTrace();
			noFail = false;
		} finally {
			System.setErr(new PrintStream(new FileOutputStream(FileDescriptor.err)));
		}
		if (dupFile.isEmpty()) {
			System.out.println("No duplicate formulas");
		} else {
			System.err.println("Found duplicate formulas");
			for (String s : dupFile) {
				System.out.println(s);
			}
		}

		String errors = errMsgs.toString();
		System.err.println(errors);

		assertTrue(noFail);
	}

	
	public void testNFA() {
		ByteArrayOutputStream errMsgs = new ByteArrayOutputStream();
		System.setErr(new PrintStream(errMsgs));

		boolean noFail = true;
		HashSet<String> set = new HashSet<>();
		ArrayList<String> dupFilePath = new ArrayList<>();
		try {
			Files.walk(Paths.get("../NFA/")).forEach(filePath -> {
				if (Files.isRegularFile(filePath) && (filePath.toString().endsWith(".timbuk"))) {
					try {
						FileReader file = new FileReader(filePath.toFile());
						System.out.println(filePath);
						TreeNode node = TreeParserProvider.parse(file);
						System.out.println(filePath + "parsed correctly");
						String cur = node.toString();
						if (set.contains(cur)) {
							dupFilePath.add(filePath.toString());
						} else {
							set.add(cur);
						}
						System.out.println(filePath);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			noFail = false;
		}finally {
			System.setErr(new PrintStream(new FileOutputStream(FileDescriptor.err)));
		}
		if (dupFilePath.isEmpty()) {
			System.out.println("No duplicate formulas");
		} else {
			System.err.println("Found duplicate formulas");
			for (String s : dupFilePath) {
				System.err.println("Duplicate found at: "+ s);
			}
		}

		String errors = errMsgs.toString();
		if(errors.length()>0){
			System.err.println("Found errors!");
			System.err.println(errors);
		}
		
		assertTrue(noFail);
	}
	
	@Test
	public void testTreeAutomata() {
		//ByteArrayOutputStream errMsgs = new ByteArrayOutputStream();
		//System.setErr(new PrintStream(errMsgs));

		boolean noFail = true;
		HashSet<String> set = new HashSet<>();
		ArrayList<String> dupFilePath = new ArrayList<>();
		try {
			Files.walk(Paths.get("../tree-automata/")).forEach(filePath -> {
				if (Files.isRegularFile(filePath) && (filePath.toString().endsWith(".timbuk"))) {
					try {
						FileReader file = new FileReader(filePath.toFile());
						System.out.println(filePath);
						TreeNode node = TreeParserProvider.parse(file);
						System.out.println(filePath + "parsed correctly");
						String cur = node.toString();
						if (set.contains(cur)) {
							dupFilePath.add(filePath.toString());
						} else {
							set.add(cur);
						}
						System.out.println(filePath);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e);
			noFail = false;
		}finally {
			System.setErr(new PrintStream(new FileOutputStream(FileDescriptor.err)));
		}
		if (dupFilePath.isEmpty()) {
			System.out.println("No duplicate formulas");
		} else {
			System.err.println("Found duplicate formulas");
			for (String s : dupFilePath) {
				System.err.println("Duplicate found at: "+ s);
			}
		}

		//String errors = errMsgs.toString();
//		if(errors.length()>0){
//			System.err.println("Found errors!");
//			System.err.println(errors);
//		}
		
		assertTrue(noFail);
	}
	
	
	/*
	 * This seems does not follow Timbuk format
	 * 
	 * */
	public void testSymbolicAutomata() {
		ByteArrayOutputStream errMsgs = new ByteArrayOutputStream();
		System.setErr(new PrintStream(errMsgs));

		boolean noFail = true;
		HashSet<String> set = new HashSet<>();
		ArrayList<String> dupFilePath = new ArrayList<>();
		try {
			Files.walk(Paths.get("../symbolic-tree-automata/")).forEach(filePath -> {
				if (Files.isRegularFile(filePath) && (filePath.toString().endsWith(".timbuk"))) {
					try {
						FileReader file = new FileReader(filePath.toFile());
						System.out.println(filePath);
						TreeNode node = TreeParserProvider.parse(file);
						System.out.println(filePath + "parsed correctly");
						String cur = node.toString();
						if (set.contains(cur)) {
							dupFilePath.add(filePath.toString());
						} else {
							set.add(cur);
						}
					
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			
			noFail = false;
		}finally {
			System.setErr(new PrintStream(new FileOutputStream(FileDescriptor.err)));
		}
		if (dupFilePath.isEmpty()) {
			System.out.println("No duplicate formulas");
		} else {
			System.err.println("Found duplicate formulas");
			for (String s : dupFilePath) {
				System.err.println("Duplicate found at: "+ s);
			}
		}

		String errors = errMsgs.toString();
		if(errors.length()>0){
			System.err.println("Found errors!");
			System.err.println(errors);
		}
		
		assertTrue(noFail);
	}

}
