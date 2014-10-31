package com.matteskridge.worldwindgeograph.components.controller;

import com.matteskridge.worldwindgeograph.graph.GraphRoute;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

/**
 * @author Matt Eskridge
 * @created 10/20/14
 */
public class RouteInformationViewer extends JPanel {

	private JPanel main;
	private GridLayout grid;

	public RouteInformationViewer() {
		setLayout(new BorderLayout());

		JPanel center = new JPanel();
		center.setLayout(new GridBagLayout());
		add(center, BorderLayout.WEST);

		main = new JPanel();
		main.setLayout(grid = new GridLayout(2,0));
		main.setBorder(BorderFactory.createEmptyBorder(0,50,0,0));
		center.add(main);

		grid.setHgap(50);
		grid.setVgap(20);
	}

	public void showRoute(GraphRoute route) {
		main.removeAll();

		addStatistic("distance", route.getLengthInMiles(), "miles");
		addStatistic("time", route.getLengthInMinutesDriving(), "minutes");
		addStatistic("places", route.getNumberOfStops(), "stops");
		addStatistic("roads", route.getNumberOfRoads(), "roads");

		main.revalidate();
	}

	private void addStatistic(String icon, double number, String units) {
		DecimalFormat format = new DecimalFormat("#,###");

		JLabel label = new JLabel(format.format(number) + " " + units);
		label.setFont(label.getFont().deriveFont(15f));

		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		panel.add(label);
		main.add(panel);
	}
}
