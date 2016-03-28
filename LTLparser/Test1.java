import java.util.*;
import java.io.*;
import java_cup.runtime.*;  // defines Symbol

/**
 * This program is to be used to test the Scanner.
 * This version is set up to test all tokens, but more code is needed to test 
 * other aspects of the scanner (e.g., input that causes errors, character 
 * numbers, values associated with tokens)
 */
public class Test1 {
    public static void main(String[] args) throws IOException {
                                           // exception may be thrown by yylex
        // test all tokens
    	// ADD CALLS TO OTHER TEST METHODS HERE
    	String[] testFiles = {"allTokens"};
    	for(String file: testFiles){
    		System.out.printf("------Testing %s------\n", file);
    		testAllTokens(file + ".in" , file + ".out");
    		CharNum.num = 1;
    		System.out.printf("------Completed %s test-------\n", file);
    	}
    }

    /**
     * testAllTokens
     *
     * Open and read from file allTokens.txt
     * For each token read, write the corresponding string to allTokens.out
     * If the input file contains all tokens, one per line, we can verify
     * correctness of the scanner by comparing the input and output files
     * (e.g., using a 'diff' command).
     */
    private static void testAllTokens(String input, String output) throws IOException {
        // open input and output files
        FileReader inFile = null;
        PrintWriter outFile = null;
        try {
            inFile = new FileReader(input);
            outFile = new PrintWriter(new FileWriter(output));
        } catch (FileNotFoundException ex) {
            System.err.println("File " + input + " not found.");
            System.exit(-1);
        } catch (IOException ex) {
            System.err.println(output +" cannot be opened.");
            System.exit(-1);
        }

        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();
        while (token.sym != sym.EOF) {
            switch (token.sym) {
            case sym.TRUE:
                outFile.println("Type: true\nLineNum: " + ((TokenVal)token.value).linenum + "\nCharNum: " + ((TokenVal)token.value).charnum + "\n");
                break;
            case sym.FALSE:
                outFile.println("Type: false\nLineNum: " + ((TokenVal)token.value).linenum + "\nCharNum: " + ((TokenVal)token.value).charnum + "\n");
                break;
            case sym.ALWAYS:
                outFile.println("Type: [] always\nLineNum: " + ((TokenVal)token.value).linenum + "\nCharNum: " + ((TokenVal)token.value).charnum + "\n");
                break;
            case sym.EVENTUALLY:
                outFile.println("Type: <> eventually\nLineNum: " + ((TokenVal)token.value).linenum + "\nCharNum: " + ((TokenVal)token.value).charnum + "\n");
                break;
            case sym.IMPLICATION:
                outFile.println("Type: -> implies\nLineNum: " + ((TokenVal)token.value).linenum + "\nCharNum: " + ((TokenVal)token.value).charnum + "\n");
                break;
            case sym.EQUIVALENCE:
                outFile.println("Type: <-> equivalent\nLineNum: " + ((TokenVal)token.value).linenum + "\nCharNum: " + ((TokenVal)token.value).charnum + "\n");
                break;
            case sym.UNTIL:
                outFile.println("Type: U until\nLineNum: " + ((TokenVal)token.value).linenum + "\nCharNum: " + ((TokenVal)token.value).charnum + "\n");
                break;
            case sym.WEAKUNTIL:
                outFile.println("Type: W weakuntil\nLineNum: " + ((TokenVal)token.value).linenum + "\nCharNum: " + ((TokenVal)token.value).charnum + "\n");
                break;
            case sym.DUAL:
                outFile.println("Type: V dual\nLineNum: " + ((TokenVal)token.value).linenum + "\nCharNum: " + ((TokenVal)token.value).charnum + "\n");
                break;
            case sym.RELEASE:
                outFile.println("Type: R release\nLineNum: " + ((TokenVal)token.value).linenum + "\nCharNum: " + ((TokenVal)token.value).charnum + "\n");
                break;
            case sym.NEXT:
                outFile.println("Type: X next\nLineNum: " + ((TokenVal)token.value).linenum + "\nCharNum: " + ((TokenVal)token.value).charnum + "\n");
                break;
            case sym.LPAREN:
            	outFile.println("Type: (\nLineNum: " + ((TokenVal)token.value).linenum + "\nCharNum: " + ((TokenVal)token.value).charnum + "\n");
                break;
            case sym.RPAREN:
            	outFile.println("Type: )\nLineNum: " + ((TokenVal)token.value).linenum + "\nCharNum: " + ((TokenVal)token.value).charnum + "\n");
                break;
            case sym.NEGATION:
                outFile.println("Type: ! negation\nLineNum: " + ((TokenVal)token.value).linenum + "\nCharNum: " + ((TokenVal)token.value).charnum + "\n");
                break;
            case sym.AND:
                outFile.println("Type: && and\nLineNum: " + ((TokenVal)token.value).linenum + "\nCharNum: " + ((TokenVal)token.value).charnum + "\n");
                break;
            case sym.OR:
                outFile.println("Type: || or\nLineNum: " + ((TokenVal)token.value).linenum + "\nCharNum: " + ((TokenVal)token.value).charnum + "\n");
                break;
            case sym.ID:
                outFile.println("Type: ID\nLineNum: " + ((IdTokenVal)token.value).linenum + "\nCharNum: " + ((IdTokenVal)token.value).charnum  
				+ "\nValue: " + ((IdTokenVal)token.value).idVal + "\n");
                break;
			default:
				outFile.println("UNKNOWN TOKEN");
            } // end switch
            token = scanner.next_token();
        } // end while
        outFile.close();
    }
}
