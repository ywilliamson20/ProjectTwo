
/***** PROJECT 2 *****/
// Student Group: Nicole Ajoy & Yvette Williamson

/***** HOW TO RUN *****/
/***** PROJECT 2 *****/
// Student Group: Nicole Ajoy & Yvette Williamson

/***** HOW TO RUN *****/
/* 
cd C:\Javalib\pascal
antlr4 Pascal.g4
javac *.java
java Main tests/test16.pas
(# between 1-19)
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
   : functionStart 
   | varDecBlock 
   ;

functionStart
   :'function' ID LEFTP ARGUMENTS (',' ARGUMENTS)* RIGHTP ':' ID
   ;
  
varDecBlock
   : VAR (varDec ';')+
   ;

varDec 
   : ID ':' type=(BOOLEAN | REAL) '=' expression   #varSingleDec
   | ID (',' ID)* ':' type=(BOOLEAN | REAL)        #varListDec
   ;

mainBlock
   : BEGIN statements END '.'
   ;

statements
   : (statement ';')*
   ;

statement
   : // emptyStatement
   | assignStatement
   | ifStatement
   | caseStatement
   | whileDoLoop
   | forDoLoop
   | writeStatement
   | readStatement
   ;

assignStatement
   : ID ':=' expression
   ;

expression
   : '(' expression ')'                                  #parenthesisExpression
   // Special functions
   | SQRT expression                                     #sqrtExpression
   | SIN expression                                      #sinExpression
   | COS expression                                      #cosExpression
   | LN expression                                       #logExpression
   | POWER expression                                    #expExpression
   // Arithmetic expressions
   | expression op=(PRODUCT | DIVIDE | MOD) expression   #multiplicativeExpression
   | expression op=(PLUS | MINUS) expression             #additiveExpression
   | MINUS expression                                    #negExpression
   // Conditional operators
   | expression AND expression                           #andExpression
   | expression OR expression                            #orExpression
   | NOT expression                                      #notExpression
   | expression op=(EQ | NEQ) expression                 #equalityExpression
   | expression op=(GT | LT | GE | LE) expression        #relationalExpression
   | NOT expression                                      #notExpression
   // Tiny expressions
   | atom                                                #atomExpression
   ;

atom
   : REAL_NUMBER        #numberAtom
   | (TRUE | FALSE)     #booleanAtom
   | ID                 #idAtom
   | STRING             #stringAtom
   ;

ifStatement
   : IF expression THEN statement (ELSE statement)?
   ;

caseStatement
   : CASE expression OF (expression ':' statements)+ END
   ;

whileDoLoop
   : WHILE expression DO BEGIN statements END
   ;

forDoLoop
   : FOR ID ':=' expression TO expression DO BEGIN statements END
   ;

writeStatement
   : WRITELN '()'                                        #writeNewline
   | WRITELN '(' expression (',' expression)* ')'        #writeInside
   ;

readStatement
   : READLN ('()')?                 #readPause
   | READLN '(' ID (',' ID )* ')'   #readInput
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
POWER          : 'exp';

// Boolean operators
TRUE           : 'true';
FALSE          : 'false';
AND            : 'and';	
NOT            : 'not';
OR             : 'or';

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

//Functions Elements
LEFTP          :'(';
RIGHTP         :')';
FUNCTIONNAME : ID ;
ARGUMENTS :    ID (',' ID)* ;
//FUNCTION:      FUNCTIONNAME LEFTP ARGUMENTS (',' ARGUMENTS)* RIGHTP ;


// Essentials
ID             : [A-Za-z][_A-Za-z0-9]*;
REAL_NUMBER    : [0-9]+('.'[0-9]+)?;
STRING         : '\'' (.*?) '\'';
WS             : [ \t\r\n]+ -> skip;