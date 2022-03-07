package model;

import java.time.LocalDate;
import java.time.chrono.Chronology;
import java.time.chrono.IsoChronology;

public class Income_Expense {

	public static final String INCOME = "INCOME";
	public static final String EXPENSE = "EXPENSE";
	

	private Double value;
	private String description;
	private String tipo;
	private LocalDate date;
	
	public Income_Expense(Double value, String description, int tipo, LocalDate date) {
		this.value = value;
		this.setDescription(description);
		this.setDate(date);
		switch(tipo) {
			case 1: this.setTipo(INCOME);
				break;
			case 2: this.setTipo(EXPENSE);
				break;
		}
		
	}
	
	public Double getValue() {
		return value;
	}
	
	public void setValue(Double value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
}
