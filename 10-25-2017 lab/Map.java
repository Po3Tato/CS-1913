// Alan Koval, koval048@umn.edu, Lab #7
class Map<KeyType, ValueType>{
  private int validCap = 0;
  private KeyType[] keys;
  private ValueType[] values;
  public Map(int length){

    if(length < 0){
      throw new IllegalArgumentException("Length must be non-negative.");
    }

    keys = (KeyType[])(new Object[length]);
    values = (ValueType[])(new Object[length]);
  }

  public void put(KeyType key, ValueType value){
    int keyIndex = keyIndexOf(key);
    if(keyIndex > -1){
      keys[keyIndex] = key;
      values[keyIndex] = value;
      return;
    }
    // there does not exist a key with the right value (to replace)
    // so add a new one if possible
    if(validCap < keys.length){
      keys[validCap] = key;
      values[validCap] = value;
      validCap++;
    }else{
      // no space!
      throw new IllegalStateException("No more space in keys!");
    }
  }

  public ValueType get(KeyType key){
    int index = keyIndexOf(key);
    if(index != -1){
      return values[index];
    }
    throw new IllegalArgumentException("Specified key does not exist!");
  }

  public boolean isIn(KeyType key){
    return keyIndexOf(key) != -1;
  }

  private int keyIndexOf(KeyType key){
    for(int i = 0; i < validCap; i++){
      if(keys[i] == null && key == null || (key != null && key.equals(keys[i]))){
        return i;
      }
    }
    return -1;
  }
}
/* test output:
No negatives
true
false
true
true
true
false
Ginny
Lavender
null
Wormtail
No Joanne
true
true
Gellert
Ginny
Hermione
null
null
No Draco
*/
