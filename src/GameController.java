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
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.event.*;
import javafx.scene.control.Button;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import java.applet.Applet;
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
	int first=0;
	
	ArrayList<ArrayList<Integer>> special_index = new ArrayList<ArrayList<Integer>>(); // Create an ArrayList object
	int indexY=1;
	int delayStar = 400;
	public  static Block big_map;
	private int player=310;
	private int flag_play=0;
	int num=1;
	int score=0;
	Timer time;

	 ArrayList <Graphics2D> ar=new  ArrayList <Graphics2D> (10);
	 ArrayList <Integer> flag_draw=new  ArrayList <Integer> (Collections.nCopies(10, 0));
	Timer levelTime;
	int goDown=0;
	Image  backgroundImage;
	private int delay=5;
	int flagSpecial=0;

	boolean state = false;
	int mouse_flag=0;
	 public static ArrayList <Boolean>  signal=new  ArrayList <Boolean>(Collections.nCopies(10, false)) ;
	 int signal_index=0;
	public LevelUp lu=new LevelUp();
private Button button;
Boolean[] running = new Boolean[3];

/**********************************-1-GameController-Constructor
 * @throws IOException ***********************************************/

	//@SuppressWarnings("deprecation") constuctor
	public GameController() throws IOException {
		
		big_map=new Block(3,7,"normal");//initialize matrix of blocks
		addKeyListener(this);//?
		addMouseMotionListener(this);
		setFocusable(true);//?
		setFocusTraversalKeysEnabled(false);//?
		time = new Timer(delay,this);//for delay of ball
		lu.level_timer();//i'm not using it for now
		time.start();
		 backgroundImage = new ImageIcon(this.getClass().getResource("/sky.jpg")).getImage();
		//backgroundImage = Toolkit.getDefaultToolkit().createImage("sky.jpg");
		//backgroundImage = ImageIO.read(new java.net.URL(getClass().getResource("src/sky.jpg"), "src/sky.jpg"));
		flag_play=1;
		if(Main.primaryStage.isShowing())
		Main.primaryStage.hide();
		
	}
	/**************************-2-mouseMoved***********************************/
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
		
	/*****************************-3-keyPressed**********************************/	
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
	/*****************************-4-moveRight**********************************/	
	public void moveRight() {
		
		flag_play=1;
		player+=20;
	}
	/*****************************-5-moveLeft**********************************/
	public void moveLeft() {
		flag_play=1;
		player-=20;
	}
	/*****************************-6-create**********************************/
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
	
	/***************************-7-paint**********************************/
	 @Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		 
		g.drawImage(backgroundImage, 0, 0,692,592,null);
		big_map.draw((Graphics2D)(g));//drawing all the blocks
		
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
	    		    	GameController gamePlay;
						try {
							gamePlay = new GameController();
							obj.setBounds(10,10,700,600);
							obj.setResizable(false);
							obj.setVisible(true);
							//object=obj;
							mouse_flag=0;
							//	obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							obj.add(gamePlay);
							
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	    		
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
	
	public int goDown(int y)
	{
		y+=50;
		return y;
		
	}
	/***********************************-8-special_blocks***********************************************/

/*	public void special_blocks(Graphics2D g,int x,int y) {
		
	//while(x<550)
	//{
		System.out.println("x="+x);
	        g.setPaint(new RadialGradientPaint(
		               new Point(80*y+120, 50*x+75), 50, new float[] { 0, 0.3f, 1 }, 
		               new Color[] { Color.RED, Color.YELLOW, Color.ORANGE }));
	            g.fill(createStar(80*y+120, 50*x+75, 20, 20, 20, 0));
	        ((Graphics2D) g).fill(createStar(80*y+120, 50*x+75, 15, 15, 10, 0));
			
		x+=50;
//	}

     /*   ActionListener taskPerformer = new ActionListener(){
              public void actionPerformed(ActionEvent evt2) {
                 //your code here
    //indexY++;
              }
        };
      
       /* if(first==0)
        {
        	int random_size= big_map.getRandomSize();
        	
        	 Timer timer = new Timer(delayStar, taskPerformer);
 	        timer.start();
 	        first=1;
        	
        }*/

	
	
	

	/***********************************-9-actionPerformed******************************************/  

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		time.start();
		if(flag_play==1)
		{

			/****if ball touch the border it change direction moving up***/
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
					//if ball touch the block -remove block 
					if(ballRect.intersects(brickRect)) {
						if(big_map.mapSpecial[i][j]==true)//check if it is special block
							
						{
						//	System.out.println("actionPerformed-special block ^^");
							
							 flagSpecial=1;
							spcial_i=i;
							spcial_j=j;
							SwingUtilities.invokeLater(new Runnable() {

							  @Override
						        public void run() {
							Star st=new Star(spcial_i,spcial_j);
							st.repaint();
						  }});
							
							//goDown=0;
							//nofity spefic tread
							
							
							//signal.set(signal_index,true);
							//object.notifyAll();
							//signal_index++;

							//flag_draw.set((int)t.getId(),1);
							big_map.mapSpecial[i][j]=false;
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
