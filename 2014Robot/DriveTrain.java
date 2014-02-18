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
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;

/**
 *
 * @author VCS Robotics
 */
public class DriveTrain {
    private Talon leftFront, leftRear, rightFront, rightRear;
    private Encoder encL, encR;
    private DoubleSolenoid shifter;
    private PIDController pidL, pidR;
    private double feet = 64.0/ 1045.0 / 14.0;
    private double angle = 1.97 / 90.0;
    private class LeftOutput implements PIDOutput {
        public void pidWrite(double d) {
            leftFront.set(d);
            leftRear.set(d);
        }
    }
    private class RightOutput implements PIDOutput {
        public void pidWrite(double d) {
            rightFront.set(d);
            rightRear.set(d);
        }
    }
    
    private class LeftSource implements PIDSource {
        public double pidGet() {
            return encL.getDistance();
        }
    }
    
    private class RightSource implements PIDSource {
        public double pidGet() {
            return encR.getDistance();
        }
    }
    
    public DriveTrain(int lf, int lr, int rf, int rr, int el1, int el2, 
            int er1, int er2, int ds1, int ds2) {
        leftFront = new Talon(lf);
        leftRear = new Talon(lr);
        rightFront = new Talon(rf);
        rightRear = new Talon(rr);
        encL = new Encoder(el1, el2);
        encR = new Encoder(er1, er2, true);
        encL.setDistancePerPulse(feet);
        encR.setDistancePerPulse(feet);
        shifter = new DoubleSolenoid(ds1, ds2);
        shifter.set(DoubleSolenoid.Value.kReverse);
        encL.start();
        encR.start();
        pidL = new PIDController(0.25,0,0.17, new LeftSource(), new LeftOutput());
        pidR = new PIDController(0.25,0,0.17, new RightSource(), new RightOutput());
    }
    
    private void setLeftRight(double left, double right) {
        if (pidL.isEnable())
            pidL.disable();
        if (pidR.isEnable())
            pidR.disable();
        left = (left > 1.0) ? 1.0 : (left < -1.0) ? -1.0 : left;
        right = (right > 1.0) ? 1.0 : (right < -1.0) ? -1.0 : right;
        leftFront.set(left);
        leftRear.set(left);
        rightFront.set(right);
        rightRear.set(right);
    }
    
    public void arcadeDrive(double power, double turn) {
        double leftMotorSpeed;
        double rightMotorSpeed;

        power = (power > 1.0) ? 1.0 : (power < -1.0) ? -1.0 : power;
        turn = (turn > 1.0) ? 1.0 : (turn < -1.0) ? -1.0 : turn;

        if (power >= 0.0) {
            power = (power * power);
        } else {
            power = -(power * power);
        }
        if (turn >= 0.0) {
            turn = (turn * turn);
        } else {
            turn = -(turn * turn);
        }
        if (power > 0.0) {
            if (turn > 0.0) {
                leftMotorSpeed = power - turn;
                rightMotorSpeed = Math.max(power, turn);
            } else {
                leftMotorSpeed = Math.max(power, -turn);
                rightMotorSpeed = power + turn;
            }
        } else {
            if (turn > 0.0) {
                leftMotorSpeed = -Math.max(-power, turn);
                rightMotorSpeed = power + turn;
            } else {
                leftMotorSpeed = power - turn;
                rightMotorSpeed = -Math.max(-power, -turn);
            }
        }
        setLeftRight(leftMotorSpeed, rightMotorSpeed);
    }
    public void tankDrive(double left, double right) {
        setLeftRight(left, right);
    }
    public void setFeetSetpoint(double setpoint) {
        if (!pidL.isEnable())
            pidL.enable();
        if (!pidR.isEnable())
            pidR.enable();
        encL.reset();
        encR.reset();
        pidL.setSetpoint(setpoint);
        pidR.setSetpoint(setpoint);
    }
    public void setAngleSetpoint(double angle) {
        if (!pidL.isEnable())
            pidL.enable();
        if (!pidR.isEnable())
            pidR.enable();
        encL.reset();
        encR.reset();
        pidL.setSetpoint(this.angle * angle);
        pidR.setSetpoint(-this.angle * angle);
    }
    public void setShifterState(boolean shift) {
        if (shift) {
            shifter.set(DoubleSolenoid.Value.kForward);
        }
        else
            shifter.set(DoubleSolenoid.Value.kReverse);
    }
}
