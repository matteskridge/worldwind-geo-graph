package com.matteskridge.worldwindgeograph.components.controller;

import com.matteskridge.worldwindgeograph.WorldWindGeoGraph;
import com.matteskridge.worldwindgeograph.graph.GraphRoute;

import javax.swing.*;
import java.awt.*;

/**
 * @author Matt Eskridge
 * @created 10/20/14
 */
public class GraphController extends JPanel {
	private WorldWindGeoGraph geo;

	private LocationSelector selector;
	private RouteInformationViewer info;

	public GraphController(WorldWindGeoGraph geo) {
		this.geo = geo;

		setLayout(new BorderLayout());
		setBackground(Color.decode("#CCCCCC"));
		setBorder(BorderFactory.createMatteBorder(3,0,0,0,Color.decode("#CCCCCC")));
		add(info = new RouteInformationViewer(), BorderLayout.CENTER);
		add(selector = new LocationSelector(this, geo.getGraph()), BorderLayout.WEST);
	}

	public void showRouteStatistics(GraphRoute route) {
		info.showRoute(route);
	}

	public WorldWindGeoGraph getWindow() {
		return geo;
	}
}
