package org.firstinspires.ftc.teamcode.dcs15815;

import java.util.ArrayList;
import java.util.Hashtable;

public class DefenderStateMachine {

    private DefenderState currentState;
    public DefenderBot bot;
    private boolean isFinished;
    private ArrayList<DefenderEventSource> eventSources;
    protected ArrayList<DefenderEvent> currentEvents;
    protected Hashtable<String,DefenderStateVariable> variables = new Hashtable<>();


    DefenderStateMachine(DefenderBot b) {
        eventSources = new ArrayList<>();
        bot = b;
        isFinished = false;
        currentEvents = new ArrayList<>();
    }

    public void addEventSource(DefenderEventSource s) {
        eventSources.add(s);
    }

    public boolean isFinished() {
        return isFinished;
    }

    public ArrayList<DefenderEvent> getCurrentEvents() {
        return currentEvents;
    }


    public void setState(DefenderState s) {
        if (currentState != null) {
            currentState.beforeStop();
        }
        currentState = s;
        currentState.beforeStart();
        currentState.setStateMachine(this);
    }

    public void setNextState(DefenderState s) {
        currentState.nextState = s;
    }

    public void setVariable(String key, DefenderStateVariable v) {
        variables.put(key, v);
    }

    public DefenderStateVariable getVariable(String key) {
        return variables.get(key);
    }

    public DefenderState getCurrentState() {
        return currentState;
    }

    public void run() {
        ArrayList<DefenderEvent> events = new ArrayList<>();

        if (isFinished) {
            return;
        }
        if (currentState == null) {
            throw new RuntimeException("State machine has no current state. Did you forget to set a first state?");
        }

        for (DefenderEventSource s : eventSources) {
            events.addAll(s.gatherEvents());
        }
        currentEvents.clear();
        currentEvents.addAll(events);
        currentState.run();

        if (currentState.isFinished()) {
            if (currentState.hasNextState()) {
                setState(currentState.nextState);
            } else {
                isFinished = true;
            }
        }
    }

}
