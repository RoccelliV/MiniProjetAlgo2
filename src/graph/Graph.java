package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
	private Map<Vertex, List<Vertex>> adjacencyMap;
	
	public Graph() {
		adjacencyMap = new HashMap<>();
	}
	
	public void addVertex(Color color) {
		Vertex newVertex = new Vertex(color);
		adjacencyMap.put(newVertex, new ArrayList<Vertex>());
		for(Vertex v : adjacencyMap.keySet()) {
			addEdge(newVertex, v);
		}
	}

	private void addEdge(Vertex newVertex, Vertex v) {
		adjacencyMap.get(newVertex).add(v);
		adjacencyMap.get(v).add(newVertex);
	}
	
	public void removeVertex(Vertex v) {
		for(List<Vertex> liste : adjacencyMap.values())
			liste.remove(v);
		adjacencyMap.remove(v);
	}
}
