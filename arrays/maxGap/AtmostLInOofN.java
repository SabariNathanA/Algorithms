package maxGap;

import java.util.Scanner;

public class AtmostLInOofN {

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
	
	private Tuple find(int l){
		int i,hereSum=0,startIndex=0,endIndex=0;
		Tuple toReturn=new Tuple();
		toReturn.high=0;
		toReturn.low=0;
		toReturn.sum=0;
		for(i=0;i<10;i++){
			hereSum += change[i];
			
			/*If the value of sum goes below 0
*/			if(hereSum<0){
				startIndex=i+1;
				endIndex=i+1;
				hereSum=0;
			}

/* TO find the endindex if sum is non zero
*/			else
				endIndex=i;
			

			if( (endIndex - startIndex + 1) > l){
				hereSum -= a[startIndex++];
				while( (a[startIndex]<0) && (startIndex<endIndex) ){
					hereSum -= a[startIndex++];
				}
				
			}

			if(hereSum>toReturn.sum){
				toReturn.low=startIndex;
				toReturn.high=endIndex;
				toReturn.sum=hereSum;
			}

		}
		
		return toReturn;
	}
	
	public static void main(String[] args) {
		int l,i,max=10;
		AtmostLInOofN mg = new AtmostLInOofN();
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
