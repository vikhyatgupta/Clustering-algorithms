package clustering;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class KHMeans {

   int CLUSTERS = 4;
    final static int DATA = 100;
    Random random = new Random();

    ArrayList<Point> data;
    ArrayList<Point> oldcentroids, centroids;
    ArrayList<Double> sumX;
    ArrayList<Double> sumY;
    HashMap<Point, Integer> clusters;
    HashMap<Integer, Integer> count;

    public KHMeans(ArrayList<Point> data) {
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

    public void KHMeansAlgo() {

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
                centroids.set(i, new Point((count.get(i) / sumX.get(i)), (count.get(i) / sumY.get(i))));
            }

            sumX.clear();
            sumY.clear();
            for (int i = 0; i < CLUSTERS; ++i) {
                sumX.add(0.0);
                sumY.add(0.0);
                count.put(i, 0);
            }
        } while (oldcentroids != centroids);

        System.out.println("-----------------KHMeans--------------");
        for (int i = 0; i < DATA; ++i) {
            System.out.println(data.get(i).display() + " : " + centroids.get(clusters.get(data.get(i))).display());
        }

    }

    public void GaussianKHMeansAlgo(){
    	
    	CLUSTERS=16;
		Point[] gc = new Point[16];
		gc[0] = new Point(20, 20);
		gc[1] = new Point(20, 40); 
		gc[2] = new Point(20, 60); 
		gc[3] = new Point(20, 80); 
		gc[4] = new Point(40, 20); 
		gc[5] = new Point(40, 40); 
		gc[6] = new Point(40, 60); 
		gc[7] = new Point(40, 80); 
		gc[8] = new Point(60, 20); 
		gc[9] = new Point(60, 40); 
		gc[10] = new Point(60, 60); 
		gc[11] = new Point(60, 80); 
		gc[12] = new Point(80, 20); 
		gc[13] = new Point(80, 40); 
		gc[14] = new Point(80, 60); 
		gc[15] = new Point(80, 80);
		
		for(int i = 0 ; i<16; ++i){
			centroids.add(gc[i]);
			
		}
		
		for(int i = 0; i< data.size(); ++i){
			clusters.put(data.get(i), getClosestGCentroid(data.get(i)));
		}
			
		System.out.println("-----------------Gaussian KHMeans--------------");
		for(int i = 0; i<DATA; ++i){
			System.out.println(data.get(i).display() +" : "+centroids.get(clusters.get(data.get(i))).display());
		}
		
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

        sumX.set(minindex, sumX.get(minindex) + (1 / p.getX()));
        sumY.set(minindex, sumY.get(minindex) + (1 / p.getY()));

        count.put(minindex, count.get(minindex) + 1);
        return minindex;
    }
    
    public int getClosestGCentroid(Point p) {
        double min = Integer.MAX_VALUE;
        int minindex = 0;
        for (int i = 0; i < CLUSTERS; ++i) {
            if (p.distance(centroids.get(i)) < min) {
                min = p.distance(centroids.get(i));
                minindex = i;
            }
        }

        return minindex;
    }
}
