//
//  Tests for CSci 1913 Lab 7
//  James Moen
//  07 Mar 17
//
//  The TRY-CATCH statements catch exceptions thrown by MAP's methods, so that
//  the program can continue to run even if a method fails. We haven't talked
//  about TRY-CATCH'es in lecture yet.
//
//  Each test has a comment that shows what it should print, and how many
//  points it is worth, for a total of 40 points.

//  HOGWARTS. The Hogwarts dating service.

class Hogwarts
{

//  MAIN. Make an instance of MAP and test it.

  public static void main(String [] args)
  {
    Map<String, String> map;

    try
    {
      map = new Map<String, String>(-5);
    }
    catch (IllegalArgumentException ignore)
    {
      System.out.println("No negatives");       //  No negatives  2 points.
    }

    map = new Map<String, String>(5);

    map.put("Harry",     "Ginny");
    map.put("Ron",       "Lavender");
    map.put("Voldemort", null);
    map.put(null,        "Wormtail");

    System.out.println(map.isIn("Harry"));      //  true          2 points.
    System.out.println(map.isIn("Ginny"));      //  false         2 points.
    System.out.println(map.isIn("Ron"));        //  true          2 points.
    System.out.println(map.isIn("Voldemort"));  //  true          2 points.
    System.out.println(map.isIn(null));         //  true          2 points.
    System.out.println(map.isIn("Joanne"));     //  false         2 points.

    System.out.println(map.get("Harry"));       //  Ginny         2 points.
    System.out.println(map.get("Ron"));         //  Lavender      2 points.
    System.out.println(map.get("Voldemort"));   //  null          2 points.
    System.out.println(map.get(null));          //  Wormtail      2 points.

    try
    {
      System.out.println(map.get("Joanne"));
    }
    catch (IllegalArgumentException ignore)
    {
      System.out.println("No Joanne");          //  No Joanne     2 points.
    }

    map.put("Ron",   "Hermione");
    map.put("Albus", "Gellert");
    map.put(null,    null);

    System.out.println(map.isIn(null));         //  true          2 points.
    System.out.println(map.isIn("Albus"));      //  true          2 points.

    System.out.println(map.get("Albus"));       //  Gellert       2 points.
    System.out.println(map.get("Harry"));       //  Ginny         2 points.
    System.out.println(map.get("Ron"));         //  Hermione      2 points.
    System.out.println(map.get("Voldemort"));   //  null          2 points.
    System.out.println(map.get(null));          //  null          2 points.

    try
    {
      map.put("Draco", "Pansy");
    }
    catch (IllegalStateException minnesota)
    {
      System.out.println("No Draco");           //  No Draco      2 points.
    }
  }
}
