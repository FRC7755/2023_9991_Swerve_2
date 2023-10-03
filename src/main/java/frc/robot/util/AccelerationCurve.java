// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.util;

/** Add your docs here. */
public class AccelerationCurve {
    
    public AccelerationCurve () {
        
    }

    static int isNegative;
    public static double apply (double input) {
        if (input < 0) isNegative = -1;
        else isNegative = 1;
        input = Math.abs(input);
        return Math.sqrt(input) * isNegative;
    }
}
