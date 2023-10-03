// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auton.framework;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Swerve;

public class AutonDrive extends CommandBase {

  Swerve swerveSubsystem;
  Timer timer;
  double timeInSeconds, xSpeedMPS, ySpeedMPS, thetaSpeedMPS;

  public AutonDrive(Swerve swerveSubsystem, double timeInSeconds, double xSpeedMPS, double ySpeedMPS, double thetaSpeedMPS) {
    this.swerveSubsystem = swerveSubsystem;
    this.timer = new Timer();
    this.timeInSeconds = timeInSeconds;
    addRequirements(swerveSubsystem);
  }

  @Override
  public void initialize() {
    /**Restarts the timer which is used to run the auton cycle. */
    timer.restart();
    swerveSubsystem.stop();
  }

  @Override
  public void execute() {
    
    /**Checks if the given time has passed since the timer has been 
     * reset. If not, run the updateModules() method to move the robot*/
    if (!timer.hasElapsed(timeInSeconds)) swerveSubsystem.updateModules(new ChassisSpeeds(xSpeedMPS, ySpeedMPS, thetaSpeedMPS), 1);
    else swerveSubsystem.stop();
  }

  @Override
  public void end(boolean interrupted) {
    swerveSubsystem.stop();
    timer.stop();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
