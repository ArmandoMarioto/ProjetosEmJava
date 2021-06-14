package Controladoras;

import Entidades.Fornecedor;
import Interface.Controladora;
import java.sql.SQLException;
import javafx.collections.ObservableList;

/**
 *
 * @author armando
 */
public class CtrFornecedor implements Controladora
{

    private static CtrFornecedor con;

    private CtrFornecedor()
    {
    }

    public static CtrFornecedor instancia()
    {
        if (con == null)
        {
            con = new CtrFornecedor();
        }
        return con;
    }

    //codigo, nome, cnpf, email, fone, site, contato, celular
    public static boolean Salvar(String nome, String cnpj, String email, String fone, String site, String contato, String celular) throws SQLException
    {
        Fornecedor fornecedor = new Fornecedor(nome, cnpj, email, fone, site, contato, celular);
        return fornecedor.insert();
    }

    public static boolean Alterar(String cod, String nome, String cnpj, String email, String fone, String site, String contato, String celular) throws SQLException
    {
        Fornecedor fornecedor = new Fornecedor(Integer.parseInt(cod), nome, cnpj, email, fone, site, contato, celular);
        return fornecedor.update();
    }

    public static boolean Apagar(int cod)
    {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setCodigo(cod);
        return fornecedor.delete();
    }

    public static ObservableList<Fornecedor> Buscar(String filtro, String busca)
    {
        Fornecedor fornecedor = new Fornecedor();
        return fornecedor.Buscar(filtro, busca);
    }

    public static ObservableList<Fornecedor> BuscarTodos()
    {
        Fornecedor fornecedor = new Fornecedor();
        return fornecedor.BuscarTodos();
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
