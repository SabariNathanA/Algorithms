package lcs;

import java.util.*;

public class LCSdp {
	
	String a;
	String b;
	int table[][];
	static Scanner scan;
	
	static int max(int a, int b){
		return (a>b)?a:b ;
	}

	void initialiseTable(int alength,int blength){
		this.table = new int[alength][blength];
		/*init the t(i,1)*/
		int rows,cols;
		String firstInA = ("" + this.a.charAt(0)),hereString;
		String firstInB = ("" + this.b.charAt(0));
		for(rows = 0;rows<alength;rows++){
			hereString = this.a.substring(0, (rows+1));
			if(hereString.contains(firstInB)){
				this.table[rows][0] = 1;
			}
		}
		for(cols = 0;cols<blength;cols++){
			hereString = this.b.substring(0, (cols+1));
			if(hereString.contains(firstInA)){
				this.table[0][cols] = 1;
			}
		}
	}
	
	void computeTable(int alength,int blength){
		int i,j,cols = blength,rows = alength;
		int topLeft,top,left;
		for(i=1;i<cols;i++){
			for(j=1;j<rows;j++){
				topLeft = this.table[j-1][i-1];
				top = this.table[j-1][i];
				left = this.table[j][i-1];
				if(a.charAt(j)==b.charAt(i)){
					this.table[j][i] = 1 + topLeft;
				}
				else
					this.table[j][i] = max(top,left);
			}
		}
	}
	
	void printTable(int alength,int blength){
		int i = alength, j=alength;
		System.out.println(this.b);
		for(i=0;i<alength;i++){
			System.out.printf("%c - ", this.a.charAt(i));
			for(j=0;j<blength;j++){
				System.out.printf("%d - ", this.table[i][j]);
			}
			System.out.println();
		}
	}
	
	String backtrack(int alength,int blength){
		String toReturn = "";
		int hereVal;
		int left;
		int rows=(alength -1),cols=(blength - 1);
		hereVal = this.table[rows][cols];
		while((rows > 0) && (cols > 0) && (hereVal > 0)){
			left = this.table[rows][cols-1];
//			topLeft = this.table[rows-1][cols-1];
			if(this.a.charAt(rows)==this.b.charAt(cols)){
				toReturn = this.a.charAt(rows) + toReturn;
				rows--;
				cols--;//to go to topleft value
			}
			else if(hereVal == left){
				cols--;//go left
			}
			else
				rows--;//go up
			hereVal = this.table[rows][cols];
		}//still we are in the 1st row or 1st col, we need to find zero
		if((rows == 0) && (hereVal != 0)){
			toReturn = this.a.charAt(rows) + toReturn;
		}
		else if((cols == 0) && (hereVal != 0)){
			toReturn = this.b.charAt(cols) + toReturn;
		}
		return toReturn;
	}
	
	public static void main(String[] args) {
		LCSdp lp = new LCSdp();
		scan = new Scanner(System.in);
		System.out.println("Enter the two Strings");
		lp.a = (scan.nextLine()).trim();
		lp.b = (scan.nextLine()).trim();
		int alength = lp.a.length(),blength = lp.b.length();
		lp.initialiseTable(alength,blength);
		
		lp.computeTable(alength, blength);
		lp.printTable(alength, blength);
		System.out.println(lp.backtrack(alength, blength));
		scan.close();
	}

}
