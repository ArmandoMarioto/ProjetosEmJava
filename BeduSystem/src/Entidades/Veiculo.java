package Entidades;

import Banco.Banco;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Veiculo
{

  private String vei_chassi ;
  private String vei_placa ;
  private String vei_modelo ;
  private String vei_marca ;
  private Date vei_ano ;
  private String vei_cor ;
  private String vei_descricao ;

    public Veiculo(String vei_chassi, String vei_placa, String vei_modelo, String vei_marca, Date vei_ano, String vei_cor, String vei_descricao) {
        this.vei_chassi = vei_chassi;
        this.vei_placa = vei_placa;
        this.vei_modelo = vei_modelo;
        this.vei_marca = vei_marca;
        this.vei_ano = vei_ano;
        this.vei_cor = vei_cor;
        this.vei_descricao = vei_descricao;
    }

    public Veiculo()
    {
    }

    public Veiculo(String vei_chassi) {
        this.vei_chassi = vei_chassi;
    }

    public Veiculo(String vei_chassi, String vei_placa, String vei_modelo, String vei_marca, Date vei_ano, String vei_cor) {
        this.vei_chassi = vei_chassi;
        this.vei_placa = vei_placa;
        this.vei_modelo = vei_modelo;
        this.vei_marca = vei_marca;
        this.vei_ano = vei_ano;
        this.vei_cor = vei_cor;
    }

    public String getVei_chassi() {
        return vei_chassi;
    }

    public void setVei_chassi(String vei_chassi) {
        this.vei_chassi = vei_chassi;
    }

    public String getVei_placa() {
        return vei_placa;
    }

    public void setVei_placa(String vei_placa) {
        this.vei_placa = vei_placa;
    }

    public String getVei_modelo() {
        return vei_modelo;
    }

    public void setVei_modelo(String vei_modelo) {
        this.vei_modelo = vei_modelo;
    }

    public String getVei_marca() {
        return vei_marca;
    }

    public void setVei_marca(String vei_marca) {
        this.vei_marca = vei_marca;
    }

    public Date getVei_ano() {
        return vei_ano;
    }

    public void setVei_ano(Date vei_ano) {
        this.vei_ano = vei_ano;
    }

    public String getVei_cor() {
        return vei_cor;
    }

    public void setVei_cor(String vei_cor) {
        this.vei_cor = vei_cor;
    }

    public String getVei_descricao() {
        return vei_descricao;
    }

    public void setVei_descricao(String vei_descricao) {
        this.vei_descricao = vei_descricao;
    }





    public Boolean insert()
    {
        String sql = "INSERT INTO veiculo(vei_chassi, vei_placa, vei_modelo, vei_marca ,vei_ano ,vei_cor,vei_descricao) VALUES('$1', '$2', '$3', '$4', '$5', '$6', '$7')";
        sql = sql.replace("$1", vei_chassi).replace("$2", vei_placa)
                .replace("$3", vei_modelo).replace("$4", vei_marca)
                .replace("$5", vei_ano.toString()).replace("$6", vei_cor)
                .replace("$8", vei_descricao);
        return Banco.getCon().manipular(sql);
    }

    public Boolean update()
    {

        String sql = "UPDATE veiculo SET vei_chassi = " + vei_chassi
                + ", vei_placa = " + vei_placa
                + ", vei_modelo = " + vei_modelo
                + ", vei_marca = '" + vei_marca
                + "', vei_ano = '" + vei_ano
                + "', vei_cor = '" + vei_cor
                 + ", vei_descricao = " + vei_descricao + "' WHERE vei_chassi = " + vei_chassi;
        return Banco.getCon().manipular(sql);
    }

    public Boolean delete()
    {
        String sql = "DELETE FROM veiculo WHERE vei_chassi = " + vei_chassi;
        return Banco.getCon().manipular(sql);
    }

    public ArrayList<Veiculo> get(String filtro, String busca)
    {

        String sql = "SELECT * FROM veiculo"
                + ((!busca.isEmpty()) ? busca + " LIKE '" + filtro + "%" + "'" : "");

        ArrayList<Veiculo> obj = new ArrayList<>();
        ResultSet rs = Banco.getCon().consultar(sql);

        try
        {
              //String vei_chassi ;String vei_placa ;String vei_modelo ;String vei_marca ;
  // Date vei_ano ;String vei_cor ;String vei_descricao ;
            while (rs != null && rs.next())
            {
                obj.add(new Veiculo(rs.getString("vei_chassi"),
                        rs.getString("vei_placa"),
                        rs.getString("vei_modelo"),
                        rs.getString("vei_marca"),
                        rs.getDate("vei_ano"),
                        rs.getString("vei_cor"),
                        rs.getString("vei_descricao")));
            }
        } catch (Exception ex)
        {
            System.out.println("Erro na classe veiculo: " + ex.getMessage());
        }
        return obj;
    }

    public ArrayList<Veiculo> getAll()
    {

        String sql = "SELECT * FROM veiculo";

        ArrayList<Veiculo> obj = new ArrayList<>();
        ResultSet rs = Banco.getCon().consultar(sql);

        try
        {
            while (rs != null && rs.next())
            {

                  obj.add(new Veiculo(rs.getString("vei_chassi"),
                        rs.getString("vei_placa"),
                        rs.getString("vei_modelo"),
                        rs.getString("vei_marca"),
                        rs.getDate("vei_ano"),
                        rs.getString("vei_cor"),
                        rs.getString("vei_descricao")));
            }
        } catch (Exception ex)
        {
            System.out.println("Erro na classe veiculo: " + ex.getMessage());
        }
        return obj;
    }


}
