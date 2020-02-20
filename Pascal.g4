/***** PROJECT 1 *****/
// Student Group: Nicole Ajoy & Yvette Williamson

/***** HOW TO RUN *****/
/* 
antlr Pascal.g4
javac Pascal*.java 
grun Pascal program tests/test#.pas -gui
(# between 1-15)
*/

/*********************************************************/

grammar Pascal;

@header
   {
      import java.lang.Math;
      import java.util.ArrayList;
      import java.util.HashMap;
      import java.util.Scanner;
   }

@members
   {
      HashMap<String, Boolean> mapBool = new HashMap();
      HashMap<String, Double> mapReal = new HashMap();
   }

/*********************************************************/

program
   : programHeader ((partBlock)* mainBlock)+
   ;

programHeader
   : PROGRAM ID ';'
   ;

partBlock
   : varDecBlock
   ;

type
   : bool
   | REAL_NUMBER
   ;

varDecBlock
   : VAR (varDec ';')+
   ;

varDec 
   : varSingleDec
   { 
      System.out.println("Map of bool variables: " + mapBool);
      System.out.println("Map of real variables: " + mapReal);
      System.out.println();
   }
   | varListDec
   { 
      System.out.println("Map of bool variables: " + mapBool);
      System.out.println("Map of real variables: " + mapReal);
      System.out.println();
   }
   ;

bool
   : TRUE 
   | FALSE
   ;

varSingleDec
   : ID ':' BOOLEAN '=' bool { mapBool.put($ID.text, new Boolean($bool.text)); }
   | ID ':' REAL '=' REAL_NUMBER { mapReal.put($ID.text, new Double($REAL_NUMBER.text)); }
   ;

varListDec returns [List<String> varList]
@init { $varList = new ArrayList<String>(); }
   : ID {$varList.add($ID.text);} (',' ID {$varList.add($ID.text);})* ':' BOOLEAN
   { 
      // System.out.println("Bool(s) declared: " + $varList);
      for (int i = 0; i < $varList.size(); i++)
         mapBool.put($varList.get(i), null);
   }
   | ID {$varList.add($ID.text);} (',' ID {$varList.add($ID.text);})* ':' REAL 
   {
      // System.out.println("Real(s) declared: " + $varList);
      for (int i = 0; i < $varList.size(); i++) 
         mapReal.put($varList.get(i), null);
   }
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
   {
      System.out.println("Map of bool variables: " + mapBool);
      System.out.println("Map of real variables: " + mapReal);
      System.out.println();
   }
   | ifStatement 
   | caseStatement
   | writeStatement
   | readStatement
   ;

assignmentStatement
   : ID ':=' expression 
   { 
      mapReal.put($ID.text, $expression.f);
      // System.out.println($ID.text + " = " + $expression.text + " = " + $expression.f);
   }
   | ID ':=' condition 
   { 
      mapBool.put($ID.text, $condition.b);
      // System.out.println();
   }
   ;

expression returns [double f]
   : '(' e=expression ')'                    { $f = $e.f; }
   | SQRT e=expression                       { $f = (Double)Math.sqrt($e.f); }
   | SIN e=expression                        { $f = (Double)Math.sin($e.f); }
   | COS e=expression                        { $f = (Double)Math.cos($e.f); }
   | LN e=expression                         { $f = (Double)Math.log($e.f); } 
   | EXP e=expression                        { $f = (Double)Math.exp($e.f); }
   | eL=expression PRODUCT eR=expression     { $f = $eL.f * $eR.f; }
   | eL=expression DIVIDE eR=expression      { $f = $eL.f / $eR.f; }
   | eL=expression PLUS eR=expression        { $f = $eL.f + $eR.f; }
   | eL=expression MINUS eR=expression       { $f = $eL.f - $eR.f; }
   | eL=expression MOD eR=expression         { $f = $eL.f % $eR.f; }
   | MINUS REAL_NUMBER                       { $f = -Double.parseDouble($REAL_NUMBER.text); }
   | REAL_NUMBER                             { $f = Double.parseDouble($REAL_NUMBER.text); }
   | ID 
   { 
      if (mapReal.containsKey($ID.text) && mapReal.get($ID.text)!=null) 
         $f = mapReal.get($ID.text);
      else if (mapReal.containsKey($ID.text) && mapReal.get($ID.text)==null)
         System.out.println($ID.text + " not initialized");
      else if (!mapReal.containsKey($ID.text))
         System.out.println("Variable " + $ID.text + " not declared");
   }
   ;

