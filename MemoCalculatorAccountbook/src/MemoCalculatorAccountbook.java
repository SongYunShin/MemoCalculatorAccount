import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


class Memo {
	
}

class Calculator {
	
}

class Account {
	
	//임시 Main_menu가 포함되어 있음
	JFrame frame = new JFrame("Account Book");
	
	JButton memo = new JButton("메모장");
	JButton calculator = new JButton("계산기");
	JButton account = new JButton("가계부");
	
	JButton updateButton = new JButton("수정");
	JButton deleteButton = new JButton("삭제");
	
	
	public void createFrame() {
		
		Container container = frame.getContentPane();
		JPanel menu_bar = new JPanel();
		JPanel excution_panel = new JPanel();
		
		menu_bar.setLayout(new GridLayout(3,1));
		menu_bar.add(memo);
		menu_bar.add(calculator);
		menu_bar.add(account);
		
		
		JPanel table_panel = new JPanel();
		String record[] = {"날짜", "항목", "가격"};
		
		DefaultTableModel model = new DefaultTableModel (record, 0);
		JTable table = new JTable(model);
		
		table_panel.add(new JScrollPane(table));
				
		excution_panel.add(table_panel, BorderLayout.CENTER);
		
		JPanel button_panel = new JPanel();
		button_panel.add(updateButton);
		button_panel.add(deleteButton);
		
		excution_panel.add(button_panel, BorderLayout.PAGE_END);
		
		container.add(menu_bar, BorderLayout.WEST);
		container.add(excution_panel, BorderLayout.CENTER);
		
		
		deleteButton.addActionListener(new SecondButtonActionListener(table, updateButton, deleteButton));
		updateButton.addActionListener(new FirstButtonActionListener(table, updateButton, deleteButton));
		
		
		frame.setSize(600,600);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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


public class MemoCalculatorAccountbook {
	
	public static int index = 0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Account frameA = new Account();
		frameA.createFrame();		

	}

}