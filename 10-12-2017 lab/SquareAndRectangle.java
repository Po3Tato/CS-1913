class Rectangle extends Polygon{
  public Rectangle(int width, int height){
    super(4, width, height, width, height);
  }

  public int area(){
    return side(0) * side(1);
  }
}

class Square extends Rectangle{
  public Square(int width){
    super(width, width);
  }
}
