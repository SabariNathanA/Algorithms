package rank;

//import java.util.Random;
import java.util.Scanner;

public class Randomised {

	static int pivot(int[] a,int l,int r){
		int p;
		p=(l+r)/2;
		//p=l;
//		System.out.println("pivot" + p);
		return p;

	}
	
	
	static void print(int[] a,int low,int j){
		int i;
		for( i=low;i<=j;i++){
			System.out.print(a[i] + " ");
		}
		System.out.println(" ");
	}
	
	static void swap(int[]a,int i,int j){
		int temp=a[i];
		a[i]=a[j];
		a[j]=temp;
		
	}
	static int partition(int[] ar, int low, int high) {
		int u=pivot(ar,low,high);
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
		/*for(i=0;i<10;i++){
			System.out.println(a[i]);
		}*/
	}

}
