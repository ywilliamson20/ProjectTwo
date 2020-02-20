(*Output:
Map of bool variables: {}
Map of real variables: {a=null, b=null, c=null}

Map of bool variables: {}
Map of real variables: {a=null, b=null, c=null, d=10.0}

Map of bool variables: {flag=null}
Map of real variables: {a=null, b=null, c=null, d=10.0}

Map of bool variables: {flag=null}
Map of real variables: {a=-2.0, b=null, c=null, d=10.0}

Map of bool variables: {flag=null}
Map of real variables: {a=-2.0, b=-1.0, c=null, d=10.0}

Map of bool variables: {flag=null}
Map of real variables: {a=-2.0, b=-1.0, c=-3.0, d=10.0}

Map of bool variables: {flag=null}
Map of real variables: {a=-2.0, b=-1.0, c=-3.0, d=3.1622776601683795}

Map of bool variables: {flag=null}
Map of real variables: {a=-2.0, b=-1.0, c=-3.0, d=-0.9880316240928618}

Map of bool variables: {flag=null}
Map of real variables: {a=-2.0, b=-1.0, c=-3.0, d=0.5253219888177297}

Map of bool variables: {flag=null}
Map of real variables: {a=-2.0, b=-1.0, c=-3.0, d=0.0}

Map of bool variables: {flag=null}
Map of real variables: {a=-2.0, b=-1.0, c=-3.0, d=1.0}

Map of bool variables: {flag=true}
Map of real variables: {a=-2.0, b=-1.0, c=-3.0, d=1.0}*)
program Test6;

var
    a, b, c : real;
    d : real = 10;
    flag : boolean;

begin
    a := (-6.5 / 3.25);
    b := (2.0 + -5) + (3 - 1.0);
    c := a + b;
    d := sqrt(d);
	d := sin(30);
	d := cos(45);
	d := ln(1);
	d := ln(exp(1));
    flag := true;
end.
