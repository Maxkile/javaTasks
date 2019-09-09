package test;

/*Pattern "singleton"*/
public class Singleton{

    private double server;
    private static final Singleton instance = new Singleton(Math.random());

    public Singleton(double name){
        this.server = name;
    }

    public static Singleton getInstance(){
        return instance;
    }

    public String toString(){
        return String.valueOf(this.server);
    }
}