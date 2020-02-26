import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class TeleBookController1 {
    private TeleBook teleBook;
    private Scanner scanner = new Scanner(System.in);


    public TeleBookController1() {
        teleBook = FileUtils.read();
    }


    private void showOperations() {
        System.out.println("Options");
        for (Options value : Options.values()) {
            System.out.println(value);
        }
    }
    private Options chooseOptions(){
        int option = scanner.nextInt();
        scanner.nextLine();
        return Options.convertToOption(option);
    }
    private void executeOption(Options options){
        switch (options) {
            case ADD_CONTACT:
                addContact();
                break;
            case SEARCH_BY_TEL:
                searchByTel();
                break;
            case EARCH_BY_NAME:
                searchByName();
                break;
            case DELETE:
                delete();
                break;
            case CLOSE:
                close();
                break;
        }
    }
    public void loop(){
        Options options = null;
        do {
            showOperations();
            try {
                options = chooseOptions();
                executeOption(options);
            } catch (NoSuchElementException e) {
                System.out.println("Invalid option");
            }
        }
        while (options!=Options.CLOSE);
    }
    private void close(){
        scanner.close();
        try {
            FileUtils.save(teleBook);
            System.out.println("Record of changes");
        } catch (IOException e) {
            System.err.println("Failed to save changes");
        }
        System.out.println("End");
    }


    private void delete() {
        System.out.println("Enter the record name to delete");
        String name = scanner.nextLine();
        boolean removed = teleBook.remove(name);
        if (removed){
            System.out.println("Record deleted");
        }
        else {
            System.out.println("There is no record with this name");
        }
        
    }

    private void searchByName() {
        System.out.println("Enter the phone number or part of it");
        String tel = scanner.nextLine();
        List<Contact> contacts = teleBook.findByTelephone(tel);
        if (contacts.isEmpty()){
            System.out.println("No results");
        }
        else {
            System.out.println("Records found");
            contacts.forEach(System.out::println);
        }

    }

    private void searchByTel() {
        System.out.println("Enter part of the name");
        String text = scanner.nextLine();
        List<Contact> contacts = teleBook.findByName(text);
        if (contacts.isEmpty()){
            System.out.println("No results");
        }
        else {
            System.out.println("Records found");
            contacts.forEach(System.out::println);
        }
    }

    private void addContact() {
        System.out.println("Enter contact name");
        String name = scanner.nextLine();
        System.out.println("Enter phone number");
        String telephone = scanner.nextLine();
        try {
            boolean add = teleBook.add(name, telephone);
            if (add){
                System.out.println("Record added");
            }
            else {
                System.out.println("Could not add record. An entry with this name already exists");
            }
        }
        catch (IllegalAccessError e) {
            System.err.println("The name and phone number cannot be blank");
        }
    }
}
