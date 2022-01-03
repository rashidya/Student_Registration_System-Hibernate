package vaidation;

import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.TextField;

import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class Validation {
    public static Pattern personName=Pattern.compile("^([A-z\\s. ]{3,80})$");
    public static Pattern text=Pattern.compile("^([A-z0-9/,\\s]{3,})$");
    public static Pattern number = Pattern.compile("^([0-9]{1,})$");
    public static Pattern decimalNumber = Pattern.compile("^([0-9.]{3,})$");
    public static Pattern contactNo=Pattern.compile("^([0][0-9]{9}|[0][0-9]{2}[-\\s][0-9]{7})$");
    public static Pattern address=Pattern.compile("^([A-z0-9/,\\s]{3,})$");
    public static Pattern nic=Pattern.compile("^([0-9]{10}[X|V]|[0-9]{12})$");


    public static boolean validate(LinkedHashMap<JFXTextField, Pattern> map){

        for (TextField textField : map.keySet()) {
            Pattern pattern = map.get(textField);
            if (!pattern.matcher(textField.getText()).matches()){
                textField.setStyle("-fx-border-color: red;"+"-fx-border-width: 2;");
                return false;
            }else {
                textField.setStyle("-fx-border-color: white;"+"-fx-border-width: 2;");
            }
        }
        return true;
    }

}
