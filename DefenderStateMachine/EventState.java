package org.firstinspires.ftc.teamcode.dcs15815;

import java.util.ArrayList;
import java.util.Hashtable;

public class EventState extends DefenderState {
    protected Hashtable<DefenderEvent, Runnable> eventResponses;

    EventState() {
        super();
        eventResponses = new Hashtable<>();
    }

    public void when(DefenderEventSource s, DefenderEvent.Type type, Runnable r) {
        eventResponses.put(new DefenderEvent(s, type), r);
    }

    public void when(DefenderEvent e, Runnable r) {
        eventResponses.put(e, r);
    }


    public void when(DefenderEventSource s, DefenderEvent.Type type, Runnable r, long debounceTimeout) {
        DefenderDebouncer debouncer = new DefenderDebouncer(debounceTimeout, r);
        eventResponses.put(new DefenderEvent(s, type), debouncer);
    }

    public void when(DefenderEvent e, Runnable r, long debounceTimeout) {
        DefenderDebouncer debouncer = new DefenderDebouncer(debounceTimeout, r);
        eventResponses.put(e, debouncer);
    }

    public void andThenWhen(DefenderEvent e, DefenderState ns) {
        nextState = ns;
        when(e, () -> {
           nextState = ns;
           isFinished = true;
        });
    }


    @Override
    public void run() {
        ArrayList<DefenderEvent> currentEvents = stateMachine.getCurrentEvents();
        eventResponses.forEach((eventPattern, response) -> {
            for (DefenderEvent event : currentEvents)
                if (eventPattern.matches(event)) {
                    if (event.hasPayload()) {
                        stateMachine.setVariable(event.getPayload().key, event.getPayload());
                    }
                    response.run();
                }

        });
    }
}
