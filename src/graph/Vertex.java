package graph;

import java.util.ArrayList;
import java.util.List;

public class Vertex {
	Color color;
	List<Edge> incoming;
	List<Edge> outcoming;

	public Vertex(Color color) {
		this.setColor(color);
		incoming = new ArrayList<>();
		outcoming = new ArrayList<>();
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public void addIncomingEdge(Edge e) {
		incoming.add(e);
	}
	
	public void addOutcomingEdge(Edge e) {
		outcoming.add(e);
	}
	
	public void removeIncomingEdge(Edge e) {
		incoming.remove(e);
	}
	
	public void removeOutcomingEdge(Edge e) {
		outcoming.remove(e);
	}
	
	public void delete() {
		for(Edge e : incoming) {
			e.deleteFromSrc();
		}
		for(Edge e : outcoming) {
			e.colorDst();
			e.deleteFromDst();
		}
		
	}
}
