package _MemoCalculatorAccountbook;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

class Account extends JPanel{

	JButton updateButton = new JButton("수정");
	JButton deleteButton = new JButton("삭제");	
	
	Account() {
		
		JPanel table_panel = new JPanel();
		String record[] = {"날짜", "항목", "가격"};
		
		DefaultTableModel model = new DefaultTableModel (record, 0);
		JTable table = new JTable(model);
		
		table_panel.add(new JScrollPane(table));
				
		add(table_panel, BorderLayout.CENTER);
		
		JPanel button_panel = new JPanel();
		button_panel.add(updateButton);
		button_panel.add(deleteButton);
		
		add(button_panel, BorderLayout.PAGE_END);
		
		
		deleteButton.addActionListener(new SecondButtonActionListener(table, updateButton, deleteButton));
		updateButton.addActionListener(new FirstButtonActionListener(table, updateButton, deleteButton));

	}

	private void setLayout(Object object) {
		// TODO Auto-generated method stub
		
	}
	
	
}

class SecondButtonActionListener implements ActionListener {
	
	JTable table;
	JButton firstButton;
	JButton secondButton;
	
	SecondButtonActionListener(JTable table, JButton firstButton, JButton secondButton) {
		
		this.table = table;
		this.firstButton = firstButton;
		this.secondButton = secondButton;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		int row = table.getSelectedRow();
		
		
		if(secondButton.getText() == "삭제") {
			
			if(row == -1)
				return;
			
			model.removeRow(row);
			MemoCalculatorAccountbook.index--;
		}
		else if(secondButton.getText() == "취소") {
			
			if(row != -1) {
				
				if(table.getEditingRow() == MemoCalculatorAccountbook.index)
					table.editingCanceled(null);
				else
					table.setValueAt(table.editCellAt(table.getEditingRow(), table.getEditingColumn()), table.getEditingRow(), table.getEditingColumn());
					
			}
			model.removeRow(MemoCalculatorAccountbook.index);
			
			firstButton.setText("수정");
			secondButton.setText("삭제");
		}
		
	}
	
}

class FirstButtonActionListener implements ActionListener {
	
	JTable table;
	JButton firstButton;
	JButton secondButton;
	
	FirstButtonActionListener(JTable table, JButton firstButton, JButton secondButton) {
		
		this.table = table;
		this.firstButton = firstButton;
		this.secondButton = secondButton;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(firstButton.getText() == "수정") {
		
			DefaultTableModel model = (DefaultTableModel)table.getModel();
			
			model.addRow(new Object[] {"","",""});

			firstButton.setText("확인");
			secondButton.setText("취소");
		
		}
		
		else if(firstButton.getText() == "확인") {
			
			MemoCalculatorAccountbook.index++;
			
			firstButton.setText("수정");
			secondButton.setText("삭제");
		}
	}
}