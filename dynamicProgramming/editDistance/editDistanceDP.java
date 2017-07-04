package editDistance;

import java.util.*;

/*
 * We convert the 'toConvert' string to 'given' string
 */

public class editDistanceDP {
	
	int[][] table;
	static int add = 3;
	static int delete = 5;
	static int replace = 7;
	static int infinity = 100000; 
	static String given;
	static String toConvert;
	
	static int min3Values(int a,int b,int c){
		if(a<b)
			if(a<c)
				return a;
			else
				return c;
		else
			if(b<c)
				return b;
			else
				return c;
	}
	
	void initialiseTables(int alength, int blength){
		this.table = new int[alength][blength];
		/* We need to set the first row as 0 because we always want to insert new element
		 * than in the A string because A length is constant we need to add the Bstring to it
		 * */
		int i;
		for(i=0;i<blength;i++){
			this.table[0][i] = 0;
		}
		/*
		 Here the length of B to which we convert is just 1 variable, so we just need deletion
		 * just put infinity to the left so that no possibility of insert happens
		 */ 
		for(i=1;i<alength;i++){
			this.table[i][0] = infinity;
		}
	}
	
	void computeTables(int alength,int blength){
		int row=1,col=1,onAdd, onDelete,onReplace;
		for(row=0;row<alength;row++){
			for(col=0;col<blength;col++){
				if(row==0){
					//only thing we can do it delete the Bstring(toconvert) to get 0-Astring
					this.table[row][col] = col * delete;
				}
				else if(col==0){
					//we need to add the Astring to 0-Bstring to obtain Astring
					this.table[row][col] = row * add;					
				}
					
				else if(given.charAt(row-1) == toConvert.charAt(col-1)){
					this.table[row][col] = this.table[row-1][col-1];
				}
				else{
					onAdd = this.table[row-1][col] + add;
					onDelete = this.table[row][col-1] + delete;
					onReplace = this.table[row-1][col-1] + replace;
					this.table[row][col] = min3Values(onAdd,onDelete,onReplace);
				}
			}
		}
	}
	
	void printTable(int alength,int blength){
		int i = alength, j=alength;
		System.out.println(toConvert);
		for(i=0;i<alength;i++){
			if(i>0)
				System.out.printf("%c - ", given.charAt(i-1));
			for(j=0;j<blength;j++){
				System.out.printf("%d - ", this.table[i][j]);
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		System.out.println("Enter the 2 strings");
		Scanner scan = new Scanner(System.in);
		
		given = (scan.next()).trim();
		toConvert = (scan.next()).trim();
		int alength = given.length(),blength = toConvert.length();
		alength++;
		blength++;
		editDistanceDP dp = new editDistanceDP();
		dp.initialiseTables(alength, blength);
		dp.computeTables(alength,blength);
		dp.printTable(alength, blength);
		scan.close();
	}

}
