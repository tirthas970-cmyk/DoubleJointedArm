package org.sciborgs1155.robot.arm;

import static edu.wpi.first.units.Units.Amps;
import static org.sciborgs1155.robot.Ports.Arm.ELBOW_MOTOR;
import static org.sciborgs1155.robot.Ports.Arm.SHOULDER_MOTOR;
import static org.sciborgs1155.robot.arm.ArmConstants.STATOR_LIMIT;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkFlexConfig;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.AngularVelocity;
import org.sciborgs1155.lib.FaultLogger;

public class RealArm implements ArmIO {
  private final SparkFlex shoulderMotor;
  private final SparkFlex elbowMotor;
  private final SparkFlexConfig config;
  private final RelativeEncoder shoulderEncoder;
  private final RelativeEncoder elbowEncoder;

  public RealArm() {
    shoulderMotor = new SparkFlex(SHOULDER_MOTOR, MotorType.kBrushless);
    elbowMotor = new SparkFlex(ELBOW_MOTOR, MotorType.kBrushless);
    shoulderEncoder = shoulderMotor.getEncoder();
    elbowEncoder = elbowMotor.getEncoder();

    config = new SparkFlexConfig();

    config.smartCurrentLimit((int) STATOR_LIMIT.in(Amps));
    config.idleMode(IdleMode.kBrake);

    FaultLogger.register(shoulderMotor);
    FaultLogger.register(elbowMotor);
  }

  @Override
  public void setShoulderVoltage(double voltage) {
    shoulderMotor.setVoltage(voltage);
  }

  @Override
  public void setElbowVoltage(double voltage) {
    elbowMotor.setVoltage(voltage);
  }

  @Override
  public Angle getShoulderPosition() {
    throw new UnsupportedOperationException("Unimplemented method 'getShoulderPosition'");
  }

  @Override
  public Angle getElbowPosition() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getElbowPosition'");
  }

  @Override
  public AngularVelocity shoulderVelocity() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'shoulderVelocity'");
  }

  @Override
  public AngularVelocity ElbowVelocity() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'ElbowVelocity'");
  }

  @Override
  public void close() throws Exception {
    shoulderMotor.close();
    elbowMotor.close();
  }
}
