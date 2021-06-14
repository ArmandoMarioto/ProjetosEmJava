/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Controladoras.CtrParametrizacao;
import Entidades.Parametrizacao;
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
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 *
 * @author Aluno
 */
public class TelaPrincipalController implements Initializable
{

    @FXML
    private HBox pndado;
    @FXML
    private ImageView imgLogoPequeno;
    @FXML
    private Label NomeEmpresa;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        try {
            if (new CtrParametrizacao().inicia())
            {
                try
                {
                    //Carrega a tebela
                    Parametrizacao para = new CtrParametrizacao().carrega();

                    NomeEmpresa.setText(para.getNomeFantasia());
                    imgLogoPequeno.setImage(SwingFXUtils.toFXImage(para.getLogoPequeno(), null));

                } catch (SQLException | IOException ex)
                {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(TelaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void evtCliente(ActionEvent event)
    {
        pndado.getChildren().clear();
        try
        {
            Parent p = FXMLLoader.load(getClass().getResource("/Interfaces/TelaGenCliente.fxml"));
            pndado.getChildren().add(p);
        } catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void evtProduto(ActionEvent event)
    {
        pndado.getChildren().clear();
        try
        {
            Parent p = FXMLLoader.load(getClass().getResource("/Interfaces/TelaGenProduto.fxml"));
            pndado.getChildren().add(p);
        } catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void evtFornecedor(ActionEvent event)
    {
        pndado.getChildren().clear();
        try
        {
            Parent p = FXMLLoader.load(getClass().getResource("/Interfaces/TelaFornecedor.fxml"));
            pndado.getChildren().add(p);
        } catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void evtParametrizacao(ActionEvent event)
    {
        pndado.getChildren().clear();
        try
        {
            Parent p = FXMLLoader.load(getClass().getResource("/Interfaces/TelaParametrizacao.fxml"));
            pndado.getChildren().add(p);
        } catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void evtAgendamento(ActionEvent event) 
    {
        pndado.getChildren().clear();
        try
        {
            Parent p = FXMLLoader.load(getClass().getResource("/Interfaces/TelaAgendar.fxml"));
            pndado.getChildren().add(p);
        } catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }

}
