package com.graph;

import java.util.Arrays;

public class DefaultGraph implements Graph {

	private static class _Edge implements Edge {

		private static final _Edge NullEdge = new _Edge();

		int from;
		int to;
		int weight;

		_Edge nextEdge;

		private _Edge() {
			weight = Integer.MAX_VALUE;
		}

		_Edge(int from, int to, int weight) {

			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		public int getWeight() {
			return weight;
		}

	}

	private static class _EdgeStaticQueue {
		_Edge first;
		_Edge last;
	}

	private int numVertexes;
	private String[] labels;
	private int numEdges;

	private _EdgeStaticQueue[] edgeQueues;

	// tag the specified vertex be visited or not
	private boolean[] visitTags;

	public DefaultGraph(int numVertexes) {
		if (numVertexes < 1) {
			throw new IllegalArgumentException();
		}
		this.numVertexes = numVertexes;
		this.visitTags = new boolean[numVertexes];
		this.labels = new String[numVertexes];
		for (int i = 0; i < numVertexes; i++) {
			labels[i] = i + "";

		}
		this.edgeQueues = new _EdgeStaticQueue[numVertexes];
		for (int i = 0; i < numVertexes; i++) {
			edgeQueues[i] = new _EdgeStaticQueue();
			edgeQueues[i].first = edgeQueues

			[i].last = _Edge.NullEdge;

		}
		this.numEdges = 0;
	}

	@Override
	public int edgeNum() {

		return numEdges;
	}

	@Override
	public Edge firstEdge(int vertex) {
		if (vertex >= numVertexes)
			throw new

			IllegalArgumentException();
		return edgeQueues[vertex].first;

	}

	@Override
	public boolean isEdge(Edge edge) {

		return (edge != _Edge.NullEdge);
	}

	@Override
	public Edge nextEdge(Edge edge) {

		return ((_Edge) edge).nextEdge;
	}

	@Override
	public int vertexesNum() {

		return numVertexes;
	}

	@Override
	public int fromVertex(Edge edge) {

		return ((_Edge) edge).from;
	}

	@Override
	public void setEdge(int from, int to, int weight) {
		// we don't allow ring exist
		if

		(from < 0 || from >= numVertexes || to < 0 || to >= numVertexes
				|| weight <

				0 || from == to)
			throw new IllegalArgumentException();
		_Edge edge = new _Edge(from, to, weight);
		edge.nextEdge = _Edge.NullEdge;
		if (edgeQueues[from].first == _Edge.NullEdge)
			edgeQueues[from].first = edge;
		else
			edgeQueues[from].last.nextEdge = edge;
		edgeQueues[from].last = edge;

	}

	@Override
	public int toVertex(Edge edge) {

		return ((_Edge) edge).to;
	}

	@Override
	public String getVertexLabel(int vertex) {
		return labels[vertex];
	}

	@Override
	public void assignLabels(String[] labels) {
		System.arraycopy(labels, 0, this.labels, 0,

		labels.length);

	}

	@Override
	// 深度优先遍历
	public void deepFirstTravel(GraphVisitor visitor) {
		Arrays.fill(visitTags, false);// reset all visit tags
		for (int i = 0; i < numVertexes; i++) {
			if (!visitTags[i])
				do_DFS(i, visitor);
		}

	}

	private final void do_DFS(int v, GraphVisitor visitor)

	{
		// first visit this vertex
		visitor.visit(this, v);
		visitTags[v] = true;

		// for each edge from this vertex, we do one time
		// and this for loop is very classical in all graph algorithms
		for (Edge e = firstEdge(v); isEdge(e); e = nextEdge(e)) {
			if (!visitTags[toVertex(e)]) {
				do_DFS(toVertex(e), visitor);
			}
		}

	}

	// to be continue

	private static class _IntQueue {
		private static class _IntQueueNode {
			_IntQueueNode next;
			int value;
		}

		_IntQueueNode first;
		_IntQueueNode last;

		// queue can only insert to the tail
		void add(int i) {
			_IntQueueNode node = new _IntQueueNode();
			node.value = i;
			node.next = null;
			if (first == null)
				first = node;
			else
				last.next = node;
			last = node;
		}

		boolean isEmpty() {
			return first == null;
		}

		// queue can only remove from the head
		int remove() {
			int val = first.value;
			if (first == last)
				first = last = null;
			else
				first = first.next;
			return val;
		}

	}

	// 广度优先遍历
	@Override
	public void breathFirstTravel(GraphVisitor visitor) {
		Arrays.fill(visitTags, false);// reset all visit tags

		for (int i = 0; i < numVertexes; i++) {
			if (!visitTags[i]) {

				do_BFS(i, visitor);
			}
		}

	}

	private void do_BFS(int v, GraphVisitor visitor) {
		// and BFS will use an queue to keep the unvisited vertexes
		// we can also just use java.util.ArrayList
		_IntQueue queue = new _IntQueue();
		queue.add(v);
		while (!queue.isEmpty()) {
			int fromV = queue.remove();
			visitor.visit(this, fromV);
			visitTags[fromV] = true;
			for (Edge e = firstEdge(fromV); isEdge

			(e); e = nextEdge(e)) {
				if (!visitTags[toVertex(e)]) {
					queue.add(toVertex(e));
				}
			}
		}
	}

	public static void main(String[] args) {

		DefaultGraph g = new DefaultGraph(9);
		g.setEdge(0, 1, 0);
		g.setEdge(0, 3, 0);
		g.setEdge(1, 2, 0);
		g.setEdge(4, 1, 0);
		g.setEdge(2, 5, 0);

		g.setEdge(3, 6, 0);
		g.setEdge(7, 4, 0);
		g.setEdge(5, 8, 0);
		g.setEdge(6, 7, 0);
		g.setEdge(8, 7, 0);

		// now we visit it
		GraphVisitor visitor = new GraphVisitor() {
			@Override
			public void visit(Graph g, int vertex) {
				System.out.print(g.getVertexLabel(vertex)

				+ " ");

			}

		};
		System.out.println("DFS==============:");
		g.deepFirstTravel(visitor);
		System.out.println();
		System.out.println("BFS==============:");
		g.breathFirstTravel(visitor);
		System.out.println();

	}
}
