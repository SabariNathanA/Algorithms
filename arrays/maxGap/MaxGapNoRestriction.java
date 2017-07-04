package maxGap;

import java.util.Scanner;

/*
 * this is about finding the maximum difference between any i & j in array
 * Only restriction is i!=j
 */
public class MaxGapNoRestriction {

	public static void main(String[] args) {
		int[] a=new int[10];
		int i;
		Scanner scan=new Scanner(System.in);
		for(i=0;i<10;i++)
			{a[i]=scan.nextInt();}
		scan.close();
		int min=0;
		int max=0;
		for(i=1;i<10;i++){
			if(a[i]>a[max]) max=i;
			else if(a[i]<a[min]) min = i;
		}
		System.out.println(max + " - " + min + " = " + (a[max]-a[min]));
	}

}
