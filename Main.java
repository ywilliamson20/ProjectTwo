//package pascal;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class Main {
    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            // No file provided to run
        }
        else {
            System.out.println("Parsing: " + args[0]);
            PascalLexer lexer = new PascalLexer(new ANTLRFileStream(args[0]));
            PascalParser parser = new PascalParser(new CommonTokenStream(lexer));
            // Pascal.g4 starts with "program" rule, so indicate parser to start there
            ParseTree tree = parser.program();
            EvalVisitor visitor = new EvalVisitor();
            visitor.visit(tree);
        }
    }
}