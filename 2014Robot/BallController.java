/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;

//import edu.wpi.first.wpilibj.DigitalSource;
/**
 *
 * @author VCS Robotics
 */
public class BallController {
    private AnalogChannel photoResistor1;
    private DigitalInput photoResistor2;
    private DigitalInput limitSwitch;
    private AnalogChannel ultrasonicSensor;
    private Solenoid catcherArm;
    private Talon armRoller;
    private boolean photoReceived;
    BallController(int rollerMotor, int photoPort1, int photoPort2, int limitPort, int catcherArm, int ultrasonicPort){
        photoResistor1 = new AnalogChannel(photoPort1);
        photoResistor2 = new DigitalInput(photoPort2);
        limitSwitch = new DigitalInput(limitPort);
        this.catcherArm = new Solenoid(catcherArm);
        armRoller = new Talon(rollerMotor);
        ultrasonicSensor = new AnalogChannel(ultrasonicPort);
    }
    public double getPhotoValue(){
        //boolean photoValue = photoResistor1.get();
        return photoResistor1.getAverageVoltage();
    }
    public boolean receivedBall(){
       //return  !photoResistor1.get() && limitSwitch.get();
        return false;
    }
    
    public void catchFromAir(){
        if(receivedBall())
            catcherArm.set(true);
        else
            catcherArm.set(false);
    }
    
    public void pickFromGround(double output){
       //if(!receivedBall()){
           armRoller.set(output);
       //}
    }
    
    public void setCatcherState(boolean a) {
        catcherArm.set(a);
    }
    
    
}

