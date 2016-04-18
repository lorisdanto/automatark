package LTLparser;

import static org.junit.Assert.*;


import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;

public class testAnd {

	@Test
	public void test() throws IOException {
		String[] path = { "test/LTLparser/and.in", "test/LTLparser/and.out" };
		String rightAnswer = "test/LTLparser/and.right";

		TestLTL testAnd = new TestLTL(path);
		testAnd.process();

		

		// System.setProperty("line.separator","\r\n");
		// String content = new
		// String(Files.readAllBytes(Paths.get(path[1]),Charset.forName("UTF-8")));
		String input = new String(Files.readAllBytes(Paths.get(path[0])), Charset.forName("UTF-8"));
		String output = new String(Files.readAllBytes(Paths.get(path[1])), Charset.forName("UTF-8"));
		String rightOutput = new String(Files.readAllBytes(Paths.get(rightAnswer)),Charset.forName("UTF-8"));
		// String content = readFile(path[1]);
		
		assertTrue(output.equals(rightOutput));
		System.out.println("\nInput to the test:");
		//print the input
		System.out.println(input);
		System.out.println("\nRight output:");
		//print the right answer
		System.out.println(rightOutput);
	}

	

}
