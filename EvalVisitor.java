import org.antlr.v4.runtime.misc.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EvalVisitor extends PascalBaseVisitor<Value> {
    private Map<String,Value> memory = new HashMap<String,Value>();


	/*********************************************************/


	@Override 
	public Value visitVarSingleDec(PascalParser.VarSingleDecContext ctx) { 
		//System.out.println("variable single declaration");
		String id = ctx.ID().getText();
		Value v = this.visit(ctx.expression());
		//System.out.println("Value stored: " + v.asString());

		switch (ctx.type.getType()) {
			case PascalParser.BOOLEAN:
				// System.out.println("Type: boolean");
				return memory.put(id, v);
			case PascalParser.REAL:
				// System.out.println("Type: real");
				return memory.put(id, v);
			default:
				throw new RuntimeException("unknown type: " + ctx.type.getType());
		}
	}


	@Override 
	public Value visitVarListDec(PascalParser.VarListDecContext ctx) {
		//System.out.println("var list declaration");
		//System.out.println("var list size: " + ctx.ID().size());

        switch (ctx.type.getType()) {
			case PascalParser.BOOLEAN:
				//System.out.println("var list type: boolean");
				for (int i = 0; i < ctx.ID().size(); i++) {
					String id = ctx.ID(i).getText();
					System.out.println("ID[" + i + "]: " + id);
					memory.put(id, new Value(false));
				}
				return Value.VOID;
			case PascalParser.REAL:
				//System.out.println("var list type: real");
				for (int i = 0; i < ctx.ID().size(); i++) {
					String id = ctx.ID(i).getText();
					System.out.println("ID [" + i + "]: " + id);
					memory.put(id, new Value(0.0));
				}
				return Value.VOID;
			default:
				throw new RuntimeException("unknown type: " + ctx.type.getType());
		}
	}
	

	@Override
	public Value visitAssignStatement(PascalParser.AssignStatementContext ctx) {
		//System.out.println("assign");
		String id = ctx.getText();
		Value v = this.visit(ctx.expression());
		System.out.println(id + " := " + v.asString());
		return memory.put(id, v);
	}
	

	@Override
	public Value visitParenthesisExpression(PascalParser.ParenthesisExpressionContext ctx) {
        return this.visit(ctx.expression());
    }


	@Override
	public Value visitSqrtExpression(PascalParser.SqrtExpressionContext ctx) {
		//System.out.println("sqrt");
		Value v = this.visit(ctx.expression());
        return new Value(Math.sqrt(v.asDouble()));
	}


	@Override
	public Value visitSinExpression(PascalParser.SinExpressionContext ctx) { 
		//System.out.println("sin");
		Value v = this.visit(ctx.expression());
        return new Value(Math.sin(v.asDouble()));
	}


	@Override
	public Value visitCosExpression(PascalParser.CosExpressionContext ctx) {
		//System.out.println("cos");
		Value v = this.visit(ctx.expression());
        return new Value(Math.cos(v.asDouble()));
	}


	@Override
	public Value visitLogExpression(PascalParser.LogExpressionContext ctx) {
		//System.out.println("log");
		Value v = this.visit(ctx.expression());
        return new Value(Math.log(v.asDouble()));
	}


	@Override
	public Value visitExpExpression(PascalParser.ExpExpressionContext ctx) { 
		//System.out.println("exp");
        Value v = this.visit(ctx.expression());
        return new Value(Math.exp(v.asDouble()));
	}


	@Override
	public Value visitMultiplicativeExpression(PascalParser.MultiplicativeExpressionContext ctx) {
		Value left = this.visit(ctx.expression(0));
        Value right = this.visit(ctx.expression(1));

        switch (ctx.op.getType()) {
            case PascalParser.PRODUCT:
                return new Value(left.asDouble() * right.asDouble());
            case PascalParser.DIVIDE:
                return new Value(left.asDouble() / right.asDouble());
            case PascalParser.MOD:
                return new Value(left.asDouble() % right.asDouble());
            default:
                throw new RuntimeException("unknown operator: " + PascalParser.tokenNames[ctx.op.getType()]);
        }
	}


	@Override 
	public Value visitAdditiveExpression(PascalParser.AdditiveExpressionContext ctx) { 
		Value left = this.visit(ctx.expression(0));
        Value right = this.visit(ctx.expression(1));
        switch (ctx.op.getType()) {
            case PascalParser.PLUS:
            
                return left.isDouble() && right.isDouble() ?
                        new Value(left.asDouble() + right.asDouble()) :
                        new Value(left.asString() + right.asString());
                       
            case PascalParser.MINUS:
                return new Value(left.asDouble() - right.asDouble());
            default:
                throw new RuntimeException("unknown operator: " + PascalParser.tokenNames[ctx.op.getType()]);
        }
	}


	@Override 
	public Value visitNegExpression(PascalParser.NegExpressionContext ctx) { 
		//System.out.println("unary minus");
		Value v = this.visit(ctx.expression());
        return new Value(-v.asDouble());
	}


	@Override 
	public Value visitAndExpression(PascalParser.AndExpressionContext ctx) { 
		//System.out.println("and");
		Value left = this.visit(ctx.expression(0));
        Value right = this.visit(ctx.expression(1));
        return new Value(left.asBoolean() && right.asBoolean());
	}


	@Override 
	public Value visitOrExpression(PascalParser.OrExpressionContext ctx) {
		//System.out.println("or");
		Value left = this.visit(ctx.expression(0));
        Value right = this.visit(ctx.expression(1));
        return new Value(left.asBoolean() || right.asBoolean());
	}
	
	
	@Override 
	public Value visitEqualityExpression(PascalParser.EqualityExpressionContext ctx) { 
		//System.out.println("equality");
		Value left = this.visit(ctx.expression(0));
        Value right = this.visit(ctx.expression(1));
		
        switch (ctx.op.getType()) {
			case PascalParser.EQ:
				if (left != null && right != null) {
					return new Value(left.equals(right));
				}
			case PascalParser.NEQ:
				if (left != null && right != null)  {
					return new Value(left.equals(right));
				}
            default:
				throw new RuntimeException("unknown operator: " + PascalParser.tokenNames[ctx.op.getType()]);
		}
	}


	@Override
	public Value visitRelationalExpression(PascalParser.RelationalExpressionContext ctx) {
		//System.out.println("relational");
		Value left = this.visit(ctx.expression(0));
        Value right = this.visit(ctx.expression(1));
		
        switch (ctx.op.getType()) {
			case PascalParser.GT:
				if (left != null && right != null) {
					return new Value(left.asDouble() > right.asDouble());
				}
			case PascalParser.LT:
				if (left != null && right != null)  {
					return new Value(left.asDouble() < right.asDouble());
				}
			case PascalParser.GTE:
				if (left != null && right != null)  {
					return new Value(left.asDouble() >= right.asDouble());
				}
			case PascalParser.LTE:
				if (left != null && right != null)  {
					return new Value(left.asDouble() <= right.asDouble());
				}
            default:
				throw new RuntimeException("unknown operator: " + PascalParser.tokenNames[ctx.op.getType()]);
		}
	}


	@Override 
	public Value visitWriteNewline(PascalParser.WriteNewlineContext ctx) {
		//System.out.println("write ln");
		System.out.println();
		return Value.VOID;
	}


	@Override
	public Value visitWriteInside(PascalParser.WriteInsideContext ctx) {
		//System.out.println("write inside");
        //System.out.println("expression list size: " + ctx.expression().size());
   
        if ( ctx.expression().size()<=1){
            for (int i = 0; i < ctx.expression().size(); i++) {
                String value = this.visit(ctx.expression(i)).asString();
               // String text = ctx.expression(i).getText();
                if(value!=null){
                        System.out.println(value);
        
          
                }
             }
        }else{
            for (int i = 0; i < ctx.expression().size(); i++) {
                String value = this.visit(ctx.expression(i)).asString();
                //String text = ctx.expression(i).getText();
                if(value!=null){
                    System.out.println(value);
                }
                
            }
         }
		return Value.VOID;
	}


	@Override 
	public Value visitIfStatement(PascalParser.IfStatementContext ctx) {
		//System.out.println("if");
        Value value = this.visit(ctx.expression());
        String choice = this.visit(ctx.expression()).asString();
		System.out.println(choice);

		if (value != null) {
			
        }
        
       return Value.VOID;
	}


	@Override 
	public Value visitForDoLoop(PascalParser.ForDoLoopContext ctx) { 
		//System.out.println("for loop");
		return this.visit(ctx.expression(0));
	}


	@Override 
	public Value visitNumberAtom(PascalParser.NumberAtomContext ctx) { 
		//System.out.println("number atom");
		return new Value(Double.valueOf(ctx.getText()));
	}


	@Override 
	public Value visitBooleanAtom(PascalParser.BooleanAtomContext ctx) { 
		//System.out.println("boolean atom");
		return new Value(Boolean.valueOf(ctx.getText()));
	}


    @Override 
    public Value visitIdAtom(PascalParser.IdAtomContext ctx) {
		//System.out.println("id atom");
		String id = ctx.getText();
		Value v = memory.get(id);
		
        if (v == null) {
            throw new RuntimeException("no such variable: " + id);
        }
        return v;
	}


    @Override 
    public Value visitStringAtom(PascalParser.StringAtomContext ctx) {
		//System.out.println("string atom");
		String str = ctx.getText();
        // strip quotes
        str = str.substring(1, str.length() - 1).replace("\"\"", "\"");
        return new Value(str);
	}
}