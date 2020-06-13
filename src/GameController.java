import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javafx.scene.control.Button;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import javafx.fxml.FXMLLoader;
import javafx.geometry.BoundingBox;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class GameController extends JPanel implements ActionListener, KeyListener,MouseMotionListener{
	private int total_brick=21;
	private int pos_x_ball=120;
	private int pos_y_ball=350;
	private int dir_y_ball=-1;
	private int dir_x_ball=-2;
	private Block big_map;
	private int player=310;
	private int flag_play=0;
	int score=0;
	Timer time;
	private int delay=3;
	boolean state = false;
private Button button;

	//constructor
	public GameController() {
		
		big_map=new Block(3, 7);//initialize matrix of blocks
		addKeyListener(this);//?
		 addMouseMotionListener(this);
		setFocusable(true);//?
		setFocusTraversalKeysEnabled(false);//?
		time = new Timer(delay,this);//for delay of ball
		time.start();
		//flag_play=1;
		
	}
	
	 public void mouseMoved(MouseEvent e) {

		
		if(e.getX()> player)
		{
			if(player>=600) {//out of boundaries
				player=600;
			}
			else {
				moveRight();
			}
		}
		else if(e.getX()< player)
			{
			if(player<10) {//out of boundaries
				player=10;
			}
				else {
					moveLeft();
				}
			}
		
			
		}
		
		
	@Override
	public void keyPressed(KeyEvent arg0) {
		
		 
		// TODO Auto-generated method stub
		if(arg0.getKeyCode()==KeyEvent.VK_RIGHT) {
			if(player>=600) {//out of boundaries
				player=600;
			}
			else {
				moveRight();
			}
		}
	if(arg0.getKeyCode()==KeyEvent.VK_LEFT) {
		if(player<10) {//out of boundaries
			player=10;
		}
		else {
			moveLeft();
		}
		}
	}
	public void moveRight() {
		
		flag_play=1;
		player+=20;
	}
	public void moveLeft() {
		flag_play=1;
		player-=20;
	}
	public void paint(Graphics g) {
	
		g.setColor(Color.black);
		g.fillRect(1, 1, 692, 592);
		
		big_map.draw((Graphics2D)(g));
			
		g.setColor(Color.yellow);
		g.fillRect(0, 0, 3, 592);
		g.fillRect(0, 0,692 , 3);
		g.fillRect(691, 0, 3, 592);
		
		g.setColor(Color.green);
		g.fillRect(player, 550, 100,8);
		 
		g.setColor(Color.pink);
		g.fillOval(pos_x_ball, pos_y_ball, 20, 20);
		g.dispose();
		
	
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		time.start();
		if(flag_play==1) {
		//	Rectangle b=new Rectangle(pos_x_ball,dir_y_ball,20,20);
		//	Rectangle p=new Rectangle(player,400,100,8);
			
		if((new BoundingBox (pos_x_ball,pos_y_ball,20,20).intersects(new BoundingBox(player,550,100,8))))
		{

			dir_y_ball=-dir_y_ball;	
		}
		for(int i=0;i<big_map.map.length;i++)
		{
			for(int j=0;j<big_map.map[0].length;j++ ) {
				if(big_map.map[i][j]>0)//not dissapper 
				{
					int brickX = j*big_map.w+80;
					int brickY = i*big_map.h+50;
					int brickW=big_map.w;
					int brickH=big_map.h;
					
					
					BoundingBox ballRect = new BoundingBox(pos_x_ball,pos_y_ball,20,20);
					BoundingBox brickRect =  new BoundingBox(brickX,brickY,brickW,brickH);
					
					if(ballRect.intersects(brickRect)) {
						big_map.setBrickValue(0,i,j);
						total_brick--;
						score+=5;
						
						if (pos_x_ball+19<=brickRect.getMaxX() || pos_x_ball+1>=brickRect.getMaxX()+brickRect.getWidth())
						{
							dir_x_ball=-dir_x_ball;
							
						}else
						{
							dir_y_ball=-dir_y_ball;
						}
					}
				}
			}
		}
			pos_x_ball+= dir_x_ball;
			pos_y_ball += dir_y_ball;
			if(pos_x_ball<0)
				dir_x_ball=-dir_x_ball;
			
			if(pos_y_ball<0)
				dir_y_ball=-dir_y_ball;
			
			if(pos_x_ball>670)
				dir_x_ball=-dir_x_ball;
		
			//}
				if(pos_y_ball>600&&flag_play==1) {
				flag_play=0;
				
			 	JFrame obj= new JFrame();
		    	obj.setTitle("END!");
		    	
		    	String scoreS=Integer.toString(score);
		    	
		    	 JButton button = new JButton("New Game");
		    	// button.setBounds(100,100,60,30);  
		    	 // Component button = new Button("Menu");
		    	 button.addActionListener(new ActionListener(){  
		    		    public void actionPerformed(ActionEvent e)
		    		    {  
		    		      	JFrame obj= new JFrame();
		    		    	GameController gamePlay= new GameController();
		    		    	obj.setBounds(10,10,700,600);
		    		    	
		    		    	obj.setResizable(false);
		    		    	obj.setVisible(true);
		    		    //	obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    		    	obj.add(gamePlay);
		    		    }  
		    		    });  
		    	 
		    	 JButton bExit = new JButton("Exit");
		    	// bExit.setBounds(50,50,60,30);  
		    	 bExit.addActionListener(new ActionListener(){
				    public void actionPerformed(ActionEvent e)
				    {  
	    		      	//JFrame obj= new JFrame();
	    		    	//obj.setBounds(10,100,700,600);
	    		    	
	    		    	//obj.setResizable(false);
	    		    //	obj.setVisible(true);
	    		    	 System.exit(0);
	    		  
	    		    	
	    		    }  
	    		    }); 
		    
		    	 Label l=new Label("Youre score:"+"  "+scoreS);
		    	l.setFont(new Font("Neuropol", Font.PLAIN, 22));
		    	 l.setBounds(50, 50, 200, 30);
		    	 bExit.setBounds(300,50,200,30);
		    	 button.setBounds(300,300,200,30);  
		    	 
		    	obj.add(l);
		    	obj.add(bExit);
		    	obj.add(button);
		    	
		    	obj.setSize(600, 300);
		    	obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    	obj.setLocationRelativeTo(null); // this method display the JFrame to center position of a screen
		    	obj.setVisible(true);
		    	//MainScreenController gc = null;
		   // return;
		    	//gc.getGameControllerJFrame();// hide window
		        // this will hide and dispose the frame, so that the application quits by
		        // itself if there is nothing else around. 
		       

		    
			
			}
		
		}
		repaint();
	}

	
	
	private boolean intersects(Rectangle b, Rectangle p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	
}
