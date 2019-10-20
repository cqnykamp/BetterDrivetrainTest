package frc.robot;

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class MotorSim {

    MotorParameters params;

    StraightTrajectory trajectory;

    double deltaTime;
    double time = 0;
    double velocity = 0; //linear, m/s
    double position = 0; //linear, m
    double accel = 0; // linear, m/s^2

    public MotorSim(MotorParameters motorParam, double deltaTime) {
        this.params = motorParam;
        this.deltaTime = deltaTime;

        trajectory = new StraightTrajectory();
    }

    public void run(double voltage) {
        // two constants, just to make things neater (no real meaning)
        double k1 = params.gearReduction / (params.radius * params.getKv());
        double k2 = params.gearReduction * params.getKt() / (params.gearReduction * params.radius);

        double motorForce = (voltage - k1 * velocity) * k2;
        double totalForce = motorForce + params.frictionForce;

        time += deltaTime;
        accel = totalForce / params.mass;
        velocity += accel * deltaTime;
        position += velocity * deltaTime;


        System.out.printf("Accel: %f Vel: %f Pos: %f", accel, velocity, position);
        System.out.println();
        trajectory.add(time, accel, velocity, position);
    }

    public static void main(String[] args) throws IOException {
        MotorParameters motorParams = new MotorParameters();
        motorParams.maxVoltage = 12;
        motorParams.stallTorque = 2.41;
        motorParams.peakCurrent = 131;
        motorParams.freeSpeed = 5330 / 60;
        motorParams.freeCurrent = 2.7;

        motorParams.radius = 1;
        motorParams.mass = 1;
        motorParams.gearReduction = 1;
        motorParams.frictionForce = 0;
    
        MotorSim motor = new MotorSim(motorParams, 0.01);

        for(int i=0; i <1000; i++) {
            motor.run(12);
        }

        // System.out.println(Arrays.toString(motor.trajectory.getVelocity()));

        // File file = new File("using_highchart.html");
        // try {
        //     Desktop.getDesktop().browse(file.toURI());
        // } catch (IOException e) {
        //     // TODO Auto-generated catch block
        // }

        String title = "Robot motion";



        String text = 
        "\n     <!DOCTYPE html>"+
        "\n     <meta charset='utf-8'>"+
        ""+
        "\n     <script src='https://code.highcharts.com/highcharts.js'></script>"+
        ""+
        "\n     <div id='container' style='width:100%; height:400px;'></div>"+
        ""+
        ""+
        "\n     <script>"+
        ""+
        "\n     document.addEventListener('DOMContentLoaded', function () {"+
        ""+
        "\n     Highcharts.chart('container', {"+
        ""+
        "\n     title: {"+
        "\n         text: '"+ title + "'" +
        "\n     },"+
        ""+
        "\n     subtitle: {"+
        "\n         text: 'Source: thesolarfoundation.com'"+
        "\n     },"+
        "\n     yAxis: {"+
        "\n         title: {"+
        "\n             text: 'Number of Employees'"+
        "\n         }"+
        "\n     },"+
        "\n     legend: {"+
        "\n         layout: 'vertical',"+
        "\n         align: 'right',"+
        "\n         verticalAlign: 'middle'"+
        "\n     },"+
        ""+
        "\n     plotOptions: {"+
        "\n         series: {"+
        "\n             label: {"+
        "\n                 connectorAllowed: false"+
        "\n             },"+
        "\n             pointStart: 2010"+
        "\n         }"+
        "\n     },"+
        ""+
        "\n     series: [{"+
        "\n         name: 'Velocity', "+
        "\n         data: "+ Arrays.deepToString(motor.trajectory.getVelocityTimeTuple()) + 
        "\n     },{ "+
        "\n         name: 'Accel',"+
        "\n         data: "+ Arrays.deepToString(motor.trajectory.getAccelTimeTuple()) + 
        "\n     },{ "+
        "\n       name: 'Position',"+
        "\n       data: "+ Arrays.deepToString(motor.trajectory.getPositionTimeTuple()) + 
        "\n     }], "+
        ""+
        "\n     reponsive: {"+
        "\n         rules: [{"+
        "\n             condition: {"+
        "\n                 maxWidth: 500,"+
        "\n                 maxHeight: 300,"+
        "\n             },"+
        "\n             chartOptions: {"+
        "\n                 legend: {"+
        "\n                     layout: 'horizontal',"+
        "\n                     lign: 'center',"+
        "\n                     verticalAlign: 'bottom'"+
        "\n                 } "+
        "\n             } "+
        "\n         }] " +
        "\n     },"+
        "\n     });"+
        "\n     });"+
        "\n     </script>";

        try (PrintWriter out = new PrintWriter("myWebpage.html")) {
            out.println(text);
        }

        File file = new File("mywebpage.html");
        try {
            Desktop.getDesktop().browse(file.toURI());
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }


  
    }
}  