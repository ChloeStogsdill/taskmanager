import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.hibernate.Session;

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

    public void addTaskClicked(ActionEvent event){
        String title = taskTitleField.getText();
        String description = descriptionField.getText();

    }

    public  void addClassClicked(ActionEvent event){

    }

    public void signOutButtonClicked(ActionEvent event){
        ViewSwitcher.switchTo(View.LOGIN);
    }


}
