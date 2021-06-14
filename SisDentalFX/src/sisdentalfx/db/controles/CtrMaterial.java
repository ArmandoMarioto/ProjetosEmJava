package sisdentalfx.db.controles;

import java.sql.ResultSet;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import sisdentalfx.db.entidades.Material;
import sisdentalfx.db.util.Banco;

public class CtrMaterial {

    private String sql;

    public boolean salvar(Material m) {
        sql = "insert into material(mat_descricao, mat_preco) values('$1', $2)";

        sql = sql.replace("$1", m.getDescricao());
        sql = sql.replace("$2", "" + m.getPreco());

        return Banco.con.manipular(sql);
    }

    public boolean alterar(Material m) {
        sql = "update material set mat_descricao = '$1', mat_preco = $2 where mat_codigo = " + m.getCodigo();

        sql = sql.replace("$1", m.getDescricao());
        sql = sql.replace("$2", "" + m.getPreco());

        return Banco.con.manipular(sql);
    }

    public boolean apagar(Material m) {
        sql = "delete from material where mat_codigo = " + m.getCodigo();

        return Banco.con.manipular(sql);
    }

    public Material getMaterial(int codigo) {
        Material m = null;

        sql = "select * from material where mat_codigo = " + codigo;
        ResultSet rs = Banco.con.consultar(sql);

        try {
            if (rs.next()) {
                m = new Material(rs.getInt("mat_codigo"), rs.getString("mat_descricao"), rs.getDouble("mat_preco"));
            }
        } catch (Exception e) {
            //
        }

        return m;
    }

    public ArrayList<Material> getMaterial(String filtro) {
        ArrayList<Material> lista = new ArrayList();

        sql = "select * from material";

        if (!filtro.isEmpty()) {
            sql += " where " + filtro;
        }
        ResultSet rs = Banco.con.consultar(sql);

        try {
            while (rs.next()) {
                lista.add(new Material(rs.getInt("mat_codigo"), rs.getString("mat_descricao"), rs.getDouble("mat_preco")));
            }

        } catch (Exception e) {
            //
        }

        return lista;
    }
}
