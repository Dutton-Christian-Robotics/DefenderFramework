package org.firstinspires.ftc.teamcode.dcs15815;

import java.lang.reflect.Field;

public class DriveToPositionState extends DefenderState {
    private DefenderBotPosition destination;
    private boolean stopAfterDriving = true;


    DriveToPositionState(DefenderBotPosition d) {
        super();
        destination = d;
    }

    DriveToPositionState(double x, double y, double h) {
        super();
        destination = new DefenderBotPosition(x, y, h);
    }

    @Override
    public void run() {
        try {
//            stateMachine.bot.findSystemByClass(navClass);

//            Method driveToPosition = ((DefenderCanNavigateToPosition)stateMachine.bot.navigation).getClass().getMethod("driveToPosition", DefenderBotPosition.class);
//            driveToPosition.invoke(stateMachine.bot.navigation, destination);

            // I don't now why I had to write it like this, but I was having a lot of trouble
            // getting this to work.
            Field f = stateMachine.bot.getClass().getField("navigation");
            ((DefenderBotCanNavigateToPosition)f.get(stateMachine.bot)).driveToPosition(destination);
//            ((DefenderCanNavigateToPosition)stateMachine.bot.navigation).driveToPosition(destination);
        } catch (Exception e) {
            System.out.println("Problem trying to driveToPosition: " + e.toString());
        } finally {
            isFinished = true;
        }
    }
}
