(*Output:
Map of bool variables: {}
Map of real variables: {a=null, b=null, c=null}

Map of bool variables: {}
Map of real variables: {a=null, b=null, c=null, d=10.0}

Map of bool variables: {e=false}
Map of real variables: {a=null, b=null, c=null, d=10.0}

Map of bool variables: {e=false, f=true}
Map of real variables: {a=null, b=null, c=null, d=10.0}

Map of bool variables: {e=false, f=true}
Map of real variables: {a=-2.0, b=null, c=null, d=10.0}

-2.0
Map of bool variables: {e=false, f=true}
Map of real variables: {a=-2.0, b=-1.0, c=null, d=10.0}

-1.0
Map of bool variables: {e=false, f=true}
Map of real variables: {a=-2.0, b=-1.0, c=-3.0, d=10.0}

-3.0
Map of bool variables: {e=false, f=true}
Map of real variables: {a=-2.0, b=-1.0, c=-3.0, d=3.1622776601683795}

3.1622776601683795
a: -2.0 | b: -1.0 | c: -3.0 | d: 3.1622776601683795
Map of bool variables: {e=false, f=true}
Map of real variables: {a=-2.0, b=-1.0, c=-3.0, d=-0.9880316240928618}

Map of bool variables: {e=false, f=true}
Map of real variables: {a=-2.0, b=-1.0, c=-3.0, d=0.5253219888177297}

Map of bool variables: {e=false, f=true}
Map of real variables: {a=-2.0, b=-1.0, c=-3.0, d=0.0}

Map of bool variables: {e=false, f=true}
Map of real variables: {a=-2.0, b=-1.0, c=-3.0, d=1.0}

d: 1.0
false
true*)
program Test7;

var
    a, b, c : real;
    d : real = 10;
    e : boolean = false;
    f : boolean = true;

begin
    a := (-6.5 / 3.25);
    writeln(a);
    b := (2.0 + -5) + (3 - 1.0);
    writeln(b);
    c := a + b;
    writeln(c);
    d := sqrt(d);
    writeln(d);

    writeln('a: ' , a , ' | b: ' , b , ' | c: ', c , ' | d: ' , d);

	d := sin(30);
	d := cos(45);
	d := ln(1);
	d := ln(exp(1));
    writeln('d: ', d);

    writeln(e);
    writeln(f);
end.
