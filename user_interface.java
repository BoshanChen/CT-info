// --== CS400 File Header Information ==--
// Name: Boshan Chen
// Email: bchen275@wisc.edu
// Team: CT
// TA: Mu Cai
// Lecturer: Florian Heimerl
// Notes to Grader: <optional extra notes>
import java.util.Scanner;

/**
 * This class implements a user interface including operations add, delete, update, search, and
 * clear the information about COVID-19 test results of university housing residents.
 * 
 * @author Boshan Chen
 *
 */
public class user_interface {
    static private UniversityHousing table = new UniversityHousing();

    /**
     * Add an entry of key (in the form of a string containing dorm name + room number)
     * and value (a Room object) to the hashtable. It will be failed if the dorm is already
     * existed.
     * 
     * @param sc scanner to read user input.
     * @return true if added successfully, false otherwise.
     */
    public static boolean add(Scanner sc) {
        // dorm name
        String dorm = "";
        System.out.println("Please enter your dorm name:");
        while (true) {
            dorm = sc.nextLine();
            if (containsOnlyLetters(dorm))
                break;
            System.out.println("illegal dorm name! Please enter again:");
        }
        dorm = cap_string(dorm);
        // dorm number
        long num;
        System.out.println("Please enter your room number:");
        while (true) {
            try {
                num = Integer.parseInt(sc.nextLine());
                if (num > 100 && num < 10000) {
                    break;
                }
                System.out.println("illegal room number! Please try again:");
            } catch (Exception e) {
                System.out.println("illegal room number! Please try again:");
            }
        }
        // check if the room is existed
        if (table.containsKey(dorm + num)) {
            System.out
                .println("This room is already existed! Please try another room or use 'update'.");
            return false;
        }
        // user's name
        String name = "";
        System.out.println("Please enter your name:");
        while (true) {
            name = sc.nextLine();
            if (containsOnlyLetters(name))
                break;
            System.out.println("illegal name! Please enter again:");
        }
        name = cap_string(name);
        // user's test result
        System.out.println("Please enter your test result:(postive/negative/null)");
        String result = "";
        Boolean test = null;
        while (true) {
            result = sc.nextLine();
            if (containsOnlyLetters(result)) {
                if (result.equalsIgnoreCase("positive")) {
                    test = true;
                    break;
                } else if (result.equalsIgnoreCase("negative")) {
                    test = false;
                    break;
                } else if (result.equalsIgnoreCase("null")) {
                    test = null;
                    break;
                }
            }
            System.out.println("illegal result! Please enter again:");
        }
        // create Resident obj
        Resident stu = new Resident(name, test);
        // create Room obj
        Room room = new Room(dorm, num, stu);

        return table.put(dorm + num, room);// I will change it to table.userPut when finished

    }

    /**
     * Delete an entry with the given key (in the form of a string containing 
     * dorm name + room number) from user.
     * 
     * @param sc scanner to read user input.
     * @return the Room object has been deleted, null if the room doesn't exist.
     */
    public static Room delete(Scanner sc) {
        // dorm name
        String dorm = "";
        System.out.println("Please enter your dorm name:");
        while (true) {
            dorm = sc.nextLine();
            if (containsOnlyLetters(dorm))
                break;
            System.out.println("illegal dorm name! Please enter again:");
        }
        dorm = cap_string(dorm);
        // dorm number
        long num;
        System.out.println("Please enter your room number:");
        while (true) {
            try {
                num = Integer.parseInt(sc.nextLine());
                if (num > 100 && num < 10000) {
                    break;
                }
                System.out.println("illegal room number! Please try again:");
            } catch (Exception e) {
                System.out.println("illegal room number! Please try again:");
            }
        }
        return table.remove(dorm + num);
    }

    /**
     * Update a user's test result. This need the user has added his/her information
     * before. Otherwise, it will be failed and suggest user to try 'add'.
     * 
     * @param sc sanner to read user input.
     * @return true if update successfully, false otherwise.
     */
    public static boolean update(Scanner sc) {
        // dorm name
        String dorm = "";
        System.out.println("Please enter your dorm name:");
        while (true) {
            dorm = sc.nextLine();
            if (containsOnlyLetters(dorm))
                break;
            System.out.println("illegal dorm name! Please enter again:");
        }
        dorm = cap_string(dorm);
        // dorm number
        long num;
        System.out.println("Please enter your room number:");
        while (true) {
            try {
                num = Integer.parseInt(sc.nextLine());
                if (num > 100 && num < 10000) {
                    break;
                }
                System.out.println("illegal room number! Please try again:");
            } catch (Exception e) {
                System.out.println("illegal room number! Please try again:");
            }
        }
        // update operation
        if (!table.containsKey(dorm + num))
            System.out.println("The room does not existed! Please try 'add'.");
        return table.updates(dorm + num);
    }

