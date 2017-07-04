package rank;

import java.util.Scanner;

public class Deterministic {

	static void swap(int[]a,int i,int j){
		int temp=a[i];
		a[i]=a[j];
		a[j]=temp;
		
	}
	
	static void print(int[] a,int low,int j){
		int i;
		for( i=low;i<=j;i++){
			System.out.print(a[i] + " ");
		}
		System.out.println(" ");
	}
	static int selectionSort3(int[] a, int start)
	{
		int in=start * 5;
		int i,j,min,minindex;
		for(i=0;i<3;i++)
		{		
			minindex=in+i;
			for(j=in+i;j<(in+5);j++)
			{
				if(a[j]<=a[minindex]){
					minindex=j;
				}
			}
			min=a[minindex];
			a[minindex]=a[in+i];
			a[in+i]=min;
		}
		return in+2;
	}
	
	
	static int goodPivot(int[] a, int i, int j){
		int n=j-i+1;
		int iterator=0;
		int group=n/5;
		if((group)>0)
		{
			while(iterator<group)
			{
				//a[iterator]=selectionSort3(a,i);
				swap(a,iterator,selectionSort3(a,iterator));
				iterator++;
			}
			
				//a[iterator]=findMid(a,(group*5),(n%5));
			//swap(a,iterator,findMid(a,(group*5),(n%5)));
			
			return findRank(a, i, group, group/10);
			//group/10 because there are 
		}
		else if((group%5)>1){
			return findMid(a,0,(n%5));
		}
		else
			return i;
		
		
	}
	
	static int findMid(int[] a, int start, int total) {
		int mid=total/2;
		mid=start + mid;
		return mid;
	}
	
	static int partition(int[] ar, int low, int high) {
		int u=goodPivot(ar,low,high);
		 //first element as pivot
		/*print(ar,low,high);*/
		swap(ar,low,u);
		/*print(ar,low,high);*/
		int pivot = ar[low];
		int i = low + 1;
		for(int j= low+1;j<=high;j++){
			if(ar[j] < pivot){
				swap(ar,i,j);
				i++;
				}
			}
		/*print(ar,low,high);*/
		swap(ar, low, i-1);
		/*print(ar,low,high);*/
		return i-1;
	}
	
	
	static int findRank(int[] a,int i,int j,int r){
		if(i<=j){
		int k=partition(a,i,j);
		if(r==(j-k+1)){//if(r==(j-k+1)){
//			System.out.println("last" + k);
			return k;
		}
		else if(r<(j-k+1))//else if(r<(j-k+1))the value is greater(better) than rank needed, search in the left sub array
		{
//			System.out.println("somewhere inside when r " + r + " is greater than" + k);
			return findRank(a,k+1,j,r);
			//return findRank(a,i,k-1,r-j+k+1);
		}
		else{
			
//			System.out.println("somewhere inside when r " + r + " is less than" + k + (j-r+1));
			//return findRank(a,k+1,j,r);
			return findRank(a,i,k,r-j+k);
		}
			
	}
		return j;
	
	
	}	
	public static void main(String[] args) {
		int[] a=new int[10];
		int i,l=0,r=9;
		Scanner scan=new Scanner(System.in);
		for(i=0;i<10;i++)
			{a[i]=scan.nextInt();}
		scan.close();
		int rank_to_be_found=5;//get input rank here
		
		int n=findRank(a, l, r, rank_to_be_found);
		System.out.println(a[n]);

	}

}
