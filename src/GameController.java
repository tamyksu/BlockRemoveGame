import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.RadialGradientPaint;

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
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.event.*;
import javafx.scene.control.Button;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.*;
import javafx.fxml.FXMLLoader;
import javafx.geometry.BoundingBox;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.FontBuilder;
import javafx.stage.Stage;


public class GameController extends JPanel implements ActionListener, KeyListener,MouseMotionListener{
	private int total_brick=21;
	private int pos_x_ball=120;
	private int pos_y_ball=350;
	private int dir_y_ball=-1;
	private int dir_x_ball=-2;
	private int spcial_i=0;
	private int spcial_j=0;
	int indexY=1;
	int delayStar = 400;
	public  static Block big_map;
	private int player=310;
	private int flag_play=0;
	int num=1;
	int score=0;
	Timer time;
	Timer levelTime;
	int goDown=0;
	private int delay=5;
	int flag_draw=0;
	boolean state = false;
	int mouse_flag=0;
	public LevelUp lu=new LevelUp();
private Button button;
boolean runnig= false;
public static JFrame object=null; 
	//constructor

	//@SuppressWarnings("deprecation")
	public GameController() {
	
		big_map=new Block(3,7,"normal");//initialize matrix of blocks
		addKeyListener(this);//?
		 addMouseMotionListener(this);
		setFocusable(true);//?
		setFocusTraversalKeysEnabled(false);//?
		time = new Timer(delay,this);//for delay of ball
		  lu.level_timer();
	
		//levelTime = new Timer(1000, null);
		//levelTime.schedule(new LevelUp(), 0, 5000);
		
		time.start();
		
	     
		flag_play=1;
		if(Main.primaryStage.isShowing())
		Main.primaryStage.hide();
	//	if(object!=null) {
	//	GameController.object.hide();
	//	}

			
		
	}
	/**************************mouseMoved***********************************/
	 public void mouseMoved(MouseEvent e) {
		 BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		 
		 Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
				    cursorImg, new Point(0, 0), "blank cursor");
		if(mouse_flag==0) {
		if(e.getX()> player)
		{
			if(player>=600) {//out of boundaries
				player=600;
			}
			else {
				moveRight();
                setCursor(blankCursor);

				
			}
		}
		else if(e.getX()< player)
			{
			if(player<10) {//out of boundaries
				player=10;
			}
				else {
					moveLeft();
					  setCursor(blankCursor);
				}
			}
		
			
		}
	 }
		
	/*****************************keyPressed**********************************/	
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
	
	//////create star////
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
	
	/***************************paint**********************************/
	public void paint(Graphics g) {
	
		g.setColor(Color.black);
		g.fillRect(1, 1, 692, 592);
		
		big_map.draw((Graphics2D)(g));
		if(flag_draw==1) {
			special_blocks((Graphics2D)g);
		}
	
		g.setColor(Color.yellow);
		g.fillRect(0, 0, 3, 592);
		g.fillRect(0, 0,692 , 3);
		g.fillRect(691, 0, 3, 592);
		
		g.setColor(Color.green);
		g.fillRect(player, 550, 100,8);
		 
		g.setColor(Color.pink);
		g.fillOval(pos_x_ball, pos_y_ball, 20, 20);
		g.setColor(Color.white);
		g.setFont(new Font("serif",Font.BOLD,25));
		g.drawString("Score: "+score, 590, 30);
		g.drawString("Level: "+num, 290, 30);
			
		
		
	     //--------------------check if there blocks on the screen-------------------    
	int flag=0;
		for(int i=0;i<big_map.map.length&& flag==0;i++)
		{
			for(int j=0;j<big_map.map[0].length&& flag==0;j++ ) {
				if(big_map.map[i][j]!=0)//not dissapper 
				{
				flag=1;
				
				}
			}}
		//----------------------------------go to next level----------------------------
		if(flag ==0)
		{
			num++;
			big_map=new Block(5, 8,"heart");
		}
				
		
		//-----------------------------------end of game--------------------------------------------
		if(pos_y_ball>600&&flag_play==1) {
			flag_play=0;
			dir_x_ball=0;
			mouse_flag=1;
			dir_y_ball=0;
		 	JFrame obj= new JFrame();
	    	obj.setTitle("END!");
	    	
	    	String scoreS=Integer.toString(score);
	    	
	    	 JButton button = new JButton("New Game");
	    	// button.setBounds(100,100,60,30);  
	    	 // Component button = new Button("Menu");
	    	 button.addActionListener(new ActionListener()
	    	 {  
	    		    public void actionPerformed(ActionEvent e)
	    		    {  
	    		    	
	    		    
	    		      	obj.setResizable(false);
	    		    	obj.setVisible(true);
	    		  //	Main.primaryStage.show();
	    		     	JFrame obj= new JFrame();
	    		    	GameController gamePlay= new GameController();
	    		    	obj.setBounds(10,10,700,600);
	    		    	
	    		    	obj.setResizable(false);
	    		    	obj.setVisible(true);
	    		    	//object=obj;
	    		    	mouse_flag=0;
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
			    	obj.setResizable(false);
			    	obj.setVisible(true);
    		    	//obj.setResizable(false);
    		    //	obj.setVisible(true);
    		    	 System.exit(0);
    		  
    		    	
    		    }  
    		    }); 
	    
	    	 Label l=new Label("Youre score:"+"  "+scoreS);
	    	l.setFont(new Font("Neuropol", Font.PLAIN, 22));
	    	 l.setBounds(50, 50, 200, 30);
	    	 bExit.setBounds(300,50,200,30);
	    	 button.setBounds(300,300,100,30);  
	    	 
	    	 obj.add(l);
	    	 obj.add(bExit);
	    	
	    
	    	obj.add(button);
	    	bExit.setVisible(true);
	    	obj.setSize(600, 300);
	    	obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    	obj.setLocationRelativeTo(null); // this method display the JFrame to center position of a screen
	    	//g.dispose();
	    //	setVisible(false);
	    	
	    	MainScreenController.object.dispose();
	    	//if()
	    	//GameController.object.dispose();
	    	obj.setVisible(true);
	    //	object=obj;
	    	//close();


		}
	g.dispose();
	}
	
	
	/***********************************special_blocks***********************************************/

	public void special_blocks(Graphics2D g) {
		
		/////600 -200 - last right low
		///600 70 right high
		
		  goDown=70*spcial_i+(70*indexY);
		//80 70 left high
        //((Graphics2D) g).setPaint(Color.RED);
        g.setPaint(new RadialGradientPaint(
                new Point(80*spcial_j+80, goDown), 50, new float[] { 0, 0.3f, 1 }, 
                new Color[] { Color.RED, Color.YELLOW, Color.ORANGE }));
            g.fill(createStar(80*spcial_j+80, goDown, 20, 20, 20, 0));
          
        //((Graphics2D) g).fill(createStar(80+spcial_i*80, 70+spcial_j*70, 20, 20, 10, 0));
        ((Graphics2D) g).fill(createStar(80*spcial_j+80,goDown, 15, 15, 10, 0));
        System.out.println("spcial_i"+spcial_i+" spcial_j "+spcial_j);
        
        
        
        
     // you can inject this property 
        
      
        ActionListener taskPerformer = new ActionListener(){
              public void actionPerformed(ActionEvent evt2) {
                 //your code here
    indexY++;
              }
        };
        if(runnig==false)
        {
        Timer timer = new Timer(delayStar, taskPerformer);
        timer.start();
        runnig=true
        		;
        }

    
        //flag_draw=0;
        
	      ////   
	}
	
	

	/***********************************actionPerformed******************************************/  

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		time.start();
		if(flag_play==1)
		{

			
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
					//remove block
					if(ballRect.intersects(brickRect)) {
						if(big_map.mapSpecial[i][j]==true)
							
						{
							spcial_i=i;
							spcial_j=j;
							flag_draw=1;
							
							
							//special power shown
							
						}
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

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	
}
