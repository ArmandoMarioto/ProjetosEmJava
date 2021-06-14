
package Principal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Principal extends Application 
{
    
   @Override
    public void start(Stage stage) throws Exception {
        Parent root;
       root = FXMLLoader.load(getClass().getResource("/Interfaces/TelaParametrizacao.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    public static void main(String[] args) {
        if(Banco.BD.conectar())
        launch(args);
        else
            System.out.println("Deu ruim");
    }
    
}
