package sisdentalfx.ui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import sisdentalfx.db.controles.CtrPessoa;
import sisdentalfx.db.entidades.Pessoa;
import sisdentalfx.db.entidades.Usuario;
import sisdentalfx.db.util.Banco;
import static sisdentalfx.ui.TelaPrincipalController._lbTelaCadAtual;
import sisdentalfx.util.MaskCampos;

public class TelaCadUsuarioController implements Initializable {

    @FXML
    private AnchorPane pnDados;
    @FXML
    private TextField tfCodigo;
    @FXML
    private TextField tfNome;
    @FXML
    private TextField tfNivel;
    @FXML
    private PasswordField tfSenha;
    @FXML
    private PasswordField tfConfSenha;
    @FXML
    private Button btNovo;
    @FXML
    private Button btAlterar;
    @FXML
    private Button btApagar;
    @FXML
    private Button btConfirmar;
    @FXML
    private Button btCancelar;
    @FXML
    private TextField tfBuscar;
    @FXML
    private Button btBuscar;
    @FXML
    private TableView<Pessoa> table;
    @FXML
    private TableColumn colCodigo;
    @FXML
    private TableColumn colNome;
    @FXML
    private TableColumn colNivel;

    private void estadoOriginal() {
        btAlterar.setDisable(true);
        btApagar.setDisable(true);
        btConfirmar.setDisable(true);
        btBuscar.setDisable(false);
        btCancelar.setDisable(false);
        btNovo.setDisable(false);
        pnDados.setDisable(true);
        table.setDisable(false);
        tfNome.setDisable(false);
        tfNivel.setDisable(false);
        tfBuscar.setDisable(false);
        tfBuscar.requestFocus();

        ObservableList<Node> componentes = pnDados.getChildren();

        for (Node n : componentes) {
            if (n instanceof TextInputControl) {
                ((TextInputControl) n).setText("");
            }
        }

        carregaTabela("");
    }

    private void carregaTabela(String filtro) {
        CtrPessoa ctrP = new CtrPessoa();
        ArrayList<Pessoa> resultado = ctrP.getPessoa(filtro, new Usuario());

        ObservableList<Pessoa> modelo = FXCollections.observableArrayList(resultado);
        table.setItems(modelo);
    }

    private void estadoEdicao() {
        pnDados.setDisable(false);
        btNovo.setDisable(true);
        btConfirmar.setDisable(false);
        btApagar.setDisable(true);
        btAlterar.setDisable(true);
        tfBuscar.setDisable(true);
        btBuscar.setDisable(true);
        table.setDisable(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tfCodigo.setDisable(true);
        MaskCampos.maxField(tfNome, 40);
        MaskCampos.numericField(tfNivel, 1);
        MaskCampos.maxField(tfSenha, 15);
        MaskCampos.maxField(tfConfSenha, 15);

        colCodigo.setCellValueFactory(new PropertyValueFactory("codigo"));
        colNome.setCellValueFactory(new PropertyValueFactory("nome"));
        colNivel.setCellValueFactory(new PropertyValueFactory("nivel"));

        estadoOriginal();
    }

    public boolean validaCampos() //Validações de banco
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);

