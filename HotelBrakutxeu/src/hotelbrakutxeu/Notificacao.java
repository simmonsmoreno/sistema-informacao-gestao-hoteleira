package hotelbrakutxeu;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 *
 * @author pc
 */
public class Notificacao {
    
    @FXML
    public void showNotificacao(String title, String text) {
        
        //Image image = new Image("gestaorestaurantesfx/imagens/checkmark_done_complete.png");
        Notifications notificationBuilder = Notifications.create()
                .title(title)
                .text(text)
                //.graphic(new ImageView(image))
                .hideAfter(Duration.seconds(5))
                .position(Pos.TOP_RIGHT);
        notificationBuilder.darkStyle();
        notificationBuilder.show();
    }
    
    public void showErro(String text){
        
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setContentText(text);
        alert.show();
    }
}
