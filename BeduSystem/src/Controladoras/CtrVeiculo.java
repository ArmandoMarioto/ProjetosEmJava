package Controladoras;

import Entidades.Cliente;
import Entidades.Veiculo;
import java.sql.Date;
import java.util.ArrayList;
import javafx.scene.control.TableView;
import javafx.scene.paint.Color;

public class CtrVeiculo
{

    private static CtrVeiculo con;

    private CtrVeiculo()
    {
    }

    public static CtrVeiculo instancia()
    {
        if (con == null)
        {
            con = new CtrVeiculo();
        }
        return con;
    }
 //String vei_chassi ;String vei_placa ;String vei_modelo ;String vei_marca ;
  // Date vei_ano ;String vei_cor ;String vei_descricao ;
    public static boolean Salvar(String vei_chassi ,String vei_placa ,String vei_modelo ,String vei_marca ,
 Date vei_ano ,String vei_cor ,String vei_descricao )
    {
        Veiculo veiculo = new Veiculo(vei_chassi ,vei_placa , vei_modelo , vei_marca ,
vei_ano , vei_cor , vei_descricao);

        return veiculo.insert();
    }

    public static boolean Alterar(String vei_chassi ,String vei_placa ,String vei_modelo ,String vei_marca ,
 Date vei_ano ,String vei_cor ,String vei_descricao)
    {
        Veiculo veiculo = new Veiculo(vei_chassi ,vei_placa , vei_modelo , vei_marca ,
vei_ano , vei_cor , vei_descricao);
        return veiculo.update();
    }

    public static boolean Apagar(String vei_chassi)
    {
        Veiculo veiculo = new Veiculo(vei_chassi);
        return veiculo.delete();
    }

    public static void adicionarVeiculo(TableView table, String vei_chassi ,String vei_placa ,String vei_modelo ,String vei_marca ,
 Date vei_ano ,String vei_cor ,String vei_descricao)
    {
        try
        {
            table.getItems().add(new Veiculo(vei_chassi ,vei_placa , vei_modelo , vei_marca ,vei_ano , vei_cor , vei_descricao));
        } catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }

    }

    public static void removerVeiculo(TableView table)
    {
        table.getItems().remove(table.getSelectionModel().getSelectedIndex());
    }

    public static ArrayList<Veiculo> copia(TableView table)
    {
        ArrayList<Veiculo> v = new ArrayList<>();
        for (int i = 0; i < table.getItems().size(); i++)
        {
            v.add((Veiculo) table.getItems().get(i));
        }

        return v;
    }


    /*public static  ObservableList<Veiculo> Buscar(String filtro, String busca)
    {
        Veiculo veculo = new Veiculo();
        return 
    }*/

}
