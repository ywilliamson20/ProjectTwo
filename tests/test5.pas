(*Output:
Map of bool variables: {}
Map of real variables: {a=null, b=null, c=null}

Map of bool variables: {}
Map of real variables: {a=null, b=null, c=null, d=100.0}

Map of bool variables: {}
Map of real variables: {a=-2.0, b=null, c=null, d=100.0}

Map of bool variables: {}
Map of real variables: {a=-2.0, b=-1.0, c=null, d=100.0}

Map of bool variables: {}
Map of real variables: {a=-2.0, b=-1.0, c=-3.0, d=100.0}*)
program Test5;
var
    a, b, c : real;
    d : real = 100;
begin
    a := (-6.5 / 3.25);
    b := (2.0 + -5) + (3 - 1.0);
    c := a + b;
end.
