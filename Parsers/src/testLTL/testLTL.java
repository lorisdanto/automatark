package testLTL;

import LTLparser.LTLListNode;
import LTLparser.LTLParserProvider;

import org.junit.Test;
import static org.junit.Assert.*;


public class testLTL {

	private void run(String input[][], String rightOutput){
		for(String[] i: input){
			LTLParserProvider test = new LTLParserProvider(i);
			LTLListNode root=  (LTLListNode) test.process();
			//TestLTL.printAllNodes(root);
			StringBuilder s= LTLParserProvider.toStringBuilder(root, false);
			String output =s.toString();
			System.out.print("Output of test: "+output);
		    System.out.println("RightOutput: "+rightOutput);
		    assertTrue(output.equals(rightOutput+System.getProperty("line.separator")));
		}
	}

	@Test
	public void testAnd() {

		String[][] input = { { "a&&b" }, { "a&b" }, { "a*b" }, { "a/\\b" } };
		String rightOutput = "((a) && (b))";
		run(input, rightOutput);

		// use System.getProperty("line.separator") so the newline can be
		// adjusted according to the operating system

		/*
		 * below is a example of how to use extraLeaf methods outside of
		 * TestLTL.java
		 */
		// TestLTL test = new TestLTL(input);
		// FormulaNode root= test.process();
		// Set<String> hset = TestLTL.extractLeaf(root, true);
		// TestLTL.printLeaf(hset);

		/**
		 * List<LTLNode> list = root.getList(); for(LTLNode node: list){
		 * StringBuilder nodes = new StringBuilder(); LTLNode node1 =
		 * node.getMyLTL1(); LTLNode node2 = node.getMyLTL2();
		 * node1.toString(nodes,0); node2.toString(nodes, 0); String info =
		 * nodes.toString(); System.out.print(info); }
		 * 
		 **/

	}

	@Test
	public void testOr() {
		String[][] input = { { "a||b" }, { "a\\/b" }, { "a+b" }, { "a|b" } };
		String rightOutput = "((a) || (b))";
		run(input, rightOutput);
	}
	
	@Test
	public void testXor() {
		String[][] input = { { "a^b" }};
		String rightOutput = "((a) XOR (b))";
		run(input, rightOutput);
	}
	
	@Test
	public void testImplication() {
		String[][] input = { { "(a)implies(b)" }, { "a->b" }};
		String rightOutput = "((a) -> (b))";
		run(input, rightOutput);
	}
	
	@Test
	public void testEquivalence() {
		String[][] input = { { "(a)equivalent(b)" }, { "a<->b" }};
		String rightOutput = "((a) <-> (b))";
		run(input, rightOutput);
	}
	
	@Test
	public void testUntil() {
		String[][] input = { { "(a)until(b)" }, { "(a)U(b)" }};
		String rightOutput = "((a) U (b))";
		run(input, rightOutput);
	}
	
	public void testWeakUntil() {
		String[][] input = { { "(a)W(b)" }};
		String rightOutput = "((a) W (b))";
		run(input, rightOutput);
	}
	
	@Test
	public void testRelease() {
		String[][] input = { { "(a)R(b)" }, { "(a)V(b)" }};
		String rightOutput = "((a) R (b))";
		run(input, rightOutput);
	}
	
	public void testStrongRelease() {
		String[][] input = { { "(a)M(b)" }};
		String rightOutput = "((a) M (b))";
		run(input, rightOutput);
	}
	
	
	@Test
	public void testAlways() {
		String[][] input = { { "G(a)" }, { "always(a)" }, { "[]a" }};
		String rightOutput = "(G(a))";
		run(input, rightOutput);
	}
	
	@Test
	public void testEventually() {
		String[][] input = { { "F(a)" }, { "<>a" }};
		String rightOutput = "(F(a))";
		run(input, rightOutput);
	}
	
	@Test
	public void testNext() {
		String[][] input = { { "X(a)" }};
		String rightOutput = "(X(a))";
		run(input, rightOutput);
	}
	
	@Test
	public void testNegation() {
		String[][] input = { { "!(a)" }};
		String rightOutput = "(!(a))";
		run(input, rightOutput);
	}
	
	@Test
	public void testTrue() {
		String[][] input = { { "true" }};
		String rightOutput = "true";
		run(input, rightOutput);
	}
	
	@Test
	public void testFalse() {
		String[][] input = { { "false" }};
		String rightOutput = "false";
		run(input, rightOutput);
	}
	
	@Test
	public void testExp(){
		String[][] input = { { "\"a0aa\"" }};
		String rightOutput = " \"a0aa\" ";
		run(input, rightOutput);
		
		String[][] input2 = { { "{a1aa}" }};
		String rightOutput2 = " {a1aa} ";
		run(input2, rightOutput2);
	}
	

	@Test
	public void testId(){
		String[][] input = { { "a0" }};
		String[][] input2 = { { "token_45" }};
		String rightOutput = "(a0)";
		String rightOutput2 = "(token_45)";
		run(input, rightOutput);
		run(input2, rightOutput2);
	}
	
	@Test
	public void testPrecedence(){
		String[][] input = { { "a->b||c" }};
		String[][] input2 = { { "a||b&&c" }};
		String[][] input3 = { { "(a)R!(b)" }};
		String rightOutput = "((a) -> ((b) || (c)))";
		String rightOutput2 = "((a) || ((b) && (c)))";
		String rightOutput3 = "((a) R (!(b)))";
		run(input, rightOutput);
		run(input2, rightOutput2);
		run(input3, rightOutput3);
	}
	
	@Test
	public void testAssociativity(){
		String[][] input = { { "a||b||c" }};
		String[][] input2 = { { "a&&b&&c" }};
		String[][] input3 = { { "(a)R(b)R(c)" }};
		String[][] input4 = { { "(a)U(b)U(c)" }};
		
		String rightOutput = "(((a) || (b)) || (c))";
		String rightOutput2 = "(((a) && (b)) && (c))";
		String rightOutput3 = "((a) R ((b) R (c)))";
		String rightOutput4 = "((a) U ((b) U (c)))";
		
		run(input, rightOutput);
		run(input2, rightOutput2);
		run(input3, rightOutput3);
		run(input4, rightOutput4);
		
	}
	
	
	
	
	
	

}
