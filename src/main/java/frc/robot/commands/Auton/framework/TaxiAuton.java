// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auton.framework;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Pneumatics;
import frc.robot.subsystems.Swerve;

public class TaxiAuton extends CommandBase {
  
  Pneumatics pneumaticsSubsystem;
  Swerve swerveSubsystem;
  Timer timer;

  public TaxiAuton(Swerve swerveSubsystem, Pneumatics pneumaticsSubsystem) {
    this.swerveSubsystem = swerveSubsystem;
    this.timer = new Timer();
    this.pneumaticsSubsystem = pneumaticsSubsystem;
    addRequirements(swerveSubsystem, pneumaticsSubsystem);
  }

  @Override
  public void initialize() {
    /**Restarts the timer which is used to run the auton cycle. */
    timer.restart();
    swerveSubsystem.stop();
    pneumaticsSubsystem.enableSubsystem();
  }

  @Override
  public void execute() {
    
    /**Checks if 3 seconds have passed since the timer has been 
     * reset. If not, run the updateModules() method to move the robot*/
    if (!timer.hasElapsed(2.75)) swerveSubsystem.updateModules(new ChassisSpeeds(.5, 0, 0), 1);
    else swerveSubsystem.stop(); //TODO: Determine timing for taxi auton
  }

  @Override
  public void end(boolean interrupted) {
    swerveSubsystem.stop();
  }

  @Override
  public boolean isFinished() {
    return timer.hasElapsed(3.5);
  }
}
