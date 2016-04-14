# AutomatArk (Automata Benchmark)
Benchmark problems for different models of automata, transducers, and related logics

##Input Format

This description is taken from the [libvata](http://www.fit.vutbr.cz/research/groups/verifit/tools/libvata/)
webpage.
All automata are expressed using the Timbuk format for tree automata. 
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
States q0 q1 q2
Final States q2 
Transitions
a() -> q0
b(q0) -> q1
c(q1, q1) -> q1
c(q1, q1) -> q2
c(q2, q2) -> q2
```

By default automata are meant to be bottom-up.
For string automata all symbols but one will have arity 1, and one symbol will have arity 
0 (the beginning-of-string).

## Sources
AutomataArk contains benchmark from the following sources.

* Alaska TACAS08 experiments, [http://lit2.ulb.ac.be/alaska/experiments.html]
* Lukas Holik's page, [http://www.fit.vutbr.cz/~holik/]
* Limi CAV15 experiments [https://github.com/thorstent/Limi]
* LibVata library [http://www.fit.vutbr.cz/research/groups/verifit/tools/libvata/]