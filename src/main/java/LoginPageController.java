import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class LoginPageController {
    private static Session session;

    @FXML
    Button registerButton;

    @FXML
    Button loginButton;

    @FXML
    TextField signupEmailField;

    @FXML
    TextField signupUsernameField;

    @FXML
    TextField signupPasswordField;

    @FXML
    TextField signupFirstNameField;

    @FXML
    TextField signupLastNameField;

    @FXML
    Label messageLabel;

    public void loginButtonClicked(ActionEvent event) {
        String username = signupUsernameField.getText();
        String password = signupPasswordField.getText();

        if(username.isEmpty() || password.isEmpty()) {
            messageLabel.setText("All fields must be populated");
        } else if(userExists(username, password)){
            switchToTasks();
        } else {
            messageLabel.setText("Username or password is incorrect");
        }
    }

    public void registerButtonClicked(ActionEvent event) {
        String email = signupEmailField.getText();
        String username = signupUsernameField.getText();
        String password = signupPasswordField.getText();
        String firstName = signupFirstNameField.getText();
        String lastName = signupLastNameField.getText();

        if(signupEmailField.getText().isEmpty() || signupUsernameField.getText().isEmpty() || signupPasswordField.getText().isEmpty() ||
                firstName.isEmpty() || lastName.isEmpty()) {
            messageLabel.setText("All fields must be populated");
        } else if(usernameExists(username)) {
            messageLabel.setText("Username already exists");
        } else if(emailExists(email)) {
            messageLabel.setText("Email already exists");
        } else {
            try {
                session = HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();

                User newUser = new User(email, username, password, firstName, lastName);

                session.persist(newUser);
                session.getTransaction().commit();

            } catch (Exception e) {
                System.out.println(e.getMessage());
                messageLabel.setText("Error occurred during sign-up. Please try again.");
            } finally {
                if (session != null && session.isOpen()) {
                    session.close();
                }
            }
        }
    }

    private boolean emailExists(String email) {
        return false;
    }

    private boolean usernameExists(String username){
        return false;
    }

    private boolean userExists(String username, String password) {
        return false;
    }

    public void switchToTasks() {
        ViewSwitcher.switchTo(View.TASK_MANAGER);
    }

    public void exitButtonClicker(ActionEvent event) {
        Platform.exit();
    }

}
