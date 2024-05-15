import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.List;

public class TaskManagerPageController {
    private static Session session;

    @FXML
    TableView<Task> allTasksTable;

    @FXML
    TableColumn<Task, String> taskNameColumn;

    @FXML
    TableColumn<Task, String> taskDescriptionColumn;

    @FXML
    TableColumn<Task, String> taskClassColumn;

    @FXML
    TableColumn<Task, String> taskDueDateColumn;

    @FXML
    TableColumn<Task, Integer> taskPriorityColumn;

    ////////////////////////////////////////////////////////////

    @FXML
    TableView<Task> allClassesTable;

    @FXML
    TableColumn<ClassFor, String> classNameColumn;

    @FXML
    TableColumn<ClassFor, String> classCRNColumn;

    @FXML
    TableColumn<ClassFor, String> classProfessorColumn;

    @FXML
    TableColumn<ClassFor, String> classDatesColumn;

    @FXML
    TableColumn<ClassFor, Integer> classTimeColumn;

    ////////////////////////////////////////////////////////////

    @FXML
    TextField taskTitleField;

    @FXML
    TextArea descriptionField;

    @FXML
    SplitMenuButton classSelector;

    @FXML
    TextField priorityField;

    ////////////////////////////////////////////////////////////

    @FXML
    TextField classField;

    @FXML
    TextField crnField;

    @FXML
    TextField professorField;

    @FXML
    TextField meetingDaysField;

    @FXML
    TextField meetingTimesField;

    ////////////////////////////////////////////////////////////

    public void initialize(){
        populateClassSelection();
    }

    public void addTaskClicked(ActionEvent event){
        String title = taskTitleField.getText();
        String description = descriptionField.getText();
        String classFor = classSelector.getText();
        String priorityString = priorityField.getText();
        int priority = priorityString.isEmpty() ? 0 : Integer.parseInt(priorityString);
        String username = CurrentUser.username;

        Task newTask = new Task(username, title, description, priority, classFor);

        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(newTask);
            session.getTransaction().commit();
            allTasksTable.getItems().add(newTask);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        taskTitleField.clear();
        descriptionField.clear();
        classSelector.setText("Select Class");
        priorityField.clear();

    }

    public  void addClassClicked(ActionEvent event){

    }

    public void signOutButtonClicked(ActionEvent event){
        ViewSwitcher.switchTo(View.LOGIN);
    }

    private void populateClassSelection(){
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query<ClassFor> query = session.createQuery("from ClassFor", ClassFor.class);
            List<ClassFor> classes = query.getResultList();
            session.getTransaction().commit();

            for (ClassFor classFor : classes) {
                MenuItem menuItem = new MenuItem(classFor.getName());
                menuItem.setOnAction(e -> classSelector.setText(classFor.getName()));
                classSelector.getItems().add(menuItem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


}
