/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DoubleSolenoid;
//import edu.wpi.first.wpilibj.Solenoid;


/**
 *
 * @author VCS Robotics
 */
public class Hood {
    
    private DoubleSolenoid hood;
    
    public Hood(int port){
        hood = new DoubleSolenoid(3, 4);
    }
    public void setHood(DoubleSolenoid.Value value){
        hood.set(value);
    }
    public void toggleHood(){
        
        if(hood.get()==DoubleSolenoid.Value.kForward){
            hood.set(DoubleSolenoid.Value.kReverse);
        }else{
            hood.set(DoubleSolenoid.Value.kForward);
        }
    }
}
