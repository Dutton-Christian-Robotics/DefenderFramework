package org.firstinspires.ftc.teamcode.dcs15815;

import com.qualcomm.robotcore.hardware.Gamepad;

import java.util.ArrayList;

public class DefenderGamepad implements DefenderEventSource {
    private Gamepad gp;
    public DefenderGamepadButton a;
    public DefenderGamepadButton b;
    public DefenderGamepadButton x;
    public DefenderGamepadButton y;
    public DefenderGamepadButton dpad_up;
    public DefenderGamepadButton dpad_down;
    public DefenderGamepadButton dpad_left;
    public DefenderGamepadButton dpad_right;
    public DefenderGamepadButton right_bumper;
    public DefenderGamepadButton left_bumper;

    public DefenderGamepadAnalogInput left_stick_x;
    public DefenderGamepadAnalogInput left_stick_y;
    public DefenderGamepadAnalogInput right_stick_x;
    public DefenderGamepadAnalogInput right_stick_y;
    public DefenderGamepadAnalogInput left_trigger;
    public DefenderGamepadAnalogInput right_trigger;

    protected ArrayList<DefenderEventSource> eventSources;


    DefenderGamepad(Gamepad g) {
        eventSources = new ArrayList<>();
        this.gp = g;

        a = new DefenderGamepadButton(this, () -> gp.a);
        eventSources.add(a);
        b = new DefenderGamepadButton(this, () -> gp.b);
        eventSources.add(b);
        x = new DefenderGamepadButton(this, () -> gp.x);
        eventSources.add(x);
        y = new DefenderGamepadButton(this, () -> gp.y);
        eventSources.add(y);

        dpad_up = new DefenderGamepadButton(this, () -> gp.dpad_up);
        eventSources.add(dpad_up);

        dpad_down = new DefenderGamepadButton(this, () -> gp.dpad_down);
        eventSources.add(dpad_down);

        dpad_left = new DefenderGamepadButton(this, () -> gp.dpad_left);
        eventSources.add(dpad_left);

        dpad_right = new DefenderGamepadButton(this, () -> gp.dpad_right);
        eventSources.add(dpad_right);

        right_bumper = new DefenderGamepadButton(this, () -> gp.right_bumper);
        eventSources.add(right_bumper);

        left_bumper = new DefenderGamepadButton(this, () -> gp.left_bumper);
        eventSources.add(left_bumper);

        left_stick_x = new DefenderGamepadAnalogInput(this, () -> gp.left_stick_x);
        eventSources.add(left_stick_x);

        left_stick_y = new DefenderGamepadAnalogInput(this, () -> gp.left_stick_y);
        eventSources.add(left_stick_y);

        right_stick_x = new DefenderGamepadAnalogInput(this, () -> gp.right_stick_x);
        eventSources.add(right_stick_x);

        right_stick_y = new DefenderGamepadAnalogInput(this, () -> gp.right_stick_y);
        eventSources.add(right_stick_y);

        left_trigger = new DefenderGamepadAnalogInput(this, () -> gp.left_trigger);
        eventSources.add(left_trigger);

        right_trigger = new DefenderGamepadAnalogInput(this, () -> gp.right_trigger);
        eventSources.add(right_trigger);

    }

    public ArrayList<DefenderEvent> gatherEvents() {
        ArrayList<DefenderEvent> events = new ArrayList<>();

        for (DefenderEventSource b : eventSources) {
            events.addAll(b.gatherEvents());
        }
        return events;
    }
}
