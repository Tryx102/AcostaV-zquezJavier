package calculadora;

import java.util.Scanner;

class StackNode {
	public StackNode(double data, StackNode underneath) {
		this.data = data;
		this.underneath = underneath;
	}

	public StackNode underneath;
	public double data;
}

public class RPN {
	public void into(double new_data) {
		StackNode new_node = new StackNode(new_data, top);
		top = new_node;
	}

	public double outof( ) {
		double top_data = top.data;
		top = top.underneath;
		return top_data;
	}

	public RPN(String comando) {
		top = null;
		this.comando = comando;
	}

	public double get( ) {
		double numero1, numero2;
		int j;

		for(int i = 0; i < comando.length( ); i++) {
			// if it's a digit
			if(Character.isDigit(comando.charAt(i))) {
				double number;

				// get a string of the number
				String temp = "";
				for(j = 0; (j < 100) && (Character.isDigit(comando.charAt(i)) || (comando.charAt(i) == '.')); j++, i++) {
					temp = temp + String.valueOf(comando.charAt(i));
				}

				// convert to double and add to the stack
				number = Double.parseDouble(temp);
				into(number);
			} else if(comando.charAt(i) == '+') {
				numero2 = outof( );
				numero1 = outof( );
				into(numero1 + numero2);
			} else if(comando.charAt(i) == '-') {
				numero2 = outof( );
				numero1 = outof( );
				into(numero1 - numero2);
			} else if(comando.charAt(i) == '*') {
				numero2 = outof( );
				numero1 = outof( );
				into(numero1 * numero2);
			} else if(comando.charAt(i) == '/') {
				numero2 = outof( );
				numero1 = outof( );
				into(numero1 / numero2);
			}
			else if(comando.charAt(i) == '^') {
				numero2 = outof( );
				numero1 = outof( );
				into(Math.pow(numero1, numero2));}
			else if(comando.charAt(i) == '%') {
				numero2 = outof( );
				numero1 = outof( );
				into(numero1%numero2);
			} else if(comando.charAt(i) != ' ') {
				throw new IllegalArgumentException( );
			}
		}

		double val = outof( );

		if(top != null) {
			throw new IllegalArgumentException( );
		}

		return val;
	}

	private String comando;
	private StackNode top;

	/* main method */
	public static void main(String args[]) {
		while(true) {
			Scanner in = new Scanner(System.in);
			System.out.println("Enter RPN expression or \"quit\".");
			String line = in.nextLine( );
			if(line.equals("quit")) {
				break;
			} else {
				RPN calc = new RPN(line);
				System.out.printf("Answer is %f\n", calc.get( ));
			}
		} 
	}
}