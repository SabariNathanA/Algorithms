package maxsubarray;

public class DivConMSA {

	static Tuple maxCrossSubArray(int[] a, int low,int mid, int high){
		int i=mid,j=mid+1,leftSum=-100,rightSum=-100,thisSum=0;
		Tuple left=new Tuple();
		left.high=mid;
		Tuple right=new Tuple();
		right.low=mid+1;
		for(i=mid;i>=0;i--){
			thisSum=thisSum+a[i];
			if(thisSum>leftSum)
			{
				left.sum=thisSum;
				left.low=i;
			}
		}
		thisSum=0;
		for(j=mid+1;j<=high;j++)
		{
			thisSum=thisSum+a[j];
			if(thisSum>rightSum)
			{
				right.sum=thisSum;
				right.high=j;
			}
		}
		Tuple toReturn=new Tuple();
		toReturn.low=left.low;
		toReturn.high=right.high;
		toReturn.sum=(left.sum + right.sum);
		return toReturn;
	}
	
	static Tuple maxSubArray(int[] a, int low, int high){
		
		Tuple toReturn=new Tuple();
		Tuple left=new Tuple();
		Tuple right=new Tuple();
		Tuple cross=new Tuple();
		
		if(high==(low)){
			toReturn.low=low;
			toReturn.high=high;
			toReturn.sum=a[low];
			//System.out.println("MaxSubCrossArray Val is " + toReturn.sum + " between " + toReturn.low + " and " + toReturn.high);
			return toReturn;
			
		}
		else{
			int mid = (high + low)/2;
			left=maxSubArray(a,low,mid);
//			System.out.println("MaxSubCrossArray Left Val is " + left.sum + " between " + left.low + " and " + left.high);
			right=maxSubArray(a,mid+1,high);
//			System.out.println("MaxSubCrossArray Right Val is " + right.sum + " between " + right.low + " and " + right.high);
			cross=maxCrossSubArray(a,low,mid,high);
//			System.out.println("MaxSubCrossArray Cross Val is " + cross.sum + " between " + cross.low + " and " + cross.high);
			if((left.sum>=right.sum) && (left.sum>=cross.sum))
			{
				toReturn.low=left.low;
				toReturn.high=left.high;
				toReturn.sum=left.sum;
//				System.out.println("MaxSubCrossArray if Left Val is " + toReturn.sum + " between " + toReturn.low + " and " + toReturn.high);
				return toReturn;
			}
			else if((right.sum>=left.sum) && (right.sum>=cross.sum))
			{
				toReturn.low=right.low;
				toReturn.high=right.high;
				toReturn.sum=right.sum;
//				System.out.println("MaxSubCrossArray if Right Val is " + toReturn.sum + " between " + toReturn.low + " and " + toReturn.high);
				return toReturn;
			}
			else
			{
				toReturn.low=cross.low;
				toReturn.high=cross.high;
				toReturn.sum=cross.sum;
//				System.out.println("MaxSubCrossArray if Cross Val is " + toReturn.sum + " between " + toReturn.low + " and " + toReturn.high);
				return toReturn;
			}
		}
	}
	
	public static void main(String[] args) {
		int[] a={-5,2,5,-9,4,-6,1,7,-4,6};
		int low=0,high=9;
		Tuple result=maxSubArray(a, low, high);
		
		
		System.out.println("MaxSubCrossArray Val is " + result.sum + " between " + result.low + " and " + result.high);
		

	}

}
