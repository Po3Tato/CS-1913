//  SHAPES. Public tests for the classes RECTANGLE and SQUARE. Comments show
//  what each test must print, and how many points it is worth.

class Shapes
{
  public static void main(String[] args)
  {
    Rectangle wreck = new Rectangle(3, 5);

    System.out.println(wreck.side(0));      //  3   1 point.
    System.out.println(wreck.side(1));      //  5   1 point.
    System.out.println(wreck.side(2));      //  3   1 point.
    System.out.println(wreck.side(3));      //  5   1 point.
    System.out.println(wreck.area());       //  15  1 point.
    System.out.println(wreck.perimeter());  //  16  1 point.

    Square nerd = new Square(7);

    System.out.println(nerd.side(0));       //  7   1 point.
    System.out.println(nerd.side(1));       //  7   1 point.
    System.out.println(nerd.side(2));       //  7   1 point.
    System.out.println(nerd.side(3));       //  7   1 point.
    System.out.println(nerd.area());        //  49  1 point.
    System.out.println(nerd.perimeter());   //  28  1 point.
  }
}
