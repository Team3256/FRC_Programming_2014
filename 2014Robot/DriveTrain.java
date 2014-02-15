/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PIDController;

/**
 *
 * @author VCS Robotics
 */
public class DriveTrain {
    private Talon leftFront, leftRear, rightFront, rightRear;
    private Encoder encL, encR;
    private DoubleSolenoid shifter;
    private PIDController pidL, pidR;
    public DriveTrain(int lf, int lr, int rf, int rr, int el1, int el2, 
            int er1, int er2, int ds1, int ds2) {
        leftFront = new Talon(lf);
        leftRear = new Talon(lr);
        rightFront = new Talon(rf);
        rightRear = new Talon(rr);
        encL = new Encoder(el1, el2);
        encR = new Encoder(er1, er2);
        shifter = new DoubleSolenoid(ds1, ds2);
        encL.start();
        encR.start();
    }
}
