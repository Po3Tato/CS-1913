//  FACTORIALS. Print some factorials

class Factorials
{

  //  FACTORIAL. Return the factorial of N.

  private static int factorial(int n)
  {
    if (n == 0)
    {
      return 1;
    }
    else
    {
      return n * factorial(n - 1);
    }
  }

  //  MAIN. Write the factorials of 0 through 10.

  public static void main(String [] args)
  {
    for (int k = 0; k <= 10; k += 1)
    {
      System.out.println(k + "! = " + factorial(k));
    }
  }
}
