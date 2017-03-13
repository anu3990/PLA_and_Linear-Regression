
//@Author - Anushree Sinha

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.*;

public class Linear_Regression extends JPanel{ 

	static int MAX_ITER = 20;
    static double LEARNING_RATE = 0.1;
    static int NUM_INSTANCES = 20;
    static int theta = 2;
    static double[] x = new double[NUM_INSTANCES];
    static double[] y = new double[NUM_INSTANCES]; 
    static boolean  computed = false;
    static double a,b;
    static ArrayList xitems = new ArrayList();
    static ArrayList yitems = new ArrayList();
    
public static void main(String[] args){
        
        
        double[] outputs = new double[NUM_INSTANCES];
       
        for(int i=0;i<NUM_INSTANCES/2;++i){
            x[i] =  randomNumber(3, 9);
            y[i] =  randomNumber(4, 8);
            outputs[i] = 1;
            System.out.println(x[i]+"\t"+y[i]+"\t"+outputs[i]);
        }
        
       
        for(int i=10;i<NUM_INSTANCES;i++){
            x[i] =  randomNumber(8,16);
            y[i] =  randomNumber(6,12);
            outputs[i] = -1;
            System.out.println(x[i]+"\t"+y[i]+"\t"+outputs[i]);
        }
        
        for(int i=0; i<20; i++){
        	xitems.add(x[i]);
        	yitems.add(y[i]);
        }
        
        
         
        
          b = covariance(x, y) / variance(x);
        
          a = mean(y) - b * mean(x);
       
          computed = true;
          
          System.out.printf("Equation of line of best fit: y = %.4f + %.4fx", a, b);
          
          JFrame f = new JFrame();
          f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          f.add(new Linear_Regression());
          f.setSize(400,400);
          f.setLocation(200,200);
          f.setVisible(true);
          
          
}



public static double randomNumber(double x, double y){
    double d = x+Math.random()*(y-x);
    return d;
}

public static double covariance(double[] x, double[] y) {

  double xmean = mean(x);

  double ymean = mean(y);

  double result = 0;

  for (int i = 0; i < x.length; i++) {

      result += (x[i] - xmean) * (y[i] - ymean);

  }

  result /= x.length - 1;

  return result;

}

public static double mean(double[] list) {

  double sum = 0;
  int n = list.length;
  for (int i = 0; i < list.length; i++) {

    sum += list[i];

  }

  return sum /n;

}

public static double variance(double[] data) {

  double mean = mean(data);

  double sumsquaredev = 0;

  for (int i = 0; i < data.length; i++) {

	  sumsquaredev += Math.pow(data[i] - mean, 2);

  }

  return sumsquaredev / (data.length - 1);

}


protected void paintComponent(Graphics g) {
	super.paintComponent(g);
	Graphics2D g2 = (Graphics2D) g;
	g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	int w = getWidth();
	int h = getHeight();
	int len = 20;
	double xInc = (double) (w - 2 * len) / 20;
	double scale = (double) (h - 2 * len) / 20;

	//y axis
	g2.draw(new Line2D.Double(len, len, len, h - len));
	
	//x axis
	g2.draw(new Line2D.Double(len, h - len, w - len, h - len));

	for (int i = 0; i < 10; i++) {
		double x = len + (double) xitems.get(i) * xInc;
		double y = h - len - scale * (double) yitems.get(i);
		g2.setPaint(Color.red);
		((Graphics2D) g).fill(new Ellipse2D.Double(x - 2, y - 2, 10, 10));
	}

	for (int i = 10; i < 20; i++) {
		double x = len + (double) xitems.get(i) * xInc;
		double y = h - len - scale * (double) yitems.get(i);
		g2.setPaint(Color.BLUE);
		((Graphics2D) g).fill(new Ellipse2D.Double(x - 2, y - 2, 10, 10));
	}
	
	Line2D.Double line1 = new Line2D.Double(20, h-a*scale, 350+b*xInc, h-a*scale-b*xInc-len*a);
	g2.setPaint(Color.BLACK);
	g2.draw(line1);
	
	

}

  
    
}
