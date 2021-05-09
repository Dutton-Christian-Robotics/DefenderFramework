package org.firstinspires.ftc.teamcode.dcs15815;

import java.util.ArrayList;
import java.util.Hashtable;

public class CompoundState extends DefenderState {
    protected ArrayList<DefenderState> states;

    CompoundState() {
        super();
        states = new ArrayList<>();
    }

    public void addState(DefenderState s) {
        states.add(s);
    }

    @Override
    public void beforeStart() {
        for (DefenderState s : states) {
            s.beforeStart();
        }
    }

    @Override
    public void beforeStop() {
        for (DefenderState s : states) {
            s.beforeStop();
        }

    }




    @Override
    public void run() {
        isFinished = true;
    }
}
