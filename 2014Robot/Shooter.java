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
    public Shooter(int talPort, int potPort, int relPort1, int relPort2,
            int lower1, int lower2, int upper1, int upper2) {
        punch = new Punch(talPort, potPort, relPort1, relPort2);
        tilt = new Tilt(lower1, lower2, upper1, upper2);
    }
    public void setPunchSetpoint(double setpoint) {
        punch.setSetpoint(setpoint);
    }
    public double getPunchSetpoint() {
        return punch.getSetpoint();
    }
    public double getPunchPosition() {
        return punch.getPosition();
    }
    public void releasePunch() {
        punch.release();
    }
    public void setTiltState(String state) {
        tilt.setState(state);
    }
    public String getTiltState() {
        return tilt.getState();
    }
}
