package controller;

import application.Main;

public class MainController {
	
	private Main main;

	public void openAddData() {
		main.showAddData();
	}
	
	public void openBalanceSheet() {
		main.showBalanceSheet();
	}
	
	public void setMain(Main main) {
		this.main = main;
	}
	
	public void exit() {
		main.exit();
	}
}
