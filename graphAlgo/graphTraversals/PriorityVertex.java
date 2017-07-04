package graphTraversals;

public class PriorityVertex {
	int vertex;
	int priority;// the distance from source node the summation of prev weighs
	public PriorityVertex(int v, int w){
		this.vertex = v;
		this.priority = w;
	}
	public PriorityVertex(){}
	public void setter(int v, int w){
		this.vertex = v;
		this.priority = w;
	}
}
