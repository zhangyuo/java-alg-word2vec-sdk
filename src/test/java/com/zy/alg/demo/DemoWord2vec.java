package com.zy.alg.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Set;

import com.zy.alg.word2vec.VectorModel;
import com.zy.alg.word2vec.Word2Vec;
import com.zy.alg.util.Tokenizer;

public class DemoWord2vec {

    public static void main(String[] args) throws IOException {

        /** word2vec模型训练 **/
        String trainFilePath = "E:/项目/word2vec/Word2Vector/prepare/搜索词_title_最终语料4.seg.txt";
        String modelFilePath = "E:/项目/word2vec/Word2Vector/prepare/搜索词_title_最终语料4.seg.model";

        /** char2vec模型训练 **/
//		String trainFilePath = "E:/zy_code/Word2Vector/prepare/taskcopus.txt";
//		String modelFilePath = "E:/zy_code/Word2Vector/prepare/charvecmodel.txt";
//		
        readByJava(trainFilePath, modelFilePath);
//		testVector(modelFilePath);
    }

    public static void readByJava(String trainFilePath, String modelFilePath) throws IOException {
        Word2Vec wv = new Word2Vec.Factory()
                .setMethod(Word2Vec.Method.Skip_Gram).setNumOfThread(20)
                .setVectorSize(200).setFreqThresold(1).build();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    new FileInputStream(trainFilePath), "utf-8"));
            int lineCount = 0;
            for (String line = br.readLine(); line != null; line = br
                    .readLine()) {
                wv.readTokens(new Tokenizer(line, " "));
                lineCount = lineCount + 1;
            }
            br.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        wv.training();
        /**saveModel保存的方法影响是否是明文(utf-8)**/
        wv.saveModel(new File(modelFilePath));
    }

    public static void testVector(String modelFilePath) {

        VectorModel vm = VectorModel.loadFromFile(modelFilePath);
        Set<VectorModel.WordScore> result1 = Collections.emptySet();

        String query = "会议";
        System.out.println(vm.getVectorSize() + "维");
        System.out.println("查询词： " + query + "\n");
        result1 = vm.similar(query);
        int num = 0;
        for (VectorModel.WordScore we : result1) {
            if (num++ >= 15) break;
            System.out.println(we.name + " :\t" + we.score);
        }
    }

}
