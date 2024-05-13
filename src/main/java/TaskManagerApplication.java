import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class TaskManagerApplication extends Application {
    public static void main(String[] args) {launch(args);}

    public void start(Stage stage){
        var scene = new Scene(new Pane());
        ViewSwitcher.setScene(scene);
        ViewSwitcher.switchTo(View.LOGIN);
        stage.setScene(scene);
        stage.show();
    }
}
