package LTLparser;


import java.io.*;
import java_cup.runtime.*;
import java.util.*;

/**
 * Main program to test the parser.
 *
 * There should be 2 command-line arguments:
 *    1. the file to be parsed
 *    2. the output file into which the formula built by the parser should be
 *       unparsed
 * The program opens the two files, creates a scanner and a parser, and
 * calls the parser.  If the parse is successful, the formula is unparsed.
 */

public class TestLTL {
	FileReader inFile;
	private PrintWriter outFile;
	public TestLTL(String[] args){
		// check for command-line args
		if (args.length != 2) {
            System.err.println("please supply name of file to be parsed " +
			                   "and name of file for unparsed version.");
            System.exit(-1);
        }
		// open input file
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
            System.err.println("File " + args[1] +
                               " could not be opened for writing.");
            System.exit(-1);
        }
	}
	
	private Symbol parseFormula(){
		try {
	        parser P = new parser(new Yylex(inFile));
	        return P.parse();
		} catch (Exception e){
			return null;
		}
	}
	
	public void process(){
		Symbol formula = parseFormula();
		FormulaNode root = (FormulaNode)formula.value; 
        root.unparse(outFile, 0);
		System.out.println("Unparsing finished.");
		System.out.println ("return leafNodes");
        Set<String> hset = new HashSet<String>();
        hset = root.returnLeafNodes(new HashSet<String>(), true);
        for (String s: hset){
            System.out.println("leaf: " + s);
        }
        System.out.println ("leafNodes printed");
        outFile.close();
	}
	
    public static void main(String[] args)
        throws IOException // may be thrown by the scanner
    {
    	TestLTL instance = new TestLTL(args);
    	instance.process();
    }
}
