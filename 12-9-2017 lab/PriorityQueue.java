class PriorityQueue<Base>{

  private class Node{
    private Base object;
    private int  rank;
    private Node left, right;

    private Node(Base object, int rank){
      this.object = object;
      this.rank = rank;
      left = null;
      right = null;
    }
  }

  private Node root;

  public PriorityQueue(){
    root = null;
  }

  public void enqueue(Base object, int rank){
    if(rank < 0){
      throw new IllegalArgumentException("The rank must be non-negative.");
    }
    if(root == null){
      root = new Node(object, rank);
      return;
    }

    Node workingNode = root, prevNode = null;
    while(workingNode != null){
      // the loop will execute at least once
      prevNode = workingNode;
      // left subtree has rank less than or equal to root
      if(rank <= workingNode.rank){
        workingNode = workingNode.left;
      }else{
        workingNode = workingNode.right;
      }
    }

    if(rank <= prevNode.rank){
      prevNode.left = new Node(object, rank);
    }else{
      prevNode.right = new Node(object, rank);
    }
  }

  public Base dequeue(){
    if(isEmpty()){
      throw new IllegalStateException("The priority queue is empty.");
    }
    // we're looking for the lowest rank, which will be the leftmost subtree
    Node workingNode = root, parent = null;
    while(workingNode.left != null){
      parent = workingNode;
      workingNode = workingNode.left;
    }
    if(parent != null){
      // fill in the gap
      parent.left = workingNode.right;
    }else{
      // we want to remove the root node
      root = workingNode.right;
    }
    return workingNode.object;
  }

  public boolean isEmpty(){
    return root == null;
  }

}
