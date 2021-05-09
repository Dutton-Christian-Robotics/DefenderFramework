package org.firstinspires.ftc.teamcode.dcs15815;

public class RunOnceState extends DefenderState {
    private Runnable block;

    RunOnceState(Runnable b) {
        super();
        block = b;
    }

    @Override
    public void run() {
        block.run();
        isFinished = true;
    }
}
