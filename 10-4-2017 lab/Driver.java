//  DRIVER. The MAIN method has tests for your class ZILLION. Each test has a
//  comment that shows what the test should print if your code is correct. It
//  also has the number of points you will receive if the test is successful.
//  These tests are worth a total of 25 points.

class Driver
{
  public static void main(String[] args)
  {
    Zillion z = new Zillion(2);
    System.out.println(z);  //  00  2 points

    z.increment();
    System.out.println(z);  //  01  2 points

    z.increment();
    System.out.println(z);  //  02  2 points

    z.increment();
    z.increment();
    z.increment();
    z.increment();
    z.increment();
    z.increment();
    z.increment();
    z.increment();

    System.out.println(z);  //  10  2 points
    z.increment();
    System.out.println(z);  //  11  2 points

    z = new Zillion(4);
    System.out.println(z);  //  0000  2 points

    for (int j = 1; j <= 999; j += 1)
    {
      z.increment();
    }
    System.out.println(z);  //  0999  2 points

    z.increment();
    System.out.println(z);  //  1000  2 points

    for (int j = 1; j <= 999; j += 1)
    {
      z.increment();
    }
    System.out.println(z);  //  1999  2 points

    z.increment();
    System.out.println(z);  //  2000  2 points

    for (int j = 1; j <= 7999; j += 1)
    {
      z.increment();
    }
    System.out.println(z);  //  9999  2 points

    z.increment();
    System.out.println(z);  //  0000  2 points

    z.increment();
    System.out.println(z);  //  0001  1 point
  }
}
