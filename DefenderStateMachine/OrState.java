package org.firstinspires.ftc.teamcode.dcs15815;

import java.util.ArrayList;
import java.util.Hashtable;

public class OrState extends CompoundState {

    OrState() {
        super();

    }

    public OrState or(DefenderState s) {
        addState(s);
        return this;
    }

    public void beforeState() {
        isFinished = false;
    }


    @Override
    public void run() {

        // this could be a problem. Could we have collision where one copy of the thread is still running
        // when another starts?
        for (DefenderState s : states) {
            if (!s.hasStateMachine()) {
                s.setStateMachine(stateMachine);
            }
//            isFinished = isFinished || s.isFinished();
            if (isFinished) {
                break;
            }
            if (!s.isFinished()) {
                Thread t = new Thread(() -> {
                   s.run();
                   if (s.isFinished() && s.hasNextState()) {
                       setNextState(s.nextState);
                   }
                    isFinished = isFinished || s.isFinished();
                });
                t.start();
            }
        }

//        isFinished = true;
    }
}
