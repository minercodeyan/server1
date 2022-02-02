package sample;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;

public class Controller {
    @FXML
    TextField ipTextField;
    @FXML
    TextField portTextField;
    @FXML
    Button connectButton;
@FXML
void initialize() {
    connectButton.setOnAction(event -> {
        try {
            SocketClass.createSocket(ipTextField.getText(), Integer.parseInt(portTextField.getText()));
            if (SocketClass.socket.isConnected()) {
                connectButton.getScene().getWindow().hide();
                openWindow("table");
            }

        } catch (Exception e) {

            System.out.println(e.toString());

        }
    }  );
    }

    private void openWindow(String fileName) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource(String.format("%s.fxml", fileName)));

        AnchorPane page = loader.load();

        Stage dialogStage = new Stage();

        dialogStage.setTitle(fileName);

        Scene scene = new Scene(page);

        dialogStage.setScene(scene);

        dialogStage.show();

        dialogStage.setOnCloseRequest(event -> {

            Stage s = (Stage) connectButton.getScene().getWindow();

            try {

               SocketClass.send("exit&");

                SocketClass.socket.close();

                SocketClass.out.close();

                SocketClass.in.close();

            } catch (IOException e) {

                e.printStackTrace();

            }

            s.close();

        });

    }

}