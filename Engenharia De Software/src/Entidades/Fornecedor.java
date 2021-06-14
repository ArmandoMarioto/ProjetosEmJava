package Entidades;

import Interface.Entidade;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author armando
 */
public class Fornecedor implements Entidade
{

    private int codigo;
    private String nome;
    private String cnpf;
    private String email;
    private String fone;
    private String site;
    private String contato;
    private String celular;

    public Fornecedor()
    {

    }

    public Fornecedor(String nome, String cnpf, String email, String fone, String site, String contato, String celular)
    {
        this.nome = nome;
        this.cnpf = cnpf;
        this.email = email;
        this.fone = fone;
        this.site = site;
        this.contato = contato;
        this.celular = celular;
    }

    public Fornecedor(int codigo, String nome, String cnpf, String email, String fone, String site, String contato, String celular)
    {
        this.codigo = codigo;
        this.nome = nome;
        this.cnpf = cnpf;
        this.email = email;
        this.fone = fone;
        this.site = site;
        this.contato = contato;
        this.celular = celular;
    }

    public Fornecedor(int codigo, String nome, String cnpf, String email, String fone)
    {
        this.codigo = codigo;
        this.nome = nome;
        this.cnpf = cnpf;
        this.email = email;
        this.fone = fone;
    }

    public Fornecedor(String nome, String email, String fone, String cnpf)
    {
        this.nome = nome;
        this.email = email;
        this.fone = fone;
        this.cnpf = cnpf;
    }

    public Fornecedor(int codigo, String nome, String email, String fone)
    {
        this.codigo = codigo;
        this.nome = nome;
        this.email = email;
        this.fone = fone;
    }

    public String getSite()
    {
        return site;
    }

    public String getContato()
    {
        return contato;
    }

    public String getCelular()
    {
        return celular;
    }

    public int getCodigo()
    {
        return codigo;
    }

    public void setCodigo(int codigo)
    {
        this.codigo = codigo;
    }

    public String getCnpf()
    {
        return cnpf;
    }

    public void setCnpf(String cnpf)
    {
        this.cnpf = cnpf;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public void setSite(String site)
    {
        this.site = site;
    }

    public void setContato(String contato)
    {
        this.contato = contato;
    }

    public void setCelular(String celular)
    {
        this.celular = celular;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getFone()
    {
        return fone;
    }

    public void setFone(String fone)
    {
        this.fone = fone;
    }

    @Override
    public Boolean insert()
    {
        String sql = "";
        //codigo, nome, cnpf, email, fone, site, contato, celular
        sql = "INSERT INTO fornecedor(for_nome,for_cnpj, for_email, for_fone, for_site, for_contato, for_celular) VALUES ('$1','$2','$3','$4','$5','$6','$7')";
        sql = sql.replace("$1", getNome());
        sql = sql.replace("$2", getCnpf());
        sql = sql.replace("$3", getEmail());
        sql = sql.replace("$4", getFone());
        sql = sql.replace("$5", getSite());
        sql = sql.replace("$6", getContato());
        sql = sql.replace("$7", getCelular());

        return Banco.Banco.getConexao().manipular(sql);
    }

    @Override
    public Boolean update()
    {
        String sql = "";
        //codigo, nome, cnpf, email, fone, site, contato, celular
        sql = "UPDATE fornecedor SET for_nome = '$1',for_cnpj = '$2', for_email = '$3', for_fone = '$4', for_site = '$5', for_contato = '$6', for_celular = '$7' WHERE for_codigo = " + getCodigo();
        sql = sql.replace("$1", getNome());
        sql = sql.replace("$2", getCnpf());
        sql = sql.replace("$3", getEmail());
        sql = sql.replace("$4", getFone());
        sql = sql.replace("$5", getSite());
        sql = sql.replace("$6", getContato());
        sql = sql.replace("$7", getCelular());

        return Banco.Banco.getConexao().manipular(sql);
    }

    public Boolean delete()
    {
        String sql = "";
        sql = "DELETE FROM fornecedor WHERE for_codigo = " + getCodigo();

        return Banco.Banco.getConexao().manipular(sql);
    }

    public ObservableList<Fornecedor> Buscar(String filtro, String busca)
    {
        ObservableList<Fornecedor> obj = FXCollections.observableArrayList();

        //ResultSet rs = Banco.BD.getCon().consultar("SELECT * FROM fornecedor WHERE for_nome LIKE '" + filtro +"%"+ "'");
        ResultSet rs = Banco.Banco.getConexao().consultar("SELECT * FROM fornecedor WHERE " + busca + " LIKE '" + filtro + "%" + "'");

        try
        {
            while (rs != null && rs.next())
            {
                obj.add(new Fornecedor(rs.getInt("for_codigo"), rs.getString("for_nome"), rs.getString("for_cnpj"), rs.getString("for_email"), rs.getString("for_fone"), rs.getString("for_site"), rs.getString("for_contato"), rs.getString("for_celular")));
            }
            //codigo, nome, cnpf, email, fone, site, contato, celular
        } catch (SQLException ex)
        {

        }
        return obj;
    }

    public ObservableList<Fornecedor> BuscarTodos()
    {
        ObservableList<Fornecedor> obj = FXCollections.observableArrayList();

        ResultSet rs = Banco.Banco.getConexao().consultar("SELECT * FROM fornecedor");

        try
        {
            while (rs != null && rs.next())
            {
                obj.add(new Fornecedor(rs.getInt("for_codigo"), rs.getString("for_nome"), rs.getString("for_cnpj"), rs.getString("for_email"), rs.getString("for_fone"), rs.getString("for_site"), rs.getString("for_contato"), rs.getString("for_celular")));
            }
        } catch (SQLException ex)
        {

        }
        return obj;
    }

    /*
    CREATE TABLE fornecedor
(
    for_codigo serial NOT NULL,
    for_nome character varying(50),
    for_cnpj character varying(50),
    for_email character varying(50),
    for_fone character varying(50),
    for_site character varying(50),
    for_contato character varying(50),
    for_celular character varying(50),
    CONSTRAINT for_pk PRIMARY KEY (for_codigo)
)
     */
    @Override
    public ArrayList<Object> get(String filtro)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
