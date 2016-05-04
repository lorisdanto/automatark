package TreeAutomataParser;

public class SymbolWithArityNode {

	public SymbolWithArityNode(String symbol, int arity) {
		mySym = symbol;
        myArity = arity;
	}
	
	public String getSymbol(){
		return mySym;
	}
	
	public int getArity(){
		return myArity;
	}

    private String mySym;
    private int myArity;
}
