package frc.robot;

import java.util.ArrayList;

public class StraightTrajectory {
    ArrayList<Double> timestamp = new ArrayList<Double>();
    ArrayList<Double> accel = new ArrayList<Double>();
    ArrayList<Double> velocity = new ArrayList<Double>();
    ArrayList<Double> position = new ArrayList<Double>();

    public void add(double timestamp, double accel, double velocity, double position) {
        this.timestamp.add(timestamp);
        this.accel.add(accel);
        this.velocity.add(velocity);
        this.position.add(position);
    }

    public int getLength() {
        return timestamp.size();
    }

    public double[] getTimestamp() {
        return arrayListToArray(timestamp);
    }
    public double[] getAccel() {
        return arrayListToArray(accel);
    }
    public double[] getVelocity() {
        return arrayListToArray(velocity);
    }
    public double[] getPosition() {
        return arrayListToArray(position);
    }

    
    public double[][] getVelocityTimeTuple() {
        return zip(getTimestamp(), getVelocity());
    }
    public double[][] getAccelTimeTuple() {
        return zip(getTimestamp(), getAccel());
    }
    public double[][] getPositionTimeTuple() {
        return zip(getTimestamp(), getPosition());
    }



    private double[] arrayListToArray(ArrayList<Double> arrayList) {
        double[] array = new double[arrayList.size()];
        for(int i=0; i< arrayList.size(); i++) {
            array[i] = arrayList.get(i);
        }
        return array;
    }

    private double[][] zip(double[] array1, double[] array2) {
        double[][] zipped = new double[array1.length][2];
        for(int i=0; i<array1.length; i++) {
            zipped[i][0] = array1[i];
            zipped[i][1] = array2[i];
        }
        return zipped;
    }

}