package Entidades;

import java.sql.ResultSet;
import Banco.Banco;
import java.sql.SQLException;
import java.util.ArrayList;

public class Usuario
{
    private Funcionário funcionario;
    private String login;
    private String senha;
    private int nivel;

    public Usuario()
    {
    }

    public Usuario(Funcionário funcionario, String login, String senha, int nivel)
    {
        this.funcionario = funcionario;
        this.login = login;
        this.senha = senha;
        this.nivel = nivel;
    }

    public Usuario(Funcionário funcionario)
    {
        this.funcionario = funcionario;
    }
    
    public Funcionário getFuncionario()
    {
        return funcionario;
    }

    public void setFuncionario(Funcionário funcionario)
    {
        this.funcionario = funcionario;
    }

    public String getLogin()
    {
        return login;
    }

    public void setLogin(String login)
    {
        this.login = login;
    }

    public String getSenha()
    {
        return senha;
    }

    public void setSenha(String senha)
    {
        this.senha = senha;
    }

    public int getNivel()
    {
        return nivel;
    }

    public void setNivel(int nivel)
    {
        this.nivel = nivel;
    }
    
    public boolean gravar()
    {
        String sql = "insert into usuario(func_codigo, usu_login,usu_senha,usu_nivel) values ($1,'$2','$3',$4)";
        sql = sql.replace("$1", String.valueOf(funcionario.getCodigo()));
        sql = sql.replace("$2", login);
        sql = sql.replace("$3", senha);
        sql = sql.replace("$4", String.valueOf(nivel));
        
        return Banco.getCon().manipular(sql);
    }
    
    public boolean alterar()
    {
        String sql = "update usuario set usu_login = '$2', usu_senha = '$3', usu_nivel = $4 where func_codigo = $1";
        sql = sql.replace("$1", String.valueOf(funcionario.getCodigo()));
        sql = sql.replace("$2", login);
        sql = sql.replace("$3", senha);
        sql = sql.replace("$4", String.valueOf(nivel));
        
        return Banco.getCon().manipular(sql);
    }
    
    public boolean remover()
    {
        String sql = "delete from usuario where usu_login = '$1'";
        sql = sql.replace("$1", login);
        
        return Banco.getCon().manipular(sql);
    }
    
    public Boolean busca(String filtro)
    {
        ResultSet rs = Banco.getCon().consultar("Select * from usuario where usu_login = '" + filtro + "'");
        Boolean achou = false;
        
        try
        {
            if(rs != null && rs.next())
            {
                setLogin(rs.getString("usu_login"));
                setNivel(rs.getInt("usu_nivel"));
                setSenha(rs.getString("usu_senha"));
                achou = true;
            }
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return achou;     
    }
    
    public Usuario busca()
    {
        ResultSet rs = Banco.getCon().consultar("Select * from usuario where func_codigo = " + funcionario.getCodigo());
        Boolean achou = false;
        
        try
        {
            if(rs != null && rs.next())
            {
                setLogin(rs.getString("usu_login"));
                setNivel(rs.getInt("usu_nivel"));
                setSenha(rs.getString("usu_senha"));
                achou = true;
            }
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return this;     
    }
    
    public ArrayList<Usuario> busca(String filtro, int tipo)
    {
        String campo = "";
        ArrayList<Usuario> usuarios = new ArrayList<>();
        ResultSet rs;
        Usuario u;
        Funcionário f = new Funcionário().get("admin");
        
        if(filtro.equals(""))
            rs = Banco.getCon().consultar("Select * from usuario inner join funcionario on usuario.func_codigo = funcionario.func_codigo and usuario.func_codigo <> " + f.getCodigo() + " AND func_ativo = true");
        else
        {
            if(tipo == 0)
                rs = Banco.getCon().consultar("Select * from usuario where func_codigo = " + new Funcionário().get(filtro).getCodigo());
            else
            {
                if(tipo == 1)
                    campo = "usu_login";
                else if (tipo == 2)
                    campo = "usu_nivel";
                rs = Banco.getCon().consultar("Select * from usuario where " + campo + " = '" + filtro + "' AND func_codigo <> " + f.getCodigo());
            }
        }
        
        
        try
        {
            while(rs != null && rs.next())
            {
                u = new Usuario(new Funcionário().get(rs.getString("func_codigo")), rs.getString("usu_login"), 
                        rs.getString("usu_senha"), rs.getInt("usu_nivel"));
                f = new Funcionário().get(rs.getInt("func_codigo"));
                u.setFuncionario(f);
                usuarios.add(u);
            }
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        
        return usuarios;
    }
}
