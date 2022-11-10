package org.firstinspires.ftc.teamcode.dcs15815;

import java.util.ArrayList;
import java.util.function.BooleanSupplier;

public class DefenderGamepadButton implements DefenderEventSource {
    private DefenderGamepad controller;
    private BooleanSupplier source;
    private boolean value;

    DefenderGamepadButton(DefenderGamepad c, BooleanSupplier s) {
        controller = c;
        source = s;
    }

    public void get() {
        value = source.getAsBoolean();
    }

    public ArrayList<DefenderEvent> gatherEvents() {
        boolean oldValue = value;
        ArrayList<DefenderEvent> eventList = new ArrayList();
        get();

        if (isUp()) {
            eventList.add(up());
        }
        if (isDown()) {
            eventList.add(down());
        }
        if (wasHeld(oldValue)) {
            eventList.add(held());
        }
        if (wasReleased(oldValue)) {
            eventList.add(released());
        }
        if (isChanged(oldValue)) {
            eventList.add(changed());
        }
        return eventList;
    }

    public boolean isUp() {
        return !value;
    }

    public boolean isDown() {
        return value;
    }

    public boolean wasHeld(boolean last) {
        return last && value;
    }

    public boolean wasReleased(boolean last) {
        return last && !value;
    }

    public boolean isChanged(boolean last) {
        return last != value;
    }
    
    public DefenderEvent up() {
        return newEventOfType(DefenderEvent.Type.UP);
    }
    public DefenderEvent down() {
        return newEventOfType(DefenderEvent.Type.DOWN);
    }

    public DefenderEvent held() {
        return newEventOfType(DefenderEvent.Type.HELD);
    }

    public DefenderEvent released() {
        return newEventOfType(DefenderEvent.Type.RELEASED);
    }

    public DefenderEvent changed() {
        return newEventOfType(DefenderEvent.Type.CHANGED);
    }

    protected DefenderEvent newEventOfType(DefenderEvent.Type t) {
        return new DefenderEvent(this, t);
    }
}
