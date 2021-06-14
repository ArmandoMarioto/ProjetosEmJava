package Entidades;

import java.sql.Date;
import java.util.ArrayList;
import Banco.Banco;
import Interface.Entidade;
import Interface.Observer;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.Email;

public class Cliente implements Entidade, Observer
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
    private ArrayList<Veiculo> veiculos;

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
////////////////////////novos

    public Cliente(Integer codigo, String nome, String cpf, String rg, String telefone, String email, String endereco, String cep, String pais, String estado, String cidade, String bairro, Date dtCadastro, ArrayList<Veiculo> veiculos)
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
        this.veiculos = veiculos;
    }

    public Cliente(String nome, String cpf, String rg, String telefone, String email, String endereco, String cep, String pais, String estado, String cidade, String bairro, Date dtCadastro, ArrayList<Veiculo> veiculos)
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
        this.veiculos = veiculos;
    }

////////////////////////////////////
    public Cliente(int codigo_cliente)
    {
        Cliente c = null;
        String sql = "select * from cliente where cli_cod = " + codigo_cliente;
        ResultSet rs = Banco.getConexao().consultar(sql);
        try
        {
            while (rs.next())
            {
                c = new Cliente(rs.getInt("cli_cod"), rs.getString("cli_nome"),
                        rs.getString("cli_cpf"), rs.getString("cli_rg"), rs.getString("cli_telefone"),
                        rs.getString("cli_email"), rs.getString("cli_endereco"), rs.getString("cli_cep"), rs.getDate("cli_datacadastro"));
            }
            this.codigo = codigo_cliente;
            this.nome = c.getNome();
            this.cpf = c.getCpf();
            this.rg = c.getRg();
            this.telefone = c.getTelefone();
            this.email = c.getEmail();
            this.endereco = c.getEndereco();
            this.cep = c.getCep();
            this.dtCadastro = c.getDtCadastro();

        } catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
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

    @Override
    public Boolean insert()
    {
        String sql = "insert into cliente(cli_cpf, cli_rg, cli_nome, "
                + "cli_telefone, cli_email, cli_endereco, cli_datacadastro, cli_cep) "
                + "VALUES('$1', '$2', '$3', '$4', '$5', '$6', '$8', '$9')";
        sql = sql.replace("$1", cpf).replace("$2", rg)
                .replace("$3", nome).replace("$4", telefone)
                .replace("$5", email).replace("$6", endereco)
                .replace("$8", dtCadastro.toString())
                .replace("$9", cep.replace("-", ""));
        //System.out.println(sql);
        return Banco.getConexao().manipular(sql);
    }

    @Override
    public Boolean delete()
    {
        String sql = "delete from cliente where cli_cod = " + codigo;
        return Banco.getConexao().manipular(sql);
    }

    @Override
    public Boolean update()
    {
        String sql = "UPDATE cliente SET cli_cpf = '$1', cli_rg = '$2', cli_nome = '$3',"
                + "cli_telefone = '$4', cli_email = '$5', cli_endereco = '$6', "
                + "cli_datacadastro = '$8', cli_cep = '$9' WHERE cli_cod = " + codigo;
        sql = sql.replace("$1", cpf).replace("$2", rg)
                .replace("$3", nome).replace("$4", telefone)
                .replace("$5", email).replace("$6", endereco)
                .replace("$8", dtCadastro.toString())
                .replace("$9", cep);
        System.out.println(sql);
        return Banco.getConexao().manipular(sql);
    }

    public ArrayList<Object> get(String filtro)
    {
        String sql = "select *from cliente";
        ResultSet rs = null;
        ArrayList<Object> a = new ArrayList<>();
        if (!nome.isEmpty())
        {
            sql += " Where cli_nome like '%" + nome + "%'";
        }
        rs = Banco.getConexao().consultar(sql);
        try
        {
            while (rs.next())
            {
                a.add(new Cliente(rs.getInt("cli_cod"), rs.getString("cli_nome"),
                        rs.getString("cli_cpf"), rs.getString("cli_rg"), rs.getString("cli_telefone"),
                        rs.getString("cli_email"), rs.getString("cli_endereco"), rs.getString("cli_cep"), rs.getDate("cli_datacadastro")));
            }
        } catch (SQLException ex)
        {
            a = null;
        }
        //pegar os veiculos
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
        rs = Banco.getConexao().consultar(sql);
        try
        {
            while (rs.next())
            {
                a.add(new Cliente(rs.getInt("cli_cod"), rs.getString("cli_nome"),
                        rs.getString("cli_cpf"), rs.getString("cli_rg"), rs.getString("cli_telefone"),
                        rs.getString("cli_email"), rs.getString("cli_endereco"), rs.getString("cli_cep"), rs.getDate("cli_datacadastro")));
            }
        } catch (SQLException ex)
        {
            a = null;
        }
        return a;
    }

    public Object Get_Por_cpf(String Cpf) throws SQLException
    {
        Cliente c = null;
        String sql = "select * from cliente where cli_cpf = '" + Cpf + "'";
        ResultSet rs = Banco.getConexao().consultar(sql);
        while (rs.next())
        {
            c = new Cliente(rs.getInt("cli_cod"), rs.getString("cli_nome"),
                    rs.getString("cli_cpf"), rs.getString("cli_rg"), rs.getString("cli_telefone"),
                    rs.getString("cli_email"), rs.getString("cli_endereco"), rs.getString("cli_cep"), rs.getDate("cli_datacadastro"));
        }
        return c;
    }

    @Override
    public String toString()
    {
        return nome;
    }

    public void addVeiculos(Veiculo v)
    {
        veiculos.add(v);
    }

    @Override
    public boolean send(Produto p) //como verificar se quantidade do produto mudou?
    {
        boolean flag = false;
        if (p.getQuantidade() > 0)
        {
            flag = Email.enviarHotmail(email, "Alteração no estoque do produto '" + p.getNome() + "'", p.getNome() + " agora disponível");
        }
        return flag;
    }

    @Override
    public boolean receive(Produto p)
    {
        return true;
    }
}
