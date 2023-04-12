module com.example.demoexcel {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;
    requires java.sql;
    requires org.xerial.sqlitejdbc;

    opens com.example.demoexcel to javafx.fxml;
    exports com.example.demoexcel;
}