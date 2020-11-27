package main;

import graph.Graph;

public class Main {
	public static void main(String[] args) {
		Graph g = new Graph();
		g.generateGraph(5, 1.0, 0.5);
		g.print();
	}
}
