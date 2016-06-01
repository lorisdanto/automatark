package RegexParser;

import java.io.FileReader;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import LTLparser.LTLNode;
import java_cup.runtime.Symbol;

/**
 * 
 * @author Fang Wang May/30/2016
 *
 * Parser for regular expressions, unit test file is in testRegex/testRegex
 * Detailed structure of the nodes is in FormulaNode.java 
 */
public class RegexParserProvider {

	FileReader inFile;
	private static PrintWriter outFile;
	private static boolean isFile; // true for file, false for string
	private String inputAsString;
	public static RegexParserProvider test;
	
	public RegexParserProvider(String[] args) {
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
					+ "and name of file for unparsed version in two args.\nFor String input, use one arg");
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
	
	public RegexParserProvider(FileReader reader) {
		isFile=true;
		inFile = reader;
	}
	
	private Symbol parseRegex() {
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

	public RegexListNode process() {
		Symbol formula = parseRegex();
		RegexListNode root = (RegexListNode) formula.value;
		return root;
	}
	
	
	/**
	 * This is a general method that print root according to its type(whether it is a String or a File)
	 * */
	public static void printAllNodes(RegexListNode root){
		if(isFile){
			toFile(root);
		}else{
			toStringBuilder(root, true);
		}
	}
	
	public static void toFile(RegexListNode root){
		if(isFile){
			root.unparse(outFile);
			System.out.println("Unparsing finished.");
			outFile.close();
		}else{
			System.err.println("This is a String, use toStringBuilder() instead");
		}
	}
	
	/**
	 * If you have something to do with the String output, use this method
	 * @param boolean is can be set to true if you want to print the output in the console 
	 * */
	public static StringBuilder toStringBuilder(RegexListNode root, boolean printString){
		if(!isFile){
			StringBuilder s = new StringBuilder();
			root.toString(s);
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
	
	public static List<RegexNode> parse(String[] args){
		test = new RegexParserProvider(args);
		RegexListNode root= test.process();
		return root.getList();
		
	}
	
	public static List<RegexNode> parse(FileReader reader){
		test = new RegexParserProvider(reader);
		RegexListNode root= test.process();
		return root.getList();
	}
	

	public static void main(String[] args) {
		List<RegexNode> nodes = parse(args);
		
		//next two lines direct output to file
		RegexListNode a = new RegexListNode(nodes);
		printAllNodes(a);
		
		
		//These lines print output
		//StringBuilder s = new StringBuilder();
		//for(RegexNode node: nodes){
		//	node.toString(s);
		//}
		//System.out.println(s.toString());
		
		
		
	}

}
