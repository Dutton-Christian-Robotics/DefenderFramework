package org.firstinspires.ftc.teamcode.dcs15815;

import com.qualcomm.robotcore.util.ElapsedTime;

public class DriveState extends DefenderState {
    private double forward = 0;
    private double strafe = 0;
    private double rotate = 0;
    private long driveTime = 0;
    private ElapsedTime timer;
    private boolean stopAfterDriving = true;


    DriveState(double forward, double strafe, double rotate, long time) {
        super();
        this.forward = forward;
        this.strafe = strafe;
        this.rotate = rotate;
        this.driveTime = time;
    }

    DriveState(double forward, double strafe, double rotate, long time, boolean stopAfterDriving) {
        super();
        this.forward = forward;
        this.strafe = strafe;
        this.rotate = rotate;
        this.driveTime = time;
        this.stopAfterDriving = stopAfterDriving;
    }

    @Override
    public void beforeStart() {
        if (driveTime > 0) {
            timer = new ElapsedTime();
            timer.reset();
        }
    }


    @Override
    public void run() {
        if (driveTime > 0) {
            if (timer.milliseconds() >= driveTime) {
                isFinished = true;
                if (stopAfterDriving) {
                    stateMachine.bot.stopDriving();
                }
            } else {
                stateMachine.bot.drivetrain.drive(forward, strafe, rotate);
            }
        }

    }
}
