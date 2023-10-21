package maman13_1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ExamApplication extends Application {
    // The main class of the exam application.

    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Exam.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("maman13_1.Exam");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
