package TaskOne;

import java.security.Key;
import java.util.Scanner;

public class UserTest {
    LinkedList list;
    Scanner in;

    public UserTest() {
        this.list = new LinkedList();
        this.in = new Scanner(System.in);
    }

    public void start(){
        System.out.println("Usage:\n1 - add data to the list\n2 - delete data from the list\n3 - get data\n0 - to terminate");
        for(int command = in.nextInt(); command != 0; command = in.nextInt()){
            switch (command){
                case 1: {
                    add();
                    System.out.println(list);
                    break;
                }
                case 2: {
                    delete();
                    System.out.println(list);
                    break;
                }
                case 3: {
                    get();
                    System.out.println(list);
                    break;
                }
                default:
                    System.out.println("Wrong command");
            }
        }
    }

    private void delete() {
        System.out.println("Enter INDEX to delete: ");
        try {
            list.delete(in.nextInt());
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    private void get() {
        System.out.println("Enter KEY to get:");
        try {
            System.out.println(list.getByKey(in.next()));
        }catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void add() {
        System.out.println("Enter KEY: ");
        String key = in.next();
        System.out.println("Enter VALUE: ");
        String value = in.next();
        try {
            list.add(key, value);
        }catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        UserTest test = new UserTest();
        test.start();
    }
}
