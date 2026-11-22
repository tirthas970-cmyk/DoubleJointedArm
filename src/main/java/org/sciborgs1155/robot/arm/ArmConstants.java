package org.sciborgs1155.robot.arm;

import static edu.wpi.first.units.Units.Amps;

import edu.wpi.first.units.measure.Current;

public class ArmConstants {

  public static final Current STATOR_LIMIT = Amps.of(30);

  public static final double SHOULDER_GEAR_RATIO = 10;
  public static final double ELBOW_GEAR_RATIO = 10;

  private ArmConstants() {}
}
