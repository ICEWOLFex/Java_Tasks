import java.io.Console;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        int n = read.nextInt();
        int m = read.nextInt();
        int[] arr = new int[n];
        int count = 1;
        for(int i = 0; i<n;i++){
            arr[i] = count;
            count++;
        }
        int step = 0;
        String result = "";
        do{
            int path = arr[step];
            step += m-1;
            if(result.isEmpty()){
                result = "" + path;
            }
            else {
                result += path;
            }
            if (step >= n){
                step = step - n;
            }
        }while (arr[step] != 1);
        System.out.println(result);
    }
}