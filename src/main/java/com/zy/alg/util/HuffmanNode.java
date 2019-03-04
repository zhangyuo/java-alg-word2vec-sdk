package com.zy.alg.util;

/**
 * Created by fangy on 13-12-17.
 * 哈夫曼树结点接口
 */
public interface HuffmanNode extends Comparable<HuffmanNode> {

    void setCode(int c);

    void setFrequency(int freq);

    int getFrequency();

    void setParent(HuffmanNode parent);

    HuffmanNode getParent();

    HuffmanNode merge(HuffmanNode sibling);
}
