/***** PROJECT 2 *****/
// Student Group: Nicole Ajoy & Yvette Williamson

/***** HOW TO RUN *****/
/* 
antlr Pascal.g4
javac Pascal*.java 
grun Pascal program tests/test#.pas -gui
(# between 1-20)
*/

/*********************************************************/

grammar Pascal;

program
   : programHeader ((partBlock)* mainBlock)+    
   ;

programHeader
   : PROGRAM ID ';'
   ;

partBlock
   : varDecBlock
   ;

varDecBlock
   : VAR (varDec ';')+
   ;

varDec 
   : varSingleDec
   | varListDec
   ;

bool
   : (TRUE | FALSE)
   ;

varSingleDec
   : ID ':' BOOLEAN '=' bool
   | ID ':' REAL '=' REAL_NUMBER
   ;

varListDec
   : ID (',' ID)* ':' BOOLEAN
   | ID (',' ID)* ':' REAL 
   ;

mainBlock
   : BEGIN statements END '.'
   ;

statements
   : (statement ';')*
   ;

statement
   : // emptyStatement
   | assignmentStatement   
   | ifStatement          
   | caseStatement        
   | whileDoLoop          
   | forDoLoop             
   | writeStatement        
   | readStatement         
   ;

assignmentStatement
   : ID ':=' expression   
   | ID ':=' condition    
   ;

expression
   : '(' e=expression ')' 
   | SQRT e=expression    
   | SIN e=expression      
   | COS e=expression      
   | LN e=expression       
   | EXP e=expression      
   | eL=expression PRODUCT eR=expression     
   | eL=expression DIVIDE eR=expression      
   | eL=expression PLUS eR=expression        
   | eL=expression MINUS eR=expression       
   | eL=expression MOD eR=expression
   | MINUS REAL_NUMBER
   | REAL_NUMBER
   | ID
   ;

condition
   : '(' e=condition ')'
   | eL=condition AND eR=condition
   | eL=condition OR eR=condition
   | NOT e=condition
   | eL=condition XOR eR=condition 
   | cL=expression EQ cR=expression
   | cL=expression NEQ cR=expression
   | cL=expression GT cR=expression
   | cL=expression LT cR=expression
   | cL=expression GE cR=expression 
   | cL=expression LE cR=expression
   | NOT c=condition
   | bool
   | ID
   ;

ifStatement
   : IF condition THEN statement (ELSE statement)?
   ;

caseStatement
   : CASE expression OF (REAL_NUMBER ':' statements)+ END
   | CASE condition OF (bool ':' statements)+ END
   | CASE ID OF (ID ':' statements)+ END
   ;

whileDoLoop
   : WHILE condition DO BEGIN statements END       
   ;

forDoLoop
   : FOR ID ':=' REAL_NUMBER TO REAL_NUMBER DO BEGIN statements END  
   ;

writeStatement
   : WRITELN '()'
   | WRITELN '(' writeParameter (',' writeParameter)* ')'
   ;

writeParameter
   : expression
   | condition
   | STRING_LIT
   | ID
   ;

readStatement
   : READLN ('()')?
   | READLN '(' ID (',' ID )* ')'
   ;

// Arithmetic operators
PLUS           : '+';
MINUS          : '-';
PRODUCT        : '*';
DIVIDE         : '/';
MOD            : 'mod';
SQRT           : 'sqrt';
SIN            : 'sin';
COS            : 'cos';
LN             : 'ln';
EXP            : 'exp';

// Boolean operators
TRUE           : 'true';
FALSE          : 'false';
AND            : 'and';	
NOT            : 'not';
OR             : 'or';
XOR            : 'xor';

// Logical operators
EQ             : '=';
NEQ            : '<>';
GT             : '>';
LT             : '<';
GE             : '>=';
LE             : '<=';

// Comments
COMMENT_B      : '{' (.*?) '}' -> skip;
COMMENT_P      : '(*' (.*?) '*)' -> skip;

// Other keywords
BEGIN          : 'begin';
BOOLEAN        : 'boolean';
CASE           : 'case';
CONST          : 'const';
DO             : 'do';
OF             : 'of';
ELSE           : 'else';
END            : 'end';
FOR            : 'for';
IF             : 'if';
PROGRAM        : 'program';
READLN         : 'readln';
REAL           : 'real';
TO             : 'to';
THEN           : 'then';
VAR            : 'var';
WHILE          : 'while';
WRITELN        : 'writeln';

// Essentials
ID             : [A-Za-z][_A-Za-z0-9]*;
REAL_NUMBER    : [0-9]+('.'[0-9]+)?;
STRING_LIT     : '\'' (.*?) '\'';
WS             : [ \t\r\n]+ -> skip;
