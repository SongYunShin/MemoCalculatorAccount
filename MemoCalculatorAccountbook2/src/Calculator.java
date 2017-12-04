public class Calculator {

	double calNum;
	double result;
	char symbol;
	

	Calculator() {
		initAllElement();
	}

	public int calculate() {
		switch (this.symbol) {
		case '+':
			result = result + calNum;
			break;
		case '-':
			result = result - calNum;
			break;
		case '*':
			result = result * calNum;
			break;
		case '/':
			try {
				result = result / calNum;
			} catch (ArithmeticException e) {
				return 0;
			}
			break;
		default:
			result = calNum;
			return 2; // 연산 기호가 없으면 calNum을 result에 대입
		}
		return 1;
	}
	

	public void setCalNum(double number) {
		this.calNum = number;
	}

	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}

	public void initAllElement() {
		calNum = 0;
		result = 0;
		symbol = 0;
	}

	public void initAllNumber() {
		result = 0;
		calNum = 0;
	}

	public void initCalNum() {
		calNum = 0;
	}

	public void initSymbol() {
		symbol = 0;
	}

	public void stringToCalNum(String stringNumber) {
		calNum = Double.parseDouble(stringNumber);
	}

	public String resultToString() {
		return new Double(result).toString();
	}
}
