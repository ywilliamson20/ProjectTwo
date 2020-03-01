import org.antlr.v4.runtime.misc.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EvalVisitor extends PascalBaseVisitor<Value> {
    private Map<String,Value> memory = new HashMap<String,Value>();

	
/*********************************************************/
// variable declarations

@Override 
public Value visitVarSingleDec(PascalParser.VarSingleDecContext ctx) { 
	String id = ctx.ID().getText();
	Value v = this.visit(ctx.expression());
	System.out.println(this.visit(ctx.expression()));

	switch (ctx.type.getType()) {
		case PascalParser.BOOLEAN:
			// System.out.println("Type: boolean");
			return memory.put(id, v); 
		case PascalParser.REAL:
			// System.out.println("Type: real");
			return memory.put(id, v); 
		default:
			throw new RuntimeException("Unknown type: " + ctx.type.getType());
	}
}

@Override 
public Value visitVarListDec(PascalParser.VarListDecContext ctx) {
	System.out.println("var list");
	return Value.VOID;
}

/*********************************************************/
// expression

// special functions
	@Override
    public Value visitLogExpression(PascalParser.LogExpressionContext ctx) { 

	
		System.out.println("log");
		return  this.visit(ctx.expression());
	}
	
	@Override
    public Value visitSqrtExpression(PascalParser.SqrtExpressionContext ctx) {

		// String str = ctx.getText();
        // // strip quotes
        // str = str.substring(1, str.length() - 1).replace("\"\"", "\"");
		// return new Value(str);
		System.out.println("sqrt");
		return  this.visit(ctx.expression());
	}

	@Override
    public Value visitSinExpression(PascalParser.SinExpressionContext ctx) { 

		// String str = ctx.getText();
        // // strip quotes
        // str = str.substring(1, str.length() - 1).replace("\"\"", "\"");
		// return new Value(str);
		System.out.println("sin");
		return  this.visit(ctx.expression());
	}

	@Override
   	public Value visitMultiplicativeExpression(PascalParser.MultiplicativeExpressionContext ctx) {

		// String str = ctx.getText();
        // // strip quotes
        // str = str.substring(1, str.length() - 1).replace("\"\"", "\"");
		// return new Value(str);
		System.out.println("multiplication");
		return  this.visit(ctx.expression(0));
	}

	@Override public Value visitAdditiveExpression(PascalParser.AdditiveExpressionContext ctx) { 
			// String str = ctx.getText();
        // // strip quotes
        // str = str.substring(1, str.length() - 1).replace("\"\"", "\"");
		// return new Value(str);
		System.out.println("add");
		return  this.visit(ctx.expression(0));
	}

	@Override public Value visitCosExpression(PascalParser.CosExpressionContext ctx) {

			// String str = ctx.getText();
        // // strip quotes
        // str = str.substring(1, str.length() - 1).replace("\"\"", "\"");
		// return new Value(str);
		System.out.println("cos");
		return  this.visit(ctx.expression());
	}

	@Override public Value visitExpExpression(PascalParser.ExpExpressionContext ctx) { 
		System.out.println("exponent");
		return  this.visit(ctx.expression());
	}

	@Override public Value visitNegExpression(PascalParser.NegExpressionContext ctx) { 
		System.out.println("minus");
		return  this.visit(ctx.expression());
	}
	@Override public Value visitOrExpression(PascalParser.OrExpressionContext ctx) {
		System.out.println("or");
		return  this.visit(ctx.expression(0));
	}

	@Override public Value visitAndExpression(PascalParser.AndExpressionContext ctx) { 
		System.out.println("and");
		return  this.visit(ctx.expression(0));
	}

	@Override public Value visitRelationalExpression(PascalParser.RelationalExpressionContext ctx) { 
		Value left = this.visit(ctx.expression(0));
        Value right = this.visit(ctx.expression(1));
		//System.out.println("relational");
        switch (ctx.op.getType()) {

			case PascalParser.GT:
				if(left != null&& right != null) {
					System.out.println(left.asDouble());
					return new Value(left.asDouble() > right.asDouble());
					
				}
			case PascalParser.LT:
				if(left != null&& right != null)  {
					System.out.println(left.asDouble());
					return new Value(left.asDouble() < right.asDouble());
					
				}
			case PascalParser.GE:
				if(left != null&& right != null)  {
					System.out.println(left.asDouble());
					return new Value(left.asDouble() >= right.asDouble());
					
				}
			case PascalParser.LE:
				if(left != null&& right != null)  {
					System.out.println(left.asDouble());
					return new Value(left.asDouble() <= right.asDouble());
					
				}
            default:
			System.out.println("relational");
        }
		
		return  this.visit(ctx.expression(0));
	}

	@Override public Value visitPrintInside(PascalParser.PrintInsideContext ctx) { 
		//Value value = this.visit(ctx.expression(0));
		System.out.println("writeln");
		//System.out.println(value.toString());
		return  this.visit(ctx.expression(0));
	 }

	// @Override public Value visitPrintNewline(PascalParser.PrintNewlineContext ctx) { 


	 //}
	// @Override public Value visitCaseStatement(PascalParser.CaseStatementContext ctx) {
	// 	Value value = this.visit(ctx.expression());
	// 	System.out.println("while");
	// 	System.out.println(value.asBoolean());
	// 	if (value!=null){
	// 		while(value.asBoolean()) {

	// 			// evaluate the code block
	// 			value = this.visit(ctx.expression());
	
	// 			// evaluate the expression
	// 			this.visit(ctx.statements());
				
	// 		}
	
	// 	}


	//  }



	@Override public Value visitEqualityExpression(PascalParser.EqualityExpressionContext ctx) { 
		System.out.println("equality");
		return  this.visit(ctx.expression(0));
	}

	@Override public Value visitWhileDoLoop(PascalParser.WhileDoLoopContext ctx) { 

		Value value = this.visit(ctx.expression());
		//System.out.println("while");
		//System.out.println(value.asBoolean());
		if (value!=null){
			while(value.asBoolean()) {

				// evaluate the code block
				this.visit(ctx.statements());
	
				// evaluate the expression
				value = this.visit(ctx.expression());
				
			}
	
		}
        
        return Value.VOID;
	}
	@Override public Value visitIfStatement(PascalParser.IfStatementContext ctx) {
		//Value value = this.visit(ctx.expression());
		System.out.println("if");
		//System.out.println(value.asBoolean());
		//if (value!=null){
			//while(value.asBoolean()) {

				// evaluate the code block
				//this.visit(ctx.statements());
	
				// evaluate the expression
				//value = this.visit(ctx.expression());
				
			//}
	
		//}
        
       return Value.VOID;

	}

	@Override public Value visitForDoLoop(PascalParser.ForDoLoopContext ctx) { 
		System.out.println("for loop");
		return  this.visit(ctx.expression(0));
	}

	@Override public Value visitNumberAtom(PascalParser.NumberAtomContext ctx) { 
		System.out.println(Double.valueOf(ctx.getText()));
		return new Value(Double.valueOf(ctx.getText()));
	}

	@Override public Value visitBooleanAtom(PascalParser.BooleanAtomContext ctx) { 
		System.out.println(Boolean.valueOf(ctx.getText()));
		return new Value(Boolean.valueOf(ctx.getText()));
	}
     @Override 
    public Value visitIdAtom(PascalParser.IdAtomContext ctx) { 
		System.out.println("id atom");
		String id = ctx.getText();
        Value value = memory.get(id);
        if (value == null) {
            throw new RuntimeException("No such variable: " + id);
        }
        return value;
	}

    @Override 
    public Value visitStringAtom(PascalParser.StringAtomContext ctx) {
		System.out.println("String");
		String str = ctx.getText();
        // strip quotes
        str = str.substring(1, str.length() - 1).replace("\"\"", "\"");
        return new Value(str);
	}
}

