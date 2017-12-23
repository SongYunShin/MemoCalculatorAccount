package _MemoCalculatorAccountbook;

import static org.junit.Assert.*;

import javax.swing.JButton;

import org.junit.Test;

public class AccountTest {
	
	Account account = new Account();
	JButton button = new JButton();

	//update 매소드를 실행했을 때 버튼이 (확인, 취소)에서 (수정, 삭제)로 성공적으로 바뀌는지 확인하는 test
	@Test
	public void testUpdateJTableJButton() {
		
		if(button.getText() == "확인")
			if(account.update(button).getText() == "수정")
				assertEquals(account.deleteButton,"삭제");
	}

	//삭제 버튼을 눌렀을 때 table의 row가 제대로 삭제되었는지 확인하는 test(한 번에 하나만 삭제 가능)
	@Test
	public void testCancel() {

		if(button.getText() == "삭제") {
			int count = account.table.getRowCount();
			account.cancel(button).getText();
			assertEquals(account.table.getRowCount()-1, count);
		}
	}

}
