package sisdentalfx.db.controles;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import sisdentalfx.db.entidades.Agenda;
import sisdentalfx.db.entidades.Consulta;
import sisdentalfx.db.entidades.Dentista;
import sisdentalfx.db.entidades.Horarios;
import sisdentalfx.db.entidades.Material;
import sisdentalfx.db.entidades.Paciente;
import sisdentalfx.db.util.Banco;
import sisdentalfx.db.util.Conexao;

public class CtrAgenda {

    private String sql;

    public boolean salvarAgendamento(Agenda a, Horarios h) {
        sql = "insert into consulta(con_data, con_horario, con_obs, con_status, den_codigo, pac_codigo) "
                + "values('$1', '$2', '$3', '$4', '$5', '$6')";

        sql = sql.replace("$1", "" + h.getData());
        sql = sql.replace("$2", h.getHora());
        sql = sql.replace("$3", "");
        sql = sql.replace("$4", "" + 0);
        sql = sql.replace("$5", "" + a.getDentista().getCodigo());
        sql = sql.replace("$6", "" + h.getPaciente().getCodigo());

        return Banco.con.manipular(sql);
    }

    public boolean apagarAgendamento(Horarios h) {
        sql = "delete from consulta where con_codigo = " + h.getCodigo();

        return Banco.con.manipular(sql);
    }

    public Agenda getAgendaDia(Dentista dent, LocalDate data) {
        Agenda a = new Agenda();
        ArrayList<Horarios> HorariosList = new ArrayList();

        try {
            HorariosList = getHorarios(dent, data);
            a = new Agenda(dent, HorariosList);
        } catch (Exception e) {
            System.out.println("ERRO NO getAgenda: " + e.getMessage());
        }
        return a;
    }
    
    public ArrayList<Horarios> getHorarios(Dentista dent, LocalDate data) {
        CtrPessoa ctrP = new CtrPessoa();
        Consulta c;
        Paciente auxPac = new Paciente();
        ArrayList<Horarios> horariosList = new ArrayList();

        sql = "select * from consulta where con_data = '" + data + "' and den_codigo = " + dent.getCodigo();

        ResultSet rs = Banco.con.consultar(sql);

        try {
            while (rs.next()) {
                CharSequence date = "" + rs.getDate("con_data");

                Paciente newPac = new Paciente();
                newPac = (Paciente) ctrP.getPessoa(rs.getInt("pac_codigo"), auxPac);
                horariosList.add(new Horarios(rs.getInt("con_codigo"), LocalDate.parse(date), rs.getString("con_horario"), newPac, c =  new Consulta(rs.getString("con_obs"), rs.getBoolean("con_status"))));
            }
        } catch (Exception e) {
            System.out.println("ERRO EM getHorarios: " + e.getMessage());
        }
        
        return horariosList;
    }
    
    public void SalvarConMat(Horarios h) throws SQLException {
        boolean achou = false;
        boolean updateF = true;
        boolean insertF = true;
        Connection conn  = Conexao.getConnect();
        
        try {
            conn.setAutoCommit(false);
            for (int i = 0; insertF && updateF && i < h.getConsulta().getMateriais().size(); i++) {

                achou = false;
                //pesquisado se já existe o material
                sql = "select * from con_mat where con_codigo = " + h.getCodigo() + " and mat_codigo = " + h.getConsulta().getMateriais().get(i).getMaterial().getCodigo();
  
                ResultSet rs = Banco.con.consultar(sql);

                try {
                    if(rs.next())
                        achou =  true;
                } catch (Exception e){
                    System.out.println("ERRO AO PESQUISAR o con_mat: " + e.getMessage());
                }

                //Se ja existe só upadate
                if(h.getConsulta().isStatus() && achou){ 
                    sql = "update con_mat set cm_qtd = '$1' where con_codigo = " + h.getCodigo() + " and mat_codigo = " + h.getConsulta().getMateriais().get(i).getMaterial().getCodigo();

                    sql = sql.replace("$1","" + h.getConsulta().getMateriais().get(i).getQtd());
                   
                    updateF = Banco.con.manipular(sql);
                }
                else{
                    sql = "insert into con_mat(con_codigo, mat_codigo, cm_qtd) values('$1', '$2', '$3')";

                    sql = sql.replace("$1", "" + h.getCodigo());
                    sql = sql.replace("$2", "" + h.getConsulta().getMateriais().get(i).getMaterial().getCodigo());
                    sql = sql.replace("$3","" + h.getConsulta().getMateriais().get(i).getQtd());
                    
                    insertF = Banco.con.manipular(sql);
                }
            }
            if(insertF && updateF)
                conn.commit();
            else
                conn.rollback();
            
         } catch (Exception e) {
            conn.rollback();
            System.out.println("ERRO NO setAutoCommit: " + e.getMessage());
        }
        finally{
            conn.setAutoCommit(true);
        }
    }
    
