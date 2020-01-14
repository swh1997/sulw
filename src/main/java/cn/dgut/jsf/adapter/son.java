
public class son extends father{
    public static void main(String[] args){
        father son=new son();
        System.out.println("son c:"+son.c+",a:"+son.method());
        son son1=new son();
        System.out.println("c:"+son1.c+",a:"+son1.method());
    }
      public int a=4;
    private int c=0;
      public String b="1";
      son(){
          System.out.println(super.method());
      }
      int method(){
          return a;
      }

}
