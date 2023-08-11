

public class Main {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);
        int count = 1;
        if(n>0) {
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = count;
                count++;
            }
            result(arr, n, m);
        }
        else {
            int[] arr = new int[Math.abs(n)+2];
            for (int i = 0; i < Math.abs(n)+2; i++) {
                arr[i] = count;
                count--;
            }
            result(arr, n, Math.abs(m));
        }
    }

    private static void result(int[] arr, int n, int m){
        if(n<0){
            n = Math.abs(n)+2;
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
                step -= n;
            }
        }while (arr[step] != 1);
        System.out.println(result);
    }

}