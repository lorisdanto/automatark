Welcome to the Github page of AutomatArk!

Here we provide serveral open-sourced parsers writen in Java for our benchmarks. 
Parsers are generated using JFlex 1.6.1 as lexer/scanner generator, and Java CUP 11b as parser generator.

Right now we have:

- Linear Temporal Logic parser (supports most logical operators of LTL)
	check src/LTLparser for details.

- Regular Expression parser (supports basic operators, character classes and commonly used meta characters)
	check src/RegexParser for details 

- Tree Automata parser
	check src/TreeAutomataParser for details

Detailed grammar files for each parser can be found in the corresponding folder (file which ends with .grammar).
If you have found any bugs or you are willing to add more features to our basic parsers, feel free to issue a pull requests or by e-mail loris@cs.wisc.edu or fwang64@wisc.edu