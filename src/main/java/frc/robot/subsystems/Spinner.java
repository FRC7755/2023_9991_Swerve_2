// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Spinner extends SubsystemBase {
  
  CANSparkMax leftSpinnerMotor, rightSpinnerMotor;

  public Spinner(int leftMotorID, int rightMotorID) {
    leftSpinnerMotor = new CANSparkMax(leftMotorID, MotorType.kBrushless);
    rightSpinnerMotor = new CANSparkMax(rightMotorID, MotorType.kBrushless);
    leftSpinnerMotor.setIdleMode(IdleMode.kBrake);
    rightSpinnerMotor.setIdleMode(IdleMode.kBrake);
  }

  public enum Node {
    LOW (-.5, -.5),
    MID (-.97, -1),
    HIGH (-.95, -1), 
    INTAKE (.5, .5),
    STOP (0, 0);

    double upperSpeed, lowerSpeed;

    Node (double upperSpeed, double lowerSpeed) {
      this.upperSpeed = upperSpeed;
      this. lowerSpeed = lowerSpeed;
    }
  }

  double reverseMultiplier;
  public void runSpinner (Node node) {
    leftSpinnerMotor.set(node.lowerSpeed); //TODO: Run testing to determine optimal speed
    rightSpinnerMotor.set(node.upperSpeed);
  }

  public void stop () {
    leftSpinnerMotor.stopMotor();
    rightSpinnerMotor.stopMotor();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
