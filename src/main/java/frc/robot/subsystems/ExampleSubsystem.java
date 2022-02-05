// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ExampleSubsystem extends SubsystemBase {

    private CANSparkMax a_frontLeft, a_frontRight, a_backLeft, a_backRight; 
    private WPI_TalonFX frontLeft, frontRight, backLeft, backRight; 
    
    SpeedControllerGroup leftMotors, rightMotors;
    SpeedControllerGroup leftAngle, rightAngle;  
    DifferentialDrive driveMotors; 
    
    

  /** Creates a new ExampleSubsystem. */
  public ExampleSubsystem() {

      frontLeft = new WPI_TalonFX(4);  
      frontLeft.setNeutralMode(NeutralMode.Brake);
      frontLeft.configClosedloopRamp(1);
      frontLeft.configOpenloopRamp(1);

      frontRight = new WPI_TalonFX(1); 
      frontRight.setNeutralMode(NeutralMode.Brake);
      frontRight.configClosedloopRamp(1);
      frontRight.configOpenloopRamp(1);

      backLeft = new WPI_TalonFX(2);
      backLeft.setNeutralMode(NeutralMode.Brake);
      backLeft.configClosedloopRamp(1);
      backLeft.configOpenloopRamp(1);
      //deez
      
      backRight = new WPI_TalonFX(0);
      backRight.setNeutralMode(NeutralMode.Brake);
      backRight.configClosedloopRamp(1);
      backRight.configOpenloopRamp(1);
      
      leftMotors = new SpeedControllerGroup(frontLeft, backLeft); 
      rightMotors = new SpeedControllerGroup(frontRight, backRight); 

      driveMotors = new DifferentialDrive(leftMotors, rightMotors); 

      a_frontLeft = new CANSparkMax(6, MotorType.kBrushless);
      a_frontLeft.setIdleMode(IdleMode.kBrake); 

      a_frontRight = new CANSparkMax(1, MotorType.kBrushless);
      a_frontRight.setIdleMode(IdleMode.kBrake); 

      a_backLeft = new CANSparkMax(5, MotorType.kBrushless);
      a_backLeft.setIdleMode(IdleMode.kBrake); 

      a_backRight = new CANSparkMax(2, MotorType.kBrushless);
      a_backRight.setIdleMode(IdleMode.kBrake); 

      leftAngle = new SpeedControllerGroup(a_frontLeft, a_backLeft); 
      rightAngle = new SpeedControllerGroup(a_frontRight, a_backRight); 
      

  }

  public void curvatureDrive(XboxController controller, double speed){
    driveMotors.curvatureDrive(Math.abs(controller.getRawAxis(0))*speed , controller.getRawAxis(4)*speed , true);

    leftAngle.set(controller.getRawAxis(1)*speed);
    rightAngle.set(controller.getRawAxis(1)*speed);

  }

  @Override
  public void periodic() {
    
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
