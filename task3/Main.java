import com.sun.jdi.Value;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    static String value = "";
    static String result = "";
    static List<Values> values = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        File report = new File("report.json");

        Scanner get_path = new Scanner(System.in);

        ObjectMapper mapper = new ObjectMapper();
        System.out.println("Введите путь до файла с результататми тестов");
        Map<?, ?> map = mapper.readValue(Paths.get(get_path.next()).toFile(), Map.class);

        for (Map.Entry<?, ?> entry : map.entrySet()) {
            value = entry.getKey() + "=" + entry.getValue();
        }

        Sort_Values();
        System.out.println("Введите путь до файла с тестами");
        Map<?, ?> map2 = mapper.readValue(Paths.get(get_path.next()).toFile(), Map.class);

        for (Map.Entry<?, ?> entry : map2.entrySet()) {
            result = entry.getKey() + "=" + entry.getValue();
        }

        Generate_Report();

        JSON_Convert();
        System.out.println(result);

        if(report.createNewFile()){
            System.out.println("Файл создан");
            JsonNode jsonNode = mapper.readTree(result);
            mapper.writeValue(new File("src/report.json"), jsonNode);
        }
        else{
            System.out.println("Файл уже существует");
            JsonNode jsonNode = mapper.readTree(result);
            mapper.writeValue(new File("src/report.json"), jsonNode);
        }
    }

    private static void Sort_Values(){
        int index = 9;
        while(index<value.length()){
            int startindex = value.indexOf('=', index);
            index = startindex;
            if(startindex>0){
                int endindex = value.indexOf(',', index);
                index = endindex;
                int id = Integer.parseInt(value.substring(startindex+1, endindex));

                startindex = value.indexOf('=', index);
                endindex = value.indexOf('}', index);
                index = endindex;
                String val = value.substring(startindex+1, endindex);
                    Values vals = new Values(id, val);
                    values.add(vals);

                    index++;
            }
            else{
                break;
            }
        }
    }

    private static void Generate_Report(){
        int startindex = 0;
        int endindex;
        int id, jsonId;
        int count=0;
         while(count<=1000){
             for(int i = 0; i<values.size();i++){
                 Values val = values.get(i);
                 id = val.Id;
                 String findId = "id="+id;
                 startindex = result.indexOf(findId, startindex);
                 endindex = result.indexOf(',',startindex);
                 jsonId = Integer.parseInt(result.substring(startindex+3,endindex));
                 if(id == jsonId){
                     startindex = result.indexOf("value=", startindex);
                    result = new StringBuilder(result).insert(startindex+6, val.getValue()).toString();
                    values.remove(i);
                    startindex = 0;
                 }
             }
             count++;
         }
    }

    private static void JSON_Convert(){
        result = result.replace('=', ':');
        result = result.replace(":",":\"");
        result = result.replace("}", "\"}");
        result = result.replace("\"[", "[");
        result = result.replace(",", "\",");
        result = result.replace("}\"", "}");
        result = result.replace("\"]", "]");
        result = result.replace("values", "\"values\"");
        result = result.replace("value:", "\"value\":");
        result = result.replace("id:", "\"id\":");
        result = result.replace("title:", "\"title\":");
        result = result.replace("tests", "{\"tests\"");
        result = result.replace("\"}]", "}]");
        result = result.replace("]\"}", "]}");
        result = result.replace("failed}", "failed\"}");
        result = result.replace("passed}", "passed\"}");
        result = new StringBuilder(result).insert(result.length(), "}").toString();
    }
}