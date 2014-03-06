/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;
import edu.wpi.first.wpilibj.templates.Punch;
import edu.wpi.first.wpilibj.templates.Tilt;
/**
 *
 * @author VCS Robotics
 */
public class Shooter {
    private Punch punch;
    private Tilt tilt;
    public Shooter(int talPort, int relPort1, int relPort2,
          int lower1, int upper1, int upper2, int setPointPort) {
        
        punch = new Punch(talPort, relPort1, relPort2, setPointPort);
        tilt = new Tilt(lower1, upper1, upper2);
    }
    public void loadShooter(double output) {
       // if(!punch.ifLoaded())
            punch.runLoader(output);
        //else
         //   punch.runLoader(-0.05);
    }
    public double getPunchSetpoint() {
        return punch.getSetpoint();
    }
    public double getPunchPosition() {
        return punch.getPosition();
    }
    
    public boolean getPunchSwitch() {
        return punch.ifLoaded();
    }
    
    public void releasePunch(double delay) {
        punch.release(delay);
    }
    public void setTiltState(String state) {
        tilt.setState(state);
    }
    public String getTiltState() {
        return tilt.getState();
    }
}
