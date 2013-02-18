package com.lxisoft.numberconverter;

import java.io.IOException;

public class NumberTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		NumberConverter converter = new NumberConverter();
		converter.readNumber();
		converter.generateNumberString();
		try {
			converter.printString();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
