package com.matteskridge.worldwindgeograph.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * @author Matt Eskridge
 * @created 10/20/14
 */
public class Dijkstra {
	public static DijkstraResult getShortestPaths(GeoGraph graph, GeoGraphNode start) {
		HashMap<GeoGraphNode, Double> distances = new HashMap();
		HashMap<GeoGraphNode, GeoGraphNode> previous = new HashMap();
		ArrayList<GeoGraphNode> unvisited = new ArrayList();

		for (GeoGraphNode node : graph.getNodes()) {
			distances.put(node, (node == start)?0:Double.POSITIVE_INFINITY);
			previous.put(node, null);
			unvisited.add(node);
		}

		while (!unvisited.isEmpty()) {
			GeoGraphNode current = getNodeWithMinimumDistance(start, distances, unvisited);
			unvisited.remove(current);

			for (GeoGraphNode adj: current.getConnections()) {
				double alt = distances.get(current) + current.distanceTo(adj);
				if (alt < distances.get(adj)) {
					distances.put(adj, alt);
					previous.put(adj, current);
				}
			}
		}

		DijkstraResult result = new DijkstraResult();
		result.start = start;
		result.previous = previous;
		result.distances = distances;
		return result;
	}

	public static GeoGraphNode getNodeWithMinimumDistance(GeoGraphNode start, HashMap<GeoGraphNode, Double> distances, ArrayList<GeoGraphNode> unvisited) {
		GeoGraphNode best = null;

		for (GeoGraphNode node: distances.keySet()) {
			if ((best == null || distances.get(node) < distances.get(best)) && unvisited.contains(node)) {
				best = node;
			}
		}

		return best;
	}

	public static class DijkstraResult {
		public GeoGraphNode start;
		public HashMap<GeoGraphNode, Double> distances;
		public HashMap<GeoGraphNode, GeoGraphNode> previous;

		public ArrayList<GeoGraphNode> shortedPathTo(GeoGraphNode end) {

			ArrayList<GeoGraphNode> path = new ArrayList<GeoGraphNode>();
			path.add(end);
			GeoGraphNode current = end;

			while (current != start && previous.containsKey(current)) {
				current = previous.get(current);
				path.add(current);
			}

			Collections.reverse(path);

			return path;
		}
	}
}
