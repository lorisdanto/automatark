# Symbolic visibly pushdown automata

## Input Format

We use a custom format for our symbolic visibly pushdown automata over the
characer theory of intervals supporting equality predicates.  The format is
specified by the following grammar with the start symbol \<automaton\>.

```
<automaton>          : 'Automaton:' <stats> <transitions> <initials> <finals>

<stats>              : int 'transitions,' int 'states'

<transitions>        : 'Transitions' <trans_list>

<trans_list>         : <trans> <trans> ... // a list of transitions

<trans>              : 'I:' <state> '-' <unary_predicate> '->' <state> // internal transition
                     : 'C:' <state> '-' <unary_predicate> ',' <stack_state> '->' <state> // call transition
                     : 'R:' <state> '-' <predicate> ',' <stack_state> '->' <state> // return transition

<state>              : int // the state label

<unary_predicate>    : '[' <unicode_range_list> ']'

<unicode_range_list> : <unicode_range> <unicode_range> ... // a list of unicode character intervals

<unicode_range>      : unicode // a single unicode character
                     : unicode '-' unicode // a range of unicode characters

<stack_state>        : int // the stack state label

<predicate>          : <unary_predicate>
                     : <binary_predicate>

<binary_predicate>   : 'neq' <unary_predicate> ', eq' <unary_predicate> // unicode character ranges that must match (eq) or not match (neq) the corresponding call transition
```


An example could look like this:
```
Automaton: 7 transitions, 3 states
Transitions 
I: 0 -[,]-> 2
I: 0 -[\u0000-\uffff]-> 0
C: 0 -[\u0000-\uffff], 0-> 1
I: 1 -[\u0000-\uffff]-> 1
C: 1 -[\u0000-\uffff], 1-> 1
R: 1 -neq [\u0000-\uffff], eq [\u0000-\uffff], 0-> 0
R: 1 -neq [\u0000-\uffff], eq [\u0000-\uffff], 1-> 1
Initial States 
0
Final States 
2
```
