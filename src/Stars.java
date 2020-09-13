/*import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Stars extends JPanel implements Runnable{
	
	ArrayList<ArrayList<Integer>> special;
	public GameController gc;
	//public Thread t;
	
	
	 public Stars(ArrayList<ArrayList<Integer>> special) {
		// TODO Auto-generated constructor stub
		this.special=special;
		
	}
	@Override
	public void run() {
		///all threads wait
		
		System.out.println(Thread.currentThread().getName());
		// TODO Auto-generated method stub
		int val=Integer.parseInt(Thread.currentThread().getName());
		
		System.out.println("array signal:  "+val+"  "+GameController.signal.get(val));
		while(GameController.signal.get(val)!=true)
		{
			try {
				wait();
				//System.out.println(Thread.currentThread().getName()+" wait");
			//	GameController.object.get(val).wait();
			
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
		int save_i=0;
		for(int i=0;i<10;i++)
		{
			
			if(gc.ThreadArray.get(i).equals(Thread.currentThread()))
			{
				save_i=i;
				i=10;
			}
		}
		ArrayList<Integer>location= special.get(save_i);
	//	gc.special_blocks((Graphics2D)(g),location.get(0),location.get(1));//x,y
		
		if(location.get(1)<560)
		{
			int new_y =gc.goDown(location.get(1));

			location.set(1, new_y);
		}
	}
	


}*/
