# ProjectTwo
# Pascal With Antlr

Hello! Here are some notes regarding this project.

## GROUP
Nicole Ajoy
Yvette Williamson
## HOW TO RUN
antlr Pascal.g4 -visitor
javac *.java
java Main tests/test20.pas
(NOTE: test number ranges from 1-22)

## COMMENTS
In order to enter input, please enter ONLY real values. If you want to enter more than one value, please enter the digits and press [ENTER] after every number. Do NOT include spaces or commas.

When working with if-statements and using real numbers as the condition, this may cause unexpected results, as real values are not exact/precise. Attempted to fix this in the Value class.

When writing user-defined functions/procedures, they MUST be written before the main block. Also, global variable declararions MUST be written right before the main block.

Need to have at least one variable in a function call (will NOT actually pass to function).

Each "for" and "while" blocks need variable declarations and continue was NOT implemented

## EXTRA STUFF
Added a DOWNTO for loop (see Test19) that counts down by one instead of counting up.

### RESOURCES USED
Mu Repository
TinyLanguage Repository
Pascal Tutorial

#FIN
Thank you.

- Nicole, Yvette
