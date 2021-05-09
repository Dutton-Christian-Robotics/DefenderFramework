package org.firstinspires.ftc.teamcode.dcs15815;

public class DefenderStateVariable {
    protected String key;
    protected double doubleValue;
    protected String stringValue;
    protected int intValue;
    protected boolean booleanValue;


    DefenderStateVariable(String k, double v) {
        this.key = k;
        this.doubleValue = v;

    }

    DefenderStateVariable(String k, int v) {
        this.key = k;
        this.intValue = v;
    }

    DefenderStateVariable(String k, String v) {
        this.key = k;
        this.stringValue = v;
    }

    DefenderStateVariable(String k, boolean v) {
        this.key = k;
        this.booleanValue = v;
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


}
