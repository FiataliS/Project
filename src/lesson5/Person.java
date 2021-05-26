package lesson5;

public class Person {
    String fio;
    String email;
    String position;
    String telephone;
    int salary;
    int age;
    private String[] lineName = {"ФИО", "Должность", "Email", "Телефон", "Зарплата", "Возраст"};
    private int[] lengthString = {27, 21, 24, 12, 7, 3};

    public Person(String fio, String position, String email, String telephone, int theSalary, int age) {
        this.fio = fio;
        this.email = email;
        this.position = position;
        this.telephone = telephone;
        this.salary = theSalary;
        this.age = age;
    }
    public void inf(){
        String[] name = {this.fio, this.position, this.email,this.telephone, Integer.toString(this.salary), Integer.toString(this.age) };
        String[] workLine = align(name);
        for (int i = 0; i < lineName.length; i++) {
            System.out.print(lineName[i]+ ": " + workLine[i] + "| ");
        }
    }

    public String[] align(String[] arr){ // выравниваем все строки по столбцам.
        for (int i = 0; i < arr.length; i++) {
            StringBuilder sB = new StringBuilder(arr[i]);
            if (sB.length() < lengthString[i]){
                for (int j = sB.length(); j < lengthString[i]; j++) {
                    sB.insert(sB.length(), " ");
                }
                arr[i] = sB.toString();
            }
         }
    return arr;
    }
}
