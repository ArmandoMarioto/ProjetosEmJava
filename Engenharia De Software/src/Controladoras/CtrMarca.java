
package Controladoras;

import Entidades.Marca;
import Interface.Controladora;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;

public class CtrMarca implements Controladora
{

    private static CtrMarca con;

    private CtrMarca()
    {
    }

    public static CtrMarca instancia()
    {
        if (con == null)
        {
            con = new CtrMarca();
        }
        return con;
    }

    public static void PreencheCombo(JFXComboBox cb_marca)
    {
        cb_marca.setItems(FXCollections.observableArrayList(new Marca().getAll()));
    }

    public static void setMarca(JFXComboBox cb_marca, Object marca)//procura e seta o objeto passado
    {
        Marca m = (Marca) marca;
        for (int i = 0; i < cb_marca.getItems().size(); i++)
        {
            if (((Marca) cb_marca.getItems().get(i)).getCodigo() == m.getCodigo())
            {
                cb_marca.getSelectionModel().select(i);
                i = cb_marca.getItems().size();
            }
        }
    }

    public static Marca get(JFXComboBox cb_marca)
    {
        Marca m = (Marca) cb_marca.getSelectionModel().getSelectedItem();
        return m;
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
