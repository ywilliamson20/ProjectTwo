// Project 2
// By Nicole Ajoy & Yvette Williamson

//----------------------------------------------------//

import org.antlr.v4.runtime.misc.NotNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.ArrayList;

public class EvalVisitor extends PascalBaseVisitor<Value> {
	//private Map<String, Value> memory = new HashMap<String, Value>();
	List<String> functions =new ArrayList<>();
	private Map<String, PascalParser.StatementsContext > call= new HashMap<String, PascalParser.StatementsContext >();
	private Map<String, PascalParser.ParametersContext > vars= new HashMap<String, PascalParser.ParametersContext >();
	List<PascalParser.StatementsContext> expr = new ArrayList<>();
	boolean pop =false;
	boolean contin =false;
    //EvalVisitor(Scope scope, Map<String, Function> functions) {
       // this.scope = scope;
       // this.functions = functions;
	//}
	Scope scope = new Scope();


	@Override 
	public Value visitVarSingleDec(PascalParser.VarSingleDecContext ctx) {
		String id = ctx.ID().getText();
		Value val = this.visit(ctx.expression());
		System.out.println("Value stored: " + val.asString());
		
		switch (ctx.type.getType()) {
			case PascalParser.BOOLEAN:
				// System.out.println("Type: boolean");
				scope.addToSymTab(id, val);
			
				return val;
			case PascalParser.REAL:
				// System.out.println("Type: real");
				scope.addToSymTab(id, val);
				
				return val;
			default:
				throw new RuntimeException("unknown type: " + ctx.type.getType());
		}
	
	}

	@Override 
	public Value visitFunctionBlock(PascalParser.FunctionBlockContext ctx){
		scope = new Scope();
		scope.getScope();
		vars.put(ctx.ID().getText(),ctx.parameters());
		call.put(ctx.ID().getText(),ctx.statements());
		//System.out.print("func" +scope.size());
		//scope = scope.getScope();
		scope.clearValue();
		return Value.VOID;
	}

	@Override 
	public Value visitSubprogramCall(PascalParser.SubprogramCallContext ctx){
		String id = ctx.ID().getText();
		//System.out.print(ctx.ID().size);
		if(expr !=null && call.containsKey(id));
		{
			
			this.visit(vars.get(id));
			//System.out.print("sub" +scope.size());
			
			this.visit(call.get(id));
			scope.rem(id);

		}
		return Value.VOID;

	}

	@Override 
	public Value visitProcedureBlock(PascalParser.ProcedureBlockContext ctx){
		scope = new Scope();
		System.out.println("var list size: ");
		scope.getScope();
		vars.put(ctx.ID().getText(),ctx.parameters());
		call.put(ctx.ID().getText(),ctx.statements());
		//System.out.print("prod" +scope.size());
		//scope.clearValue();
		return Value.VOID;
	}

	@Override 
	public Value visitBlocks(PascalParser.BlocksContext ctx)
	{
		for (int i = 0; i < ctx.functionBlock().size(); i++) {
		this.visit(ctx.functionBlock(i));
		//System.out.print("funcs"+scope.size());
		scope.clearValue();
		
		}
		for (int i = 0; i < ctx.procedureBlock().size(); i++) {
		this.visit(ctx.procedureBlock(i));
		//System.out.print("prob"+scope.size());
		scope.clearValue();
		}
		scope.clearValue();
		this.visit(ctx.mainBlock());
		
		return Value.VOID;
	}

	
	
	@Override 
	public Value visitVarListDec(PascalParser.VarListDecContext ctx) {
		System.out.println("var list size: " + ctx.ID().size());

        switch (ctx.type.getType()) {
			case PascalParser.BOOLEAN:
				//System.out.println("var list type: boolean");
				for (int i = 0; i < ctx.ID().size(); i++) {
					String id = ctx.ID(i).getText();
					//System.out.println("ID[" + i + "]: " + id);
					//memory.put(id, new Value(false));
					scope.addToSymTab(id, new Value(false));
				}
				return Value.VOID;
			case PascalParser.REAL:
				//System.out.println("var list type: real");
				for (int i = 0; i < ctx.ID().size(); i++) {
					String id = ctx.ID(i).getText();
					//System.out.println("ID [" + i + "]: " + id);
					scope.addToSymTab(id, new Value(0.0));
					//memory.put(id, new Value(0.0));
				}
				
				return Value.VOID;
			default:
				throw new RuntimeException("unknown type: " + ctx.type.getType());
		}
	}
	

