module com.example.JoshuaBurt_COMP1011Sec005_Lab02 {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens joshuaburt_sec005_ex03 to javafx.fxml;
    exports joshuaburt_sec005_ex03;
}