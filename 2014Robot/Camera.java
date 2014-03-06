/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.networktables2.type.NumberArray;

/**
 *
 * @author VCS Robotics
 */
public class Camera {
    private NetworkTable server;
    private int ids;
    public Camera() {
        server = NetworkTable.getTable("SmartDashboard");
        ids = 0;
    }
    public boolean isHot() {
        final NumberArray t = new NumberArray();
        server.retrieveValue("ids",t);
        return (ids == 2);
    }
}
