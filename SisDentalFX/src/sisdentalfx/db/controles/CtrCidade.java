package sisdentalfx.db.controles;

import java.sql.ResultSet;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import sisdentalfx.db.entidades.Cidade;
import sisdentalfx.db.entidades.UF;
import sisdentalfx.db.util.Banco;

public class CtrCidade {

    private String sql = "";

    public boolean salvar(Cidade c) {
        sql = "insert into cidade(cid_nome, est_cod) values('$1', '$2')";

        sql = sql.replace("$1", c.getNome());
        sql = sql.replace("$2", "" + c.getUf());

        return Banco.con.manipular(sql);
    }

    public boolean alterar(Cidade c) {
        sql = "update cidade set cid_nome = '$1', est_cod = '$2' where cid_cod = " + c.getCodigo();

        sql = sql.replace("$1", c.getNome());
        sql = sql.replace("$2", "" + c.getUf());

        return Banco.con.manipular(sql);
    }

    public boolean apagar(Cidade c) {
        sql = "delete from cidade where cid_cod = " + c.getCodigo();

        return Banco.con.manipular(sql);
    }

    public Cidade getCidade(int codigo) {
        Cidade c = null;
        CtrUf ctrU = new CtrUf();

        sql = "select * from cidade where cid_cod = " + codigo;
        ResultSet rs = Banco.con.consultar(sql);

        try {
            if (rs.next()) {
                c = new Cidade(rs.getInt("cid_cod"), rs.getString("cid_nome"), ctrU.getUF(rs.getInt("est_cod")));
            }
        } catch (Exception e) {
            //
        }

        return c;
    }

    public Cidade getCididade_name(String nome, UF uf) {
        Cidade c = null;
        CtrUf ctrU = new CtrUf();

        sql = "select * from cidade where cid_nome = '$1' and est_cod = '$2'";
        sql = sql.replace("$1", nome);
        sql = sql.replace("$2", "" + uf.getCodigo());
        ResultSet rs = Banco.con.consultar(sql);

        try {
            if (rs.next()) {
                c = new Cidade(rs.getInt("cid_cod"), rs.getString("cid_nome"), uf);
            }
        } catch (Exception e) {
            //
        }

        return c;
    }

    public ArrayList<Cidade> getCidades(int filtro) {
        CtrUf ctrU = new CtrUf();
        ArrayList<Cidade> lista = new ArrayList();

        sql = "select * from cidade where est_cod = " + filtro;

        ResultSet rs = Banco.con.consultar(sql);

        try {
            while (rs.next()) {
                lista.add(new Cidade(rs.getInt("cid_cod"), rs.getString("cid_nome"), ctrU.getUF(rs.getInt("est_cod"))));
            }
        } catch (Exception e) {
            //
        }
        return lista;
    }
}
