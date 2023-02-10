public class FindOrderArray {
    public static void main(String[] args) {
        int[] array = {4, 8, 9, 11, 14};
        System.out.print(findNumber(array, 11) ? "Número encontrado" : "Número não está no array");
    }

    public static boolean findNumber(int[] array, int number) {
        int mid = array.length/2;
        int left = 0;
        int rigth = array.length - 1;
        boolean hasNumber = false;
        do {
            if(array[mid] == number) {
                hasNumber = true;
            }
            else if(array[mid] > number) {
                rigth = mid;
            }
            else {
                left = mid;
            }
            mid = rigth - left / 2;
        } while(!hasNumber && left < rigth);
        return hasNumber;
    }
}
