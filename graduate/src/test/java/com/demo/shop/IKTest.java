package com.demo.shop;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IKTest {
    @Test
    public void testIk() throws Exception {
        String text = "检测奶粉中的微生物，微量元素和脂肪";
        //创建分词对象
        Analyzer anal = new IKAnalyzer(true);
        StringReader reader = new StringReader(text);
        //分词
        TokenStream ts = anal.tokenStream("", reader);
        CharTermAttribute term = ts.getAttribute(CharTermAttribute.class);
        ts.reset();
        //遍历分词数据
        while (ts.incrementToken()) {
            System.out.print(term.toString() + "|");
        }
        ts.close();
        reader.close();
        System.out.println();
    }

    @Test
    public void testDfs() {
        List<List<Integer>> array = new ArrayList<>();
        List<Integer> arr1 = new ArrayList() {{
            add(1);
            add(4);
            add(3);
            add(6);
            add(7);
        }};
        List<Integer> arr2 = new ArrayList() {{
            add(1);
            add(2);
            add(3);
            add(5);
            add(8);
        }};
        List<Integer> arr3 = new ArrayList() {{
            add(1);
            add(4);
            add(5);
            add(6);
            add(9);
        }};
        array.add(arr1);
        array.add(arr2);
        array.add(arr3);

        List<List<Integer>> ret = new ArrayList<>();
        dfs(0, array, new ArrayList<>(), ret);
        Set<Set<Integer>> sets=new HashSet<>();
        for (List<Integer> list : ret) {
            sets.add(new HashSet<>(list));
        }
        //合理，这个例子很合理！
        List<Set<Integer>> retList = new ArrayList<>(sets);
        int i = 0;
        while (i < retList.size()) {
            int j = 0;
            while (j < retList.size()) {
                if (retList.get(j).size() <= retList.get(i).size() || !retList.get(j).containsAll(retList.get(i))) {
                    j++;
                } else {
                    /**
                     * 有bug，此时retList.get(i)已经变了
                     */
                    retList.remove(j);
                }
            }
            i++;
        }
        System.out.println(retList);

        System.out.println(sets);
        System.out.println(sets.size());
    }


    @Test
    public void testCombineWords(){
        String pattern="<span style='color: red;font-weight: bolder;'>(.*?)</span>";
        Pattern p = Pattern.compile(pattern);
        String text1="<span style='color: red;font-weight: bolder;'>我爱</span>你中中";
        String text2="我爱<span style='color: red;font-weight: bolder;'>你</span><span style='color: red;font-weight: bolder;'>中</span><span style='color: red;font-weight: bolder;'>中</span>";
        Matcher m = p.matcher(text2);

        /**
         * 有重复的词，应该放到一个Set里面去一下重
         */
        Set<String> matchPatternSet = new HashSet<>();
        while (m.find()) {
            matchPatternSet.add(m.group(1));
        }
        for (String matchPattern : matchPatternSet) {
            text1 = Pattern.compile(matchPattern).matcher(text1).replaceAll("<span style='color: red;font-weight: bolder;'>" + matchPattern + "</span>");
        }
        System.out.println(text1);
    }

    private void dfs(int index, List<List<Integer>> array, List<Integer> tmp, List<List<Integer>> ret) {
        if (index >= array.size()) {
            ret.add(new ArrayList(tmp));
            return;
        }
        for (int i = 0; i < array.get(index).size(); i++) {
            tmp.add(array.get(index).get(i));
            dfs(index + 1, array, tmp, ret);
            tmp.remove(array.get(index).get(i));
        }
    }
}
