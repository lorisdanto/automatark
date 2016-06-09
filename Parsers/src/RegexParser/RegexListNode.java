package RegexParser;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import RegexParser.FormulaNode;


public class RegexListNode extends FormulaNode{
	private List<RegexNode> myRegexList;

	public RegexListNode(List<RegexNode> S) {
		myRegexList = S;
	}
	
	public void addRegex(RegexNode r){
		myRegexList.add(r);
	}

	public List<RegexNode> getList() {
		return myRegexList;
	}

	public void toString(StringBuilder s) {
		Iterator<RegexNode> it = myRegexList.iterator();
		try {
			while (it.hasNext()) {
				((RegexNode) it.next()).toString(s);
				s.append(System.getProperty("line.separator"));
			}
		} catch (NoSuchElementException ex) {
			System.err.println("unexpected NoSuchElementException in RegexListNode.toString");
			System.exit(-1);
		}
	}

	public void unparse(PrintWriter p) {
		Iterator<RegexNode> it = myRegexList.iterator();
		try {
			while (it.hasNext()) {
				((RegexNode) it.next()).unparse(p);
				p.print(System.getProperty("line.separator"));
			}
		} catch (NoSuchElementException ex) {
			System.err.println("unexpected NoSuchElementException in RegexListNode.unparse");
			System.exit(-1);
		}

	}

}
