package TreeAutomataParser;

import java.io.*;
import java_cup.runtime.*;


/**
 * Main program to test and generate the parser.
 *
 * How to use this program:
 * TWO command-line arguments: 
 * 1. the file to be parsed 
 * 2. the output file into which the trees built by the parser should be unparsed 
 * The program opens the two files, creates a scanner and a parser, and calls the
 * parser. If the parse is successful, the formula can be unparsed.
 * 
 * example command lines: test/TreeAutomata/A0054 test/TreeAutomata/A0054.out
 */

public class TreeParserProvider {

	public static void main(String[] args)
	        throws IOException // may be thrown by the scanner
	    {
	        // check for command-line args
	        if (args.length != 2) {
	            System.err.println("please supply name of file to be parsed " +
				                   "and name of file for unparsed version.");
	            System.exit(-1);
	        }

	        // open input file
	        FileReader inFile = null;
	        try {
	            inFile = new FileReader(args[0]);
	        } catch (FileNotFoundException ex) {
	            System.err.println("File " + args[0] + " not found.");
	            System.exit(-1);
	        }

	        // open output file
	        PrintWriter outFile = null;
	        try {
	            outFile = new PrintWriter(args[1]);
	        } catch (FileNotFoundException ex) {
	            System.err.println("File " + args[1] +
	                               " could not be opened for writing.");
	            System.exit(-1);
	        }

	        parser P = new parser(new Yylex(inFile));

	        Symbol root = null; // the parser will return a Symbol whose value
	                            // field is the translation of the root nonterminal
	                            // (i.e., of the nonterminal "program")

	        try {
	            root = P.parse(); // do the parse
	            System.out.println ("program parsed correctly.");
	        } catch (Exception ex){
	            System.err.println("Exception occured during parse: " + ex);
	            System.exit(-1);
	        }
	        ((TreeNode)root.value).unparse(outFile);
	        System.out.println ("unparsing finished.");
	        
	        outFile.close();

	        return;
	    }

}
