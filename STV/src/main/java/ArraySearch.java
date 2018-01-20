/* Se cauta o valuare untr-un sir de cel mult 100 valori*/

public class ArraySearch {

    public static void main(String[] args) {
        Integer[] arr = new Integer[]{1,2,3,4,5,6,7,8,9,10, null};
        System.out.println(ArraySearch.include(arr, null));
    }

    public static <T> Boolean include(T[] arr, T val) {
        for (T el : arr) {
            if (el != null && el.equals(val) || el == val) {
                return true;
            }
        }

        return false;
    }
}
