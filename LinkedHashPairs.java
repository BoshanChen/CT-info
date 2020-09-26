// --== CS400 File Header Information ==--
// Name: Boshan Chen
// Email: bchen275@wisc.edu
// Team: CT
// TA: Mu Cai
// Lecturer: Florian Heimerl
// Notes to Grader: <optional extra notes>

/**
 * This class implements the linked entries that will be used in HashTableMap.java
 * 
 * @author Boshan Chen
 *
 */
public class LinkedHashPairs<KeyType, ValueType> {
    private KeyType key;
    private ValueType value;
    private LinkedHashPairs<KeyType, ValueType> next;
    
    LinkedHashPairs(KeyType key, ValueType value) {
        this.key = key;
        this.value = value;
        this.next = null;
  }

  public ValueType getValue() {
        return value;
  }

  public KeyType getKey() {
        return key;
  }
  
  public void setValue(ValueType value) {
        this.value = value;
  }

  public LinkedHashPairs<KeyType, ValueType> getNext() {
        return next;
  }

  public void setNext(LinkedHashPairs<KeyType, ValueType> next) {
        this.next = next;
  }
}
