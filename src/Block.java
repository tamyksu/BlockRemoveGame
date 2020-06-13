import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

import javafx.scene.shape.Rectangle;

public class Block {
	public int map[][];
	public int w,h;
	public Block(int row,int col) {
		map = new int[row][col];
		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map[0].length;j++) {//initialize the look
				map[i][j]=1;
			}
	}
	
		
		w=540/col;
		h=150/row;
	}	
	public void draw(Graphics2D g) {
		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map[0].length;j++) {
			if(map[i][j]>0) {
				g.setColor(Color.pink);
				g.fillRect(j*w+80, i*h+50, w, h);
				g.setStroke(new BasicStroke(3));
				g.setColor(Color.black);
				
				g.drawRect(j*w+80, i*h+50, w, h);;
			}
			
			}
			}
			}
	
	public void setBrickValue(int value,int row,int col)
	{
		map[row][col]=value;
	}
	}



