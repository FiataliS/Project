package lesson5;

public class MainLesson5 {
    static Person[] persArray = new Person[5];

    public static void addPerson(){
        persArray[0] = new Person("Иванов Иван Иванович", "Инженер", "ivivan@mailbox.com", "89534123412", 30000, 30);
        persArray[1] = new Person("Иванова Надежда Ивановна", "Кассир", "Ivanova@mailbox.com", "89564362451", 45000, 40);
        persArray[2] = new Person("Степанов Иван Иванович", "Дворник", "Megatron@mailbox.com", "89222123612", 15000, 52);
        persArray[3] = new Person("Петров Николай Геннадьевич", "Слесарь сантехник", "Dvornik2000@mailbox.com", "89541422331", 35000, 41);
        persArray[4] = new Person("Смирнов Алексей Валерьевич", "Генеральный директор", "Smirnov1945@mailbox.com", "89777777777", 100000, 54);
    }

    public static void findAgeFrom(int findAge){
        for (int i = 0; i < persArray.length ; i++) {
            if (persArray[i].age > findAge){
                persArray[i].inf();
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        addPerson();
        findAgeFrom(40);
    }
}
