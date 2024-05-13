import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.hibernate.Session;

public class LoginPageController {
    private static Session session;

    @FXML
    Button registerButton;

    @FXML
    Button loginButton;

    public void loginButtonClicked(ActionEvent event) {

    }

    public void registerButtonClicked(ActionEvent event) {

    }

    private void addUser(String username, String password){

    }

    private boolean userExists(String username){
        return false;
    }

    public void switchToTasks(ActionEvent event) {

    }

    public void exitButtonClicker(ActionEvent event) {
        Platform.exit();
    }

}
