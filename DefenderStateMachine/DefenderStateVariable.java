package org.firstinspires.ftc.teamcode.dcs15815;

public class DefenderStateVariable {

    public enum Type {
        INT, DOUBLE, BOOLEAN, STRING
    }

    protected String key;
    protected double doubleValue;
    protected String stringValue;
    protected int intValue;
    protected boolean booleanValue;
    protected Type type;


    DefenderStateVariable(String k, double v) {
        this.key = k;
        this.doubleValue = v;
        type = Type.DOUBLE;

    }

    DefenderStateVariable(String k, int v) {
        this.key = k;
        this.intValue = v;
        type = Type.INT;
    }

    DefenderStateVariable(String k, String v) {
        this.key = k;
        this.stringValue = v;
        type = Type.STRING;
    }

    DefenderStateVariable(String k, boolean v) {
        this.key = k;
        this.booleanValue = v;
        type = Type.BOOLEAN;
    }

    public Type getType() {
        return type;
    }

    public double getDouble() {
        return doubleValue;
    }

    public int getInt() {
        return intValue;
    }
    public String getString() {
        return stringValue;
    }

    public boolean getBoolean() {
        return booleanValue;
    }

    public boolean matches(DefenderStateVariable other) {
        switch (type) {
            case BOOLEAN:
                return getBoolean() == other.getBoolean();
            case DOUBLE:
                return getDouble() == other.getDouble();
            case INT:
                return getInt() == other.getInt();
            case STRING:
                return getString() == other.getString();
            default:
                return false;

        }
    }


}
