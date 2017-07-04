/*Can be used for Stock problem*/
/* Its not in Order of N
*/

package maxGap;

/* this method  uses Kadine in reverse */

import java.util.Scanner;

public class MaxGapWithinLength {

	int[] a=new int[10];
	int[] change=new int[10];
	
	
	private void changeArrayCreator(){
		int n=this.a.length;
		
		int i;
		this.change[0]=this.a[0];
		for(i=1;i<n;i++){
			this.change[i]=this.a[i]-this.a[i-1];
		}
	}
	
	private Tuple Kadine(int start/*start is logically at the right side*/, int end,int max)
	{
		int j=start;
		int i=j;
		int hereMax=0;
		Tuple toReturn;
		toReturn=new Tuple();
		toReturn.high=start;
		toReturn.low=end;
		toReturn.sum=0;
		
		while(j>end){
			hereMax=hereMax+this.change[j];
			if(hereMax>max){
				toReturn.sum=hereMax;
				
			}
			else if(hereMax<0){
				hereMax=0;
				i=j-1;
				toReturn.high=i;
			}
			j--;
		}
		
		return toReturn;
	}
	
	
	private Tuple find(int l){
		Tuple toReturn,temp;
		toReturn=new Tuple();
		toReturn.high=0;
		toReturn.low=0;
		toReturn.sum=0;
		
		int j=l,max=this.change[0];
/*		start from l so that the kadine also starts from high  to 0th element So as to 
 * include the 0th element */
		for(;j<10;j++){
			temp=this.Kadine(j,j-l,max);
			if(temp.sum>max){
				toReturn=temp;
				max=temp.sum;
			}
		}
		return toReturn;
	}
	
	public static void main(String[] args) {
		int l,i,max=10;
		MaxGapWithinLength mg = new MaxGapWithinLength();
		Scanner scan =new Scanner(System.in);
		Tuple result;
		
		for(i=0;i<max;i++)
		{
			mg.a[i]=scan.nextInt();
		}
		
		mg.changeArrayCreator();
		
		for(i=0;i<10;i++)
		{
			System.out.println(mg.change[i]);
		}
		
		System.out.println("Enter the number of TestCases");
		
		int t=scan.nextInt();
		while(t>0){
			l=scan.nextInt();
			result=mg.find(l);
			System.out.println("For " + l + " max is " + result.sum + " between "+result.low+" "+result.high);
			
			t--;
		}
		scan.close();


	}

}
