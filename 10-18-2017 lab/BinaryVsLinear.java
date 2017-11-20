class BinaryVsLinear
{
  private static int linearSearch(int key, int[] array){
    int count = 0;
    for(int i = 0; i < array.length; i++){
      count++;
      if(array[i] == key){
        return count;
      }
    }
    return count;
  }

  private static int binarySearch(int key, int[] array){
    int count = 0, low = 0;
    int high = array.length - 1;

    while(low <= high){
      int mid = (high + low) / 2;

      count++;
      if(array[mid] == key){
        return count;
      }
      count++;
      if(key < array[mid]){
        high = mid-1;
      }else{
        low = mid+1;
      }
    }
    return count;
  }

  // For testing purposes (to see if I got my binarySearch algorithm correct, since I wrote it myself)
  private static int testBinarySearch(int key, int[] array){
    int count = 0, low = 0;
    int high = array.length - 1;

    while(low <= high){
      int mid = (high + low) / 2;

      count++;
      if(array[mid] == key){
        return mid;
      }
      count++;
      if(key < array[mid]){
        high = mid-1;
      }else{
        low = mid+1;
      }
    }
    return -1;
  }

  public static void main(String[] args){
    // test my binarySearch algorithm
    int[] testArr = new int[]{1,2,3,4,5,6,7,8,9,10};
    //System.out.println("Personal tests for binarySearch--------------------");
    for(int i = 1; i <= 11; i++){
      //System.out.println(testBinarySearch(i, testArr));
    }

    //System.out.println("Table output for graph (average operations)--------");

    for(int length = 1; length <= 30; length += 1){
      int[] array = new int[length];
      for(int index = 0; index < length; index += 1){
        array[index] = index;
      }

      double linearTotal = 0.0;
      double binaryTotal = 0.0;
      for(int element = 0; element < length; element += 1){
        linearTotal += linearSearch(element, array);
        binaryTotal += binarySearch(element, array);
      }

      double linearAverage = linearTotal / length;
      double binaryAverage = binaryTotal / length;
      System.out.println(length + " " + linearAverage + " " + binaryAverage);
    }
  }
}

// program output:
/*
1 1.0 1.0
2 1.5 2.0
3 2.0 2.3333333333333335
4 2.5 3.0
5 3.0 3.4
6 3.5 3.6666666666666665
7 4.0 3.857142857142857
8 4.5 4.25
9 5.0 4.555555555555555
10 5.5 4.8
11 6.0 5.0
12 6.5 5.166666666666667
13 7.0 5.3076923076923075
14 7.5 5.428571428571429
15 8.0 5.533333333333333
16 8.5 5.75
17 9.0 5.9411764705882355
18 9.5 6.111111111111111
19 10.0 6.2631578947368425
20 10.5 6.4
21 11.0 6.523809523809524
22 11.5 6.636363636363637
23 12.0 6.739130434782608
24 12.5 6.833333333333333
25 13.0 6.92
26 13.5 7.0
27 14.0 7.074074074074074
28 14.5 7.142857142857143
29 15.0 7.206896551724138
30 15.5 7.266666666666667
*/
