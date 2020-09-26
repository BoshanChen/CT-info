/**
 * This class inherits from the HashTableMap class and provides a majortiy of back-end functions
 * that can be called using a User Interface provided by the front-end developers. It is able to
 * loads all data from the JSON file provided by Data Wrangler into an array of LinkedList, to
 * remove data in the array while also deleting from the file, and to add data into both the array
 * and the file.
 * 
 * NOTE made by Haining !!DO NOT DELETE!!PLEASE READ!!
 * 
 * 1) This class inherits my own implementation of HashTableMap, so it may not suit yours. However,
 * be assured that my implementation of HashTableMap passed every GradeScope tests and plenty of
 * tests of my own, and it is reasonably safe to be adopted. In theory, as long as your 
 * HashTableMap class passed all tests, this class should work with it after minimal editing. 
 * 2) Front-end Dev should mainly focus on how to call the correct functions after the user inputs 
 * and how to assemble and pass user input values as parameters to these methods (such as get(), 
 * remove(), userPut() etc.), and if user input is invalid (pay attention to what is a valid input) 
 * to these methods, you need to prompt the user. If the user input is valid but is causing 
 * back-end problems (you check this by passing the user input in and see if functions will return 
 * special values or throw exceptions due to adding the same key, removing or getting non-existing 
 * key), you need to prompt the user too.
 * 3) Data Wranglers (yeah including me) should provide methods for loading data from and saving 
 * data (including deletion) to a file in another class.
 * 4) I am not sure what Test Engineer should do, but I think testing front-end UI and the back-end 
 * functions being called corresponding to the user input should be very essential.
 * 5) This class should be developed by Back-end Devs, but I have to start working on this class 
 * ahead because this class links the data file and the front-end UI. I have to say that it is more 
 * easily to be developed just by a single person for conjunctions between different classes. 
 * What's causing this problem is that no one seems actively starting. WE NEED TO GET MOVING or we 
 * won't be able to finish this before the deadline since next week will be filled with Midterms.
 * 6) We should really provide other teammates codes with COMMENTS & JAVA DOCS. This would not take 
 * too much time but will really save teammates' a lot of time. It improves the conjunction between 
 * classes and help reduce the number of bugs.
 * 
 * @author Haining Qiu
 *
 */
public class UniversityHousing extends HashTableMap<String, Room> {
  
  /**
   * Loads all data from the file into the hashTableArray by repeatedly calling put() method
   * 
   * @return true if all data loaded successfully
   */
  public boolean loadData() {
    // This method should repeatedly calls the put() method without modifying the file
    // NOTE that this method is called when loading data from the file, NOT when dealing with user
    // input
    return false;
  }

  /**
   * Adds a new Room instance provided by the user input into the array and also saves this addition
   * to the file.
   * 
   * @param roomInfo room information in the form of <dorm name + room num> as a String key
   * @param room     the Room instance as the value
   * @return true if the user input is successful
   */
  public boolean userPut(String roomInfo, Room room) {
    // This method should act in the same way as the put() method, and the only difference is that
    // it will also modify the file to save the changes.
    
    // NOTE that this method is called when dealing with the user input, so this function will be
    // called in the user interface. Please check with the front-end dev to ensure its correct
    // functionality and its correct usage.
    
    // NOTE that a pair with the same key cannot be added again and this function will return false.
    return false;
  }

  /**
   * Removes a Room instance from the array, returns that Room instance, and also deletes this room
   * from the file.
   * 
   * @param roomInfo room information in the form of <dorm name + room num> as a String key
   * @return the Room instance that was removed both from the array and the file
   */
  @Override
  public Room remove(String roomInfo) {
    // Make a call to the deletion method provided by Data Wrangler here to modify the file
    
    // NOTE that this method is called when dealing with the user input, so this function will be
    // called in the user interface. Please check with the front-end dev to ensure its correct
    // functionality and its correct usage.

    // NOTE that removing a pair that does not exist will return null reference.
    return super.remove(roomInfo);
  }
  /**
   * Updates the resident's test result with given room info.
   * The room has been checked that is not empty
   * 
   * @param roomInfo room information in the form of <dorm name + room num> as a String key
   * @return the Room instance that was removed both from the array and the file
   */
  public boolean updates(String roomInfo) {
      //updates the resident's test result with given room Info.
      //the room has been checked that is not empty.
    return false;
  }
  
  /**
   * Calculates the position of this room in the array by hashing a string composed of the dorm name
   * and the room number and taking the absolute value of the Room's hashCode() modulus the current
   * capacity of this array.
   * 
   * @param roomInfo the room to be indexed
   * @param length   the current capacity of the array
   * @return the position where this room should be stored in the array
   */


  
}
