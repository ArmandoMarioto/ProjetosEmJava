package sisdentalfx.db.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javafx.application.Platform;
import javafx.scene.control.TextArea;

public class Banco {

    static public Conexao con = null;

    public Banco() {
        //VAZIO
    }

    public static boolean conectar() {
        if (con == null) {
            con = new Conexao();
            return con.conectar("jdbc:postgresql://localhost:5432/", "sisdentaldb", "postgres", "postgres123");
        }
        return true;
    }

    public static boolean criarBD(String BD) {
        try {
            //Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost/";
            Connection con = DriverManager.getConnection(url, "postgres", "postgres123");

            Statement statement = con.createStatement();
            statement.execute("CREATE DATABASE " + BD + " WITH OWNER = postgres ENCODING = 'UTF8'  "
                    + "TABLESPACE = pg_default LC_COLLATE = 'Portuguese_Brazil.1252'  "
                    + "LC_CTYPE = 'Portuguese_Brazil.1252'  CONNECTION LIMIT = -1;");
            statement.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public static boolean criarTabelas(String script, String BD) {
        try {
            //Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost/" + BD;
            Connection con = DriverManager.getConnection(url, "postgres", "postgres123");

            Statement statement = con.createStatement();
            RandomAccessFile arq = new RandomAccessFile(script, "r");
            while (arq.getFilePointer() < arq.length()) {
                statement.addBatch(arq.readLine());
            }
            statement.executeBatch();

            statement.close();
            con.close();
        } catch (Exception e) {
            System.out.println("Erro ao criar tabela: " + e.getMessage());
            return false;
        }
        return true;
    }
    static public boolean realizaBackupRestauracao(String arqlote, TextArea ta) 
    {
        Runtime r = Runtime.getRuntime();
        try {
            Process p = r.exec("bkp\\"+arqlote);
            if (p != null) {
                InputStreamReader str = new InputStreamReader(p.getErrorStream());
                BufferedReader reader = new BufferedReader(str);
                String linha;
                while ((linha = reader.readLine()) != null) {
                    System.out.println(linha);
                    final String lin=linha;
                    Platform.runLater(()->{
                    ta.appendText(lin+"\n");});
                }
            }
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

}
