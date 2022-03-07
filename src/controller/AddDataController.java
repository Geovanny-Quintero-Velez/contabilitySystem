package controller;

import java.time.LocalDate;
import java.time.chrono.Chronology;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.IncompleteFieldsException;

public class AddDataController {
	
	private Main main;

	@FXML
	TextField tfValue;
	
	@FXML
	TextArea taDescription;
	
	@FXML
	RadioButton rbIncome_Type;
	
	@FXML
	RadioButton rbExpense_Type;
	
	@FXML
	Button btCreateValue;
	
	@FXML
	DatePicker dpDate;
	
	public void createValue()  {
		try {
			if((tfValue.getText().equals("")) || ((rbIncome_Type.isSelected() == false) && (rbExpense_Type.isSelected() == false)) ) {
				throw new IncompleteFieldsException();
			}
			Double value = Double.parseDouble(tfValue.getText());
			String description = taDescription.getText();
			int type = 0;
			if(rbIncome_Type.isSelected() && !(rbExpense_Type.isSelected())) {
				type = 1;
			}
			else {
				type = 2;
			}
			LocalDate date = dpDate.getValue();
			main.createValue(value, description, type, date);
			tfValue.setText("");
			taDescription.setText("");
			rbExpense_Type.setSelected(false);
			rbIncome_Type.setSelected(false);
		}catch(IncompleteFieldsException e) {
			e.showErrorAlert();
		}
		
	}
	
	public void selectRbIncome() {
		rbExpense_Type.setSelected(false);
	}
	
	public void selectRbExpense() {
		rbIncome_Type.setSelected(false);
	}

	public Main getMain() {
		return main;
	}

	public void setMain(Main main) {
		this.main = main;
	}
	
	
}
