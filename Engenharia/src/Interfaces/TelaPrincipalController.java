/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;

/**
 *
 * @author Aluno
 */
public class TelaPrincipalController implements Initializable
{

    @FXML
    private HBox pndado;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
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

}
