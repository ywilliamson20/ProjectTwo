// Project 2
// By Nicole Ajoy & Yvette Williamson

//----------------------------------------------------//

grammar Pascal;

program
   : programHeader blocks
   ;

programHeader
   : PROGRAM ID ';'
   ;

blocks
   : (functionBlock | procedureBlock)* mainBlock
   ;

functionBlock
   : FUNCTION ID '(' parameters ')' ':' type=(BOOLEAN | REAL) ';' (varDecBlock)* BEGIN statements END ';'
   ;

procedureBlock
   : PROCEDURE ID '(' parameters ')' ';' (varDecBlock)* BEGIN statements END ';'
   ;

parameters
   : varDec (';' varDec)*
   ;

mainBlock
   : (varDecBlock)* BEGIN statements END '.'
   ;

varDecBlock
   : VAR (varDec ';')+
   ;

varDec 
   : ID ':' type=(BOOLEAN | REAL) '=' expression   #varSingleDec
   | ID (',' ID)* ':' type=(BOOLEAN | REAL)        #varListDec
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
   | subprogramCall
   ;

assignStatement
   : ID ':=' expression
   ;

expression
   : '(' expression ')'                                  #parenthesisExpression
   | subprogramCall                                      #subprogramCallExpression
   // special functions
   | SQRT expression                                     #sqrtExpression
   | SIN expression                                      #sinExpression
   | COS expression                                      #cosExpression
   | LN expression                                       #logExpression
   | EXP expression                                      #expExpression
   // arithmetic expressions
   | expression op=(PRODUCT | DIVIDE | MOD) expression   #multiplicativeExpression
   | expression op=(PLUS | MINUS) expression             #additiveExpression
   | MINUS expression                                    #negExpression
   // conditional operators
   | NOT expression                                      #notExpression
   | expression AND expression                           #andExpression
   | expression OR expression                            #orExpression
   | expression op=(EQ | NEQ) expression                 #equalityExpression
   | expression op=(GT | LT | GTE | LTE) expression      #relationalExpression
   // tiny expressions
   | atom                                                #atomExpression
   ;

subprogramCall
   : ID '(' arguments ')'
   ;

arguments
   : ID (',' ID)*
   ;

atom
   : REAL_NUMBER        #numberAtom
   | (TRUE | FALSE)     #booleanAtom
   | ID                 #idAtom
   | STRING             #stringAtom
   ;

ifStatement
   : IF expression THEN (CONTINUE)? statement (BREAK)?  (ELSE statement (BREAK)?(CONTINUE)?)?
   ;

caseStatement
   : CASE expression OF (expression ':' statements)+ END
   ;

whileDoLoop
   : WHILE expression DO BEGIN statements END
   ;

forDoLoop
   : FOR ID ':=' expression count=(TO | DOWNTO) expression DO BEGIN statements END
   ;

writeStatement
   : WRITELN '()'                                        #writeNewline
   | WRITELN '(' expression (',' expression)* ')'        #writeInside
   ;

readStatement
   : READLN ('()')?                 #readPause
   | READLN '(' ID (',' ID )* ')'   #readInput
   ;

// arithmetic operators
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

// boolean operators
TRUE           : 'true';
FALSE          : 'false';
AND            : 'and';	
NOT            : 'not';
OR             : 'or';

// logical operators
EQ             : '=';
NEQ            : '<>';
GT             : '>';
LT             : '<';
GTE            : '>=';
LTE            : '<=';

// comments
COMMENT_B      : '{' (.*?) '}' -> skip;
COMMENT_P      : '(*' (.*?) '*)' -> skip;

// keywords
BEGIN          : 'begin';
BOOLEAN        : 'boolean';
BREAK          : 'break';
CASE           : 'case';
CONST          : 'const';
CONTINUE       : 'continue';
DO             : 'do';
DOWNTO         : 'downto';
FUNCTION       : 'function';
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
PROCEDURE      : 'procedure';
VAR            : 'var';
WHILE          : 'while';
WRITELN        : 'writeln';

// atoms
ID             : [A-Za-z][_A-Za-z0-9]*;
REAL_NUMBER    : [0-9]+('.'[0-9]+)?;
STRING         : '\'' (.*?) '\'';
WS             : [ \t\r\n]+ -> skip;