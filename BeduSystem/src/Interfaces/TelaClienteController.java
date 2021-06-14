package Interfaces;

import Controladoras.CtrCliente;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import Utils.MaskFieldUtil;
import Banco.Banco;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import org.json.JSONObject;

public class TelaClienteController implements Initializable
{

    @FXML
    private GridPane pndadosVCep;
    @FXML
    private JFXTextField tnome;
    @FXML
    private JFXTextField trg;
    @FXML
    private JFXTextField tcpf;
    @FXML
    private JFXTextField ttelefone;
    @FXML
    private JFXTextField temail;
    @FXML
    private JFXComboBox<String> cbestado;
    @FXML
    private JFXComboBox<String> cbpais;
    @FXML
    private JFXComboBox<String> cbcidade;
    @FXML
    private JFXComboBox<String> cbbairro;
    @FXML
    private JFXTextField tcep;
    @FXML
    private Label lblErroNome;
    @FXML
    private Label lblErroTelefone;
    @FXML
    private Label lblErroRG;
    @FXML
    private Label lblErroEmail;
    @FXML
    private Label lblErroCPF;
    @FXML
    private Label lblErroEndereco;
    @FXML
    private Label lblErroCep;
    @FXML
    private TableColumn<Object, String> colnome;
    @FXML
    private TableColumn<Object, String> colrg;
    @FXML
    private TableColumn<Object, String> colcpf;
    @FXML
    private TableColumn<Object, String> coltelefone;
    @FXML
    private TableColumn<Object, String> colendereço;
    @FXML
    private TableColumn<Object, String> colemail;
    @FXML
    private JFXDatePicker dtcadastro;
    @FXML
    private TableView<Object> tabela;
    @FXML
    private JFXButton btnovo;
    @FXML
    private JFXButton btalterar;
    @FXML
    private JFXButton btnApagar;
    @FXML
    private JFXButton btnconfirmar;
    @FXML
    private JFXButton btncancelar;
    @FXML
    private JFXTextField tendereco;
    @FXML
    private AnchorPane pnCampos;

