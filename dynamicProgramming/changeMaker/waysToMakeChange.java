package changeMaker;

import java.util.Scanner;

public class waysToMakeChange {
	int table[][];
	int denom[];
	
	static int sum(int a, int b){
		return (a+b) ;
	}
	
	public waysToMakeChange(int ChangeNeededFor,int NumberOfCoins){
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
			//V(i,1) = i
			/*We know the first denom is Re.1, so
			all row of column 1 will be 1 because only 1 way with Re.1*/
			this.table[i++][0] = 1; 
		}
		while(j<cols){
			/* T()0,j) = 1
			 * 0rs can be given in 1 way*/
			this.table[0][j++] = 1;
		}
		//its okay to start at 2nd denom so j=1
		for(j=1;j<cols;j++){
			denomination = this.denom[j];
			for(i=0;i<rows;i++){
				dintChoose = this.table[i][j-1];//the same value but previous denom
				if(i>=denomination)
				//i has the value because we initialised ChangeNeeded from 0
					choose = this.table[i-denomination][j];
				else
					/*when we can't choose a value with jth denom, we just put 0 ways	
*/					choose = 0;
				this.table[i][j] = sum(choose, dintChoose);
			}
		}
	}
	
	void printTable(int ChangeNeededFor,int NumberOfCoins){
		int i = ChangeNeededFor, j=NumberOfCoins;
		for(i=0;i<ChangeNeededFor;i++){
			System.out.print(i + " - ");
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
		waysToMakeChange  ce = new waysToMakeChange(ChangeNeededFor, NumberOfCoins);
		
		ce.getDenoms(NumberOfCoins);
		ce.tableGenerator(ChangeNeededFor, NumberOfCoins);
		ce.printTable(ChangeNeededFor, NumberOfCoins);
		scan.close();
	}

}
