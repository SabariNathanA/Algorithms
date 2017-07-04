package maxsubarray;
/*
 * Given a set of numbers containing numbers from 0 to n in random order
 * Need to find the smallest sub array that has all k numbers where 0<=k<=n
 */

import java.util.Scanner;
public class MinSubAHavingKIntegers {

	public void find(int[] a, int k)
	{
		int i=0,j=0,count=0;
		int n=a.length;
		int[] b=new int[k];
		for(i=0;i<k;i++)
		{
			b[i]=0;
		}
		i=0;
		while(a[i]>k)	i++;	//to find the first occurrence of the number within K elements
		int startIndex=i; //and set the start index
		while(count<k && i<n){
			if(a[i]<=k) {
				if(b[a[i]-1]==0) 
					count++; 
				b[a[i]-1]++;
			}
			i++;
		}
		int endIndex=i-1;//FInd the first set of occurrence of ALL values in k  and set ENDiNDEX
		j=endIndex;
		i=startIndex;
		int minLen=endIndex- startIndex + 1;
		int temp;
		while(j<n && i<n){
			if(a[i]<=k){
				temp=a[i];
				b[temp-1]--;
				if(b[temp-1]==0){
					count--;
					j++;
					while(j< n && a[j]!=temp ) j++;
					if(j<n ){
						count++;
						b[a[j]-1]++;
						if(j-i+1<minLen){
							startIndex=i+1;
							endIndex=j;
							minLen=startIndex-endIndex + 1;
						}
					}
				}
				else{
					startIndex=i+1;
					if((startIndex-endIndex + 1) < minLen)
						minLen=endIndex - startIndex + 1;
				}
			}
			else if(count==k){
				if(j-i+1<minLen){
					startIndex=i+1;
					endIndex=j;
					minLen=endIndex -startIndex + 1;
					
				}
			}
			i++;
		}
	System.out.println("minSubArray is " + minLen + " between "+startIndex+" "+endIndex);
	}
	
	public static void main(String[] args) 
	{
		int k,i;
		int[] a=new int[10];
		System.out.println("Enter the Array elements");
		Scanner scan=new Scanner(System.in);
		for(i=0;i<a.length;i++)
		{
			a[i]=scan.nextInt();
		}
		System.out.println("Enter the Array elements");
		k=scan.nextInt();
		scan.close();
		MinSubAHavingKIntegers m=new MinSubAHavingKIntegers();
		m.find(a, k);
	}

}
