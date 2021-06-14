package FX;

import Classes.SystemData;
import Classes.Usuario;
import Controladoras.ctrSystem;
import Controladoras.ctrUsuario;
import JDBC.Banco;
import Main.FXMLDocumentController;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FXMLSystemController implements Initializable
{
    @FXML
    private JFXTextField tf_nome;
    @FXML
    private JFXTextField tf_big;
    @FXML
    private JFXTextField tf_small;
    @FXML
    private JFXTextField tf_email;
    @FXML
    private JFXTextField tf_fone;
    @FXML
    private JFXTextField tf_usuario;
    @FXML
    private JFXPasswordField tf_senha;
    @FXML
    private Button btn_big;
    @FXML
    private Button btn_small;
    @FXML
    private Button btn_salvar;
    @FXML
    private JFXTextField tf_ramo;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
        tf_usuario.setText("admin");
        tf_senha.setText("admin");
    }    

    @FXML
    private void ClickBig(ActionEvent event)
    {
        getFilePath(tf_big);
    }

    @FXML
    private void ClickSmall(ActionEvent event)
    {
        getFilePath(tf_small);
    }

    @FXML
    private void ClickSalvar(ActionEvent event) throws FileNotFoundException, SQLException
    {
        if(!tf_nome.getText().isEmpty() && !tf_usuario.getText().isEmpty() && !tf_senha.getText().isEmpty())
        {
            new ctrSystem().SetSystem(new SystemData(tf_nome.getText(), tf_ramo.getText(),tf_big.getText(), tf_small.getText(), tf_email.getText(), tf_fone.getText()));
            
            ctrUsuario ctr = new ctrUsuario();
            ctr.Salvar(new Usuario(0, tf_usuario.getText(), tf_senha.getText(), true));
        }
        System.out.println(Banco.getCon().getMensagemErro());
        FXMLDocumentController.STAGE.close();
    }
    
    private void getFilePath(JFXTextField target)
    {
        JFileChooser dialog = new JFileChooser();
        dialog.addChoosableFileFilter(new FileNameExtensionFilter("Images", "jpg", "png"));
        dialog.setFileFilter(dialog.getChoosableFileFilters()[1]);
        
        if(dialog.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
        {
            target.setText(dialog.getSelectedFile().getPath());
        }
    }
}
