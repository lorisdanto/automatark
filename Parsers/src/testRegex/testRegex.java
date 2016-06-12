package testRegex;

import static org.junit.Assert.*;
import org.junit.Test;

import RegexParser.RegexParserProvider;
import RegexParser.RegexListNode;

public class testRegex {

	private void run(String input[][], String rightOutput) {
		for (String[] i : input) {
			RegexParserProvider test = new RegexParserProvider(i);
			RegexListNode root = (RegexListNode) test.process();
			StringBuilder s = RegexParserProvider.toStringBuilder(root, false);
			String output = s.toString();
			System.out.print("Output of test: " + output);
			System.out.println("RightOutput: " + rightOutput);
			assertTrue(output.equals(rightOutput + System.getProperty("line.separator")));
		}
	}

	/**
	 * In Java String, we have to use two backslashes to mean one single
	 * backslash, but when reading the file or type in command line, use one
	 * backslash instead of two.
	 * 
	 * Not yet tested: space in characterClass or outside of characterClass,
	 * this can only be tested in files
	 */
	@Test
	public void testDifferntCharType() {
		// single char 'd'
		String[][] input1 = { { "d" } };
		// meta character '\d'
		String[][] input2 = { { "\\d" } };
		// means only 'a'
		String[][] input3 = { { "\\a" } };
		// '\\d' means a '\' and a 'd'
		String[][] input4 = { { "\\\\d" } };
		
		String rightOutput1 = "(Char:d)";
		String rightOutput2 = "(Meta:d)";
		String rightOutput3 = "(Escaped:a)";
		String rightOutput4 = "(Escaped:\\)(Char:d)";
		run(input1, rightOutput1);
		run(input2, rightOutput2);
		run(input3, rightOutput3);
		run(input4, rightOutput4);
	}

	@Test
	public void testConcatenation() {
		String[][] input1 = { { "ab" } };
		String[][] input2 = { { "abc" } };
		String[][] input3 = { { "abc|def" } };
		String rightOutput1 = "(Char:a)(Char:b)";
		String rightOutput2 = "(Char:a)(Char:b)(Char:c)";
		String rightOutput3 = "((Char:a)(Char:b)(Char:c) | (Char:d)(Char:e)(Char:f))";
		run(input1, rightOutput1);
		run(input2, rightOutput2);
		run(input3, rightOutput3);
	}

	@Test
	public void testUnion() {
		String[][] input1 = { { "a|b" } };
		String[][] input2 = { { "a|b|c" } };
		String rightOutput1 = "((Char:a) | (Char:b))";
		String rightOutput2 = "(((Char:a) | (Char:b)) | (Char:c))";
		run(input1, rightOutput1);
		run(input2, rightOutput2);
	}

	@Test
	public void testSpecialSingleChar() {
		// special char '.' [~\n]
		String[][] input1 = { { "." } };
		// special character '^' Start of String
		String[][] input2 = { { "^a" } };
		// special character '$' End of String
		String[][] input3 = { { "a$" } };
		// mix
		String[][] input4 = { { "^.a$" } };
		String rightOutput1 = "(Dot)";
		String rightOutput2 = "SOS((Char:a))";
		String rightOutput3 = "((Char:a))EOS";
		String rightOutput4 = "SOS((Dot)(Char:a))EOS";
		run(input1, rightOutput1);
		run(input2, rightOutput2);
		run(input3, rightOutput3);
		run(input4, rightOutput4);
	}

	@Test
	public void testPlusStarOptional() {
		// test star and parenthesis
		String[][] input1 = { { "a*" } };
		String[][] input2 = { { "ab*" } };
		String[][] input3 = { { "(ab)*" } };
		// test plus and parenthesis
		String[][] input4 = { { "a+" } };
		String[][] input5 = { { "ab+" } };
		String[][] input6 = { { "(ab)+" } };
		// test star and parenthesis
		String[][] input7 = { { "a?" } };
		String[][] input8 = { { "ab?" } };
		String[][] input9 = { { "(ab)?" } };
		// mix
		String[][] input10 = { { "(a+b)*" } };
		String[][] input11 = { { "(a*b)?" } };
		String[][] input12 = { { "(a?b)+" } };

		String rightOutput1 = "((Char:a)*)";
		String rightOutput2 = "(Char:a)((Char:b)*)";
		String rightOutput3 = "(((Char:a)(Char:b))*)";
		String rightOutput4 = "((Char:a)+)";
		String rightOutput5 = "(Char:a)((Char:b)+)";
		String rightOutput6 = "(((Char:a)(Char:b))+)";
		String rightOutput7 = "((Char:a)?)";
		String rightOutput8 = "(Char:a)((Char:b)?)";
		String rightOutput9 = "(((Char:a)(Char:b))?)";
		String rightOutput10 = "((((Char:a)+)(Char:b))*)";
		String rightOutput11 = "((((Char:a)*)(Char:b))?)";
		String rightOutput12 = "((((Char:a)?)(Char:b))+)";

		run(input1, rightOutput1);
		run(input2, rightOutput2);
		run(input3, rightOutput3);
		run(input4, rightOutput4);
		run(input5, rightOutput5);
		run(input6, rightOutput6);
		run(input7, rightOutput7);
		run(input8, rightOutput8);
		run(input9, rightOutput9);
		run(input10, rightOutput10);
		run(input11, rightOutput11);
		run(input12, rightOutput12);
	}

