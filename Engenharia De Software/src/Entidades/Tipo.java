
package Entidades;

import Banco.Banco;
import Interface.Entidade;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Tipo implements Entidade
{

    private Integer codigo;
    private String nome;
    private String desc;

    public Tipo()
    {
        codigo = null;
        nome = null;
        desc = null;
    }

    public Tipo(int codigo)
    {
        this.codigo = codigo;
        getDadosTipo();
    }

    public Tipo(Integer codigo, String nome, String desc)
    {
        this.codigo = codigo;
        this.nome = nome;
        this.desc = desc;
    }

    public Integer getCodigo()
    {
        return codigo;
    }

    public void setCodigo(int codigo)
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

    public ArrayList<Object> get()//ainda em progresso
    {
        String sql = "select *from tipo";
        ResultSet rs = null;
        ArrayList<Object> a = new ArrayList<>();
        if (codigo != null)
        {
            sql += " tip_cod = " + codigo;
        }
        rs = Banco.getConexao().consultar(sql);
        try
        {
            while (rs.next())
            {
                a.add(new Tipo(rs.getInt("tip_cod"), rs.getString("tip_nome"), rs.getString("tip_desc")));
            }
        } catch (SQLException ex)
        {
            a = null;
        }
        return a;
    }

    public void getDadosTipo()
    {
        String sql = "select *from tipo";
        ResultSet rs = null;
        Tipo t = null;
        if (codigo != null)
        {
            sql += " where tip_cod = " + codigo;
        }
        rs = Banco.getConexao().consultar(sql);
        try
        {
            while (rs.next())
            {
                t = new Tipo(rs.getInt("tip_cod"), rs.getString("tip_nome"), rs.getString("tip_desc"));
            }
        } catch (SQLException ex)
        {
            t = null;
        }
        if (t != null)
        {
            nome = t.getNome();
            desc = t.getDesc();
        } else
        {
            nome = "Tipo Nao CadastradoN";
        }
    }

    @Override
    public Boolean insert()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean update()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean delete()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Object> get(String filtro)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
