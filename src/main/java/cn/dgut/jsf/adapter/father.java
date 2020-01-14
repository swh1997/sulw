
public class father {
    public  int c=3;
    private int a=2;
    father(){
        System.out.println("father c:"+this.c);
    }
    public int getA(){
        return this.a;
    }
    public void setA(int a){
        this.a=a;
    }
    int  method(){
        return a;
    }
}
