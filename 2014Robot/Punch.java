/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.AnalogChannel;
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
    private AnalogChannel pot;
    private DoubleSolenoid release;
    private PIDController pid;
    
    public Punch(int talPort, int potPort, int relPort1, int relPort2) {
        pullBack = new Talon(talPort);
        pot = new AnalogChannel(potPort);
        release = new DoubleSolenoid(relPort1, relPort2);
        pid = new PIDController(0,0,0,pot, pullBack);
        pid.enable();
    }
    
    public void setSetpoint(double setpoint) {
        pid.setSetpoint(setpoint);
    }
    
    public double getSetpoint() {
        return pid.getSetpoint();
    }
    
    public double getPosition() {
        return (double) pot.pidGet();
    }
    
    public void release() {
        release.set(DoubleSolenoid.Value.kForward);
        Timer.delay(.075);
        release.set(DoubleSolenoid.Value.kReverse);
    }
}
