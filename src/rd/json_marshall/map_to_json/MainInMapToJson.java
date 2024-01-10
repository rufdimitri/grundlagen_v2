package rd.json_marshall.map_to_json;

import com.google.gson.reflect.TypeToken;
import rd.json_marshall.map_to_json.model.CachedPdfFile;
import rd.json_marshall.map_to_json.model.FileIdentity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainInMapToJson {
    public static void main(String[] args) {
        System.out.println("-------------------- simpleMap -------------");
        //create object and marshall
        Map<String, Integer> initialSimpleMapObject = simpleMap();
        String simpleMapStringIntegerJsonString = JsonUtil.marshallToJson(initialSimpleMapObject);
        System.out.println("object initial: " + initialSimpleMapObject);
        System.out.println("json-string: " + simpleMapStringIntegerJsonString);
        //unmarshall
        Map<String,Integer> simpleMapStringIntegerObject = JsonUtil.unmarshallFromJson(simpleMapStringIntegerJsonString, new TypeToken<>() {});
        System.out.println("object recovered: " + simpleMapStringIntegerObject);

        System.out.println("----------- complexMap -----------");
        //create object and marshall
        Map<Integer, List<CachedPdfFile>> initialComplexMapObject = cachedFilesPerFileIdentityHashCode();
        System.out.println("object initial: " + initialComplexMapObject);
        String complexMapJsonString = JsonUtil.marshallToJson(initialComplexMapObject);
        System.out.println("json-string: " + complexMapJsonString);
        //unmarshall
        Map<Integer, List<CachedPdfFile>> complexMapObject = JsonUtil.unmarshallFromJson(complexMapJsonString, new TypeToken<>(){});
        System.out.println("object recovered: " + complexMapObject);
    }

    static Map<String,Integer> simpleMap() {
        Map<String,Integer> map = new HashMap<>();
        map.put("Abc", 3);
        map.put("Abcd", 4);
        map.put("Abcde", 5);
        return map;
    }

    static List<Integer> list() {
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(7);
        list.add(11);
        return list;
    }

    static Map<Integer, List<CachedPdfFile>> cachedFilesPerFileIdentityHashCode() {
        Map<Integer, List<CachedPdfFile>> result = new HashMap<>();
        FileIdentity fileIdentity = new FileIdentity("file.txt", "folder1", 256L, 512L);
        CachedPdfFile cachedPdfFile = new CachedPdfFile(List.of("page1", "page2"), fileIdentity, 559L);
        result.put(777, List.of(cachedPdfFile, cachedPdfFile));
        return result;
    }
}
