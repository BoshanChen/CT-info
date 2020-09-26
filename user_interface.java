import java.util.Scanner;


public class user_interface{
    static private UniversityHousing table = new UniversityHousing();

    public static boolean add(Scanner sc) {
        //dorm name
        String dorm = "";
        System.out.println("Please enter your dorm name:");
        while(true) {
            dorm = sc.nextLine();
            if(!containsDigit(dorm)) break;
            System.out.println("illegal dorm name! Please enter again:");
        }
        dorm = cap_string(dorm);
        
        //dorm number
        int num ;
        System.out.println("Please enter your room number:");
        while(true) {
            try {
                num = Integer.parseInt(sc.nextLine());
                if(num > 100 && num < 10000) {
                    break;
                }
                System.out.println("illegal room number! Please try again:");
            } catch(Exception e) {
                System.out.println("illegal room number! Please try again:");
            }
        }
        //check if the room is empty
         if(table.containsKey(dorm+num)) {
             System.out.println("This room is already full! Please try another room or use 'update'.");
             return false;
         }
        
        //user's name 
        String name = "";
        System.out.println("Please enter your name:");
        while(true) {
            name = sc.nextLine();
            if(!containsDigit(name)) break;
            System.out.println("illegal name! Please enter again:");
        }
        name = cap_string(name);
        //user's test result
        System.out.println("Please enter your test result:(postive/negative/null)");
        String result = "";
        Boolean test = null;
        while(true) {
            result = sc.nextLine();
            if(!containsDigit(result)) { 
                if(result.equalsIgnoreCase("positive")) {test = true; break;}
                else if (result.equalsIgnoreCase("negative")) {test = false; break;}
                else if (result.equalsIgnoreCase("null")) {test = null; break;}
            }
            System.out.println("illegal result! Please enter again:");
        }
        //create Resident obj
        Resident stu = new Resident(name,test);
        //create Room obj
         Room room = new Room(dorm,num,stu);
         
         return table.put(dorm + num, room);// I will change it to table.userPut when finished
        
    }
    
    public static Room delete(Scanner sc) {
        //dorm name
        String dorm = "";
        System.out.println("Please enter your dorm name:");
        while(true) {
            dorm = sc.nextLine();
            if(!containsDigit(dorm)) break;
            System.out.println("illegal dorm name! Please enter again:");
        }
        dorm = cap_string(dorm);
        //dorm number
        int num ;
        System.out.println("Please enter your room number:");
        while(true) {
            try {
                num = Integer.parseInt(sc.nextLine());
                if(num > 100 && num < 10000) {
                    break;
                }
                System.out.println("illegal room number! Please try again:");
            } catch(Exception e) {
                System.out.println("illegal room number! Please try again:");
            }
        }
        return table.remove(dorm+num);
    }
    
    public static boolean update(Scanner sc) {
        //dorm name
        String dorm = "";
        System.out.println("Please enter your dorm name:");
        while(true) {
            dorm = sc.nextLine();
            if(!containsDigit(dorm)) break;
            System.out.println("illegal dorm name! Please enter again:");
        }
        dorm = cap_string(dorm);
        //dorm number
        int num ;
        System.out.println("Please enter your room number:");
        while(true) {
            try {
                num = Integer.parseInt(sc.nextLine());
                if(num > 100 && num < 10000) {
                    break;
                }
                System.out.println("illegal room number! Please try again:");
            } catch(Exception e) {
                System.out.println("illegal room number! Please try again:");
            }
        }
        // update operation
        if(!table.containsKey(dorm+num)) System.out.println("The room is empty! Please try 'add'.");
        return table.updates(dorm+num);
    }
    
    public static Resident search(Scanner sc) {
        //dorm name
        String dorm = "";
        System.out.println("Please enter your dorm name:");
        while(true) {
            dorm = sc.nextLine();
            if(!containsDigit(dorm)) break;
            System.out.println("illegal dorm name! Please enter again:");
        }
        dorm = cap_string(dorm);
        //dorm number
        int num ;
        System.out.println("Please enter your room number:");
        while(true) {
            try {
                num = Integer.parseInt(sc.nextLine());
                if(num > 100 && num < 10000) {
                    break;
                }
                System.out.println("illegal room number! Please try again:");
            } catch(Exception e) {
                System.out.println("illegal room number! Please try again:");
            }
        }
        if(!table.containsKey(dorm+num)) return null;
        return table.get(dorm+num).getResident();
    }
    
    private static void menu() {
        System.out.println("(A):Add test result. This will need your name, dorm name, and room number.\n"
                          +"(D):Delete test result. This will need your dorm name and room number.\n"
                          +"(U):Update test result. If you have added your test result before, then use this to 'add' your information.\n"
                          +"(S):Search test result. This will need your dorm name and room number.\n"
                          +"(Q): Quit.");
        line();
    }
    private static void line() {
        for(int i = 0; i < 50; ++i) {
            System.out.print("*-");
        }
        System.out.println("*");
    }
    private static char firstChar(Scanner sc) {
        String input = sc.nextLine().toLowerCase();
        char first = '\0';
        if(input.length() == 0||input == null) {
            return first;
        }
        return first = input.charAt(0);
    }
    private static String cap_string(String string) {
        String[] input_string = string.toLowerCase().split(" ");
        string = "";
        for(int i = 0; i < input_string.length; ++i) {
            string = input_string[i].substring(0,1).toUpperCase() + input_string[i].substring(1);
        }
        return string;
    }
    private static boolean containsDigit(String s) {
        boolean legal = false;
        char[] c = null;
        if (s != null && !s.isEmpty()) {
            c = s.toCharArray();
            for (int i = 0; i < c.length; ++i) {
                if (legal = Character.isDigit(c[i])) {// once a char is digit, assign legal true and break
                    break;
                }
            }
        }

        return legal;
    }
    public static void main(String[] arg) {
        Scanner sc = new Scanner(System.in);
        
        char instr = '\0';
        line();
        System.out.println(
            "Welcome to COVID-19 information of University Housing!\n"
            +"In this application you will be able to add your test result, and delete or update it anytime!\n"
            + "You can also search others' information if you know their name, dorm, and room number."
            );
        line();
        menu();
        while(instr != 'q') {
            System.out.println("Enter your action:");
            instr = firstChar(sc);
        
        if(instr == 'a') {
            if(add(sc)) System.out.println("added successfully!");
            else System.out.println("added failed :( Please try again.");
            continue;
        }
        else if(instr == 'd') {
            Room room = delete(sc);
            if(room == null) System.out.println("The room doesn't exist! Please try another one.");
            else System.out.println("Room:" + room.getDormName() + ", " + 
            room.getRoomNum() +" has been removed.");
            continue;
        }
        else if(instr == 'u') {
            boolean update = update(sc);
            System.out.println("The result has been changed to" + update + ".");
        }
        else if(instr == 's') {
            Resident stu = search(sc);
            String result = "";
            if(stu == null) System.out.println("The room doesn't exist! Please try 'add'.");
            else {
                if(stu.getResult() != null) {
                    if(stu.getResult().equals(true)) result = "positive";
                    else result = "negative";
                    System.out.println("The resident in this room is " + 
                    stu.getName() + ", and the test result is " + result + ".");
                } else {
                    System.out.println("The resident in this room is " + 
                        stu.getName() + ", and the resident hasn't taken any test yet.");
                }
            }
            
        }
        else if(instr == 'q') break;
        else {System.out.println("Unknown action! Please try again!");
        }
        }
        
    }

}
