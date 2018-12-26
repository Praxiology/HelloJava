package com.tree.rbtree;

import java.util.Map;

public class NodeKV<K,V> implements Map.Entry<K,V> {
	
	 public static final boolean RED   = false;
	 public static final boolean BLACK = true;
	
	K key;
    V value;
    NodeKV<K,V> left;
    NodeKV<K,V> right;
    NodeKV<K,V> parent;
    boolean color = BLACK;

	public NodeKV(K key2, V value2, Object object) {
	}

	@Override
	public K getKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V setValue(V value) {
		// TODO Auto-generated method stub
		return null;
	}

}
