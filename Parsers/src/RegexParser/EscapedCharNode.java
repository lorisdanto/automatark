package RegexParser;

import java.io.PrintWriter;

/**
 * 
 * EscapedCharacters are the character that appears after a backslash but is not 
 * a metaCharacter. "\\" is treated as one backslash
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
 * THUS, the character [^tnrfbBdDsSvwW] after a backslash is an escapedChar
 * ONLY the character after the backslash is stored in myVal
 */
public class EscapedCharNode extends CharNode{
	
	public EscapedCharNode(char val) {
		super(val);
	}
	
	public void unparse(PrintWriter p) {
		//p.print("\\");
		p.print("Escaped:");
		p.print(myChar);
	}
	
	public void toString(StringBuilder s) {
		//s.append("\\");
		s.append("Escaped:");
		s.append(myChar);
	}
}
