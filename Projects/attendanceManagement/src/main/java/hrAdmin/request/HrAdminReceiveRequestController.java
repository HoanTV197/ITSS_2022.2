package main.java.hrAdmin.request;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import attendanceLog.AttendanceDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.java.utils.Notification;

public class HrAdminReceiveRequestController implements Initializable{

	AttendanceDB attendanceDB = AttendanceDB.GetInstance();
	
    @FXML
    private TableView<Notification> notificationsList;
	
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		List<Notification> notifications = attendanceDB.getNotifications("HRAdmin");
		
        ObservableList<Notification> items = FXCollections.observableArrayList(notifications);

        TableColumn<Notification, String> fromColomn = new TableColumn<>("From");
        fromColomn.setCellValueFactory(new PropertyValueFactory<>("fromId"));
        
        TableColumn<Notification, String> contentColumn = new TableColumn<>("Content");
        contentColumn.setCellValueFactory(new PropertyValueFactory<>("content"));
        
        notificationsList.getColumns().addAll(fromColomn, contentColumn);

        notificationsList.setItems(items);
	}
	
}
