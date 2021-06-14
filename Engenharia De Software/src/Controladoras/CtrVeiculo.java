package Controladoras;

import Entidades.Cliente;
import Entidades.Marca;
import Entidades.Modelo;
import Entidades.Veiculo;
import Interface.Controladora;
import java.util.ArrayList;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.paint.Color;

public class CtrVeiculo implements Controladora
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

    public static boolean Salvar(int marca, int modelo, int dono, String chassi, String placa, Color cor)
    {
        Veiculo veiculo = new Veiculo(0, modelo, marca, chassi, cor, placa);

        return veiculo.insert();
    }

    public static boolean Alterar(String cod, Marca marca, Modelo modelo, Cliente dono, String chassi, String placa, Color cor)
    {
        Veiculo veiculo = new Veiculo(Integer.parseInt(cod), modelo.getCodigo(), marca.getCodigo(), chassi, cor, placa);
        return veiculo.update();
    }

    public static boolean Apagar(String cod)
    {
        Veiculo veiculo = new Veiculo(Integer.parseInt(cod));
        return veiculo.delete();
    }

    public static void adicionarVeiculo(TableView table, Object marca, Object modelo, String chassi, String placa, Color color)
    {
        try
        {
            table.getItems().add(new Veiculo(0, ((Modelo) modelo).getCodigo(), ((Marca) marca).getCodigo(), chassi, color, placa));
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

    public static void setVeiculos(int codigo_proprietario, TableView table)
    {
        Veiculo veiculo = new Veiculo();
        ArrayList<Veiculo> v = veiculo.getAll_Owner(codigo_proprietario);
        for (int i = 0; i < v.size(); i++)
        {
            table.getItems().add(v.get(i));
        }
    }

    /*public static  ObservableList<Veiculo> Buscar(String filtro, String busca)
    {
        Veiculo veculo = new Veiculo();
        return 
    }*/
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
