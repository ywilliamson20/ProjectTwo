(*Output:
Map of bool variables: {a=null, b=null, c=null}
Map of real variables: {}

Map of bool variables: {a=true, b=null, c=null}
Map of real variables: {}

Map of bool variables: {a=true, b=false, c=null}
Map of real variables: {}

Map of bool variables: {a=true, b=false, c=false}
Map of real variables: {}

a and b = false
Map of bool variables: {a=true, b=false, c=true}
Map of real variables: {}

a or b = true
Map of bool variables: {a=false, b=false, c=true}
Map of real variables: {}

Map of bool variables: {a=false, b=true, c=true}
Map of real variables: {}

Map of bool variables: {a=false, b=true, c=true}
Map of real variables: {}

(a or b) and b = true*)
program Test8;

var
    a, b, c: boolean;

begin
    a := true;
    b := false;

    c := a and b;
    writeln('a and b = ', c);

    c := a or b;
    writeln('a or b = ' , c);

    a := false;
    b := true;

    c := (a or b) and b;
    writeln('(a or b) and b = ' , c);
end.
