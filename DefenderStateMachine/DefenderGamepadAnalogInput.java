package org.firstinspires.ftc.teamcode.dcs15815;

import java.util.ArrayList;
import java.util.function.DoubleSupplier;

public class DefenderGamepadAnalogInput implements DefenderEventSource {
    private DefenderGamepad controller;
    private DoubleSupplier source;
    private double value;

    DefenderGamepadAnalogInput(DefenderGamepad c, DoubleSupplier s) {
        controller = c;
        source = s;
    }

    public void get() {
        value = source.getAsDouble();
    }

    public ArrayList<DefenderEvent> gatherEvents() {
        double oldValue = value;
        ArrayList<DefenderEvent> eventList = new ArrayList();
        get();

        if (isZero()) {
            eventList.add(zero());
        }
        if (isNotZero()) {
            eventList.add(notZero());
        }
        if (isPositive()) {
            eventList.add(positive());
        }
        if (isNegative()) {
            eventList.add(negative());
        }
        if (isChanged(oldValue)) {
            eventList.add(changed());
        }
        return eventList;
    }

    public boolean isZero() {
        return value == 0;
    }

    public boolean isNotZero() {
        return value != 0;
    }

    public boolean isPositive() {
        return value > 0;
    }

    public boolean isNegative() {
        return value < 0;
    }
    public boolean isChanged(double last) {
        return last != value;
    }


    public DefenderEvent zero() {
        return newEventOfType(DefenderEvent.Type.ZERO);
    }
    public DefenderEvent notZero() {
        return newEventOfType(DefenderEvent.Type.NOTZERO);
    }

    public DefenderEvent positive() {
        return newEventOfType(DefenderEvent.Type.POSITIVE);
    }

    public DefenderEvent negative() {
        return newEventOfType(DefenderEvent.Type.NEGATIVE);
    }

    public DefenderEvent changed() {
        return newEventOfType(DefenderEvent.Type.CHANGED);
    }

    protected DefenderEvent newEventOfType(DefenderEvent.Type t) {
        return new DefenderEvent(this, t);
    }
}