    /**
     * Search the resident's test result with given room info (dorm name + room number).
     * This needs the resident's information has been added before.
     * 
     * @param sc scanner to read user input.
     * @return the Resident object, null if the room info doesn't exist.
     */
    public static Resident search(Scanner sc) {
        // dorm name
        String dorm = "";
        System.out.println("Please enter your dorm name:");
        while (true) {
            dorm = sc.nextLine();
            if (containsOnlyLetters(dorm))
                break;
            System.out.println("illegal dorm name! Please enter again:");
        }
        dorm = cap_string(dorm);
        // dorm number
        long num;
        System.out.println("Please enter your room number:");
        while (true) {
            try {
                num = Integer.parseInt(sc.nextLine());
                if (num > 100 && num < 10000) {
                    break;
                }
                System.out.println("illegal room number! Please try again:");
            } catch (Exception e) {
                System.out.println("illegal room number! Please try again:");
            }
        }
        if (!table.containsKey(dorm + num))
            return null;
        return table.get(dorm + num).getResident();
    }

    /**
     * Clear all information in the table. This will ask user for a second time
     * verification to continue the clear operation.
     * 
     * @param sc scanner to read user input.
     */
    public static void clear(Scanner sc) {
        while (true) {
            System.out.println(
                "Caution: this operation will delete all the data, are you sure to contiune?(y/n)");
            String ans = sc.nextLine();
            if (ans.equalsIgnoreCase("y")) {
                table.clear();
                return;
            } else if (ans.equalsIgnoreCase("n")) {
                System.out.println("Operation canceled.");
                return;
            }
        }
    }

    /**
     * Help to list the menu.
     * 
     */
    private static void menu() {
        line();
        System.out
            .println("(A):Add test result. This will need your name, dorm name, and room number.\n"
                + "(D):Delete test result. This will need your dorm name and room number.\n"
                + "(M):Show the menu."
                + "(U):Update test result. If you have added your test result before, then use this to 'add' your information.\n"
                + "(S):Search test result. This will need your dorm name and room number.\n"
                + "(Q):Quit." + "(*):Clear all information.");
        line();
    }

    /**
     * Help to create a line.
     * 
     */
    private static void line() {
        for (int i = 0; i < 50; ++i) {
            System.out.print("*-");
        }
        System.out.println("*");
    }

    /**
     * Help to read the first character from user input and convert it to lowercase.
     * 
     * @param sc scanner to read user input
     * @return the first character from user's input
     */
    private static char firstChar(Scanner sc) {
        String input = sc.nextLine().toLowerCase();
        char first = '\0';
        if (input.length() == 0 || input == null) {
            return first;
        }
        return first = input.charAt(0);
    }

    /**
     * Help to 'formalize' user input. For example, if the user input is "abCd EfG",
     * the output will be "Abcd Efg".
     * 
     * @param string the string need formalized
     * @return a formalized string
     */
    private static String cap_string(String string) {
        String[] input_string = string.toLowerCase().split(" ");
        string = "";
        for (int i = 0; i < input_string.length; ++i) {
            string = input_string[i].substring(0, 1).toUpperCase() + input_string[i].substring(1);
        }
        return string;
    }

    /**
     * Help to check if the string contains only letters.
     * 
     * @param s string need to be checked.
     * @return true if the string contains only letters, false otherwise.
     */
    private static boolean containsOnlyLetters(String s) {
        boolean onlyLetters = true;
        char[] c = null;
        if (s != null && !s.isEmpty()) {
            c = s.toCharArray();
            for (int i = 0; i < c.length; ++i) {
                if (!Character.isLetter(c[i])) {// once a char is digit, assign legal true and
                    onlyLetters = false; // break
                    break;
                }
            }
        }

        return onlyLetters;
    }

    public static void main(String[] arg) {
        Scanner sc = new Scanner(System.in);

        char instr = '\0';
        line();
        System.out.println("Welcome to COVID-19 information of University Housing!\n"
            + "In this application you will be able to add your test result, and delete or update it anytime!\n"
            + "You can also search others' information if you know their name, dorm, and room number.");
        menu();
        while (instr != 'q') {
            System.out.println("Enter your action:");
            instr = firstChar(sc);

            if (instr == 'a') {
                if (add(sc))
                    System.out.println("added successfully!");
                else
                    System.out.println("added failed :( Please try again.");
                continue;
            } else if (instr == 'd') {
                Room room = delete(sc);
                if (room == null)
                    System.out.println("The room doesn't exist! Please try another one.");
                else
                    System.out.println("Room:" + room.getDormName() + ", " + room.getRoomNum()
                        + " has been removed.");
                continue;
            } else if (instr == 'm') {
                menu();
            } else if (instr == 'u') {
                boolean update = update(sc);
                System.out.println("The result has been changed to" + update + ".");
            } else if (instr == 's') {
                Resident stu = search(sc);
                String result = "";
                if (stu == null)
                    System.out.println("The room doesn't exist! Please try 'add'.");
                else {
                    if (stu.getResult() != null) {
                        if (stu.getResult().equals(true))
                            result = "positive";
                        else
                            result = "negative";
                        System.out.println("The resident in this room is " + stu.getName()
                            + ", and the test result is " + result + ".");
                    } else {
                        System.out.println("The resident in this room is " + stu.getName()
                            + ", and the resident hasn't taken any test yet.");
                    }
                }

            } else if (instr == 'q')
                break;
            else if (instr == '*')
                clear(sc);
            else {
                System.out.println("Unknown action! Please try again!");
            }
        }

    }

}
