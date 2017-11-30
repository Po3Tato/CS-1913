//  POTTERY. Driver class, for testing. Each comment shows a point value (for a
//  total of 40 points) and what it should print.

class Pottery
{

//  MAIN. Harry Potter and the Hairier Pottery.

  public static void main(String [] args)
  {
    FamilyTree family = new FamilyTree("Al");

    family.addParents("Al",    "Harry",  "Ginny");
    family.addParents("Harry", "James",  "Lily" );
    family.addParents("Ginny", "Arthur", "Molly");

    try
    {
      family.addParents("Joanne", "Peter", "Anne");
    }
    catch (IllegalArgumentException ignore)
    {
      System.out.println("No Joanne.");  //  2 No Joanne.
    }

    System.out.println(family.isDescendant("Joanne", "Joanne"));  //  2 false

    System.out.println(family.isDescendant("Al", "Al"));          //  2 true
    System.out.println(family.isDescendant("Al", "Harry"));       //  2 true
    System.out.println(family.isDescendant("Al", "Ginny"));       //  2 true
    System.out.println(family.isDescendant("Al", "James"));       //  2 true
    System.out.println(family.isDescendant("Al", "Lily"));        //  2 true
    System.out.println(family.isDescendant("Al", "Arthur"));      //  2 true
    System.out.println(family.isDescendant("Al", "Molly"));       //  2 true
    System.out.println(family.isDescendant("Al", "Joanne"));      //  2 false

    System.out.println(family.isDescendant("Harry", "Harry"));    //  2 true
    System.out.println(family.isDescendant("Harry", "Al"));       //  2 false
    System.out.println(family.isDescendant("Harry", "James"));    //  2 true
    System.out.println(family.isDescendant("Harry", "Lily"));     //  2 true
    System.out.println(family.isDescendant("Harry", "Ginny"));    //  2 false
    System.out.println(family.isDescendant("Harry", "Arthur"));   //  2 false
    System.out.println(family.isDescendant("Harry", "Molly"));    //  2 false
    System.out.println(family.isDescendant("Harry", "Joanne"));   //  2 false

    System.out.println(family.isDescendant("Ginny", "Arthur"));   //  2 true
    System.out.println(family.isDescendant("Arthur", "Ginny"));   //  2 false
  }
}
