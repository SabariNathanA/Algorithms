package changeMaker;


import java.util.Scanner;

/* CoinExchange problem we create an array column matrix 
 * with change needed for as the rows and the denom as col*/ 
public class CoinExchange {
	
	int table[][];
	int denom[];
	
	static int min(int a, int b){
		return (a<b)?a:b ;
	}
	
	CoinExchange(int ChangeNeededFor,int NumberOfCoins){
		this.table = new int[ChangeNeededFor][NumberOfCoins];
		this.denom = new int[NumberOfCoins];
	}
	
	void getDenoms(int NumberOfCoins){
		Scanner scans = new Scanner(System.in);
		int iterator=0;
		System.out.println("Enter the  denom in ascending order");
		while(iterator<NumberOfCoins){
			this.denom[iterator] = scans.nextInt();
			iterator++;
		}
		scans.close();
	}
	
	void tableGenerator(int ChangeNeededFor,int NumberOfCoins){
		int rows = ChangeNeededFor, cols = NumberOfCoins;
		int i=0,j=0;
		int denomination, choose,dintChoose;
		/*i is row iterator and j is column iterator*/
		while(i<rows){
			/*We know the first denom is Re.1, so
			copy value of change needed to all row of column 1*/
			this.table[i][0] = i; //V(i,1) = i
			i++;
			/*No Need to set the row 0 as 0 since it is default V(0,j) = 0*/
		}
		//its okay to start at 2nd denom so j=1
		for(j=1;j<cols;j++){
			denomination = this.denom[j];
			for(i=0;i<rows;i++){
				dintChoose = this.table[i][j-1];//the same value but previous denom
				if(i>=denomination)
				//i has the value because we initialised ChangeNeeded from 0
					choose = 1 + this.table[i-denomination][j];
				else
					/*When we cant choose this jth coin, we just put +infinty*/
					choose = 10000; 
				this.table[i][j] = min(choose, dintChoose);
			}
		}
	}
	
	void backtrack(int value,int ChangeNeededFor,int NumberOfCoins){
		int presentDenom = NumberOfCoins - 1, predecessorDenom;
		System.out.print(value + " = ");
		while(value>0){
			predecessorDenom = presentDenom-1;
			if(predecessorDenom < 0 || presentDenom == 0){
				/* Corner Case when the denom 1 is reached, just keep going up 
				 * without checking */
				System .out.print( denom[presentDenom] + " + " );
				value = value - denom[presentDenom];
			}
			else if(this.table[value][presentDenom] == this.table[value][predecessorDenom]){
				/* Means we did not choose present denomination (just go left)
				 * Value remains same
				 * presentDenom decreases*/
				presentDenom--;
			}
			else{
				/* Print the denomination (go UP to some extent)
				 * Value reduces by presentDenom
				 * presentDenom remains same*/
				System.out.print( denom[presentDenom] + " + " );
				value = value - denom[presentDenom];
			}
		}
	}
	
	void printTable(int ChangeNeededFor,int NumberOfCoins){
		int i = ChangeNeededFor, j=NumberOfCoins;
		for(i=0;i<ChangeNeededFor;i++){
			for(j=0;j<NumberOfCoins;j++){
				System.out.printf("%d -", this.table[i][j]);
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter the number of denominations");
		int NumberOfCoins = scan.nextInt();
		
		System.out.println("Enter the number of maximum value of change needed");
		int ChangeNeededFor = scan.nextInt();
		
		
		ChangeNeededFor++;
		CoinExchange ce = new CoinExchange(ChangeNeededFor, NumberOfCoins);
		
		ce.getDenoms(NumberOfCoins);
		ce.tableGenerator(ChangeNeededFor, NumberOfCoins);
		ce.printTable(ChangeNeededFor, NumberOfCoins);
		int valueToBeChanged = 12;
		ce.backtrack(valueToBeChanged, ChangeNeededFor, NumberOfCoins);
		scan.close();
	}

}
