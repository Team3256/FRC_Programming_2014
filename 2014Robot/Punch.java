/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author VCS Robotics
 */
public class Punch {
    private Talon pullBack;
    private DoubleSolenoid release;
    private DigitalInput setPoint;
    
    public Punch(int talPort, int relPort1, int relPort2, int setPointPort) {
        pullBack = new Talon(talPort);
        release = new DoubleSolenoid(relPort1, relPort2);
        setPoint = new DigitalInput(setPointPort);
    }
    
    public boolean ifLoaded(){
        return setPoint.get();    
    }
    public void runLoader(double output){
        pullBack.set(output);
    }
    public double getSetpoint() {
        return 0.0;
    }
    
    public double getPosition() {
        return 0.0;
    }
    
    public void release(double delay) {
        release.set(DoubleSolenoid.Value.kForward);
        Timer.delay(delay);
        release.set(DoubleSolenoid.Value.kReverse);
    }
}
