/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;

//import edu.wpi.first.wpilibj.DigitalSource;
/**
 *
 * @author VCS Robotics
 */
public class BallController {
    private DigitalInput photoResistor;
    private DigitalInput limitSwitch;
    private Solenoid catcherArm;
    private Talon armRoller;
    private boolean photoReceived;
    BallController(int rollerMotor, int photoPort, int limitPort, int catcherArm){
        photoResistor = new DigitalInput(photoPort);
        limitSwitch = new DigitalInput(limitPort);
        this.catcherArm = new Solenoid(catcherArm);
        armRoller = new Talon(rollerMotor);
    }
    
    public boolean receivedBall(){
        
        return  !photoResistor.get() && limitSwitch.get();
    }
    
    public void catchFromAir(){
        if(receivedBall() )
            catcherArm.set(true);
        else
            catcherArm.set(false);
            
    }
    
    public void pickFromGround(){
       if(!receivedBall()){
           armRoller.set(1);
       }
    }
    
    
}

