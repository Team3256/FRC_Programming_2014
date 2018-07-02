/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.templates.*;
import edu.wpi.first.wpilibj.SimpleRobot;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class MMXIVRobot extends SimpleRobot {
    BallController ballControl;
    Camera camera;
    DriveTrain drivetrain;
    Shooter shooter;
    XboxController driver, operator;
    /**
     * This function is called once each time the robot enters autonomous mode.
     */
    public MMXIVRobot() {
        ballControl = new BallController(Constants.ROLLER_MOTOR_PORT,Constants.PHOTO_PORT, Constants.PHOTO_PORT_1,
                                    Constants.LIMIT_PORT,Constants.CATCHER_PORT, Constants.ULTRASONIC_PORT);
        camera = new Camera();
        drivetrain = new DriveTrain(Constants.LEFT_FRONT_MOTOR_PORT, Constants.LEFT_REAR_MOTOR_PORT,
                                    Constants.RIGHT_FRONT_MOTOR_PORT, Constants.RIGHT_REAR_MOTOR_PORT,
                                    Constants.LEFT_DRIVE_ENC_PORT1, Constants.LEFT_DRIVE_ENC_PORT2,
                                    Constants.RIGHT_DRIVE_ENC_PORT1, Constants.RIGHT_DRIVE_ENC_PORT2,
                                    Constants.SHIFTER_PORT1, Constants.SHIFTER_PORT2);
        shooter = new Shooter(Constants.PUNCH_MOTOR_PORT, Constants.PHOTO_PORT, Constants.REL_PORT1,
                            Constants.REL_PORT2, Constants.LOWER_PORT1, 
                            Constants.UPPER_PORT1, Constants.UPPER_PORT2);
        driver = new XboxController(1);
        operator = new XboxController(2);
    }
    
    public void autonomous() {
        
    }

    /**
     * This function is called once each time the robot enters operator control.
     */
    public void operatorControl() {
        while(isOperatorControl()) {
            drivetrain.arcadeDrive(driver.getLeftY(), driver.getRightX());
            drivetrain.setShifterState(driver.getButtonRB());
            
        }
    }
    
    /**
     * This function is called once each time the robot enters test mode.
     */
    public void test() {
    
    }
}
