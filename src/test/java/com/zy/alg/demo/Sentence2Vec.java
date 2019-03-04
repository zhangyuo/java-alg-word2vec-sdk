package com.zy.alg.demo;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import com.zy.alg.word2vec.VectorModel;

public class Sentence2Vec {

	public static void main(String[] args) throws IOException{
		
		String modelFilePath = "E:/项目/模型数据资源库-all/WordVec4GuideTag";
		// 输入一个句子并计算句子向量
		String Sentence2words = "本人 09年 研究生 毕业 ， 具有 多年 的 c 和 java 开发 经验 ， 希望 可以 合作 ， 联系 扣扣   270   907   838  ";
		String[] Sent2words = Sentence2words.split(" ");
		Sent2vec(Sent2words, modelFilePath);
		
	}
	
	public static void Sent2vec(String[] sent2words, String modelFilePath) throws IOException{
		
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(
				new FileOutputStream(modelFilePath + ".sent2vec"), "utf-8"), true);
		VectorModel vm = VectorModel.loadFromFile(modelFilePath);
		float[] sentvec = new float[vm.getVectorSize()];
		for (String word : sent2words) {
			if (!vm.getWordMap().containsKey(word)) {
				continue;
			}
			//累加
			for (int i = 0; i < sentvec.length; i++) {
				sentvec[i] += vm.getWordVector(word)[i];
			}
		}
		//求平均
		for (int j = 1; j < sentvec.length; j++) {
			sentvec[j] = sentvec[j] / sentvec.length;
			pw.print(sentvec[j] + " ");
		}
		pw.print("\n");
		pw.close();
	}
	
}
