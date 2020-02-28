// Resource: https://github.com/bkiers/Mu/blob/master/src/main/java/mu/Value.java

public class Value {
    Object value;
    
    public static Value NULL = new Value();
    public static Value VOID = new Value();

    private Value() {
        value = new Object();
    }

    public Value(Object v) {
        value = v;
    }

    public Boolean asBoolean() {
        return (Boolean)value;
    }

    public Double asDouble() {
        return (Double)value;
    }

    @Override
    public boolean equals(Object o) {
        if (value == o) {
            return true;
        }
        if (value == null || o == null) {
            return false;
        }
        if (o.getClass() != value.getClass()) {
            return false;
        }
        Value that = (Value)o;
        return this.value.equals(that.value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}