package com.tree.test;

import java.util.TreeSet;

import org.junit.Test;

import com.tree.rbtree.Node;
import com.tree.rbtree.RBTree;

public class TestBR {
	@Test
	public void testBrTree() {
		RBTree brt = new RBTree();
		brt.put(0);
		brt.put(1);
		brt.put(10);
		brt.put(11);
		brt.put(20);
		brt.put(-1);
		brt.put(-90);
		brt.put(-71);
		brt.printTree();
		
		//TreeMap<String, Object> t = null;
		TreeSet<Object> t =null;
	}

	// null引用可以继续使用么？
	// @Test
	public void testNull() {
		Node root = new Node(1);
		Node lt = root.left;
		lt = new Node(2);
		if (root.left != null) {
			System.err.println("yes");
		} else {
			System.err.println("no");
		}
	}

}
