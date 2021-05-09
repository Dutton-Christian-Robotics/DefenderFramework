package org.firstinspires.ftc.teamcode.dcs15815;

import java.util.ArrayList;

public class DefenderState {
    protected String label;
    protected DefenderState nextState;
    protected boolean isFinished;

    protected DefenderStateMachine stateMachine;

    DefenderState() {
        isFinished = false;
    }

    DefenderState(DefenderStateMachine sm) {
        isFinished = false;
        setStateMachine(sm);
    }

    public void setStateMachine(DefenderStateMachine stateMachine) {
        this.stateMachine = stateMachine;
    }


    public void beforeStart() {

    }

    public void beforeStop() {

    }

    public void run() {
    }

    public boolean isFinished() {
        return isFinished;
    }
    public void setIsFinished(boolean b) {
        isFinished = b;
    }

    public boolean hasNextState() {
        return nextState != null;
    }

    public void andThen(DefenderState ns) {
        nextState = ns;
    }

    public AndState and(DefenderState s) {
        AndState newAndState = new AndState();
        newAndState.addState(this);
        newAndState.addState(s);
        return newAndState;
    }

    public OrState or(DefenderState s) {
        OrState newOrState = new OrState();
        newOrState.addState(this);
        newOrState.addState(s);
        return newOrState;
    }

}
