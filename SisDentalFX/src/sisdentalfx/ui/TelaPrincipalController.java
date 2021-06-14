package sisdentalfx.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import sisdentalfx.db.util.Banco;

public class TelaPrincipalController implements Initializable {

    // componentes publicados
    public static HBox _pnDados;
    public static Accordion _pnMenu;
    public static Label _lbTelaCadAtual;
    public static Label _lbLogin;
    public static TitledPane _menuCadastro;
    public static TitledPane _menuConsultas;
    public static TitledPane _menuRelatorios;
    public static TitledPane _menuGerenciamento;
    public static Button _btLogout;

    @FXML
    private HBox pnDados;
    @FXML
    private Accordion pnMenu;
    @FXML
    private Label lbTelaCadAtual;
    @FXML
    private Label lbLogin;
    @FXML
    private Label lbPaciente;
    @FXML
    private Label lbDentista;
    @FXML
    private Label lbMaterial;
    @FXML
    private Label lbProcedimentos;
    @FXML
    private Label lbAgendar;
    @FXML
    private Label lbRegAtendimento;
    @FXML
    private Label lbUsuario;
    @FXML
    private Label lbBackup;
    @FXML
    private Label lbRestore;
    @FXML
    private TitledPane menuCadastro;
    @FXML
    private TitledPane menuConsultas;
    @FXML
    private TitledPane menuRelatorios;
    @FXML
    private TitledPane menuGerenciamento;
    @FXML
    private Button btLogout;

    private void logar() {
        lbTelaCadAtual.setText("");
        lbLogin.setText("");
        btLogout.setDisable(true);
        pnMenu.setDisable(true);
        menuCadastro.setDisable(false);
        menuConsultas.setDisable(false);
        menuRelatorios.setDisable(false);
        menuGerenciamento.setDisable(false);

        try {
            Parent root = FXMLLoader.load(getClass().getResource("TelaLogin.fxml"));

            pnDados.getChildren().clear();
            pnDados.getChildren().add(root);
        } catch (IOException ex) {
            //
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        _pnDados = pnDados;
        _lbTelaCadAtual = lbTelaCadAtual;
        _pnMenu = pnMenu;
        _menuCadastro = menuCadastro;
        _menuConsultas = menuConsultas;
        _menuRelatorios = menuRelatorios;
        _menuGerenciamento = menuGerenciamento;
        _lbLogin = lbLogin;
        _btLogout = btLogout;

        logar();
    }

    @FXML
    private void evtDentista(MouseEvent event) {
        try {
            lbTelaCadAtual.setText("Cadastro de " + lbDentista.getText());
            Parent root = FXMLLoader.load(getClass().getResource("TelaCadDentista.fxml"));

            pnDados.getChildren().clear();
            pnDados.getChildren().add(root);
        } catch (IOException ex) {
            //
        }
    }

    @FXML
    private void evtPaciente(MouseEvent event) {
        try {
            lbTelaCadAtual.setText("Cadastro de " + lbPaciente.getText());
            Parent root = FXMLLoader.load(getClass().getResource("TelaCadPaciente.fxml"));

            pnDados.getChildren().clear();
            pnDados.getChildren().add(root);
        } catch (Exception e) {
            //
        }
    }

    @FXML
    private void evtMaterial(MouseEvent event) {
        try {
            lbTelaCadAtual.setText("Cadastro de " + lbMaterial.getText());
            Parent root = FXMLLoader.load(getClass().getResource("TelaCadMaterial.fxml"));

            pnDados.getChildren().clear();
            pnDados.getChildren().add(root);
        } catch (Exception e) {
            //erro de abertura
        }
    }

    @FXML
    private void evtUsuario(MouseEvent event) {
        try {
            lbTelaCadAtual.setText("Cadastro de " + lbUsuario.getText());
            Parent root = FXMLLoader.load(getClass().getResource("TelaCadUsuario.fxml"));

            pnDados.getChildren().clear();
            pnDados.getChildren().add(root);
        } catch (Exception e) {
            //erro de abertura
        }
    }

    @FXML
    private void evtProcedimento(MouseEvent event) {
        try {
            lbTelaCadAtual.setText("Cadastro de " + lbProcedimentos.getText());
            Parent root = FXMLLoader.load(getClass().getResource("TelaCadProcedimentos.fxml"));

            pnDados.getChildren().clear();
            pnDados.getChildren().add(root);
        } catch (Exception e) {
            //erro de abertura
        }
    }

    @FXML
    private void evtLogout(ActionEvent event) {
        logar();
    }

    @FXML
    private void evtAgendar(MouseEvent event) {
        try {
            lbTelaCadAtual.setText(lbAgendar.getText());
            Parent root = FXMLLoader.load(getClass().getResource("TelaCadAgendamento.fxml"));

            pnDados.getChildren().clear();
            pnDados.getChildren().add(root);
        } catch (Exception e) {
            //erro de abertura
        }
    }

    @FXML
    private void evtRegAtendimento(MouseEvent event) {
        try {
            lbTelaCadAtual.setText(lbRegAtendimento.getText());
            Parent root = FXMLLoader.load(getClass().getResource("TelaRegAtendimento.fxml"));

            pnDados.getChildren().clear();
            pnDados.getChildren().add(root);
        } catch (Exception e) {
            //erro de abertura
        }
    }

    @FXML
    private void evtBackup(MouseEvent event) {
        VBox painel = new VBox();
        TextArea ta = new TextArea();
        painel.getChildren().add(ta);
        pnDados.getChildren().clear();
        pnDados.getChildren().add(painel);
        
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        if(Banco.realizaBackupRestauracao("copiar.bat", ta))
            a.setContentText("Backup efetuado!");
        else
            a.setContentText("Erro ao efetuar o backup!");
       
        a.showAndWait();
    }

    @FXML
    private void evtRestore(MouseEvent event) {
         VBox painel = new VBox();
        TextArea ta = new TextArea();
        painel.getChildren().add(ta);
        pnDados.getChildren().clear();
        pnDados.getChildren().add(painel);
         Alert a = new Alert(Alert.AlertType.INFORMATION);
        if(Banco.realizaBackupRestauracao("restaurar.bat",ta))
            a.setContentText("Restauração efetuada!");
        else
            a.setContentText("Erro ao efetuar a restauração!");
        a.showAndWait();
    }
}
