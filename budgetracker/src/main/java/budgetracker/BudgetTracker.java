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

		Label lblSummary = new Label("Session Summary:");
		lblSummary.setFont(new Font("Century Gothic", Font.BOLD, 17));
		lblSummary.setBounds(50, 270, 200, 25);
		frame.getContentPane().add(lblSummary);

		summaryArea = new TextArea();
		summaryArea.setBounds(50, 300, 600, 250);
		frame.getContentPane().add(summaryArea);

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
