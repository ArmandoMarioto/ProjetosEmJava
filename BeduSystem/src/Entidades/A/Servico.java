package Entidades.A;
import Banco.Banco;
import Entidades.Produto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Servico
{
    private Integer codigo_servico;
    private String nome;
    private String desc;

    public Servico()
    {
    }

    public Servico(Integer codigo_servico, String nome, String desc)
    {
        this.codigo_servico = codigo_servico;
        this.nome = nome;
        this.desc = desc;
    }

    public Servico(String nome, String desc)
    {
        this.nome = nome;
        this.desc = desc;
    }

    public Servico(Integer codigo_servico) throws SQLException
    {
        this.codigo_servico = codigo_servico;
        preenceDados();
    }

    public Servico(Integer codigo_servico, String nome, String desc, ArrayList<Produto> peças)
    {
        this.codigo_servico = codigo_servico;
        this.nome = nome;
        this.desc = desc;
    }
    public Integer getCodigo_servico()
    {
        return codigo_servico;
    }

    public void setCodigo_servico(Integer codigo_servico)
    {
        this.codigo_servico = codigo_servico;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getDesc()
    {
        return desc;
    }

    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    @Override
    public String toString()
    {
        return nome;
    }
    
    private void preenceDados() throws SQLException
    {
        String sql = "select * from Servicos where serv_codigo = " + codigo_servico;
        ResultSet rs = null;
        rs = Banco.getCon().consultar(sql);
        while (rs.next())
        {
            nome = rs.getString("serv_descricao");
            desc = rs.getString("serv_descricao");
        }
    }
    
        public static ArrayList<Object> get(String f) throws SQLException
    {
        String sql = "select * from Servicos";
        if (!f.isEmpty())
        {
            sql += " where serv_descricao like'%" + f + "%'";
        }
        ResultSet rs = Banco.getCon().consultar(sql);
        ArrayList<Object> a = new ArrayList<>();
        while (rs.next())
        {
            Servico s = new Servico(rs.getInt("serv_codigo"), rs.getString("serv_descricao"), rs.getString("serv_descricao"));
            a.add(s);
        }
        return a;
    }

}
