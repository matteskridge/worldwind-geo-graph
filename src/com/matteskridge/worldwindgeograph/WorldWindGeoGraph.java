package com.matteskridge.worldwindgeograph;

import com.matteskridge.worldwindgeograph.components.controller.GraphController;
import com.matteskridge.worldwindgeograph.components.viewer.GraphViewer;
import com.matteskridge.worldwindgeograph.graph.GeoGraph;
import com.matteskridge.worldwindgeograph.graph.GraphRoute;

import javax.swing.*;
import java.awt.*;

/**
 * @author Matt Eskridge
 * @created 10/20/14
 */
public class WorldWindGeoGraph extends JFrame {
	private GeoGraph graph;
	private GraphViewer viewer;
	private GraphController controller;

	public WorldWindGeoGraph() {
		graph = new GeoGraph("graph.xml");

		add(viewer = new GraphViewer(this), BorderLayout.CENTER);
		add(controller = new GraphController(this), BorderLayout.SOUTH);

		setSize(500,400);
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("WorldWind Geo Graph \u00A9 Matt Eskridge");
		setVisible(true);
	}

	public GeoGraph getGraph() {
		return graph;
	}

	public static void main(String[] args) {
		new WorldWindGeoGraph();
	}

	public void displayRoute(GraphRoute route) {
		viewer.displayRoute(route);
	}
}
