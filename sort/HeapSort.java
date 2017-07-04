package sort;


public class HeapSort {

	static void swap(int[]a,int i,int j){
		int temp=a[i];
		a[i]=a[j];
		a[j]=temp;
		
	}
	
	static int parent(int x){
		return (x-1)/2;
	}
	
	static int leftChild(int x){
		return ((2 * x) + 1);
	}
	
	static int rightChild(int x){
		return ((2 * x) + 2);
	}
	
	public static void bottomUpHeapify(int[] a,int index,int endIndex){
		int leftChild=leftChild(index);
		int rightChild=rightChild(index);
		int largest=index;
		if(leftChild<endIndex) 
			largest=(a[index]>a[leftChild])?index:leftChild;
		if(rightChild<endIndex) 
			largest=(a[largest]>a[rightChild])?largest:rightChild;
		if(largest!=index){
			swap(a,index,largest);
			bottomUpHeapify(a,largest,endIndex);
		}
	}
	
	public static void buildMaxHeap(int[] a){
		int n=a.length;
		int i=(n/2);
		for(;i>=0;i--){
			bottomUpHeapify(a,i,n);
		}
	}
	
	public static void heapSort(int[] a){
		int n=a.length;
		int i;
		buildMaxHeap(a);
		for(i=n-1;i>0;i--){
			swap(a,0,i);
			bottomUpHeapify(a,0,i);
		}
	}
	
	public static void main(String[] args) {
		int[] a={100,6,5,8,2,1,12,3,9,7,4,110,102,104,105,109,201,200,0};
		heapSort(a);
		int i=0;
		for(;i<a.length;i++)
			System.out.println(a[i]);
	}

}
