package com.zy.alg.prepare;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * @author zhangycqupt@163.com
 * @date 2018/08/23 23:33
 */
public class DataProcess {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream("G:\\project\\word2vec\\input\\ser.corpus.filter.csv"), "utf-8"));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(
                new FileOutputStream("G:\\project\\word2vec\\output\\taskcopus-test.txt"), "utf-8"), true);
        String line;
        while ((line = br.readLine()) != null) {
            String[] seg = line.split(",");
            if (seg.length == 10) {
                String corpus = seg[8] + "。" + seg[9];
                String corpus1 = corpus.toLowerCase();
                if (corpus1.equals("")
                        || corpus1.equals("null")
                        || corpus1 == null) {
                    continue;
                }
                String[] charsegen = corpus1.split("[\u4e00-\u9fa5]");
                String[] charsegzh = corpus1.split("[^\u4e00-\u9fa5]");
                if (charsegen.length == 0) {
                    String[] charseg = charsegzh[0].split("");
                    for (int m = 0; m < charseg.length; m++) {
                        pw.write(charseg[m] + " ");
                    }
                    pw.write("\n");
                } else {
                    for (int i = 0, j = 0; i < charsegzh.length & j < charsegen.length; i++, j++) {
                        String[] charseg = charsegzh[i].split("");
                        j += charsegzh[i].length();
                        for (int m = 0; m < charseg.length; m++) {
                            pw.write(charseg[m] + " ");
                        }
                        if (j >= charsegen.length) {
                            break;
                        }
                        pw.write(charsegen[j] + "");
                        i += charsegen[j].length() - 1;
                        j--;
                    }
                    pw.write("\n");
                }
            }
        }
        br.close();
        pw.close();
    }

}
