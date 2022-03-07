package application;
	
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import controller.MainController;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Income_Expense;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
	private ArrayList<Income_Expense> values = new ArrayList<>();
	
	private Stage currentStage;
	@Override
	public void start(Stage primaryStage) {
		try {
			showMain();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void showBalanceSheet() {
		try {
			BorderPane root;
			BorderPane BalanceSheet = FXMLLoader.load(getClass().getResource("../ui/BalanceSheet.fxml"));
			Stage stage = currentStage;
			root = (BorderPane)stage.getScene().getRoot();
			root.setCenter(BalanceSheet);
			stage.show();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showAddData() {
		try {
			BorderPane root;
			BorderPane AddData = FXMLLoader.load(getClass().getResource("../ui/AddData.fxml"));
			Stage stage = currentStage;
			root = (BorderPane)stage.getScene().getRoot();
			root.setCenter(AddData);
			stage.show();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showMain() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/Main.fxml"));
			BorderPane root;
			root = (BorderPane)loader.load();
			MainController controller = loader.getController();
			controller.setMain(this);
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("../ui/application.css").toExternalForm());
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
			currentStage = stage;
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void exit() {
		currentStage.close();
	}
	
	public void createValue(Double value, String description, int tipo, LocalDate date) {
		values.add(new Income_Expense(value, description, tipo, date));
	}
	
	public ArrayList<Income_Expense> getValues() {
		return values;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
