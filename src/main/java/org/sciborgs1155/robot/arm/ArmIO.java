package org.sciborgs1155.robot.arm;

import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.AngularVelocity;

public interface ArmIO extends AutoCloseable {

  /**
   * Sets Voltage of Shoulder
   *
   * @param voltage The Voltage
   */
  void setShoulderVoltage(double voltage);

  /**
   * Sets Voltage of Elbow Joint
   *
   * @param voltage The Voltage
   */
  void setElbowVoltage(double voltage);

  /**
   * @return Position of shoulder in radians
   */
  Angle getShoulderPosition();

  /**
   * @return Position of elbow in radians
   */
  Angle getElbowPosition();

  /**
   * @return velocity of shoulder in radians per second
   */
  AngularVelocity shoulderVelocity();

  /**
   * @return velocity of elbow in radians per sec
   */
  AngularVelocity ElbowVelocity();
}
