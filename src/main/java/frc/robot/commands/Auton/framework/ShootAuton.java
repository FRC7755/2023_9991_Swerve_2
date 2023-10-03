// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auton.framework;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Spinner;
import frc.robot.subsystems.Spinner.Node;

public class ShootAuton extends CommandBase {

  Spinner spinnerSubsystem;
  Timer timer;  
  
  public ShootAuton(Spinner spinnerSubsystem) {
    this.spinnerSubsystem = spinnerSubsystem;
    timer = new Timer();
    addRequirements(spinnerSubsystem);
    System.out.println("ShootAuton is constructed");
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.restart();
    // spinnerSubsystem.stop();
    System.out.println("Code Initialized");
  }

  Node node;
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    System.out.println("Code executes");

    if (!timer.hasElapsed(1)) {
      System.out.println("If is working");
      node = Node.MID;
    }

    else {
      node = Node.STOP;
      System.out.println("Else is working");
    }

    spinnerSubsystem.runSpinner(node);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    spinnerSubsystem.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return timer.hasElapsed(1.5);
  }
}
