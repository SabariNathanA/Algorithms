package rank;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;


public class SmallestKElementsMedian {

	static long startTime = System.currentTimeMillis();
	static long endTime;
	static int findRank(float[] a,int i,int j,int r){
		int k=partition(a,i,j);
		if(r==(j-k+1)){
			return k;
		}
		else if(r<(j-k+1)){
			return findRank(a,k+1,j,r);
		}
		else{
			return findRank(a,i,k,r-j+k);
		}
			
	}
	
	static void print(float[] a,int low,int j){
		int i;
		for( i=low;i<=j;i++){
			System.out.printf("%.0f ",a[i]);
		}
		System.out.printf("\n %d \n" ,(i));
	}
	static int partition(float[] ar, int low, int high) {
		int u=pivot(ar,low,high);
		swap(ar,low,u);
		float pivot = ar[low];
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

	static void swap(float[]a,int i,int j){
		float temp=a[i];
		a[i]=a[j];
		a[j]=temp;
	}
	
	static int pivot(float[] a,int l,int r){
		int p;
		p=(l+r)/2;
//		System.out.println("pivot" + p);
		return p;

	}

	
	public static void compute(float[] a,int twiceK) throws IOException{
		int medianIndex=twiceK/2,val=twiceK; 
		FileInputStream fis = null;
		DataInputStream dis = null;
		try{
			fis = new FileInputStream("output.txt");
			dis = new DataInputStream(fis);
			while((dis.available()>0) ){
				medianIndex = findRank(a,0,val-1,(val/2));
				val= medianIndex + 1;
				while( (val<twiceK) && (dis.available()>0) ){
					a[val++]=dis.readFloat();
				}
			}
			int temp=val-medianIndex;
			medianIndex=findRank(a,0,val-1,temp);
			endTime = System.currentTimeMillis();
			print(a,0,medianIndex-1);
			
		}catch(IOException e){
			System.out.println("Error" + e.getMessage());
		}
		finally{
			if(fis != null){
				fis.close();
			}
			if(dis != null){
				dis.close();
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException {

		int i,k=10000,twiceK=2*k;
		float[] a=new float[twiceK];
		FileInputStream fis = null;
		DataInputStream dis = null;
//Data read from file		
		try{
			fis = new FileInputStream("output.txt");
			dis = new DataInputStream(fis);
			for(i=0;i<twiceK;i++){
				a[i]=dis.readFloat();
			}
			
		}catch(Exception e){
			System.out.println("Error" + e.getMessage());
		}
		finally{
			if(fis != null){
				fis.close();
			}
			if(dis != null){
				dis.close();
			}
		}
		
		compute(a,twiceK);
		
		System.out.println("Took "+(endTime - startTime) + " ms");
	
	}
}

/*
*
*
STATISTICS
Input Size: 10,000,000
Time Taken: 29s
*
*
***/
