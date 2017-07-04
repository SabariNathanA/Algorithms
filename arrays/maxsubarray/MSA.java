package maxsubarray;

import java.util.Scanner;

/*Kadine MSA*/
/*do not kadine right unless you desired  need them
*/public class MSA {

	int[] a=new int[10];
	private Tuple KadineRight(int start/*start is logically at the right side*/, int end,int max)
	{
		int j=start;
		int i=j;
		int hereMax=0;
		Tuple toReturn;
		toReturn=new Tuple();
		toReturn.high=start;
		toReturn.low=end;
		toReturn.sum=0;
		
		while(j>=end){
			hereMax=hereMax+this.a[j];
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
	
	private Tuple KadineLeft(int start/*start is logically at the right side*/, int end,int max)
	{
		int j=start;
		int i=j;
		int hereMax=0;
		Tuple toReturn;
		toReturn=new Tuple();
		toReturn.low=start;
		toReturn.high=end;
		toReturn.sum=0;
		
		while(j<end){
			hereMax=hereMax+this.a[j];
			if(hereMax>max){
				toReturn.sum=hereMax;
				/*toReturn.high=i;
				toReturn.low=j;*/
			}
			else if(hereMax<0){
				hereMax=0;
				i=j+1;
				toReturn.low=i;
			}
			j++;
		}
		
		return toReturn;
	}
	public static void main(String[] args) {
		int i,max=0;
		MSA mg = new MSA();
		Scanner scan =new Scanner(System.in);
		Tuple result;
		
		for(i=0;i<mg.a.length;i++)
		{
			mg.a[i]=scan.nextInt();
		}
		scan.close();
		result=mg.KadineRight(mg.a.length-1,0, max);
		System.out.println("max is " + result.sum + " between "+result.low+" "+result.high);
		result=mg.KadineLeft(0,mg.a.length, max);
		System.out.println("max is " + result.sum + " between "+result.low+" "+result.high);

	}

}
