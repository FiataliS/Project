package Lesson2_2;

    public class MySizeArrayException extends Exception {
        public MySizeArrayException(String msg) {
            super(msg);
        }
    }
    class SizeArrayException{
        public static void getArr (String arr[][]) throws  MySizeArrayException{
            if (arr.length !=4) throw new MySizeArrayException("Возникло MySizeArrayException\nНеверный размер массива");
        }
    }







