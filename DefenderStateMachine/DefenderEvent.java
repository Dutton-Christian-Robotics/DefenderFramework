package org.firstinspires.ftc.teamcode.dcs15815;

public class DefenderEvent {
    public DefenderEventSource source;
    public Type type;
    protected DefenderStateVariable payload;

    public enum Type {
        UP, DOWN, HELD, RELEASED, CHANGED,
        POSITIVE, NEGATIVE, ZERO, NOTZERO,
        VALUE
    }

    DefenderEvent(DefenderEventSource s, Type t) {
        source = s;
        type = t;
    }

    DefenderEvent(DefenderEventSource s, Type t, DefenderStateVariable p) {
        source = s;
        type = t;
        payload = p;
    }

    public boolean matches(DefenderEvent other) {
        boolean doTheyMatch =( this.source == other.source) && (this.type == other.type);
        if (hasPayload()) {
            doTheyMatch = doTheyMatch && payload.matches(other.getPayload());
        }
        return doTheyMatch;
    }

    public boolean hasPayload() { return payload != null; }
    public DefenderStateVariable getPayload() {
        return payload;
    }

    public void setPayload(DefenderStateVariable payload) {
        this.payload = payload;
    }

    public DefenderEvent copyWithPayload(DefenderStateVariable payload) {
        DefenderEvent theCopy = new DefenderEvent(source, type, payload);
        return theCopy;
    }

    public DefenderEvent copyWithPayload(int v) {
        return copyWithPayload(new DefenderStateVariable("payload", v));
    }

    public DefenderEvent copyWithPayload(double v) {
        return copyWithPayload(new DefenderStateVariable("payload", v));
    }

    public DefenderEvent copyWithPayload(boolean v) {
        return copyWithPayload(new DefenderStateVariable("payload", v));
    }

    public DefenderEvent copyWithPayload(String v) {
        return copyWithPayload(new DefenderStateVariable("payload", v));
    }




}
