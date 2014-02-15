/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DoubleSolenoid;

/**
 *
 * @author VCS Robotics
 */
public class Tilt {
    private DoubleSolenoid lowerActuator;
    private DoubleSolenoid upperActuator;
    private String state;
    public Tilt(int lower1, int lower2, int upper1, int upper2) {
        lowerActuator = new DoubleSolenoid(lower1, lower2);
        upperActuator = new DoubleSolenoid(upper1, upper2);
        state = "kUp";
    }
    
    public String getState() {
        return state;
    }
    
    public void setState(String state) {
        this.state = state;
        if (state.equals("kUp")) {
            lowerActuator.set(DoubleSolenoid.Value.kForward);
            upperActuator.set(DoubleSolenoid.Value.kForward);
        } else if (state.equals("kMiddle")) {
            lowerActuator.set(DoubleSolenoid.Value.kForward);
            upperActuator.set(DoubleSolenoid.Value.kReverse);
        } else {
            lowerActuator.set(DoubleSolenoid.Value.kReverse);
            upperActuator.set(DoubleSolenoid.Value.kReverse);
        }
    }
    
    
}
