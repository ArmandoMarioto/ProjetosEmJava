package Entidades;

import Banco.Banco;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Fornecedor 
{
    private int cod;
    private String cnpj;
    private String nome;
    private String telefone;
    private String email;
    
    private String rua;
    private String bairro;
    private int num;
    private String cidade;
    private String cep;

    public Fornecedor() {}
    
    public Fornecedor(int cod)
    {
        this.cod = cod;
    }
    
    public Fornecedor(int cod, String cnpj, String nome, String telefone, String email, String rua, String bairro, int num, String cidade, String cep) {
        this.cod = cod;
        this.cnpj = cnpj;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.rua = rua;
        this.bairro = bairro;
        this.num = num;
        this.cidade = cidade;
        this.cep = cep;
    }

    public int salvar(int flag)
    {
        String sql;
        if(flag == 1)
            sql = "update fornecedor set forn_cnpj = '$1', forn_nome = '$2', forn_telefone = '$3', forn_email = '$4', forn_rua = '$5', forn_bairro = '$6', forn_num = $7, forn_cidade = '$8', forn_cep = '$9' where forn_cod = " + cod;
        else
        {
            ResultSet rs;
            int cod = -1;
            
            try{
                rs = Banco.con.consultar("select * from fornecedor where forn_cnpj = '" + cnpj + "'");
                if(rs.next())
                    cod = rs.getInt("forn_cod");
                
                if(cod != -1)
                    return -1;
            }catch(Exception er){
                System.out.println("Erro: " + er.getMessage());
            }
            
            sql = "insert into fornecedor (forn_cod, forn_cnpj, forn_nome, forn_telefone, forn_email, forn_rua, forn_bairro, forn_num, forn_cidade, forn_cep)"
                + "values (nextval('seq_fornecedor') , '$1', '$2', '$3', '$4', '$5', '$6', $7, '$8', '$9')";
        }
        
        sql = sql.replace("$1", cnpj);
        sql = sql.replace("$2", nome);
        sql = sql.replace("$3", telefone);
        sql = sql.replace("$4", email);
        sql = sql.replace("$5", rua);
        sql = sql.replace("$6", bairro);
        sql = sql.replace("$7", "" + num);
        sql = sql.replace("$8", cidade);
        sql = sql.replace("$9", cep);
        System.out.println("" + sql);
        
        if(Banco.con.manipular(sql))
            return 1;
        return 0;
    }
    
    public ArrayList<Fornecedor> buscar (String str)
    {
        String sql = "select * from fornecedor where " + str;
        ResultSet rs;
        ArrayList<Fornecedor> list = new ArrayList<>();
        
        try
        {
            rs = Banco.con.consultar(sql);
            while(rs.next())
                list.add(new Fornecedor(rs.getInt("forn_num"), rs.getString("forn_cnpj"), rs.getString("forn_nome"), rs.getString("forn_telefone"), rs.getString("forn_email"), rs.getString("forn_rua"), rs.getString("forn_bairro"), rs.getInt("forn_num"), rs.getString("forn_cidade"), rs.getString("forn_cep")));
        }catch(Exception er)
        {
            System.out.println("Erro: " + er.getMessage());
        }
        return list;
    }
    
    public boolean excluir()
    {
        String str = "delete from fornecedor where forn_cod = " + cod;
        return Banco.con.manipular(str);
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }
    
    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}
