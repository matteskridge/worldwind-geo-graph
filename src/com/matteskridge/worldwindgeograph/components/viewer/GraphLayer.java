package com.matteskridge.worldwindgeograph.components.viewer;

import com.matteskridge.worldwindgeograph.graph.GeoGraph;
import com.matteskridge.worldwindgeograph.graph.GeoGraphNode;
import com.matteskridge.worldwindgeograph.graph.GraphRoute;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.*;

import java.awt.*;
import java.util.ArrayList;

/**
 * @author Matt Eskridge
 * @created 10/20/14
 */
public class GraphLayer extends RenderableLayer {
	private GeoGraph graph;
	private GraphRoute route;
	private ArrayList<Renderable> cache;

	public GraphLayer(GeoGraph graph) {
		this.graph = graph;
		this.cache = null;
	}

	/**
	 * This method simply overrides worldwind's rendering function to
	 * draw the cached renderable elements. The preRender function
	 * renders elements which have priority over other elements,
	 * primarily the elements which are drawn across the surface of
	 * the globe, as opposed to in the air.
	 */
	public void preRender(DrawContext dc) {
		if (cache == null) {
			createGraphRenderables();
		}

		for (Renderable render: cache) {
			if (render instanceof PreRenderable) {
				((PreRenderable)render).preRender(dc);
			}
		}
	}

	/**
	 * This method simply overrides worldwind's rendering function to
	 * draw the cached renderable elements.
	 */
	public void render(DrawContext dc) {
		if (cache == null) {
			createGraphRenderables();
		}

		for (Renderable render: cache) {
			render.render(dc);
		}
	}

	/**
	 * This is called whenever the cache is empty to create a new
	 * cache. The cache can be emptied explicitly with decache().
	 */
	private void createGraphRenderables() {
		cache = new ArrayList<Renderable>();
		createRenderableLocations();
		createRenderablePaths();
	}

	public void createRenderableLocations() {

		// For each important location which deserves a label
		for (GeoGraphNode node: graph.getImportantNodes()) {

			PointPlacemarkAttributes attrs = new PointPlacemarkAttributes();
			//attrs.setImageColor(Color.decode("#000000"));
			attrs.setLineMaterial(new Material(Color.decode("#3E50C2")));
			attrs.setLineWidth(10d);
			attrs.setScale(10d);
			attrs.setUsePointAsDefaultImage(true);

			PointPlacemark mark = new PointPlacemark(node.getPosition());
			mark.setAttributes(attrs);
			mark.setLabelText(node.getName());
			cache.add(mark);

			System.out.println("Adding marker... "+node.getPosition()+","+node.getName());

		}
	}

	public void createRenderablePaths() {

		// For each location
		for (GeoGraphNode node: graph.getNodes()) {
			for (GeoGraphNode connection: node.getConnections()) {

				boolean highlight = route != null && route.bestPathContains(node, connection);
				Color color = (highlight) ? Color.decode("#00FF00") : Color.decode("#FFC04A");

				ArrayList<Position> positions = new ArrayList<Position>();
				positions.add(node.getPosition());
				positions.add(connection.getPosition());

				ShapeAttributes attrs = new BasicShapeAttributes();
				attrs.setInteriorMaterial(new Material(color));
				attrs.setOutlineMaterial(new Material(color));
				attrs.setOutlineWidth(3d);

				SurfacePolyline line = new SurfacePolyline(positions);
				line.setAttributes(attrs);
				cache.add(line);

			}
		}
	}

	/**
	 * Empty the cache so that this software can create a new set of
	 * renderables. This must be called every time the graph is changed,
	 * and those changes are intended to be shown to the user.
	 */
	public void decache() {
		cache = null;
	}

	public void displayRoute(GraphRoute route) {
		this.route = route;
		decache();
	}
}
