import org.antlr.v4.runtime.misc.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EvalVisitor extends PascalBaseVisitor<Value> {
    private Map<String,Value> memory = new HashMap<String,Value>();

	@Override
    public Value visitLogExpression(PascalParser.LogExpressionContext ctx) { 

		// String str = ctx.getText();
        // // strip quotes
        // str = str.substring(1, str.length() - 1).replace("\"\"", "\"");
		// return new Value(str);
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
		System.out.println("relational");
		return  this.visit(ctx.expression(0));
	}

	@Override public Value visitEqualityExpression(PascalParser.EqualityExpressionContext ctx) { 
		System.out.println("equality");
		return  this.visit(ctx.expression(0));
	}

	// @Override public Value visitNumberAtom(PascalParser.NumberAtomContext ctx) { 
	// 	System.out.println("real number");
	// 	return  this.visit(ctx.getText());
	// }

	// @Override public Value visitBooleanAtom(PascalParser.BooleanAtomContext ctx) { 
	// 	System.out.println("boolean");
	// 	return  this.visit(ctx.getText());
	// }

	// @Override public Value visitIdAtom(PascalParser.IdAtomContext ctx) { 
	// 	System.out.println("Id");
	// 	return  this.visit(ctx.getText());
	// }

	// @Override public Value visitStringAtom(PascalParser.StringAtomContext ctx) {
	// 	System.out.println("String");
	// 	return  this.visit(ctx.getText());
	// }

	// @Override public Value visitAtomExpression(PascalParser.AtomExpressionContext ctx) { 
	// 	System.out.println("atom expression");
	// 	return  this.visit(ctx.expression(0));
	// }
}

