package com.matteskridge.worldwindgeograph.components.viewer;

import com.matteskridge.worldwindgeograph.WorldWindGeoGraph;
import com.matteskridge.worldwindgeograph.graph.GraphRoute;
import gov.nasa.worldwind.BasicModel;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.globes.Earth;
import gov.nasa.worldwind.layers.Layer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * @author Matt Eskridge
 * @created 10/20/14
 */
public class GraphViewer extends JPanel {

	public static final Earth globe = new Earth();

	private WorldWindGeoGraph geo;
	private WorldWindowGLCanvas canvas;
	private GraphLayer layer;

	public GraphViewer(WorldWindGeoGraph geo) {
		this.geo = geo;

		canvas = new WorldWindowGLCanvas();
		canvas.setModel(new BasicModel());

		optimizeInterface();
		zoomInToLocation();
		configureLayer();

		setLayout(new BorderLayout());
		add(canvas, BorderLayout.CENTER);

		setBackground(Color.decode("#666666"));
	}

	private void optimizeInterface() {
		ArrayList<Layer> remove = new ArrayList();

		for (Layer layer : canvas.getModel().getLayers()) {
			if (layer.getName().equals("World Map")) {
				remove.add(layer);
			} else if (layer.getName().equals("Compass")) {
				remove.add(layer);
			} else if (layer.getName().contains("Scale")) {
				remove.add(layer);
			} else if (layer.getName().contains("Place Names")) {
				remove.add(layer);
			}
		}

		for (Layer drop: remove) {
			canvas.getModel().getLayers().remove(drop);
		}
	}

	private void zoomInToLocation() {
		Angle latitude = Angle.fromDegreesLatitude(34.5300);
		Angle longitude = Angle.fromDegreesLongitude(-86.5850);
		Position position = new Position(latitude, longitude, 200000);
		canvas.getView().setEyePosition(position);
		canvas.getView().setPitch(Angle.fromDegrees(60));
		canvas.getView().setHeading(Angle.fromDegrees(-20));
	}

	private void configureLayer() {
		canvas.getModel().getLayers().add(layer = new GraphLayer(geo.getGraph()));
	}

	public void displayRoute(GraphRoute route) {
		layer.displayRoute(route);
	}
}