condition returns [boolean b]
   : '(' e=condition ')'               { $b = $e.b; }
   | eL=condition AND eR=condition     { $b = $eL.b && $eR.b; }
   | eL=condition OR eR=condition      { $b = $eL.b || $eR.b; }
   | NOT e=condition                   { $b = !$e.b; }
   | eL=condition XOR eR=condition 
   { 
      if ($eL.text != $eR.text) $b = true;
      else $b = false;
   }
   | cL=expression EQ cR=expression    { $b = $cL.f == $cR.f; }
   | cL=expression NEQ cR=expression   { $b = $cL.f != $cR.f; }
   | cL=expression GT cR=expression    { $b = $cL.f > $cR.f; }
   | cL=expression LT cR=expression    { $b = $cL.f < $cR.f; }
   | cL=expression GE cR=expression    { $b = $cL.f >= $cR.f; }
   | cL=expression LE cR=expression    { $b = $cL.f <= $cR.f; }
   | NOT c=condition                   { $b = !$c.b; }
   | TRUE                              { $b = true; }
   | FALSE                             { $b = false; }
   | ID
   { 
      if (mapBool.containsKey($ID.text) && mapBool.get($ID.text)!=null)
         $b = mapBool.get($ID.text);
      else if (mapBool.containsKey($ID.text) && mapBool.get($ID.text)==null)
         System.out.println($ID.text + " not initialized");
      else if (!mapBool.containsKey($ID.text))
         System.out.println("Variable " + $ID.text + " not declared");
   }
   ;

ifStatement
   : IF condition THEN {$condition.b}? statement
   | IF condition THEN {$condition.b}? statement ELSE {!$condition.b}? statement
   ;

caseStatement
   : CASE expression OF (REAL ':' statement)+ END ';'
   | CASE expression OF (BOOLEAN ':' statement)+ END ';'
   ;

writeStatement
   : WRITELN '()' { System.out.println(); } // emptyParameter
   | WRITELN '(' writeParameter (',' writeParameter)* ')' { System.out.print("\n"); }
   ;

writeParameter
   : STRING_LIT 
   { 
      // Remove single apostrophes from string
      System.out.print($STRING_LIT.text.substring(1, $STRING_LIT.text.length()-1));
   }
   | ID
   { 
      if (mapBool.containsKey($ID.text))
         System.out.print(mapBool.get($ID.text));
      else if (mapReal.containsKey($ID.text))
         System.out.print(mapReal.get($ID.text));
      else
         System.out.println("ERR");
   }
   | expression { System.out.print($expression.f); }
   | condition { System.out.print($condition.b); }
   ;

readStatement returns [List<String> readList]
@init 
{ 
   $readList = new ArrayList<String>();
   int counter = 1;
}
   : READLN ('()')?
   {
      Scanner sc = new Scanner(System.in);
      String userInput = sc.nextLine(); // Wait for input then assign
      // System.out.println("Input: " + userInput); // Print result
   }
   | READLN '(' ID {$readList.add($ID.text);} (',' {counter++;} ID {$readList.add($ID.text);})* ')'
   {
      int i = 0;
      Scanner sc = new Scanner(System.in);
      while (i < counter)
      {
         String userInput = sc.next(); // Wait for input then assign
         $readList.add(userInput);
         // System.out.println("Input: " + userInput); // Print result
         if (mapReal.containsKey($readList.get(i)))
            mapReal.put($readList.get(i), Double.parseDouble(userInput));
         else
            System.out.println("ERR" + "\n");
         i++;
      }
   }
   ;

/*********************************************************/

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
OF             : 'of';
ELSE           : 'else';
END            : 'end';
IF             : 'if';
PROGRAM        : 'program';
READLN         : 'readln';
REAL           : 'real';
THEN           : 'then';
VAR            : 'var';
WRITELN        : 'writeln';

// Essentials
ID             : [A-Za-z][_A-Za-z0-9]*;
REAL_NUMBER    : [0-9]+('.'[0-9]+)?;
STRING_LIT     : '\'' (.*?) '\'';
WS             : [ \t\r\n]+ -> skip;