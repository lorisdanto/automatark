package testLTL;
import LTLparser.TestLTL;

import static org.junit.Assert.*;


import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;

import org.junit.Test;

import LTLparser.FormulaNode;
import LTLparser.TestLTL;

public class testLTL {

	@Test
	public void testAnd(){
		
		String[] input = {"a&&b"};
		TestLTL test = new TestLTL(input);
		FormulaNode root= test.process();
		//TestLTL.printAllNodes(root);
		StringBuilder s= TestLTL.toStringBuilder(root, false);
		String str = s.toString();
		String rightOutput = "((a) && (b))";
		System.out.print("Output of test: "+str);
	    System.out.println("RightOutput: "+rightOutput);

	    //use System.getProperty("line.separator") so the newline can be adjusted according to the operating system
		assertTrue(str.equals(rightOutput+System.getProperty("line.separator")+System.getProperty("line.separator")));
		
		/*
		 * below is a example of how to use extraLeaf methods outside of TestLTL.java
		 * */
		//TestLTL test = new TestLTL(input);
		//FormulaNode root= test.process();
		//Set<String> hset = TestLTL.extractLeaf(root, true);
		//TestLTL.printLeaf(hset);
		
	}

	

}
