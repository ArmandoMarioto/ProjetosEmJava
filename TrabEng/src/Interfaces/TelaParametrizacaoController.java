package Interfaces;

import Entidades.Parametrizacao;
import Controladoras.CtrParametrizacao;
import Controladoras.MaskFieldUtil;

import javax.swing.JFileChooser;
import com.jfoenix.controls.JFXButton;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;
import javax.swing.filechooser.FileNameExtensionFilter;

public class TelaParametrizacaoController implements Initializable {

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
    @FXML
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
    @FXML
    private ImageView imgview;
    @FXML
    private ImageView imgview2;

    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        MaskFieldUtil.foneField(tfFone);
        try {
            if(new CtrParametrizacao().inicia())
            {
                try {
                    //Carrega a tebela
                    Parametrizacao para = new CtrParametrizacao().carrega();
                    
                    tfNomeFantasia.setText(para.getNomeFantasia());
                    tfRazaoSocial.setText(para.getRazaoSocial());
                    tfEndereco.setText(para.getEndereco());
                    tfCorFundo.setText(para.getCorFundo());
                    tfEmail.setText(para.getEmail());
                    tfSite.setText(para.getSite());
                    tfFone.setText(para.getFone());
                    tfFonte.setText(para.getTipoFonte());
                    
                    imgview.setImage(SwingFXUtils.toFXImage(para.getLogoGrande(), null));
                    imgview2.setImage(SwingFXUtils.toFXImage(para.getLogoPequeno(), null));
                    
                    
                } catch (SQLException | IOException ex)
                {
                    //Logger.getLogger(TelaParametrizacaoController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            else
            {
                // primeira vez do programa
            }} catch (SQLException ex) {
            Logger.getLogger(TelaParametrizacaoController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        }

    }

    @FXML
    private void evtLogoPequeno(ActionEvent event) throws FileNotFoundException, IOException {
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
            imgview2.setFitHeight(bi2.getHeight());
            imgview2.setFitWidth(bi2.getWidth());
            imgview2.setImage(SwingFXUtils.toFXImage(bi2, null));
        }
    }

@FXML
    private void evtSalvar(ActionEvent event) throws SQLException, FileNotFoundException, IOException 
    {
        if (!tfRazaoSocial.getText().equals("") && !tfNomeFantasia.getText().isEmpty()  &&!tfEndereco.getText().isEmpty() &&!tfCorFundo.getText().isEmpty() &&!tfFonte.getText().isEmpty() &&!tfSite.getText().isEmpty() &&!tfEmail.getText().isEmpty() &&!tfFone.getText().isEmpty() && f!=null &&f2!=null) 
        {
            new CtrParametrizacao().Manipular(tfNomeFantasia.getText(),tfRazaoSocial.getText(),caminho,caminho2,tfEndereco.getText(), tfCorFundo.getText(), tfFonte.getText(), tfSite.getText(), tfEmail.getText(), tfFone.getText());
        } 
        else 
        {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setContentText("Campos Incorreto!!!");
            alerta.showAndWait();
        }

    }


  

}
/*ResultSet rs = Banco.BD.getCon().consultar("select LogoGrande from parametrizacao");
            rs.next();
            BufferedImage bi = ImageIO.read(new ByteArrayInputStream(rs.getBytes("LogoGrande")));
            imgview.setImage(SwingFXUtils.toFXImage(bi, null));


  private void evtAlterar(ActionEvent event) throws SQLException, IOException {

        Parametrizacao para = new Parametrizacao(tfNomeFantasia.getText(), tfRazaoSocial.getText(),
                tfEndereco.getText(), tfCorFundo.getText(), tfFonte.getText(), tfSite.getText(), tfEmail.getText(), tfFone.getText());
        CtrParametrizacao Ctr = new CtrParametrizacao();

        flag = true;
        if (!tfNomeFantasia.getText().isEmpty() && !tfRazaoSocial.getText().isEmpty() && !caminhogrande.isEmpty() && !caminhopequeno.isEmpty()) 
        {

            Ctr.Manipular(para);
            ResultSet rs = Banco.BD.getCon().consultar("select LogoGrande from parametrizacao");
            rs.next();
            BufferedImage bi = ImageIO.read(new ByteArrayInputStream(rs.getBytes("LogoGrande")));
            imgview.setImage(SwingFXUtils.toFXImage(bi, null));
            
            ResultSet rs2 = Banco.BD.getCon().consultar("select logopequeno from parametrizacao");
            rs2.next();
            BufferedImage bi2 = ImageIO.read(new ByteArrayInputStream(rs2.getBytes("logopequeno")));
            imgview2.setImage(SwingFXUtils.toFXImage(bi2, null));
            
        } else {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setContentText("Campos Incorreto!!!");
            alerta.showAndWait();
        }
        limpa();


public void limpa()
{
    tfCorFundo.setText("");
    tfEmail.setText("");
    tfEndereco.setText("");
    tfFone.setText("");
    tfFonte.setText("");
    tfNomeFantasia.setText("");
    tfSite.setText("");
    tfRazaoSocial.setText("");
}

        

    }

*/