package clustering;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Vikhyat
 */
public class Point {

    double x, y;
    double cluster;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getX() {
        return x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getY() {
        return y;
    }

    public void setCluster(double cluster) {
        this.cluster = cluster;
    }

    public double getCluster() {
        return cluster;
    }

    public double distance(Point other) {
        return Math.sqrt(Math.pow(x - other.x, 2) + Math.pow(y - other.y, 2));
    }

    public String display() {
        return "( " + String.format("%.2f", x) + " , " + String.format("%.2f", y) + " )";
    }
}
