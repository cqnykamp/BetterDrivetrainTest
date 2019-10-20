package frc.robot;

public class MotorParameters {
    double maxVoltage;
    double stallTorque;
    double peakCurrent;
    double freeSpeed; //rotations per second
    double freeCurrent;

    double radius; //meters
    double frictionForce; //should be negative
    double mass;
    double gearReduction;

    double motorNum;

    public double getResistance() {
        return maxVoltage / peakCurrent;
    }

    public double getKt() {
        return stallTorque / peakCurrent;
    }

    public double getKv() {
        return freeSpeed / (maxVoltage - freeCurrent * getResistance());
    }

    // public MotorParameters(double maxVoltage, double stallTorque, double peackCurrent,
    //                         double freeSpeed, double freeCurrent,
    //                         double radius, double frictionForce, double mass, double gearReduction,
    //                         double motorNum
    //                         ) {
    //     this.maxVoltage = maxVoltage;
    //     this.stallTorque = stallTorque;

    //     System.out.println();
    // }
}