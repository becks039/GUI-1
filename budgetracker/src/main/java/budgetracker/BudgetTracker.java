package budgetracker;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class BudgetTracker {

	private JFrame frame;
	private TextField incomeAmountField, incomeSourceField;
	private TextField expenseAmountField, expenseDateField, expenseCategoryField;
	private TextArea summaryArea;

	private ArrayList<Income> incomeList = new ArrayList<>();
	private ArrayList<Expense> expenseList = new ArrayList<>();

	class Income {
		String source;
		double amount;

		public String toString() {
			return "Income: " + source + " | Amount: " + amount;
		}
	}

	class Expense {
		String category, date;
		double amount;

		public String toString() {
			return "Expense: " + category + " | Date: " + date + " | Amount: " + amount;
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				BudgetTracker window = new BudgetTracker();
				window.frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public BudgetTracker() {
		initialize();
	}

	private void showReplayOption() {
		int choice = JOptionPane.showConfirmDialog(frame, "Do you want to continue?", "Continue?", JOptionPane.YES_NO_OPTION);
		if (choice == JOptionPane.NO_OPTION) {
			System.exit(0);
		}
	}

	private double getTotalIncome() {
		return incomeList.stream().mapToDouble(i -> i.amount).sum();
	}

	private double getTotalExpense() {
		return expenseList.stream().mapToDouble(e -> e.amount).sum();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		Label title = new Label("Personal Budget Tracker");
		title.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 22));
		title.setForeground(Color.DARK_GRAY);
		title.setBounds(220, 30, 300, 30);
		frame.getContentPane().add(title);

		Label lblIncome = new Label("Add Income");
		lblIncome.setFont(new Font("Century Gothic", Font.BOLD, 17));
		lblIncome.setBounds(50, 80, 200, 25);
		frame.getContentPane().add(lblIncome);

		Label lblIncomeSource = new Label("Source:");
		lblIncomeSource.setBounds(50, 110, 60, 20);
		frame.getContentPane().add(lblIncomeSource);

		incomeSourceField = new TextField();
		incomeSourceField.setBounds(120, 110, 150, 20);
		frame.getContentPane().add(incomeSourceField);

		Label lblIncomeAmount = new Label("Amount:");
		lblIncomeAmount.setBounds(290, 110, 60, 20);
		frame.getContentPane().add(lblIncomeAmount);

		incomeAmountField = new TextField();
		incomeAmountField.setBounds(360, 110, 100, 20);
		frame.getContentPane().add(incomeAmountField);

		Button btnAddIncome = new Button("Add Income");
		btnAddIncome.setBounds(480, 110, 100, 25);
		btnAddIncome.setBackground(new Color(0, 128, 192));
		btnAddIncome.setForeground(Color.WHITE);
		frame.getContentPane().add(btnAddIncome);

		// Expense Fields
		Label lblExpense = new Label("Add Expense");
		lblExpense.setFont(new Font("Century Gothic", Font.BOLD, 17));
		lblExpense.setBounds(50, 160, 200, 25);
		frame.getContentPane().add(lblExpense);

		Label lblExpenseCategory = new Label("Category:");
		lblExpenseCategory.setBounds(50, 190, 70, 20);
		frame.getContentPane().add(lblExpenseCategory);

		expenseCategoryField = new TextField();
		expenseCategoryField.setBounds(130, 190, 140, 20);
		frame.getContentPane().add(expenseCategoryField);

		Label lblExpenseDate = new Label("Date:");
		lblExpenseDate.setBounds(290, 190, 40, 20);
		frame.getContentPane().add(lblExpenseDate);

		expenseDateField = new TextField();
		expenseDateField.setBounds(340, 190, 100, 20);
		frame.getContentPane().add(expenseDateField);

		Label lblExpenseAmount = new Label("Amount:");
		lblExpenseAmount.setBounds(450, 190, 60, 20);
		frame.getContentPane().add(lblExpenseAmount);

		expenseAmountField = new TextField();
		expenseAmountField.setBounds(520, 190, 100, 20);
		frame.getContentPane().add(expenseAmountField);

		Button btnAddExpense = new Button("Add Expense");
		btnAddExpense.setBounds(290, 220, 100, 25);
		btnAddExpense.setBackground(new Color(0, 128, 192));
		btnAddExpense.setForeground(Color.WHITE);
		frame.getContentPane().add(btnAddExpense);

		Button btnViewSummary = new Button("View Summary");
		btnViewSummary.setBounds(400, 220, 120, 25);
		btnViewSummary.setBackground(new Color(0, 128, 192));
		btnViewSummary.setForeground(Color.WHITE);
		frame.getContentPane().add(btnViewSummary);

		// Summary Display
		Label lblSummary = new Label("Session Summary:");
		lblSummary.setFont(new Font("Century Gothic", Font.BOLD, 17));
		lblSummary.setBounds(50, 270, 200, 25);
		frame.getContentPane().add(lblSummary);

		summaryArea = new TextArea();
		summaryArea.setBounds(50, 300, 600, 250);
		frame.getContentPane().add(summaryArea);

		// --- Event Handling ---

		btnAddIncome.addActionListener(e -> {
			try {
				String source = incomeSourceField.getText().trim();
				double amount = Double.parseDouble(incomeAmountField.getText().trim());

				if (source.isEmpty() || amount <= 0) {
					JOptionPane.showMessageDialog(frame, "Invalid income entry.");
					return;
				}

				Income inc = new Income();
				inc.source = source;
				inc.amount = amount;
				incomeList.add(inc);

				JOptionPane.showMessageDialog(frame, "Income added.");
				showReplayOption();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(frame, "Invalid input.");
			}
		});

		btnAddExpense.addActionListener(e -> {
			try {
				String category = expenseCategoryField.getText().trim();
				String date = expenseDateField.getText().trim();
				double amount = Double.parseDouble(expenseAmountField.getText().trim());

				if (category.isEmpty() || date.isEmpty() || amount <= 0) {
					JOptionPane.showMessageDialog(frame, "Invalid expense entry.");
					return;
				}

				Expense exp = new Expense();
				exp.category = category;
				exp.date = date;
				exp.amount = amount;
				expenseList.add(exp);

				JOptionPane.showMessageDialog(frame, "Expense added.");
				showReplayOption();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(frame, "Invalid input.");
			}
		});

		btnViewSummary.addActionListener(e -> {
			StringBuilder summary = new StringBuilder();
			double totalIncome = getTotalIncome();
			double totalExpense = getTotalExpense();
			double balance = totalIncome - totalExpense;

			summary.append("Total Income: " + totalIncome + "\n");
			summary.append("Total Expense: " + totalExpense + "\n");
			summary.append("Current Balance: " + balance + "\n\n");

			summary.append("--- Income Records ---\n");
			for (Income i : incomeList) summary.append(i + "\n");

			summary.append("\n--- Expense Records ---\n");
			for (Expense e1 : expenseList) summary.append(e1 + "\n");

			summaryArea.setText(summary.toString());
			showReplayOption();
		});
	}
} 
