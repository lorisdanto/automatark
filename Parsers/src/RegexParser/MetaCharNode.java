package RegexParser;

import java.io.PrintWriter;

/**
 * 
 * MetaCharacters are: 
 * \t {tab character} 
 * \n {newline character} 
 * \r {carriage
 * return character} 
 * \f {form feed character} 
 * \b {match a 'word' boundary} 
 * \B {match not a 'word' boundary} 
 * \d {a digit, [0-9]} 
 * \D {not a digit, [^0-9]} 
 * \s {whitespace, [ \t\n\r\f]} 
 * \S {not a whitespace, [^ \t\n\r\f]} 
 * \v {matches a vertical tab character} 
 * \w {'word' character, [a-zA-Z0-9_]} 
 * \W {not a 'word' character, [^a-zA-Z0-9_]}
 * 
 * ONLY the character after the backslash is stored in myVal
 */
public class MetaCharNode extends CharNode {

	public MetaCharNode(char val) {
		super(val);
	}

	public void unparse(PrintWriter p) {
		p.print("Meta:");
		//p.print("\\");
		p.print(myChar);
	}

	public void toString(StringBuilder s) {
		s.append("Meta:");
		//s.append("\\");
		s.append(myChar);
	}
}
