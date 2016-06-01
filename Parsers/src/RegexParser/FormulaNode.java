package RegexParser;

import java.io.*;
import java.util.*;

/**
 * 
 * @author Fang Wang May/31/2016
 *
 * Structure:
 * 		FormulaNode
 *  	|		\
 *  RegexNode  RegexListNode 
 * 
 *  RegexListNode is a list of all regular expressions parsed from input.
 *  All other nodes are extended from RegexNode.
 *  UnionNode and ConcatenationNode are list of RegexNodes.
 *  
 *  CharNodes is used as a category, it includes: NormalCharNode, MetaCharNode and EscapedCharNode.
 *  NormalCharNode: char that is treated as a single character.
 *  MetaCharNode: meta character that has special meaning, see comments in MetaCharNode.java for details.
 *  EscapedCharNode: character that comes after a backslash, this character is treated as a single character. see comments in EscapedCharNode.java for details.
 *  
 *  CharacterClass and NotCharacterClass has a list of IntervalNodes. Each interval node can be a single char, range(e.g. [a-z]), 
 *  or the special case that has two chars when the CharacterClass ends with a minus sign(e.g. [+-])  
 *  
 *  RepetitionNode has three modes, {min}, {min,} and {min,max}. The nearest character is detached to RepetitionNode, or node in the parenthesis. 
 *  e.g. a{2},  (ab){1,2}
 *  ab{1,2} is treated as a(b{1,2}) 
 */
public abstract class FormulaNode { 
    // every subclass must provide an unparse operation and toString method
    abstract public void unparse(PrintWriter p);
	abstract public void toString(StringBuilder s);
}

