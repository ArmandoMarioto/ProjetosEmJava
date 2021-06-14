
package Interfaces;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class TelaLoginController implements Initializable
{

    @FXML
    private JFXTextField txbUsuario;
    @FXML
    private JFXPasswordField txbSenha;
    @FXML
    private JFXButton btnLogin;
    @FXML
    private JFXButton btnCancelar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }

}
