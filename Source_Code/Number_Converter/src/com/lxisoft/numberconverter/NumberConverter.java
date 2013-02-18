package com.lxisoft.numberconverter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NumberConverter {
	
	double number;
	BufferedReader input;
	String sNumber;
	
	int length;
	int position;
	long temp;
	
	String sReal, sDecimal;
	long lReal, lDecimal;
	int dotPosition;
	
	boolean decimalReverse = false;
	boolean positionFlags[] = new boolean[MAXREAL];

	private static final int ROUNDOFFINDEX = 3; // tells where the round off should occur: 
												// should be 1 greater than the rounded off length
	private static final int MAXLENGTH = Long.toString(Long.MAX_VALUE).length();
	private static final int MAXREAL = 10;
	
	
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
		sReal = "";
		sDecimal = "";
		dotPosition = 0;
		int i = 0;
		while(i < MAXREAL){
			positionFlags[i] = false;
			i++;
		}
	}
	
	public void readNumber()
	{
		try {
			
//			System.out.println(MAXLENGTH);
			System.out.print("Enter a number : ");
			
			String num = input.readLine();
			number = Double.parseDouble(num);
			
						
			dotPosition = num.indexOf(".");
//			System.out.println(dotPosition);
			
			if(dotPosition == -1){
				sReal = num;
				sDecimal = "0";
//				System.out.println(sDecimal);
			}
			else{
			sReal = num.substring(0, dotPosition);
//			System.out.println(sReal);
			sDecimal = num.substring(dotPosition+1, num.length());
//			System.out.println(sDecimal);
		} 
			lReal = Long.parseLong(sReal);
			
			if(sDecimal.length() > MAXLENGTH){
				System.out.println("\nYou may only enter a maximum of 19 digits after the decimal point.");
				throw new NumberFormatException();
			}
			
			lDecimal = Long.parseLong(sDecimal);
			
			//-- following section is for the round off function
			long roundOff = Math.round(number);
			
			if(roundOff == lReal){ // checks if the round off value for the real part
								   // remains unchanged during the round off function
				int index = ROUNDOFFINDEX;
				long tempVal = 0;
				long roundOffDecimal = 0;
				
				decimalReverse = true;
				long decRev = reverse(lDecimal);
				decimalReverse = false;
				
				while(index > 0){
					tempVal = decRev % 10;
					if(index == 1){
						if(tempVal >= 5)
							roundOffDecimal++;
						break;
					}
					roundOffDecimal = (roundOffDecimal * 10) + tempVal;
					decRev /= 10;
					index--;
				}
				
				lDecimal = roundOffDecimal;
				sDecimal = new Long(lDecimal).toString();
			}
			else
			{
				lReal = roundOff;
				lDecimal = 0;
			}
			//-- round off section ends here
			length = sReal.length();
//			System.out.println(length);
		}
		catch (IOException e) {
			e.printStackTrace();
			waitAndExit();
		}
		catch (NumberFormatException e){
			System.out.println("You did not enter a valid number.\nRestart the application to start again...\n\nPRESS ENTER TO EXIT");
			waitAndExit();
		}
	}

	private void generateRealString()
	{
		temp = lReal;
//		System.out.println(temp);
		long currentDigit = 0;
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
		if(length > MAXREAL)
			{
			System.out.println("Please enter a ten digit number or less for the real part...!!");
			}
		else
		{
			if(temp == 0)
				sNumber = "Zero";
				else
				{
			
			currentDigit = 0;
			temp = reverse(temp);

			while(position > 0){
				
				currentDigit = temp % 10;
				
				switch(position){
				
				case 10:
					currentWord = onesBuilder(currentDigit);
					currentWord = checkEmptyAndAppend(currentWord, "hundred");
					break;
				case 9:
					if(length > position && currentDigit != 0)
					{
						currentWord = " and ";
						tensFlag = true;
						positionFlags[position-1] = true;
					}
					currentWord += tensBuilder(currentDigit, "crore");
					currentWord = checkEmptyAndAppend(currentWord, "");
					
					break;
				
				case 8:
					if(length > position+1 && currentDigit != 0 && tensFlag == false)
					{
						currentWord = " and ";
					}
					currentWord += onesBuilder(currentDigit);
					currentWord = checkEmptyAndAppend(currentWord, "crore");
					
					if(tensFlag == false && currentDigit == 0 && length > position) currentWord = " crore ";

					break;
				
				case 7:
					currentWord = tensBuilder(currentDigit, "lakh");
					currentWord = checkEmptyAndAppend(currentWord, "");
					break;
				
				case 6:
					currentWord = onesBuilder(currentDigit);
					currentWord = checkEmptyAndAppend(currentWord, "lakh");
					
					if(currentDigit == 0 && length > position && positionFlags[position] == true) currentWord = " lakh ";
					break;
				
				case 5:
					currentWord = tensBuilder(currentDigit, "thousand");
					currentWord = checkEmptyAndAppend(currentWord,"");
					break;
					
				case 4:
					currentWord = onesBuilder(currentDigit);
					currentWord = checkEmptyAndAppend(currentWord, "thousand");
					
					if(currentDigit == 0 && length > position && positionFlags[position] == true) currentWord = " thousand ";
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
	
	public void printString() throws IOException{
		System.out.println("\nThe desired number is : " + sNumber);
		System.out.println("\nPress Enter to exit.");
		waitAndExit();
	}
	
	private String tensBuilder(long currentDigit, String positionString)
	{
		String currentWord = "";
		if(currentDigit == 1)
		{
		currentDigit = (temp/10) % 10;
		temp = temp / 10;
		positionFlags[position-1] = true;
		position--;
		positionFlags[position-1] = true;
		if(currentDigit != 0)
			{
			currentWord = tens[(int) (currentDigit-1)] + " " + positionString;
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
			positionFlags[position-1] = true;
			currentWord = tenplus[(int) (currentDigit-1)];
		}
		}
		return currentWord;
	}
             
	private String onesBuilder(long currentDigit){
		String currentWord = "";
		if(currentDigit != 0)
		{
		currentWord = ones[(int) (currentDigit-1)];
		positionFlags[position-1] = true;
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

	public void generateNumberString() {
		generateRealString();
		generateDecimalString();
	}

	private void generateDecimalString() {
		temp = lDecimal;
		position = sDecimal.length();//new Long(lDecimal).toString().length();//-- to find the length of the modified decimal value after round off
		long currentDigit;
		boolean zeroFlag = false;
		int zeroCount = 0;
		
		if(temp != 0){
			sNumber += " point";
			decimalReverse = true;
			temp = reverse(temp);
//			System.out.println(temp);
			while(position > 0){
				currentDigit = temp % 10;
				
				if(currentDigit == 0){
					zeroCount++;
					zeroFlag = true;
				}
				else{
					if(zeroFlag)
					{
						for(int i = 0; i < zeroCount; i++)
							sNumber += " zero";
					zeroFlag = false;
					zeroCount = 0;
					}
					sNumber += " " + onesBuilder(currentDigit);
				}
				
				position--;
				temp = temp / 10;
			}
		}
		
	}

	private long reverse(long value){
		long tempVar = value;
		long currentDigit = 0;
		long rev = 0;
		int length = sDecimal.length();
		if(decimalReverse){
			while(length > 0){
				currentDigit = tempVar % 10;
				rev = (rev * 10) + currentDigit;
				tempVar = tempVar / 10;
				length--;
			}
		}
		else
		{
		while(tempVar > 0)
		{
			currentDigit = tempVar % 10;
			rev = (rev * 10) + currentDigit;
			tempVar = tempVar / 10;
		}
		}
		return rev;
	}

	@SuppressWarnings("unused")
	public void waitAndExit(){
		BufferedReader ip = new BufferedReader(new InputStreamReader(System.in));
		try {
			String i = ip.readLine();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		System.exit(0);
	}
}