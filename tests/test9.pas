(*Output:
Map of bool variables: {}
Map of real variables: {a=null, b=null}
Enter two floats:

Input: 1.0 2.0

A: 1.0
B: 2.0
Addition (A+B): 3.0
Subtraction (A-B): -1.0
Multiplying (A*B): 2.0
Dividing (A/B): 0.5*)
program Test9;
var
a, b: real;

begin

   writeln('Enter two floats:');
   readln(a, b);

   writeln('A: ', a);
   writeln('B: ', b);
   writeln('Addition (A+B): ', (a+b));
   writeln('Subtraction (A-B): ', (a-b));
   writeln('Multiplying (A*B): ', (a*b));
   writeln('Dividing (A/B): ', (a/b));
end.
