//@Author - Anushree Sinha


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Perceptron_Learning_Algorithm extends JPanel{
    static int MAX_ITER = 20;
    static double LEARNING_RATE = 0.1;
    static int NUM_INSTANCES = 20;
    static int theta = 2;
    static ArrayList xitems = new ArrayList();
    static ArrayList yitems = new ArrayList();
    
    private double slope;
	public double getSlope() {
		return slope;
	}

	public void setSlope(double slope) {
		this.slope = slope;
	}

	private double bias = 0.0;
	
	 static Perceptron_Learning_Algorithm pla = new Perceptron_Learning_Algorithm();
    
    public double getBias() {
		return bias;
	}

	public void setBias(double bias) {
		this.bias = bias;
	}

	public static void main(String[] args){
        
        double[] x = new double[NUM_INSTANCES];
        double[] y = new double[NUM_INSTANCES]; 
        double[] outputs = new double[NUM_INSTANCES];
        
        
        
        //ten random points of class 1
        for(int i=0;i<NUM_INSTANCES/2;++i){
            x[i] =  randomNumber(3, 9);
            y[i] =  randomNumber(4, 8);
            outputs[i] = 1;
            System.out.println(x[i]+"\t"+y[i]+"\t"+outputs[i]);
        }
        
        //ten random points of class -1
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
        
       
          
        
    	        
        double[] weights = new double[3]; //2 for input variables and one for bias
        double localError, globalError;
        int p, iteration, output;
        
        weights[0] = randomNumber(0, 1);
        weights[1] = randomNumber(0, 1);
        weights[2] = randomNumber(0, 1); 
        
        
        iteration = 0;
        do{
            iteration++;
            globalError = 0;
            
            for(p = 0;p<NUM_INSTANCES;p++){
                
                output = calculateOutput(theta, weights, x[p], y[p]);
                
                localError = outputs[p] - output; 
                
                weights[0] += LEARNING_RATE*localError*x[p];
                weights[1] += LEARNING_RATE*localError*y[p];
                
                //update bias
                weights[2] += LEARNING_RATE*localError;
                
                globalError += (localError*localError);  
            }
            /*Root Mean Square */
            System.out.println("Iteration_Number "+iteration+" : Root Mean Square = "+Math.sqrt(globalError/NUM_INSTANCES));
        }while(globalError != 0 && iteration < MAX_ITER);
        
        System.out.println("Output Line Equation:");
        System.out.println(weights[0]+"*x "+weights[1]+"*y + "+weights[2]+" = 0");
        

        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new Perceptron_Learning_Algorithm());
        f.setSize(400,400);
        f.setLocation(200,200);
        f.setVisible(true);
        
       
       
        pla.setBias(weights[2]);
        pla.setSlope(-(weights[0]/weights[1]));
        
        
        
        
        
        
    }
    
    public static double randomNumber(double min, double max){
        double d = min+Math.random()*(max-min);
        return d;
    }
    
   
    static int calculateOutput(int theta, double weights[], double x, double y){
        double sum  = x*weights[0] + y*weights[1] + weights[2];
        return sum>=theta ? 1:-1;
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

		Line2D.Double line1 = new Line2D.Double(20, 150, 350, 250);
		g2.setPaint(Color.BLACK);
		g2.draw(line1);

		
		g2.setPaint(Color.GREEN);
		
	
		
		Line2D.Double line2 = new Line2D.Double(20+pla.getSlope()*xInc, 150+pla.getBias()*scale, 350+pla.getSlope()*xInc, 250+pla.getBias()*scale);

		g2.draw(line2);
		

    }
    
    
    
    
}