class PolyMath{
  public static void main(String[] args){
    Poly p = new Poly();
    System.out.println("empty polynomial");
    System.out.println(p);
    p.add(0,0);
    try{
      p.add(0,-1);
    }catch(IllegalArgumentException e){
      System.out.println("negative exponent caught");
    }

    p.add(1,0).add(1,1).add(1,2).add(1,3);
    System.out.println("Geometric partial  series:");
    System.out.println(p);
    System.out.println("Add -x^3");
    System.out.println(p.add(-1,3));
    System.out.println("Add 0x^0, 0x^2, 0x^-1");
    System.out.println(p.add(0,0).add(0,2));
    try{
      p.add(0,-1);
    }catch(IllegalArgumentException e){
      System.out.println("negative exponent caught");
    }
    System.out.println("Add -2x^2");
    System.out.println(p.add(-2,2));
    System.out.println("remove all adds");
    System.out.println(p.add(1,2).add(-1,1).add(-1,0));
    System.out.println("Add back a add");
    System.out.println(p.add(1200,4382));

    Poly a = new Poly();
    Poly b = new Poly();
    a.add(1,0).add(1,1).add(2,3);
    b.add(-1,0).add(2,1).add(3,2).add(1,3).add(2,4);
    System.out.println("\n2x^4 + x^3 + 3x^2 + 2x - 1, plus 2x^3 + x + 1");
    System.out.println(a.plus(b.minus()));

    System.out.println("2x^4 + x^3 + 3x^2 + 2x - 1, minus 2x^3 + x + 1");
    System.out.println(b.plus(a.minus()));

    System.out.println("\n");
    Poly p0 = new Poly();
    Poly p1 = new Poly().term(1, 3).term(1, 1).term(1, 2);
    Poly p2 = new Poly().term(2, 1).term(3, 2);
    Poly p3 = p2.minus();

    System.out.println(p0);           //  0
    System.out.println(p1);           //  1x3 + 1x2 + 1x1
    System.out.println(p2);           //  3x2 + 2x1
    System.out.println(p3);           //  −3x2 − 2x1

    System.out.println(p1.plus(p2));  //  1x3 + 4x2 + 3x1
    System.out.println(p1.plus(p3));  //  1x3 − 2x2 − 1x1
  }
}
