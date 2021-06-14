package sisdentalfx.ui;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import org.json.JSONObject;
import sisdentalfx.db.controles.CtrCidade;
import sisdentalfx.db.controles.CtrPessoa;
import sisdentalfx.db.controles.CtrUf;
import sisdentalfx.db.entidades.Cidade;
import sisdentalfx.db.entidades.Paciente;
import sisdentalfx.db.entidades.Pessoa;
import sisdentalfx.db.entidades.UF;
import sisdentalfx.db.util.Banco;
import sisdentalfx.db.util.Conexao;
import static sisdentalfx.ui.TelaPrincipalController._lbTelaCadAtual;
import sisdentalfx.util.AcessoCEP;
import sisdentalfx.util.MaskCampos;

public class TelaCadPacienteController implements Initializable {
    public static Parent _atual;
    public static String _email;
    @FXML
    private AnchorPane pnDados;
    @FXML
    private TextField tfCodigo;
    @FXML
    private TextField tfNome;
    @FXML
    private TextField tfCPF;
    @FXML
    private TextField tfTelefone;
    @FXML
    private TextField tfEmail;
    @FXML
    private ImageView img;
    @FXML
    private TextField tfCEP;
    @FXML
    private TextField tfBairro;
    @FXML
    private TextField tfRua;
    @FXML
    private TextField tfNumero;
    @FXML
    private TextArea tfHistorico;
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
    private Button btBuscar;
    @FXML
    private TableView<Pessoa> table;
    @FXML
    private TableColumn colCodigo;
    @FXML
    private TableColumn colNome;
    @FXML
    private ComboBox<Cidade> cbCidade;
    @FXML
    private ComboBox<UF> cbUf;
    @FXML
    private Button btFoto;
    @FXML
    private TextField tfBuscar;

    private File file;
    @FXML
    private Button btEmail;

    private void estadoOriginal() {
        file = null;
        img.setImage(null);
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
        cbUf.getItems().clear();
        cbUf.setValue(null);
        cbCidade.getItems().clear();
        cbCidade.setValue(null);

        ObservableList<Node> componentes = pnDados.getChildren();

        for (Node n : componentes) {
            img.setImage(null);
            if (n instanceof TextInputControl) {
                ((TextInputControl) n).setText("");
            }
            if (n instanceof ComboBox) {
                ((ComboBox) n).getItems().clear();
            }
        }
        carregaTabela("");
        carregaComboUF("");
    }

    private void carregaTabela(String filtro) {
        CtrPessoa ctrP = new CtrPessoa();
        ArrayList<Pessoa> resultado = ctrP.getPessoa(filtro, new Paciente());

        ObservableList<Pessoa> modelo = FXCollections.observableArrayList(resultado);
        table.setItems(modelo);
    }

    private void carregaComboUF(String filtro) {
        //Carregando comboBox de UF´s
        CtrUf ctrU = new CtrUf();
        ArrayList<UF> result = ctrU.getUF(filtro);

        ObservableList<UF> ufs = FXCollections.observableArrayList(result);

        cbUf.setItems(ufs);
        //cbUf.setValue(cbUf.getItems().get(0)); //inicializando comboBox

        if (!cbUf.getSelectionModel().isSelected(-1)) {
            carregaComboCidade(cbUf.getSelectionModel().getSelectedItem().getCodigo());
        }
    }

    private void carregaComboCidade(int codigo) {
        //limpando comboBox
        cbCidade.getItems().clear();
        cbCidade.setValue(null);

        //Carregando ComboBox de Cidades
        CtrCidade ctrC = new CtrCidade();
        ArrayList<Cidade> result = ctrC.getCidades(codigo);

        ObservableList<Cidade> cids = FXCollections.observableArrayList(result);
        cbCidade.setItems(cids);
        cbCidade.setValue(cbCidade.getItems().get(0));
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

    private boolean validaCampos() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        if (tfNome.getText().trim().isEmpty()) {
            alert.setHeaderText("Campo 'Nome' é um campo requerido.");
            alert.showAndWait();
            tfNome.setText("");
            tfNome.requestFocus();
            return false;
        }
        if (tfTelefone.getText().trim().isEmpty()) {
            alert.setHeaderText("Campo 'Telefone' é um campo requerido.");
            alert.showAndWait();
            tfTelefone.setText("");
            tfTelefone.requestFocus();
            return false;
        }
        if (tfCPF.getText().trim().isEmpty()) {
            alert.setHeaderText("Campo 'CPF' é um campo requerido.");
            alert.showAndWait();
            tfCPF.setText("");
            tfCPF.requestFocus();
            return false;
        }
        if (cbUf.getValue() == null) {
            alert.setHeaderText("Campo 'UF' é um campo requerido.");
            alert.showAndWait();
            cbUf.requestFocus();
            return false;
        }
        if (cbCidade.getValue() == null) {
            alert.setHeaderText("Campo 'Cidade' é um campo requerido.");
            alert.showAndWait();
            cbCidade.requestFocus();
            return false;
        }
        return true;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //mascaras
        MaskCampos.maxField(tfNome, 40);
        MaskCampos.cpfField(tfCPF);
        MaskCampos.numericField(tfTelefone, 11);
        MaskCampos.maxField(tfEmail, 50);
        MaskCampos.numericField(tfCEP, 8);
        MaskCampos.maxField(tfBairro, 100);
        MaskCampos.maxField(tfRua, 100);
        MaskCampos.maxField(tfNumero, 6);

        tfCodigo.setDisable(true);

        colCodigo.setCellValueFactory(new PropertyValueFactory("codigo"));
        colNome.setCellValueFactory(new PropertyValueFactory("nome"));

        estadoOriginal();
    }

