import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    private static float x_center;
    private static float y_center;
    private static double rad;
    public static void main(String[] args) {
        Scanner get_path1 = new Scanner(System.in);
        Path path1 = Paths.get(get_path1.next());
        Path path2 = Paths.get(get_path1.next());

        get_first_file(path1);
        int n = get_array_size(path2);
        float[] x_array = new float[n];
        float[] y_array = new float[n];
        try (BufferedReader reader = Files.newBufferedReader(path2, StandardCharsets.UTF_8)) {
            int i = 0;
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    int index = line.indexOf(" ");
                    x_array[i] = Float.parseFloat(line.substring(0, index));
                    y_array[i] = Float.parseFloat(line.substring(index, line.length()));
                    i++;
                }
                catch (NumberFormatException x){
                    System.out.println("В файле должы быть только координаты / Ошибка: " + x);
                    break;
                }
            }
            for(int a = 0; a<n; a++){
                Double result = Math.sqrt(Math.pow((x_array[a] - x_center),2) + Math.pow((y_array[a] - y_center),2));
                if(result < rad){
                    System.out.println(1);
                }
                else if (result == rad){
                    System.out.println(0);
                }
                else if (result > rad){
                    System.out.println(2);
                }
            }
            reader.close();
        } catch (IOException message) {
            System.err.format("Указан неверный путь / ", message);
        }
    }
    private static void get_first_file(Path path){
        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            int count=1;
            String line;
            while ((line = reader.readLine()) != null && count<=2) {
                try {
                    if(count == 1){
                        int index = line.indexOf(" ");
                        x_center = Float.parseFloat(line.substring(0,index));
                        y_center = Float.parseFloat(line.substring(index,line.length()));
                    }
                    else{
                        rad = Integer.parseInt(line);
                    }
                    count++;
                }
                catch (NumberFormatException x){
                    System.out.println("В файле должы быть только координаты целые числа / Ошибка: " + x);
                    break;
                }
            }
            reader.close();
        } catch (IOException message) {
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
}