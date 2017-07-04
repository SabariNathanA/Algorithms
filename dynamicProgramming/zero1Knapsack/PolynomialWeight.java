package zero1Knapsack;

import java.util.Scanner;
import java.util.Arrays;

/*The title of this is POLYNOMIAL because this uses O(nW)
 * of space if weight is polynomial we can develop this algo
 * the table is (first i items,,,,weights)
 * i is from 1-->n
 * weights is from 1--->givenW*/
public class PolynomialWeight {

	int table[][];
	int itemValue[];
	int itemWeight[];
	
	static int max(int a, int b){
		return (a>b)?a:b ;
	}
	
	public PolynomialWeight(int KnapsackWeight,int NumberOfItems){
		this.itemValue = new int[NumberOfItems];
		this.itemWeight = new int[NumberOfItems];
		this.table = new int[NumberOfItems][KnapsackWeight];
	}
	
	void getItemValueWeight(int NumberOfItems){
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the Value<space>Weight");
		for(int i=0;i<NumberOfItems;i++){
			this.itemValue[i] = scan.nextInt();
			this.itemWeight[i] = scan.nextInt();
		}
		scan.close();
	}
	
	void initializeTables(int KnapsackWeight,int NumberOfItems){
		int i;
		for(i=1;i<KnapsackWeight;i++){
			if(this.itemWeight[0] <= i){
				this.table[0][i] = this.itemValue[0];	
			}
			else
				this.table[0][i] = 0;
		}
		/*T(1,j) = V1 if v1< wj
		else 0*/
		for(i=0;i<NumberOfItems;i++){
			this.table[i][0] = 0;
			/*Initialising the leftmost row so that easy computation*/ 
		}
	}
	
	void computeTables(int KnapsackWeight,int NumberOfItems){
		int i,j,cols = KnapsackWeight,rows = NumberOfItems;
		int neededWeightNow,dintChoose,choose;
		for(j=1;j<cols;j++){
			neededWeightNow = j;
			for(i=1;i<rows;i++){
				dintChoose = this.table[i-1][j];//the same value but previous denom
				if(this.itemWeight[i]<=neededWeightNow)
					choose = this.itemValue[i] + this.table[i-1][j-this.itemWeight[i]];
				else
					/*When we cant choose this jth coin, we just put +0*/
					choose = 0; 
				this.table[i][j] = max(choose, dintChoose);
			}
		}
	}
	
	void printTable(int KnapsackWeight,int NumberOfItems){
		int i = NumberOfItems, j=KnapsackWeight;
		System.out.println("Each Row is after including ith element");
		System.out.println("Each column is For Knapsack of weight from 0 to givenW");
		for(i=0;i<NumberOfItems;i++){
			for(j=0;j<KnapsackWeight;j++){
				System.out.printf("%d - ", this.table[i][j]);
			}
			System.out.println();
		}
	}
	
	void backtrack(int weightNeeded,int KnapsackWeight,int NumberOfItems){
		int presentItem = NumberOfItems - 1, predecessorItem;
		System.out.print(weightNeeded + "("+this.table[presentItem][weightNeeded] + ") = ");
		while(weightNeeded>0){
			predecessorItem = presentItem-1;
			if(predecessorItem < 0 || presentItem == 0){
				/* Corner Case when the denom 1 is reached, just keep going up 
				 * without checking */
				System.out.print( this.itemWeight[presentItem] + "(" + this.itemValue[presentItem] + ") + " );
				weightNeeded = weightNeeded - itemWeight[presentItem];
			}
			else if(this.table[presentItem][weightNeeded] == this.table[predecessorItem][weightNeeded]){
				/* Means we did not choose present denomination (just go left)
				 * Value remains same
				 * presentDenom decreases*/
				presentItem--;
			}
			else{
				/* Print the denomination (go UP to some extent)
				 * Value reduces by presentDenom
				 * presentDenom remains same*/
				System.out.print( this.itemWeight[presentItem] +"(" + this.itemValue[presentItem] + ") + " );
				weightNeeded = weightNeeded - this.itemWeight[presentItem];
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter the number of items");
		int NumberOfItems = scan.nextInt();
		
		System.out.println("Enter the Weight of Knapsack");
		int KnapsackWeight = scan.nextInt();
		KnapsackWeight++;//actually here we map to index of array
		PolynomialWeight pw = new PolynomialWeight(KnapsackWeight,NumberOfItems);
		pw.getItemValueWeight(NumberOfItems);
		
		System.out.println("Value Array" + Arrays.toString(pw.itemValue));	
		System.out.println("Weight Array" + Arrays.toString(pw.itemWeight));
		scan.close();
		
		pw.initializeTables(KnapsackWeight,NumberOfItems);
		pw.computeTables(KnapsackWeight,NumberOfItems);
		pw.printTable(KnapsackWeight, NumberOfItems);
		pw.backtrack(7, KnapsackWeight, NumberOfItems);
	}

}
