// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auton.framework;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Pneumatics;

public class IntakeAuton extends CommandBase {

  Timer timer;

  Pneumatics pneumaticsSubsystem;
  double timeInSeconds;
  
  public IntakeAuton(Pneumatics pneumaticsSubsystem, double timeInSeconds) {
    this.timer = new Timer();
    this.pneumaticsSubsystem = pneumaticsSubsystem;
    this.timeInSeconds = timeInSeconds;
    addRequirements(pneumaticsSubsystem);
  }

  @Override
  public void initialize() {
    pneumaticsSubsystem.changeState(Value.kReverse);
    timer.restart();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (!timer.hasElapsed(timeInSeconds)) {
      pneumaticsSubsystem.changeState(Value.kForward);
    }
    else {
      pneumaticsSubsystem.changeState(Value.kReverse);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    timer.stop();
    pneumaticsSubsystem.changeState(Value.kReverse);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
