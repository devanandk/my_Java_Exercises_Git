package com.lxisoft.numberconverter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NumberConverter {
	
	int number;
	BufferedReader input;
	String sNumber;
	int length;
	int position;
	int temp;
	private static final int MAXLENGTH = 9;
	
	String ones[] = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
	String tens[] = {"eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen","seventeen", "eighteen",
					 "nineteen"};
	String tenplus[] = {"ten","twenty", "thirty", "fourty", "fifty", "sixty", "seventy", "eighty", "ninety"};
	
	public NumberConverter()
	{
		number = 0;
		input = new BufferedReader(new InputStreamReader(System.in));
		sNumber = "";
		length = 0;
		position = length;
	}
	
	public void readNumber()
	{
		try {
			
			System.out.print("Enter a number : ");
			
			String num = input.readLine();
			number = Integer.parseInt(num);
			length = num.length();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void generateString()
	{
		temp = number;
		int rev = 0;
		int currentDigit = 0;
		String currentWord = "";
		position = length;
		boolean tensFlag = false;
		
		if(temp < 0)
		{
		sNumber = "negative ";
		temp = temp * -1;
		length = length - 1;
		position = length;
		}
		if(length > MAXLENGTH)
			System.out.println("Please enter a nine digit number or less...!!");
		else
		{
			if(number == 0)
				sNumber = "Zero";
				else
				{
			while(temp > 0)
			{
				currentDigit = temp % 10;
				rev = (rev * 10) + currentDigit;
				temp = temp / 10;
			}
			currentDigit = 0;
			temp = rev;

			while(position > 0){
				
				currentDigit = temp % 10;
				
				switch(position){
				
				case 9:
					currentWord = tensBuilder(currentDigit, "crore");
					currentWord = checkEmptyAndAppend(currentWord, "");
					break;
				
				case 8:
					currentWord = onesBuilder(currentDigit);
					currentWord = checkEmptyAndAppend(currentWord, "crore");
					break;
				
				case 7:
					currentWord = tensBuilder(currentDigit, "lakh");
					currentWord = checkEmptyAndAppend(currentWord, "");
					break;
				
				case 6:
					currentWord = onesBuilder(currentDigit);
					currentWord = checkEmptyAndAppend(currentWord, "lakh");
					break;
				
				case 5:
					currentWord = tensBuilder(currentDigit, "thousand");
					currentWord = checkEmptyAndAppend(currentWord,"");
					break;
					
				case 4:
					currentWord = onesBuilder(currentDigit);
					currentWord = checkEmptyAndAppend(currentWord, "thousand");
					break;
					
				case 3:
					currentWord = onesBuilder(currentDigit);
					currentWord = checkEmptyAndAppend(currentWord, "hundred");
					break;
					
				case 2:
					if(length > position && currentDigit != 0)
					{
						currentWord = " and ";
						tensFlag = true;
					}
					currentWord += tensBuilder(currentDigit,"");
					break;
					
				case 1:
					if(length > 2 && currentDigit != 0 && tensFlag != true){
						currentWord = "and";
					}
					currentWord += " " + onesBuilder(currentDigit);
					break;
				}
							
				sNumber = sNumber + currentWord;
				currentWord = "";
				
				temp = temp / 10;
				position--;
			}
		}
		}
	}
	
	@SuppressWarnings("unused")
	public void printString() throws IOException{
		System.out.println("\nThe desired number is : " + sNumber);
		System.out.println("\nPress Enter to exit.");
		int i = input.read();
	}
	
	private String tensBuilder(int currentDigit, String positionString)
	{
		String currentWord = "";
		if(currentDigit == 1)
		{
		currentDigit = (temp/10) % 10;
		temp = temp / 10;
		position--;
		if(currentDigit != 0)
			{
			currentWord = tens[currentDigit-1] + " " + positionString;
			}
		else
			{
			currentWord = tenplus[0] + " " + positionString;
			}
		}
		else
		{
		if(currentDigit != 0)
		{
			currentWord = tenplus[currentDigit-1];
		}
		}
		return currentWord;
	}
             
	private String onesBuilder(int currentDigit){
		String currentWord = "";
		if(currentDigit != 0)
		{
		currentWord = ones[currentDigit-1];
		}
		return currentWord;
	}

	private String checkEmptyAndAppend(String currentWord, String appendString){
		if(currentWord.equals("")){
			return "";
		}
			else{
				
				return currentWord + " " + appendString + " ";
		}
	}
}
