/*Can be used for Stock problem*/


package maxGap;
import java.util.Scanner;
public class MaxGapAfterLength {
	int[] a=new int[10];
	int[][] min=new int[10][2];
	
	private void  createMinArray(){//computes an array representing the leftmost minimum
		// value corresponding to every element
		int i, mini=this.a[0];
		this.min[0][0]=mini;
		int minIndex = 0;
		
		for(i=1;i<10;i++){
			if(a[i]<mini){
				this.min[i][0]=this.a[i];
				mini=this.a[i];
				minIndex = i;
				this.min[i][1] = minIndex; //stores the index of the mmin
			}
			else
			{
				this.min[i][1] = minIndex;
				this.min[i][0]=mini;
			}
		}
		
	}
/*Finds the maximum sum such that the day lies after l days.*/
	private void find(int l){
		int j=l,max=-1000000,maxIndex=l,minIndex=0;
		
		for(;j<10;j++){
			/*starts from 0+l to n*/
			if(max<(this.a[j]-this.min[j-l][0])){
				/*compares with the value at min[j-l] which
			contains the value in the original array which has the minimum value.*/
				max=this.a[j]-this.min[j-l][0];
				maxIndex=j;
				minIndex=this.min[j-l][1];
			}
		}
		
		
		System.out.println("For " + l + " max is " + max + " between "+minIndex+" "+maxIndex);
	}
	
	public static void main(String[] args) {
		int i,max=10;
		MaxGapAfterLength mg = new MaxGapAfterLength();
		Scanner scan =new Scanner(System.in);
		
		
		for(i=0;i<max;i++)
		{
			mg.a[i]=scan.nextInt();
		}
		
		
		mg.createMinArray();
		

		for(i=0;i<max;i++)
		{
			System.out.println(mg.min[i][0] +" - "+ mg.min[i][1]);
		}
		
		System.out.println("Enter the number of TestCases");
		int t=scan.nextInt();
		while(t>0){
			int l=scan.nextInt();
			mg.find(l);
			
			t--;
		}
		scan.close();
	}

}
