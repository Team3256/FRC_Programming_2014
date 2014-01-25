/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.networktables2.type.NumberArray;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.TableKeyNotDefinedException;
//import edu.wpi.first.wpilibj.templates.*;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotTemplate extends SimpleRobot {
    DriveTrain test;
    NetworkTable server;
    Elevator maxwell;
    Feeder thomas;
    Shooter newman;
    PID akaash;
    Compressor tanay;
    Hood hood;
    private int ids;
    
    public RobotTemplate() {
        test = new DriveTrain(1,2,3,4);
        server = NetworkTable.getTable("SmartDashboard");
        thomas = new Feeder(8,7, 10);
        maxwell = new Elevator(9);
        newman = new Shooter(5);
        akaash = new PID(0.5, 0.0, 0.005, 0.0, 0.0);
        tanay = new Compressor(1, 1);
       //tanay.start();
        hood = new Hood(1);
        SmartDashboard.putNumber("1", 1.0);
        
    }
    /**
     * This function is called once each time the robot enters autonomous mode.
     */
    public void autonomous() {
        /* try {
               final NumberArray t = new NumberArray();
               server.retrieveValue("ids",t);
               SmartDashboard.putNumber("Works", (double) t.size());
               ids = t.size();
           } catch (TableKeyNotDefinedException exp) {}
        if(ids >1){
            test.driveLeft(-0.5);
            test.driveRight(0.5);
            Timer.delay(4);
            test.driveLeft(0);
            test.driveRight(0);
        }else{
            test.driveLeft(0);
            test.driveRight(0);
        }*/
        while(isAutonomous()){
           // hood.setHood(DoubleSolenoid.Value.kOff);
            hood.setHood(DoubleSolenoid.Value.kReverse);
            Timer.delay(2);
            hood.setHood(DoubleSolenoid.Value.kForward);
            //hood.setHood(DoubleSolenoid.Value.kReverse);
        }
    }

    /**
     * This function is called once each time the robot enters operator control.
     */
    public void operatorControl() {
        XboxController rishi = new XboxController(2);
        boolean BoyzInDaHood = true;
        while(isOperatorControl()) {
           
            if(rishi.getButtonX()){
                thomas.setWedge(-0.5);
            }
            else if(rishi.getButtonB()){
                thomas.setWedge(0.5);
            }
            else{
                thomas.setWedge(0);
            }
            
            if(rishi.getButtonA()){
                thomas.setRoller(1);
                maxwell.setElevator(-1);
            }
            else if(rishi.getButtonY()) {
                maxwell.setElevator(1);
                thomas.setRoller(-1);
            }
            else if(rishi.getLeftTrigger()) {
                thomas.setRoller(1);
            }
            else if(rishi.getButtonLB()) {
                thomas.setRoller(-1);
            }
            else {
                thomas.setRoller(0);
                maxwell.setElevator(0);
            }
            
            if(rishi.getRightTrigger()){
                
                newman.setShooter(3000);
            }
            else{
                newman.setShooter(0);
            }
           
            if(rishi.getButtonRB() && BoyzInDaHood){
                hood.toggleHood();
                BoyzInDaHood = false;
            }
            else if (!rishi.getButtonRB())
                BoyzInDaHood = true;
            
            test.arcadeDrive(rishi.getLeftY(), rishi.getRightX(), true);
            System.out.println("turn " + rishi.getRightX());
           
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
