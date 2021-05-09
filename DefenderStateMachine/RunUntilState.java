package org.firstinspires.ftc.teamcode.dcs15815;

import java.util.function.BooleanSupplier;

public class RunUntilState extends DefenderState {
    private Runnable block;
    private BooleanSupplier isFinishedWhen;

    RunUntilState(Runnable b, BooleanSupplier i) {
        super();
        block = b;
        isFinishedWhen = i;
    }

    @Override
    public void run() {
        block.run();
        isFinished = isFinishedWhen.getAsBoolean();
    }

}
