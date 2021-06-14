package sisdentalfx.db.controles;

import java.sql.ResultSet;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import sisdentalfx.db.entidades.UF;
import sisdentalfx.db.util.Banco;

public class CtrUf {

    private String sql = "";

    public boolean salvar(UF u) {
        sql = "insert into estado(est_sgl, est_nome, pais_cod) values('$1', '$2', 1)";

        sql = sql.replace("$1", u.getSigla());
        sql = sql.replace("$2", u.getNome());

        return Banco.con.manipular(sql);
    }

    public boolean alterar(UF u) {
        sql = "update estado set est_sgl = '$1', est_nome = '$2', pais_cod = $3 where est_cod = " + u.getCodigo();

        sql = sql.replace("$1", u.getSigla());
        sql = sql.replace("$2", u.getNome());
        sql = sql.replace("$3", "" + 1);

        return Banco.con.manipular(sql);
    }

    public boolean apagar(UF u) {
        sql = "delete from estado where est_codigo = " + u.getCodigo();

        return Banco.con.manipular(sql);
    }

    public UF getUF(int codigo) {
        UF uf = null;
        sql = "select * from estado where est_cod = " + codigo;
        ResultSet rs = Banco.con.consultar(sql);

        try {
            if (rs.next()) {
                uf = new UF(rs.getInt("est_cod"), rs.getString("est_sgl"), rs.getString("est_nome"));
            }
        } catch (Exception e) {
            //
        }

        return uf;
    }

    public UF getUF_Slg(String slg) {
        UF uf = null;

        sql = "select * from estado where est_sgl = '$1'";
        sql = sql.replace("$1", slg);

        ResultSet rs = Banco.con.consultar(sql);

        try {
            if (rs.next()) {
                uf = new UF(rs.getInt("est_cod"), rs.getString("est_sgl"), rs.getString("est_nome"));
            }
        } catch (Exception e) {
            //
        }

        return uf;
    }

    public ArrayList<UF> getUF(String filtro) {
        ArrayList<UF> lista = new ArrayList();

        sql = "select * from estado";

        if (!filtro.isEmpty()) {
            sql += " where " + filtro;
        }

        ResultSet rs = Banco.con.consultar(sql);

        try {
            while (rs.next()) {
                lista.add(new UF(rs.getInt("est_cod"), rs.getString("est_sgl"), rs.getString("est_nome")));
            }
        } catch (Exception e) {
            //
        }
        return lista;
    }
}
