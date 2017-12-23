package _MemoCalculatorAccountbook;

import static org.junit.Assert.*;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.junit.Test;

public class MemoPanelTest {
    public JTable table;
	
	@Test
	public void creatclass() {
		MemoPanel memo = new MemoPanel();
		assertTrue(memo.colNames[1]=="내용");
	}

	@Test
	public void testbtnaddRow() {
		MemoPanel memo = new MemoPanel();
	       DefaultTableModel model = new DefaultTableModel(memo.colNames,0);
	       model.addRow(new Object[]{"2017-12-22","소웨공과제"}); 
	       assertFalse(model.getValueAt(0, 0)=="소웨공과제");
	}

}
