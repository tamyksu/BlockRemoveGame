	import java.awt.Label;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JFrame;

import javafx.event.ActionEvent;
    import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
	import javafx.scene.control.TextField;
import javafx.stage.Stage;

	public class MainScreenController implements Initializable  {
	public static JFrame object;
	    @FXML
	    private Button start_game_bt;

	    @FXML
	    private TextField best_record;
	    private GameController gamePlay;
	    public JFrame obj;
	    
	    /***********************game_button**************************************/
	    @FXML
	    void game_button(ActionEvent event) throws IOException {
	       
	   
	    	
	    	 obj= new JFrame();
	    	 object=obj;
	    	 
	    	 gamePlay= new GameController();
	    	obj.setBounds(10,10,700,600);
	    	
	    	obj.setResizable(false);
	    	//this.close();
	    	obj.setVisible(true);
	    	obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    	obj.add(gamePlay);
	    	//obj.setVisible(false);
	    	//getGameControllerJFrame();


	    }

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			// TODO Auto-generated method stub
		
			try {
				
				DBConnection db=new DBConnection();
				db.establishDBConnection();
				int result= db.getData();
				best_record.setText(Integer.toString(result));
			} catch (SQLException e) {
				System.out.println("MainScreenController error in initialize");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


	}

