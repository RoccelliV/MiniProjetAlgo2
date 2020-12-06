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

	public Vertex(Vertex v) {
		this.setColor(v.color);
		incoming = new ArrayList<>();
		for(Edge e : v.incoming)
			incoming.add(new Edge(e));
		outcoming = new ArrayList<>();
		for(Edge e : v.outcoming)
			outcoming.add(new Edge(e));
	}

	public Color getColor() {
		return color;
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
	
	public int getScoreForHeristic2() {
		int score = 0;
		for(Edge e : outcoming) {
			int tmp = e.getDst().getNbRedOutcoming() + e.getDst().getNbBlueIncoming() - e.getDst().getNbRedIncoming() - e.getDst().getNbBlueOutcoming();
			score += e.getColor() == Color.RED ? (tmp*2) : (tmp/2);
		}
		return score;
	}
	
	public int getNbBlueOutcoming() {
		int count = 0;
		for(Edge e : outcoming)
			if(e.getColor() == Color.BLUE)
				count ++;
		return count;
	}
	
	public int getNbRedOutcoming() {
		int count = 0;
		for(Edge e : outcoming)
			if(e.getColor() == Color.RED)
				count ++;
		return count;
	}
	
	public int getNbBlueIncoming() {
		int count = 0;
		for(Edge e : incoming)
			if(e.getColor() == Color.BLUE)
				count ++;
		return count;
	}
	
	public int getNbRedIncoming() {
		int count = 0;
		for(Edge e : incoming)
			if(e.getColor() == Color.RED)
				count ++;
		return count;
	}
}
