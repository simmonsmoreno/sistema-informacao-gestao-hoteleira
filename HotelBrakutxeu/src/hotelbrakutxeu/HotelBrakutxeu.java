package hotelbrakutxeu;

import hotelbrakutxeu.model.database.ConnectionFactory;
import java.sql.Connection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author pc
 */
public class HotelBrakutxeu extends Application {
    
    public static Stage stage = null;
    private double x, y;
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/FXML_Login.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Connection conn = ConnectionFactory.getConnection();
        launch(args);
        
    }
}
