// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auton;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Auton.framework.TaxiAuton;
// import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
// import frc.robot.commands.Auton.framework.ShootAuton;
// import frc.robot.commands.Auton.framework.TaxiAuton;
import frc.robot.subsystems.Pneumatics;
import frc.robot.subsystems.Swerve;

public class PlaceAndTaxi extends SequentialCommandGroup {
  
  public PlaceAndTaxi(Swerve swerveSubsystem, Pneumatics pneumaticsSubsystem) {
    super (
      new TaxiAuton(swerveSubsystem, pneumaticsSubsystem)
    );
  }
}
