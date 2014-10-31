package com.matteskridge.worldwindgeograph.graph;

import java.util.ArrayList;

/**
 * @author Matt Eskridge
 * @created 10/20/14
 */
public class GraphRoute {
	private GeoGraph graph;
	private GeoGraphNode from;
	private GeoGraphNode to;

	private double length;
	private ArrayList<GeoGraphNode> bestPath;

	public GraphRoute(GeoGraph graph, GeoGraphNode from, GeoGraphNode to) {
		this.graph = graph;
		this.from = from;
		this.to = to;

		computeBestPath();
	}

	private void computeBestPath() {
		bestPath = graph.getShortestPath(from, to);
		length = 0;

		for (int i = 0; i < bestPath.size()-1; i++) {
			length += bestPath.get(i).distanceTo(bestPath.get(i+1));
		}
	}

	public double getLengthInMeters() {
		return length;
	}

	public double getLengthInMiles() {
		return length * 0.000621371;
	}

	public double getLengthInMinutesDriving() {
		return (getLengthInMiles() / 50) * 60;
	}

	public double getNumberOfStops() {
		return bestPath.size();
	}

	public double getNumberOfRoads() {
		return bestPath.size() - 1;
	}

	public ArrayList<GeoGraphNode> getPath() {
		return bestPath;
	}

	public boolean bestPathContains(GeoGraphNode node, GeoGraphNode connection) {
		for (int i = 0; i < bestPath.size() - 1; i++) {
			if ((bestPath.get(i) == node && bestPath.get(i+1) == connection) || (bestPath.get(i) == connection && bestPath.get(i+1) == node)) return true;
		}
		return false;
	}
}
