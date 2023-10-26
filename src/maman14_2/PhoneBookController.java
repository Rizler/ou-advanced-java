package maman14_2;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.UnaryOperator;

public class PhoneBookController {
    @FXML
    private TextField searchField;

    @FXML
    private TextArea phoneBookText;

    private PhoneBook phoneBook = new PhoneBook();

    @FXML
    void initialize() {
        // Display the phone book entries whenever the search field changes
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            displayPhoneBookEntries();
        });
    }

    @FXML
    void addPressed(ActionEvent event) {
        // Create a dialog to get the name and phone number, then add them to the phone book
        Optional<Pair<String, String>> result = showAddEntryDialog();

        result.ifPresent(nameNumber -> {
            phoneBook.addEntry(nameNumber.getKey(), nameNumber.getValue());
            displayPhoneBookEntries();
        });
    }

    @FXML
    void loadPressed(ActionEvent event) {
        // Load a phone book from a file
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("."));
        fileChooser.setTitle("Open Phone Book File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Phone Books", "*.phonebook"));
        File file = fileChooser.showOpenDialog(getStage());
        if (file == null) {
            return;
        }
        try {
            FileInputStream fileOutStream = new FileInputStream(file);
            ObjectInputStream objOutStream = new ObjectInputStream(fileOutStream);
            try {
                phoneBook = (PhoneBook) objOutStream.readObject();
            } catch (ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, "Bad file format").showAndWait();
            }
            objOutStream.close();
            fileOutStream.close();
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Error loading file: " + e).showAndWait();
        }

        displayPhoneBookEntries();
    }

    @FXML
    void savePressed(ActionEvent event) {
        // Save the phone book to a file
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("."));
        fileChooser.setTitle("Save Phone Book File");
        fileChooser.setInitialFileName("mybook.phonebook");
        File file = fileChooser.showSaveDialog(getStage());
        if (file == null) {
            return;
        }
        try {
            FileOutputStream fileOutStream = new FileOutputStream(file);
            ObjectOutputStream objOutStream = new ObjectOutputStream(fileOutStream);
            objOutStream.writeObject(phoneBook);
            objOutStream.close();
            fileOutStream.close();
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Error saving file: " + e).showAndWait();
        }
    }

    private Stage getStage() {
        return (Stage) searchField.getScene().getWindow();
    }

    private void displayPhoneBookEntries() {
        // Display the phone book entries in the text area. Use the search field to filter the entries.
        ArrayList<PhoneBookEntry> entries = phoneBook.getEntries();
        String searchName = searchField.getText();
        if (!searchName.isEmpty()) {
            entries = phoneBook.search(searchName);
        }

        StringBuilder sb = new StringBuilder();
        for (PhoneBookEntry entry : entries) {
            sb.append(entry.toString()).append("\n");
        }
        phoneBookText.setText(sb.toString());
    }

    private static Optional<Pair<String, String>> showAddEntryDialog() {
        // Create a custom dialog for adding an entry to the phone book
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Add entry");
        dialog.setHeaderText("Add an entry to the phone book");

        // Set the button types.
        ButtonType addButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

        // Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField nameField = new TextField();
        nameField.setPromptText("Name");
        TextField phoneNumberField = new TextField();
        phoneNumberField.setPromptText("Phone Number");

        grid.add(new Label("Name:"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("Phone number:"), 0, 1);
        grid.add(phoneNumberField, 1, 1);

        // Enable/Disable login button depending on whether a username was entered.
        Node addButton = dialog.getDialogPane().lookupButton(addButtonType);
        addButton.setDisable(true);


        // Only allow numbers in the phone number field.
        UnaryOperator<TextFormatter.Change> integerFilter = change -> {
            String input = change.getText();
            if (input.matches("[0-9]*")) {
                return change;
            }
            return null;
        };
        phoneNumberField.setTextFormatter(new TextFormatter<String>(integerFilter));

        // Only enable add button if both fields are filled.
        nameField.textProperty().addListener((observable, oldValue, newValue) -> {
            addButton.setDisable(newValue.trim().isEmpty() || phoneNumberField.getText().trim().isEmpty());
        });
        phoneNumberField.textProperty().addListener((observable, oldValue, newValue) -> {
            addButton.setDisable(newValue.trim().isEmpty() || phoneNumberField.getText().trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

        // Request focus on the name field by default.
        Platform.runLater(nameField::requestFocus);

        // Convert the result to a name-phone number-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                return new Pair<>(nameField.getText(), phoneNumberField.getText());
            }
            return null;
        });

        return dialog.showAndWait();
    }

}
