import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MemoCalculatorAccountbook extends JFrame {

	JTextField inputTf;
	private JFrame frm;
	private JTabbedPane tabbedPane;
	private MainPanel MainPanel01;
	private JPanel panel2;
	private JPanel panel3;

	MemoCalculatorAccountbook() {
		frm = new JFrame();
		frm.setTitle("�������Ͽ� �����̿� �ֿ����� �Ƕ�������");
		frm.setSize(520, 500);
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.setResizable(false);
		frm.setLocation(500, 200);

		//add MainPanel
		tabbedPane = new JTabbedPane();
		tabbedPane.setTabPlacement(JTabbedPane.LEFT);
		MainPanel01 = new MainPanel();
		panel2 = new JPanel(); // ���� �� �޸��� ���� �̷��� ��
		panel3 = new JPanel();
		// setContentPane(contentPane);
		tabbedPane.add("����", MainPanel01);
		tabbedPane.add("�޸���", panel2);
		tabbedPane.add("�����", panel3);
		frm.add(tabbedPane);
		frm.setVisible(true);
		// contentPane.requestFocus(true);
	}

	class MainPanel extends JPanel {

		JButton numberBtn[];
		JButton addBtn;
		JButton subBtn;
		JButton mulBtn;
		JButton divBtn;
		JButton PBtn; // pound ��ư
		JButton KBtn; // kg��ư
		JButton cmBtn; // cm ��ư
		JButton InBtn; // inch ��ư
		JButton CBtn; // C��ư
		JButton FBtn; // F��ư
		JButton equalBtn;
		JButton ACBtn;
		JButton backBtn;

		String inputTfString;
		boolean calPushBtn; // �����ȣ�� �ߺ� ������ �������� ����

		Calculator cal;

		MainPanel() {
			// ���̾ƿ� �����ڸ� ����
			setLayout(null);
			// Calculate Ŭ������ ��ü ����
			cal = new Calculator();

			// inputTfString �ʱ�ȭ
			initInputTfString();

			// calPushBtn �ʱ�ȭ

			calPushBtn = false; // ������ ���� ����. true �� �����ȣ�� ���� ����
			// �����ȣ�� ���� ���¶�� �����ȣ�� �Է� ���� �ʴ´�.

			// �ؽ�Ʈ �ʵ� �ʱ�ȭ
			inputTf = new JTextField(20);
			inputTf.setBounds(115, 40, 210, 40);
			inputTf.setHorizontalAlignment(SwingConstants.RIGHT);
			inputTf.setFont(new Font(null, Font.PLAIN, 22));
			inputTf.setFocusable(false);

			// �ؽ�Ʈ �ʵ忡 cal.result ���� ���. result�� 0���� �ʱ�ȭ �����Ƿ� 0�� ���.
			setInputTfResult(cal);

			// numberBtn �ʱ�ȭ
			numberBtn = new JButton[10];
			for (Integer i = 0; i < 10; i++) {
				numberBtn[i] = new JButton(i.toString());
				numberBtn[i].setFont(new Font(null, Font.PLAIN, 20)); // ��Ʈ ũ�⸦
																		// 20����
																		// ����
				numberBtn[i].addActionListener(new NumberActionListener());
				if (i != 0)
					numberBtn[i].setSize(45, 30); // 0�̿��� ��ư ũ��
				else
					numberBtn[i].setSize(100, 30); // 0�� ��ư ũ��
			}

			// ���� ��ư �ʱ�ȭ
			addBtn = new JButton("+");
			addBtn.setBounds(115, 90, 45, 30);
			addBtn.setFont(new Font(null, Font.PLAIN, 18));
			addBtn.addActionListener(new CalculateActionListener());

			subBtn = new JButton("-");
			subBtn.setBounds(170, 90, 45, 30);
			subBtn.setFont(new Font(null, Font.PLAIN, 25));
			subBtn.addActionListener(new CalculateActionListener());

			mulBtn = new JButton("*");
			mulBtn.setBounds(225, 90, 45, 30);
			mulBtn.setFont(new Font(null, Font.PLAIN, 20));
			mulBtn.addActionListener(new CalculateActionListener());

			divBtn = new JButton("/");
			divBtn.setBounds(280, 90, 45, 30);
			divBtn.setFont(new Font(null, Font.PLAIN, 20));
			divBtn.addActionListener(new CalculateActionListener());

			// pound kg ��ȯ
			PBtn = new JButton("P");
			PBtn.setBounds(280, 130, 45, 30);
			PBtn.setFont(new Font(null, Font.BOLD, 15));
			PBtn.addActionListener(new PKActionListener());

			KBtn = new JButton("k");
			KBtn.setBounds(280, 170, 45, 30);
			KBtn.setFont(new Font(null, Font.BOLD, 15));
			KBtn.addActionListener(new PKActionListener());

			// cmBtn �ʱ�ȭ -> cm Inch ��ȯ
			InBtn = new JButton("In");
			InBtn.setBounds(280, 210, 45, 30);
			InBtn.setFont(new Font(null, Font.BOLD, 15));
			InBtn.addActionListener(new ICActionListener());

			cmBtn = new JButton("cm");
			cmBtn.setBounds(280, 250, 45, 30);
			cmBtn.setFont(new Font(null, Font.BOLD, 11));
			cmBtn.addActionListener(new ICActionListener());

			// FBtn �ʱ�ȭ -> ���� ȭ��
			CBtn = new JButton("C");
			CBtn.setBounds(170, 250, 45, 30);
			CBtn.setFont(new Font(null, Font.BOLD, 15));
			CBtn.addActionListener(new CFActionListener());

			FBtn = new JButton("F");
			FBtn.setBounds(225, 250, 45, 30);
			FBtn.setFont(new Font(null, Font.BOLD, 15));
			FBtn.addActionListener(new CFActionListener());

			// AC
			ACBtn = new JButton("AC");
			ACBtn.setBounds(335, 90, 45, 70);
			ACBtn.setFont(new Font(null, Font.BOLD, 11));
			ACBtn.addActionListener(new AdjustActionListener());

			backBtn = new JButton("��");
			backBtn.setBounds(335, 40, 45, 40);
			backBtn.setFont(new Font(null, Font.BOLD, 11));
			backBtn.addActionListener(new AdjustActionListener());

			// equalBtn �ʱ�ȭ
			equalBtn = new JButton("=");
			equalBtn.setBounds(335, 170, 45, 110);
			equalBtn.setFont(new Font(null, Font.PLAIN, 18));
			equalBtn.addActionListener(new CalculateActionListener());

			// numberBtn ��ġ ����
			numberBtn[0].setBounds(115, 250, 45, 30);
			for (int i = 1, x = 115, y = 250; i < 10; i++) {
				if (i % 3 == 1) {
					x = 115;
					y = y - 40;
				}
				numberBtn[i].setLocation(x, y);
				x += 55;
			}

			// add inputTextField
			add(inputTf);

			// add numberBtn
			for (int i = 0; i < 10; i++)
				add(numberBtn[i]);

			// add All Btn
			add(FBtn);
			add(cmBtn);
			add(addBtn);
			add(subBtn);
			add(mulBtn);
			add(divBtn);
			add(PBtn);
			add(KBtn);
			add(InBtn);
			add(CBtn);
			add(equalBtn);
			add(ACBtn);
			add(backBtn);
		}

		class NumberActionListener implements ActionListener { // ���� Ű ������
			public void actionPerformed(ActionEvent e) {
				for (Integer i = 0; i < 10; i++) {
					if (e.getSource().equals(numberBtn[i])) {
						if (inputTf.getText().equals("0")) // �Է�â�� 0 �̸� 0�� ��������
							if (i == 0)
								return; // �ƹ��͵� ����
						if (inputTfString.equals("")) // ���Է� ���ۼ��� ��Ʈ ũ�� �ʱ�ȭ
							inputTf.setFont(new Font(null, Font.PLAIN, 22)); // ��Ʈ
																				// ũ��
																				// �ʱ�ȭ
						setInputTfString(i.toString()); // �� �ܿ��� ��� �Է��� �޴´�
						inputTf.setText(inputTfString); // ������ ���ڿ��� ���
					}
					calPushBtn = false; // ���� Ű�� �ѹ��̶� ������ �ٽ�
										// ���� ��ư�� ���� �� �ְ� ��
				}
			}
		}

		class AdjustActionListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource().equals(ACBtn)) {
					cal.initAllElement(); // cal ��ü�� number1 number2 result 0����
											// �ʱ�ȭ
					initInputTfString();
					setInputTfResult(cal); // inputTf �ؽ�Ʈ �ʵ忡 result�� ���, 0�� ���
					calPushBtn = false;
					inputTf.setFont(new Font(null, Font.PLAIN, 22)); // ��Ʈ ũ��
																		// �ʱ�ȭ
				} else if (e.getSource().equals(ACBtn)) {
					initInputTfString(); // inputTfString �� ""�� �ʱ�ȭ
					inputTf.setText("0"); // inputTf �ؽ�Ʈ �ʵ忡 0�� ���
					inputTf.setFont(new Font(null, Font.PLAIN, 22)); // ��Ʈ ũ��
																		// �ʱ�ȭ
				} else if (e.getSource().equals(backBtn)) {// �齺���̽� Ű�� ���������� �׼�
					if (inputTfString.length() > 0) { // ���ڿ��� ���̰� 0���� ũ�� �ѹ��ھ�
														// �߶󳪰���.
						if (inputTfString.length() < 12)
							inputTf.setFont(new Font(null, Font.PLAIN, 22)); // ��Ʈ
																				// ũ��
																				// �ʱ�ȭ
						inputTfString = inputTfString.substring(0, inputTfString.length() - 1);
						if (inputTfString.length() == 0) // 1���� 0�� �Ǵ� ���� 0�� ���
							inputTf.setText("0");
						else // �� �ܿ��� �׻� �齺���̽��� ���ڿ��� ���
							inputTf.setText(inputTfString);
					}
				}
			}
		}

		// pound kg ��ȯ
		class PKActionListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {

			}
		}

		// inch cm ��ȯ
		class ICActionListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {

			}
		}

		// ����ȭ��
		class CFActionListener implements ActionListener {
			// Float cels = Float.valueOf(inputTf.getText());
			// Float fah = Float.valueOf(inputTf.getText());

			public void actionPerformed(ActionEvent e) {
				Float cels = Float.valueOf(inputTf.getText());
				Float fah = Float.valueOf(inputTf.getText());
				if (e.getSource().equals(CBtn)) {
					// transBtn = true;
					// Float cels = Float.valueOf(inputTf.getText());
					// transBtn = false;
					// if (e.getSource().equals(FBtn)) {
					// transBtn = false;
					float fahs = ((9 / 5) * cels) + 32;
					if (e.getSource().equals(equalBtn)) {
						inputTf.setText(Float.toString(fahs));
						// }

					}

				} else if (e.getSource().equals(FBtn)) {
					float celsius = (5 / 9) * (fah - 32);
					if (e.getSource().equals(equalBtn)) {
						inputTf.setText(Float.toString(celsius));
					}
				}

			}
		}

		class CalculateActionListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				if (calPushBtn && !e.getSource().equals(equalBtn)) // calPushBtn
																	// �� true �鼭
					// eqaul��ư �̿��� �����ư�� ������
					// �ƹ��� ��ɵ� �������� �ʰ� ����
					// ������ �ߺ� �Է��� ����
					// ��, ����ڰ� ����-����-����-������ �ƴ�
					// ����-����-����-���� �ൿ�� �� ��� ��ž
					return;
				cal.stringToCalNum(inputTf.getText()); // �ؽ�Ʈ �ʵ忡 �ִ� ���ڿ���
														// cal.calNum
				// �� ����
				if (cal.calculate() != 2) // ���
					setInputTfResult(cal); // ���ġ�� �ؽ�Ʈ �ʵ忡 ���
				initInputTfString(); // ���ڿ��� �ʱ�ȭ
				// �ߺ� ���� ���Բ� �����ϱ�
				if (e.getSource().equals(addBtn))
					cal.setSymbol('+'); // �� ��ư�� �´� ���� ��ȣ�� �Է�
				else if (e.getSource().equals(subBtn))
					cal.setSymbol('-');
				else if (e.getSource().equals(mulBtn))
					cal.setSymbol('*');
				else if (e.getSource().equals(divBtn))
					cal.setSymbol('/');
				else if (e.getSource().equals(equalBtn)) { // equal ��ư�� ������
															// �����ȣ�� �ʱ�ȭ
					cal.initSymbol();
					cal.initCalNum();
					return;
				}
				cal.initCalNum(); // ���ڸ� �ʱ�ȭ(�翬�� ����)
				calPushBtn = true; // ���� ��ư �ߺ� �Է� ����
			}
		}

		public void setInputTfString(String inputTfString) {
			if (this.inputTfString.length() < 16) {
				if (this.inputTfString.length() > 11) // ������ ���ڿ��� 11���� ũ��
					// ��Ʈ ũ�⸦ �۰�
					inputTf.setFont(new Font(null, Font.PLAIN, 16));
				if (this.inputTfString.equals("0"))
					if (!inputTfString.equals("."))
						this.inputTfString = "";
				this.inputTfString = this.inputTfString + inputTfString; // 16����
																			// ���ڱ�����
																			// ����
			}
		}

		public void setInputTfResult(Calculator cal) {
			Double result = cal.result;

			int length = result.toString().length(); // ���ڿ��� ����
			String resultString; // ��� ���ڿ��� ���� ���ڿ� ����

			if (result.toString().substring(length - 2, length).equals(".0")) // �Ǽ�
																				// 0.0,
																				// 1.0����
				// �Ҽ��� �Ʒ� .0�� ����
				resultString = result.toString().substring(0, length - 2);
			else if (searchE(result.toString().toCharArray()))
				if (isInteger(cal.result)) {
					Long longResult = (long) cal.result; // ���ڿ��� E�� ������ Long
															// ����Ŭ���� ����
					if (longResult.toString().length() < 15) // ���ڿ��� ���̰� 14�� ���ϸ�
																// ���������� ǥ��
						resultString = longResult.toString();
					else
						resultString = result.toString(); // 15�� �̻��̸� E�� ��
															// ��������� ǥ��
				} else {
					DecimalFormat df = new DecimalFormat("#.###############");
					resultString = df.format(result); // �Ǽ����� ������� �ʰ� ���
					if (resultString.length() > 17) // 17�� ������ ��� ������� �ʴ´�.
						resultString = result.toString();
				}
			else
				resultString = result.toString(); // E�� ���� �Ǽ��� �׳� �Ǽ������� ǥ��

			if ((length = resultString.length()) > 22)
				resultString = resultString.substring(0, 22);
			if (length > 12)
				inputTf.setFont(new Font(null, Font.PLAIN, 16));
			inputTf.setText(resultString);
		}

		public void initInputTfString() {
			this.inputTfString = "";
		}

		public boolean searchE(char[] array) { // ���ڿ����� E�� �߰ߵǸ� true�� ����
			for (int i = 0; i < array.length; i++)
				if (array[i] == 'E')
					return true;
			return false;
		}

		public boolean isInteger(double result) {
			if (result == (long) result)
				return true;
			return false;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel"); // �����
																					// ����
		} catch (Exception e) {
			e.printStackTrace();
		}
		new MemoCalculatorAccountbook();
	}
}