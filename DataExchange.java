
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * This class serves for data exchange between the "data.json" JSON file and the hash table. It
 * provides functions that can build a Room instance from a JSONObject or build a JSONObject from a
 * Room instance, and that can adding or deleting JSONObject to the only JSONArray in the file. In
 * other word, this class provides methods that enable the data exchange between local non-volatile
 * storage and the volatile hash table. However, the adding and deleting methods are of extremely
 * bad efficiency as JSON file is not suitable for potentially infinite stream of data. If back-end
 * level needs to update this file (i.e. modify the information of an existing room), call
 * deleteJSON() method with parameter of the original room reference and then call addJSON() method
 * with parameter of the updated room reference.
 * 
 * @author Haining Qiu
 *
 */
public class DataExchange {

  private final static String FILE_NAME = "data.json"; // file name
  
  private final static String DORM_NAME = "dormName"; // JSONObject key
  private final static String ROOM_NUMBER = "roomNo"; // JSONObject key
  private final static String RESIDENT = "resident"; // JSONObject key
  private final static String NAME = "name"; // JSONObject key
  private final static String TEST = "test"; // JSONObject key

  /**
   * Checks if the file exists, is initialized, and is ready for JSON operations.
   * 
   * @return true if the file exists, is initialized, and ready for JSON operations
   */
  public static boolean isInitialized() {
    File file = new File(FILE_NAME);
    if (!file.exists()) {
      return false;
    }
    try {
      FileReader fileR = new FileReader(FILE_NAME);
      if (fileR.read() != (int) '[') {
        fileR.close();
        return false;
      }
      fileR.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      return false;
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  /**
   * Creates the file if not existed, and initializes/resets the file to be a JSON file with one
   * empty JSONArray that can store information of each room in a JSONObject. Call this method if in
   * need of a deletion of all data in the file to reset the file. The created file will be named
   * "data.json" inside the current working directory (the project file).
   */
  public static void fileInitialization() {
    try (FileWriter fileW = new FileWriter(FILE_NAME)) {
      new FileWriter(FILE_NAME, false).close(); // delete all data in the file
      JSONArray jsonArray = new JSONArray();
      fileW.write(jsonArray.toJSONString());
      fileW.close();
    } catch (IOException e) {
      System.out.println("File initialization failed.");
      e.printStackTrace();
    }

  }

  /**
   * Constructs a JSONObject from a Room instance
   * 
   * @param room the Room instance passed from back-end level
   * @return a JSONObject that is to be stored in the file
   */
  public static JSONObject buildJSONObj(Room room) {
    JSONObject roomObj = new JSONObject();
    JSONObject residentObj = new JSONObject();
    if (room.isEmpty()) {
      residentObj = null;
    } else {
      residentObj.put(NAME, room.getResident().getName());
      residentObj.put(TEST, room.getResident().getResult());
    }
    roomObj.put(DORM_NAME, room.getDormName());
    roomObj.put(ROOM_NUMBER, room.getRoomNum());
    roomObj.put(RESIDENT, residentObj);
    return roomObj;
  }
  
  /**
   * Constructs a Room instance from a object (cast to a JSONObject) of a room.
   * 
   * @param roomObj JSONObject of a room
   * @return the Room instance constructed
   */
  public static Room buildRoomInstance(Object roomObj) {
    String dormName = (String) ((JSONObject) roomObj).get(DORM_NAME);
    long roomNum = (Long) ((JSONObject) roomObj).get(ROOM_NUMBER);
    if (((JSONObject) roomObj).get(RESIDENT) == null) { // empty room
      Room room = new Room(dormName, roomNum);
      return room;
    } else { // non-empty room with a resident
      String name = (String) ((JSONObject) ((JSONObject) roomObj).get(RESIDENT)).get(NAME);
      Boolean test = (Boolean) ((JSONObject) ((JSONObject) roomObj).get(RESIDENT)).get(TEST);
      Resident resident = new Resident(name, test);
      Room room = new Room(dormName, roomNum, resident);
      return room;
    }
  }

  /**
   * Adds the room information to the file in the form of JSONObject. This is done by parsing all
   * data in the file to a JSONArray, deleting all data in the file, adding a new JSONObject to that
   * JSONArray and writing it back. This will result in extremely low efficiency when dealing with
   * large data size, but this is the only way to modify this JSON file since JSON file is not
   * suitable for saving potentially infinite stream of data. We could create multiple JSON files
   * instead, but that would make the hash table implementation extremely useless.
   * 
   * @param room the Room instance to be added
   * @return true if the file is successfully modified
   */
  public static boolean addJSON(Room room) {
    if (!isInitialized()) {
      fileInitialization();
    }
    JSONObject roomObj = buildJSONObj(room);
    JSONParser parser = new JSONParser();
    try {
      JSONArray arr = (JSONArray) parser.parse(new FileReader(FILE_NAME)); // fetch the original
                                                                           // JSONArray
      arr.add(roomObj); // add new Room as a JSONObject to this JSONArray
      try {
        new FileWriter(FILE_NAME, false).close(); // delete all data in the file
        FileWriter file = new FileWriter(FILE_NAME);
        file.write(arr.toJSONString()); // write the new JSONArray to the file
        file.flush();
        file.close();
        return true;
      } catch (IOException e) {
        System.out.println("Failed to write to file.");
        e.printStackTrace();
        return false;
      }
    } catch (FileNotFoundException e) {
      System.out.println("File Not Found.");
      e.printStackTrace();
      return false;
    } catch (ParseException e) {
      System.out.println("Error in File Content.");
      e.printStackTrace();
      return false;
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
  }

  /**
   * Deletes the room information from the file in the form of JSONObject. This is done by parsing
   * all data in the file to a JSONArray, deleting all data in the file, removing the JSONObject
   * from that JSONArray and writing it back. This will result in extremely low efficiency when
   * dealing with large data size, but this is the only way to modify this JSON file since JSON file
   * is not suitable for saving potentially infinite stream of data. We could create multiple JSON
   * files instead, but that would make the hash table implementation extremely useless.
   * 
   * @param room the Room instance to be removed
   * @return true if the file is successfully modified
   */
  public static boolean deleteJSON(Room room) {
    if (!isInitialized()) { // file has not been initialized
      return false;
    }
    JSONObject roomObj = buildJSONObj(room);
    JSONParser parser = new JSONParser();
    try {
      JSONArray arr = (JSONArray) parser.parse(new FileReader(FILE_NAME)); // fetch the original
                                                                           // JSONArray
      if (!arr.remove(roomObj)) { // remove this Room as a JSONObject from this JSONArray
        return false;
      }
      try {
        new FileWriter(FILE_NAME, false).close(); // delete all data in the file
        FileWriter file = new FileWriter(FILE_NAME);
        file.write(arr.toJSONString()); // write the new JSONArray to the file
        file.flush();
        file.close();
        return true;
      } catch (IOException e) {
        System.out.println("Failed to write to file.");
        e.printStackTrace();
        return false;
      }
    } catch (FileNotFoundException e) {
      System.out.println("File Not Found.");
      e.printStackTrace();
      return false;
    } catch (ParseException e) {
      System.out.println("Error in File Content.");
      e.printStackTrace();
      return false;
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
  }

  /**
   * Returns the size of the parsed JSONArray (i.e. the number of room JSONObject stored in this
   * file). Use this method as a bound of the index (e.g. i < size() - 1 when iterating through).
   * 
   * @return the size of the parsed JSONArray, -1 if exceptions thrown.
   */
  public static int size() {
    JSONParser parser = new JSONParser();
    try {
      JSONArray arr = (JSONArray) parser.parse(new FileReader(FILE_NAME));
      return arr.size();
    } catch (FileNotFoundException e) {
      System.out.println("File Not Found.");
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ParseException e) {
      System.out.println("Error in File Content.");
      e.printStackTrace();
    }
    return -1;
  }

  /**
   * Loads the specific room in the parsed JSONArray pointed by the index and returns a Room
   * instance that is built from that room information. Repeatedly call this method to load all data
   * from the file into the hash table array.
   * 
   * @param i the index of the parsed JSONArray
   * @return a Room instance built from the room pointed by the index, null if exceptions thrown.
   */
  public static Room loadOne(int i) {
    JSONParser parser = new JSONParser();
    try {
      JSONArray arr = (JSONArray) parser.parse(new FileReader(FILE_NAME));
      return buildRoomInstance(arr.get(i));
    } catch (FileNotFoundException e) {
      System.out.println("File Not Found.");
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ParseException e) {
      System.out.println("Error in File Content.");
      e.printStackTrace();
    }
    return null;
  }
}
