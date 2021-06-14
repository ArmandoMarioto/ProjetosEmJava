package Entidades;

import Banco.Banco;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OS
{
    private int codigo;
    private String descricao;
    private Date data;
    private Funcionário funcionario;
    private Status status;
    private Orçamento orcamento;

    public OS()
    {
    }

    public OS(int codigo, Funcionário funcionario)
    {
        this.codigo = codigo;
        this.funcionario = funcionario;
        this.descricao = "";
    }

    public OS(int codigo, String descricao, Funcionário funcionario)
    {
        this.codigo = codigo;
        this.descricao = descricao;
        this.funcionario = funcionario;
    }

    public OS(int codigo, String descricao, Funcionário funcionario, Orçamento orcamento)
    {
        this.codigo = codigo;
        this.descricao = descricao;
        this.funcionario = funcionario;
        this.orcamento = orcamento;
    }

    public OS(int codigo, String descricao, Funcionário funcionario, Status status, Orçamento orcamento)
    {
        this.codigo = codigo;
        this.descricao = descricao;
        this.funcionario = funcionario;
        this.status = status;
        this.orcamento = orcamento;
    }

    public OS(int codigo, String descricao, Date data, Funcionário funcionario, Status status)
    {
        this.codigo = codigo;
        this.descricao = descricao;
        this.data = data;
        this.funcionario = funcionario;
        this.status = status;
    }

    public OS(int codigo, String descricao, Date data, Funcionário funcionario, Status status, Orçamento orcamento)
    {
        this.codigo = codigo;
        this.descricao = descricao;
        this.data = data;
        this.funcionario = funcionario;
        this.status = status;
        this.orcamento = orcamento;
    }
    
    public int getCodigo()
    {
        return codigo;
    }

    public void setCodigo(int codigo)
    {
        this.codigo = codigo;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public Funcionário getFuncionario()
    {
        return funcionario;
    }

    public void setFuncionario(Funcionário funcionario)
    {
        this.funcionario = funcionario;
    }

    public Orçamento getOrcamento()
    {
        return orcamento;
    }

    public void setOrcamento(Orçamento orcamento)
    {
        this.orcamento = orcamento;
    }

    public Status getStatus()
    {
        return status;
    }

    public void setStatus(Status status)
    {
        this.status = status;
    }
    
    public boolean alterar()
    {
        String sql = "update ordem_de_servico set os_descricao = '$2', func_codigo = $3, stat_codigo = $4, os_data = '$5' "
                + "where os_codigo = $1";
        sql = sql.replace("$1", String.valueOf(codigo));
        sql = sql.replace("$2", descricao);
        sql = sql.replace("$3", String.valueOf(funcionario.getCodigo()));
        sql = sql.replace("$4", String.valueOf(status.getCodigo()));
        sql = sql.replace("$5", String.valueOf(data));
        
        return Banco.getCon().manipular(sql);
    }
    
    public ArrayList<OS> busca()
    {
        String campo = "";
        ArrayList<OS> os = new ArrayList<>();
        ResultSet rs,rsO,rsOS,rsS,rsaux;
        Orçamento o = null;
        ArrayList<Funcionário> funcionario = new ArrayList<>();
        ArrayList<Status> status = new ArrayList<>();
        Funcionário f = null;
        Status s = null;
        Cliente c = null;
        Veiculo v = null;
        
        
        rs = Banco.getCon().consultar("select * from ordem_de_servico inner join status on "
                + "status.stat_codigo = ordem_de_servico.stat_codigo and status.stat_descricao <> 'finalizado' "
                + "inner join orcamento on ordem_de_servico.orc_codigo = orcamento.orc_codigo inner join cliente "
                + "on orcamento.cli_codigo = cliente.cli_cod inner join veiculo on orcamento.vei_placa = veiculo.vei_placa");
        
        
        try
        {
            while(rs != null && rs.next())
            {   
                f = new Funcionário().get(rs.getInt("func_codigo"));
                v = new Veiculo(rs.getString("vei_chassi"), rs.getString("vei_placa"), rs.getString("vei_modelo"), 
                       rs.getString("vei_marca"), rs.getDate("vei_ano"), rs.getString("vei_cor"), 
                       rs.getString("vei_descricao"));
                c = new Cliente();
                c.setCep(rs.getString("cli_cep"));
                c.setCodigo(rs.getInt("cli_cod"));
                c.setCpf(rs.getString("cli_cpf"));
                c.setDtCadastro(rs.getDate("cli_datacadastro"));
                c.setEmail(rs.getString("cli_email"));
                c.setEndereco(rs.getString("cli_endereco"));
                c.setNome(rs.getString("cli_nome"));
                c.setRg(rs.getString("cli_rg"));
                c.setTelefone(rs.getString("cli_telefone"));
                c.addVeiculos(v);

                s = new Status(rs.getInt("stat_codigo"), rs.getString("stat_descricao"));

                if(s != null && c != null && v != null && f != null)
                {
                    o = new Orçamento(rs.getInt("orc_codigo"), rs.getString("orc_descricao"), f, c, v);
                    os.add(new OS(rs.getInt("os_codigo"), rs.getString("os_descricao"),rs.getDate("os_data"), f, s, o));
                }
                
                
                
               /*rsO = Banco.getCon().consultar("select * from orcamento where orc_codigo = " + rs.getInt("orc_codigo"));
               if(rsO != null && rsO.next())
               {
                   rsaux = Banco.getCon().consultar("select * from veiculo where vei_placa = '" + rsO.getString("vei_placa") + "'");
                   if(rsaux != null && rsaux.next())
                   {
                       f = new Funcionário(); f.setCodigo(rsO.getInt("cli_codigo"));
                       v = new Veiculo(rsaux.getString("vei_chassi"), rsaux.getString("vei_placa"), rsaux.getString("vei_modelo"), 
                               rsaux.getString("vei_marca"), rsaux.getDate("vei_ano"), rsaux.getString("vei_cor"), 
                               rsaux.getString("vei_descricao"));
                       rsaux = Banco.getCon().consultar("select * from cliente where cli_cod = " + rsO.getInt("cli_codigo"));
                       if(rsaux != null && rsaux.next())
                       {
                           c = new Cliente();
                           c.setCep(rsaux.getString("cli_cep"));
                           c.setCodigo(rsaux.getInt("cli_cod"));
                           c.setCpf(rsaux.getString("cli_cpf"));
                           c.setDtCadastro(rsaux.getDate("cli_datacadastro"));
                           c.setEmail(rsaux.getString("cli_email"));
                           c.setEndereco(rsaux.getString("cli_endereco"));
                           c.setNome(rsaux.getString("cli_nome"));
                           c.setRg(rsaux.getString("cli_rg"));
                           c.setTelefone(rsaux.getString("cli_telefone"));
                           c.addVeiculos(v);
                           
                           
                           o = new Orçamento(rsO.getInt("orc_codigo"), rsO.getString("orc_descricao"), f, c, v);
                           funcionario.add(f);
                       }
                   }
               }
               rsaux = Banco.getCon().consultar("select * from status where stat_codigo = " + rs.getInt("stat_codigo"));
               if(rsaux != null && rsaux.next())
               {
                   s = new Status(rs.getInt("stat_codigo"), rsaux.getString("stat_descricao"));
                   status.add(s);
               }
               if(s != null && c != null && v != null)
                   os.add(new OS(rs.getInt("os_codigo"), rs.getString("os_descricao"),rs.getDate("os_data"), funcionario, status, o));*/
            }
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        
        return os;
    }

    public Date getData()
    {
        return data;
    }

    public void setData(Date data)
    {
        this.data = data;
    }
}
