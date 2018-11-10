package com.yuxian.huffman;

import java.util.HashMap;

public class HuffmanDecode {
	
//	public static HuffmanTree storeHeaderTree(String header) {
//		String[] pairs = header.split(",");
//		
//	}
	
	public static String decode(String origin, HuffmanTree header) {
		
		HuffmanNode temp = (HuffmanNode) header;
		StringBuilder res = new StringBuilder();
		
		for(char c: origin.toCharArray()) {
			if(c=='0') {
				if(temp.left instanceof HuffmanNode) {
					temp = (HuffmanNode) temp.left;
				}else if(temp.left instanceof HuffmanLeaf) {
					HuffmanLeaf leaf = (HuffmanLeaf) temp.left;
					res.append(leaf.val);
					temp = (HuffmanNode) header;
				}
			}else if(c=='1') {
				if(temp.right instanceof HuffmanNode) {
					temp = (HuffmanNode) temp.right;
				}else if(temp.right instanceof HuffmanLeaf) {
					HuffmanLeaf leaf = (HuffmanLeaf) temp.right;
					res.append(leaf.val);
					temp = (HuffmanNode) header;
				}
			}
		}
		
		return res.toString();
	}
	
	public static void main(String[] args) {
//		storeHeaderTree("a:1111,i:1110, :110,e:1011,s:10101,d:10100,f:1001,o:1000,g:01111,x:01110,t:01101,u:01100,h:0101,c:01001,r:01000,n:001,l:00011,p:00010,m:0000,");
		// test input
		String test = "hello world!";

		// get frequency ary
		int[] freqAry = HuffmanEncode.getFreqAry(test);
		
		// get header Tree
		HuffmanTree freqTree = HuffmanEncode.buildFreqTree(freqAry);
		

		// store header in map
		HashMap<Character, String> header = HuffmanEncode.getCodeMap(freqTree);
		
		String str = HuffmanEncode.encode(test, header);
		
		System.out.println(header);
		System.out.println(str);
		System.out.println(decode(str,freqTree));
		
		
	}
}
