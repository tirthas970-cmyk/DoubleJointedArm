package org.sciborgs1155.robot.arm;

import static edu.wpi.first.units.Units.Amps;
import static edu.wpi.first.units.Units.Radians;
import static edu.wpi.first.units.Units.RadiansPerSecond;
import static org.sciborgs1155.robot.Ports.Arm.ELBOW_MOTOR;
import static org.sciborgs1155.robot.Ports.Arm.SHOULDER_MOTOR;
import static org.sciborgs1155.robot.arm.ArmConstants.ELBOW_GEAR_RATIO;
import static org.sciborgs1155.robot.arm.ArmConstants.SHOULDER_GEAR_RATIO;
import static org.sciborgs1155.robot.arm.ArmConstants.STATOR_LIMIT;

import com.revrobotics.PersistMode;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.ResetMode;
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
  private final SparkFlexConfig shoulderConfig;
  private final SparkFlexConfig elbowConfig;
  private final RelativeEncoder shoulderEncoder;
  private final RelativeEncoder elbowEncoder;

  /** Configs and creates RealArm */
  public RealArm() {
    shoulderMotor = new SparkFlex(SHOULDER_MOTOR, MotorType.kBrushless);
    elbowMotor = new SparkFlex(ELBOW_MOTOR, MotorType.kBrushless);
    shoulderEncoder = shoulderMotor.getEncoder();
    elbowEncoder = elbowMotor.getEncoder();

    shoulderConfig = new SparkFlexConfig();
    shoulderConfig.smartCurrentLimit((int) STATOR_LIMIT.in(Amps)).idleMode(IdleMode.kBrake);
    shoulderConfig
        .encoder
        .positionConversionFactor((2 * Math.PI) / SHOULDER_GEAR_RATIO)
        .velocityConversionFactor((2 * Math.PI / 60.0) / SHOULDER_GEAR_RATIO);

    elbowConfig = new SparkFlexConfig();
    elbowConfig.smartCurrentLimit((int) STATOR_LIMIT.in(Amps)).idleMode(IdleMode.kBrake);
    elbowConfig
        .encoder
        .positionConversionFactor((2 * Math.PI) / ELBOW_GEAR_RATIO)
        .velocityConversionFactor((2 * Math.PI / 60.0) / ELBOW_GEAR_RATIO);

    shoulderMotor.configure(
        shoulderConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    elbowMotor.configure(
        elbowConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

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
    return Radians.of(shoulderEncoder.getPosition());
  }

  @Override
  public Angle getElbowPosition() {
    return Radians.of(elbowEncoder.getPosition());
  }

  @Override
  public AngularVelocity shoulderVelocity() {
    return RadiansPerSecond.of(shoulderEncoder.getVelocity());
  }

  @Override
  public AngularVelocity elbowVelocity() {
    return RadiansPerSecond.of(elbowEncoder.getVelocity());
  }

  @Override
  public void close() throws Exception {
    shoulderMotor.close();
    elbowMotor.close();
  }
}
