class RunnyStack<Base>{
  private int depth, runs;
  private Run top;

  private class Run{
    private int length;
    private Base value;
    private Run next;
    private Run(Base value, int length, Run next){
      this.value = value;
      this.length = length;
      this.next = next;
    }
  }

  public RunnyStack(){
    depth = runs = 0;
    // not technicallly required
    top = null;
  }

  public boolean isEmpty(){
    return top == null;
  }

  public void push(Base b){
    if(runEqual(top, b)){
      // add 1 to the length of top (for the new element)
      top.length++;
    }else{
      // make a new top
      top = new Run(b, 1, top);
      runs++;
    }

    depth++;
  }

  public void pop(){
    if(isEmpty()){
      throw new IllegalStateException("The stack is empty!");
    }

    if(top.length > 1){
      top.length--;
    }else{
      top = top.next;
      runs--;
    }

    depth--;
  }

  public Base peek(){
    if(isEmpty()){
      throw new IllegalStateException("the stack is empty!");
    }

    return top.value;
  }

  public int runs(){
    return runs;
  }
  public int depth(){
    return depth;
  }

  public boolean runEqual(Run r, Base b){
    if(r == null){
      return false;
    }
    if(r.value == null && b == null){
      return true;
    }
    return b != null && b.equals(r.value);
  }
}

/*
Tests output:
true
0
0
No pop
No peek
A
1
1
false
B
2
2
B
3
2
B
4
2
C
5
3
C
6
3
C
5
3
B
4
2
B
3
2
A
1
1
true
0
0
No peek
*/
