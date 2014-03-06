/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.templates.*;
import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Timer;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotTemplate extends SimpleRobot {
    Compressor compressor;
    BallController ballControl;
    Camera camera;
    DriveTrain drivetrain;
    Shooter shooter;
    XboxController driver, operator, manip;
    /**
     * This function is called once each time the robot enters autonomous mode.
     */
    public RobotTemplate() {
        ballControl = new BallController(Constants.ROLLER_MOTOR_PORT,Constants.PHOTO_PORT_1,Constants.PHOTO_PORT_2, 
                                         Constants.LIMIT_PORT, Constants.CATCHER_PORT, Constants.ULTRASONIC_PORT);
        camera = new Camera();
        drivetrain = new DriveTrain(Constants.LEFT_FRONT_MOTOR_PORT, Constants.LEFT_REAR_MOTOR_PORT,
                                    Constants.RIGHT_FRONT_MOTOR_PORT, Constants.RIGHT_REAR_MOTOR_PORT,
                                    Constants.LEFT_DRIVE_ENC_PORT1, Constants.LEFT_DRIVE_ENC_PORT2,
                                    Constants.RIGHT_DRIVE_ENC_PORT1, Constants.RIGHT_DRIVE_ENC_PORT2,
                                    Constants.SHIFTER_PORT1, Constants.SHIFTER_PORT2);
        shooter = new Shooter(Constants.PUNCH_MOTOR_PORT, Constants.REL_PORT1,
                            Constants.REL_PORT2, Constants.LOWER_PORT1, 
                            Constants.UPPER_PORT1, Constants.UPPER_PORT2, Constants.SET_POINT_PORT);
        driver = new XboxController(1);
        operator = new XboxController(2);
        compressor = new Compressor(1,1);
        shooter.setTiltState("kUp");
        compressor.start();
    }
    
    public void autonomous() {
        //shooter.setTiltState("kMiddle");
        shooter.loadShooter(1.0);
        drivetrain.reset();
        Timer.delay(1.0);
        shooter.loadShooter(0.0);
        ballControl.pickFromGround(.55);
        ballControl.setCatcherState(true);
        Timer.delay(0.5);
        ballControl.pickFromGround(0.0);
        for (int i = 0; i < 300 && isAutonomous(); i++) {
            drivetrain.setFeetSetpoint(5.0);
            drivetrain.log();
            shooter.setTiltState("kMiddle");
            Timer.delay(.005);
        }
        //Timer.delay(1.0);
        if(camera.isHot())
            shooter.releasePunch(.5);
        else
        {
            Timer.delay(5);
            shooter.releasePunch(.5);
        }   
    }

    /**
     * This function is called once each time the robot enters operator control.
     */
    public void operatorControl() { //shot at 7ft to 17ft distance from goal at 45 degrees with 9.5 in tubing
        boolean toggle = false;
        while(isOperatorControl()) {
            drivetrain.setShifterState(driver.getButtonRB());
            if (operator.getButtonY())
                shooter.setTiltState("kUp");
            if (operator.getButtonB()) {
                shooter.setTiltState("kMiddle");
                ballControl.setCatcherState(false);
            }
            else
                ballControl.setCatcherState(!shooter.getTiltState().equals("kDown"));
            if (operator.getButtonA()) {
                shooter.setTiltState("kDown");
                ballControl.pickFromGround(.65);
            } else if (operator.getButtonX())
                ballControl.pickFromGround(-0.6);
            else
                ballControl.pickFromGround(-0.05);
            
            if (operator.getLeftTrigger()) {
                shooter.loadShooter(Math.abs(operator.getLeftX()));    
                DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser1,1,"Esta bien");
                DriverStationLCD.getInstance().updateLCD();
            } 
            else
                shooter.loadShooter(-0.05);
            if (operator.getRightTrigger())
                shooter.releasePunch(.075);
            if (driver.getButtonA()) {
                drivetrain.setAngleSetpoint(90.0);
            } else {
                drivetrain.arcadeDrive(-driver.getLeftY(), driver.getRightX());
            }
            if (driver.getButtonB()) {
                drivetrain.reset();
            }
             System.out.println(shooter.getPunchSwitch());
            //DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser4,1,"" + shooter.getPunchSwitch());
            drivetrain.log();
        }
    }
    
    /**
     * This function is called once each time the robot enters test mode.
     */
    public void test() {
    
    }
}
