package test;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class Config{
    protected String PROVINCE_NAME;
    public String PROVINCE_CAPITAL_NAME;
    public String PROVINCE_TAX;
    public String PROVINCE_PRODUCTION;
    public String PROVINCE_MANPOWER;
    public String PROVINCE_OWNER;
    public String[] PROVINCE_PAST_OWNERS;

}
//public class Parser {
//    private String name;
//    private String surname;
//
//    public void Parse() throws java.io.IOException,java.io.FileNotFoundException{
//        Properties props = new Properties();
//        props.load(new FileInputStream(new File("D:\\java\\javaTasks\\src\\ru\\netcrackeredu\\sivashchenko\\consolecalcDemo\\Test.properties")));
//        props.list(System.out);
//        //        String[] sname =  props.getProperty("surname").split(",");
//        //        System.out.println(sname[0] + " " + sname[1] );
//    }
//    public static void main(String[] args) throws java.io.IOException,java.io.FileNotFoundException{
//        Parser parser = new Parser();
//        parser.Parse();
//    }
//}
