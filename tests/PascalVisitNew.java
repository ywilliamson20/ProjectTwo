import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;

public class PascalVisitNew<T> extends PascalBaseVisitor<T>{

    @Override public T visitWhileDoLoop(PascalParser.WhileDoLoopContext ctx) { return visitChildren(ctx); }
   


}