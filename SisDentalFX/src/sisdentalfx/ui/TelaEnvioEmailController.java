/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sisdentalfx.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sisdentalfx.util.Email;

/**
 * FXML Controller class
 *
 * @author Aluno
 */
public class TelaEnvioEmailController implements Initializable {

    @FXML
    private ComboBox<?> cbmodelo;
    @FXML
    private Label lbdestino;
    @FXML
    private TextField txtitulo;
    @FXML
    private TextArea txtexto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbdestino.setText(TelaCadPacienteController._email);
    }    

    @FXML
    private void evtModelo(ActionEvent event) {
    }

    @FXML
    private void evtEnviar(ActionEvent event) {
        if(!Email.enviarEmail(lbdestino.getText(), txtitulo.getText(), txtexto.getText()))
        {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Erro ao enviar o email");
            a.showAndWait();
        }
        evtCancelar(event);
    }

    @FXML
    private void evtCancelar(ActionEvent event) {
        Parent root = TelaCadPacienteController._atual;

        TelaPrincipalController._pnDados.getChildren().clear();
        TelaPrincipalController._pnDados.getChildren().add(root);
    }
    
}
