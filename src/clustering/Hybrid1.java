package clustering;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Hybrid1 {

    final static int CLUSTERS = 4;
    final static int DATA = 100;
    Random random = new Random();

    ArrayList<Point> data;
    ArrayList<Point> oldcentroids, centroids;
    ArrayList<Double> sumX;
    ArrayList<Double> sumY;
    HashMap<Point, Integer> clusters;
    HashMap<Integer, Integer> count;

    public Hybrid1(ArrayList<Point> data) {
        this.data = data;
        centroids = new ArrayList<Point>();
        oldcentroids = new ArrayList<Point>();
        clusters = new HashMap<Point, Integer>();
        count = new HashMap<Integer, Integer>();
        sumX = new ArrayList<Double>();
        sumY = new ArrayList<Double>();
    }

    public HashMap<Point, Integer> getClusters() {
        return clusters;
    }

    public ArrayList<Point> getCentroids() {
        return centroids;
    }

    public void Hybrid1Algo() {

        for (int i = 0; i < CLUSTERS; ++i) {
            int rand = random.nextInt(99);
            centroids.add(data.get(rand));

        }
        for (int i = 0; i < CLUSTERS; ++i) {
            sumX.add(0.0);
            sumY.add(0.0);
            count.put(i, 0);
        }

        do {

            for (int i = 0; i < DATA; ++i) {
                clusters.put(data.get(i), getClosestCentroid(data.get(i)));
            }

            oldcentroids = centroids;
            for (int i = 0; i < CLUSTERS; ++i) {
                centroids.set(i, new Point(sumX.get(i)/(count.get(i)+(i)), sumY.get(i)/(count.get(i)+(i))));
            }

            sumX.clear();
            sumY.clear();
            for (int i = 0; i < CLUSTERS; ++i) {
                sumX.add(0.1);
                sumY.add(0.1);
                count.put(i, 0);
            }
        } while (oldcentroids != centroids);

        System.out.println("-----------------Hybrid1--------------");
        for (int i = 0; i < DATA; ++i) {
            System.out.println(data.get(i).display() + " : " + centroids.get(clusters.get(data.get(i))).display());
        }

    }
    
    public void GaussianHybrid1Algo(){
    	
    }

    public int getClosestCentroid(Point p) {
        double min = Integer.MAX_VALUE;
        int minindex = 0;
        for (int i = 0; i < CLUSTERS; ++i) {
            if (p.distance(centroids.get(i)) < min) {
                min = p.distance(centroids.get(i));
                minindex = i;
            }
        }

        sumX.set(minindex, sumX.get(minindex) + p.getX());
        sumY.set(minindex, sumY.get(minindex) + p.getY());

        count.put(minindex, count.get(minindex) + 1);
        return minindex;
    }
}
