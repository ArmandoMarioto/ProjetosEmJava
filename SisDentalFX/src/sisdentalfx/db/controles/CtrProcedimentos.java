package sisdentalfx.db.controles;

import java.sql.ResultSet;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import sisdentalfx.db.entidades.Procedimentos;
import sisdentalfx.db.util.Banco;

public class CtrProcedimentos {

    private String sql = "";

    public boolean salvar(Procedimentos p) {
        sql = "insert into procedimentos(pro_descricao, pro_preco, pro_tempo) values('$1', $2, $3)";

        sql = sql.replace("$1", p.getDescricao());
        sql = sql.replace("$2", "" + p.getPreco());
        sql = sql.replace("$3", "" + p.getTempo());

        return Banco.con.manipular(sql);
    }

    public boolean alterar(Procedimentos p) {
        sql = "update procedimentos set pro_descricao = '$1', pro_preco = $2, pro_tempo = $3 where pro_codigo = " + p.getCodigo();

        sql = sql.replace("$1", p.getDescricao());
        sql = sql.replace("$2", "" + p.getPreco());
        sql = sql.replace("$3", "" + p.getTempo());

        return Banco.con.manipular(sql);
    }

    public boolean apagar(Procedimentos p) {
        sql = "delete from procedimentos where pro_codigo = " + p.getCodigo();

        return Banco.con.manipular(sql);
    }

    public Procedimentos getProcedimentos(int codigo) {
        Procedimentos p = null;

        sql = "select * from procedimentos where pro_codigo = " + codigo;
        ResultSet rs = Banco.con.consultar(sql);

        try {
            if (rs.next()) {
                p = new Procedimentos(rs.getInt("pro_codigo"), rs.getString("pro_descricao"), rs.getDouble("pro_preco"), rs.getInt("pro_tempo"));
            }
        } catch (Exception e) {
            //
        }

        return p;
    }

    public ArrayList<Procedimentos> getProcedimentos(String filtro) {
        ArrayList<Procedimentos> lista = new ArrayList();

        sql = "select * from procedimentos";

        if (!filtro.isEmpty()) {
            sql += " where " + filtro;
        }

        ResultSet rs = Banco.con.consultar(sql);

        try {
            while (rs.next()) {
                lista.add(new Procedimentos(rs.getInt("pro_codigo"), rs.getString("pro_descricao"), rs.getDouble("pro_preco"), rs.getInt("pro_tempo")));
            }

        } catch (Exception e) {
            //
        }

        return lista;
    }
}
