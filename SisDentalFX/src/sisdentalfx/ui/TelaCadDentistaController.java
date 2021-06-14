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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import sisdentalfx.db.controles.CtrPessoa;
import sisdentalfx.db.entidades.Dentista;
import sisdentalfx.db.entidades.Pessoa;
import sisdentalfx.db.entidades.Usuario;
import sisdentalfx.db.util.Banco;
import static sisdentalfx.ui.TelaPrincipalController._lbTelaCadAtual;
import sisdentalfx.util.MaskCampos;

public class TelaCadDentistaController implements Initializable {

    @FXML
    private Button btCancelar;
    @FXML
    private Button btAlterar;
    @FXML
    private Button btApagar;
    @FXML
    private Button btConfirmar;
    @FXML
    private TableView<Pessoa> table;
    @FXML
    private AnchorPane pnDados;
    @FXML
    private TextField tfCodigo;
    @FXML
    private TextField tfNome;
    @FXML
    private TextField tfCro;
    @FXML
    private TextField tfTelefone;
    @FXML
    private TextField tfEmail;
    @FXML
    private Button btNovo;
    @FXML
    private TextField tfBuscar;
    @FXML
    private Button btBuscar;
    @FXML
    private TableColumn colCodigo;
    @FXML
    private TableColumn colNome;

    public String NomeAntigo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Colocando mascaras
        MaskCampos.maxField(tfNome, 40);
        MaskCampos.maxField(tfEmail, 40);
        MaskCampos.numericField(tfCro, 10);
        MaskCampos.numericField(tfTelefone, 11);

        tfCodigo.setDisable(true);

        colCodigo.setCellValueFactory(new PropertyValueFactory("codigo"));
        colNome.setCellValueFactory(new PropertyValueFactory("nome"));

        estadoOriginal();
    }

    private void estadoOriginal() {
        NomeAntigo = "";
        btAlterar.setDisable(true);
        btApagar.setDisable(true);
        btConfirmar.setDisable(true);
        btBuscar.setDisable(false);
        btCancelar.setDisable(false);
        btNovo.setDisable(false);
        pnDados.setDisable(true);
        table.setDisable(false);
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
        ArrayList<Pessoa> resultado = ctrP.getPessoa(filtro, new Dentista());

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

    @FXML
    private void evtCancelar(ActionEvent event) {

        if ((pnDados.isDisable() && !tfNome.getText().isEmpty()) || (!pnDados.isDisable() && !tfNome.getText().isEmpty())) {
            estadoOriginal();
        } else {
            _lbTelaCadAtual.setText("");
            TelaPrincipalController._pnDados.getChildren().clear();
        }
    }

    private boolean validaCampos() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        //validando nome
        if (tfNome.getText().trim().isEmpty()) {
            alert.setHeaderText("Campo 'Nome' é um campo requerido.");
            alert.showAndWait();
            tfNome.setText("");
            tfNome.requestFocus();
            return false;
        }
        //validando CRO
        if (tfCro.getText().trim().isEmpty()) {
            alert.setHeaderText("Campo 'CRO' é um campo requerido.");
            alert.showAndWait();
            tfCro.setText("");
            tfCro.requestFocus();
            return false;
        }
        //validando Telefone
        if (tfTelefone.getText().trim().isEmpty()) {
            alert.setHeaderText("Campo 'Telefone' é um campo requerido.");
            alert.showAndWait();
            tfTelefone.setText("");
            tfTelefone.requestFocus();
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
        CtrPessoa ctrP = new CtrPessoa();

        Dentista d = (Dentista) table.getSelectionModel().getSelectedItem();
        Usuario u = (Usuario) ctrP.getPessoa(d.getNome(), d.getNome());

        ctrP.alterar(d);
        ctrP.alterar(u);
        estadoEdicao();
    }

    @FXML
    private void evtBtApagar(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Confirmar a excluão de: \n" + tfNome.getText());

        if (alert.showAndWait().get() == ButtonType.OK) {
            CtrPessoa ctrP = new CtrPessoa();

            Usuario u = (Usuario) ctrP.getPessoa(tfNome.getText(), tfNome.getText());

            ctrP.apagar(new Dentista(Integer.parseInt(tfCodigo.getText()), "", "", "", "", null));
            ctrP.apagar(new Usuario(u.getCodigo(), "", 0, ""));
            estadoOriginal();
        }
    }

    @FXML
    private void evtBtConfimar(ActionEvent event) {
        int cod;
        CtrPessoa ctrP = new CtrPessoa();
        Dentista d;
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
                /*Gravando dentista, na tabela de dentista e de usuario !!!*/
                //Depois de validar os canpos NOT NULL e seus respectivos tamanhos
                d = new Dentista(tfNome.getText(), tfCro.getText(), tfTelefone.getText(), tfEmail.getText(), null);
                u = new Usuario(tfNome.getText(), 3, tfNome.getText());

                if (ctrP.salvar(d) && ctrP.salvar(u)) {
                    alert.setHeaderText("Dentista cadastrado com sucesso !!!");
                } else {
                    alert.setHeaderText("Erro ao cadastrar Dentitsta: \n" + Banco.con.getMensagemErro());
                }

                alert.showAndWait();
                estadoOriginal();
            }
        } else //Alterando cadastro
        {
            if (validaCampos())//função que valida compos NOT NULL e tamanho no banco
            {
                //pesquisando o usuario com os mesmos dados do dentitas
                Usuario u2 = (Usuario) ctrP.getPessoa(NomeAntigo, NomeAntigo);

                //Depois de validar os canpos NOT NULL e seus respectivos tamanhos
                d = new Dentista(Integer.parseInt(tfCodigo.getText()), tfNome.getText(), tfCro.getText(), tfTelefone.getText(), tfEmail.getText(), null);
                u = new Usuario(u2.getCodigo(), tfNome.getText(), 3, tfNome.getText());

                if (ctrP.alterar(d) && ctrP.alterar(u)) {
                    alert.setHeaderText("Dentista alterado com sucesso !!!");
                } else {
                    alert.setHeaderText("Erro ao alterar dentista: \n" + Banco.con.getMensagemErro());
                }

                alert.showAndWait();
                estadoOriginal();
            }
        }
    }

    @FXML
    private void evtBtBuscar(ActionEvent event) {
        carregaTabela("upper(den_nome) like '%" + tfBuscar.getText().toUpperCase() + "%'");
        tfBuscar.setText("");
    }

    @FXML
    private void evtTable(MouseEvent event) {
        if (table.getSelectionModel().getSelectedIndex() >= 0) {
            Dentista d = (Dentista) table.getSelectionModel().getSelectedItem();

            NomeAntigo = d.getNome(); //salvando o nome antigo para alterar

            tfCodigo.setText("" + d.getCodigo());
            tfNome.setText(d.getNome());
            tfEmail.setText(d.getEmail());
            tfCro.setText(d.getCRO());
            tfTelefone.setText(d.getTelefone());

            btAlterar.setDisable(false);
            btApagar.setDisable(false);
            btNovo.setDisable(true);
            table.setDisable(true);
            btBuscar.setDisable(true);
            tfBuscar.setDisable(true);
        }
    }
}
