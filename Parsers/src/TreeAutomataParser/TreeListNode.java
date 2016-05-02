package TreeAutomataParser;

import java.io.PrintWriter;
import java.util.List;

import LTLparser.LTLNode;

public class TreeListNode {

	public TreeListNode(List<TreeNode> l) {
		myTrees = l;
	}

	
	public void unparse(PrintWriter p) {
		for (TreeNode node: myTrees){
			node.unparse(p);
			p.print(System.getProperty("line.separator"));
		}
	}
	
	public List<TreeNode> getTreeAutomata(){
		return myTrees;
	}
	
	private List<TreeNode> myTrees;
}
