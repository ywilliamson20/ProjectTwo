// Project 2
// By Nicole Ajoy & Yvette Williamson

//----------------------------------------------------//

import java.util.HashMap;
import java.util.Map;

public class Scope {
    private Scope parentScope;
    private Map<String, Value> symbolTable;

    Scope() {
        parentScope = null;
        symbolTable = new HashMap<String, Value>();
    }

    Scope(Scope p) {
        parentScope = p;
        symbolTable = new HashMap<String, Value>();
    }
    
    public Scope getScope() {
        return parentScope;
    }

    public boolean isGlobalScope() {
        return parentScope == null;
    }

    public void addToSymTab(String id, Value val) {
    	symbolTable.put(id, val);
    }
    
    public Value getValue(String id) {
        Value val = symbolTable.get(id);
        // If variable exists in scope
        if (val != null) {
            return val;
        }
        // If this isn't global scope, check parent scope
        else if (!isGlobalScope()) {
            return parentScope.getValue(id);
        }
        // If var found but no value, or if not found in any scope (not even global),
        // then var does not exist
        else {
            return null;
        }
    }

    public void setValue(String id, Value val) {
        // Variable exists in scope, so update its value
        if (getValue(id) != null) {
            symbolTable.replace(id, val);
        }
        // Create a variable in current scope
        else {
            symbolTable.put(id, val);
        }
    }
    public void clearValue() {
        symbolTable.clear();
    }

    public void resetValue(String id, Value val) {
        // Variable in scope
        if (symbolTable.containsKey(id)) {
            symbolTable.put(id, val);
        }
        // Variable not declared in this scope, check parent scope
        else if (parentScope != null) {
            parentScope.resetValue(id, val);
        }
    }
    public int size() {

        return symbolTable.size();
    }

    @Override
    public String toString() {
    	StringBuilder sb = new StringBuilder();
    	for (Map.Entry<String, Value> var: symbolTable.entrySet()) {
    		sb.append(var.getKey()).append("->").append(var.getValue()).append(",");
    	}
    	return sb.toString();
    }
}