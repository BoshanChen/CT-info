/**
 * This class represents a resident who lives in a room of the University Housing with his or her
 * basic information provided. This class in contained in the Room class. The test result is the
 * latest test result of this resident.
 * 
 * @author Haining Qiu
 *
 */
public class Resident {

  private String name; // resident's name
  private Boolean test; // resident's COVID-19 test result 1)TRUE represents a POSITIVE test result;
                        // 2)FALSE represents a NEGATIVE test result; 3)NULL represents NOT TESTED;

  /**
   * Constructs a resident instance with his or her name, and age provided. The COVID-19 test result
   * is set to null if not provided.
   * 
   * @param name resident's name
   * @param age  resident's age
   */
  public Resident(String name) {
    this.name = name;
    this.test = null;
  }

  /**
   * Constructs a resident instance with his or her name, age, and COVID-19 test result provided.
   * 
   * @param name resident's name
   * @param age  resident's age
   * @param test resident's COVID-19 test result
   */
  public Resident(String name, Boolean test) {
    this.name = name;
    this.test = test;
  }
  
  /**
   * Gets the resident's COVID-19 test result.
   * 
   * @return null if the result is unknown, true if this resident was tested POSITIVE.
   */
  public Boolean getResult() {  
    return test;
  }

  /**
   * Gets the resident's name.
   * 
   * @return resident's name
   */
  public String getName() {
    return name;
  }
  
}
