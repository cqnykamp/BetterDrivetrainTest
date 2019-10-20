package frc.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Drivetrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  VictorSP frontLeft = new VictorSP(0);
  VictorSP frontRight = new VictorSP(1);
  VictorSP backLeft = new VictorSP(2);
  VictorSP backRight = new VictorSP(3);

  public void drive(double throttle, double turn) {

  }

  public void drivePath() {
    // argument: path object
  }

  public boolean isFollowingPath() {
    return false;
  }

  public void driveStraightLine(double distance) {

  }

  public void turnInPlace(double angle) {

  }

  public void driveArc() {
    //argument: circle arc object
  }



  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
