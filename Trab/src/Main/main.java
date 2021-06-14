package Main;

import Controladoras.ctrSystem;
import JDBC.Banco;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class main extends Application
{    
    @Override
    public void start(Stage stage) throws Exception
    {
        Parent root;
        FXMLDocumentController.STAGE = stage;
        
        if(new ctrSystem().CheckSystem())
            root = FXMLLoader.load(getClass().getResource("/FX/FXMLSystem.fxml"));
        else
            root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args)
    {
        if(Banco.conectar())
            launch(args);   
    }
}
