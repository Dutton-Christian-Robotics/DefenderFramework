package org.firstinspires.ftc.teamcode.dcs15815;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.lang.reflect.InvocationTargetException;

abstract public class DefenderFsmOpMode extends LinearOpMode {
    protected DefenderStateMachine stateMachine;

    protected Class<? extends DefenderBot> botClass;
    protected Class<? extends DefenderBotConfiguration> configClass;
    public DefenderBot bot;

    public DefenderGamepad controller1;
    public DefenderGamepad controller2;

    public void setBot(DefenderBot b) {
        this.bot = b;
    }

    public void setBotClass(Class<? extends DefenderBot> botClass) {
        this.botClass = botClass;
    }

    public void setConfigClass(Class<? extends DefenderBotConfiguration> configClass) {
        this.configClass = configClass;
    }

    public abstract void configureBot();

    public abstract void configureStates();

    @Override
    public void runOpMode() {
        controller1 = new DefenderGamepad(gamepad1);
        controller2 = new DefenderGamepad(gamepad2);

        configureBot();
        try {
            if (bot == null) {
                bot = botClass.getConstructor(HardwareMap.class, DefenderBotConfiguration.class, Telemetry.class).newInstance(hardwareMap, configClass, telemetry);
            }
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not create bot instance.");
        }

        stateMachine = new DefenderStateMachine(bot);
        stateMachine.addEventSource(controller1);
        stateMachine.addEventSource(controller2);
        for (DefenderBotSystem s : bot.getSystems()) {
            stateMachine.addEventSource(s);
        }
        configureStates();

        // somehow execute states designed for init mode

        waitForStart();

        while (opModeIsActive() && !stateMachine.isFinished()) {
            stateMachine.run();
        }

    }

    public void setFirstState(DefenderState s) {
        stateMachine.setState(s);
    }

    public void beginWith(DefenderState s) {
        // should check to see if a state has been set with
        stateMachine.setState(s);
    }

}
