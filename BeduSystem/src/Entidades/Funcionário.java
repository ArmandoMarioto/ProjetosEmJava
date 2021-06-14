package Entidades;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import Banco.Banco;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Funcionário
{
    private int codigo;
    private String nome;
    private String funcao;
    private String telefone;
    private String celular;
    private String email;
    private String rua;
    private String bairro;
    private String cidade;
    private Date entrada;
    private boolean sexo;
    private boolean ativo;
    private String endereco;

    public Funcionário()
    {
    }

    public Funcionário(int codigo) {
        this.codigo = codigo;
    }
    

    public Funcionário(int codigo, String nome, String funcao, String rua, String bairro, String cidade, Date nascimento, boolean sexo)
    {
        this.codigo = codigo;
        this.nome = nome;
        this.funcao = funcao;
        this.rua = rua;
        this.bairro = bairro;
        this.cidade = cidade;
        this.entrada = nascimento;
        this.sexo = sexo;
        this.rua = "";
        this.bairro = "";
        this.cidade = "";
        ativo = true;
        endereco = rua + ", " + bairro + ", " + cidade; 
    }

    public Funcionário(int codigo, String nome, String funcao, String telefone, String celular, String email, String rua, String bairro, String cidade, Date nascimento, boolean sexo)
    {
        this.codigo = codigo;
        this.nome = nome;
        this.funcao = funcao;
        this.telefone = telefone;
        this.celular = celular;
        this.email = email;
        this.rua = rua;
        this.bairro = bairro;
        this.cidade = cidade;
        this.entrada = nascimento;
        this.sexo = sexo;
        ativo = true;
        endereco = rua + ", " + bairro + ", " + cidade;
    }

    public int getCodigo()
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

    public String getFuncao()
    {
        return funcao;
    }

    public void setFuncao(String funcao)
    {
        this.funcao = funcao;
    }

    public String getTelefone()
    {
        return telefone;
    }

    public void setTelefone(String telefone)
    {
        this.telefone = telefone;
    }

    public String getCelular()
    {
        return celular;
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

    public String getRua()
    {
        return rua;
    }

    public void setRua(String rua)
    {
        this.rua = rua;
    }

    public String getBairro()
    {
        return bairro;
    }

    public void setBairro(String bairro)
    {
        this.bairro = bairro;
    }

    public String getCidade()
    {
        return cidade;
    }

    public void setCidade(String cidade)
    {
        this.cidade = cidade;
    }

    public boolean getSexo()
    {
        return sexo;
    }

    public void setSexo(boolean sexo)
    {
        this.sexo = sexo;
    }

    public boolean isAtivo()
    {
        return ativo;
    }

    public void setAtivo(boolean ativo)
    {
        this.ativo = ativo;
    }

    public Date getEntrada()
    {
        return entrada;
    }

    public void setEntrada(Date entrada)
    {
        this.entrada = entrada;
    }

    public String getEndereco()
    {
        return endereco;
    }

    public void setEndereco(String endereco)
    {
        this.endereco = endereco;
    }
    
    @Override
    public String toString()
    {
        return nome;
    }
    
    public boolean gravar()
    {
        String sql = "insert into funcionario(func_nome,func_funcao,func_telefone,func_celular,func_email,func_rua,"
                + "func_bairro,func_cidade,func_sexo,func_dtentrada,func_ativo) values ('$1','$2','$3','$4','$5','$6','$7',"
                + "'$8',$9,'$10',$11)";
        sql = sql.replace("$1", nome);
        sql = sql.replace("$2", funcao);
        sql = sql.replace("$3", telefone);
        sql = sql.replace("$4", celular);
        sql = sql.replace("$5", email);
        sql = sql.replace("$6", rua);
        sql = sql.replace("$7", bairro);
        sql = sql.replace("$8", cidade);
        sql = sql.replace("$9", String.valueOf(sexo));
        sql = sql.replace(nome + "0", String.valueOf(entrada));
        sql = sql.replace(nome + "1", String.valueOf(ativo));
        
        return Banco.getCon().manipular(sql);
    }
    
    public boolean alterar()
    {
        String sql = "update funcionario set func_nome = '$2', func_funcao = '$3', func_telefone = '$4',"
                + "func_celular = '$5', func_email = '$6', func_rua = '$7',func_bairro = '$8',func_cidade='$9',"
                + "func_sexo = $10 where func_codigo = $1";
        sql = sql.replace("$1", String.valueOf(codigo));
        sql = sql.replace("$2", nome);
        sql = sql.replace("$3", funcao);
        sql = sql.replace("$4", telefone);
        sql = sql.replace("$5", celular);
        sql = sql.replace("$6", email);
        sql = sql.replace("$7", rua);
        sql = sql.replace("$8", bairro);
        sql = sql.replace("$9", cidade);
        sql = sql.replace(codigo + "0", String.valueOf(sexo));
        
        return Banco.getCon().manipular(sql);
    }
    
    public boolean remover()
    {
        String sql = "update funcionario set func_ativo = $2 where func_codigo = $1";
        sql = sql.replace("$1", String.valueOf(codigo));
        sql = sql.replace("$2", String.valueOf(false));
        
        return Banco.getCon().manipular(sql);  
    }
    
    public Funcionário get(String filtro)
    {
        Funcionário f = new Funcionário();
        f.setCodigo(0);
        ResultSet rs = Banco.getCon().consultar("select * from funcionario where func_nome like '%" + filtro + "%'");
        
        try
        {
            if(rs != null && rs.next())
            {
                f.setAtivo(rs.getBoolean("func_ativo"));
                f.setBairro(rs.getString("func_bairro"));
                f.setCelular(rs.getString("func_celular"));
                f.setCidade(rs.getString("func_cidade"));
                f.setCodigo(rs.getInt("func_codigo"));
                f.setEmail(rs.getString("func_email"));
                f.setFuncao(rs.getString("func_funcao"));
                f.setEntrada(rs.getDate("func_dtentrada"));
                f.setNome(rs.getString("func_nome"));
                f.setRua(rs.getString("func_rua"));
                f.setSexo(rs.getBoolean("func_sexo"));
                f.setTelefone(rs.getString("func_telefone"));
            }
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(Funcionário.class.getName()).log(Level.SEVERE, null, ex);
        }
        return f;
    }
    
    public Funcionário get_nome(String filtro)
    {
        Funcionário f = new Funcionário();
        f.setCodigo(0);
        ResultSet rs = Banco.getCon().consultar("select * from funcionario where func_nome = '" + filtro + "'");
        
        try
        {
            if(rs != null && rs.next())
            {
                f.setAtivo(rs.getBoolean("func_ativo"));
                f.setBairro(rs.getString("func_bairro"));
                f.setCelular(rs.getString("func_celular"));
                f.setCidade(rs.getString("func_cidade"));
                f.setCodigo(rs.getInt("func_codigo"));
                f.setEmail(rs.getString("func_email"));
                f.setFuncao(rs.getString("func_funcao"));
                f.setEntrada(rs.getDate("func_dtentrada"));
                f.setNome(rs.getString("func_nome"));
                f.setRua(rs.getString("func_rua"));
                f.setSexo(rs.getBoolean("func_sexo"));
                f.setTelefone(rs.getString("func_telefone"));
                f.setCodigo(rs.getInt("func_codigo"));
            }
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(Funcionário.class.getName()).log(Level.SEVERE, null, ex);
        }
        return f;
    }
    
    public Funcionário get(int filtro)
    {
        Funcionário f = new Funcionário();
        f.setCodigo(0);
        ResultSet rs = Banco.getCon().consultar("select * from funcionario where func_codigo = " + filtro);
        
        try
        {
            if(rs != null && rs.next())
            {
                f.setNome(rs.getString("func_nome"));
                f.setAtivo(rs.getBoolean("func_ativo"));
                f.setBairro(rs.getString("func_bairro"));
                f.setCelular(rs.getString("func_celular"));
                f.setCidade(rs.getString("func_cidade"));
                f.setCodigo(rs.getInt("func_codigo"));
                f.setEmail(rs.getString("func_email"));
                f.setFuncao(rs.getString("func_funcao"));
                f.setEntrada(rs.getDate("func_dtentrada"));
                f.setNome(rs.getString("func_nome"));
                f.setRua(rs.getString("func_rua"));
                f.setSexo(rs.getBoolean("func_sexo"));
                f.setTelefone(rs.getString("func_telefone"));
            }
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(Funcionário.class.getName()).log(Level.SEVERE, null, ex);
        }
        return f;
    }
    
    public ArrayList<String> getNomes()
    {
        ArrayList<String> nomes = new ArrayList<>();
        ResultSet rs = Banco.getCon().consultar("select func_nome from funcionario");
        
        try
        {
            while(rs != null && rs.next())            
            {
                nomes.add(rs.getString("func_nome"));
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(Funcionário.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nomes;
    }
    
    public ArrayList<Funcionário> getAll(String filtro)
    {
        ArrayList<Funcionário> funcionarios = new ArrayList<>();
        Funcionário f;
        ResultSet rs = Banco.getCon().consultar("select * from funcionario where func_nome like '%" + filtro + "%' and func_ativo = TRUE and func_nome <> 'admin'");
        
        try
        {
            while(rs != null && rs.next())
            {
                f = new Funcionário();
                f.setAtivo(rs.getBoolean("func_ativo"));
                f.setBairro(rs.getString("func_bairro"));
                f.setCelular(rs.getString("func_celular"));
                f.setCidade(rs.getString("func_cidade"));
                f.setCodigo(rs.getInt("func_codigo"));
                f.setEmail(rs.getString("func_email"));
                f.setFuncao(rs.getString("func_funcao"));
                f.setEntrada(rs.getDate("func_dtentrada"));
                f.setNome(rs.getString("func_nome"));
                f.setRua(rs.getString("func_rua"));
                f.setSexo(rs.getBoolean("func_sexo"));
                f.setTelefone(rs.getString("func_telefone"));
                funcionarios.add(f);
            }
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(Funcionário.class.getName()).log(Level.SEVERE, null, ex);
        }
        return funcionarios;
    }
    
    public ObservableList<Funcionário> getAll(String filtro,String campo)
    {
        ObservableList<Funcionário> funcionarios = FXCollections.observableArrayList();
        Funcionário f;
        ResultSet rs;
        String sql = "select * from funcionario where ";
        
        if(!campo.equals("func_dtentrada") && !campo.equals("func_sexo"))
            sql +=  campo + " like '%" + filtro + "%'";
        else if(campo.equals("func_sexo"))
            sql += campo + " = " + filtro;
        else
            sql += campo + " <" + filtro;
        sql += " and func_ativo = TRUE and func_nome <> 'admin'";
            
        rs = Banco.getCon().consultar(sql);
        try
        {
            while(rs != null && rs.next())
            {
                f = new Funcionário();
                f.setAtivo(rs.getBoolean("func_ativo"));
                f.setBairro(rs.getString("func_bairro"));
                f.setCelular(rs.getString("func_celular"));
                f.setCidade(rs.getString("func_cidade"));
                f.setCodigo(rs.getInt("func_codigo"));
                f.setEmail(rs.getString("func_email"));
                f.setFuncao(rs.getString("func_funcao"));
                f.setEntrada(rs.getDate("func_dtentrada"));
                f.setNome(rs.getString("func_nome"));
                f.setRua(rs.getString("func_rua"));
                f.setSexo(rs.getBoolean("func_sexo"));
                f.setTelefone(rs.getString("func_telefone"));
                funcionarios.add(f);
            }
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(Funcionário.class.getName()).log(Level.SEVERE, null, ex);
        }
        return funcionarios;
    }
    
    public ArrayList<Funcionário> getAtivos()
    {
        ArrayList<Funcionário> funcionario = new ArrayList<>();
        Funcionário f;
        ResultSet rs = Banco.getCon().consultar("select * from funcionario where func_nome <> 'admin' AND func_ativo = 1");
        
        try
        {
            while(rs != null && rs.next())            
            {
                f = new Funcionário();
                f.setAtivo(rs.getBoolean("func_ativo"));
                f.setBairro(rs.getString("func_bairro"));
                f.setCelular(rs.getString("func_celular"));
                f.setCidade(rs.getString("func_cidade"));
                f.setCodigo(rs.getInt("func_codigo"));
                f.setEmail(rs.getString("func_email"));
                f.setFuncao(rs.getString("func_funcao"));
                f.setEntrada(rs.getDate("func_dtentrada"));
                f.setNome(rs.getString("func_nome"));
                f.setRua(rs.getString("func_rua"));
                f.setSexo(rs.getBoolean("func_sexo"));
                f.setTelefone(rs.getString("func_telefone"));
                funcionario.add(f);
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(Funcionário.class.getName()).log(Level.SEVERE, null, ex);
        }
        return funcionario;
    }
}
