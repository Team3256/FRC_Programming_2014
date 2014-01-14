/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.templates.DriveTrain;
import edu.wpi.first.wpilibj.templates.XboxController;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotTemplate extends SimpleRobot {
    /**
     * This function is called once each time the robot enters autonomous mode.
     */
    public void autonomous() {
        DriveTrain test = new DriveTrain(1, 2, 3, 4);
        test.driveLeft(0.5);
        Timer.delay(2);
        test.driveLeft(0);
    }

    /**
     * This function is called once each time the robot enters operator control.
     */
    public void operatorControl() {
        DriveTrain test = new DriveTrain(1, 2, 3, 4);
        XboxController rishi = new XboxController(2);
        while(isOperatorControl()) {
            test.driveLeft(rishi.getLeftY());
            test.driveRight(rishi.getRightY());
        }
        
        //Timer.delay(2);
        //test.driveLeft(0);

    }
    
    /**
     * This function is called once each time the robot enters test mode.
     */
    public void test() {
    
    }
}
