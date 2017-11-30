// Alan Koval, lab #11
class Deque<Base>{
  private class Node{
    private Node left, right;
    private Base value;
    private Node(Base value, Node left, Node right){
      this.left = left;
      this.right = right;
      this.value = value;
    }
  }

  private Node head;
  public Deque(){
    head = new Node(null, null, null);
    // the left is the rear, the right the front (rather arbitrarily, if I don't say so myself)
    head.left = head;
    head.right = head;
  }

  public void enqueueFront(Base value){
    Node toInsert = new Node(value, head, head.right);
    toInsert.left.right = toInsert;
    toInsert.right.left = toInsert;
  }

  public void enqueueRear(Base value){
    Node toInsert = new Node(value, head.left, head);
    toInsert.left.right = toInsert;
    toInsert.right.left = toInsert;
  }

  public Base dequeueFront(){
    if(isEmpty()){
      throw new IllegalStateException("Deque is empty.");
    }
    Node toDelete = head.right;
    toDelete.left.right = toDelete.right;
    toDelete.right.left = toDelete.left;
    return toDelete.value;
  }

  public Base dequeueRear(){
    if(isEmpty()){
      throw new IllegalStateException("Deque is empty.");
    }
    Node toDelete = head.left;
    toDelete.left.right = toDelete.right;
    toDelete.right.left = toDelete.left;
    return toDelete.value;
  }

  public boolean isEmpty(){
    return head.right == head;
  }

}
/* test output:
true
No dequeueFront.
No dequeueRear.
false
C
B
A
true
A
B
C
true
C
B
A
true
A
B
C
true
*/
