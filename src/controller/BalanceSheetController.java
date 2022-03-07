package controller;

import model.Income_Expense;
import model.InvalidTimeException;

import java.util.ArrayList;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class BalanceSheetController {
	
	private Main main;
	
	@FXML
	TableView balanceTable_Incomes;
	
	@FXML
	TableView balanceTable_Expenses;
	
	@FXML
	TableColumn incomes;
	
	@FXML
	TableColumn expenses;
	
	@FXML
	DatePicker dpFilter_Begin;
	
	@FXML
	DatePicker dpFilter_End;
	
	@FXML
	TextField tfBenefit;
	
	@FXML
	TextField tfTotalIncome;
	
	@FXML
	TextField tfTotalExpense;
	
	@FXML
	Button btApplyFilter;
	
	@FXML
	Button btRemoveFilter;
	
	private ArrayList<Double> incomesModel = new ArrayList<>();
	
	private ArrayList<Double> expensesModel = new ArrayList<>();
	
	public void initialize() {
		incomes.setCellValueFactory(new PropertyValueFactory<>("Income"));
		expenses.setCellValueFactory(new PropertyValueFactory<>("Expense"));
		ObservableList<Double> valuesIncomes = FXCollections.observableArrayList(incomesModel);
		ObservableList<Double> valuesExpenses = FXCollections.observableArrayList(expensesModel);
		balanceTable_Incomes.setItems(valuesIncomes);
		balanceTable_Expenses.setItems(valuesExpenses);
		
		updadeTotalBenefit(main.getValues());
		updateIncomesList(main.getValues());
		updateExpensesList(main.getValues());
	}
	
	public void updateIncomesList(ArrayList<Income_Expense> list) {
		incomesModel.removeAll(incomesModel);
		for(int i= 0; i<list.size(); i++) {
			if(list.get(i).getTipo().equals(Income_Expense.INCOME)) {
				incomesModel.add(list.get(i).getValue());
			}
		}
	}
	
	public void updateExpensesList(ArrayList<Income_Expense> list) {
		expensesModel.removeAll(expensesModel);
		for(int i= 0; i<list.size(); i++) {
			if(list.get(i).getTipo().equals(Income_Expense.EXPENSE)) {
				expensesModel.add(list.get(i).getValue());
			}
		}
	}
	
	public Double UpdadeTotalIncomes(ArrayList<Income_Expense> list) {
		Double total = 0.0;
		for(int i= 0; i<list.size(); i++) {
			if(list.get(i).getTipo().equals(Income_Expense.INCOME)) {
				total += list.get(i).getValue();
			}
		}
		total = (double) Math.round((total.doubleValue()*100.0)/100.0);
		tfTotalIncome.setText(total.toString());
		return total;
	}
	
	public Double UpdadeTotalExpenses(ArrayList<Income_Expense> list) {
		Double total = 0.0;
		for(int i= 0; i<list.size(); i++) {
			if(list.get(i).getTipo().equals(Income_Expense.EXPENSE)) {
				total += list.get(i).getValue();
			}
		}
		total = (double) Math.round((total.doubleValue()*100.0)/100.0);
		tfTotalExpense.setText(total.toString());
		return total;
	}

	public void updadeTotalBenefit(ArrayList<Income_Expense> list) {
		Double totalIncomes = UpdadeTotalIncomes(list);
		Double totalExpenses = UpdadeTotalExpenses(list);
		Double totalBenefit = totalIncomes - totalExpenses;
		tfBenefit.setText(totalBenefit.toString());
	}
	
	public void filterData() {
		try {
			ArrayList<Income_Expense> list = main.getValues();
			ArrayList<Income_Expense> filteredValues = new ArrayList<>();
			int diferencia = dpFilter_Begin.getChronology().compareTo(dpFilter_End.getChronology());
			if(diferencia<0) {
				throw new InvalidTimeException();
			}
			for(int i = 0; i<incomesModel.size(); i++) {
				if((dpFilter_Begin.getValue().compareTo(list.get(i).getDate())>= 0) && (dpFilter_End.getValue().compareTo(list.get(i).getDate())>= 0)) {
					filteredValues.add(list.get(i));
				}
			}
			updateIncomesList(filteredValues);
			updateExpensesList(filteredValues);
			updadeTotalBenefit(filteredValues);
		}catch(InvalidTimeException e) {
			e.showErrorAlert();
		}
	}
	
	public void removeFilter() {
		updadeTotalBenefit(main.getValues());
		updateIncomesList(main.getValues());
		updateExpensesList(main.getValues());
	}

	public Main getMain() {
		return main;
	}

	public void setMain(Main main) {
		this.main = main;
	}
}
