import java.util.*;
import java.io.*;

public class tree_height {
    class FastScanner {
		StringTokenizer tok = new StringTokenizer("");
		BufferedReader in;

		FastScanner() {
			in = new BufferedReader(new InputStreamReader(System.in));
		}

		String next() throws IOException {
			while (!tok.hasMoreElements())
				tok = new StringTokenizer(in.readLine());
			return tok.nextToken();
		}
		int nextInt() throws IOException {
			return Integer.parseInt(next());
		}
	}

	class Node {
		int val;
		List<Node> children;

		public Node() {
			children = new ArrayList<>();
		}

		public void addChild(Node node) {
			children.add(node);
		}

		public void setVal(int val) {
			this.val = val;
		}
	}

	public class TreeHeight {
		int n;
		int parent[];
		Node root;
		
		void read() throws IOException {
			FastScanner in = new FastScanner();
			n = in.nextInt();
			parent = new int[n];
			for (int i = 0; i < n; i++) {
				parent[i] = in.nextInt();
			}
		}

		Node buildTree() {
			Node[] nodes = new  Node[n];
			for (int i = 0; i < n; i++) {
				nodes[i] = new Node();
			}

			for (int child_index = 0; child_index < n; child_index++) {
				int parent_index =  parent[child_index];
				if (parent_index == -1) {
					root = nodes[child_index];
					root.setVal(child_index);
				}
				else {
					nodes[child_index].setVal(child_index);
					nodes[parent_index].addChild(nodes[child_index]);
				}
			}
			return root;
		}


		int computeHeight(Node node) {
			 // Iterative BFS approach to avoid stack overflow
			if (node == null) {
				return 0;
			}
			
			Queue<Node> queue = new LinkedList<>();
			queue.add(node);
			int height = 0;
			
			while (!queue.isEmpty()) {
				// Process all nodes at current level
				int levelSize = queue.size();
				for (int i = 0; i < levelSize; i++) {
					Node current = queue.poll();
					
					// Add all children to the queue
					for (Node child : current.children) {
						queue.add(child);
					}
				}
				// Increment height after processing each level
				height++;
			}
			
			return height;
		}
	}

	static public void main(String[] args) throws IOException {
            new Thread(null, new Runnable() {
                    public void run() {
                        try {
                            new tree_height().run();
                        } catch (IOException e) {
                        }
                    }
                }, "1", 1 << 26).start();
	}
	public void run() throws IOException {
		TreeHeight tree = new TreeHeight();
		tree.read();
		Node root = tree.buildTree();
		System.out.println(tree.computeHeight(root));
	}
}
