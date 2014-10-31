package com.matteskridge.worldwindgeograph.graph;

import com.matteskridge.worldwindgeograph.components.viewer.GraphViewer;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.geom.Vec4;
import gov.nasa.worldwind.render.SurfacePolyline;

import java.util.ArrayList;

/**
 * @author Matt Eskridge
 * @created 10/20/14
 */
public class GeoGraphNode {
	private int guid;
	private static int guid_last = 0;

	private String id;
	private String name;

	// If true, then the user can select this as a destination
	private boolean important;

	// Coordinates are stored in degrees
	private double latitude;
	private double longitude;

	// Connections with this node
	private ArrayList<GeoGraphNode> connections;
	private ArrayList<Double> weights;

	public GeoGraphNode() {
		connections = new ArrayList<GeoGraphNode>();
		weights = new ArrayList<Double>();

		guid_last++;
		guid = guid_last;
	}

	public double distanceTo(GeoGraphNode node) {
		Vec4 p1 = GraphViewer.globe.computePointFromPosition(getPosition());
		Vec4 p2 = GraphViewer.globe.computePointFromPosition(node.getPosition());

		return p1.distanceTo3(p2);

		/*
		ArrayList<Position> positions = new ArrayList<Position>();
		positions.add(node.getPosition());
		positions.add(getPosition());

		SurfacePolyline line = new SurfacePolyline(positions);
		double length = line.getLength(GraphViewer.globe);

		System.out.println("From "+getName()+" -> "+node.getName()+" "+length);
		return length / 2;*/
	}

	public void connect(GeoGraphNode node) {
		if (connections.contains(node)) {
			return;
		}

		connections.add(node);
		weights.add(distanceTo(node));
	}

	public void disconnect(GeoGraphNode node) {
		int index = connections.indexOf(node);
		connections.remove(index);
		weights.remove(index);
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setCoordinates(double[] coords) {
		latitude = coords[0];
		longitude = coords[1];
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double[] getCoordinates() {
		return new double[]{latitude, longitude};
	}

	public void setImportant(boolean important) {
		this.important = important;
	}

	public boolean isImportant() {
		return important;
	}

	public ArrayList<GeoGraphNode> getConnections() {
		return connections;
	}

	public ArrayList<Double> getWeights() {
		return weights;
	}

	public Position getPosition() {
		double altitude = GraphViewer.globe.getElevation(Angle.fromDegreesLatitude(latitude), Angle.fromDegreesLongitude(longitude));
		return new Position(Angle.fromDegreesLatitude(latitude), Angle.fromDegreesLongitude(longitude), altitude);
	}

	public int hashCode() {
		return guid;
	}
}
