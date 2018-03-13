// Alan Koval, project #3

// compile: javac Concordance.java
// run: java ConcordanceTester

// note that this program uses itself as its test file

// moving imports from Nomenclator to top
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// testing class w/ main method
class ConcordanceTester{
  public static void main(String[] args){
    System.out.println("--------- Test File (Concordance.java) -----------");
    System.out.print(ConcordanceTable.fromFile("Concordance.java"));
  }
}

// concordance table class, the meat of the project
class ConcordanceTable{
  // this is the only accessible method from this class. It returns a
  // string that is the concordance table for the file at filePath
  public static String fromFile(String filePath){
    Nomenclator reader = new Nomenclator(filePath, true);

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

//
//  NOMENCLATOR. Read names from a Java source file.
//
//    James Moen
//    04 Dec 17
//
//  The version of 01 Dec 17 could not handle /*...*/ comments that extend over
//  multiple lines, but this one can.
//

//  NOMENCLATOR. Read names from a Java source file.  It acts like an ITERATOR,
//  but it has two NEXT methods: one for names, and one for the line numbers of
//  those names.

class Nomenclator
{
  private char              ch;                 //  Current CHAR from READER.
  private static final char eof = (char) 0x00;  //  End of file sentinel.
  private static final char eol = (char) 0x0A;  //  End of line sentinel.
  private int               index;              //  Index into LINE.
  private String            line;               //  Current LINE from READER.
  private boolean           listing;            //  Are we listing the file?
  private String            name;               //  Current name.
  private int               number;             //  Current line number.
  private String            path;               //  Pathname to READER's file.
  private BufferedReader    reader;             //  Read CHARs from here.

//  Constructor. Initialize a new NOMENCLATOR that reads from a text file whose
//  pathname is PATH. If we can't open it then throw an exception. LISTING says
//  whether we should copy the file to standard output as we read it.

  public Nomenclator(String path, boolean listing)
  {
    try
    {
      index = 0;
      line = "";
      this.listing = listing;
      number = 0;
      this.path = path;
      reader = new BufferedReader(new FileReader(path));
      skipChar();
    }
    catch (IOException ignore)
    {
      throw new IllegalArgumentException("Can't open '" + path + "'.");
    }
  }

//  HAS NEXT. Test if there's another name waiting to be read. If so, then read
//  it, so NEXT NAME and NEXT NUMBER can return it and its line number later.

  public boolean hasNext()
  {
    while (true)
    {
      if (Character.isJavaIdentifierStart(ch))
      {
        skipName();
        return true;
      }
      else if (Character.isDigit(ch))
      {
        skipNumber();
      }
      else
      {
        switch (ch)
        {
          case eof:
          {
            return false;
          }
          case '"':
          case '\'':
          {
            skipDelimited();
            break;
          }
          case '/':
          {
            skipComment();
            break;
          }
          default:
          {
            skipChar();
            break;
          }
        }
      }
    }
  }

//  NEXT NAME. If HAS NEXT was true, then return the next name. If HAS NEXT was
//  false, then return an undefined string.

  public String nextName()
  {
    return name;
  }

//  NEXT NUMBER. If HAS NEXT was true, then return the line number on which the
//  next name appears. If HAS NEXT was false, then return an undefined INT.

  public int nextNumber()
  {
    return number;
  }

//  SKIP CHAR. If no more CHARs remain unread in LINE, then read the next line,
//  adding an EOL at the end. If no lines can be read, then read a line with an
//  EOF char in it. Otherwise just read the next char from LINE and return it.

  private void skipChar()
  {
    if (index >= line.length())
    {
      index = 0;
      number += 1;
      try
      {
        line = reader.readLine();
        if (line == null)
        {
          line = "" + eof;
        }
        else
        {
          if (listing)
          {
            System.out.format("%05d ", number);
            System.out.println(line);
          }
          line += eol;
        }
      }
      catch (IOException ignore)
      {
        line = "" + eof;
      }
    }
    ch = line.charAt(index);
    index += 1;
  }

//  SKIP COMMENT. We end up here if we read a '/'. If it is followed by another
//  '/', or by a '*', then we skip a comment. We must skip comments so that any
//  names that appear in them will be ignored.

  private void skipComment()
  {
    skipChar();
    if (ch == '*')
    {
      skipChar();
      while (true)
      {
        if (ch == '*')
        {
          skipChar();
          if (ch == '/')
          {
            skipChar();
            return;
          }
        }
        else if (ch == eof)
        {
          return;
        }
        else
        {
          skipChar();
        }
      }
    }
    else if (ch == '/')
    {
      skipChar();
      while (true)
      {
        if (ch == eof)
        {
          return;
        }
        else if (ch == eol)
        {
          skipChar();
          return;
        }
        else
        {
          skipChar();
        }
      }
    }
  }

//  SKIP DELIMITED. Skip a string constant or a character constant, so that any
//  names that appear inside them will be ignored.  Throw an exception if there
//  is a missing delimiter at the end.

  private void skipDelimited()
  {
    char delimiter = ch;
    skipChar();
    while (true)
    {
      if (ch == delimiter)
      {
        skipChar();
        return;
      }
      else
      {
        switch (ch)
        {
          case eof:
          case eol:
          {
            throw new IllegalStateException("Bad string in '" + path + "'.");
          }
          case '\\':
          {
            skipChar();
            if (ch == eol || ch == eof)
            {
              throw new IllegalStateException("Bad string in '" + path + "'.");
            }
            else
            {
              skipChar();
            }
            break;
          }
          default:
          {
            skipChar();
            break;
          }
        }
      }
    }
  }

//  SKIP NAME. Skip a name, but convert it to a STRING, stored in NAME.

  private void skipName()
  {
    StringBuilder builder = new StringBuilder();
    while (Character.isJavaIdentifierPart(ch))
    {
      builder.append(ch);
      skipChar();
    }
    name = builder.toString();
  }

//  SKIP NUMBER. Skip something that might be a number. It starts with a digit,
//  followed by zero or more letters and digits. We must do this so the letters
//  aren't treated as names.

  private void skipNumber()
  {
    skipChar();
    while (Character.isJavaIdentifierPart(ch))
    {
      skipChar();
    }
  }

//  MAIN. Get a file pathname from the command line. Read a series of names and
//  their line numbers from the file, and write them one per line. For example,
//  the command "java Nomenclator Nomenclator.java" reads names from the source
//  file you are now looking at. This method is only for debugging!

  public static void main(String [] args)
  {
    Nomenclator reader = new Nomenclator(args[0], false);
    while (reader.hasNext())
    {
      System.out.println(reader.nextNumber() + " " + reader.nextName());
    }
  }
}
