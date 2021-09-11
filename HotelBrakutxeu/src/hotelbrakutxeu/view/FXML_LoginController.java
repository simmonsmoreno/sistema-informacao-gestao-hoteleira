package hotelbrakutxeu.view;

import hotelbrakutxeu.Notificacao;
import hotelbrakutxeu.model.dao.FuncionarioDAO;
import hotelbrakutxeu.model.domain.Funcionario;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class FXML_LoginController implements Initializable {

    @FXML
    private Pane parent;
    @FXML
    private Label lblErro;
    @FXML
    private TextField txtUser;
    @FXML
    private PasswordField txtSenha;
    
    boolean achou = false;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        lblErro.setVisible(false);
    }    

    @FXML
    private void handleEntrar(ActionEvent event) throws IOException {
        
        for(Funcionario f: new FuncionarioDAO().listar()){
            
            //testar se existe funcionario com o nome ou email e a senha introduzidos
            if((f.getNome().equalsIgnoreCase(txtUser.getText()) || 
                    f.getEmail().equalsIgnoreCase(txtUser.getText())) && 
                    f.getSenha().equals(txtSenha.getText())){
                
                //new FXMLHomeController().setfOnline(f);
                achou = true;
                
                System.out.println(f.toString());
                
                switch(f.getCargo()){
                    case "admin": abrirTela("/hotelbrakutxeu/view/admin/FXML_MainAdmin.fxml"); break;
                    case "recep": abrirTela("/hotelbrakutxeu/view/recep/FXML_MainRecep.fxml"); break;
                    default: System.out.println("Erro em detetar cargo do administrador");
                }
                
                //mostrar notificação com o nome do funcionario e o cargo
                new Notificacao().showNotificacao("Bem-Vindo ", f.getNome());
                
                //não verificar mais nenhum funcionario
                break;
            } 
        }
        
        //se não encontrou nenhum funcionario mostrar erro
        if(!achou) lblErro.setVisible(true);
    
    }
    
    public void abrirTela(String tela) throws IOException{
        
        //criar um estágio para a tela principal
        Stage stageTelaPrincipal = new Stage();
        
        //pegar o estágio da tela de login (atual) e fechar 
        Stage stageLogin = (Stage) parent.getScene().getWindow();
        stageLogin.close();

        //criar um parente root que é a tela que vamos apresentar
        Parent root = FXMLLoader.load(getClass().getResource(tela));
        
        Scene scene = new Scene(root);
        
        stageTelaPrincipal.setScene(scene);
        stageTelaPrincipal.setResizable(false);
        stageTelaPrincipal.show();
    }

    
}
