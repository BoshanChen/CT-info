import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class test_user_interface {
    private static UniversityHousing table = user_interface.getTable();
    public static boolean testAdd() {
        File file = new File("input.txt");
        try {
            user_interface.execute(file);
        } catch (FileNotFoundException e) {
            System.out.println("no file found");
        }
        
        // expecting size 3
        if(table.size() != 3) return false;
        // expecting null
        if(table.get("Cole1234").getResident() != null) return false;
        // expecting Bc
        if(!table.get("Wat1231").getResident().getName().equals("Bc"))
            return false;
        // expecting null
        if(table.get("Wat1231").getResident().getResult() != null)
            return false;
        return true;
    }
    
    public static boolean testSearch() {
        Scanner sc = null;
        try {
            sc = new Scanner(new File("input2.txt"));
        } catch (FileNotFoundException e) {}
        // expecting "Bc"
        if(!user_interface.search(sc).getName().equals("Bc")) return false;
        // expecting null
        if(user_interface.search(sc).getResult() != null) return false;
        
        return true;
    }
    
    public static boolean testDelete() {
        Scanner sc = null;
        try {
            sc = new Scanner(new File("input3.txt"));
        } catch (FileNotFoundException e) {}
        // expecting size 2
        user_interface.delete(sc);
        if(table.size() != 2) return false;
        // expecting size 1
        user_interface.delete(sc);
        if(table.size() != 1) return false;
        return true;
    }
    
    public static boolean testClear() {
        Scanner sc = null;
        try {
            sc = new Scanner(new File("input5.txt"));
        } catch (FileNotFoundException e) {}
        user_interface.clear(sc);
        if(table.size() != 0) return false;
        return true;
    }
    
    public static boolean testUpdate() {
        Scanner sc = null;
        try {
            sc = new Scanner(new File("input4.txt"));
        } catch (FileNotFoundException e) {}
        user_interface.add(sc);
        // add a resident to an empty room
        user_interface.update(sc);
        if(!table.get("Wat1231").getResident().getName().equals("Bc")) return false;
        // change an existing resident's test result
        user_interface.update(sc);
        if(table.get("Wat1231").getResident().getResult() != false) return false;
        // replace a room with a new resident
        user_interface.update(sc);
        if(table.get("Wat1231").getResident().getResult() != true) return false;
        if(!table.get("Wat1231").getResident().getName().equals("Cb")) return false;
        // remove a resident, leaving an empty room
        user_interface.update(sc);
        if(table.get("Wat1231").getResident() != null) return false;
        return true;
        
    }
    
    public static void main(String[] args) {
        
        System.out.println("testAdd: " + testAdd());
        System.out.println("testSearch: " + testSearch());
        System.out.println("testDelete: " + testDelete());
        System.out.println("testClear: " + testClear());
        System.out.println("testUpdate: " + testUpdate());
        
    }

}
