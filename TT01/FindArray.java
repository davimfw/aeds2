public class FindArray {
    public static void main(String[] args) {
        int[] array = {6, 3, 7, 0};
        System.out.print(findNumber(array, 0) ? "Número encontrado" : "Número não está no array");
    }

    public static boolean findNumber(int[] array, int number) {
        boolean hasNumber = false;
        for(int i = 0; i < array.length; i++) {
            if(array[i] == number) {
                hasNumber = true;
                i = array.length;
            }
        }
        return hasNumber;
    }
}