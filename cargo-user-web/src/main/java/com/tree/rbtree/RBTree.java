package com.tree.rbtree;

import java.util.TreeMap;

public class RBTree {

	private Node root;
	
	private TreeMap<String, Object> map = null;
	

	@SuppressWarnings("unused")
	public boolean put(int v) {
		boolean result = true;
		Node next = null;
		if (root == null) {
			root = new Node(v);
			next = root;
		} else {
			int crel = -2;
			next = root;
			do {
				crel = next.compare(v);
			   if (1 == crel) {
					next = getLeftOrRight(next, v, true);
				} else if (-1 == crel) {
					next = getLeftOrRight(next, v, false);
				}
			} while (0 != crel);
		}
		//处理平衡
		fixAfterInsertion(next);
		return result;
	}
	
	//平衡
	 /** From CLR */
    private void fixAfterInsertion(Node x) {
        x.color = Node.RED;
        //不空，不是root，父节点的颜色是红色
        while (x != null && x != root && x.parent.color == Node.RED) {
        	//父节点是左节点
            if (parentOf(x) == leftOf(parentOf(parentOf(x)))) {
            	//获得父节点的兄弟节点（叔叔节点，右节点）
                Node y = rightOf(parentOf(parentOf(x)));
                //如果叔叔节点（右节点）是红色
                if (colorOf(y) == Node.RED) {
                	//把父节点设置成黑色
                    setColor(parentOf(x), Node.BLACK);
                    //把叔叔节点设置成黑色
                    setColor(y, Node.BLACK);
                    //把爷爷节点设置成红色
                    setColor(parentOf(parentOf(x)), Node.RED);
                    //指向爷爷节点
                    x = parentOf(parentOf(x));
                    //如果叔叔节点（右节点）是黑色
                } else {
                	//如果自身是父节点的右节点
                    if (x == rightOf(parentOf(x))) {
                    	//指向父节点
                        x = parentOf(x);
                        //左旋
                        rotateLeft(x);
                    }
                    //将父节点设置成黑色
                    setColor(parentOf(x), Node.BLACK);
                    //将爷爷节点设置成红色
                    setColor(parentOf(parentOf(x)), Node.RED);
                    //将爷爷节点右旋
                    rotateRight(parentOf(parentOf(x)));
                }
                //父节点是右节点
            } else {
            	//获得爷爷的右子节点（右叔叔节点）
                Node y = leftOf(parentOf(parentOf(x)));
                //如果叔叔节点是红色
                if (colorOf(y) == Node.RED) {
                	//将自身的父节点设置成黑色
                    setColor(parentOf(x), Node.BLACK);
                    //将叔叔节点设置成黑色
                    setColor(y, Node.BLACK);
                    //将爷爷节点设置成红色
                    setColor(parentOf(parentOf(x)), Node.RED);
                    //指向爷爷节点
                    x = parentOf(parentOf(x));
                    //叔叔节点是黑色
                } else {
                	//如果自生是左节点
                    if (x == leftOf(parentOf(x))) {
                    	//指向父节点
                        x = parentOf(x);
                        //右旋
                        rotateRight(x);
                    }
                    //设置父节点是黑色
                    setColor(parentOf(x), Node.BLACK);
                    //设置爷爷节点是红色
                    setColor(parentOf(parentOf(x)), Node.RED);
                    //将爷爷节点左旋
                    rotateLeft(parentOf(parentOf(x)));
                }
            }
        }
        //将根节点设置为黑色
        root.color = Node.BLACK;
    }
    
    private static <K,V> boolean colorOf(Node p) {
        return (p == null ? Node.BLACK : p.color);
    }

    private static <K,V> Node parentOf(Node p) {
        return (p == null ? null: p.parent);
    }

    private static <K,V> void setColor(Node p, boolean c) {
        if (p != null)
            p.color = c;
    }

    private static <K,V> Node leftOf(Node p) {
        return (p == null) ? null: p.left;
    }

    private static <K,V> Node rightOf(Node p) {
        return (p == null) ? null: p.right;
    }
    
    /** From CLR */
    private void rotateLeft(Node p) {
        if (p != null) {
            Node r = p.right;
            p.right = r.left;
            if (r.left != null)
                r.left.parent = p;
            r.parent = p.parent;
            if (p.parent == null)
                root = r;
            else if (p.parent.left == p)
                p.parent.left = r;
            else
                p.parent.right = r;
            r.left = p;
            p.parent = r;
        }
    }

    /** From CLR */
    private void rotateRight(Node p) {
        if (p != null) {
            Node l = p.left;
            p.left = l.right;
            if (l.right != null) l.right.parent = p;
            l.parent = p.parent;
            if (p.parent == null)
                root = l;
            else if (p.parent.right == p)
                p.parent.right = l;
            else p.parent.left = l;
            l.right = p;
            p.parent = l;
        }
    }

	

	private Node getLeftOrRight(Node p, int v, boolean LR) {
		Node nd = null;
		if (null != p) {
			nd = LR ? p.right : p.left;
			if (null == nd) {
				if (LR) {
					p.right = new Node(v);
					p.right.parent = p;
				} else {
					p.left = new Node(v);
					p.left.parent = p;
				}
			}
		}
		return nd;
	}

	/*****************print***************************************/
	public void printTree() {
		printSubtree(root);
	}

	public void printSubtree(Node node) {
		if (node.right != null) {
			printTree(node.right, true, "");
		}
		printNodeValue(node);
		if (node.left != null) {
			printTree(node.left, false, "");
		}
	}

	private void printNodeValue(Node node) {
		//if (node.v == 0) {
		//	System.out.print("0");
		//} else {
			System.out.println(node.getV());
		//}
		//System.out.println();
	}

	private void printTree(Node node, boolean isRight, String indent) {
		if (node.right != null) {
			printTree(node.right, true, indent + (isRight ? "        " : " |      "));
		}
		System.out.print(indent);
		if (isRight) {
			System.out.print(" /");
		} else {
			System.out.print(" \\");
		}
		System.out.print("----- ");
		printNodeValue(node);
		if (node.left != null) {
			printTree(node.left, false, indent + (isRight ? " |      " : "        "));
		}
	}
}
