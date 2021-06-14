/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author armando
 */
public class Agendar 
{
    //Nome,Telefone,Modelo,Placa,endereço,serviços,dia,horario,disponivel
    private int codigo;
    private String nome;
    private String telefone;
    private String modelo;
    private String Placa;
    private String endereco;
    private String cep;
    private String servicos;
    private Date dia;
    private String horario;
    private String disponivel;
    private String obs;

    public Agendar() {
    }

    public Agendar(int codigo, String nome, String telefone, String modelo, String Placa, String endereco, String cep, String servicos, Date dia, String horario, String disponivel, String obs) {
        this.codigo = codigo;
        this.nome = nome;
        this.telefone = telefone;
        this.modelo = modelo;
        this.Placa = Placa;
        this.endereco = endereco;
        this.cep = cep;
        this.servicos = servicos;
        this.dia = dia;
        this.horario = horario;
        this.disponivel = disponivel;
        this.obs = obs;
    }

    public Agendar(String nome, String telefone, String modelo, String Placa, String endereco, String cep, String servicos, Date dia, String horario, String disponivel, String obs) {
        this.nome = nome;
        this.telefone = telefone;
        this.modelo = modelo;
        this.Placa = Placa;
        this.endereco = endereco;
        this.cep = cep;
        this.servicos = servicos;
        this.dia = dia;
        this.horario = horario;
        this.disponivel = disponivel;
        this.obs = obs;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return Placa;
    }

    public void setPlaca(String Placa) {
        this.Placa = Placa;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getServicos() {
        return servicos;
    }

    public void setServicos(String servicos) {
        this.servicos = servicos;
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(String disponivel) {
        this.disponivel = disponivel;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    

   

    
    
    public boolean Salvar(String sql) throws SQLException
    {
        setDisponivel("Reservado");
       ///cod,Nome,Telefone,Modelo,Placa,endereço,cep,serviços,dia,horario,disponivel,obs
        sql = "INSERT INTO agendamento_servico(age_nome,age_telefone, age_modelo, age_placa, age_endereco,age_cep, age_servico,age_data,age_horario,age_disponivel,age_obs) VALUES ('$1','$2','$3','$4','$5','$6','$7','$8','$9','$10','$11')";
        sql = sql.replace("$1", getNome());
        sql = sql.replace("$2", getTelefone());
        sql = sql.replace("$3", getModelo());
        sql = sql.replace("$4", getPlaca());
        sql = sql.replace("$5", getEndereco());
        sql = sql.replace("$6", getCep());
        sql = sql.replace("$7", getServicos());
        sql = sql.replace("$8", getDia().toString());
        sql = sql.replace("$9", getHorario());
        sql = sql.replace("$10", getDisponivel());
        sql = sql.replace("$11", getObs());
        

        return Banco.Banco.con.manipular(sql);
    }

    public boolean Alterar(String sql)
    {
                                                                                                                                                           //cod,Nome,Telefone,Modelo,Placa,endereço,cep,serviços,dia,horario,disponivel,obs
        sql = "UPDATE agendamento_servico SET age_nome = '$1',age_telefone = '$2', age_modelo = '$3', age_placa = '$4', age_endereco = '$5', age_cep = '$6', age_servico = '$7', age_data = '$8' , age_horario = '$9' , age_disponivel = '$10' , age_obs = '$11'  WHERE age_codigo = " + getCodigo();
        sql = sql.replace("$1", getNome());
        sql = sql.replace("$2", getTelefone());
        sql = sql.replace("$3", getModelo());
        sql = sql.replace("$4", getPlaca());
        sql = sql.replace("$5", getEndereco());
        sql = sql.replace("$6", getCep());
        sql = sql.replace("$7", getServicos());
        sql = sql.replace("$8", getDia().toString());
        sql = sql.replace("$9", getHorario());
        sql = sql.replace("$10", getDisponivel());
        sql = sql.replace("$11", getObs());

        return Banco.Banco.con.manipular(sql);
    }

    public boolean Apagar(String sql)
    {
        sql = "DELETE FROM agendamento_servico WHERE age_codigo = " + getCodigo();

        return Banco.Banco.con.manipular(sql);
    }
    public ObservableList<String> BuscarServicos()
    {
        ObservableList<String> obj = FXCollections.observableArrayList();
        
        ResultSet rs = Banco.Banco.con.consultar("SELECT ser_nome FROM servico");

        try
        {
            while (rs != null && rs.next())
            {
                obj.add(rs.getString("ser_nome"));
            }
        } catch (SQLException ex)
        {

        }
        return obj;
    }
    
    public ObservableList<Agendar> BuscarHorarios(String servico, LocalDate data)
    {
        ObservableList<Agendar> obj = FXCollections.observableArrayList();
        
        ResultSet rs = Banco.Banco.con.consultar("SELECT * FROM agendamento_servico where '"+servico+"' = age_servico and age_data = '"+data+"'");
                                                                                                                                                    //Nome,Telefone,Modelo,Placa,endereço,serviços,dia,horario,disponivel
        try
        {
            while (rs != null && rs.next())
            {
                obj.add(new Agendar(rs.getInt("age_codigo"), rs.getString("age_nome"), rs.getString("age_telefone"), rs.getString("age_modelo"), rs.getString("age_placa"), rs.getString("age_endereco"),rs.getString("age_cep"), rs.getString("age_servico"), rs.getDate("age_data"), rs.getString("age_horario"), rs.getString("age_disponivel"), rs.getString("age_obs")));
            }
        } catch (SQLException ex)
        {

        }
        return obj;
    }

    /*
    
     //cod,Nome,Telefone,Modelo,Placa,endereço,cep,serviços,dia,horario,disponivel,obs
    CREATE TABLE agendamento_servico(

    age_codigo       serial NOT NULL,
    age_nome       varchar(20)          NOT NULL,
    age_telefone varchar(20)  ,
    age_modelo varchar(20) ,
    age_placa varchar(20) ,
    age_endereco varchar(20) ,
    age_cep varchar(20) ,
    age_servico       varchar(20)          NOT NULL,
    age_data       date           NOT NULL,
    age_horario    varchar(5)     NOT NULL,
    age_disponivel         char(10),
    age_obs            varchar(10),
    CONSTRAINT "PK15" PRIMARY KEY (age_codigo)

)
;
    
    insert into agendamento_servico(age_nome,age_telefone,age_modelo,age_placa,age_endereco,age_cep,age_servico,age_data,age_horario,age_disponivel,age_obs) values('Armando','32752432','Honda civic','DGC-1932','Domingos Dare','19500000','Troca de Pneu','19/12/2018','8:00','Reservado','Pneu Furado');



    
    insert into agendamento_servico(age_nome,age_telefone,age_modelo,age_placa,age_endereco,age_cep,age_servico,age_data,age_horario,age_disponivel,age_obs) 
values('','','','','','','troca de pneu','19/12/2018','12:30','Disponivel','');
*/
}
