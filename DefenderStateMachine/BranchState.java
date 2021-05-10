package org.firstinspires.ftc.teamcode.dcs15815;

import java.util.ArrayList;

public class BranchState extends EventState {
    protected DefenderEvent trigger;
    protected String payloadName;

    BranchState(DefenderEvent e, String pn) {
        super();
        trigger = e;
        payloadName = pn;
    }

    public void when(double v, Runnable r) {
        when(trigger.copyWithPayload(v), r);
//        when(trigger, () -> {
//            double value = stateMachine.getVariable(payloadName).getDouble();
//            if (value == v) {
//                r.run();
//            }
//        });
    }

    public void andThenWhen(double v, DefenderState ns) {
        andThenWhen(trigger.copyWithPayload(v), ns);

//        when(trigger, () -> {
//            double value = stateMachine.getVariable(payloadName).getDouble();
//            if (value == v) {
//                nextState = ns;
//                isFinished = true;
//            }
//        });
    }

    public void when(int v, Runnable r) {
        when(trigger.copyWithPayload(v), r);
//        when(trigger, () -> {
//            int value = stateMachine.getVariable(payloadName).getInt();
//            if (value == v) {
//                r.run();
//            }
//        });
    }

    public void andThenWhen(int v, DefenderState ns) {
        andThenWhen(trigger.copyWithPayload(v), ns);

//        when(trigger, () -> {
//            int value = stateMachine.getVariable(payloadName).getInt();
//            if (value == v) {
//                nextState = ns;
//                isFinished = true;
//            }
//        });
    }
}
