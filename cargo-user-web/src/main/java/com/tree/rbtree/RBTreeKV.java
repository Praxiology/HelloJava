package com.tree.rbtree;

import java.util.Comparator;

public class RBTreeKV<V, K> {
	private final Comparator<? super K> comparator = null;
	/**
	 * The number of entries in the tree
	 */
	private transient int size = 0;

	/**
	 * The number of structural modifications to the tree.
	 */
	private transient int modCount = 0;
	private transient NodeKV<K, V> root;

	public V put(K key, V value) {
		NodeKV<K, V> t = root;
		if (t == null) {
			compare(key, key); // type (and possibly null) check

			root = new NodeKV<>(key, value, null);
			size = 1;
			modCount++;
			return null;
		}
		int cmp;
		NodeKV<K, V> parent;
		// split comparator and comparable paths
		Comparator<? super K> cpr = comparator;
		if (cpr != null) {
			do {
				parent = t;
				cmp = cpr.compare(key, t.key);
				if (cmp < 0)
					t = t.left;
				else if (cmp > 0)
					t = t.right;
				else
					return t.setValue(value);
			} while (t != null);
		} else {
			if (key == null)
				throw new NullPointerException();
			@SuppressWarnings("unchecked")
			Comparable<? super K> k = (Comparable<? super K>) key;
			do {
				parent = t;
				cmp = k.compareTo(t.key);
				if (cmp < 0)
					t = t.left;
				else if (cmp > 0)
					t = t.right;
				else
					return t.setValue(value);
			} while (t != null);
		}
		NodeKV<K, V> e = new NodeKV<>(key, value, parent);
		if (cmp < 0)
			parent.left = e;
		else
			parent.right = e;
		fixAfterInsertion(e);
		size++;
		modCount++;
		return null;
	}

	int compare(Object k1, Object k2) {
		return comparator == null ? ((Comparable<? super K>) k1).compareTo((K) k2) : comparator.compare((K) k1, (K) k2);
	}

	private void fixAfterInsertion(NodeKV<K, V> x) {
		x.color = NodeKV.RED;
		// 不空，不是root，父节点的颜色是红色
		while (x != null && x != root && x.parent.color == NodeKV.RED) {
			// 父节点是左节点
			if (parentOf(x) == leftOf(parentOf(parentOf(x)))) {
				// 获得父节点的兄弟节点（叔叔节点，右节点）
				NodeKV y = rightOf(parentOf(parentOf(x)));
				// 如果叔叔节点（右节点）是红色
				if (colorOf(y) == NodeKV.RED) {
					// 把父节点设置成黑色
					setColor(parentOf(x), NodeKV.BLACK);
					// 把叔叔节点设置成黑色
					setColor(y, NodeKV.BLACK);
					// 把爷爷节点设置成红色
					setColor(parentOf(parentOf(x)), NodeKV.RED);
					// 指向爷爷节点
					x = parentOf(parentOf(x));
					// 如果叔叔节点（右节点）是黑色
				} else {
					// 如果自身是父节点的右节点
					if (x == rightOf(parentOf(x))) {
						// 指向父节点
						x = parentOf(x);
						// 左旋
						rotateLeft(x);
					}
					// 将父节点设置成黑色
					setColor(parentOf(x), Node.BLACK);
					// 将爷爷节点设置成红色
					setColor(parentOf(parentOf(x)), Node.RED);
					// 将爷爷节点右旋
					rotateRight(parentOf(parentOf(x)));
				}
				// 父节点是右节点
			} else {
				// 获得爷爷的右子节点（右叔叔节点）
				NodeKV y = leftOf(parentOf(parentOf(x)));
				// 如果叔叔节点是红色
				if (colorOf(y) == NodeKV.RED) {
					// 将自身的父节点设置成黑色
					setColor(parentOf(x), NodeKV.BLACK);
					// 将叔叔节点设置成黑色
					setColor(y, NodeKV.BLACK);
					// 将爷爷节点设置成红色
					setColor(parentOf(parentOf(x)), Node.RED);
					// 指向爷爷节点
					x = parentOf(parentOf(x));
					// 叔叔节点是黑色
				} else {
					// 如果自生是左节点
					if (x == leftOf(parentOf(x))) {
						// 指向父节点
						x = parentOf(x);
						// 右旋
						rotateRight(x);
					}
					// 设置父节点是黑色
					setColor(parentOf(x), Node.BLACK);
					// 设置爷爷节点是红色
					setColor(parentOf(parentOf(x)), Node.RED);
					// 将爷爷节点左旋
					rotateLeft(parentOf(parentOf(x)));
				}
			}
		}
		// 将根节点设置为黑色
		root.color = NodeKV.BLACK;
	}

	private static <K, V> boolean colorOf(NodeKV<K, V> p) {
		return (p == null ? NodeKV.BLACK : p.color);
	}

	private static <K, V> NodeKV<K, V> parentOf(NodeKV<K, V> p) {
		return (p == null ? null : p.parent);
	}

	private static <K, V> void setColor(NodeKV<K, V> p, boolean c) {
		if (p != null)
			p.color = c;
	}

	private static <K, V> NodeKV<K, V> leftOf(NodeKV<K, V> p) {
		return (p == null) ? null : p.left;
	}

	private static <K, V> NodeKV<K, V> rightOf(NodeKV<K, V> p) {
		return (p == null) ? null : p.right;
	}

	/** From CLR */
	private void rotateLeft(NodeKV<K, V> p) {
		if (p != null) {
			NodeKV<K, V> r = p.right;
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
	private void rotateRight(NodeKV<K, V> p) {
		if (p != null) {
			NodeKV<K, V> l = p.left;
			p.left = l.right;
			if (l.right != null)
				l.right.parent = p;
			l.parent = p.parent;
			if (p.parent == null)
				root = l;
			else if (p.parent.right == p)
				p.parent.right = l;
			else
				p.parent.left = l;
			l.right = p;
			p.parent = l;
		}
	}

}
