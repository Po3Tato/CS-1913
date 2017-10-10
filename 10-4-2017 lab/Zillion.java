// Alan Koval koval048@umn.edu, Lab #4
// Assignment: https://ay17.moodle.umn.edu/pluginfile.php/1406392/mod_resource/content/2/lab4.html

class Zillion{
  private int[] num;
  public Zillion(int size){
    num = new int[size];
  }

  public void increment(){
    int i = 0;
    while(i < num.length && num[i] == 9){
      num[i] = 0;
      i++;
    }

    if(i < num.length){
      num[i]++;
    }
  }

  @Override
  public String toString(){
    String result = "";
    for(int count = 0; count < num.length; count++){
      result += num[num.length - 1 - count];
    }

    return result;
  }
}

/* Output:
00
01
02
10
11
0000
0999
1000
1999
2000
9999
0000
0001
*/
