class Poly{
  private class Term{
    private int coef, exp;
    private Term next;
    private Term(int coef, int exp, Term next){
      this.coef = coef;
      this.exp = exp;
      this.next = next;
    }
  }

  private Term head;

  public Poly(){
    head = new Term(-1, -1, null);
  }

  private Poly copy(boolean negate){
    Poly newPoly = new Poly();
    Term currentTerm = head;
    while(currentTerm.next != null){
      Term t = currentTerm.next;
      newPoly.term(negate ? -t.coef : t.coef, t.exp);
      currentTerm = t;
    }

    return newPoly;
  }

  private Poly copy(){
    return copy(false);
  }
  public Poly minus(){
    return copy(true);
  }

  public Poly term(int coef, int exp){
    // check if exp is in the polynomial
      Term t = head;
      while(t.next != null){
        if(t.next.exp == exp){
          throw new IllegalArgumentException("exp must not be present in the polynomial.");
        }
        t = t.next;
      }

      return add(coef, exp);
  }

  public Poly add(int coef, int exp){
    // polynomials have only non-negative exponents
    if(exp < 0){
      throw new IllegalArgumentException("Exponent must be a non-negative integer.");
    }
    if(coef == 0){
      return this;
    }

    Term currentNode = head;
    while(currentNode.next != null){
      Term workingNode = currentNode.next;
      // if the exponent matches, add the coefficient to the existing one, and delete the node if necessary
      if(workingNode.exp == exp){
        workingNode.coef += coef;
        if(workingNode.coef == 0){
          deleteTerm(currentNode, workingNode);
        }
        return this;
      }

      // if the exponenet is greater than needed, add a new term
      if(workingNode.exp < exp){
        insertAfter(currentNode, coef, exp);
        return this;
      }

      currentNode = workingNode;
    }
    // the only possibility here is that the exponenet is less than all the ones in this list, thus we should add it
    insertAfter(currentNode, coef, exp);
    return this;
  }

  public Poly plus(Poly other){
    Poly added = other.copy();
    Term n = head;
    while(n.next != null){
      added.add(n.next.coef, n.next.exp);
      n = n.next;
    }

    return added;
  }

  public String toString(){
    StringBuilder sb = new StringBuilder();
    Term currentTerm = head;
    while(currentTerm.next != null){
      Term workingTerm = currentTerm.next;

      sb.append((currentTerm == head ? workingTerm.coef : Math.abs(workingTerm.coef)) + "x^" + workingTerm.exp);
      if(workingTerm.next != null){
        if(workingTerm.next.coef < 0){
          sb.append(" - ");
        }else{
          sb.append(" + ");
        }
      }

      currentTerm = workingTerm;
    }

    return sb.toString().length() > 0 ? sb.toString() : "0";
  }

  private void insertAfter(Term term1, int coef, int exp){
    term1.next = new Term(coef, exp, term1.next);
  }
  private void deleteTerm(Term prevTerm, Term term){
    prevTerm.next = term.next;
  }

}

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
    System.out.println("remove all terms");
    System.out.println(p.add(1,2).add(-1,1).add(-1,0));
    System.out.println("Add back a term");
    System.out.println(p.add(1200,4382));

    Poly a = new Poly();
    Poly b = new Poly();
    a.add(1,0).add(1,1).add(2,3);
    b.add(-1,0).add(2,1).add(3,2).add(1,3).add(2,4);
    System.out.println("\n2x^4 + x^3 + 3x^2 + 2x - 1, plus 2x^3 + x + 1");
    System.out.println(a.plus(b));

    System.out.println("2x^4 + x^3 + 3x^2 + 2x - 1, minus 2x^3 + x + 1");
    System.out.println(b.plus(a.minus()));

    System.out.println("x^4 - 3x^4");
    System.out.println(new Poly().term(1,4).plus(new Poly().term(-3,4)));

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

    System.out.println("Checking term error");
    try{
        Poly p4 = new Poly().term(1,3).term(2,3);
    }catch(IllegalArgumentException e){
      System.out.println("duplicate term error caught");
    }

  }
}
/* output:
empty polynomial
0
negative exponent caught
Geometric partial  series:
1x^3 + 1x^2 + 1x^1 + 1x^0
Add -x^3
1x^2 + 1x^1 + 1x^0
Add 0x^0, 0x^2, 0x^-1
1x^2 + 1x^1 + 1x^0
negative exponent caught
Add -2x^2
-1x^2 + 1x^1 + 1x^0
remove all terms
0
Add back a term
1200x^4382

2x^4 + x^3 + 3x^2 + 2x - 1, plus 2x^3 + x + 1
2x^4 + 3x^3 + 3x^2 + 3x^1
2x^4 + x^3 + 3x^2 + 2x - 1, minus 2x^3 + x + 1
2x^4 - 1x^3 + 3x^2 + 1x^1 - 2x^0
x^4 - 3x^4
-2x^4


0
1x^3 + 1x^2 + 1x^1
3x^2 + 2x^1
-3x^2 - 2x^1
1x^3 + 4x^2 + 3x^1
1x^3 - 2x^2 - 1x^1
Checking term error
duplicate term error caught
*/
