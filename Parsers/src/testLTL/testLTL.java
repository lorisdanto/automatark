package testLTL;
import LTLparser.TestLTL;
import LTLparser.LTLListNode;
import LTLparser.LTLNode;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

/*
 * import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
 * 
 * */


public class testLTL {
	
	private void run(String input[][], String rightOutput){
		for(String[] i: input){
			TestLTL test = new TestLTL(i);
			LTLListNode root=  (LTLListNode) test.process();
			//TestLTL.printAllNodes(root);
			StringBuilder s= TestLTL.toStringBuilder(root, false);
			String output =s.toString();
			System.out.print("Output of test: "+output);
		    System.out.println("RightOutput: "+rightOutput);
		    assertTrue(output.equals(rightOutput+System.getProperty("line.separator")));
		}
		
	}

	@Test
	public void testAnd(){
		
		String[][] input = {{"a&&b"},{"a&b"}, {"a*b"}};
		String rightOutput = "((a) && (b))";
		run(input,rightOutput);
		

	    //use System.getProperty("line.separator") so the newline can be adjusted according to the operating system
		
		
		/*
		 * below is a example of how to use extraLeaf methods outside of TestLTL.java
		 * */
		//TestLTL test = new TestLTL(input);
		//FormulaNode root= test.process();
		//Set<String> hset = TestLTL.extractLeaf(root, true);
		//TestLTL.printLeaf(hset);
		
		/**
		 * List<LTLNode> list = root.getList();
		for(LTLNode node: list){
			StringBuilder nodes = new StringBuilder();
			LTLNode node1 = node.getMyLTL1();
			LTLNode node2 = node.getMyLTL2();
			node1.toString(nodes,0);
			node2.toString(nodes, 0);
			String info = nodes.toString();
			System.out.print(info);
		}
		 * 
		 * **/
		
		
		
		
	}

	

}
