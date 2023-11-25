module main_module {
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;


    opens main_module to javafx.fxml;
    exports main_module;
    exports main_module.control;
    opens main_module.control to javafx.fxml;
}