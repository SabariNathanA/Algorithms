package sort;

import java.util.Scanner;

public class InversionPoints {
	int[] a=new int[10];
	int count=0;
	void MSort(int a[],int l,int r)
	{
		int k;
		if(l<r)
			{
			k=(l+r)/2;
			this.MSort(a, l, k);
			this.MSort(a, k+1, r);
			this.Merge(a,l,r,k);
			}
		
		
	}

	void Merge(int a[],int l,int r,int k)
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
				this.count=this.count + k -i + 1;
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
		int l=0,r=4,i;
		InversionPoints cms = new InversionPoints();
		Scanner scan=new Scanner(System.in);
		for(i=0;i<5;i++)
			{cms.a[i]=scan.nextInt();
			}
		cms.MSort(cms.a,l,r);
		scan.close();
		System.out.println(cms.count);

	}

}
