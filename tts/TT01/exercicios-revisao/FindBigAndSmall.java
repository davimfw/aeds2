public class FindBigAndSmall {
    public static void main(String[] args) {
        
    }
    public static int[] FindNumbers(int[] array) {
        int maior = array[0];
        int menor = array[0];
        for(int i = 1; i < array.length; i++) {
            if(array[i] > maior) {
                maior = array[i];
            } else if(array[i] < menor) {
                menor = array[i];
            }
        }
        int[] resp = {menor, maior};
        return resp;
    }
}