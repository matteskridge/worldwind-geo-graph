package com.matteskridge.worldwindgeograph.components.controller;

import com.matteskridge.worldwindgeograph.graph.GeoGraph;
import com.matteskridge.worldwindgeograph.graph.GeoGraphNode;
import com.matteskridge.worldwindgeograph.graph.GraphRoute;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author Matt Eskridge
 * @created 10/20/14
 */
public class LocationSelector extends JPanel implements ActionListener {

	private JComboBox from, to;
	private GraphController controller;
	private GeoGraph graph;

	private Dimension size = new Dimension(150,20);

	public LocationSelector(GraphController controller, GeoGraph graph) {
		this.controller = controller;
		this.graph = graph;

		setBackground(Color.decode("#FFFFFF"));
		setLayout(new GridBagLayout());
		setBorder(BorderFactory.createEmptyBorder(5,20,20,20));

		JPanel main = new JPanel();
		main.setOpaque(false);
		main.setLayout(new GridLayout(0,1));
		main.add(buildLabel("Origin:"));
		main.add(from = buildOptionBox());
		main.add(buildLabel("Destination:"));
		main.add(to = buildOptionBox());
		add(main);

		from.setSelectedIndex(0);
		to.setSelectedIndex(1);

		actionPerformed(null);
	}

	private JLabel buildLabel(String name) {
		JLabel label = new JLabel(name);
		label.setBorder(BorderFactory.createEmptyBorder(5,10,5,0));
		//label.setPreferredSize(size);
		return label;
	}

	private JComboBox buildOptionBox() {
		DefaultComboBoxModel model = new DefaultComboBoxModel();

		ArrayList<GeoGraphNode> nodes = graph.getImportantNodes();
		Collections.sort(nodes, new AlphabeticalNodeComparator());

		for (GeoGraphNode node: nodes) {
			model.addElement(node.getName());
		}

		JComboBox box = new JComboBox(model);
		box.setPreferredSize(new Dimension(200,23));
		box.addActionListener(this);
		return box;
	}

	private GeoGraphNode getSelectedNode(JComboBox box) {
		ArrayList<GeoGraphNode> nodes = graph.getImportantNodes();
		Collections.sort(nodes, new AlphabeticalNodeComparator());
		return nodes.get(box.getSelectedIndex());
	}

	public void actionPerformed(ActionEvent actionEvent) {
		GeoGraphNode source = getSelectedNode(from);
		GeoGraphNode dest = getSelectedNode(to);

		GraphRoute route = graph.getRoute(source, dest);

		controller.showRouteStatistics(route);
		controller.getWindow().displayRoute(route);
	}

	class AlphabeticalNodeComparator implements Comparator<GeoGraphNode> {

		public int compare(GeoGraphNode n1, GeoGraphNode n2) {
			return n1.getName().compareTo(n2.getName());
		}
	}
}
