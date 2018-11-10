package com.yuxian.huffman;

import java.util.HashMap;

public class main {
	public static void main(String[] args) {
		// origin input
		String origin = "hello world!";

		// get frequency ary
		int[] freqAry = HuffmanEncode.getFreqAry(origin);
		
		// get header Tree
		HuffmanTree freqTree = HuffmanEncode.buildFreqTree(freqAry);
		

		// store header in map
		HashMap<Character, String> header = HuffmanEncode.getCodeMap(freqTree);
		
		String str = HuffmanEncode.encode(origin, header);

		// print header
		System.out.println("char\tfreqs\tcode");
		HuffmanEncode.printCode(freqTree, new StringBuilder());
		System.out.println("");
		System.out.println("origin: "+origin);
		System.out.println("encoded: "+str);
		System.out.println("decoded: "+HuffmanDecode.decode(str,freqTree));
	}
}
