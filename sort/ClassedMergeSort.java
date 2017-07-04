package sort;
import java.util.Scanner;

public class ClassedMergeSort {

	int[] a=new int[10];
	
	static void MSort(int a[],int l,int r)
	{
		int k,i=0;
		if(l<r)
			{
			k=(l+r)/2;
			
			MSort(a, l, k);
			for(i=l;i<=k;i++)
			System.out.print(a[i] + " ");
			System.out.print("\t");
			MSort(a, k+1, r);
			for(i=k+1;i<=r;i++)
				System.out.print(a[i] + " ");
			System.out.print("\n going to merge \n");
			Merge(a,l,r,k);
			for(i=l;i<=r;i++)
				System.out.print(a[i] + " ");
			System.out.println();
			}
		
		
	}

	static void Merge(int a[],int l,int r,int k)
	{
		int i,j,index=0;
		int b[]=new int[r-l+1];
		i=l; j=k+1; 
		while(i<=k && j<=r){
			if(a[i]<a[j]){
				b[index]=a[i];
				index++;
				i++;
			}
			else if(a[i]>a[j]){
				b[index]=a[j];
				index++;
				j++;
			}
		}
		while(i<=k){
			b[index]=a[i];
			index++;
			i++;
		}
		while(j<=r){
			b[index]=a[j];
			index++;
			j++;
		}
		index=0;
		i=l;
		while(i<=r)
		{
			a[i]=b[index];
			i++;
			index++;
		}
	}

	public static void main(String[] args) {
		int l=0,r=9,i;
		ClassedMergeSort cms = new ClassedMergeSort();
		Scanner scan=new Scanner(System.in);
		for(i=0;i<10;i++)
			{cms.a[i]=scan.nextInt();
			//System.out.println(a[i]);
			}
		MSort(cms.a,l,r);
		scan.close();
		for(i=0;i<10;i++)
			System.out.println(cms.a[i]);


	}

}
