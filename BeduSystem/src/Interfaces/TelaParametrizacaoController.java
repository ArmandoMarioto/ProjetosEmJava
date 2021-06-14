package Interfaces;

import Entidades.Parametrizacao;

import javax.swing.JFileChooser;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;
import javax.swing.filechooser.FileNameExtensionFilter;
import Utils.MaskFieldUtil;
import bedusystem.BeduSystem;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLConnection;
import org.json.JSONObject;

public class TelaParametrizacaoController implements Initializable
{

    @FXML
    private JFXTextField tfNomeFantasia;
    @FXML
    private JFXTextField tfRazaoSocial;
    @FXML
    private JFXButton btLogoGrande;
    @FXML
    private JFXButton btLogoPequeno;
    @FXML
    private JFXTextField tfEndereco;
    @FXML
    private JFXTextField tfCorFundo;
    private JFXTextField tfFonte;
    @FXML
    private JFXTextField tfSite;
    @FXML
    private JFXTextField tfEmail;
    @FXML
    private JFXTextField tfFone;
    @FXML
    private JFXButton btSalvar;
    private FileInputStream f;
    private FileInputStream f2;
    private String caminho = null;
    private String caminho2 = null;
    private Parametrizacao para;
    @FXML
    private ImageView imgview;
    @FXML
    private ImageView imgview2;
    @FXML
    private JFXComboBox<String> cbcidade;
    @FXML
    private JFXComboBox<String> cbbairro;
    @FXML
    private JFXTextField tfCep;
    @FXML
    private JFXButton btConsultar;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        MaskFieldUtil.foneField(tfFone);
        MaskFieldUtil.cepField(tfCep);
        try
        {
            if (Controladoras.CtrParametrizacao.instancia().inicia())
            {
                try
                {
                    //Carrega a tebela
                   para = Controladoras.CtrParametrizacao.instancia().carrega();
                    
                    tfNomeFantasia.setText(para.getNome());
                    tfRazaoSocial.setText(para.getRazaoSocial());
                    tfFone.setText(para.getTelefone());
                    tfEmail.setText(para.getEmail());
                    tfEndereco.setText(para.getRua());
                    cbbairro.getSelectionModel().select(para.getBairro());
                    cbcidade.getSelectionModel().select(para.getCidade());
                    tfCep.setText(para.getCep());
                    evtConsultar();
                    tfCorFundo.setText(para.getCor());
                    tfSite.setText(para.getSite());

                    imgview.setImage(SwingFXUtils.toFXImage(para.getLogoGrande(), null));
                    imgview2.setImage(SwingFXUtils.toFXImage(para.getLogoPequeno(), null));

                } catch (SQLException | IOException ex)
                {
                    System.out.println(ex.getMessage());
                }

            } else
            {
                // primeira vez do programa
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(TelaParametrizacaoController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    private void evtLogoGrande(ActionEvent event) throws IOException
    {
        JFileChooser fc = new JFileChooser();
        fc.setToolTipText("Abra Imagem");
        fc.setFileFilter(new FileNameExtensionFilter("Image files", "bmp", "png", "jpg"));
        fc.setAcceptAllFileFilterUsed(false);

        if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
        {
            caminho = fc.getSelectedFile().getPath();
            File arq = new File(caminho);

            f = new FileInputStream(arq);
            BufferedImage bi = ImageIO.read(f);
            imgview.setImage(SwingFXUtils.toFXImage(bi, null));
            para.setLogoGrande(bi);
        }

    }

    @FXML
    private void evtLogoPequeno(ActionEvent event) throws FileNotFoundException, IOException
    {
        JFileChooser fc = new JFileChooser();
        fc.setToolTipText("Abra Imagem");
        fc.setFileFilter(new FileNameExtensionFilter("Image files", "bmp", "png", "jpg"));
        fc.setAcceptAllFileFilterUsed(false);

        if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
        {
            caminho2 = fc.getSelectedFile().getPath();
            File arq2 = new File(caminho2);
            f2 = new FileInputStream(arq2);
            BufferedImage bi2 = ImageIO.read(f2);
            imgview2.setImage(SwingFXUtils.toFXImage(bi2, null));
            para.setLogoPequeno(bi2);
        }
    }

    @FXML
    private void evtSalvar(ActionEvent event) throws SQLException, FileNotFoundException, IOException
    {
        if (!tfRazaoSocial.getText().isEmpty() && !tfNomeFantasia.getText().isEmpty() && !tfEndereco.getText().isEmpty() && !tfCorFundo.getText().isEmpty() && !tfCep.getText().isEmpty() && !tfSite.getText().isEmpty() && !tfEmail.getText().isEmpty() && !tfFone.getText().isEmpty())
        {
          //nome,fantasia,logoGrande,logoPequeno,telefone,email,razaoSocial,rua,bairro,cidade,cep,cor,site;
            if(caminho != null && caminho2 != null)
            {
                   if(Controladoras.CtrParametrizacao.instancia().Manipular(tfNomeFantasia.getText(),tfNomeFantasia.getText(), caminho, caminho2,
                   tfFone.getText(), tfEmail.getText(),tfRazaoSocial.getText(), tfEndereco.getText(),cbbairro.getValue(),cbcidade.getValue(),tfCep.getText(), tfCorFundo.getText(), tfSite.getText()))
                    {
                    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                    alerta.setContentText("Cadastrado com Sucesso!!!");
                    alerta.showAndWait();
                    }
            }
            else
            {
                if(Controladoras.CtrParametrizacao.instancia().alterar(tfNomeFantasia.getText(),tfNomeFantasia.getText(), para.getLogoGrande(), para.getLogoPequeno(),
                   tfFone.getText(), tfEmail.getText(),tfRazaoSocial.getText(), tfEndereco.getText(),cbbairro.getValue(),cbcidade.getValue(),tfCep.getText(), tfCorFundo.getText(), tfSite.getText()))
                    {
                    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                    alerta.setContentText("Alterado com Sucesso!!!");
                    alerta.showAndWait();
                    }
            }

            Parent root = FXMLLoader.load(getClass().getResource("/bedusystem/FXMLPrincipal.fxml"));
            Scene scene = new Scene(root);

            
            BeduSystem.getStage().setScene(scene);
        } else
        {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setContentText("Campos Incorreto!!!");
            alerta.showAndWait();
        }

    }

    @FXML
    private void evtConsultar() 
    {
         String str = consultaCep(tfCep.getText().replace("-", ""), "json");
        JSONObject my_obj = new JSONObject(str);
        cbcidade.getItems().clear();
        cbcidade.getItems().add(my_obj.getString("city"));
        cbcidade.getSelectionModel().select(my_obj.getString("city"));
        
        cbbairro.getItems().clear();
        cbbairro.getItems().add(my_obj.getString("district"));
        cbbairro.getSelectionModel().select(my_obj.getString("district"));

    }

}