	@Test
	public void testCharacterClassAndNotCharacterClass() {
		// test CharacterClass
		String[][] input1 = { { "[a]" } };
		String[][] input2 = { { "[ab]" } };
		String[][] input3 = { { "[[]]" } };
		String[][] input4 = { { "[0-9]" } };
		String[][] input5 = { { "[a-z]" } };
		// special case when MINUS is treated as single character
		String[][] input6 = { { "[a-z-]" } };
		// special case when MINUS is treated as single character
		String[][] input7 = { { "[a-z+-]" } };
		// [\d]
		String[][] input8 = { { "[\\d]" } };
		// [\\d] should be treated as one backslash and one 'd'
		String[][] input9 = { { "[\\\\d]" } };

		// test NotCharacterClass
		String[][] input10 = { { "[^a]" } };
		String[][] input11 = { { "[^ab]" } };
		String[][] input12 = { { "[^[]]" } };
		String[][] input13 = { { "[^0-9]" } };
		String[][] input14 = { { "[^a-z]" } };
		String[][] input15 = { { "[^a-z-]" } };
		String[][] input16 = { { "[^a-z+-]" } };
		String[][] input17 = { { "[^\\d]" } };
		String[][] input18 = { { "[^\\\\d]" } };

		String rightOutput1 = "([Char:a ])";
		String rightOutput2 = "([Char:a Char:b ])";
		String rightOutput3 = "([Char:[ Char:] ])";
		String rightOutput4 = "([Char:0-Char:9 ])";
		String rightOutput5 = "([Char:a-Char:z ])";
		String rightOutput6 = "([Char:a-Char:z Char:- ])";
		String rightOutput7 = "([Char:a-Char:z Char:+ Char:- ])";
		String rightOutput8 = "([Meta:d ])";
		String rightOutput9 = "([Escaped:\\ Char:d ])";
		String rightOutput10 = "([^Char:a ])";
		String rightOutput11 = "([^Char:a Char:b ])";
		String rightOutput12 = "([^Char:[ Char:] ])";
		String rightOutput13 = "([^Char:0-Char:9 ])";
		String rightOutput14 = "([^Char:a-Char:z ])";
		String rightOutput15 = "([^Char:a-Char:z Char:- ])";
		String rightOutput16 = "([^Char:a-Char:z Char:+ Char:- ])";
		String rightOutput17 = "([^Meta:d ])";
		String rightOutput18 = "([^Escaped:\\ Char:d ])";

		run(input1, rightOutput1);
		run(input2, rightOutput2);
		run(input3, rightOutput3);
		run(input4, rightOutput4);
		run(input5, rightOutput5);
		run(input6, rightOutput6);
		run(input7, rightOutput7);
		run(input8, rightOutput8);
		run(input9, rightOutput9);
		run(input10, rightOutput10);
		run(input11, rightOutput11);
		run(input12, rightOutput12);
		run(input13, rightOutput13);
		run(input14, rightOutput14);
		run(input15, rightOutput15);
		run(input16, rightOutput16);
		run(input17, rightOutput17);
		run(input18, rightOutput18);
	}

	@Test
	public void testRepetition() {
		//String rightOutput = "";
		//test three different modes
		String[][] input1 = { { "a{2}" } };
		String[][] input2 = { { "a{23,}" } };
		String[][] input3 = { { "a{23,24}" } };
		// this should be treated as a(b{2})
		String[][] input4 = { { "ab{2}" } };
		// include parenthesis
		String[][] input5 = { { "(ab){2}" } };
		String[][] input6 = { { "(ab){2,}" } };
		String[][] input7 = { { "(ab){23,}" } };
		String[][] input8 = { { "(ab){23,25}" } };
		//include character class
		String[][] input9 = { { "[ab]{2}" } };
		String[][] input10 = { { "[ab]{23,}" } };
		String[][] input11 = { { "[ab]{23,24}" } };
		
		String rightOutput1 = "(( (Char:a) {2}))";
		String rightOutput2 = "(( (Char:a) {23,}))";
		String rightOutput3 = "(( (Char:a) {23,24}))";
		String rightOutput4 = "(Char:a)(( (Char:b) {2}))";
		String rightOutput5 = "(( ((Char:a)(Char:b)) {2}))";
		String rightOutput6 = "(( ((Char:a)(Char:b)) {2,}))";
		String rightOutput7 = "(( ((Char:a)(Char:b)) {23,}))";
		String rightOutput8 = "(( ((Char:a)(Char:b)) {23,25}))";
		String rightOutput9 = "(( ([Char:a Char:b ]) {2}))";
		String rightOutput10 = "(( ([Char:a Char:b ]) {23,}))";
		String rightOutput11 = "(( ([Char:a Char:b ]) {23,24}))";
		
		
		run(input1, rightOutput1);
		run(input2, rightOutput2);
		run(input3, rightOutput3);
		run(input4, rightOutput4);
		run(input5, rightOutput5);
		run(input6, rightOutput6);
		run(input7, rightOutput7);
		run(input8, rightOutput8);
		run(input9, rightOutput9);
		run(input10, rightOutput10);
		run(input11, rightOutput11);
	}

}
