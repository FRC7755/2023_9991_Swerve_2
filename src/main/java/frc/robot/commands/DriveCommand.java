// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Swerve;
//import frc.robot.util.AccelerationCurve;

public class DriveCommand extends CommandBase {
  
  Swerve swerveSubsystem;

  Timer timer;

  DoubleSupplier xSpeed, ySpeed, thetaSpeed;

  BooleanSupplier slowMode, resetEncoders, resetGyro, turnAround, xMode;

  public DriveCommand(
    Swerve swerveSubsystem,
    DoubleSupplier xSpeed,
    DoubleSupplier ySpeed,
    DoubleSupplier thetaSpeed,
    BooleanSupplier slowMode,
    BooleanSupplier resetGyro,
    BooleanSupplier turnAround,
    BooleanSupplier xMode
  ) {
    this.swerveSubsystem = swerveSubsystem;
    this.xSpeed = xSpeed;
    this.ySpeed = ySpeed;
    this.thetaSpeed = thetaSpeed;
    this.slowMode = slowMode;
    this.resetGyro = resetGyro;
    this.turnAround = turnAround;
    this.xMode = xMode;

    timer = new Timer();

    addRequirements(swerveSubsystem);
  }

  
  @Override
  public void initialize() {
    swerveSubsystem.stop();
    timer.start();
  }

  double speedMultiplier, oneEighty, turnSpeed;
  boolean wasOneEighty;
  @Override
  public void execute() {

    double transformedXSpeed = xSpeed.getAsDouble();
    double transformedYSpeed = ySpeed.getAsDouble();
    double transformedThetaSpeed = thetaSpeed.getAsDouble();

    double xDeadband = 0.15;
    double yDeadband = 0.15;
    double thetaDeadband = 0.15;

    if (transformedXSpeed < xDeadband) transformedXSpeed = 0;
    if (transformedYSpeed < yDeadband) transformedYSpeed = 0;
    if (transformedThetaSpeed < thetaDeadband) transformedThetaSpeed = 0;

    this.speedMultiplier = slowMode.getAsBoolean() ? 0.25 : 1;

    if (turnAround.getAsBoolean() && !wasOneEighty) {

      timer.reset();
      oneEighty = 1;
      wasOneEighty = true;

    } else if (oneEighty != 0 && timer.hasElapsed(1)) {

      oneEighty = 0;
      wasOneEighty = false;

    }

    if (resetGyro.getAsBoolean()) swerveSubsystem.resetGyro();

    if (xMode.getAsBoolean()) swerveSubsystem.xMode();
    else if (
      Math.abs(transformedXSpeed) > .15 ||
      Math.abs(transformedYSpeed) > .15 ||
      Math.abs(transformedThetaSpeed) > .15
    ) {

      this.turnSpeed = transformedThetaSpeed;

      swerveSubsystem.updateModules(
        ChassisSpeeds.fromFieldRelativeSpeeds(
          transformedXSpeed,
          transformedYSpeed,
          this.turnSpeed + oneEighty,
          swerveSubsystem.getGyroRotation()
        ),
        speedMultiplier
      );

    } else {

      swerveSubsystem.stop();
      swerveSubsystem.updateModules(
        ChassisSpeeds.fromFieldRelativeSpeeds(
          0,
          0,
          oneEighty, 
          swerveSubsystem.getGyroRotation()
        ),
        1
      );

  }

}

  
  @Override
  public void end(boolean interrupted) {
    swerveSubsystem.stop();
  }

  
  @Override
  public boolean isFinished() {
    return false;
  }
}
