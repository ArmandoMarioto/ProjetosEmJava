
package Entidades;

import java.sql.ResultSet;
import javafx.scene.image.Image;
import Banco.Banco;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import javax.imageio.ImageIO;

public class Parametrizacao
{
    private String nome;
    private String fantasia;
    private BufferedImage logoGrande;
    private BufferedImage logoPequeno;
    private String telefone;
    private String email;
    private String razaoSocial;
    private String rua;
    private String bairro;
    private String cidade;
    private String cep;
    private String cor;
    private String site;

    public Parametrizacao(String nome, String fantasia, String telefone, String email, String razaoSocial, String rua, String bairro, String cidade, String cep, String cor,String site) {
        this.nome = nome;
        this.fantasia = fantasia;
        this.telefone = telefone;
        this.email = email;
        this.razaoSocial = razaoSocial;
        this.rua = rua;
        this.bairro = bairro;
        this.cidade = cidade;
        this.cep = cep;
        this.cor = cor;
        this.site = site;
    }

    public Parametrizacao()
    {
    }

    public Parametrizacao(String nome, String fantasia, BufferedImage logoGrande, BufferedImage logoPequeno, String telefone, String email, String razaoSocial, String rua, String bairro, String cidade, String cep, String cor,String site)
    {
        this.nome = nome;
        this.fantasia = fantasia;
        this.logoGrande = logoGrande;
        this.logoPequeno = logoPequeno;
        this.telefone = telefone;
        this.email = email;
        this.razaoSocial = razaoSocial;
        this.rua = rua;
        this.bairro = bairro;
        this.cidade = cidade;
        this.cep = cep;
        this.cor = cor;
        this.site = site;
    }
    

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getFantasia()
    {
        return fantasia;
    }

    public void setFantasia(String fantasia)
    {
        this.fantasia = fantasia;
    }

    public BufferedImage getLogoGrande()
    {
        return logoGrande;
    }

    public void setLogoGrande(BufferedImage logoGrande)
    {
        this.logoGrande = logoGrande;
    }

    public BufferedImage getLogoPequeno()
    {
        return logoPequeno;
    }

    public void setLogoPequeno(BufferedImage logoPequeno)
    {
        this.logoPequeno = logoPequeno;
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

    public String getRazaoSocial()
    {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial)
    {
        this.razaoSocial = razaoSocial;
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

    public String getCep()
    {
        return cep;
    }

    public void setCep(String cep)
    {
        this.cep = cep;
    }

    public String getCor()
    {
        return cor;
    }

    public void setCor(String cor)
    {
        this.cor = cor;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }
    
    
    public boolean inicia() throws SQLException
    {
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM parametrizacao");
        return (rs != null && rs.next());
    }
        public Parametrizacao carrega() throws SQLException, IOException
    {
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM parametrizacao");
        rs.next();
        return new Parametrizacao(rs.getString("nome"),rs.getString("fantasia"),ImageIO.read(new ByteArrayInputStream(rs.getBytes("logoGrande"))), ImageIO.read(new ByteArrayInputStream(rs.getBytes("logoPequeno"))), 
                rs.getString("telefone"), rs.getString("email"), rs.getString("razaoSocial"), rs.getString("rua"), rs.getString("bairro"), rs.getString("cidade"), rs.getString("cep"), rs.getString("cor"), rs.getString("site"));
    }
        
        public boolean Manipular(String caminho, String caminho2) throws SQLException, FileNotFoundException, IOException
    {
        if (caminho != null && caminho2 != null)
        {
            File arq = new File(caminho);
            File arq2 = new File(caminho2);
            FileInputStream f = new FileInputStream(arq);
            FileInputStream f2 = new FileInputStream(arq2);
            Connection connection = null;
            PreparedStatement statement = null;

            connection = Banco.getCon().getConnection();

            Banco.getCon().consultar("delete from parametrizacao");
            statement = connection.prepareStatement("INSERT INTO parametrizacao(nome,fantasia,logoGrande,logoPequeno,telefone,email,razaoSocial,rua,bairro,cidade,cep,cor,site) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");
            statement.setString(1, nome);
            statement.setString(2, fantasia);
            statement.setBinaryStream(3, f, (int) arq.length());
            statement.setBinaryStream(4, f2, (int) arq2.length());
            statement.setString(5, telefone);
            statement.setString(6, email);
            statement.setString(7, razaoSocial);
            statement.setString(8, rua);
            statement.setString(9, bairro);
            statement.setString(10, cidade);
            statement.setString(11, cep);
            statement.setString(12, cor);
            statement.setString(13, site);
            try
            {
                statement.executeUpdate();
                statement.close();
                 return true;

            } catch (SQLException e)
            {
                System.out.println(e);
            }
        } return false;

    }
        
    public boolean alterar(BufferedImage img,BufferedImage img2) throws SQLException, IOException
    {
            Connection connection = null;
            PreparedStatement statement = null;
            
           ByteArrayOutputStream bimg = new ByteArrayOutputStream();
           ImageIO.write(img, "jpg", bimg);
           InputStream f = new ByteArrayInputStream(bimg.toByteArray());
           
           ByteArrayOutputStream bimg2 = new ByteArrayOutputStream();
           ImageIO.write(img2, "jpg", bimg2);
           InputStream f2 = new ByteArrayInputStream(bimg2.toByteArray());

            connection = Banco.getCon().getConnection();
            //nome,fantasia,logoGrande,logoPequeno,telefone,email,razaoSocial,rua,bairro,cidade,cep,cor,site;
            statement = connection.prepareStatement("UPDATE parametrizacao SET nome = ?,fantasia = ?,logoGrande = ? ,logoPequeno = ?,telefone = ?, email = ?, razaoSocial = ?, rua = ?, bairro = ?, cidade = ?, cep = ?, cor = ?, site = ?");
            statement.setString(1, nome);
            statement.setString(2, fantasia);
            statement.setBinaryStream(3, f, bimg.toByteArray().length);
            statement.setBinaryStream(4, f2, bimg2.toByteArray().length);
            statement.setString(5, telefone);
            statement.setString(6, email);
            statement.setString(7, razaoSocial);
            statement.setString(8, rua);
            statement.setString(9, bairro);
            statement.setString(10, cidade);
            statement.setString(11, cep);
            statement.setString(12, cor);
            statement.setString(13, site);
            try
            {
                statement.executeUpdate();
                statement.close();
                return true;

            } catch (SQLException e)
            {
                System.out.println(e);
            }
            return false;
    }
        
}
/*
 //nome,fantasia,logoGrande,logoPequeno,telefone,email,razaoSocial,rua,bairro,cidade,cep,cor,site;
create table parametrizacao
(
    nome varchar(50) not null,
    fantasia varchar(50) not null,
    logoGrande bytea,
    logoPequeno bytea,
    telefone varchar(50) not null,
    email varchar(50) not null,
    razaoSocial varchar(50) not null,
    rua varchar(50) not null,
    bairro varchar(50) not null,
    cidade varchar(50) not null,
    cep varchar(50) not null,
    cor varchar(50) not null,
    site varchar(50) not null 
)
*/