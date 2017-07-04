#include<stdio.h>
#include<string.h>

int N=5;
int a[5];
void bubblesort()
{
	int i,j,temp;
	for (i=0;i<N;i++)
	{
		for(j=0;j<N-1-i;j++)
		{
			if( a[j]>a[j+1] )	
			{	
				temp=a[i];
				a[i]=a[j];
				a[j]=temp;
			}
		}
	}
}


void insertionsort()
{
	int i,j,key;
	for(i=1;i<N;i++)
	{
		key=a[i];
		j=i-1;
		while((a[j]>key) && (j>=0))
		{
			a[j+1]=a[j]; j--;
		}
		a[j+1]=key;
	}
}


void selectionsort()
{
	int i,j,min,minindex;
	for(i=0;i<N;i++)
	{		
		minindex=i;
		for(j=i;j<N;j++)
		{
			if(a[j]<=a[minindex]){
				minindex=j;
			}
		}
		min=a[minindex];
		a[minindex]=a[i];
		a[i]=min;
	}
	printf("--%d--",a[i-1]);
}


void main()
{
	int i;
	for(i=0;i<N;i++)
		scanf("%d",&a[i]);
//	for(i=0;i<N;i++)
//		printf("%d\n",a[i]);
	selectionsort();
	for(i=0;i<N;i++)
		printf("%d\n",a[i]);
}
