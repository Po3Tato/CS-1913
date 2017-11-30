// Alan Koval, Lab #12
class FamilyTree{
  private class Node{
    private Node mother, father;
    private String name;
    private Node(String name, Node mother, Node father){
      this.name = name;
      this.mother = mother;
      this.father = father;
    }
  }

  private Node root;

  public FamilyTree(String ego){
    root = new Node(ego, null, null);
  }

  private Node find(String name){
    return find(name, root);
  }

  public Node find(String name, Node findRoot){
    if(findRoot == null){
      return null;
    }

    if(findRoot.name.equals(name)){
      return findRoot;
    }

    Node motherRes = find(name, findRoot.mother);
    if(motherRes != null){
      return motherRes;
    }
    Node fatherRes = find(name, findRoot.father);
    if(fatherRes != null){
      return fatherRes;
    }

    return null;
  }

  public void addParents(String ego, String father, String mother){
    Node self = find(ego);
    if(self == null){
      throw new IllegalArgumentException("That person does not exist.");
    }
    self.father = new Node(father, null, null);
    self.mother = new Node(mother, null, null);
  }

  public boolean isDescendant(String ego, String ancestor){
    Node self = find(ego);
    Node anc = find(ancestor);
    if(self == null || anc == null){
      return false;
    }
    return isDescendant(self, anc);
  }

  private boolean isDescendant(Node root, Node ancestor){
    return find(ancestor.name, root) != null;
  }
}

/* test output:
No Joanne.
false
true
true
true
true
true
true
true
false
true
false
true
true
false
false
false
false
true
false
*/
