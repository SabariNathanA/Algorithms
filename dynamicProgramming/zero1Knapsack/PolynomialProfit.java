package zero1Knapsack;
/* Since W is not polynomial. Profits are the maximum
 * We have keep calculating minimum weight Knapsack required to make 
 * that amount of profit. We can't make more profit than the addition of all items
 * wil amount to profit and weight. Any Knapsack more weight than that can not change 
 * it. 
 * Also we can get only the minimum guaranteed profit, not exact profit
 * 1 2
 * 5 3
 * 4 6
 * 2 1
 * 1 1
 * Try for weight 8
 * Atleast 8 value we can get 
 * */

import java.util.Scanner;

public class PolynomialProfit {
	
	int table[][];
	int itemValue[];
	int itemWeight[];
	static int infinity = 10000;
	static Scanner scan;
	
	static int min(int a, int b){
		return (a<b)?a:b ;
	}
	
	int getItemValueWeight(int NumberOfItems){
		int profit=0;
		this.itemValue = new int[NumberOfItems];
		this.itemWeight = new int[NumberOfItems];
		
		System.out.println("Enter the Value<space>Weight");
		for(int i=0;i<NumberOfItems;i++){
			this.itemValue[i] = scan.nextInt();
			this.itemWeight[i] = scan.nextInt();
			profit = profit + this.itemValue[i];
		}
		return profit;
	}
	
	void initializeTables(int MaxProfit,int NumberOfItems){
		int i;
		this.table = new int[NumberOfItems][MaxProfit];
		
		for(i=1;i<MaxProfit;i++){
			if(this.itemValue[0]==i){
				this.table[0][i] = this.itemWeight[0];
			}
			else
				this.table[0][i] = infinity;
		}
		
		for(i=1;i<NumberOfItems;i++){
			if(this.itemValue[i]>=1){
				this.table[i][1] = min(this.table[i-1][1],this.itemWeight[i]);
			}
			else
				this.table[i][1] = this.table[i-1][1];
		}
		/*T(i,1) = w1 if v1 <= i
		else infinity*/
	}
	
	void printTable(int MaxProfit,int NumberOfItems){
		int i = NumberOfItems, j=MaxProfit;
		System.out.println("Each Row is after including ith element");
		System.out.println("Each column is For Knapsack of weight from 0 to givenW");
		for(i=0;i<NumberOfItems;i++){
			for(j=0;j<MaxProfit;j++){
				System.out.printf("%d - ", this.table[i][j]);
			}
			System.out.println();
		}
	}
	
	void computeTable(int MaxProfit,int NumberOfItems){
		int i,j,cols = MaxProfit,rows = NumberOfItems;
		int neededProfit,dintChoose,choose;
		for(j=2;j<cols;j++){
			neededProfit = j;
			for(i=1;i<rows;i++){
				dintChoose = this.table[i-1][j];//the same value but previous denom
				if(this.itemValue[i]<=neededProfit)
					choose = this.itemWeight[i] + this.table[i-1][j-this.itemValue[i]];
				else
					/*When we cant choose this jth coin, we just put +0*/
					choose = infinity; 
				this.table[i][j] = min(choose, dintChoose);
			}
		}

	}
	
	int backtrack(int KnapsackWeight,int MaxProfit,int NumberOfItems){
		int presentWeight = this.table[NumberOfItems-1][MaxProfit - 1];
		int i = MaxProfit-2;
		while(KnapsackWeight < presentWeight){
			presentWeight = this.table[NumberOfItems-1][i--];
		}
		return i;
		
	}
	
	public static void main(String[] args) {
		
		scan = new Scanner(System.in);
		PolynomialProfit pp = new PolynomialProfit();
		
		System.out.println("Enter the number of items");
		int NumberOfItems = scan.nextInt();
		int MaxProfit = pp.getItemValueWeight(NumberOfItems);
		MaxProfit = MaxProfit +1;
		
		pp.initializeTables(MaxProfit,NumberOfItems);
		pp.computeTable(MaxProfit,NumberOfItems);
		pp.printTable(MaxProfit, NumberOfItems);
		
		System.out.println("Enter the Weight of Knapsack");
		int KnapsackWeight = scan.nextInt();
		int neededMinWeight = pp.backtrack(KnapsackWeight,MaxProfit,NumberOfItems);
		System.out.println(neededMinWeight);
		scan.close();
	}

}
