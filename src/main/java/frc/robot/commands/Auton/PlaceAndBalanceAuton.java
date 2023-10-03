// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auton;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Auton.framework.BalanceAuton;
import frc.robot.subsystems.Pneumatics;
import frc.robot.subsystems.Swerve;

public class PlaceAndBalanceAuton extends SequentialCommandGroup {

  public PlaceAndBalanceAuton(Swerve swerveSubsystem, Pneumatics pneumaticsSubsystem) {
    
    super (
      new BalanceAuton(swerveSubsystem, pneumaticsSubsystem)
    );
  }

}