    public ArrayList<Consulta.itensMat> getItensMat(Horarios h) {
        CtrMaterial ctrM = new CtrMaterial();
        
        sql = "select * from con_mat where con_codigo = " + h.getCodigo();
        
        ResultSet rs = Banco.con.consultar(sql);
        
        try {
            while(rs.next())
                h.getConsulta().addMaterial(ctrM.getMaterial(rs.getInt("mat_codigo")), rs.getInt("cm_qtd"));
                
        } catch (Exception e) {
            System.out.println("ERRO getItensMat: " + e.getMessage());
        }
        return h.getConsulta().getMateriais();
    }
    
    public boolean apagarConMat(Horarios h, Consulta.itensMat iten){
        sql = "delete from con_mat where con_codigo = " + h.getCodigo() + " and mat_codigo = " + iten.getMaterial().getCodigo();
        
        return Banco.con.manipular(sql);
    }
   
    public void SalvarConPro(Horarios h) throws SQLException {
        boolean achou = false;
        boolean updateF = true;
        boolean insertF = true;
        Connection conn  = Conexao.getConnect();
        
        try {
            conn.setAutoCommit(false);
            for (int i = 0; insertF && updateF && i < h.getConsulta().getProcedimentos().size(); i++) {

                achou = false;
                //pesquisado se já existe o material
                sql = "select * from con_pro where con_codigo = " + h.getCodigo() + " and pro_codigo = " + h.getConsulta().getProcedimentos().get(i).getProcedimentos().getCodigo();
  
                ResultSet rs = Banco.con.consultar(sql);

                try {
                    if(rs.next())
                        achou =  true;
                } catch (Exception e){
                    System.out.println("ERRO AO PESQUISAR o con_pro: " + e.getMessage());
                }

                //Se ja existe só upadate
                if(h.getConsulta().isStatus() && achou){ 
                     sql = "update con_pro set cp_qtd = '$1' where con_codigo = " + h.getCodigo() + " and pro_codigo = " + h.getConsulta().getProcedimentos().get(i).getProcedimentos().getCodigo();

                    sql = sql.replace("$1","" + h.getConsulta().getProcedimentos().get(i).getQtd());
                   
                    updateF = Banco.con.manipular(sql);
                }
                else{
                    sql = "insert into con_pro(con_codigo, pro_codigo, cp_qtd) values('$1', '$2', '$3')";

                    sql = sql.replace("$1", "" + h.getCodigo());
                    sql = sql.replace("$2", "" + h.getConsulta().getProcedimentos().get(i).getProcedimentos().getCodigo());
                    sql = sql.replace("$3","" + h.getConsulta().getProcedimentos().get(i).getQtd());
                    
                    insertF = Banco.con.manipular(sql);
                }
            }
            if(insertF && updateF)
                conn.commit();
            else
                conn.rollback();
            
         } catch (Exception e) {
            conn.rollback();
            System.out.println("ERRO NO setAutoCommit do SalvarConPro: " + e.getMessage());
        }
        finally{
            conn.setAutoCommit(true);
        }
    }
    
    public ArrayList<Consulta.itensProc> getItensPro(Horarios h) {
        CtrProcedimentos ctrP = new CtrProcedimentos();
        
        sql = "select * from con_pro where con_codigo = " + h.getCodigo();
        
        ResultSet rs = Banco.con.consultar(sql);
        
        try {
            while(rs.next())
                h.getConsulta().addProcedimentos(ctrP.getProcedimentos(rs.getInt("pro_codigo")), rs.getInt("cp_qtd"));
                
        } catch (Exception e) {
            System.out.println("ERRO getItensPro: " + e.getMessage());
        }
        return h.getConsulta().getProcedimentos();
    }
    
     public boolean apagarConPro(Horarios h, Consulta.itensProc iten){
        sql = "delete from con_pro where con_codigo = " + h.getCodigo() + " and pro_codigo = " + iten.getProcedimentos().getCodigo();
        
        return Banco.con.manipular(sql);
    }
    
    public boolean salvarConsulta(Horarios h) {
        sql = "update consulta set con_obs = '$1', con_status = '$2' where con_codigo = " + h.getCodigo();
        
        sql = sql.replace("$1", h.getConsulta().getObs());
        sql = sql.replace("$2", "" + 1);
        
        return Banco.con.manipular(sql);
    }
    
    public Horarios buscaConsulta(Horarios h){
        CtrPessoa ctrP =  new CtrPessoa();
        Paciente auxPac =  new Paciente();
        Horarios newHor = null;
        Consulta newCon = null;
        
        sql = "select * from consulta where con_codigo = " + h.getCodigo();
        ResultSet rs =  Banco.con.consultar(sql);
        
        try {
            if(rs.next())
            {
                //pegando obs e status de consulta
                newCon = new Consulta(rs.getString("con_obs"), rs.getBoolean("con_status"));
              
                //pegando dados do agendamento
                CharSequence date = "" + rs.getDate("con_data");
                newHor = new Horarios(rs.getInt("con_codigo"), LocalDate.parse(date), rs.getString("con_horario"), (Paciente)ctrP.getPessoa(rs.getInt("pac_codigo"), auxPac), newCon);
            }
        } catch (Exception e) {
            System.out.println("ERRO NO buscConsulta");
        }
        return newHor;
    }
}
