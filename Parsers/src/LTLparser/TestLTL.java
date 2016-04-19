package LTLparser;

import java.io.*;
import java_cup.runtime.*;
import java.util.*;

/**
 * Main program to test the parser.
 *
 * Two ways to use this program:
 * 1. When there is ONLY 1 command-line argument. the argument will be interpreted as a String as input 
 * 2. 2 command-line arguments: 1. the file to be parsed 2. the
 * output file into which the formula built by the parser should be unparsed The
 * program opens the two files, creates a scanner and a parser, and calls the
 * parser. If the parse is successful, the formula can be unparsed.
 */

public class TestLTL {
	FileReader inFile;
	private static PrintWriter outFile;
	private static boolean fileOrString; // true for file, false for string
	private String inputAsString;
	public TestLTL(String[] args) {
		// check for command-line args
		if (args.length == 1) {
			fileOrString = false;
			inputAsString = args[0];
			System.out.println("the String to be parsed is " + args[0]);
		} else if (args.length == 2) {
			fileOrString = true;
			System.out
					.println("the input file to be parsed is " + args[0] + "\nthe unparsed output file is " + args[1]);
		} else {
			System.err.println("For file input/output, please supply name of file to be parsed "
					+ "and name of file for unparsed version in two args.");
			System.exit(-1);
		}
		// open input file
		if (args.length == 2) {
			try {
				inFile = new FileReader(args[0]);
			} catch (FileNotFoundException ex) {
				System.err.println("File " + args[0] + " not found.");
				System.exit(-1);
			}
			// open output file
			outFile = null;
			try {
				outFile = new PrintWriter(args[1]);
			} catch (FileNotFoundException ex) {
				System.err.println("File " + args[1] + " could not be opened for writing.");
				System.exit(-1);
			}
		}

	}

	private Symbol parseFormula() {
		try {
			if (fileOrString) {
				parser P = new parser(new Yylex(inFile));
				return P.parse();
			}
			parser P = new parser(new Yylex(new StringReader(inputAsString)));
			return P.parse();

		} catch (Exception e) {
			return null;
		}
	}

	public FormulaNode process() {
		Symbol formula = parseFormula();
		FormulaNode root = (FormulaNode) formula.value;
		return root;
	}
	
	/**
	 * @param FormulaNode, boolean
	 * if boolean is true, return all leafNodes including embedded expressions,
	 * if it is false, return leafNodes except embedded expressions 
	 * */
	public static Set<String> extractLeaf(FormulaNode root, boolean returnEmbeddedExp){
		return root.returnLeafNodes(new HashSet<String>(), returnEmbeddedExp);
	}
	
	/**
	 * This is a general method that print root according to its type(whether it is a String or a File)
	 * */
	public static void printAllNodes(FormulaNode root){
		if(fileOrString){
			toFile(root);
		}else{
			toStringBuilder(root, true);
		}
	}
	
	public static void toFile(FormulaNode root){
		if(fileOrString){
			root.unparse(outFile, 0);
			System.out.println("Unparsing finished.");
			System.out.println("return leafNodes");
			outFile.close();
		}else{
			System.err.println("This is a String, use toStringBuilder() instead");
		}
	}
	
	/**
	 * If you have something to do with the String output, use this method
	 * @param boolean is can be set to true if you want to print the output in the console 
	 * */
	public static StringBuilder toStringBuilder(FormulaNode root, boolean printString){
		if(!fileOrString){
			StringBuilder s = new StringBuilder();
			root.toString(s, 0);
			if(printString){
				String result = s.toString();
				System.out.println(result);
			}
			return s;
		}else{
			System.err.println("This is a File, use toFile() instead");
			return null;
		}	
	}
	
	public static void printLeaf(Set<String> hset){
		int i=1;
		for (String s : hset) {
			System.out.println("leaf " + i +": " + s);
			i++;
		}
		System.out.println("All leafNodes are printed");
	}

	public static void main(String[] args) throws IOException
	{
		TestLTL test = new TestLTL(args);
		FormulaNode root= test.process();
		printAllNodes(root);
		Set<String> hset = extractLeaf(root, true);
		printLeaf(hset);
	}

	
}
