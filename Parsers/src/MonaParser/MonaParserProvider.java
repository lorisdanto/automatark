package MonaParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java_cup.runtime.Symbol;

public class MonaParserProvider {
	static FileReader inFile;
	
	public MonaParserProvider(FileReader reader) {
		inFile = reader;
	}
	
	public Symbol parseFormula(){
		parser P = new parser(new Yylex(inFile));
		try {
			return P.parse();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	

	public static void main(String[] args) throws IOException{
		
		try {
            inFile = new FileReader(args[0]);
        } catch (FileNotFoundException ex) {
            System.err.println("File " + args[0] + " not found.");
            System.exit(-1);
        }
		MonaParserProvider monaProvider= new MonaParserProvider(inFile);
		Symbol s = monaProvider.parseFormula();
		System.out.println(s);
	}
	
	

}
