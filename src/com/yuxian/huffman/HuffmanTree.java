package com.yuxian.huffman;

public abstract class HuffmanTree implements Comparable<HuffmanTree>{
	
	public final int frequency;
	public HuffmanTree(int freq) {
		this.frequency = freq;
	}

	@Override
	public int compareTo(HuffmanTree f) {
		return this.frequency-f.frequency;
	}
	public String toString() {
		return String.valueOf(this.frequency);
	}
}

class HuffmanLeaf extends HuffmanTree{
	public final char val;
	
	public HuffmanLeaf(int freq, char val) {
		super(freq);
		this.val = val;
	}
}

class HuffmanNode extends HuffmanTree{

	public final HuffmanTree left,right;
	public HuffmanNode(HuffmanTree left, HuffmanTree right) {
		super(left.frequency + right.frequency);
		this.left = left;
		this.right = right;
	}
}