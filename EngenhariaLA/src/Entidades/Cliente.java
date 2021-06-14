package Entidades;

import java.sql.Date;
import java.util.ArrayList;
import Banco.Banco;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Cliente
{

    private Integer codigo;
    private String nome;
    private String cpf;
    private String rg;
    private String telefone;
    private String email;
    private String endereco;
    private String cep;
    private String pais;
    private String estado;
    private String cidade;
    private String bairro;
    private Date dtCadastro;

    public Cliente()
    {
    }

    public Cliente(String nome)
    {
        this.nome = nome;
    }

    public Cliente(Integer codigo, String nome, String cpf, String rg, String telefone, String email, String endereco, String cep, String pais, String estado, String cidade, String bairro, Date dtCadastro)
    {
        this.codigo = codigo;
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.cep = cep;
        this.pais = pais;
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.dtCadastro = dtCadastro;
    }

    public Cliente(String nome, String cpf, String rg, String telefone, String email, String endereco, String cep, String pais, String estado, String cidade, String bairro, Date dtCadastro)
    {
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.cep = cep;
        this.pais = pais;
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.dtCadastro = dtCadastro;
    }

    public Cliente(Integer codigo, String nome, String cpf, String rg, String telefone, String email, String endereco, String cep, Date dtCadastro)
    {
        this.codigo = codigo;
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.cep = cep;
        this.dtCadastro = dtCadastro;
    }

    public Date getDtCadastro()
    {
        return dtCadastro;
    }

    public void setDtCadastro(Date dtCadastro)
    {
        this.dtCadastro = dtCadastro;
    }

    public Integer getCodigo()
    {
        return codigo;
    }

    public void setCodigo(Integer codigo)
    {
        this.codigo = codigo;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getCpf()
    {
        return cpf;
    }

    public void setCpf(String cpf)
    {
        this.cpf = cpf;
    }

    public String getRg()
    {
        return rg;
    }

    public void setRg(String rg)
    {
        this.rg = rg;
    }

    public String getTelefone()
    {
        return telefone;
    }

    public void setTelefone(String telefone)
    {
        this.telefone = telefone;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getEndereco()
    {
        return endereco;
    }

    public void setEndereco(String endereco)
    {
        this.endereco = endereco;
    }

    public String getCep()
    {
        return cep;
    }

    public void setCep(String cep)
    {
        this.cep = cep;
    }

    public String getPais()
    {
        return pais;
    }

    public void setPais(String pais)
    {
        this.pais = pais;
    }

    public String getEstado()
    {
        return estado;
    }

    public void setEstado(String estado)
    {
        this.estado = estado;
    }

    public String getCidade()
    {
        return cidade;
    }

    public void setCidade(String cidade)
    {
        this.cidade = cidade;
    }

    public String getBairro()
    {
        return bairro;
    }

    public void setBairro(String bairro)
    {
        this.bairro = bairro;
    }

    public boolean Add()
    {
        String sql = "insert into cliente(cli_cpf, cli_rg, cli_nome, "
                + "cli_telefone, cli_email, cli_endereco, cli_datacadastro, end_cep) "
                + "VALUES('$1', '$2', '$3', '$4', '$5', '$6', '$8', '$9')";
        sql = sql.replace("$1", cpf).replace("$2", rg)
                .replace("$3", nome).replace("$4", telefone)
                .replace("$5", email).replace("$6", endereco)
                .replace("$8", dtCadastro.toString())
                .replace("$9", cep.replace("-", ""));
        //System.out.println(sql);
        return Banco.con.manipular(sql);
    }

    public boolean Remove()
    {
        String sql = "delete from cliente where cli_cod = " + codigo;
        return Banco.con.manipular(sql);
    }

    public boolean Altera()
    {
        String sql = "UPDATE cliente SET cli_cpf = '$1', cli_rg = '$2', cli_nome = '$3',"
                + "cli_telefone = '$4', cli_email = '$5', cli_endereco = '$6', "
                + "cli_datacadastro = '$8', end_cep = '$9' WHERE cli_cod = " + codigo;
        sql = sql.replace("$1", cpf).replace("$2", rg)
                .replace("$3", nome).replace("$4", telefone)
                .replace("$5", email).replace("$6", endereco)
                .replace("$8", dtCadastro.toString())
                .replace("$9", cep);
        System.out.println(sql);
        return Banco.con.manipular(sql);
    }

    public ArrayList<Object> Get()
    {
        String sql = "select *from cliente";
        ResultSet rs = null;
        ArrayList<Object> a = new ArrayList<>();
        if (!nome.isEmpty())
        {
            sql += " Where cli_nome like '%" + nome + "%'";
        }
        rs = Banco.con.consultar(sql);
        try
        {
            while (rs.next())
            {
                a.add(new Cliente(rs.getInt("cli_cod"), rs.getString("cli_nome"),
                        rs.getString("cli_cpf"), rs.getString("cli_rg"), rs.getString("cli_telefone"),
                        rs.getString("cli_email"), rs.getString("cli_endereco"), rs.getString("end_cep"), rs.getDate("cli_datacadastro")));
            }
        } catch (SQLException ex)
        {
            a = null;
        }
        return a;
    }

    public ArrayList<Object> GetAvancado(String f1, String f2)
    {
        String sql = "select *from cliente";
        ResultSet rs = null;
        ArrayList<Object> a = new ArrayList<>();
        if (!f1.isEmpty())
        {
            if (f2.equals("Nome"))
            {
                sql += " Where cli_nome like '%" + f1 + "%'";
            } else if (f2.equals("RG"))
            {
                sql += " Where cli_rg = '" + f1 + "'";
            }
        }
        rs = Banco.con.consultar(sql);
        try
        {
            while (rs.next())
            {
                a.add(new Cliente(rs.getInt("cli_cod"), rs.getString("cli_nome"),
                        rs.getString("cli_cpf"), rs.getString("cli_rg"), rs.getString("cli_telefone"),
                        rs.getString("cli_email"), rs.getString("cli_endereco"), rs.getString("end_cep"), rs.getDate("cli_datacadastro")));
            }
        } catch (SQLException ex)
        {
            a = null;
        }
        return a;
    }
    /*private void PreencheDados(String pais, String estado, String cidade, String bairro, String cep)
    {
        String sql = "";
        ResultSet rs;
    }*/
}