	@Override
	public Value visitAssignStatement(PascalParser.AssignStatementContext ctx) {
		String id = ctx.ID().getText();
		Value val = this.visit(ctx.expression());
		if(scope.getValue(id)==null){
			throw new RuntimeException("need to declare variable: " + id);
		}
		
		//System.out.println("Id: " + id + " | Value: " + v.asString());
		scope.setValue(id, val);
		//return memory.put(id, val);
		return  this.visit(ctx.expression());
	}
	

	@Override
	public Value visitParenthesisExpression(PascalParser.ParenthesisExpressionContext ctx) {
        return this.visit(ctx.expression());
    }


	@Override
	public Value visitSqrtExpression(PascalParser.SqrtExpressionContext ctx) {
		Value v = this.visit(ctx.expression());
        return new Value(Math.sqrt(v.asDouble()));
	}


	@Override
	public Value visitSinExpression(PascalParser.SinExpressionContext ctx) {
		Value v = this.visit(ctx.expression());
        return new Value(Math.sin(v.asDouble()));
	}


	@Override
	public Value visitCosExpression(PascalParser.CosExpressionContext ctx) {
		Value v = this.visit(ctx.expression());
        return new Value(Math.cos(v.asDouble()));
	}


	@Override
	public Value visitLogExpression(PascalParser.LogExpressionContext ctx) {
		Value v = this.visit(ctx.expression());
        return new Value(Math.log(v.asDouble()));
	}


	@Override
	public Value visitExpExpression(PascalParser.ExpExpressionContext ctx) {
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
		Value val = this.visit(ctx.expression());
		return new Value(-val.asDouble());
	}


	@Override 
	public Value visitNotExpression(PascalParser.NotExpressionContext ctx) { 
		Value val = this.visit(ctx.expression());
		return new Value(!val.asBoolean());
	}


	@Override 
	public Value visitAndExpression(PascalParser.AndExpressionContext ctx) { 
		Value left = this.visit(ctx.expression(0));
		Value right = this.visit(ctx.expression(1));
		return new Value(left.asBoolean() && right.asBoolean());
	}


	@Override 
	public Value visitOrExpression(PascalParser.OrExpressionContext ctx) {
		Value left = this.visit(ctx.expression(0));
		Value right = this.visit(ctx.expression(1));
		return new Value(left.asBoolean() || right.asBoolean());
	}
	
	
	@Override 
	public Value visitEqualityExpression(PascalParser.EqualityExpressionContext ctx) { 
		Value left = this.visit(ctx.expression(0));
		Value right = this.visit(ctx.expression(1));
		
		switch (ctx.op.getType()) {
			case PascalParser.EQ:
				if (left != null && right != null) {
					return new Value(left.equals(right));
				}
			case PascalParser.NEQ:
				if (left != null && right != null)  {
					return new Value(!left.equals(right));
				}
			default:
				throw new RuntimeException("unknown operator: " + PascalParser.tokenNames[ctx.op.getType()]);
		}
	}


	@Override
	public Value visitRelationalExpression(PascalParser.RelationalExpressionContext ctx) {
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
		System.out.println();
		return Value.VOID;
	}


	@Override
	public Value visitWriteInside(PascalParser.WriteInsideContext ctx) {
		//System.out.println("expression list size: " + ctx.expression().size());
		String output = "";
		
		if (ctx.expression().size() <= 1) {
            for (int i = 0; i < ctx.expression().size(); i++) {
				String token = this.visit(ctx.expression(i)).asString();
				// String text = ctx.expression(i).getText();
				if (token != null) {
					output = output + token;
					//System.out.println(v);
				}
			}
		}
		else {
			for (int i = 0; i < ctx.expression().size(); i++) {
				String token = this.visit(ctx.expression(i)).asString();
				//String text = ctx.expression(i).getText();
				if (token != null) {
					output = output + token;
					//System.out.println(v);
				}
			}
		}
		
		System.out.println(output);
		return Value.VOID;
	}


	@Override
	public Value visitReadPause(PascalParser.ReadPauseContext ctx) {
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		return Value.VOID;
	}


	@Override
	public Value visitReadInput(PascalParser.ReadInputContext ctx) {
		Scanner sc = new Scanner(System.in);
		
		for (int i = 0; i < ctx.ID().size(); i++) {
			String id = ctx.ID(i).getText();
			String input = sc.nextLine();
			try {
				Value v = new Value(Double.parseDouble(input));
				//memory.put(id, v);
				scope.addToSymTab(id,v);
			}
			catch (Exception e) {
				throw new RuntimeException("cannot read variables of this type");
			}
		}
		
		return Value.VOID;
	}


