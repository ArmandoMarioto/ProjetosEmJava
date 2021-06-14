
package bedusystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Banco.Banco;
import javafx.scene.image.Image;

public class BeduSystem extends Application
{
    private static Stage stage;
    @Override
    public void start(Stage stage) throws Exception
    {
         this.stage = stage;
         String janela = null;
        if(!Banco.conectar())
            System.out.println(Banco.getCon().getMensagemErro());
        else
        {
            
            if(Controladoras.CtrParametrizacao.instancia().inicia())
            {
                janela = "/bedusystem/FXMLPrincipal.fxml";
            }
            else
            {
                janela = "/Interfaces/TelaParametrizacao.fxml";
            }
            //janela = "/bedusystem/FXMLPrincipal.fxml";
            Parent root = FXMLLoader.load(getClass().getResource(janela));
            Scene scene = new Scene(root);
            this.getStage().setScene(scene);

            stage.setScene(scene);
            stage.setTitle("BeduSystem");
            stage.setResizable(false);
            stage.getIcons().add(new Image("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTC0hTooIzutrVymMUpxmN50SWUEdkrN9LXtzCUGmYppaN0dJ46wA"));
            stage.show();
        }
    }

    public static void main(String[] args)
    {
        launch(args);
    }

    public static Stage getStage() {
        return stage;
    }
    
}
