package LTLparser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import java_cup.runtime.Symbol;

/**
 * Main program to test the cats parser.
 *
 * There should be 2 command-line arguments:
 *    1. the file to be parsed
 *    2. the output file into which the AST built by the parser should be
 *       unparsed
 * The program opens the two files, creates a scanner and a parser, and
 * calls the parser.  If the parse is successful, the AST is unparsed.
 */

public class Test2 {
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
        ((FormulaNode)root.value).unparse(outFile, 0);
        System.out.println ("unparsing finished.");
        System.out.println ("return leafNodes");
        Set<String> hset = new HashSet<String>();
        hset = ((FormulaNode)root.value).returnLeafNodes(new HashSet<String>(), true);
        for (String s: hset){
            System.out.println("leaf: " + s);
        }
        System.out.println ("leafNodes printed");
        outFile.close();

        return;
    }
}
