package LTLparser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import java_cup.runtime.Symbol;

/**
 * Main program to test and generate the parser.
 *
 * Two ways to use this program:
 * 1. When there is ONLY 1 command-line argument. the argument will be interpreted as a String as input 
 * 2. 2 command-line arguments: 1. the file to be parsed 2. the
 * output file into which the formula built by the parser should be unparsed The
 * program opens the two files, creates a scanner and a parser, and calls the
 * parser. If the parse is successful, the formula can be unparsed.
 */

public class LTLParserProvider {
	FileReader inFile;
	private static PrintWriter outFile;
	private static boolean isFile; // true for file, false for string
	private String inputAsString;
	public static LTLParserProvider test;
	
	public LTLParserProvider(String[] args) {
		// check for command-line args
		if (args.length == 1) {
			isFile = false;
			inputAsString = args[0];
			System.out.println("the String to be parsed is " + args[0]);
		} else if (args.length == 2) {
			isFile = true;
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

	public LTLParserProvider(FileReader reader) {
		isFile=true;
		inFile = reader;
	}
	
	private Symbol parseFormula() {
		try {
			if (isFile) {
				parser P = new parser(new Yylex(inFile));
				return P.parse();
			}
			parser P = new parser(new Yylex(new StringReader(inputAsString)));
			return P.parse();

		} catch (Exception e) {
			return null;
		}
	}

	public LTLListNode process() {
		Symbol formula = parseFormula();
		LTLListNode root = (LTLListNode) formula.value;
		return root;
	}
	
	/**
	 * @param FormulaNode, boolean
	 * if boolean is true, return all leafNodes including embedded expressions,
	 * if it is false, return leafNodes except embedded expressions 
	 * */
	public static Set<String> extractLeaf(LTLListNode root, boolean returnEmbeddedExp){
		return root.returnLeafNodes(new HashSet<String>(), returnEmbeddedExp);
	}
	
	/**
	 * This is a general method that print root according to its type(whether it is a String or a File)
	 * */
	public static void printAllNodes(LTLListNode root){
		if(isFile){
			toFile(root);
		}else{
			toStringBuilder(root, true);
		}
	}
	
	public static void toFile(LTLListNode root){
		if(isFile){
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
	public static StringBuilder toStringBuilder(LTLListNode root, boolean printString){
		if(!isFile){
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
	
	public static List<LTLNode> parse(String[] args){
		test = new LTLParserProvider(args);
		LTLListNode root= test.process();
		return root.getList();
		
	}
	
	public static List<LTLNode> parse(FileReader reader){
		test = new LTLParserProvider(reader);
		LTLListNode root= test.process();
		return root.getList();
		
	}

	public static void main(String[] args) throws IOException
	{
		//This is how you get a list of LTLNodes, I still use command line arguments,
		//The explanation of how to use the command line argument is at the comment of the program above
		List<LTLNode> nodes = parse(args);
		
		//This is for verification
		StringBuilder s = new StringBuilder();
		for(LTLNode node: nodes){
			node.toString(s, 0);
		}
		System.out.println(s.toString());
		
		/*
		 * 
		 *test = new TestLTL(args);
		 *LTLListNode root= test.process();
		 *printAllNodes(root);
		 *Set<String> hset = extractLeaf(root, true);
		 *printLeaf(hset);
		 * */
		
	}

	
}