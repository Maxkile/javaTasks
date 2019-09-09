import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regexpr {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("[,.?]");
        String eric = "eqweqwewqeqw";
        System.out.println(eric.matches("[a-zA-Z]+(?i)$"));
        String[] arr = pattern.split("Mother, you have washed the window,haven't you? Yes, i have.");
        for(String word:arr){
            System.out.println(word);
        }
        Matcher matcher = pattern.matcher("Eric Eric Eric Eric Eric");
        System.out.println(matcher.replaceAll("Whitebeard"));
        //        while(matcher.find()){
//            System.out.println(matcher.group());
//        }
    }

}
