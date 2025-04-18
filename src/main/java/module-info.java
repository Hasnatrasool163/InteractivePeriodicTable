module org.htech.interactiveperiodictable {
    requires javafx.controls;
    requires javafx.fxml;


    exports org.htech.interactiveperiodictable.modal;
    opens org.htech.interactiveperiodictable.modal to javafx.fxml;
    exports org.htech.interactiveperiodictable.layout;
    opens org.htech.interactiveperiodictable.layout to javafx.fxml;
    exports org.htech.interactiveperiodictable.controller to javafx.graphics;
    exports org.htech.interactiveperiodictable.graphs to javafx.graphics;
}