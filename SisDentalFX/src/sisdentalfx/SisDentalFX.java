package sisdentalfx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import sisdentalfx.db.util.Banco;

public class SisDentalFX extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ui/TelaPrincipal.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        
        if(Banco.conectar())
            launch(args);
        else
        {
            if(Banco.criarBD("sisdentaldb"))
                if(Banco.criarTabelas("sisdentaldb.txt", "sisdentaldb"))
                    JOptionPane.showMessageDialog(null, "Primeiro acesso ao sistema\nA base de dados foi criado\nÉ necessária a execução do sistema novamente");
                else
                    JOptionPane.showMessageDialog(null, "FATAL ERROR: Erro ao criar as tabelas");
            else
                JOptionPane.showMessageDialog(null, Banco.con.getMensagemErro());
            Platform.exit();
        }
    }

}
