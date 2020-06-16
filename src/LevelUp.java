import java.util.Timer;
import java.util.TimerTask;

public class LevelUp extends TimerTask {
int i=0;
int new_map[][] ;
//Block new_map1;
public static LevelUp lu;
	@Override
	public void run() {
		// TODO Auto-generated method stub
	//	new_map1=new Block(3+i, 7);//initialize all to 1
		new_map =new int[3+i][Block.map[0].length];
		
		i++;
		for(int k=0;k<=Block.map.length;k++) {
			for(int j=0;j<Block.map[0].length;j++) {
				if(k==0)
				{
					new_map[k][j]=1;
				}
				new_map[k][j]=Block.map[k-1][j];//initilize to new map
			}
			
			}
		
	}
	
	public void level_timer() {
		Timer timer = new Timer();
		timer.schedule(new LevelUp(), 0, 5000);
		
	}

}
