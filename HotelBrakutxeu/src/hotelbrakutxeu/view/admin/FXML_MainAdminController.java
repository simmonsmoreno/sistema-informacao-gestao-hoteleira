package hotelbrakutxeu.view.admin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class FXML_MainAdminController implements Initializable {

    @FXML
    private BorderPane borderpane;
    @FXML
    private Button btnHome;
    @FXML
    private Button btnReservas;
    @FXML
    private Button btnQuartos;
    @FXML
    private Button btnFuncionarios;
    @FXML
    private Button btnIndicacoes;
    @FXML
    private Button btnCentroAjuda;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        carregarTela("FXML_AdminHome.fxml");
        btnHome.setStyle("-fx-background-color: #1d2a45; -fx-text-fill: #ce9e6d;");
        btnReservas.setStyle("-fx-background-color: transparent; -fx-text-fill: #576271;");
        btnQuartos.setStyle("-fx-background-color: transparent; -fx-text-fill: #576271;");
        btnFuncionarios.setStyle("-fx-background-color: transparent; -fx-text-fill: #576271;");
        btnIndicacoes.setStyle("-fx-background-color: transparent; -fx-text-fill: #576271;");
        btnCentroAjuda.setStyle("-fx-background-color: transparent; -fx-text-fill: #576271;");
    }    

    @FXML
    private void home(MouseEvent event) {
        carregarTela("FXML_AdminHome.fxml");
        btnHome.setStyle("-fx-background-color: #1d2a45; -fx-text-fill: #ce9e6d;");
        btnReservas.setStyle("-fx-background-color: transparent; -fx-text-fill: #576271;");
        btnQuartos.setStyle("-fx-background-color: transparent; -fx-text-fill: #576271;");
        btnFuncionarios.setStyle("-fx-background-color: transparent; -fx-text-fill: #576271;");
        btnIndicacoes.setStyle("-fx-background-color: transparent; -fx-text-fill: #576271;");
        btnCentroAjuda.setStyle("-fx-background-color: transparent; -fx-text-fill: #576271;");
    }

    @FXML
    private void reservas(MouseEvent event) {
        carregarTela("FXML_AdminReservas.fxml");
        btnHome.setStyle("-fx-background-color: transparent; -fx-text-fill: #576271;");
        btnReservas.setStyle("-fx-background-color: #1d2a45; -fx-text-fill: #ce9e6d;");
        btnQuartos.setStyle("-fx-background-color: transparent; -fx-text-fill: #576271;");
        btnFuncionarios.setStyle("-fx-background-color: transparent; -fx-text-fill: #576271;");
        btnIndicacoes.setStyle("-fx-background-color: transparent; -fx-text-fill: #576271;");
        btnCentroAjuda.setStyle("-fx-background-color: transparent; -fx-text-fill: #576271;");
    }

    @FXML
    private void quartos(MouseEvent event) {
        carregarTela("FXML_AdminQuartos.fxml");
        btnHome.setStyle("-fx-background-color: transparent; -fx-text-fill: #576271;");
        btnReservas.setStyle("-fx-background-color: transparent; -fx-text-fill: #576271;");
        btnQuartos.setStyle("-fx-background-color: #1d2a45; -fx-text-fill: #ce9e6d;");
        btnFuncionarios.setStyle("-fx-background-color: transparent; -fx-text-fill: #576271;");
        btnIndicacoes.setStyle("-fx-background-color: transparent; -fx-text-fill: #576271;");
        btnCentroAjuda.setStyle("-fx-background-color: transparent; -fx-text-fill: #576271;");
    }

    @FXML
    private void funcionarios(MouseEvent event) {
        carregarTela("FXML_AdminFuncionarios.fxml");
        btnHome.setStyle("-fx-background-color: transparent; -fx-text-fill: #576271;");
        btnReservas.setStyle("-fx-background-color: transparent; -fx-text-fill: #576271;");
        btnQuartos.setStyle("-fx-background-color: transparent; -fx-text-fill: #576271;");
        btnFuncionarios.setStyle("-fx-background-color: #1d2a45; -fx-text-fill: #ce9e6d;");
        btnIndicacoes.setStyle("-fx-background-color: transparent; -fx-text-fill: #576271;");
        btnCentroAjuda.setStyle("-fx-background-color: transparent; -fx-text-fill: #576271;");
    }

    @FXML
    private void indicacoes(MouseEvent event) {
        carregarTela("FXML_AdminIndicacoes.fxml");
        btnHome.setStyle("-fx-background-color: transparent; -fx-text-fill: #576271;");
        btnReservas.setStyle("-fx-background-color: transparent; -fx-text-fill: #576271;");
        btnQuartos.setStyle("-fx-background-color: transparent; -fx-text-fill: #576271;");
        btnFuncionarios.setStyle("-fx-background-color: transparent; -fx-text-fill: #576271;");
        btnIndicacoes.setStyle("-fx-background-color: #1d2a45; -fx-text-fill: #ce9e6d;");
        btnCentroAjuda.setStyle("-fx-background-color: transparent; -fx-text-fill: #576271;");
    }

    @FXML
    private void centroAjuda(MouseEvent event) {
        carregarTela("FXML_AdminCentroAjuda.fxml");
        btnHome.setStyle("-fx-background-color: transparent; -fx-text-fill: #576271;");
        btnReservas.setStyle("-fx-background-color: transparent; -fx-text-fill: #576271;");
        btnQuartos.setStyle("-fx-background-color: transparent; -fx-text-fill: #576271;");
        btnFuncionarios.setStyle("-fx-background-color: transparent; -fx-text-fill: #576271;");
        btnIndicacoes.setStyle("-fx-background-color: transparent; -fx-text-fill: #576271;");
        btnCentroAjuda.setStyle("-fx-background-color: #1d2a45; -fx-text-fill: #ce9e6d;");
    }
    
    private void carregarTela(String tela){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/hotelbrakutxeu/view/admin/" +tela));
            borderpane.setCenter(root);
        } catch (IOException ex) {
            Logger.getLogger(FXML_MainAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
