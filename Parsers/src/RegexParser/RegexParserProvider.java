package RegexParser;

import java.io.FileReader;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import LTLparser.LTLNode;
import java_cup.runtime.Symbol;

/**
 * 
 * @author Fang Wang May/30/2016
 *
 *         Parser for regular expressions, unit test file is in
 *         testRegex/testRegex Detailed structure of the nodes is in
 *         FormulaNode.java No longer needs to use ExtractUsableLines.java, the
 *         provider filters parse-able input lines for you.
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
		isFile = true;
		inFile = reader;
	}

	private Symbol parseRegex(String line) {
		try {
			parser P = new parser(new Yylex(new StringReader(line)));
			return P.parse();

		} catch (Exception e) {
			return null;
		}
	}

	private RegexNode filterModifiers(String line) {
		RegexNode node = null;
		Symbol formula = null;
		boolean hasModifier = false;
		int pos = 0;
		// possibly has modifier
		if (line.length() >= 3 && line.charAt(0) == '/') {
			if (line.charAt(line.length() - 1) == '/') {
				// e.g. /a/ only means 'a'
				hasModifier = false;
				line = line.substring(1, line.length() - 1);
			} else {
				for (pos = line.length() - 1; pos > 0; pos--) {
					char tempChar = line.charAt(pos);
					if (tempChar == '/') {
						hasModifier = true;
						break;
					} else {
						if ((tempChar != 'm') && (tempChar != 'i') && (tempChar != 'H')&& (tempChar != 'U')&& (tempChar != 's')&& (tempChar != 'R')&& (tempChar != 'P')) {
							break;
						}
					}
				}
			}
		}

		if (hasModifier) {
			formula = parseRegex(line.substring(1, pos));
			return new ModifierNode((RegexNode) formula.value, line.substring(pos + 1, line.length()));
		} else {
			formula = parseRegex(line);
			node = (RegexNode) formula.value;
		}

		return node;
	}

	public RegexListNode process() {
		List<RegexNode> list = new LinkedList<RegexNode>();
		if (isFile) {
			try (BufferedReader br = new BufferedReader(inFile)) {
				String fileLine;
				while ((fileLine = br.readLine()) != null) {
					// process the line.
					System.out.println("Line to be parsed is: " + fileLine);
					try {
						list.add(filterModifiers(fileLine));

						// outFile.println(fileLine);
					} catch (NullPointerException e) {
						System.out.println("Cannot be parsed: " + fileLine);
						continue;
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			list.add(filterModifiers(inputAsString));
		}

		RegexListNode rlist = new RegexListNode(list);
		return rlist;
	}

	/**
	 * This is a general method that print root according to its type(whether it
	 * is a String or a File)
	 */
	public static void printAllNodes(RegexListNode root) {
		if (isFile) {
			toFile(root);
		} else {
			toStringBuilder(root, true);
		}
	}

	public static void toFile(RegexListNode root) {
		if (isFile) {
			root.unparse(outFile);
			System.out.println("Unparsing finished.");
			outFile.close();
		} else {
			System.err.println("This is a String, use toStringBuilder() instead");
		}
	}

	/**
	 * If you have something to do with the String output, use this method
	 * 
	 * @param boolean
	 *            is can be set to true if you want to print the output in the
	 *            console
	 */
	public static StringBuilder toStringBuilder(RegexListNode root, boolean printString) {
		if (!isFile) {
			StringBuilder s = new StringBuilder();
			root.toString(s);
			if (printString) {
				String result = s.toString();
				System.out.println(result);
			}
			return s;
		} else {
			System.err.println("This is a File, use toFile() instead");
			return null;
		}
	}

	public static List<RegexNode> parse(String[] args) {
		test = new RegexParserProvider(args);
		RegexListNode root = test.process();
		return root.getList();

	}

	public static List<RegexNode> parse(FileReader reader) {
		test = new RegexParserProvider(reader);
		RegexListNode root = test.process();
		return root.getList();
	}

	public static void main(String[] args) {
		List<RegexNode> nodes = parse(args);

		// next two lines direct output to file
		RegexListNode a = new RegexListNode(nodes);
		printAllNodes(a);

		// These lines print output
		// StringBuilder s = new StringBuilder();
		// for(RegexNode node: nodes){
		// node.toString(s);
		// }
		// System.out.println(s.toString());

	}

}
