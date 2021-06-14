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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import sisdentalfx.db.controles.CtrProcedimentos;
import sisdentalfx.db.entidades.Procedimentos;
import sisdentalfx.db.util.Banco;
import static sisdentalfx.ui.TelaPrincipalController._lbTelaCadAtual;
import sisdentalfx.util.MaskCampos;

public class TelaCadProcedimentosController implements Initializable {

    @FXML
    private TextField tfCodigo;
    @FXML
    private TextArea tfDescricao;
    @FXML
    private TextField tfTempo;
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
    private TableView<Procedimentos> table;
    @FXML
    private TableColumn colCodigo;
    @FXML
    private TableColumn colDescricao;
    @FXML
    private TableColumn colPreco;
    @FXML
    private TableColumn colTempo;
    @FXML
    private AnchorPane pnDados;
    @FXML
    private TextField tfPreco;

    public void estadoOriginal() {
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

        ObservableList<Node> componentes = pnDados.getChildren(); // limpando os campos da tela

        for (Node n : componentes) {
            if (n instanceof TextInputControl) {
                ((TextInputControl) n).setText("");
            }
        }

        carregaTabela("");
    }

    public void carregaTabela(String filtro) {
        CtrProcedimentos ctrP = new CtrProcedimentos();
        ArrayList<Procedimentos> resultado = ctrP.getProcedimentos(filtro);

        ObservableList<Procedimentos> modelo = FXCollections.observableArrayList(resultado);
        table.setItems(modelo);
    }

    public void estadoEdicao() {
        pnDados.setDisable(false);
        btNovo.setDisable(true);
        btConfirmar.setDisable(false);
        btApagar.setDisable(true);
        btAlterar.setDisable(true);
        tfBuscar.setDisable(true);
        btBuscar.setDisable(true);
        table.setDisable(true);
    }

    public boolean validaCampos() // função para validar campos em branco ou muito extensos 
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        if (tfDescricao.getText().trim().isEmpty()) {
            alert.setHeaderText("Campo 'Descrição' é um campo requerido.");
            alert.showAndWait();
            tfDescricao.setText("");
            tfDescricao.requestFocus();
            return false;
        }
        if (tfDescricao.getText().length() > 200) {
            alert.setHeaderText("Campo 'Descrição' longo de mais.");
            alert.showAndWait();
            tfDescricao.setText("");
            tfDescricao.requestFocus();
            return false;
        }
        if (tfPreco.getText().trim().isEmpty()) {
            alert.setHeaderText("Campo 'Preço' é um campo requerido.");
            alert.showAndWait();
            tfPreco.setText("");
            tfPreco.requestFocus();
            return false;
        }
        if (tfTempo.getText().trim().isEmpty()) {
            alert.setHeaderText("Campo 'Tempo' é um campo requerido.");
            alert.showAndWait();
            tfTempo.setText("");
            tfTempo.requestFocus();
            return false;
        }
        return true;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tfCodigo.setDisable(true);
        MaskCampos.monetaryField(tfPreco);
        MaskCampos.numericField(tfTempo, 6);

        colCodigo.setCellValueFactory(new PropertyValueFactory("codigo"));
        colDescricao.setCellValueFactory(new PropertyValueFactory("descricao"));
        colPreco.setCellValueFactory(new PropertyValueFactory("preco"));
        colTempo.setCellValueFactory(new PropertyValueFactory("tempo"));

        estadoOriginal();
    }

    @FXML
    private void evtBtNovo(ActionEvent event) {
        estadoEdicao();
    }

    @FXML
    private void evtBtAlterar(ActionEvent event) {
        CtrProcedimentos ctrP = new CtrProcedimentos();
        Procedimentos p = table.getSelectionModel().getSelectedItem();

        ctrP.alterar(p);
        estadoEdicao();
    }

    @FXML
    private void evtBtApagar(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Confirmar a excluão de: \n" + tfDescricao.getText());

        if (alert.showAndWait().get() == ButtonType.OK) {
            CtrProcedimentos ctrP = new CtrProcedimentos();
            ctrP.apagar(new Procedimentos(Integer.parseInt(tfCodigo.getText()), "", 0, 0));
            estadoOriginal();
        }
    }

    @FXML
    private void evtBtConfirmar(ActionEvent event) {
        int cod;

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        CtrProcedimentos ctrP = new CtrProcedimentos();
        Procedimentos p;

        try {
            cod = Integer.parseInt(tfCodigo.getText());
        } catch (Exception e) {
            cod = 0;
        }

        if (cod == 0) //Novo cadastro
        {
            if (validaCampos())//Validando o banco de Dados
            {
                //Depois de validar os canpos NOT NULL e seus respectivos tamanhos
                p = new Procedimentos(tfDescricao.getText(), Double.parseDouble(tfPreco.getText().replace(".", "").replace(",", ".")), Integer.parseInt(tfTempo.getText()));

                if (ctrP.salvar(p)) {
                    alert.setHeaderText("Procedimento Cadastrado com sucesso !!!");
                } else {
                    alert.setHeaderText("Erro ao cadastrar procedimento: \n" + Banco.con.getMensagemErro());
                }

                alert.showAndWait();
                estadoOriginal();
            }
        } else //Alterando 
        {
            if (validaCampos())//função que valida compos NOT NULL e tamanho no banco
            {
                //Depois de validar os canpos NOT NULL e seus respectivos tamanhos
                p = new Procedimentos(Integer.parseInt(tfCodigo.getText()), tfDescricao.getText(), Double.parseDouble(tfPreco.getText().replace(".", "").replace(",", ".")), Integer.parseInt(tfTempo.getText()));

                if (ctrP.alterar(p)) {
                    alert.setHeaderText("Procedimento alterado com sucesso !!!");
                } else {
                    alert.setHeaderText("Erro ao alterar procedimento: \n" + Banco.con.getMensagemErro());
                }

                alert.showAndWait();
                estadoOriginal();
            }
        }
    }

    @FXML
    private void evtCancelar(ActionEvent event) {
        if ((pnDados.isDisable() && !tfDescricao.getText().isEmpty()) || (!pnDados.isDisable() && !tfDescricao.getText().isEmpty())) {
            estadoOriginal();
        } else {
            _lbTelaCadAtual.setText("");
            TelaPrincipalController._pnDados.getChildren().clear();
        }
    }

    @FXML
    private void evtBtBuscar(ActionEvent event) {
        carregaTabela("upper(pro_descricao) like '%" + tfBuscar.getText().toUpperCase() + "%'");
        tfBuscar.setText("");
    }

    @FXML
    private void evtTable(MouseEvent event) {

        if (table.getSelectionModel().getSelectedIndex() >= 0) {
            Procedimentos p = table.getSelectionModel().getSelectedItem();

            tfCodigo.setText("" + p.getCodigo());
            tfDescricao.setText(p.getDescricao());
            tfPreco.setText(MaskCampos.zeros(Double.toString(p.getPreco())));
            tfTempo.setText("" + p.getTempo());

            btAlterar.setDisable(false);
            btApagar.setDisable(false);
            btNovo.setDisable(true);
            table.setDisable(true);
            btBuscar.setDisable(true);
            tfBuscar.setDisable(true);
        }
    }

}
