package sort;


/**
 * 
 */

/**
 * @author sabari
 *
 */
public class QuickSort {

	/**
	 * @param args
	 */
	static int pivot(int[] a,int l,int r){
		int p;
		p=l+(r-l)/2;
		//p=l;
		//System.out.println("pivot" + p);
		return p;

	}
	
	/*static int partition(int[] a,int l,int r,int p){
		int i=l,j=r;
		System.out.println("in partition" + l + r);
		int temp;
		while(i<=j){
			System.out.println("1");
			while(a[i]<=a[p] &&(i<r) ){i++;System.out.println("2");}
			while(a[j]>a[p]&&(j>=l)  ){j--;System.out.println("3");}
			if(i<j){
				temp=a[i];
				a[i]=a[j];
				a[j]=temp;
				i++;
				j--;
				System.out.println("4");
			}
			
		}
		System.out.println("k" + j);
		return j;
	}*/
	static void swap(int[]a,int i,int j){
		int temp=a[i];
		a[i]=a[j];
		a[j]=temp;
		
	}
	static int partition(int[] ar, int low, int high) {
		int u=pivot(ar,low,high);
		 //first element as pivot
		swap(ar,low,u);
		int pivot = ar[low];
		int i = low + 1;
		for(int j= low+1;j<=high;j++){
			if(ar[j] < pivot){
				swap(ar,i,j);
				i++;
			}
		}
	swap(ar, low, i-1);
	return i-1;
	}
	
	public void qs(int[] a,int l,int r){
		if(l<r){
		//int p=pivot(a,l,r);
		//int k=partition(a,l,r,p);
		int k=partition(a,l,r);
		qs(a,l,k);
		qs(a,k+1,r);	
		}
	}
	
	
	/*public static void main(String[] args) {
		int[] a=new int[10];
		int i,l=0,r=9;
		Scanner scan=new Scanner(System.in);
		for(i=0;i<10;i++)
			{a[i]=scan.nextInt();}
		scan.close();
		qs(a,l,r);
		for(i=0;i<10;i++)
			System.out.println("** " + a[i]);

	}*/

}
