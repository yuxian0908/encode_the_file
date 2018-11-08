package com.yuxian.huffman;

import java.util.ArrayList;

/*
 * Min priorityQueue
 * */
public class MyPriorityQueue<T extends Comparable<T>> {
	private ArrayList<T> store = new ArrayList<T>();
	
	public void insert(T ele) {
		// psuedo code
		/*
		 * store.add(ele)
		 * while(parent && ele<parent){
		 * 		swap(ele, parent)
		 * }
		 * */
		
		store.add(ele);
		int index = store.size()-1;
		while( index/2>=0 && ele.compareTo(store.get((index-1)/2))<1 ) {
			int parent = (index-1)/2;
			ele = store.get(parent);
			store.set(parent, store.get(index));
			store.set(index, ele);
			index = parent;
			ele = store.get(parent);
			if(parent==0) break;
		}
	}
	
	public T deleteFirst() {
		
		return store.get(0);
	}
	
	public void printTree() {
		System.out.println(store.toString());
	}
	
	public static void main(String[] args) {
		MyPriorityQueue<Integer> test = new MyPriorityQueue<>();
		test.insert(3);
		test.printTree();
		test.insert(1);
		test.printTree();
		test.insert(2);
		test.printTree();
		test.insert(4);
		test.printTree();
		test.insert(0);
		test.printTree();
	}
}
