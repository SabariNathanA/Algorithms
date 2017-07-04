package sumOf2givesAnumber;
import sort.*;
import java.util.Scanner;

class toRet
{
	int left=0;
	int right=0;
	boolean flag=false;
}

public class givenK {

	toRet findXY(int[] a,int k){
		int l=0;
		toRet ret=new toRet();
		int r=a.length-1;
		while(l<r){
			if(a[l]+a[r]==k){
				ret.left=l;
				ret.right=r;
				ret.flag=true;
				break;
			}
			else if(a[l]+a[r]<k)
				l++;
			else
				r--;
		}
		return ret;
	}
	
	public static void main(String[] args) {
		int[] a=new int[10];
		int i,l=0,r=9;
		Scanner scan=new Scanner(System.in);
		for(i=0;i<10;i++)
			{a[i]=scan.nextInt();}
		System.out.println("Enter the K value");
		int k=scan.nextInt();
		scan.close();
		QuickSort q=new QuickSort();
		q.qs(a,l,r);
		System.out.println("Enter the K value");
		givenK obj=new givenK();
		toRet ret=obj.findXY(a, k);
		if(ret.flag)
			System.out.println(ret.left +"" + ret.right);
		else
			System.out.println("No such Element exists");
	}

}
