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

      sb.append(workingTerm.coef + "x^" + workingTerm.exp);
      if(workingTerm.next != null){
        sb.append(" + ");
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
