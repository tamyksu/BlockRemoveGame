/*import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Stars extends JPanel implements Runnable{
	
	
	public GameController gc;
	//public Thread t;
	@Override
	public void run() {
		
		System.out.println(Thread.currentThread().getName());
		// TODO Auto-generated method stub
		int val=Integer.parseInt(Thread.currentThread().getName());
		
		System.out.println("array signal:  "+val+"  "+GameController.signal.get(val));
		while(GameController.signal.get(val)!=true)
		{
			try {
				//System.out.println(Thread.currentThread().getName()+" wait");
				GameController.object.get(val).wait();
			
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				//System.err.println("Stars- func run problem ");
				//e.printStackTrace();
				System.out.println("func Stop wait");
			}
		}
		System.out.println(" Stop wait");
		repaint();
		
		
	}
	
	public void paint(Graphics g) {
		
		gc.special_blocks((Graphics2D)(g));
	}
	


}
*/