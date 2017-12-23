package _MemoCalculatorAccountbook;

import static org.junit.Assert.*;

import javax.swing.JButton;

import org.junit.Test;

public class AccountTest {
	
	Account account = new Account();
	JButton button = new JButton();

	//update �żҵ带 �������� �� ��ư�� (Ȯ��, ���)���� (����, ����)�� ���������� �ٲ���� Ȯ���ϴ� test
	@Test
	public void testUpdateJTableJButton() {
		
		if(button.getText() == "Ȯ��")
			if(account.update(button).getText() == "����")
				assertEquals(account.deleteButton,"����");
	}

	//���� ��ư�� ������ �� table�� row�� ����� �����Ǿ����� Ȯ���ϴ� test(�� ���� �ϳ��� ���� ����)
	@Test
	public void testCancel() {

		if(button.getText() == "����") {
			int count = account.table.getRowCount();
			account.cancel(button).getText();
			assertEquals(account.table.getRowCount()-1, count);
		}
	}

}
