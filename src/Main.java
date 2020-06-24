import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	public static Stage primaryStage;

	@Override
	public void start(Stage primaryStage) throws Exception{
		
	    Parent root=FXMLLoader.load(getClass().getResource("MenuScreen.fxml"));
	    primaryStage.setScene(new Scene(root));
	    
	    primaryStage.show();
	   this.primaryStage=primaryStage;
	  

	}

	public static void main(String[] args) {
	    launch(args);
	    
	}
	}

