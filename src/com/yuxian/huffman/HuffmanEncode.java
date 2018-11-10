package com.yuxian.huffman;

import java.util.Arrays;

public class HuffmanEncode {
	
	public static HuffmanTree buildFreqTree(int[] freqs) {
		// psuedo code
		/*
		 * store all freqs(>0) in queue
		 * 
		 * while queue.size > 1 
		 * 		small1 = get small from queue
		 * 		small2 = get small from queue
		 * 		node = new HuffmanNode(small1,small2)
		 * 		store node in queue
		 * 
		 * return queue.pop()
		 * 
		 * */
		
		
		MyPriorityQueue<HuffmanTree> queue = new MyPriorityQueue<>();
		for(int i=0; i<freqs.length; i++) {
			if(freqs[i]>0) queue.insert(new HuffmanLeaf(freqs[i], (char) i));
		}
		while(queue.size()>1) {
			HuffmanTree small1 = queue.deleteFirst();
			HuffmanTree small2 = queue.deleteFirst();
			queue.insert(new HuffmanNode(small1, small2));
		}
		return queue.deleteFirst();
	}
	
	public static void printCode(HuffmanTree tree, StringBuilder prefix) {
		// psuedo code
		/*
		 * if tree is Leaf
		 * 		print
		 * 
		 * else if tree is Node
		 * 
		 * 		prefix.append("0")
		 * 		printCode(tree.left, prefix)
		 * 		prefix.deleteCharAt(prefix.length()-1)
		 * 
		 * 		prefix.append("1")
		 * 		printCode(tree.right)
		 * 		prefix.deleteCharAt(prefix.length()-1) 
		 * 
		 * */
		
		if(tree instanceof HuffmanLeaf) {
			HuffmanLeaf leaf = (HuffmanLeaf) tree;
			System.out.println(leaf.val+"\t"+leaf.frequency+"\t"+prefix);
			
		}else if(tree instanceof HuffmanNode) {
			HuffmanNode node = (HuffmanNode) tree;
			
			// left
			prefix.append("0");
			printCode(node.left, prefix);
			prefix.deleteCharAt(prefix.length()-1);
			
			// right
			prefix.append("1");
			printCode(node.right, prefix);
			prefix.deleteCharAt(prefix.length()-1);
		}
	}
	
	public static int[] getFreqAry(String str) {
		int[] res = new int[256];
		for(char c: str.toCharArray()) {
			res[c]++;
		}
		
		return res;
	}
	
	public static void main(String[] args) {
		int[] test = getFreqAry("this is an example for huffman encoding");
		HuffmanTree res = buildFreqTree(test);
		System.out.println("char\tfreqs\tcode");
		printCode(res, new StringBuilder());
	}
}





