import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.hibernate.Session;
import org.hibernate.query.Query;


import java.sql.Time;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    TableView<ClassFor> allClassesTable;

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
        populateTaskTable();
        populateClassTable();
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
        String name = classField.getText();
        String crn = crnField.getText();
        String professor = professorField.getText();
        String meetingDaysString = meetingDaysField.getText();
        String meetingTimesString = meetingTimesField.getText();
        String username = CurrentUser.username;

        Date meetingDays = null;
        Time meetingTimes = null;
        try {
            meetingDays = new SimpleDateFormat("yyyy-MM-dd").parse(meetingDaysString); // Assuming the date is in yyyy-MM-dd format
            meetingTimes = Time.valueOf(meetingTimesString); // Assuming the time is in HH:mm:ss format
        } catch (ParseException | IllegalArgumentException e) {
            e.printStackTrace();
        }

        ClassFor newClass = new ClassFor(name, crn, professor, meetingDays, meetingTimes, username);

        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(newClass);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        classField.clear();
        crnField.clear();
        professorField.clear();
        meetingDaysField.clear();
        meetingTimesField.clear();

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

    private void populateTaskTable(){
        taskNameColumn.setCellValueFactory(new PropertyValueFactory<>("Title"));
        taskDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("Description"));
        taskClassColumn.setCellValueFactory(new PropertyValueFactory<>("ClassFor"));
        taskDueDateColumn.setCellValueFactory(new PropertyValueFactory<>("DueDate"));
        taskPriorityColumn.setCellValueFactory(new PropertyValueFactory<>("PriorityRating"));

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            session.beginTransaction();
            List<Task> tasks = session.createQuery("from Task", Task.class).getResultList();
            session.getTransaction().commit();

            ObservableList<Task> taskList = FXCollections.observableArrayList(tasks);
            allTasksTable.setItems(taskList);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    private void populateClassTable(){
        classNameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        classCRNColumn.setCellValueFactory(new PropertyValueFactory<>("CRN"));
        classProfessorColumn.setCellValueFactory(new PropertyValueFactory<>("Professor"));
        classDatesColumn.setCellValueFactory(new PropertyValueFactory<>("MeetingDays"));
        classTimeColumn.setCellValueFactory(new PropertyValueFactory<>("MeetingTimes"));

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            List<ClassFor> classes = session.createQuery("from ClassFor", ClassFor.class).getResultList();
            session.getTransaction().commit();

            ObservableList<ClassFor> classList = FXCollections.observableArrayList(classes);
            allClassesTable.setItems(classList);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    private void completeTaskButtonClicked(ActionEvent event){
        Task selectedTask = allTasksTable.getSelectionModel().getSelectedItem();

        if (selectedTask == null) {
            return;
        }

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(selectedTask);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        allTasksTable.getItems().remove(selectedTask);
    }

    private void deleteClassButtonClicked(ActionEvent event){
        ClassFor selectedClass = allClassesTable.getSelectionModel().getSelectedItem();

        if (selectedClass == null) {
            return;
        }

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(selectedClass);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        allClassesTable.getItems().remove(selectedClass);
    }


}
