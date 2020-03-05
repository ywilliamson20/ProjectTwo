// Project 2
// By Nicole Ajoy & Yvette Williamson

//----------------------------------------------------//

public class Value {
    final Object value;
    
    public static Value VOID = new Value(new Object());

    Value() {
        value = null;
    }

    Value(Object v) {
        value = v;
    }

    public Boolean asBoolean() {
        return (Boolean)value;
    }

    public Double asDouble() {
        return (Double)value;
    }

    public String asString() {
        return String.valueOf(value);
    }

    public boolean isNumber() {
        return value instanceof Number;
    }

    public boolean isDouble() {
        return value instanceof Double;
    }

    public boolean isBoolean() {
        return value instanceof Boolean;
    }

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

        if (this.isNumber() && that.isNumber()) {
            double diff = Math.abs(this.asDouble() - that.asDouble());
            return diff < 0.00000000001;
        }
        else {
            return this.value.equals(that.value);
        }
    }
}