    @FXML
    private void evtBtNovo(ActionEvent event) {
        estadoEdicao();
    }

    @FXML
    private void evtBtAlterar(ActionEvent event) {
        Paciente p = (Paciente) table.getSelectionModel().getSelectedItem();
        CtrPessoa ctrP = new CtrPessoa();

        ctrP.alterar(p);
        estadoEdicao();
    }

    @FXML
    private void evtBtApagar(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Confirmar a excluão de: \n" + tfNome.getText());

        if (alert.showAndWait().get() == ButtonType.OK) {
            CtrPessoa ctrP = new CtrPessoa();
            ctrP.apagar(new Paciente(Integer.parseInt(tfCodigo.getText()), "", "", "", "", "", "", "", "", "", null, null, null));
            estadoOriginal();
        }
    }

    @FXML
    private void evtBtConfirmar(ActionEvent event) {
        int cod;
        CtrPessoa ctrP = new CtrPessoa();
        Paciente p;
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
                p = new Paciente(tfNome.getText(), tfCPF.getText(), tfTelefone.getText(), tfEmail.getText(), tfHistorico.getText(), tfCEP.getText(),
                        tfBairro.getText(), tfRua.getText(), tfNumero.getText(), null, cbCidade.getSelectionModel().getSelectedItem(), "rua");
                System.out.println(cbCidade.getSelectionModel().getSelectedItem().getUf());
                if (ctrP.salvar(p)) {
                    alert.setHeaderText("Paciente cadastrado com sucesso !!!");

                    if (file != null)//Gravando imagem no banco
                    {
                        try {
                            File caminho = new File(file.getAbsolutePath());
                            FileInputStream fis = new FileInputStream(caminho);
                            PreparedStatement ps = Conexao.getConnect().prepareStatement("update paciente set pac_foto = ? where pac_codigo = " + Banco.con.getMaxPK("paciente", "pac_codigo"));
                            ps.setBinaryStream(1, fis, (int) caminho.length());
                            ps.executeUpdate();
                            ps.close();
                            fis.close();
                        } catch (Exception e) {
                            System.out.println("Erro ao Gravar Imagem no banco !!!");
                        }
                    }
                } else {
                    alert.setHeaderText("Erro ao cadastrar Paciente: \n" + Banco.con.getMensagemErro());
                }

                alert.showAndWait();
                estadoOriginal();
            }
        } else //Alterando cadastro
        {
            if (validaCampos())//função que valida compos NOT NULL e tamanho no banco
            {
                //Depois de validar os canpos NOT NULL e seus respectivos tamanhos
                p = new Paciente(Integer.parseInt(tfCodigo.getText()), tfNome.getText(), tfCPF.getText(), tfTelefone.getText(), tfEmail.getText(), tfHistorico.getText(), tfCEP.getText(),
                        tfBairro.getText(), tfRua.getText(), tfNumero.getText(), null, cbCidade.getSelectionModel().getSelectedItem(), "rua");

                if (ctrP.alterar(p)) {
                    alert.setHeaderText("Paciente alterado com sucesso !!!");
                    if (file != null)//Gravando imagem no banco
                    {
                        try {
                            File caminho = new File(file.getAbsolutePath());
                            FileInputStream fis = new FileInputStream(caminho);
                            PreparedStatement ps = Conexao.getConnect().prepareStatement("update paciente set pac_foto = ? where pac_codigo = " + tfCodigo.getText());
                            ps.setBinaryStream(1, fis, (int) caminho.length());
                            ps.executeUpdate();
                            ps.close();
                            fis.close();
                        } catch (Exception e) {
                            System.out.println("Erro ao Gravar Imagem no banco !!!");
                        }
                    }
                } else {
                    alert.setHeaderText("Erro ao alterar paciente: \n" + Banco.con.getMensagemErro());
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
        carregaTabela("upper(pac_nome) like '%" + tfBuscar.getText().toUpperCase() + "%'");
        tfBuscar.setText("");
    }

    @FXML
    private void evtTable(MouseEvent event) {
        if (table.getSelectionModel().getSelectedIndex() >= 0) {
            Paciente p = (Paciente) table.getSelectionModel().getSelectedItem();

            tfCodigo.setText("" + p.getCodigo());
            tfNome.setText(p.getNome());
            tfEmail.setText(p.getEmail());
            tfCPF.setText(p.getCPF());
            tfTelefone.setText(p.getTelefone());
            tfCEP.setText(p.getCEP());
            tfBairro.setText(p.getBairro());
            tfRua.setText(p.getRua());
            tfNumero.setText(p.getNumero());
            tfHistorico.setText(p.getHisClinico());
            cbCidade.setValue(p.getCidade());
            cbUf.setValue(p.getCidade().getUf());

            //trazendo foto do banco
            try {
                PreparedStatement ps = Conexao.getConnect().prepareStatement("select pac_foto from paciente where pac_codigo = " + p.getCodigo());
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    byte[] imgBytes = rs.getBytes(1);
                    InputStream in = new ByteArrayInputStream(imgBytes);
                    BufferedImage bImageFromConvert = ImageIO.read(in);
                    ImageIO.write(bImageFromConvert, "jpg", new File("C:\\Users\\vinicius\\Desktop\\imagem.jpg"));
                    Image imagem = new Image("file:\\C:\\Users\\vinicius\\Desktop\\imagem.jpg");
                    /*ImageIO.write(bImageFromConvert, "jpg", new File("D:\\imagem.jpg"));
                    Image imagem = new Image("file:\\D:\\imagem.jpg");*/
                    img.setImage(imagem);
                }
                rs.close();
                ps.close();
            } catch (Exception e) {
                System.out.println("Erro ao Trazer Imagem do banco de dados !!!");
            }

            btAlterar.setDisable(false);
            btApagar.setDisable(false);
            btNovo.setDisable(true);
            table.setDisable(true);
            btBuscar.setDisable(true);
            tfBuscar.setDisable(true);
        }
    }

    @FXML
    private void evtCEP(KeyEvent event) {
        //testando tecla para pesquisar CEP
        if (event.getCode() == event.getCode().ENTER) {
            if (tfCEP.getText().trim().isEmpty()) //se deu enter com o tf em 
            {
                cbUf.setValue(null);

                cbCidade.getItems().clear();
                cbCidade.setValue(null);

                tfBairro.setText("");
                tfRua.setText("");
                tfNumero.setText("");
            } else {
                String JSON = AcessoCEP.consultaCep(tfCEP.getText(), "json");

                JSONObject my_obj = new JSONObject(JSON);
                System.out.println(JSON);

                //buscando estado
                CtrUf ctrU = new CtrUf();
                UF uf = ctrU.getUF_Slg(my_obj.getString("state"));
                cbUf.getSelectionModel().select(uf);

                //buscando cidade
                CtrCidade ctrC = new CtrCidade();
                Cidade cid = ctrC.getCididade_name(my_obj.getString("city"), cbUf.getSelectionModel().getSelectedItem());
                cbCidade.getSelectionModel().select(cid);

                tfBairro.setText(my_obj.getString("district"));
                tfRua.setText(my_obj.getString("address"));
            }
        }
    }

    @FXML
    private void evtBtFoto(ActionEvent event) {
        //Evento para abrir janela de escolha da foto
        FileChooser fc = new FileChooser(); //abrir janela

        //filtros
        FileChooser.ExtensionFilter jpgFilter = new FileChooser.ExtensionFilter("Arquivo Imagem(*.JPG)", "*.JPG");

        fc.getExtensionFilters().addAll(jpgFilter); //add os filtros
        fc.setInitialDirectory(new File("C:\\Users\\vinicius\\Desktop")); //abrindo em janela especifica
        //fc.setInitialDirectory(new File("D:"));

        File arq = fc.showOpenDialog(null);

        if (arq != null) {
            file = new File(arq.getAbsolutePath());
            Image imagem = new Image(arq.toURI().toString());

            img.setFitWidth(140);
            img.setFitHeight(140);
            img.setImage(imagem);
        }
    }

    @FXML
    private void evtUfClicked(ActionEvent event) {
        //Evento para atualiza o comboBox de cidade assim que se escolher a UF
        if (!cbUf.getSelectionModel().isEmpty()) {
            carregaComboCidade(cbUf.getSelectionModel().getSelectedItem().getCodigo());
        }
    }

    @FXML
    private void evtBtEmail(ActionEvent event) {
        try {
            _email = tfEmail.getText();
            _atual = (Parent) TelaPrincipalController._pnDados.getChildren().get(0);
            Parent root = FXMLLoader.load(getClass().getResource("TelaEnvioEmail.fxml"));

            TelaPrincipalController._pnDados.getChildren().clear();
            TelaPrincipalController._pnDados.getChildren().add(root);
        } catch (IOException ex) {
            //
        }
    }
}
