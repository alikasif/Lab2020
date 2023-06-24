package rippling;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SynonymousQueryTest {

    @Test
    public void testSynonym() {
        Map<String, String> synonymMap = new HashMap<>();

        synonymMap.put("a", "b");
        synonymMap.put("b", "b");
        synonymMap.put("x", "y");
        synonymMap.put("y", "y");

        String[] sentences = {"a is x", "b is x"};

        List<String[]> sentencesList = new ArrayList<>();
        sentencesList.add(sentences);

        SynonymChecker synonymChecker = new SynonymChecker();
        Map<String, Boolean> stringBooleanMap = synonymChecker.checkSimilarity(synonymMap, sentencesList);
        System.out.println(stringBooleanMap);
        for(Map.Entry<String, Boolean> entryMap : stringBooleanMap.entrySet()) {
            Assert.assertTrue(entryMap.getValue());
        }
    }

    @Test
    public void testSynonym2Sentences() {
        Map<String, String> synonymMap = new HashMap<>();

        synonymMap.put("a", "b");
        synonymMap.put("b", "b");
        synonymMap.put("x", "y");
        synonymMap.put("y", "y");

        String[] sentences1 = {"a is x", "b is x"};
        String[] sentences2 = {"a is x", "b is y"};

        List<String[]> sentencesList = new ArrayList<>();
        sentencesList.add(sentences1);
        sentencesList.add(sentences2);

        SynonymChecker synonymChecker = new SynonymChecker();
        Map<String, Boolean> stringBooleanMap = synonymChecker.checkSimilarity(synonymMap, sentencesList);
        System.out.println(stringBooleanMap);
        for(Map.Entry<String, Boolean> entryMap : stringBooleanMap.entrySet()) {
            Assert.assertTrue(entryMap.getValue());
        }
    }

    @Test
    public void testSynonym2SentencesDifferent() {
        Map<String, String> synonymMap = new HashMap<>();

        synonymMap.put("a", "b");
        synonymMap.put("b", "b");
        synonymMap.put("x", "y");
        synonymMap.put("y", "y");

        String[] sentences1 = {"a is x", "b is x"};
        String[] sentences2 = {"a is x", "b is z"};

        List<String[]> sentencesList = new ArrayList<>();
        sentencesList.add(sentences1);
        sentencesList.add(sentences2);

        SynonymChecker synonymChecker = new SynonymChecker();
        Map<String, Boolean> stringBooleanMap = synonymChecker.checkSimilarity(synonymMap, sentencesList);
        System.out.println(stringBooleanMap);
        String key = sentences1[0]+"|"+sentences1[1];
        Assert.assertTrue(stringBooleanMap.get(key));

        String key2 = sentences2[0]+"|"+sentences2[1];
        Assert.assertFalse(stringBooleanMap.get(key2));
    }

    @Test
    public void testSynonymMap() {

        String[] synonym1 = {"a", "b"};
        String[] synonym2 = {"b", "c"};
        String[] synonym3 = {"d", "a"};

        String[] synonym8 = {"x", "y"};
        String[] synonym4 = {"y", "z"};
        String[] synonym5 = {"x", "z"};

        String[] synonym6 = {"p", "q"};
        String[] synonym7 = {"m", "n"};

        List<String[]> synonymList = new ArrayList<>();
        synonymList.add(synonym1); synonymList.add(synonym2); synonymList.add(synonym3); synonymList.add(synonym4);
        synonymList.add(synonym8); synonymList.add(synonym7); synonymList.add(synonym6); synonymList.add(synonym5);

        SynonymChecker synonymChecker = new SynonymChecker();
        Map<String, String> synonymMap = synonymChecker.prepareSynonymMap2(synonymList);

        Assert.assertEquals("a", synonymMap.get("b"));
        Assert.assertEquals("a", synonymMap.get("a"));
        Assert.assertEquals("a", synonymMap.get("c"));
        Assert.assertEquals("a", synonymMap.get("d"));

        Assert.assertEquals("x", synonymMap.get("x"));
        Assert.assertEquals("x", synonymMap.get("y"));
        Assert.assertEquals("x", synonymMap.get("z"));

        Assert.assertEquals("p", synonymMap.get("p"));
        Assert.assertEquals("p", synonymMap.get("q"));

        Assert.assertEquals("m", synonymMap.get("n"));
        Assert.assertEquals("m", synonymMap.get("m"));
    }
}
