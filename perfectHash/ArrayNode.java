package perfectHash;

public class ArrayNode {
	public int count;
	public int[] bucket = null;
	public int a;
	public int b;
	public int p;
	public ArrayNode( int sizeOfArray ){
		bucket = new int[sizeOfArray];
	}

}
