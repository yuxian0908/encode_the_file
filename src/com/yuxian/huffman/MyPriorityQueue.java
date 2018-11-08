package com.yuxian.huffman;

import java.util.ArrayList;

/*
 * Min priorityQueue
 * */
public class MyPriorityQueue<T extends Comparable<T>> {
	private ArrayList<T> store = new ArrayList<T>();
	private int size = 0;
	
	public void insert(T ele) {
		// psuedo code
		/*
		 * store.add(ele)
		 * while(parent && ele<parent){
		 * 		swap(ele, parent)
		 * }
		 * */
		
		store.add(ele);
		size = store.size();
		int index = size-1;
		int parent = (index-1)/2;
		int count = 0;
		while( index/2>=0 && ele.compareTo(store.get(parent))<=0 ) {
			ele = store.get(parent);
			store.set(parent, store.get(index));
			store.set(index, ele);
			index = parent;
			ele = store.get(index);
			parent = (index-1)/2;
			if(parent==0) count++;
			if(count==2) break;
		}
	}
	
	public T deleteFirst() {
		// psuedo code
		/*
		 * res = store.get(0)
		 * swap(last, first)
		 * delete last
		 * 
		 * while(ele>child){
		 * 		swap(ele,child)
		 * }
		 * 
		 * */
		if(store.size()==0) {
			return null;
		}
		
		T res = store.get(0);
		T ele = store.get(store.size()-1);
		store.set(0, ele);
		store.remove(store.size()-1);
		size = store.size();
		
		
		int index = 0;
		int child = 0;
		
		while(child<size && size>1) {
			if(size>2) {
				if(child==0) {
					child = 1;
				}
				if(child+1<size && store.get(child).compareTo(store.get(child+1))>0) {
					child++;
				}
				if(store.get(index).compareTo(store.get(child))>0) {
					ele = store.get(child);
					store.set(child, store.get(index));
					store.set(index, ele);
				}
				index = child;
				child=child*2+1;
			}else {
				if(child+1<size && store.get(child).compareTo(store.get(child+1))>0) {
					child++;
					ele = store.get(child);
					store.set(child, store.get(index));
					store.set(index, ele);
				}else {
					break;
				}
			}
			
		}
		
		return res;
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
		test.insert(5);
		test.printTree();
		test.insert(4);
		test.printTree();
		test.insert(0);
		test.printTree();
		test.insert(2);
		test.printTree();
		test.insert(-1);
		test.printTree();
		test.insert(-15);
		test.printTree();
		test.insert(-10);
		test.printTree();
		test.insert(-20);
		test.printTree();
		System.out.println(test.deleteFirst());
//		test.printTree();
		System.out.println(test.deleteFirst());
//		test.printTree();
		System.out.println(test.deleteFirst());
//		test.printTree();
		System.out.println(test.deleteFirst());
//		test.printTree();
		System.out.println(test.deleteFirst());
//		test.printTree();
		System.out.println(test.deleteFirst());
		System.out.println(test.deleteFirst());
		System.out.println(test.deleteFirst());
		System.out.println(test.deleteFirst());
		System.out.println(test.deleteFirst());
		test.printTree();
	}
}
