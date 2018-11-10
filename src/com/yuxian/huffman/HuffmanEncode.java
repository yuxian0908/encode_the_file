package com.yuxian.huffman;

import java.util.HashMap;
import java.util.Stack;

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
	
	public static int[] getFreqAry(String str) {
		int[] res = new int[256];
		for(char c: str.toCharArray()) {
			res[c]++;
		}
		
		return res;
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
	
	private static class CodeNode{
		HuffmanTree node;
		String prefix;
		public CodeNode(HuffmanTree node, StringBuilder prefix) {
			this.node = node;
			this.prefix = prefix.toString();
		}
	}
	
	public static HashMap<Character, String> getCodeMap(HuffmanTree tree){
		//psuedo code
		/*
		 * class CodeNode{
		 * 		HuffmanTree node;
		 * 		StringBuilder prefix;
		 * }
		 * 
		 * CodeNode root = new CodeNode(tree, prefix)
		 * 
		 * Stack store = new Stack()
		 * store.put(root)
		 * 
		 * while(store is not empty){
		 * 		temp = store.pop()
		 * 		if(temp.node instanceof HuffmanLeaf){
		 * 			map.put()
		 * 		}else if{
		 * 			store.put(new CodeNode(tree.left, prefix))
		 * 			store.put(new CodeNode(tree.right, prefix))
		 * 		}
		 * }
		 * 
		 * */
		
		HashMap<Character, String> header = new HashMap<>();
		StringBuilder prefix = new StringBuilder();
		CodeNode root = new CodeNode(tree, prefix);
		Stack<CodeNode> store = new Stack<>();
		store.push(root);
		while(!store.isEmpty()) {
			CodeNode temp = store.pop();
			if(temp.node instanceof HuffmanLeaf) {
				
				HuffmanLeaf leaf = (HuffmanLeaf) temp.node;
				StringBuilder p = new StringBuilder(temp.prefix);
				header.put(leaf.val, p.toString());
				
			}else if(temp.node instanceof HuffmanNode) {
				
				HuffmanNode node = (HuffmanNode) temp.node;
				StringBuilder p = new StringBuilder(temp.prefix);
				p.append("0");
				store.push(new CodeNode(node.left, p));
				p.deleteCharAt(p.length()-1);
				p.append("1");
				store.push(new CodeNode(node.right, p));
				p.deleteCharAt(p.length()-1);
				
			}
		}
		return header;
	}

	
	public static String encode(String origin, HashMap<Character, String> header) {
		StringBuilder res = new StringBuilder();
		for(char c: origin.toCharArray()) {
			res.append(header.get(c));
		}
		return res.toString();
	}
	
	public static void main(String[] args) {
		
		// test input
		String test = "this is an example for huffman encoding";
		
		// get frequency ary
		int[] freqAry = getFreqAry(test);
		HuffmanTree freqTree = buildFreqTree(freqAry);
		
		// print header
		System.out.println("char\tfreqs\tcode");
		printCode(freqTree, new StringBuilder());
		
		// store header in map
		HashMap<Character, String> header = getCodeMap(freqTree);
		System.out.println(test);
		System.out.println(encode(test, header));
	}
	
}