    private Integer f;
    private Object o;
    @FXML
    private JFXTextField tbusca;
    @FXML
    private JFXComboBox<String> cbbusca;
    @FXML
    private JFXButton bt_adcionar_veiculos;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        inicia_componentes();
        EstadoOriginal();
    }

       public String consultaCep(String cep, String formato)
    {
        String username = "seu login";
        String password = "sua senha";
        String proxyHost = "177.131.35.1";
        String proxyPort = "3128";

        StringBuffer dados = new StringBuffer();
        try
        {
           URL url = new URL("http://apps.widenet.com.br/busca-cep/api/cep." + formato + "?code=" + cep);
            URLConnection con = url.openConnection();
            con.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
            InputStream in = con.getInputStream();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
                String s = "";
                while (null != (s = br.readLine()))
                {
                    dados.append(s);
                }
            }
        } catch (IOException ex)
        {
            System.out.println(ex);

        }
        return dados.toString();
    }

    @FXML
    private void evtBuscaCep()
    {
        String str = consultaCep(tcep.getText().replace("-", ""), "json");
        JSONObject my_obj = new JSONObject(str);
        cbestado.getSelectionModel().select(my_obj.getString("state"));
        
        cbcidade.getItems().clear();
        cbcidade.getItems().add(my_obj.getString("city"));
        cbcidade.getSelectionModel().select(my_obj.getString("city"));
        
        cbbairro.getItems().clear();
        cbbairro.getItems().add(my_obj.getString("district"));
        cbbairro.getSelectionModel().select(my_obj.getString("district"));
        
        cbpais.getSelectionModel().select("Brasil");
    }

    @FXML
    private void evtBuscaCliente(ActionEvent event)
    {
        CarregaTabela(tbusca.getText());
    }

    @FXML
    private void evtClickTabela(MouseEvent event)
    {
        try
        {
            o = tabela.getSelectionModel().getSelectedItem() == null ? null : tabela.getSelectionModel().getSelectedItem();
        } catch (Exception ex)
        {
            o = null;
        }
        if (o != null)
        {
            //EstadoOriginal();
            CtrCliente.GetCliente(tnome, tcpf, trg, ttelefone, temail, tendereco, tcep, cbpais, cbestado, cbcidade, cbbairro, dtcadastro, o);
            btnovo.setDisable(true);
            btalterar.setDisable(false);
            btnApagar.setDisable(false);
            btncancelar.setDisable(false);
            evtBuscaCep();
            //ModoEdicao();
        }

    }

    @FXML
    private void evtNovo(ActionEvent event)
    {
        ModoEdicao();
        f = 1;
    }

    @FXML
    private void evtAlterar(ActionEvent event)
    {
        if (o != null)
        {
            ModoEdicao();
        }
        f = 0;
    }

    @FXML
    private void evtApagar(ActionEvent event)
    {
        if (o != null)
        {
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setContentText("Esta Operação Fará o Cliente Será Apagado Da Base De Dados");
            if (a.showAndWait().get() == ButtonType.OK)
            {
                if (CtrCliente.ExcluirCliente(o))
                {
                    a = new Alert(Alert.AlertType.INFORMATION);
                    a.setContentText("Cliente Excluido Com Sucesso!!!");
                    a.show();
                } else
                {
                    a = new Alert(Alert.AlertType.ERROR);
                    a.setContentText("Erro ao Excluir Cliente!!!");
                    a.show();
                }
                CarregaTabela("");
                EstadoOriginal();
            }

        }
    }

    @FXML
    private void evtConfirmar(ActionEvent event)
    {
        if (validaCampos())
        {
            if (f == 1 && CtrCliente.SalvarCliente(tnome.getText(), tcpf.getText(), trg.getText(), ttelefone.getText(), temail.getText(), tendereco.getText(), tcep.getText(),
                    cbpais.getSelectionModel().getSelectedItem(), cbestado.getSelectionModel().getSelectedItem(), cbcidade.getSelectionModel().getSelectedItem(), cbbairro.getSelectionModel().getSelectedItem(),
                    Date.valueOf(dtcadastro.getValue())))
            {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("Cliente Cadastrado Com Sucesso!!!");
                a.show();
                EstadoOriginal();
            } else if (f == 0 && o != null && CtrCliente.AlteraCliente(o, tnome.getText(), tcpf.getText(), trg.getText(), ttelefone.getText(), temail.getText(), tendereco.getText(), tcep.getText(),
                    cbpais.getSelectionModel().getSelectedItem(), cbestado.getSelectionModel().getSelectedItem(), cbcidade.getSelectionModel().getSelectedItem(), cbbairro.getSelectionModel().getSelectedItem(),
                    Date.valueOf(dtcadastro.getValue())))
            {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("Cliente Alterado Com Sucesso!!!");
                a.show();
                EstadoOriginal();
            } else
            {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText(Banco.getCon().getMensagemErro());
                a.show();
            }

        }
    }

    @FXML
    private void evtCancelar(ActionEvent event)
    {
        o = null;
        EstadoOriginal();
    }

    private void inicia_componentes()
    {
        colnome.setCellValueFactory(new PropertyValueFactory<Object, String>("nome"));
        colcpf.setCellValueFactory(new PropertyValueFactory<Object, String>("cpf"));
        colemail.setCellValueFactory(new PropertyValueFactory<Object, String>("email"));
        colendereço.setCellValueFactory(new PropertyValueFactory<Object, String>("endereco"));
        colrg.setCellValueFactory(new PropertyValueFactory<Object, String>("rg"));
        coltelefone.setCellValueFactory(new PropertyValueFactory<Object, String>("telefone"));
        MaskFieldUtil.cepField(tcep);
        MaskFieldUtil.cpfCnpjField(tcpf);
        MaskFieldUtil.foneField(ttelefone);
        MaskFieldUtil.numericField(trg);//máscara de RG
        cbestado.getItems().addAll("AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RO", "RS", "RR", "SC", "SE", "SP", "TO");
        cbpais.getItems().add("Brasil");
        cbbusca.getItems().addAll("Nome", "RG");
        cbbusca.getSelectionModel().select(0);
        f = 1;
        o = null;
    }

    private boolean validaCampos()
    {
        boolean b = true;
        if (tnome.getText().matches(".*\\d+.*") || tnome.getText().length() == 0 || tnome.getText().length() > 50)
        {
            if (tnome.getText().matches(".*\\d+.*"))
            {
                lblErroNome.setText("Caractéres Inválidos");
            } else if (tnome.getText().length() == 0)
            {
                lblErroNome.setText("Insira o Nome");
            } else
            {
                lblErroNome.setText("Limite Máximo Atingido(50 Caracteres)");
            }
            lblErroNome.setVisible(true);
            b = false;
        } else
        {
            lblErroNome.setVisible(false);
        }
        //42555440852
        if (trg.getText().length() < 10 )
        {
            lblErroRG.setText("Dígitos Insuficiente");
            lblErroRG.setVisible(true);
            b = false;
        } else
        {
            lblErroRG.setVisible(false);
        }
        if (!CPF(tcpf.getText().replace(".", "").replace("-", "")))
        {
            lblErroCPF.setText("CPF Inválido!!!");
            lblErroCPF.setVisible(true);
            b = false;
        } else
        {
            lblErroCPF.setVisible(false);
        }
        if (ttelefone.getText().length() != 14)
        {
            lblErroTelefone.setText("Quantidade de Digitos Deve Ser 14");
            lblErroTelefone.setVisible(true);
            b = false;
        } else
        {
            lblErroTelefone.setVisible(false);
        }
        if (temail.getText().length() == 0 || !temail.getText().contains("@"))
        {
            lblErroEmail.setText("Email Inválido");
            lblErroEmail.setVisible(true);
            b = false;
        } else
        {
            lblErroEmail.setVisible(false);
        }
        if (tendereco.getText().replaceAll(" ", "").length() == 0)
        {
            lblErroEndereco.setText("Endereço em Branco");
            lblErroEndereco.setVisible(true);
            b = false;
        } else
        {
            lblErroEndereco.setVisible(false);
        }
        if (tcep.getText().length() != 9)
        {
            lblErroCep.setText("CEP Inválido (9 caracteres)");
            lblErroCep.setVisible(true);
            b = false;
        } else
        {
            lblErroCep.setVisible(false);
        }
        return b;
    }

    private void desativasLabelsErro()
    {
        lblErroCPF.setVisible(false);
        lblErroCep.setVisible(false);
        lblErroEmail.setVisible(false);
        lblErroEndereco.setVisible(false);
        lblErroNome.setVisible(false);
        lblErroRG.setVisible(false);
        lblErroTelefone.setVisible(false);
    }

    private void ModoEdicao()
    {
        dtcadastro.setValue(LocalDate.now());
        dtcadastro.setDisable(false);
        btalterar.setDisable(true);
        btnApagar.setDisable(true);
        btncancelar.setDisable(false);
        btnconfirmar.setDisable(false);
        btnovo.setDisable(true);
        pnCampos.setDisable(false);
        desativasLabelsErro();
        f = 0;
    }

    void EstadoOriginal()
    {
        dtcadastro.setValue(LocalDate.now());
        btalterar.setDisable(true);
        btnApagar.setDisable(true);
        btncancelar.setDisable(true);
        btnconfirmar.setDisable(true);
        btnovo.setDisable(false);
        dtcadastro.setDisable(true);
        pnCampos.setDisable(true);
        desativasLabelsErro();
        tcep.setText("");
        tcpf.setText("");
        temail.setText("");
        tendereco.setText("");
        tnome.setText("");
        trg.setText("");
        ttelefone.setText("");
        CarregaTabela("");
        f = 1;
        cbbairro.getSelectionModel().select("");
        cbcidade.getSelectionModel().select("");
        cbestado.getSelectionModel().select("");
        cbpais.getSelectionModel().select("");
    }

    private void CarregaTabela(String filtro)
    {
        tabela.getItems().clear();
        ObservableList<Object> ob = FXCollections.observableArrayList(CtrCliente.instancia().getLista(filtro, cbbusca.getSelectionModel().getSelectedItem()));
        tabela.setItems(ob);
    }

    public static boolean CPF(String strCpf)
    {
        if (strCpf.isEmpty())
        {
            return false;
        }
        int d1, d2;
        int digito1, digito2, resto;
        int digitoCPF;
        String nDigResult;
        d1 = d2 = 0;
        digito1 = digito2 = resto = 0;
        for (int nCount = 1; nCount < strCpf.length() - 1; nCount++)
        {
            digitoCPF = Integer.valueOf(strCpf.substring(nCount - 1, nCount)).intValue();
            d1 = d1 + (11 - nCount) * digitoCPF;
            d2 = d2 + (12 - nCount) * digitoCPF;
        }
        resto = (d1 % 11);
        if (resto < 2)
        {
            digito1 = 0;
        } else
        {
            digito1 = 11 - resto;
        }
        d2 += 2 * digito1;
        resto = (d2 % 11);
        if (resto < 2)
        {
            digito2 = 0;
        } else
        {
            digito2 = 11 - resto;
        }
        String nDigVerific = strCpf.substring(strCpf.length() - 2, strCpf.length());
        nDigResult = String.valueOf(digito1) + String.valueOf(digito2);
        return nDigVerific.equals(nDigResult);
    }

    @FXML
    private void evtAdicionaVeiculo(ActionEvent event)
    {
        try
        {
            Parent p = FXMLLoader.load(getClass().getResource("/Interfaces/TelaGenVeiculo.fxml"));
            Scene scene = new Scene(p);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.showAndWait();
        } catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}
