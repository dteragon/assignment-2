
public class Notation {

	/**
	 * Convert an infix expression into a postfix expression
	 * @param infix - the infix expression in string format
	 * @return the postfix expression in string format
	 * @throws InvalidNotationFormatException - if the infix expression format is invalid
	 */
	public static String convertInfixToPostfix(String infix) throws InvalidNotationFormatException {
		
		NotationQueue<String> postfix = new NotationQueue<>(infix.length());
		NotationStack<String> stack = new NotationStack<>(infix.length());
		
		for (int i = 0; i < infix.length(); i++) {
			
			char next = infix.charAt(i);
			if (next >= 48 && next <= 57) {
				postfix.enqueue(next + "");
				
			} else if (next == '(') {
				stack.push(next + "");
				
			} else if (next == '*' || next == '/' || next == '+' || next == '-') {
				while (!stack.isEmpty()) {
					
					int stackPrio = operatorPriority(stack.top());
					int nextPrio = operatorPriority(next + "");
					if (stackPrio >= nextPrio)
						postfix.enqueue(stack.pop());
					else
						break;
				}
				stack.push(next + "");
				
			} else if (next == ')') {
				while (!stack.top().equals("(")) {
					
					postfix.enqueue(stack.pop());
					if (stack.isEmpty())
						throw new InvalidNotationFormatException();
				}
				stack.pop();
			}
		}
		
		while (!stack.isEmpty())
			postfix.enqueue(stack.pop());
		return postfix.toString();
	}
	
	private static int operatorPriority(String op) {

		if (op.equals("*") || op.equals("/"))
			return 2;
		else if (op.equals("+") || op.equals("-"))
			return 1;
		else
			return 0;
	}
	
	/**
	 * Convert the Postfix expression to the Infix expression
	 * @param postfix - the postfix expression in string format
	 * @return the infix expression in string format
	 * @throws InvalidNotationFormatException - if the postfix expression format is invalid
	 */
	public static String convertPostfixToInfix(String postfix) throws InvalidNotationFormatException {
		
		NotationStack<String> stack = new NotationStack<>(postfix.length());
		
		for (int i = 0; i < postfix.length(); i++) {
			char next = postfix.charAt(i);
			
			if (next >= 48 && next <= 57) {
				stack.push(next + "");
				
			} else if (next == '*' || next == '/' || next == '+' || next == '-') {
				
				if (stack.size() < 2)
					throw new InvalidNotationFormatException();
				
				String operand = stack.pop();
				stack.push("(" + stack.pop() + next + operand + ")");	
			}
		}
		
		if (stack.size() > 1)
			throw new InvalidNotationFormatException();
		return stack.pop();
		
	}
	
	/**
	 * Evaluates a postfix expression from a string to a double
	 * @param postfix - the postfix expression in String format
	 * @return the evaluation of the postfix expression as a double
	 * @throws InvalidNotationFormatException - if the postfix expression format is invalid
	 */
	public static double evaluatePostfixExpression(String postfix) throws InvalidNotationFormatException {

		NotationStack<String> stack = new NotationStack<>(postfix.length());

		for (int i = 0; i < postfix.length(); i++) {
			char next = postfix.charAt(i);
			
			if (next >= 48 && next <= 57) {
				stack.push(next + "");
				
			} else if (next == '*' || next == '/' || next == '+' || next == '-') {
				
				if (stack.size() < 2)
					throw new InvalidNotationFormatException();
				
				Double operand2 = Double.parseDouble(stack.pop());
				Double operand1 = Double.parseDouble(stack.pop());
				double result = 0;
				
				switch(next) {
				
				case '*':
					result = operand1 * operand2;
					break;
					
				case '/':
					result = operand1 / operand2;
					break;
					
				case '+':
					result = operand1 + operand2;
					break;
					
				default:
					result = operand1 - operand2;
					break;
				}
				
				stack.push(result + "");
			}
		}

		if (stack.size() > 1)
			throw new InvalidNotationFormatException();
		return Double.parseDouble(stack.pop());
	}
}
