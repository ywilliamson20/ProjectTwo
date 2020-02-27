//package pascal;

import java.lang.Exception;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;



public class Main {

    public static void main(String[] args) throws Exception {

        if (args.length == 0) {
            //args = new String[]{"src/main/mu/test.mu"};
        }

        System.out.println("parsing: " + args[0]);

        PascalLexer lexer = new PascalLexer(new ANTLRFileStream(args[0]));
        PascalParser parser = new PascalParser(new CommonTokenStream(lexer));
        ParseTree tree = parser.parse();
        PascalVisitor visitor = new PascalVisitor();
        visitor.visit(tree);
    }
}