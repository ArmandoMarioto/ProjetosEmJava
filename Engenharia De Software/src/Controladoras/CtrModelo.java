/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladoras;

import Entidades.Marca;
import Entidades.Modelo;
import Interface.Controladora;
import Interface.Entidade;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;

/**
 *
 * @author Aluno
 */
public class CtrModelo implements Controladora
{

    private static CtrModelo con;

    private CtrModelo()
    {
    }

    public static CtrModelo instancia()
    {
        if (con == null)
        {
            con = new CtrModelo();
        }
        return con;
    }

    public static void PreencheCombo(JFXComboBox cb_modelo, Object marca)
    {

        cb_modelo.setItems(FXCollections.observableArrayList(new Modelo().get(((Marca) marca).getCodigo())));
    }

    public static void setMarca(JFXComboBox<Object> cb_marca, Object marca)//procura e seta o objeto passado
    {
        Modelo m = (Modelo) marca;
        for (int i = 0; i < cb_marca.getItems().size(); i++)
        {
            if (((Modelo) cb_marca.getItems().get(i)).getCodigo() == m.getCodigo())
            {
                cb_marca.getSelectionModel().select(i);
                i = cb_marca.getItems().size();
            }
        }
    }

    @Override
    public Boolean Salvar()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean Alterar()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean Excluir()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
