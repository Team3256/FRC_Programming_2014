/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Victor;
/**
 *
 * @author VCS Robotics
 */
public class Feeder {
    
    private Victor roller;
    private Victor wedge1;
    private Victor wedge2;
    
    public Feeder(int roller, int wedge1, int wedge2){
        this.roller = new Victor(roller);
        this.wedge1 = new Victor(wedge1);
        this.wedge2 = new Victor(wedge2);
    }
    
    public void setWedge(double speed){
        wedge1.set(speed);
        wedge2.set(speed);
    }
    public void setRoller(double speed){
        roller.set(speed);
    }
}
