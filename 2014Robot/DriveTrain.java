/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStationLCD;
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
    private double angle = 1.68 / 90.0;
    private class LeftOutput implements PIDOutput {
        public void pidWrite(double d) {
            leftFront.set(-d);
            leftRear.set(-d);
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
        System.out.println(feet);
        encL.setDistancePerPulse(feet);
        encR.setDistancePerPulse(feet);
        shifter = new DoubleSolenoid(ds1, ds2);
        shifter.set(DoubleSolenoid.Value.kReverse);
        encL.start();
        encR.start();
        pidL = new PIDController(0.6,0.05,0.2, new LeftSource(), new LeftOutput());
        pidR = new PIDController(0.6,0.05,0.2, new RightSource(), new RightOutput());
        pidL.disable();
        pidR.disable();
    }
    
    private void setLeftRight(double left, double right) {
        if (pidL.isEnable())
            pidL.disable();
        if (pidR.isEnable())
            pidR.disable();
        left = (left > 1.0) ? 1.0 : (left < -1.0) ? -1.0 : left;
        right = (right > 1.0) ? 1.0 : (right < -1.0) ? -1.0 : right;
        leftFront.set(-left);
        leftRear.set(-left);
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
        if (setpoint < encL.getDistance()-.05 && setpoint < encR.getDistance()-.05) {
            arcadeDrive(-.6,.1);
        } else if (setpoint > encR.getDistance()+.05 && setpoint > encL.getDistance()+.05) {
            arcadeDrive(.6,-.1);
        } else
            arcadeDrive(0,0);
    }
    public void setAngleSetpoint(double angle) {
        if (angle * this.angle< encL.getDistance()-.05 && angle * this.angle< encR.getDistance()-.05) {
            arcadeDrive(0, -0.8);
        } else if (angle * this.angle> encL.getDistance()-.05 && angle * this.angle> encR.getDistance()-.05) {
            arcadeDrive(0,.8);
        } else
            arcadeDrive(0,0);
    }
    public void setShifterState(boolean shift) { 
        if (shift) {
            shifter.set(DoubleSolenoid.Value.kForward);
        }
        else
            shifter.set(DoubleSolenoid.Value.kReverse);
    }
    public void reset() {
        encL.reset();
        encR.reset();
    }
    public void log() {
        DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser2,1,"" + encL.getDistance());
        DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser3,1,"" + encR.getDistance());
        DriverStationLCD.getInstance().updateLCD();
        System.out.println("left encoder: "+ encL.getDistance());
        System.out.println("right encoder: "+ encR.getDistance());
    }
    public double get() {
        return leftFront.get() + leftRear.get() + rightFront.get() + rightRear.get();
    }
}
