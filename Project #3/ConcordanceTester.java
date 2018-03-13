class ConcordanceTester{
  public static void main(String[] args){
    System.out.println("--------- Given Test File (testFile1.java) -----------");
    System.out.print(ConcordanceTable.fromFile("testFile1.java"));
    System.out.println("--------- Custom Test File (testFile2.java) ----------");
    System.out.print(ConcordanceTable.fromFile("testFile2.java"));
  }
}
