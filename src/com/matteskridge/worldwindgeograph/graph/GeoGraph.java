package com.matteskridge.worldwindgeograph.graph;

import com.matteskridge.worldwindgeograph.utils.DataFileManager;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Matt Eskridge
 * @created 10/20/14
 */
public class GeoGraph {

	private ArrayList<GeoGraphNode> nodes;
	private HashMap<GeoGraphNode, Dijkstra.DijkstraResult> shortestPaths;

	public GeoGraph(String fileName) {
		readDataFromFile(fileName);
		computeShortestPaths();
		printDebug();
	}

	/**
	 * This loads data from the data file, which is bundled in the
	 * JAR or in the project source folder.
	 */
	private void readDataFromFile(String fileName) {
		String data = DataFileManager.getFile(fileName);
		GraphDataReader reader = new GraphDataReader(data);
		nodes = reader.getNodes();
	}

	private void computeShortestPaths() {
		shortestPaths = new HashMap<GeoGraphNode, Dijkstra.DijkstraResult>();
		for (GeoGraphNode node : nodes) {
			shortestPaths.put(node, Dijkstra.getShortestPaths(this, node));
		}
	}

	public ArrayList<GeoGraphNode> getImportantNodes() {
		ArrayList<GeoGraphNode> important = new ArrayList<GeoGraphNode>();

		for (GeoGraphNode node: nodes) {
			if (node.isImportant()) {
				important.add(node);
			}
		}

		return important;
	}

	public GraphRoute getRoute(GeoGraphNode source, GeoGraphNode dest) {
		return new GraphRoute(this, source, dest);
	}

	public ArrayList<GeoGraphNode> getNodes() {
		return nodes;
	}

	private void printDebug() {

		for (GeoGraphNode start : nodes) {
			Dijkstra.DijkstraResult result = shortestPaths.get(start);

			System.out.println("Dijkstra says...");
			for (GeoGraphNode node : result.distances.keySet()) {
				System.out.println("  "+start.getName()+" -> "+node.getName()+": "+ result.distances.get(node));
			}
			for (GeoGraphNode node : result.previous.keySet()) {
				if (result.previous.get(node) == null) continue;
				System.out.println("  "+node.getName()+ "  Prev: "+result.previous.get(node).getName());
				//for (GeoGraphNode item : result.previous.get(node)) {
				//	System.out.println("    "+item.getName());
				//}
			}
		}
	}

	public ArrayList<GeoGraphNode> getShortestPath(GeoGraphNode start, GeoGraphNode end) {
		Dijkstra.DijkstraResult result = shortestPaths.get(start);
		return result.shortedPathTo(end);
	}
}
