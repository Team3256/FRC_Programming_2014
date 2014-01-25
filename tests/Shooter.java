/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Victor;
import java.util.TimerTask;
/**
 *
 * @author VCS Robotics
 */
public class Shooter {
    private Victor shooter;
    private Encoder shooterEncoder = new  Encoder(2, 12);
    private PID akaash = new PID(0.002, 0.0, 0.0, 0.0, 0.0);
    java.util.Timer controlLoop;
    double current, setpoint;
    
    private class PIDTask extends TimerTask {
        private Shooter shooter;
        
        public PIDTask(Shooter s) {
            if (s == null) {
                throw new NullPointerException("Given Shooter was null");
            }
            shooter = s;
        }
        
        public void run() {
            shooter.taskRunner();
        }
    }
    
    public Shooter(int shooter){
    
        this.shooter = new Victor(shooter);
        shooterEncoder.start();
        controlLoop = new java.util.Timer();
        
        controlLoop.schedule(new PIDTask(this),0L, (long) (.05 * 1000));
    }
    
    public int getShooterEncoder(){
      return shooterEncoder.getRaw();
  }
    
    public void setShooter(double setpoint){
        this.setpoint = setpoint;
    }
    
    private void taskRunner() {
        current = shooterEncoder.getRaw();
        double output = akaash.calc(setpoint, current);
        if (output > 1)
            output = 1;
        else if (output < 0)
            output = 0;
        if (setpoint == 0)
        {
            akaash.resetError();
            output = 0;
        }
        System.out.println("setpoint " + setpoint + " current " + current + " output " + output);
        shooter.set(output);
        shooterEncoder.reset();
    }
}
