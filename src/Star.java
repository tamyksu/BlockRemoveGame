import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RadialGradientPaint;
import java.awt.Shape;
import java.awt.geom.Path2D;

import javax.swing.JPanel;

public class Star extends JPanel{
	int x,y;
	public Star(int x, int y)
	{
		this.x=x;
		this.y=y;
		System.out.println("Star Constructor");
		
		repaint();
	}
	
	public void starFall()
	{
		y=+50;
		
	}
	
	public void paint(Graphics g) {
		
		System.out.println("Star paint");
		special_blocks((Graphics2D)g,x,y);
	
		
	
	}
	
	/*
	 * not entering paint methode
	 * maybe problem with calling repaint()
	 * 
	 * 
	 */
	
	
	public void special_blocks(Graphics2D g,int x,int y) {
	System.out.println("star: special block cuurent x "+ x+ "y "+y);
			
		        g.setPaint(new RadialGradientPaint(
			               new Point(80*y+120, 50*x+75), 50, new float[] { 0, 0.3f, 1 }, 
			               new Color[] { Color.RED, Color.YELLOW, Color.ORANGE }));
		            g.fill(createStar(80*y+120, 50*x+75, 20, 20, 20, 0));
		        ((Graphics2D) g).fill(createStar(80*y+120, 50*x+75, 15, 15, 10, 0));
		        starFall();
		        
	}
				
	private static Shape createStar(double centerX, double centerY,
	        double innerRadius, double outerRadius, int numRays,
	        double startAngleRad)
	    {
	        Path2D path = new Path2D.Double();
	        double deltaAngleRad = Math.PI / numRays;
	        for (int i = 0; i < numRays * 2; i++)
	        {
	            double angleRad = startAngleRad + i * deltaAngleRad;
	            double ca = Math.cos(angleRad);
	            double sa = Math.sin(angleRad);
	            double relX = ca;
	            double relY = sa;
	            if ((i & 1) == 0)
	            {
	                relX *= outerRadius;
	                relY *= outerRadius;
	            }
	            else
	            {
	                relX *= innerRadius;
	                relY *= innerRadius;
	            }
	            if (i == 0)
	            {
	                path.moveTo(centerX + relX, centerY + relY);
	            }
	            else
	            {
	                path.lineTo(centerX + relX, centerY + relY);
	            }
	        }
	        path.closePath();
	        return path;
	    }
	
	
	
	

}
