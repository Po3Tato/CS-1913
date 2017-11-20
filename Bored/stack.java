// how fast can I write a stack (linked) class? time start: 2:55pm, finish: 3:05pm
class Element<Base>{
  private Base value;
  private Element<Base> previous;
  public Element(Base el, Element<Base> prev){
    value = el;
    previous = prev;
  }

  public Element<Base> getLink(){
    return previous;
  }

  public Base getValue(){
    return value;
  }
}

class Stack<Base>{
  private Element<Base> top;
  public boolean isEmpty(){
    return top == null;
  }
  public void push(Base el){
    Element<Base> newEl = new Element<Base>(el, top);
    top = newEl;
  }
  public Base pop(){
    if(isEmpty()){
      throw new IllegalStateException("Stack is empty.");
    }
    Base ret = top.getValue();
    top = top.getLink();
    return ret;
  }

  public Base peek(){
    return top.getValue();
  }
}

class Test{
  public static void main(String[]args){
    Stack<String> stack = new Stack<String>();
    System.out.println(stack.isEmpty());
    stack.push("a");
    stack.push("b");
    stack.push("c");
    System.out.println(stack.isEmpty());
    System.out.println(stack.pop());
    System.out.println(stack.peek());
    System.out.println(stack.pop());
    System.out.println(stack.pop());
    System.out.println(stack.isEmpty());
  }
}
