package graph;

public class Edge {
	private Color color;
	private Vertex src;
	private Vertex dst;
	
	public Edge(Color color, Vertex src, Vertex dst) {
		this.color = color;
		this.src = src;
		this.dst = dst;
		src.addOutcomingEdge(this);
		dst.addIncomingEdge(this);
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
	
	public void delete() {
		deleteFromSrc();
		deleteFromDst();
	}

	public void deleteFromSrc() {
		src.removeOutcomingEdge(this);
	}
	
	public void deleteFromDst() {
		dst.removeIncomingEdge(this);
	}
	
	public void colorDst() {
		dst.setColor(color);
	}
}
