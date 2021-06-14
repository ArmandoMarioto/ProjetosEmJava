package Controladoras;

import Entidades.Fornecedor;
import java.sql.SQLException;
import javafx.collections.ObservableList;

/**
 *
 * @author armando
 */
public class CtrFornecedor
{

    private String sql;

    //codigo, nome, cnpf, email, fone, site, contato, celular
    public boolean Salvar(String nome, String cnpj, String email, String fone, String site, String contato, String celular) throws SQLException
    {
        Fornecedor fornecedor = new Fornecedor(nome, cnpj, email, fone, site, contato, celular);
        return fornecedor.Salvar(sql);
    }

    public boolean Alterar(String cod, String nome, String cnpj, String email, String fone, String site, String contato, String celular) throws SQLException
    {
        Fornecedor fornecedor = new Fornecedor(Integer.parseInt(cod), nome, cnpj, email, fone, site, contato, celular);
        return fornecedor.Alterar(sql);
    }

    public boolean Apagar(int cod)
    {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setCodigo(cod);
        return fornecedor.Apagar(sql);
    }

    public ObservableList<Fornecedor> Buscar(String filtro, String busca)
    {
        Fornecedor fornecedor = new Fornecedor();
        return fornecedor.Buscar(filtro, busca);
    }

    public ObservableList<Fornecedor> BuscarTodos()
    {
        Fornecedor fornecedor = new Fornecedor();
        return fornecedor.BuscarTodos();
    }
}
