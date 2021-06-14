package Entidades;

import java.sql.ResultSet;
import java.util.ArrayList;
import Banco.Banco;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Status 
{
    private int codigo;
    private String descricao;

    public Status()
    {
    }

    public Status(int codigo, String descricao)
    {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Status(int codigo)
    {
        this.codigo = codigo;
        this.descricao = "";
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
    
    public boolean gravar()
    {
        String sql = "insert into status(stat_descricao) values('$1')";
        sql = sql.replace("$1", descricao);
        
        return Banco.getCon().manipular(sql);
    }
    
    public boolean alterar()
    {
        String sql = "update status set stat_descricao = '$1' where stat_codigo = " + codigo;
        sql = sql.replace("$1", descricao);
        
        return Banco.getCon().manipular(sql);
    }
    
    public boolean remover()
    {
        String sql = "delete from status where stat_codigo = " + codigo;
        
        return Banco.getCon().manipular(sql);
    }
    
    public ArrayList<Status>buscaTodos()
    {
        ArrayList<Status> status = new ArrayList<>();
        ResultSet rs = Banco.getCon().consultar("select * from status");
        
        try
        {
            while(rs != null && rs.next())     
                status.add(new Status(rs.getInt("stat_codigo"), rs.getString("stat_descricao")));
        } catch (SQLException ex)
        {
            Logger.getLogger(Status.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }
    
    public ArrayList<String> buscaTodosDescricao()
    {
        ArrayList<String> status = new ArrayList<>();
        ResultSet rs = Banco.getCon().consultar("select * from status");
        
        try
        {
            while(rs != null && rs.next())     
                status.add(rs.getString("stat_descricao"));
        } catch (SQLException ex)
        {
            Logger.getLogger(Status.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }
    
    public Status buscaStatus(String desc)
    {
        Status s = null;
        ResultSet rs = Banco.getCon().consultar("select * from status where stat_descricao = '" + desc + "'");
        
        try
        {
            if(rs != null && rs.next())     
                s = new Status(rs.getInt("stat_codigo"), desc);
        } catch (SQLException ex)
        {
            Logger.getLogger(Status.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }
}
