/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;

/**
 *
 * @author VCS Robotics
 */
public class Tilt {
    private Solenoid lowerActuator;
    private DoubleSolenoid upperActuator;
    private String state;
    public Tilt(int lower1, int upper1, int upper2) {
        lowerActuator = new Solenoid(lower1);
        upperActuator = new DoubleSolenoid(upper1, upper2);
        setState("kUp");
    }
    
    public String getState() {
        return state;
    }
    
    public void setState(String state) {
        this.state = state;
        if (state.equals("kUp")) {
            lowerActuator.set(false);
            upperActuator.set(DoubleSolenoid.Value.kForward);
        } else if (state.equals("kMiddle")) {
            lowerActuator.set(true);
            upperActuator.set(DoubleSolenoid.Value.kForward);
        } else {
            lowerActuator.set(false);
            upperActuator.set(DoubleSolenoid.Value.kReverse);
        }
    }
    
    
}
