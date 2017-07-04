package subsetSum;

import java.util.*;
public class ssDP {
	boolean table[][];
	int set[];
	//returns the value needed for sum checking
	int getValues(){
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the number of elements in the set");
		int cardinality = scan.nextInt();
		this.set=new int[cardinality];
		int iterator=0;
		System.out.println("Enter the elements in the set");
		while(iterator<cardinality){
			this.set[iterator++]=scan.nextInt();
		}
		System.out.println("Enter the sum needed");
		int neededSum = scan.nextInt();
		scan.close();
		return neededSum;
	}
	
	//sets 1st row and 1st column
	void initialiseTable(int cardinality,int neededSum){
		this.table=new boolean[cardinality][neededSum];
		int i;
		for(i=0;i<cardinality;i++){
			//for 0 sum, everyone can give. used in line fR CHOOSE in compute
			this.table[i][0]=true;
			//when an element can perfectly subtract a sum, it is true
		}
		for(i=0;i<neededSum;i++){
			if(i==this.set[0]){
				this.table[0][i]=true;
			}
		}
	}
	
	void computeTable(int cardinality,int neededSum){
		int row,col;
		boolean choose=false,notChoose=false;
		for(row=1;row<cardinality;row++){
			for(col=1;col<neededSum;col++){
				notChoose=this.table[row-1][col];
				if(col-this.set[row] >= 0)//HERE USED
					choose=this.table[row-1][col-this.set[row]];
				else
					choose=false;
				if(choose||notChoose ) {
					this.table[row][col]=true;
				}
			}
		}
	}
	
	void printTable(int neededSum,int cardinality){
		int i = cardinality, j=neededSum;
		for(i=0;i<cardinality;i++){
			for(j=0;j<neededSum;j++){
				System.out.print(this.table[i][j] + " - ");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		ssDP ss= new ssDP();
		int neededSum=ss.getValues();
		neededSum++;
		int cardinality = ss.set.length;
		ss.initialiseTable(cardinality,neededSum);
		ss.computeTable(cardinality,neededSum);
		ss.printTable(neededSum, cardinality);
	}

}
