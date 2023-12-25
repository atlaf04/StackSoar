package UI;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Controller_SplashScreen {
	@FXML 
	private AnchorPane anchorpane;
	@FXML 
	private ImageView image;
	@FXML 
	private Button gettingstarted;
	
	private Stage primarystage;
	private Scene scene;
	private Parent root;
	
	
	public void handlestartbutton(ActionEvent e)   {
		
		try {
            //Button clicked, switch to the "Login.fxml" scene

		root = FXMLLoader.load(getClass().getResource("Login.fxml"));
		
		primarystage = (Stage)((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(root,900,600);
		primarystage.setScene(scene);
		primarystage.show();
		}
		catch (Exception ex) {
			
		}
	}

}