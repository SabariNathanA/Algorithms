package maxsubarray;

import java.util.Scanner;

public class MinLengthArrayAtleastSum {

	int[] a=new int[10];
	private Tuple find(int start, int end,int sum){
		int j=start,k,tempsum=0;
		int i=j;
		int minLength=end-start+1;
		int hereSum=0;
		
		
		Tuple toReturn;
		toReturn=new Tuple();
		toReturn.low=start;
		toReturn.high=end;
		toReturn.sum=0;
		
		
		while(j<end){
			hereSum=hereSum+this.a[j];
			if(hereSum>=sum){
				k=j;
				while(k>=i){
					tempsum=tempsum+a[k];
					if(tempsum>=sum){
						if(minLength>j-k+1){
							minLength=j-k+1;
							toReturn.low=k;
							toReturn.high=j;
							i=k+1;
							toReturn.sum=tempsum;
						}
					}
					k--;
				}
				
				hereSum=tempsum-a[k+1];
				tempsum=0;
				
			}
			else if(hereSum<0){
				hereSum=0;
				i=j+1;
			}
			j++;
		}
		
		return toReturn;
	}

	public static void main(String[] args) {
		int i,max=0;
		MinLengthArrayAtleastSum mg = new MinLengthArrayAtleastSum();
		Scanner scan =new Scanner(System.in);
		Tuple result;
		
		for(i=0;i<mg.a.length;i++)
		{
			mg.a[i]=scan.nextInt();
		}
		System.out.println("Enter the sum to be fonud");
		max=scan.nextInt();
		scan.close();
		result=mg.find(0, mg.a.length, max);
		System.out.println("minSum is " + result.sum + " between "+result.low+" "+result.high);
	}

}
