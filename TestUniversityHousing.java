import org.json.simple.parser.JSONParser;

public class TestUniversityHousing {
    public static boolean test1() {
        DataExchange.fileInitialization();
        JSONParser parser = new JSONParser();
        Room room1 = new Room("Waters", 1001);
        Room room2 = new Room("Cole", (long)431, new Resident("Henry", false));
        Room testRoom1 = null;
        Room testRoom2 = null;
        
        DataExchange.addJSON(room1);
        testRoom1 = DataExchange.loadOne(0);
        DataExchange.addJSON(room2);
        testRoom2 = DataExchange.loadOne(1);
        System.out.println("size: " + DataExchange.size());
        
        UniversityHousing test = new UniversityHousing();
        test.loadData();
        System.out.println("loadData() test: " + test.size());

        Resident resident = new Resident("Bai", false);
        Room room3 = new Room("Cole", 466, resident);
        System.out.println(
            "userPutTest " + test.userPut(room3.getDormName() + room3.getRoomNum(), room3));
        System.out.println(test.size());
        System.out.println(test.get(room3.getDormName() + room3.getRoomNum()));

        System.out.println("removeResTest: " + test.update("Cole",(long) 431, true));
        return true;
    }

    public static void main(String[] args) {
        System.out.println(test1());
    }

}