	@Override 
	public Value visitIfStatement(PascalParser.IfStatementContext ctx) {
        //System.out.println("expression list size: " + ctx.expression(0).size());
		String choice = this.visit(ctx.expression()).asString();
		int val = ctx.statement().size();
		boolean brea =false;
		boolean cont=false;
		boolean skip =false;
		
		//System.out.println(choice);
		

		if (choice == "true") {
			if(ctx.BREAK(0)!=null&&brea==false){
				pop=true;
				brea=true;
				return Value.VOID;
			}
			else if (ctx.BREAK(0)!=null&&brea==true){
				this.visit(ctx.statement(0));
			}
			else if(ctx.BREAK(0)==null)
			{
				this.visit(ctx.statement(0));
			}
			if(ctx.CONTINUE(0)!=null&&cont==false){
				cont=true;
				pop=true;
				//System.out.println("continue");
				this.visit(ctx.statement(0));

			}
			else if(ctx.CONTINUE(0)!=null&&cont==false&&skip==false)
			{
				cont=true;
				if (ctx.CONTINUE(0)!=null&&cont==true){
					skip=true;
					this.visit(ctx.statement(0));
					//this.visit(ctx.statement().expression(0)).asDouble=this.visit(ctx.statement().expression(0)).asDouble()+1;
				}
				else if (ctx.CONTINUE(0)!=null&&cont==true&&skip==true){

					this.visit(ctx.statement(0));
				}
				

			}
			
	}	
			
		
		if (choice == "false") {
			if(ctx.BREAK(0)!=null&&brea==true){

				brea=false;
				return Value.VOID;
			}
			else if (ctx.BREAK(0)!=null&&brea==false){
				if(val==2){
					this.visit(ctx.statement(1));
				}
			}
			else if(ctx.BREAK(0)==null){
				this.visit(ctx.statement(1));
			}
			if(ctx.CONTINUE(0)!=null&&cont==true){
				cont=false;
				
				
			}else if (ctx.CONTINUE(0)!=null&&cont==false){
				if(val==2){
					this.visit(ctx.statement(1));
				}
				
				
			}
		
			 
		}
		
		return Value.VOID;
	}

	
	@Override 
	public Value visitCaseStatement(PascalParser.CaseStatementContext ctx) {
		//Value value = this.visit(ctx.expression());
		//System.out.println(value.asBoolean());

		//System.out.println("expression list size: " + ctx.expression().size());
		//System.out.println("statements list size: " + ctx.statements().size());
		String condition = this.visit(ctx.expression(0)).asString();

		for (int i = 1; i < ctx.expression().size(); i++) {
			String currentCase = this.visit(ctx.expression(i)).asString();
			if (condition.equals(currentCase)) {
				this.visit(ctx.statements(i-1));
			}
		}

		return Value.VOID;
	}


	@Override
	public Value visitWhileDoLoop(PascalParser.WhileDoLoopContext ctx) { 
		Value val = this.visit(ctx.expression());
		
		while (val.asBoolean() == true) {
			if(pop==true){
				//val = this.visit(ctx.expression());
				return Value.VOID;
			}else{
				this.visit(ctx.statements());
				val = this.visit(ctx.expression());
				//pop=false;
			}
			
		}
		
		return Value.VOID;
	}
	
	@Override
	public Value visitForDoLoop(PascalParser.ForDoLoopContext ctx) { 
        int start = this.visit(ctx.expression(0)).asDouble().intValue();
		int stop = this.visit(ctx.expression(1)).asDouble().intValue();
		
		Value val = this.visit(ctx.expression(0));
		scope.addToSymTab(ctx.ID().getText(), val);
		//memory.put(ctx.ID().getText(), val);
		
		//System.out.println(this.visit(ctx.expression(0)).asString());
		//System.out.println(this.visit(ctx.expression(1)).asString());
		
		switch (ctx.count.getType()) {
			case PascalParser.TO:
				//System.out.println("to");
				if (stop > start) {
					for (int i = start; i <= stop; i++) {
						scope.addToSymTab(ctx.ID().getText(), new Value(i));
						//memory.put(ctx.ID().getText(), new Value(i));
						this.visit(ctx.statements());
					}
					return Value.VOID;
				}
			case PascalParser.DOWNTO:
				//System.out.println("downto");
				if (start > stop) {
					for (int i = start; i >= stop; i--) {
						scope.addToSymTab(ctx.ID().getText(), new Value(i));
						//memory.put(ctx.ID().getText(), new Value(i));
						this.visit(ctx.statements());
					}
					return Value.VOID;
				}
			default:
				throw new RuntimeException("unknown count: " + PascalParser.tokenNames[ctx.count.getType()]);
		}
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
		//Value val = memory.get(id);
		Value scop = scope.getValue(id);
		
		if (scop == null) {
			throw new RuntimeException("no such variable: " + id);
		}
		
		return scop;
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
