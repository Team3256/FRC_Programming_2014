/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Joystick;
/**
 *
 * @author VCS Robotics
 */
public class XboxController {
    private Joystick stick;
    
    public XboxController(int port) {
        stick = new Joystick(port);
    }
    
    public boolean getButtonA() {
        return stick.getRawButton(1);
    }
    public boolean getButtonB() {
        return stick.getRawButton(2);
    }
    public boolean getButtonX() {
        return stick.getRawButton(3);
    }
    public boolean getButtonY() {
        return stick.getRawButton(4);
    }
    public boolean getButtonLB() {
        return stick.getRawButton(5); 
    }
    public boolean getButtonRB() {
        return stick.getRawButton(6);
    }
    public boolean getButtonBack() {
        return stick.getRawButton(7);        
    }
    public boolean getButtonStart(){
        return stick.getRawButton(8);        
    }
    public boolean getButtonLeftStick() {
        return stick.getRawButton(9);
    }
    public boolean getButtonRightStick() {
        return stick.getRawButton(10);
    }
    
    public double getLeftY() {
        return stick.getRawAxis(2);
    }
    
    public double getLeftX() {
        return stick.getRawAxis(1);
    }
    
    public double getRightY() {
        return stick.getRawAxis(5);
    }
    
    public double getRightX() {
        return stick.getRawAxis(4);
    }
    public boolean getLeftTrigger() {
        return stick.getZ() > .5;
    }
    public boolean getRightTrigger() {
        return stick.getZ() < -.5;
    }
}