package Main;

import Banco.Banco;
import Controladoras.CtrParametrizacao;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class Main extends Application
{

    private static Stage stage;

    @Override
    public void start(Stage stage) throws Exception
    {
        this.stage = stage;
        String janela = null;
        if (Banco.conectar())
        {
            //if(new CtrParametrizacao().inicia())
            {
                janela = "/Interfaces/TelaPrincipal.fxml";
            }
            /*else
            {
                janela = "/Interfaces/TelaParametrizacao.fxml";
            }*/
            Parent root = FXMLLoader.load(getClass().getResource(janela));
            Scene scene = new Scene(root);
            this.stage.setScene(scene);
            if (Controladoras.CtrParametrizacao.instancia().inicia())
            {
                stage.setMaximized(true);
            }

            scene.getStylesheets().add("/estilo/estilo1.css");
            stage.show();

        } else
        {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText(Banco.getConexao().getMensagemErro());
            a.showAndWait();
            System.exit(0);
        }

    }

    public static void main(String[] args)
    {
        launch(args);
    }

    public static Stage getStage()
    {
        return stage;
    }

}
