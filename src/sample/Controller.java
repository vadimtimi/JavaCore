package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import javax.management.loading.MLetContent;

public class Controller {
    @FXML
    private TextArea textChat;

    @FXML
    private TextField textArea;

    private void sendMessage() {
        if(!textArea.getText().isEmpty()) {
            textChat.appendText(textArea.getText() + "\n");
            textArea.setText("");
        }else {
            String toastMsg = "Нет данных для отправки...";
            int toastMsgTime = 3500; //3.5 seconds
            int fadeInTime = 500; //0.5 seconds
            int fadeOutTime= 500; //0.5 seconds
            Stage stage = (Stage)textArea.getScene().getWindow();
            Toast.makeText(stage, toastMsg, toastMsgTime, fadeInTime, fadeOutTime);
        }
    }

    public void ClickOnSend(ActionEvent actionEvent) {
        sendMessage();
    }

    public void handleEnterPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            // Нажата клашиша ENTER
            sendMessage();
        }
    }
}
