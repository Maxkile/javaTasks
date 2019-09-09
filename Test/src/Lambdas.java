import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

@FunctionalInterface
interface Function<T>{
    T somefunct(T arg);
}

@FunctionalInterface
interface ArrayFun<M>{
    int counfun(M[] arr,M elem);
}

public class Lambdas{

    static <T> int numofEqualElems(T[] arr, T elem){
        int num = 0;
        for(T obj:arr){
            if (obj.equals(elem)) num++;
        }
        return num;
    }

    static <T> int applyArrFun(ArrayFun<T> fun,T[] arr,T val){
        return fun.counfun(arr,val);
    }


    static String reverseStr(String str){
        StringBuilder res = new StringBuilder(str);
        return res.reverse().toString();
    }
    static int compareStrings(String str1,String str2){
        return str1.length() - str2.length();
    }
    public int isEven(int num){
        return num%2;
    }

    static <T> T applyFucntion(T arg,Function<T> funct){
        return  funct.somefunct(arg);
    }

    public static void main(String[] args) {
        System.out.println(applyFucntion("w",Lambdas::reverseStr));
        String[] mas = {"qwer","ty","uiop","qwer","qwer","qwer"};
        String elem = "qwer";
        System.out.println(applyArrFun(Lambdas::<String>numofEqualElems,mas,elem));
        HashSet<String> strset = new HashSet<>();
        strset.add("qwerty");
        strset.add("q");
        strset.add("qweqwqeqwe");
        System.out.println(Collections.max(strset,Lambdas::compareStrings));
    }
}
