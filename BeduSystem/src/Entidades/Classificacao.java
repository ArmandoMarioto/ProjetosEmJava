
package Entidades;

import Banco.Banco;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Classificacao {
    private int cod;
    private String descricao;

    public Classificacao() {
    }

    public Classificacao(int cod) {
        this.cod = cod;
    }
    
    

    public Classificacao(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public ArrayList<Classificacao> buscar()
    {
        String sql = "select * from classificacao";
        ResultSet rs;
        ArrayList<Classificacao> list = new ArrayList<>();
        
        try
        {
            rs = Banco.con.consultar(sql);
            while(rs.next())
                list.add(new Classificacao(rs.getInt("class_codigo"), rs.getString("class_descricao")));
        }catch(Exception er){
            System.out.println("Erro: " + er.getMessage());
        }
            
        return list;
    }
    
    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
