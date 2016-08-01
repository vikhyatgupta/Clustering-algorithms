package clustering;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class GraphPlot extends JFrame implements ActionListener {

    JPanel panel;
    JMenuBar jmenubar;
    JMenu jmenu1, jmenu2;
    JMenuItem dataType1, dataType2, alg1, alg2, alg3, alg4, ext, alg12, alg21, alg31, alg41;
    DataSet ds;

    int flag;

    GraphPlot() {
        flag = 0;
        panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setOpaque(true);

        jmenubar = new JMenuBar();
        jmenu1 = new JMenu("DataSet");
        jmenu2 = new JMenu("Clustering Algorithm");
        jmenubar.add(jmenu1);
        jmenubar.add(jmenu2);

        dataType1 = new JMenuItem("Random");
        jmenu1.add(dataType1);
        dataType1.addActionListener(this);

        dataType2 = new JMenuItem("Gaussian");
        jmenu1.add(dataType2);
        dataType2.addActionListener(this);

        alg1 = new JMenuItem("Random KMean");
        jmenu2.add(alg1);
        alg1.addActionListener(this);

        alg12 = new JMenuItem("Gaussian KMean");
        jmenu2.add(alg12);
        alg12.addActionListener(this);

        alg2 = new JMenuItem("Random KHMean");
        jmenu2.add(alg2);
        alg2.addActionListener(this);

        alg21 = new JMenuItem("Gaussian KHMean");
        jmenu2.add(alg21);
        alg21.addActionListener(this);

        alg3 = new JMenuItem("Random Hybrid1");
        jmenu2.add(alg3);
        alg3.addActionListener(this);

        alg31 = new JMenuItem("Gaussian Hybrid1");
        jmenu2.add(alg31);
        alg31.addActionListener(this);

        alg4 = new JMenuItem("Random Hybrid2");
        jmenu2.add(alg4);
        alg4.addActionListener(this);

        alg41 = new JMenuItem("Gaussian Hybrid2");
        jmenu2.add(alg41);
        alg41.addActionListener(this);

        this.setJMenuBar(jmenubar);

        this.add(panel);
        panel.setPreferredSize(new Dimension(1000, 800));
        panel.setBackground(Color.WHITE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        panel.removeAll();
        JMenuItem src = (JMenuItem) e.getSource();
        if (src == dataType1) {
            ds = new DataSet(100);
            BufferedImage bi = ds.RandomGenerator();
            panel.add(new JLabel(new ImageIcon(bi)));
            panel.revalidate();

        } else if (src == dataType2) {
            ds = new DataSet(224);
            BufferedImage bi = ds.GaussianRandomGenerator();
            panel.add(new JLabel(new ImageIcon(bi)));
            panel.revalidate();

        } else if (src == alg1) {
            BufferedImage bi = ds.applyKmean();
            panel.add(new JLabel(new ImageIcon(bi)));
            panel.revalidate();
        } else if (src == alg12) {
            BufferedImage bi = ds.applyGaussianKmean();
            panel.add(new JLabel(new ImageIcon(bi)));
            panel.revalidate();
        } else if (src == alg2) {
            BufferedImage bi = ds.applyKHmean();
            panel.add(new JLabel(new ImageIcon(bi)));
            panel.revalidate();
        } else if (src == alg21) {
            BufferedImage bi = ds.applyGaussianKHmean();
            panel.add(new JLabel(new ImageIcon(bi)));
            panel.revalidate();
        } else if (src == alg3) {
            BufferedImage bi = ds.applyHybrid1();
            panel.add(new JLabel(new ImageIcon(bi)));
            panel.revalidate();
        } else if (src == alg31) {
            BufferedImage bi = ds.applyGaussianHybrid1();
            panel.add(new JLabel(new ImageIcon(bi)));
            panel.revalidate();
        } else if (src == alg4) {
            BufferedImage bi = ds.applyHybrid2();
            panel.add(new JLabel(new ImageIcon(bi)));
            panel.revalidate();
        } else if (src == alg41) {
            BufferedImage bi = ds.applyGaussianHybrid2();
            panel.add(new JLabel(new ImageIcon(bi)));
            panel.revalidate();
        }
    }

    class DataSet {

        Random rgx = new Random();
        Random rgy = new Random();
        ArrayList<Point> dataset;

        public DataSet(int size) {
            dataset = new ArrayList<Point>();
            for (int i = 0; i < size; ++i) {
                double x = (double) rgx.nextInt(99);
                double y = (double) rgy.nextInt(99);
                dataset.add(new Point(x + 1, y + 1));
            }

        }

        private BufferedImage RandomGenerator() {

            BufferedImage bi = new BufferedImage(800, 800, BufferedImage.TYPE_INT_ARGB);

            Graphics g = bi.getGraphics();
            g.setColor(Color.BLACK);
            g.drawLine(20, 0, 20, 600);
            g.drawLine(20, 600, 600, 600);

            for (int i = 0; i < dataset.size(); i++) {
                g.drawOval((int) dataset.get(i).getX() * 5 + 20, (int) dataset.get(i).getY() * 5 + 20, 6, 6);
            }
            return bi;
        }

        private BufferedImage GaussianRandomGenerator() {

            BufferedImage bi = new BufferedImage(800, 800, BufferedImage.TYPE_INT_ARGB);

            Graphics g = bi.getGraphics();
            g.setColor(Color.BLACK);
            g.drawLine(20, 0, 20, 600);
            g.drawLine(20, 600, 600, 600);

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
            for (int i = 0; i < gc.length; i++) {
                g.fillOval((int) gc[i].getX() * 5 + 20, (int) gc[i].getY() * 5 + 20, 6, 6);
            }

            return bi;
        }

        private BufferedImage applyKmean() {

            BufferedImage bi = new BufferedImage(800, 800, BufferedImage.TYPE_INT_ARGB);
            Graphics g = bi.getGraphics();

            KMeans km = new KMeans(dataset);
            km.KMeansAlgo();

            ArrayList<Point> centroids = km.getCentroids();
            HashMap<Point, Integer> clusters = km.getClusters();

            g.setColor(Color.BLACK);
            g.drawLine(20, 0, 20, 600);
            g.drawLine(20, 600, 600, 600);

            for (int i = 0; i < clusters.size(); i++) {

                switch (clusters.get(dataset.get(i))) {
                    case 0:
                        g.setColor(Color.PINK);
                        break;
                    case 1:
                        g.setColor(Color.GREEN);
                        break;
                    case 2:
                        g.setColor(Color.CYAN);
                        break;
                    case 3:
                        g.setColor(Color.ORANGE);
                        break;

                    default:
                        g.setColor(Color.BLACK);
                        break;
                }
                g.fillOval((int) dataset.get(i).getX() * 5 + 20, (int) dataset.get(i).getY() * 5 + 20, 6, 6);
            }

            for (int i = 0; i < 4; ++i) {
                g.setColor(Color.BLACK);

                g.fillOval((int) centroids.get(i).getX() * 5 + 20, (int) centroids.get(i).getY() * 5 + 20, 6, 6);
            }
            return bi;
        }

        private BufferedImage applyKHmean() {

            BufferedImage bi = new BufferedImage(800, 800, BufferedImage.TYPE_INT_ARGB);
            Graphics g = bi.getGraphics();

            KHMeans khm = new KHMeans(dataset);
            khm.KHMeansAlgo();

            ArrayList<Point> centroids = khm.getCentroids();
            HashMap<Point, Integer> clusters = khm.getClusters();

            g.setColor(Color.BLACK);
            g.drawLine(20, 0, 20, 600);
            g.drawLine(20, 600, 600, 600);

            for (int i = 0; i < clusters.size(); i++) {

                switch (clusters.get(dataset.get(i))) {
                    case 0:
                        g.setColor(Color.PINK);
                        break;
                    case 1:
                        g.setColor(Color.GREEN);
                        break;
                    case 2:
                        g.setColor(Color.CYAN);
                        break;
                    case 3:
                        g.setColor(Color.ORANGE);
                        break;

                    default:
                        g.setColor(Color.BLACK);
                        break;
                }
                g.fillOval((int) dataset.get(i).getX() * 5 + 20, (int) dataset.get(i).getY() * 5 + 20, 6, 6);
            }

            for (int i = 0; i < 4; ++i) {
                g.setColor(Color.BLACK);

                g.fillOval((int) centroids.get(i).getX() * 5 + 20, (int) centroids.get(i).getY() * 5 + 20, 6, 6);
            }
            return bi;
        }

        private BufferedImage applyGaussianKmean() {

            BufferedImage bi = new BufferedImage(800, 800, BufferedImage.TYPE_INT_ARGB);
            Graphics g = bi.getGraphics();

            KMeans kgm = new KMeans(dataset);
            kgm.GaussianKMeansAlgo();

            ArrayList<Point> centroids = kgm.getCentroids();
            HashMap<Point, Integer> clusters = kgm.getClusters();

            g.setColor(Color.BLACK);
            g.drawLine(20, 0, 20, 600);
            g.drawLine(20, 600, 600, 600);

            for (int i = 0; i < clusters.size(); i++) {

                switch (clusters.get(dataset.get(i))) {
                    case 0:
                        g.setColor(Color.PINK);
                        break;
                    case 1:
                        g.setColor(Color.GREEN);
                        break;
                    case 2:
                        g.setColor(Color.CYAN);
                        break;
                    case 3:
                        g.setColor(Color.ORANGE);
                        break;
                    case 4:
                        g.setColor(Color.BLUE);
                        break;
                    case 5:
                        g.setColor(Color.DARK_GRAY);
                        break;
                    case 6:
                        g.setColor(Color.MAGENTA);
                        break;
                    case 7:
                        g.setColor(Color.YELLOW);
                        break;

                    default:
                        g.setColor(Color.LIGHT_GRAY);
                        break;
                }
                g.fillOval((int) dataset.get(i).getX() * 5 + 20, (int) dataset.get(i).getY() * 5 + 20, 6, 6);
            }

            for (int i = 0; i < 4; ++i) {
                g.setColor(Color.BLACK);

                g.fillOval((int) centroids.get(i).getX() * 5 + 20, (int) centroids.get(i).getY() * 5 + 20, 6, 6);
            }
            return bi;
        }

        private BufferedImage applyGaussianKHmean() {

            BufferedImage bi = new BufferedImage(800, 800, BufferedImage.TYPE_INT_ARGB);
            Graphics g = bi.getGraphics();

            KHMeans kgm = new KHMeans(dataset);
            kgm.GaussianKHMeansAlgo();

            ArrayList<Point> centroids = kgm.getCentroids();
            HashMap<Point, Integer> clusters = kgm.getClusters();

            g.setColor(Color.BLACK);
            g.drawLine(20, 0, 20, 600);
            g.drawLine(20, 600, 600, 600);

            for (int i = 0; i < clusters.size(); i++) {

                switch (clusters.get(dataset.get(i))) {
                    case 0:
                        g.setColor(Color.PINK);
                        break;
                    case 1:
                        g.setColor(Color.GREEN);
                        break;
                    case 2:
                        g.setColor(Color.CYAN);
                        break;
                    case 3:
                        g.setColor(Color.ORANGE);
                        break;

                    default:
                        g.setColor(Color.BLACK);
                        break;
                }
                g.fillOval((int) dataset.get(i).getX() * 5 + 20, (int) dataset.get(i).getY() * 5 + 20, 6, 6);
            }

            for (int i = 0; i < 4; ++i) {
                g.setColor(Color.BLACK);

                g.fillOval((int) centroids.get(i).getX() * 5 + 20, (int) centroids.get(i).getY() * 5 + 20, 6, 6);
            }
            return bi;
        }

        private BufferedImage applyHybrid1() {

            BufferedImage bi = new BufferedImage(800, 800, BufferedImage.TYPE_INT_ARGB);
            Graphics g = bi.getGraphics();

            Hybrid1 hbd1 = new Hybrid1(dataset);
            hbd1.Hybrid1Algo();

            ArrayList<Point> centroids = hbd1.getCentroids();
            HashMap<Point, Integer> clusters = hbd1.getClusters();

            g.setColor(Color.BLACK);
            g.drawLine(20, 0, 20, 600);
            g.drawLine(20, 600, 600, 600);

            for (int i = 0; i < clusters.size(); i++) {

                switch (clusters.get(dataset.get(i))) {
                    case 0:
                        g.setColor(Color.PINK);
                        break;
                    case 1:
                        g.setColor(Color.GREEN);
                        break;
                    case 2:
                        g.setColor(Color.CYAN);
                        break;
                    case 3:
                        g.setColor(Color.ORANGE);
                        break;

                    default:
                        g.setColor(Color.BLACK);
                        break;
                }
                g.fillOval((int) dataset.get(i).getX() * 5 + 20, (int) dataset.get(i).getY() * 5 + 20, 6, 6);
            }

            for (int i = 0; i < 4; ++i) {
                g.setColor(Color.BLACK);

                g.fillOval((int) centroids.get(i).getX() * 5 + 20, (int) centroids.get(i).getY() * 5 + 20, 6, 6);
            }
            return bi;
        }

        private BufferedImage applyGaussianHybrid1() {

            BufferedImage bi = new BufferedImage(800, 800, BufferedImage.TYPE_INT_ARGB);
            Graphics g = bi.getGraphics();

            Hybrid1 hbd1 = new Hybrid1(dataset);
            hbd1.GaussianHybrid1Algo();

            ArrayList<Point> centroids = hbd1.getCentroids();
            HashMap<Point, Integer> clusters = hbd1.getClusters();

            g.setColor(Color.BLACK);
            g.drawLine(20, 0, 20, 600);
            g.drawLine(20, 600, 600, 600);

            for (int i = 0; i < clusters.size(); i++) {

                switch (clusters.get(dataset.get(i))) {
                    case 0:
                        g.setColor(Color.PINK);
                        break;
                    case 1:
                        g.setColor(Color.GREEN);
                        break;
                    case 2:
                        g.setColor(Color.CYAN);
                        break;
                    case 3:
                        g.setColor(Color.ORANGE);
                        break;

                    default:
                        g.setColor(Color.BLACK);
                        break;
                }
                g.fillOval((int) dataset.get(i).getX() * 5 + 20, (int) dataset.get(i).getY() * 5 + 20, 6, 6);
            }

            for (int i = 0; i < 4; ++i) {
                g.setColor(Color.BLACK);

                g.fillOval((int) centroids.get(i).getX() * 5 + 20, (int) centroids.get(i).getY() * 5 + 20, 6, 6);
            }
            return bi;
        }

        private BufferedImage applyHybrid2() {

            BufferedImage bi = new BufferedImage(800, 800, BufferedImage.TYPE_INT_ARGB);
            Graphics g = bi.getGraphics();

            Hybrid2 hbd2 = new Hybrid2(dataset);
            hbd2.Hybrid2Algo();

            ArrayList<Point> centroids = hbd2.getCentroids();
            HashMap<Point, Integer> clusters = hbd2.getClusters();

            g.setColor(Color.BLACK);
            g.drawLine(20, 0, 20, 600);
            g.drawLine(20, 600, 600, 600);

            for (int i = 0; i < clusters.size(); i++) {

                switch (clusters.get(dataset.get(i))) {
                    case 0:
                        g.setColor(Color.PINK);
                        break;
                    case 1:
                        g.setColor(Color.GREEN);
                        break;
                    case 2:
                        g.setColor(Color.CYAN);
                        break;
                    case 3:
                        g.setColor(Color.ORANGE);
                        break;

                    default:
                        g.setColor(Color.BLACK);
                        break;
                }
                g.fillOval((int) dataset.get(i).getX() * 5 + 20, (int) dataset.get(i).getY() * 5 + 20, 6, 6);
            }

            for (int i = 0; i < 4; ++i) {
                g.setColor(Color.BLACK);

                g.fillOval((int) centroids.get(i).getX() * 5 + 20, (int) centroids.get(i).getY() * 5 + 20, 6, 6);
            }
            return bi;
        }

        private BufferedImage applyGaussianHybrid2() {

            BufferedImage bi = new BufferedImage(800, 800, BufferedImage.TYPE_INT_ARGB);
            Graphics g = bi.getGraphics();

            Hybrid2 hbd2 = new Hybrid2(dataset);
            hbd2.GaussianHybrid2Algo();

            ArrayList<Point> centroids = hbd2.getCentroids();
            HashMap<Point, Integer> clusters = hbd2.getClusters();

            g.setColor(Color.BLACK);
            g.drawLine(20, 0, 20, 600);
            g.drawLine(20, 600, 600, 600);

            for (int i = 0; i < clusters.size(); i++) {

                switch (clusters.get(dataset.get(i))) {
                    case 0:
                        g.setColor(Color.PINK);
                        break;
                    case 1:
                        g.setColor(Color.GREEN);
                        break;
                    case 2:
                        g.setColor(Color.CYAN);
                        break;
                    case 3:
                        g.setColor(Color.ORANGE);
                        break;

                    default:
                        g.setColor(Color.BLACK);
                        break;
                }
                g.fillOval((int) dataset.get(i).getX() * 5 + 20, (int) dataset.get(i).getY() * 5 + 20, 6, 6);
            }

            for (int i = 0; i < 4; ++i) {
                g.setColor(Color.BLACK);

                g.fillOval((int) centroids.get(i).getX() * 5 + 20, (int) centroids.get(i).getY() * 5 + 20, 6, 6);
            }
            return bi;
        }
    }
}
