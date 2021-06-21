package Lesson2_2;

    class MyArrayDataException extends Exception {
        public MyArrayDataException (String msg){super(msg);}
    }

    class ArrayData {

        public static void getArr (String arr[][]) throws MyArrayDataException {
            int result = 0;
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr.length; j++) {
                    try {
                    result += Integer.parseInt(arr[i][j]);
                    } catch (NumberFormatException  e) {
                        throw new MyArrayDataException("Возникло MyArrayDataException\nВ i=" + i + " j=" + j + " массива,\nгде было значение: " + arr[i][j]);
                    }
                }
            }
            System.out.println("Сумма значений массива = " + result);
        }
    }


