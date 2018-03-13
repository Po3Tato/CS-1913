class test{
  public static void main(String[] args){
    // a comment
    int var1 = 0; // inline comment
    /* starred comment */ int var2 = 0; // and inline comment
    // can we pick up var1 from the next line?
    var1++;
    // how about some more variables...
    int a = 0, b = 2, c = 3;
    int d = 1;
    var1 = a + b + c + d;
    String otherVar = "otherVar" + "otherVar";
  }
/*//*//***
// comment in a multiline comment?
***/
  public int randomNumber(){
    for(double v = 0; v < 1; v += .1){
      v += 1; v -= .5; v -= .3; v--; v++;
    }
    return 4;
  }

  public double random(){
    return .4;
  }
}
