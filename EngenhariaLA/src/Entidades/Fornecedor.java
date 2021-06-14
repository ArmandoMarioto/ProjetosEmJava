package Entidades;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author armando
 */
public class Fornecedor
{

    private int codigo;
    private String nome;
    private String cnpf;
    private String email;
    private String fone;
    private String site;
    private String contato;
    private String celular;
    private String endereco;
    private String cep;

    public Fornecedor()
    {

    }

    public Fornecedor(int codigo, String nome, String cnpf, String email, String fone, String site, String contato, String celular, String endereco, String cep) {
        this.codigo = codigo;
        this.nome = nome;
        this.cnpf = cnpf;
        this.email = email;
        this.fone = fone;
        this.site = site;
        this.contato = contato;
        this.celular = celular;
        this.endereco = endereco;
        this.cep = cep;
    }

    public Fornecedor(String nome, String cnpf, String email, String fone, String site, String contato, String celular, String endereco, String cep) {
        this.nome = nome;
        this.cnpf = cnpf;
        this.email = email;
        this.fone = fone;
        this.site = site;
        this.contato = contato;
        this.celular = celular;
        this.endereco = endereco;
        this.cep = cep;
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
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

    public boolean Salvar(String sql) throws SQLException
    {
        //codigo, nome, cnpf, email, fone, site, contato, celular
        sql = "INSERT INTO fornecedor(for_nome,for_cnpj, for_email, for_fone, for_site, for_contato, for_celular, for_endereco, for_cep) VALUES ('$1','$2','$3','$4','$5','$6','$7','$8','$9')";
        sql = sql.replace("$1", getNome());
        sql = sql.replace("$2", getCnpf());
        sql = sql.replace("$3", getEmail());
        sql = sql.replace("$4", getFone());
        sql = sql.replace("$5", getSite());
        sql = sql.replace("$6", getContato());
        sql = sql.replace("$7", getCelular());
        sql = sql.replace("$8", getEndereco());
        sql = sql.replace("$9", getCep());
        

        return Banco.Banco.con.manipular(sql);
    }

    public boolean Alterar(String sql)
    {
        //codigo, nome, cnpf, email, fone, site, contato, celular
        sql = "UPDATE fornecedor SET for_nome = '$1',for_cnpj = '$2', for_email = '$3', for_fone = '$4', for_site = '$5', for_contato = '$6', for_celular = '$7', for_endereco = '$8', for_cep = '$9' WHERE for_codigo = " + getCodigo();
        sql = sql.replace("$1", getNome());
        sql = sql.replace("$2", getCnpf());
        sql = sql.replace("$3", getEmail());
        sql = sql.replace("$4", getFone());
        sql = sql.replace("$5", getSite());
        sql = sql.replace("$6", getContato());
        sql = sql.replace("$7", getCelular());
        sql = sql.replace("$8", getEndereco());
        sql = sql.replace("$9", getCep());

        return Banco.Banco.con.manipular(sql);
    }

    public boolean Apagar(String sql)
    {
        sql = "DELETE FROM fornecedor WHERE for_codigo = " + getCodigo();

        return Banco.Banco.con.manipular(sql);
    }

    public ObservableList<Fornecedor> Buscar(String filtro, String busca)
    {
        ObservableList<Fornecedor> obj = FXCollections.observableArrayList();

        //ResultSet rs = Banco.BD.getCon().consultar("SELECT * FROM fornecedor WHERE for_nome LIKE '" + filtro +"%"+ "'");
        ResultSet rs = Banco.Banco.con.consultar("SELECT * FROM fornecedor WHERE " + busca + " LIKE '" + filtro + "%" + "'");

        try
        {
            while (rs != null && rs.next())
            {
                obj.add(new Fornecedor(rs.getInt("for_codigo"), rs.getString("for_nome"), rs.getString("for_cnpj"), rs.getString("for_email"), rs.getString("for_fone"), rs.getString("for_site"), rs.getString("for_contato"), rs.getString("for_celular"), rs.getString("for_endereco"), rs.getString("for_cep")));
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

        ResultSet rs = Banco.Banco.con.consultar("SELECT * FROM fornecedor");

        try
        {
            while (rs != null && rs.next())
            {
                obj.add(new Fornecedor(rs.getInt("for_codigo"), rs.getString("for_nome"), rs.getString("for_cnpj"), rs.getString("for_email"), rs.getString("for_fone"), rs.getString("for_site"), rs.getString("for_contato"), rs.getString("for_celular"), rs.getString("for_endereco"), rs.getString("for_cep")));
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
}
