package TreeAutomataParser;

import java.io.PrintWriter;
import java.util.ArrayList;

public class TreeNode {

	public TreeNode(ArrayList<SymbolWithArityNode> aList, String ID, ArrayList<String> sList, ArrayList<String> fList, ArrayList<TransitionNode> tList){
		arityList = aList;
		myID = ID;
		stateList = sList;
        finalStateList = fList;
        transitionList = tList;   
	}
    
    public void unparse(PrintWriter p){
    	//print Ops first
    	p.print("Ops ");
        for(SymbolWithArityNode node: arityList){
            p.print(node.getSymbol());
            p.print(":");
            p.print(node.getArity());
            p.print(" ");
        }
        p.print(System.getProperty("line.separator"));
        p.print(System.getProperty("line.separator"));
        
        //then print ID
        p.print("Automation ");
		p.print(myID);
		p.print(System.getProperty("line.separator"));
		p.print(System.getProperty("line.separator"));
		
		//then print states
		p.print("States ");
		for(String s: stateList){
			p.print(s);
			p.print(" ");
		}
		p.print(System.getProperty("line.separator"));
		p.print(System.getProperty("line.separator"));
		
		//then print final states
		p.print("Final States ");
		for(String s: finalStateList){
			p.print(s);
			p.print(" ");
		}
		p.print(System.getProperty("line.separator"));
		p.print(System.getProperty("line.separator"));
		
		//then print transitions
		p.println("Transitions");
		for (TransitionNode node: transitionList){
			node.unparse(p);
		}
		p.print(System.getProperty("line.separator"));
		p.print(System.getProperty("line.separator"));
    }
    
    public String toString(){
    	//print Ops first
    	StringBuilder s = new StringBuilder();
    	s.append("Ops ");
        for(SymbolWithArityNode node: arityList){
        	s.append(node.getSymbol());
        	s.append(":");
        	s.append(node.getArity());
        	s.append(" ");
        }
        s.append(System.getProperty("line.separator"));
        s.append(System.getProperty("line.separator"));
        
        //then print ID
        s.append("Automation ");
        s.append(myID);
        s.append(System.getProperty("line.separator"));
        s.append(System.getProperty("line.separator"));
		
		//then print states
        s.append("States ");
		for(String str: stateList){
			s.append(str);
			s.append(" ");
		}
		s.append(System.getProperty("line.separator"));
		s.append(System.getProperty("line.separator"));
		
		//then print final states
		s.append("Final States ");
		for(String str: finalStateList){
			s.append(str);
			s.append(" ");
		}
		s.append(System.getProperty("line.separator"));
		s.append(System.getProperty("line.separator"));
		
		//then print transitions
		s.append("Transitions");
		s.append(System.getProperty("line.separator"));
		for (TransitionNode node: transitionList){
			node.toString(s);
		}
		s.append(System.getProperty("line.separator"));
		s.append(System.getProperty("line.separator"));
		
		return s.toString();
    }
    
    public ArrayList<SymbolWithArityNode> getArity(){
    	return arityList;
    }
    
    public String getID(){
    	return myID;
    }
    
    public ArrayList<String> getStates(){
    	return stateList;
    }
    
    public ArrayList<String> getFinalStates(){
    	return finalStateList;
    }
    
    public ArrayList<TransitionNode> getTransitions(){
    	return transitionList;
    }

	private ArrayList<SymbolWithArityNode> arityList;
	private String myID;
    private ArrayList<String> stateList;
    private ArrayList<String> finalStateList;
    private ArrayList<TransitionNode> transitionList;

}
