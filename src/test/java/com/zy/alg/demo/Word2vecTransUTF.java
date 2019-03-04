package com.zy.alg.demo;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Map;

import com.zy.alg.word2vec.VectorModel;

public class Word2vecTransUTF {
	
	public static void main(String[] args) throws IOException{
		
		String originalCorpusPath = "E:/项目/word2vec/Word2Vector/prepare/";
		String modelPath = originalCorpusPath+"搜索词_title_最终语料4.seg.model";
		
		VectorModel vm = VectorModel.loadFromFile(modelPath);
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(
				new FileOutputStream(modelPath + ".UTF"), "utf-8"), true);
		
		Map<String, float[]> wordMap = vm.getWordMap();
		pw.println(vm.getWordMap().size() + "\t" +vm.getVectorSize());
		for(Map.Entry<String, float[]> v: wordMap.entrySet()){
			pw.print(v.getKey()+"\t");
			int num = 0;
			for(float vv: v.getValue()){
				num++;
				if(num == v.getValue().length){
					pw.print(vv);
				} else {
					pw.print(vv+"\t");
				}
			}
			pw.println();
		}
		pw.close();
	}

}

