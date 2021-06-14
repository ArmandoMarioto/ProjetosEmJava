
package Entidades;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import javax.imageio.ImageIO;

public class Parametrizacao 
{
    private String NomeFantasia;
    private String RazaoSocial;
    private BufferedImage LogoGrande;
    private BufferedImage LogoPequeno;
    private String Endereco;
    private String CorFundo;
    private String TipoFonte;
    private String Site;
    private String Email;
    private String Fone;

    public Parametrizacao() {
    }

    public Parametrizacao(String NomeFantasia, String RazaoSocial, BufferedImage LogoGrande, BufferedImage LogoPequeno, String Endereco, String CorFundo, String TipoFonte, String Site, String Email, String Fone) {
        this.NomeFantasia = NomeFantasia;
        this.RazaoSocial = RazaoSocial;
        this.LogoGrande = LogoGrande;
        this.LogoPequeno = LogoPequeno;
        this.Endereco = Endereco;
        this.CorFundo = CorFundo;
        this.TipoFonte = TipoFonte;
        this.Site = Site;
        this.Email = Email;
        this.Fone = Fone;
    }



   

    
    public Parametrizacao(String NomeFantasia, String RazaoSocial, String Endereco, String CorFundo, String TipoFonte, String Site, String Email, String Fone) {
        this.NomeFantasia = NomeFantasia;
        this.RazaoSocial = RazaoSocial;
        this.Endereco = Endereco;
        this.CorFundo = CorFundo;
        this.TipoFonte = TipoFonte;
        this.Site = Site;
        this.Email = Email;
        this.Fone = Fone;
    }

    public String getNomeFantasia() {
        return NomeFantasia;
    }

    public void setNomeFantasia(String NomeFantasia) {
        this.NomeFantasia = NomeFantasia;
    }

    public String getRazaoSocial() {
        return RazaoSocial;
    }

    public void setRazaoSocial(String RazaoSocial) {
        this.RazaoSocial = RazaoSocial;
    }

    public BufferedImage getLogoGrande() {
        return LogoGrande;
    }

    public BufferedImage getLogoPequeno() {
        return LogoPequeno;
    }




    public String getEndereco() {
        return Endereco;
    }

    public void setEndereco(String Endereco) {
        this.Endereco = Endereco;
    }

    public String getCorFundo() {
        return CorFundo;
    }

    public void setCorFundo(String CorFundo) {
        this.CorFundo = CorFundo;
    }

    public String getTipoFonte() {
        return TipoFonte;
    }

    public void setTipoFonte(String TipoFonte) {
        this.TipoFonte = TipoFonte;
    }

    public String getSite() {
        return Site;
    }

    public void setSite(String Site) {
        this.Site = Site;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getFone() {
        return Fone;
    }

    public void setFone(String Fone) {
        this.Fone = Fone;
    }

    public void setLogoGrande(BufferedImage LogoGrande) {
        this.LogoGrande = LogoGrande;
    }

    public void setLogoPequeno(BufferedImage LogoPequeno) {
        this.LogoPequeno = LogoPequeno;
    }


    
    public boolean inicia() throws SQLException
    {
        ResultSet rs = Banco.BD.getCon().consultar("SELECT * FROM parametrizacao");
        return (rs != null && rs.next());
    }
    public Parametrizacao carrega() throws SQLException, IOException
    {
        ResultSet rs = Banco.BD.getCon().consultar("SELECT * FROM parametrizacao");
        rs.next();
        return new Parametrizacao(rs.getString("NomeFantasia"),rs.getString("RazaoSocial"),ImageIO.read(new ByteArrayInputStream(rs.getBytes("logogrande"))),ImageIO.read(new ByteArrayInputStream(rs.getBytes("logopequeno"))),rs.getString("Endereco"),rs.getString("CorFundo"),rs.getString("TipoFonte"),rs.getString("Site"),rs.getString("Email"),rs.getString("Telefone"));
    }
    public boolean Manipular(String caminho,String caminho2) throws SQLException, FileNotFoundException, IOException 
    {
        File arq = new File(caminho);
        File arq2 = new File(caminho2);
        FileInputStream f = new FileInputStream(arq);
        FileInputStream f2 = new FileInputStream(arq2);
        Connection connection = null;
        PreparedStatement statement = null;

        connection = Banco.BD.getCon().getConnection();

         Banco.BD.getCon().consultar("delete from parametrizacao");
        statement = connection.prepareStatement("INSERT INTO parametrizacao(nomefantasia,RazaoSocial,LogoGrande,LogoPequeno,Endereco,CorFundo,TipoFonte,Site,Email,Telefone) VALUES(?,?,?,?,?,?,?,?,?,?)");
        statement.setString(1, getNomeFantasia());
        statement.setString(2, getRazaoSocial());
        statement.setBinaryStream(3, f,(int)arq.length());
        statement.setBinaryStream(4, f2,(int)arq2.length());
        statement.setString(5, getEndereco());
        statement.setString(6, getCorFundo());
        statement.setString(7, getTipoFonte());
        statement.setString(8, getSite());
        statement.setString(9, getEmail());
        statement.setString(10,getFone());

        try {
            statement.executeUpdate();
            statement.close();
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setContentText("Cadastrado com sucesso!!!");
            alerta.showAndWait();

        } catch (SQLException e) {
            System.out.println(e);
        }

        return true;
    }

}