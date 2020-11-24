package graph;

public class Edge {
	private Color color;
	private Vertex src;
	private Vertex dst;
	
	public Edge(Color color, Vertex src, Vertex dst) {
		this.color = color;
		this.src = src;
		this.dst = dst;
	}

	Color getColor() {
		return color;
	}

	Vertex getSrc() {
		return src;
	}

	Vertex getDst() {
		return dst;
	}

}
