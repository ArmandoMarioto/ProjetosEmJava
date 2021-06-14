package sisdentalfx.db.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexao {

    static private Connection connect;
    private String erro;

    public Conexao() {
        erro = "";
        connect = null;
    }

    public boolean conectar(String local, String banco, String usuario, String senha) {
        boolean conectado = false;

        try {
            String url = local + banco;
            connect = DriverManager.getConnection(url, usuario, senha);
            conectado = true;
        } catch (SQLException sqlex) {
            erro = "Impossivel Conecatar com a Base do Banco de Dados: \n" + sqlex.toString();
        } catch (Exception ex) {
            erro = "Erro: \n" + ex.getMessage();
        }

        return conectado;
    }

    public String getMensagemErro() {
        return erro;
    }

    public boolean getEstadoConexao() {
        return (connect != null);
    }

    public boolean manipular(String sql) //inserir, alterar, excluir
    {
        boolean executou = false;

        try {
            Statement statement = connect.createStatement();
            int result = statement.executeUpdate(sql);
            statement.close();

            if (result >= 1) {
                executou = true;
            }
        } catch (SQLException sqlex) {
            erro = "Erro de manipulação de dados: \n" + sqlex.toString();
        }
        return executou;
    }

    public ResultSet consultar(String sql) {
        ResultSet rs = null;

        try {
            Statement statement = connect.createStatement();
            rs = statement.executeQuery(sql);
        } catch (SQLException sqlex) {
            erro = "Erro no Consultar: \n" + sqlex.toString();
        }

        return rs;
    }

    public int getMaxPK(String tabela, String chave) {
        String sql = "select max(" + chave + ") from " + tabela;

        int max = 0;

        ResultSet rs = consultar(sql);

        try {
            if (rs.next()) {
                max = rs.getInt(1);
            }
        } catch (SQLException sqlex) {
            erro = "Erro no getMaxPK: \n" + sqlex.toString();
            max = -1;
        }

        return max;
    }

    static public Connection getConnect() {
        return connect;
    }
}
