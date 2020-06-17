import java.util.Timer;
import java.util.TimerTask;

public class LevelUp extends TimerTask {
int i=0;
int new_map[][] ;
//Block new_map1;
//public static LevelUp lu;
	@Override
	public void run() {
		System.out.println("check run  5 sec");
		//I want that every 30 sec there will be another line of blocks
		//I need create new matrix that got another line of blocks
		//
		// TODO Auto-generated method stub
	//	new_map1=new Block(3+i, 7);//initialize all to 1
		i++;
		new_map =new int[3+i][7];
		
		//i++;
		// I put the old matrix in the new matrix
		for(int k=0;k<3+i;k++)
		{
			for(int j=0;j<7;j++)
			{
				if(k==0)//the first line it has Blocks
				{
					new_map[k][j]=1;
				}
				else {
				new_map[k][j]=Block.map[k-1][j];//initilize to new map as the old map
				}
				}
			
		}
		GameController.big_map=new Block(3+i, 7);
		
		for(int k=0; k<3+i; k++)
			  for(int j=0; j<7; j++)
				  GameController.big_map.map[k][j]=new_map[k][j];
		
		//i++;
		
	}
	
	public void level_timer() {
		Timer timer = new Timer();
		timer.schedule(new LevelUp(), 0, 3000);
		System.out.println("check level_timer");
		
	}

}
