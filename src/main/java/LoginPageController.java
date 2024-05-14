import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;

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
    TextField loginUsernameField;

    @FXML
    TextField loginPasswordField;

    @FXML
    Label messageLabel;

    public void loginButtonClicked(ActionEvent event) {
        String username = loginUsernameField.getText();
        String password = loginPasswordField.getText();

        if(username.isEmpty() || password.isEmpty()) {
            messageLabel.setText("All fields must be populated");
        } else if(userExists(username, password)){
            CurrentUser.username = username;
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

                signupEmailField.clear();
                signupFirstNameField.clear();
                signupLastNameField.clear();
                signupPasswordField.clear();
                signupUsernameField.clear();

                messageLabel.setText("Sign-up successful");

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
        boolean emailExists = false;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            String hql = "From User WHERE Email = :email";
            TypedQuery<User> query = session.createQuery(hql, User.class);
            query.setParameter("email", email);
            User user = query.getSingleResult();
            emailExists = user!=null;
        } catch (Exception e) {
            messageLabel.setText("Error occurred during log-in. Please try again.");
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return emailExists;
    }

    private boolean usernameExists(String username){
        boolean usernameExists = false;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            String hql = "From User WHERE Username = :username";
            TypedQuery<User> query = session.createQuery(hql, User.class);
            query.setParameter("username", username);
            User user = query.getSingleResult();
            usernameExists = user!=null;
        } catch (Exception e) {
            messageLabel.setText("Error occurred during log-in. Please try again.");
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return usernameExists;
    }

    private boolean userExists(String username, String password) {
        boolean userExists = false;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            String hql = "From User WHERE Username = :username AND Password = :password";
            TypedQuery<User> query = session.createQuery(hql, User.class);
            query.setParameter("username", username);
            query.setParameter("password", password);
            User user = query.getSingleResult();
            userExists = user!=null;
        } catch (Exception e) {
            messageLabel.setText("Error occurred during log-in. Please try again.");
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return userExists;
    }

    public void switchToTasks() {
        ViewSwitcher.switchTo(View.TASK_MANAGER);
    }

    public void exitButtonClicked(ActionEvent event) {
        Platform.exit();
    }

}
