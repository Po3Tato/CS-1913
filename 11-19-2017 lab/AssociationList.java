// Alan Koval, koval048, Lab #10

class AssociationList<Key, Value>{
  private class Node{
    private Key key;
    private Value value;
    private Node next;
    private Node(Key key, Value value, Node next){
      this.key = key;
      this.value = value;
      this.next = next;
    }
  }

  private Node head;
  public AssociationList(){
    head = new Node(null, null, null);
  }

  public void put(Key key, Value value){
    Node workingNode = head.next;
    while(workingNode != null){
      if(isEqual(workingNode.key, key)){
        workingNode.value = value;
        return;
      }
      workingNode = workingNode.next;
    }
    // the key is not in the linked list, so add it to the beginning of the list
    head.next = new Node(key, value, head.next);
  }

  public boolean isIn(Key key){
    Node workingNode = head.next;
    while(workingNode != null){
      if(isEqual(workingNode.key, key)){
        return true;
      }
      workingNode = workingNode.next;
    }
    return false;
  }

  public Value get(Key key){
    Node workingNode = head.next;
    while(workingNode != null){
      if(isEqual(workingNode.key, key)){
        return workingNode.value;
      }
      workingNode = workingNode.next;
    }
    throw new IllegalArgumentException("Key not found.");
  }

  public void delete(Key key){
    Node beforeNode = head;
    while(beforeNode.next != null){
      Node workingNode = beforeNode.next;
      if(isEqual(workingNode.key, key)){
        // delete the Node
        beforeNode.next = workingNode.next;
        return;
      }
      beforeNode = workingNode;
    }
  }

  private boolean isEqual(Key left, Key right){
    return left == null && right == null || (left != null && left.equals(right));
  }
}

/* Test output:
false
No null
true
false
true
true
true
false
Lavender
Ginny
null
Wormtail
No Joanne
false
true
null
Ginny
Ginny
Hermione
No Dean
*/
