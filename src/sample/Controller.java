package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    private TextArea textChat;

    @FXML
    private TextField textArea;

    public void ClickOnSend(ActionEvent actionEvent) {
        textChat.appendText(textArea.getText() + "\n");
    }
}
