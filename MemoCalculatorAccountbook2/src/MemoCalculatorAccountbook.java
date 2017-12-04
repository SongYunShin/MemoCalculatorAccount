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
		frm.setTitle("♡나워니와 송윤이와 주영이의 피땀눈물♥");
		frm.setSize(520, 500);
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.setResizable(false);
		frm.setLocation(500, 200);

		//add MainPanel
		tabbedPane = new JTabbedPane();
		tabbedPane.setTabPlacement(JTabbedPane.LEFT);
		MainPanel01 = new MainPanel();
		panel2 = new JPanel(); // 대충 뭐 메모장 계산기 이런거 들어감
		panel3 = new JPanel();
		// setContentPane(contentPane);
		tabbedPane.add("계산기", MainPanel01);
		tabbedPane.add("메모장", panel2);
		tabbedPane.add("가계부", panel3);
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
		JButton PBtn; // pound 버튼
		JButton KBtn; // kg버튼
		JButton cmBtn; // cm 버튼
		JButton InBtn; // inch 버튼
		JButton CBtn; // C버튼
		JButton FBtn; // F버튼
		JButton equalBtn;
		JButton ACBtn;
		JButton backBtn;

		String inputTfString;
		boolean calPushBtn; // 연산기호의 중복 눌림을 막기위한 변수

		Calculator cal;

		MainPanel() {
			// 레이아웃 관리자를 없앰
			setLayout(null);
			// Calculate 클래스의 객체 생성
			cal = new Calculator();

			// inputTfString 초기화
			initInputTfString();

			// calPushBtn 초기화

			calPushBtn = false; // 눌리지 않은 상태. true 면 연산기호가 눌린 상태
			// 연산기호가 눌린 상태라면 연산기호를 입력 받지 않는다.

			// 텍스트 필드 초기화
			inputTf = new JTextField(20);
			inputTf.setBounds(115, 40, 210, 40);
			inputTf.setHorizontalAlignment(SwingConstants.RIGHT);
			inputTf.setFont(new Font(null, Font.PLAIN, 22));
			inputTf.setFocusable(false);

			// 텍스트 필드에 cal.result 값을 출력. result는 0으로 초기화 됐으므로 0이 출력.
			setInputTfResult(cal);

			// numberBtn 초기화
			numberBtn = new JButton[10];
			for (Integer i = 0; i < 10; i++) {
				numberBtn[i] = new JButton(i.toString());
				numberBtn[i].setFont(new Font(null, Font.PLAIN, 20)); // 폰트 크기를
																		// 20으로
																		// 설정
				numberBtn[i].addActionListener(new NumberActionListener());
				if (i != 0)
					numberBtn[i].setSize(45, 30); // 0이외의 버튼 크기
				else
					numberBtn[i].setSize(100, 30); // 0의 버튼 크기
			}

			// 연산 버튼 초기화
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

			// pound kg 변환
			PBtn = new JButton("P");
			PBtn.setBounds(280, 130, 45, 30);
			PBtn.setFont(new Font(null, Font.BOLD, 15));
			PBtn.addActionListener(new PKActionListener());

			KBtn = new JButton("k");
			KBtn.setBounds(280, 170, 45, 30);
			KBtn.setFont(new Font(null, Font.BOLD, 15));
			KBtn.addActionListener(new PKActionListener());

			// cmBtn 초기화 -> cm Inch 변환
			InBtn = new JButton("In");
			InBtn.setBounds(280, 210, 45, 30);
			InBtn.setFont(new Font(null, Font.BOLD, 15));
			InBtn.addActionListener(new ICActionListener());

			cmBtn = new JButton("cm");
			cmBtn.setBounds(280, 250, 45, 30);
			cmBtn.setFont(new Font(null, Font.BOLD, 11));
			cmBtn.addActionListener(new ICActionListener());

			// FBtn 초기화 -> 섭씨 화씨
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

			backBtn = new JButton("←");
			backBtn.setBounds(335, 40, 45, 40);
			backBtn.setFont(new Font(null, Font.BOLD, 11));
			backBtn.addActionListener(new AdjustActionListener());

			// equalBtn 초기화
			equalBtn = new JButton("=");
			equalBtn.setBounds(335, 170, 45, 110);
			equalBtn.setFont(new Font(null, Font.PLAIN, 18));
			equalBtn.addActionListener(new CalculateActionListener());

			// numberBtn 위치 설정
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

		class NumberActionListener implements ActionListener { // 숫자 키 리스너
			public void actionPerformed(ActionEvent e) {
				for (Integer i = 0; i < 10; i++) {
					if (e.getSource().equals(numberBtn[i])) {
						if (inputTf.getText().equals("0")) // 입력창이 0 이면 0을 눌렀을때
							if (i == 0)
								return; // 아무것도 안함
						if (inputTfString.equals("")) // 재입력 시작순간 폰트 크기 초기화
							inputTf.setFont(new Font(null, Font.PLAIN, 22)); // 폰트
																				// 크기
																				// 초기화
						setInputTfString(i.toString()); // 그 외에는 계속 입력을 받는다
						inputTf.setText(inputTfString); // 더해진 문자열을 출력
					}
					calPushBtn = false; // 숫자 키가 한번이라도 눌리면 다시
										// 연산 버튼을 누를 수 있게 함
				}
			}
		}

		class AdjustActionListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource().equals(ACBtn)) {
					cal.initAllElement(); // cal 객체의 number1 number2 result 0으로
											// 초기화
					initInputTfString();
					setInputTfResult(cal); // inputTf 텍스트 필드에 result를 출력, 0이 출력
					calPushBtn = false;
					inputTf.setFont(new Font(null, Font.PLAIN, 22)); // 폰트 크기
																		// 초기화
				} else if (e.getSource().equals(ACBtn)) {
					initInputTfString(); // inputTfString 을 ""로 초기화
					inputTf.setText("0"); // inputTf 텍스트 필드에 0을 출력
					inputTf.setFont(new Font(null, Font.PLAIN, 22)); // 폰트 크기
																		// 초기화
				} else if (e.getSource().equals(backBtn)) {// 백스페이스 키를 눌렀을때의 액션
					if (inputTfString.length() > 0) { // 문자열의 길이가 0보다 크면 한문자씩
														// 잘라나간다.
						if (inputTfString.length() < 12)
							inputTf.setFont(new Font(null, Font.PLAIN, 22)); // 폰트
																				// 크기
																				// 초기화
						inputTfString = inputTfString.substring(0, inputTfString.length() - 1);
						if (inputTfString.length() == 0) // 1에서 0이 되는 순간 0을 출력
							inputTf.setText("0");
						else // 그 외에는 항상 백스페이스된 문자열을 출력
							inputTf.setText(inputTfString);
					}
				}
			}
		}

		// pound kg 변환
		class PKActionListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {

			}
		}

		// inch cm 변환
		class ICActionListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {

			}
		}

		// 섭씨화씨
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
																	// 이 true 면서
					// eqaul버튼 이외의 연산버튼을 누르면
					// 아무런 명령도 수행하지 않고 종료
					// 연산의 중복 입력을 방지
					// 즉, 사용자가 숫자-연산-숫자-연산이 아닌
					// 숫자-연산-연산-연산 행동을 할 경우 스탑
					return;
				cal.stringToCalNum(inputTf.getText()); // 텍스트 필드에 있는 문자열을
														// cal.calNum
				// 에 대입
				if (cal.calculate() != 2) // 계산
					setInputTfResult(cal); // 결과치를 텍스트 필드에 출력
				initInputTfString(); // 문자열을 초기화
				// 중복 눌림 없게끔 수정하기
				if (e.getSource().equals(addBtn))
					cal.setSymbol('+'); // 각 버튼에 맞는 연산 기호를 입력
				else if (e.getSource().equals(subBtn))
					cal.setSymbol('-');
				else if (e.getSource().equals(mulBtn))
					cal.setSymbol('*');
				else if (e.getSource().equals(divBtn))
					cal.setSymbol('/');
				else if (e.getSource().equals(equalBtn)) { // equal 버튼을 누르면
															// 연산기호를 초기화
					cal.initSymbol();
					cal.initCalNum();
					return;
				}
				cal.initCalNum(); // 숫자를 초기화(재연산 방지)
				calPushBtn = true; // 연산 버튼 중복 입력 방지
			}
		}

		public void setInputTfString(String inputTfString) {
			if (this.inputTfString.length() < 16) {
				if (this.inputTfString.length() > 11) // 합쳐질 문자열이 11보다 크면
					// 폰트 크기를 작게
					inputTf.setFont(new Font(null, Font.PLAIN, 16));
				if (this.inputTfString.equals("0"))
					if (!inputTfString.equals("."))
						this.inputTfString = "";
				this.inputTfString = this.inputTfString + inputTfString; // 16개의
																			// 숫자까지만
																			// 받음
			}
		}

		public void setInputTfResult(Calculator cal) {
			Double result = cal.result;

			int length = result.toString().length(); // 문자열의 길이
			String resultString; // 결과 문자열을 받을 문자열 변수

			if (result.toString().substring(length - 2, length).equals(".0")) // 실수
																				// 0.0,
																				// 1.0등의
				// 소수점 아래 .0을 없앰
				resultString = result.toString().substring(0, length - 2);
			else if (searchE(result.toString().toCharArray()))
				if (isInteger(cal.result)) {
					Long longResult = (long) cal.result; // 문자열에 E가 있으면 Long
															// 래핑클래스 선언
					if (longResult.toString().length() < 15) // 문자열의 길이가 14개 이하면
																// 정수형으로 표현
						resultString = longResult.toString();
					else
						resultString = result.toString(); // 15개 이상이면 E가 들어간
															// 축약형으로 표현
				} else {
					DecimalFormat df = new DecimalFormat("#.###############");
					resultString = df.format(result); // 실수형을 축약하지 않고 출력
					if (resultString.length() > 17) // 17개 이하일 경우 축약하지 않는다.
						resultString = result.toString();
				}
			else
				resultString = result.toString(); // E가 없는 실수는 그냥 실수형으로 표현

			if ((length = resultString.length()) > 22)
				resultString = resultString.substring(0, 22);
			if (length > 12)
				inputTf.setFont(new Font(null, Font.PLAIN, 16));
			inputTf.setText(resultString);
		}

		public void initInputTfString() {
			this.inputTfString = "";
		}

		public boolean searchE(char[] array) { // 문자열에서 E가 발견되면 true를 리턴
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
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel"); // 룩앤필
																					// 적용
		} catch (Exception e) {
			e.printStackTrace();
		}
		new MemoCalculatorAccountbook();
	}
}