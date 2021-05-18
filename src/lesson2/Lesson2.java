package lesson2;

public class Lesson2 {
    public static void main(String[] args) {

//1. Задать целочисленный массив, состоящий из элементов 0 и 1. Например:
//[ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ]. С помощью цикла и условия заменить 0 на 1, 1 на 0;
        int[] arr1 = {1, 1, 0, 0, 1, 0, 1, 1};
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] == 0) {
                arr1[i] = 1;
            } else {
                arr1[i] = 0;
            }
        }

//2. Задать пустой целочисленный массив размером 8. С помощью цикла заполнить его значениями 0 3 6 9 12 15 18 21;
        int[] arr2 = new int[8];
        int x = 0;
        for (int i = 1; i < arr2.length; i++) {
            arr2[i] = x +=3;
        }

//3. Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ] пройти по нему циклом, и числа меньшие 6 умножить на 2;
        int[] arr3 = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        for (int i = 0; i < arr3.length; i++) {
            if (arr3[i] > 6) {
                arr3[i] *= 2;
            }
        }

//4. Создать квадратный двумерный целочисленный массив (количество строк и столбцов одинаковое),
// и с помощью цикла(-ов) заполнить его диагональные элементы единицами;
        int[][] arr4 = new int[8][8];
        for (int i = 0; i < arr4.length; i++) {
            arr4[i][i] = 1;
        }

// 5. ** Задать одномерный массив и найти в нем минимальный и максимальный элементы (без помощи интернета);
        int[] arr5 = {3, 5, 3, -2, 11, 4, 5, 2, 3, 8, 18, 1};
        int min = arr5[0], max = arr5[0];
        for (int i = 1; i < arr5.length; i++) {
            if (min > arr5[i]) {
                min = arr5[i];
            } else if (max < arr5[i]) {
                max = arr5[i];
            }
        }

//6. ** Написать метод, в который передается не пустой одномерный целочисленный массив, метод должен вернуть true,
// если в массиве есть место, в котором сумма левой и правой части массива равны.
// Примеры: checkBalance([2, 2, 2, 1, 2, 2, || 10, 1]) → true, checkBalance([1, 1, 1, || 2, 1]) → true,
// граница показана символами ||, эти символы в массив не входят.
        int[] arr6 = {1, 1, 1, 2, 1};
        checkBalance(arr6);

    }

    static boolean checkBalance(int arr[]){
        int arrLenght = arr.length - 1;
        int sumLeft = 0, sumRight = 0;
        boolean equally= false;
        int checks = arrLenght;
        for (int i = 0; i < checks; i++) { // проверки
            for (int j=0; j <= arrLenght; j++){
                if (j!=arrLenght){
                    sumLeft += arr[j];
                } else {
                    sumRight += arr[arrLenght];
                }
            }
            if (sumLeft == sumRight){
                equally = true;
                break;
            } else if (sumLeft < sumRight){
                break;
            }
            arrLenght --;
            sumLeft = 0;
        }
        return equally;
    }
}
