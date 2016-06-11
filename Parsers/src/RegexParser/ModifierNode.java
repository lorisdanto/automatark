package RegexParser;

import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

public class ModifierNode extends RegexNode {

	public ModifierNode(RegexNode r, String s) {
		this.myRegex1 = r;
		mySet = new HashSet<Character>();
		for (int i = 0; i < s.length(); i++) {
			this.mySet.add(s.charAt(i));
		}
	}

	@Override
	public void unparse(PrintWriter p) {
		p.print("/");
		myRegex1.unparse(p);
		p.print("/Modifiers: ");
		if (!mySet.isEmpty()) {
			for (char c : this.mySet) {
				p.print(c);
			}
		}

	}

	@Override
	public void toString(StringBuilder s) {
		s.append("/");
		myRegex1.toString(s);
		s.append("/Modifiers: ");
		if (!mySet.isEmpty()) {
			for (char c : this.mySet) {
				s.append(c);
			}
		}

	}

	public RegexNode getMyRegex1() {
		return myRegex1;
	}

	public void addToSet(char c) {
		mySet.add(c);
	}

	public Set<Character> getSet() {
		return mySet;
	}

	protected RegexNode myRegex1;
	protected Set<Character> mySet;
}
