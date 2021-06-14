/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladoras;

import Entidades.Agendar;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import javafx.collections.ObservableList;

/**
 *
 * @author armando
 */
public class CtrAgendar 
{
    private String sql;

    //cod,Nome,Telefone,Modelo,Placa,endereço,cep,serviços,dia,horario,disponivel,obs
    public boolean Salvar(String nome, String telefone, String modelo, String placa, String endereco,String cep, String servicos,Date dia,String horario,String disponivel,String obs) throws SQLException
    {
        Agendar agendar = new Agendar(nome, telefone, modelo, placa, endereco, cep, servicos,dia,horario,disponivel,obs);
        return agendar.Salvar(sql);
    }

    public boolean Alterar(String cod, String nome, String telefone, String modelo, String placa, String endereco,String cep, String servicos,Date dia,String horario,String disponivel,String obs) throws SQLException
    {
        Agendar agendar = new Agendar(Integer.parseInt(cod),nome, telefone, modelo, placa, endereco, cep, servicos,dia,horario,disponivel,obs);
        return agendar.Alterar(sql);
    }

    public boolean Apagar(int cod)
    {
        Agendar agendar = new Agendar();
        agendar.setCodigo(cod);
        return agendar.Apagar(sql);
    }
    
    public ObservableList<String> BuscarServicos()
    {
        Agendar agenda = new Agendar();
        return agenda.BuscarServicos();
    }
    
    public ObservableList<Agendar> BuscarHorarios(String servico, LocalDate data)
    {
        Agendar agenda = new Agendar();
        return agenda.BuscarHorarios(servico,data);
    }

}
