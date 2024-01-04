package rd.json_marshall.map_to_json;

import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainInMapToJson {
    public static void main(String[] args) {
        //TODO

        String jsonString = JsonUtil.marshallToJson(simpleMap(), new TypeToken<Map<String,Integer>>() {}.getType());
        System.out.println("-------------------- simpleMap -------------\n" + jsonString);

        System.out.println("- list -\n" + JsonUtil.marshallToJson(list(), new TypeToken<List<Integer>>() {}.getType()));

        //jsonString = JsonUtil.marshallToJson(cachedFilesPerFileIdentityHashCode(), new TypeToken<List<Integer>>() {}.getType());
        //System.out.println("- cachedFilesPerFileIdentityHashCode -\n" + jsonString );
        //Map<Integer, List<CachedPdfFile>> cachedFilesPerFileIdentityHashCode = JsonUtil.unmarshallFromJson(jsonString, Map.class, null);
        //System.out.println(cachedFilesPerFileIdentityHashCode.get(777));

        jsonString = JsonUtil.marshallToJson(cachedFilesPerFileIdentityHashCode(), new TypeToken<Map<Integer, List<CachedPdfFile>>>() {}.getType());
        System.out.println("- cachedFilesPerFileIdentityHashCode -\n" + jsonString );

        Map<Integer, List<CachedPdfFile>> cachedFilesPerFileIdentityHashCode2 = JsonUtil.unmarshallFromJson(jsonString, new TypeToken<Map<Integer, List<CachedPdfFile>>>() {}.getType());
        System.out.println(cachedFilesPerFileIdentityHashCode2.get(777));
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
