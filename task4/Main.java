import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        Path path = Paths.get(args[0]);

        try(BufferedReader reader = Files.newBufferedReader(path,StandardCharsets.UTF_8)) {
            int n = get_array_size(path);
            int[] array = new int[n];
            int i=0;
            String line;
            while ((line = reader.readLine()) != null) {
                    array[i] = Integer.parseInt(line);
                    i++;
            }
            reader.close();
            int final_number = optional_num(array);
            int count = 0;
            for(int a = 0; a<n;a++){
                if (array[a]<final_number) {
                    while(array[a]<final_number){
                        array[a]++;
                        count++;
                    }
                }
                else if(array[a]>final_number){
                    while(array[a]>final_number){
                        array[a]--;
                        count++;
                    }
                }
                else{
                    continue;
                }
            }
            System.out.println(count);
        }catch (IOException message) {
            System.err.format("Указан неверный путь / ", message);
        }
    }

    private static int get_array_size(Path path){
        int n=0;
        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            while (reader.readLine() != null) {
                n++;
            }
            reader.close();
        } catch (IOException message) {
            System.err.format("Указан неверный путь / ", message);
        }
        return n;
    }

    private static int optional_num(int[] arr){
        long average;
        if (arr.length > 0) {
            long sum = 0;
            for (int j = 0; j < arr.length; j++) {
                sum += arr[j];
            }
            average = sum / arr.length;
            return Math.round(average);
        }
        else {
            return 0;
        }
    }
}