        if (tfNome.getText().trim().isEmpty()) {
            alert.setHeaderText("Campo 'Nome' é um campo requerido.");
            alert.showAndWait();
            tfNome.setText("");
            tfNome.requestFocus();
            return false;
        }
        if (tfNivel.getText().trim().isEmpty()) {
            alert.setHeaderText("Campo 'Nível' é um campo requerido.");
            alert.showAndWait();
            tfNivel.setText("");
            tfNivel.requestFocus();
            return false;
        }
        if (!tfNivel.getText().equals("1") && !tfNivel.getText().equals("2")) {
            alert.setHeaderText("Campo 'Nível' só aceita valores de 1 a 2.\nTodo Gerenciamento de Nível 3, é Feito na Tela 'Cadastro de Dentista'.");
            alert.showAndWait();
            tfNivel.setText("");
            tfNivel.requestFocus();
            return false;
        }
        if (tfSenha.getText().trim().isEmpty()) {
            alert.setHeaderText("Campo 'Senha' é um campo requerido.");
            alert.showAndWait();
            tfSenha.setText("");
            tfSenha.requestFocus();
            return false;
        }
        if (!tfSenha.getText().equals(tfConfSenha.getText())) {
            alert.setHeaderText("As senhas diferentes !!!");
            alert.showAndWait();
            tfSenha.setText("");
            tfConfSenha.setText("");
            tfSenha.requestFocus();
            return false;
        }
        return true;
    }

    @FXML
    private void evtBtNovo(ActionEvent event) {
        estadoEdicao();
    }

    @FXML
    private void evtBtAlterar(ActionEvent event) {
        Usuario u = (Usuario) table.getSelectionModel().getSelectedItem();
        CtrPessoa ctrP = new CtrPessoa();

        ctrP.alterar(u);
        estadoEdicao();
    }

    @FXML
    private void evtBtApagar(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Confirmar a excluão de: \n" + tfNome.getText());

        if (alert.showAndWait().get() == ButtonType.OK) {
            CtrPessoa ctrP = new CtrPessoa();
            ctrP.apagar(new Usuario(Integer.parseInt(tfCodigo.getText()), "", 0, ""));
            estadoOriginal();
        }
    }

    @FXML
    private void evtBtConfirmar(ActionEvent event) {
        int cod;
        CtrPessoa ctrP = new CtrPessoa();
        Usuario u;
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        try {
            cod = Integer.parseInt(tfCodigo.getText());
        } catch (Exception e) {
            cod = 0;
        }

        if (cod == 0)// novo cadastro
        {
            if (validaCampos()) //função que valida compos NOT NULL e tamanho no banco
            {
                //Depois de validar os canpos NOT NULL e seus respectivos tamanhos
                u = new Usuario(tfNome.getText(), Integer.parseInt(tfNivel.getText()), tfSenha.getText());

                if (ctrP.salvar(u)) {
                    alert.setHeaderText("Usuário cadastrado com sucesso !!!");
                } else {
                    alert.setHeaderText("Erro ao cadastrar Usuário: \n" + Banco.con.getMensagemErro());
                }

                alert.showAndWait();
                estadoOriginal();
            }
        } else //Alterando cadastro
        {
            if (validaCampos())//função que valida compos NOT NULL e tamanho no banco
            {
                //Depois de validar os canpos NOT NULL e seus respectivos tamanhos
                u = new Usuario(Integer.parseInt(tfCodigo.getText()), tfNome.getText(), Integer.parseInt(tfNivel.getText()), tfSenha.getText());

                if (ctrP.alterar(u)) {
                    alert.setHeaderText("Usuário alterado com sucesso !!!");
                } else {
                    alert.setHeaderText("Erro ao alterar usuário: \n" + Banco.con.getMensagemErro());
                }

                alert.showAndWait();
                estadoOriginal();
            }
        }
    }

    @FXML
    private void evtCancelar(ActionEvent event) {
        if ((pnDados.isDisable() && !tfNome.getText().isEmpty()) || (!pnDados.isDisable() && !tfNome.getText().isEmpty())) {
            estadoOriginal();
        } else {
            _lbTelaCadAtual.setText("");
            TelaPrincipalController._pnDados.getChildren().clear();
        }
    }

    @FXML
    private void evtBtBuscar(ActionEvent event) {
        carregaTabela("upper(usu_nome) like '%" + tfBuscar.getText().toUpperCase() + "%'");
        tfBuscar.setText("");
    }

    @FXML
    private void evtTable(MouseEvent event) {
        if (table.getSelectionModel().getSelectedIndex() >= 0) {
            Usuario u = (Usuario) table.getSelectionModel().getSelectedItem();

            tfCodigo.setText("" + u.getCodigo());
            tfNome.setText(u.getNome());
            tfNivel.setText("" + u.getNivel());
            tfSenha.setText(u.getSenha());
            tfConfSenha.setText(u.getSenha());

            btAlterar.setDisable(false);
            btApagar.setDisable(false);
            btNovo.setDisable(true);
            table.setDisable(true);
            btBuscar.setDisable(true);
            tfBuscar.setDisable(true);

            if (tfNivel.getText().equals("3")) {
                tfNome.setDisable(true);
                tfNivel.setDisable(true);
                btApagar.setDisable(true);
                btAlterar.setDisable(true);
            } else {
                tfNome.setDisable(false);
                tfNivel.setDisable(false);
                btApagar.setDisable(false);
            }
        }
    }
}
