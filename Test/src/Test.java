import javax.management.Query;
import java.io.*;
import java.sql.Array;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.lang.Math.sin;


@FunctionalInterface
interface Checker<T>{
    public boolean check(T arg);
}

class Test{

    public static void evaluate(List<Integer> list, Checker<Integer> checker) {
        for(Integer n: list)  {
            if(checker.check(n)) {
                System.out.print(n + " ");
            }
        }
        System.out.println();
    }

    public static boolean allZeroes(List<?> list){
        return list.stream().map((x) -> ((Object) x).equals(0)).reduce((x,y)-> x && y).get();
    }

    public static void main(String[] args) throws IOException {
        List<Integer> list = Arrays.asList(0, 0, 0, 0, 0, 0, 0);
        System.out.println(allZeroes(list));

    }
}
