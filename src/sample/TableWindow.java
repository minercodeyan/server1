package sample;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

import java.io.OutputStreamWriter;

import java.net.Socket;

public class TableWindow {

    @FXML

    TextField nameTextField;

    @FXML

    TextField authorNameTextField;

    @FXML

    TextField clientNameTextField;

    @FXML

    TextField isAlrightTextField;

    @FXML

    private TableView<Document> table;

    @FXML

    private TableColumn<Document, String> idColumn;

    @FXML

    private TableColumn<Document, String> nameColumn;

    @FXML

    private TableColumn<Document, String> authorNameColumn;

    @FXML

    private TableColumn<Document, String> clientNameColumn;
    @FXML
    private TableColumn<Document, String> isAlrightColumn;
    @FXML
    Button loadButton;
    public void addButtonClicked() {
        if (nameTextField.getText().isEmpty() || authorNameTextField.getText().isEmpty()
                || clientNameTextField.getText().isEmpty() || isAlrightTextField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Empty text field error");
            alert.setContentText("Fill all the text fields to continue");
            alert.showAndWait();

        } else {

            try {

               SocketClass.send("insert&" + nameTextField.getText() + "&" + authorNameTextField.getText() + "&" +
                        clientNameTextField.getText() + "&" + isAlrightTextField.getText());
                nameTextField.clear();
                authorNameTextField.clear();
                clientNameTextField.clear();
                isAlrightTextField.clear();
                loadButtonClicked();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void loadButtonClicked() throws IOException {
        initialize();
        table.setItems(getProfessionData(SocketClass.sendAndGet("show&").split("&&")));
        loadButton.setVisible(false);
    }

    private void initialize() {
        idColumn.setCellValueFactory(cell -> cell.getValue().idProperty());
        nameColumn.setCellValueFactory(cell -> cell.getValue().nameProperty());
        authorNameColumn.setCellValueFactory(cell -> cell.getValue().authorNameProperty());
        clientNameColumn.setCellValueFactory(cell -> cell.getValue().clientNameProperty());
        isAlrightColumn.setCellValueFactory(cell -> cell.getValue().isAlrightProperty());

    }

    private ObservableList<Document> getProfessionData(String[] data) {
        ObservableList<Document> list = FXCollections.observableArrayList();
        for (String datum : data) {
            String[] row = datum.split("&");
            Document d = new Document(Integer.parseInt(row[0]), row[1], row[2], row[3], row[4]);
            list.add(d);
        }
        return list;
    }

    public void deleteButtonClicked() {

        Document document = table.getSelectionModel().getSelectedItem();

        if (document != null) {

            try {

                String reply = SocketClass.sendAndGet("delete&" + document.getId() + "&");

                if (reply.equals("error")) {

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);

                    alert.setContentText("There is no record in database consisting the name you've entered, please " +

                            "try something else");

                    alert.showAndWait();

                } else {

                    loadButtonClicked();

                }

            } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Selection error");
                alert.setContentText("You have not selected the row");
                alert.showAndWait();

            }

        }

    public void updateButtonClicked() throws IOException {

        Document document = table.getSelectionModel().getSelectedItem();

        if (document != null) {

            if (nameTextField.getText().isEmpty()) {
                nameTextField.setText(String.valueOf(document.getName()));
                authorNameTextField.setText(String.valueOf(document.getAuthorName()));
                clientNameTextField.setText(String.valueOf(document.getClientName()));
                isAlrightTextField.setText(String.valueOf(document.getIsAlright()));

            } else {

                String updateString = "update&" + document.getId() + "&" + nameTextField.getText() + "&" +
                        authorNameTextField.getText() + "&" + clientNameTextField.getText() + "&" +
                        isAlrightTextField.getText() + "&";
                SocketClass.send(updateString);
                nameTextField.clear();
                authorNameTextField.clear();
                clientNameTextField.clear();
                isAlrightTextField.clear();
                loadButtonClicked();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Selection error");
            alert.setContentText("You have not selected the row");
            alert.showAndWait();

        }

    }

}