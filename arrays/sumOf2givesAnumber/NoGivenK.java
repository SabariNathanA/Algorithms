package sumOf2givesAnumber;

import java.util.Scanner;

import sort.QuickSort;

public class NoGivenK {

	public static void main(String[] args) {
		int[] a=new int[10];
		int i,l=0,r=9;
		Scanner scan=new Scanner(System.in);
		for(i=0;i<10;i++)
			{a[i]=scan.nextInt();}
		scan.close();
		QuickSort q=new QuickSort();
		q.qs(a,l,r);
		givenK obj=new givenK();
		for(i=0;i<10;i++){
			toRet ret=obj.findXY(a, a[i]);
			if(ret.flag)
				System.out.println("For " + a[i] + " = " + a[ret.left] +" + " + a[ret.right]);
		}

	}

}
