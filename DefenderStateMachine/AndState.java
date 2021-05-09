package org.firstinspires.ftc.teamcode.dcs15815;

import java.util.ArrayList;
import java.util.Hashtable;

public class AndState extends CompoundState {

    AndState() {
        super();

    }

    public AndState and(DefenderState s) {
        addState(s);
        return this;
    }

    @Override
    public void run() {
        // not sure what goes in here! Do we KEEP creating a thread for each state
        // or is it created and run once? I think it needs to be the former, for example,
        // to make sure that event states get updated lists of events...
        isFinished = true;

        // this could be a problem. Could we have collision where one copy of the thread is still running
        // when another starts?
        for (DefenderState s : states) {
            isFinished = isFinished && s.isFinished();
            if (!isFinished && !s.isFinished()) {
                Thread t = new Thread(() -> s.run());
                t.start();
            }

        }

//        isFinished = true;
    }
}
