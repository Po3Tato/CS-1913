// Alan Koval, project #3

class ConcordanceTable{

  // this is the only accessible method from this class. It returns a
  // string that is the concordance table for the file at filePath
  public static String fromFile(String filePath){
    Nomenclator reader = new Nomenclator(filePath, false);

    // make our tree
    ConcordanceBST tree = new ConcordanceBST();

    while(reader.hasNext()){
      tree.addTerm(reader.nextName(), reader.nextNumber());
    }

    return tree.toTable();
  }

  // where all the messy stuff goes
  private static class ConcordanceBST{
    // a node object for use in the linked list to hold line numbers
    private class NumberNode{
      private int number;
      private NumberNode next;
      private NumberNode(int number){
        this.number = number;
        this.next = next;
      }
    }

    // a node object for use in the BST to hold file keywords
    private class BSTNode{
      private String name;
      private BSTNode right, left;
      // a pointer to the first and last node in the linked list representing the line numbers
      // we need the first to print the line numbers in order and the last to add to the end of the list
      private NumberNode firstListNode, lastListNode;
      private BSTNode(String name){
        this.name = name;
        this.right = right;
        this.left = left;
      }
    }

    // the root of the BST holding file keywords
    private BSTNode root;

    private void addTerm(String name, int lineNumber){
      if(root == null){
        NumberNode newNumberNode = new NumberNode(lineNumber);
        root = new BSTNode(name);
        root.firstListNode = newNumberNode;
        root.lastListNode = newNumberNode;
      }
      // transverse as necessary. finalNode is the BSTNode in which the line number will be added to the linked list
      BSTNode parent = null, workingNode = root, finalNode = null;
      while(workingNode != null){
        parent = workingNode;
        int compareVal = name.compareTo(workingNode.name);
        // the terms should be ordered, with lexicographically smaller on the left and larger on the right
        if(compareVal < 0){
          workingNode = workingNode.left;
        }else if(compareVal > 0){
          workingNode = workingNode.right;
        }else{
          // this term is already in the tree
          finalNode = workingNode;
          break;
        }
      }

      // term is not in the tree
      if(finalNode == null){
        NumberNode newNumberNode = new NumberNode(lineNumber);
        // add a new BSTNode
        BSTNode newNode = new BSTNode(name);
        newNode.firstListNode = newNumberNode;
        newNode.lastListNode = newNumberNode;
        // add it to the correct side of the parent
        if(name.compareTo(parent.name) < 0){
          parent.left = newNode;
        }else{
          parent.right = newNode;
        }
        return;
      }

      // add a new NumberNode to finalNode (since we are traversing the file in order, we only need to check
      // if the last node has the same line number)
      if(finalNode.lastListNode.number != lineNumber){
        finalNode.lastListNode.next = new NumberNode(lineNumber);
        finalNode.lastListNode = finalNode.lastListNode.next;
      }
    }

    private String toTable(){
      return toTable(root);
    }

    private String toTable(BSTNode rootNode){
      // transverse depth first, going left, root, right to preserve order
      if(rootNode == null){
        return "";
      }
      // most names are not more than 2 tabs wide. If so, it doesn't really matter that much,
      // it just won't look so pretty
      String centerValue = rootNode.name + "\t";
      if(rootNode.name.length() < 8){
        centerValue += "\t";
      }
      // traverse our linked list. Note that we're certain that there's at least one element there
      NumberNode workingNode = rootNode.firstListNode;
      while(workingNode != null){
        centerValue += String.format("%05d", workingNode.number);
        if(workingNode.next != null){
          centerValue += " ";
        }

        workingNode = workingNode.next;
      }

      return toTable(rootNode.left) + centerValue + "\n" + toTable(rootNode.right);
    }
  }
}
