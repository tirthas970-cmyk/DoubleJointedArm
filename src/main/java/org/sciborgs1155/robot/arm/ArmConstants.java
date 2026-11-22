package org.sciborgs1155.robot.arm;

import static edu.wpi.first.units.Units.Amps;

import edu.wpi.first.units.measure.Current;

public class ArmConstants {

  public static final Current STATOR_LIMIT = Amps.of(30);

  private ArmConstants() {}
}
