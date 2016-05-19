# Nondeterministic finite automata

##Input Format

Our description format for NFA is the same as for tree automata. 
All symbols but one will have arity 1, and one symbol will have arity 
0 (the beginning-of-string).
We use the Timbuk format for tree automata and this description is available at [libvata](http://www.fit.vutbr.cz/research/groups/verifit/tools/libvata/).
The format is specified by the following grammar with the start symbol <file>.

```
<file>            : 'Ops' <label_list> <automaton>

<label_list>      : <label_decl> <label_decl> ... // a list of label declarations

<label_decl>      : string ':' int // a symbol declaration (the name and the arity)

<automaton>       : 'Automaton' string 'States' <state_list> 'Final States' <state_list> 'Transitions' <transition_list>

<state_list>      : <state> <state> ... // a list of states

<state>           : string // the name of a state

<transition_list> : <transition> <transition> ... // a list of transitions

<transition>      : <label> '(' <state> ',' <state> ',' ... ')' '->' <state> // a transition

<label>           : string // the name of a label
```
An example could look like this:
```
Ops a:0 b:1 c:2

Automaton A
States q0 q1
Final States q1 
Transitions
a() -> q0
b(q0) -> q1
c(q1) -> q1
```

Here q0 is the initial state
