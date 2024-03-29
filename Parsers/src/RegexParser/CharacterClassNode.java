package RegexParser;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;


public class CharacterClassNode extends RegexNode{
	public CharacterClassNode(List<IntervalNode> list){
		this.myList = list;
	}
	
	@Override
	public void unparse(PrintWriter p) {
		p.print("[");
		Iterator<IntervalNode> it = myList.iterator();
        try {
            while (it.hasNext()) {
                ((IntervalNode)it.next()).unparse(p);
            }
        } catch (NoSuchElementException ex) {
            System.err.println("unexpected NoSuchElementException in CharacterClassNode.print");
            System.exit(-1);
        }
        
        p.print("]");
	}

	@Override
	public void toString(StringBuilder s) {
		s.append("[");
		Iterator<IntervalNode> it = myList.iterator();
        try {
            while (it.hasNext()) {
                ((IntervalNode)it.next()).toString(s);
            }
        } catch (NoSuchElementException ex) {
            System.err.println("unexpected NoSuchElementException in CharacterClassNode.print");
            System.exit(-1);
        }
        
        s.append("]");
	}
	
	public List<IntervalNode> getIntervals(){
    	return myList;
    }
	
	public void addInterval(IntervalNode i){
		myList.add(i);
	}
	
	public int getSize(){
		return myList.size();
	}
	
	private List<IntervalNode> myList;

}
