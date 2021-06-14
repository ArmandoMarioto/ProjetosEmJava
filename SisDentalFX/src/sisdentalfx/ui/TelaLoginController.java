package sisdentalfx.ui;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sisdentalfx.db.entidades.Login;
import sisdentalfx.db.entidades.Usuario;
import sisdentalfx.db.util.Banco;
import static sisdentalfx.ui.TelaPrincipalController._btLogout;
import static sisdentalfx.ui.TelaPrincipalController._lbLogin;
import static sisdentalfx.ui.TelaPrincipalController._menuCadastro;
import static sisdentalfx.ui.TelaPrincipalController._menuGerenciamento;
import static sisdentalfx.ui.TelaPrincipalController._menuRelatorios;
import static sisdentalfx.ui.TelaPrincipalController._pnDados;
import static sisdentalfx.ui.TelaPrincipalController._pnMenu;

public class TelaLoginController implements Initializable {

    @FXML
    private TextField tfUsuario;
    @FXML
    private PasswordField tfSenha;
    @FXML
    private Button btConectar;
    @FXML
    private Button btCancelar;

    public void permissao(Login l) {
        _pnMenu.setDisable(false);
        _lbLogin.setText("Usuário:" + l.getUsuario());
        _btLogout.setDisable(false);

        if (l.getNivel() == 2) {
            _menuRelatorios.setDisable(true);
            _menuGerenciamento.setDisable(true);
        } else if (l.getNivel() == 3) {
            _menuCadastro.setDisable(true);
            _menuGerenciamento.setDisable(true);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tfUsuario.requestFocus();
    }

    @FXML
    private void evtBtConectar(ActionEvent event) {
        Login l = null;
        Usuario u = null;
        String sql = "select * from usuario where usu_nome = '" + tfUsuario.getText() + "' and usu_senha = '" + tfSenha.getText() + "'";
        ResultSet rs = Banco.con.consultar(sql);

        try {
            if (rs.next()) {
                u = new Usuario(rs.getString("usu_nome"), rs.getInt("usu_Nivel"), rs.getString("usu_senha"));
            }

            l = new Login(u.getNome(), u.getSenha(), true, u.getNivel());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro ao logar.");
            alert.setHeaderText("Usuário ou Senha Inválidos.");
            alert.showAndWait();

            tfUsuario.setText("");
            tfSenha.setText("");
            tfUsuario.requestFocus();
        }
       
        if (l.isTipo()) {
            _pnDados.getChildren().clear();
            permissao(l);
        }
    }

    @FXML
    private void evtBtCancelar(ActionEvent event) {
        Platform.exit();
    }
}
