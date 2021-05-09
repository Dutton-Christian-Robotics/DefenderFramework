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
        return (this.source == other.source) && (this.type == other.type);
    }

    public boolean hasPayload() { return payload != null; }
    public DefenderStateVariable getPayload() {
        return payload;
    }

    public void setPayload(DefenderStateVariable payload) {
        this.payload = payload;
    }



}
