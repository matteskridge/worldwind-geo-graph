package com.matteskridge.worldwindgeograph.graph;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.ArrayList;

/**
 * @author Matt Eskridge
 * @created 10/20/14
 */
public class GraphDataReader {
	public String data;

	public boolean parsed;
	private String name;
	private ArrayList<GeoGraphNode> nodes;

	public GraphDataReader(String data) {
		this.data = data;
	}

	private void parseData() {
		if (parsed) {
			return;
		}

		nodes = new ArrayList<GeoGraphNode>();

		Document doc = stringToXmlDocument(data);
		readNodes(doc);
		readConnections(doc);

		parsed = true;
	}

	private void readNodes(Document doc) {
		NodeList nodes = doc.getElementsByTagName("node");

		System.out.println("Reading Nodes...");

		// For each element in the XML
		for (int i = 0; i < nodes.getLength(); i++) {

			// Create the element
			GeoGraphNode node = new GeoGraphNode();
			node.setId(nodes.item(i).getAttributes().getNamedItem("id").getTextContent());
			node.setName(nodes.item(i).getAttributes().getNamedItem("name").getTextContent());
			node.setImportant(nodes.item(i).getAttributes().getNamedItem("important").getTextContent().equals("true"));
			node.setLatitude(Double.parseDouble(""+nodes.item(i).getAttributes().getNamedItem("latitude").getTextContent()));
			node.setLongitude(Double.parseDouble("" + nodes.item(i).getAttributes().getNamedItem("longitude").getTextContent()));

			System.out.println("  Creating graph node "+node.getId());

			// Add the element. Connections are handled later.
			this.nodes.add(node);
		}
	}

	private void readConnections(Document doc) {
		NodeList nodes = doc.getElementsByTagName("node");

		System.out.println("Reading Connections...");

		// For each element in the XML
		for (int i = 0; i < nodes.getLength(); i++) {

			GeoGraphNode node = getNodeById(nodes.item(i).getAttributes().getNamedItem("id").getTextContent());
			String[] links = nodes.item(i).getAttributes().getNamedItem("connections").getTextContent().split(",");

			// For each ID# in the element
			for (int j = 0; j < links.length; j++) {
				GeoGraphNode to = getNodeById(links[j]);

				// This graph is undirected, so connect both.
				node.connect(to);
				to.connect(node);

				System.out.println("  Connecting graph node "+node.getId()+" <--> "+to.getId());
			}

		}
	}

	public static Document stringToXmlDocument(String xml) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			InputSource is = new InputSource(new StringReader(xml));
			return builder.parse(is);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	private GeoGraphNode getNodeById(String id) {
		for (GeoGraphNode node: nodes) {
			if (id.equals(node.getId())) {
				return node;
			}
		}

		return null;
	}

	public ArrayList<GeoGraphNode> getNodes() {
		parseData();
		return nodes;
	}
}
