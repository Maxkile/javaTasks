package ru.skillbench.tasks.javaapi.collections;

import java.util.*;

public class StringFilterImpl implements StringFilter {

    private HashSet<String> storage;

    private interface Filter{
        public boolean isSuitable(String str,String anotherstr);
    }

    /**
     *
     * @param filter - interface, capable for getting suitable string from the set
     * @param pattern
     * @return Iterator for suitable Strings from the set
     */
    private Iterator<String> getIterator(Filter filter,String pattern){
        HashSet<String> result = new HashSet<String>();
        for(String elem:storage){
            if (filter.isSuitable(elem,pattern)) result.add(elem);
        }
        return result.iterator();
    }

    public StringFilterImpl() {
        this.storage = new HashSet<String>();
    }

    @Override
    public void add(String s) {
        if (s != null) storage.add(s.toLowerCase());
        else storage.add(s);
    }

    @Override
    public boolean remove(String s) {
        if (s == null){
            if (storage.contains(s)){
                storage.remove(s);
                return true;
            } else return false;
        } else if (!storage.contains(s.toLowerCase()))return false;
                else{
                    storage.remove(s.toLowerCase());
                    return true;
                }
    }

    @Override
    public void removeAll() {
        storage.clear();
    }

    @Override
    public Collection<String> getCollection() {
        return storage;
    }

    @Override
    public Iterator<String> getStringsContaining(String chars) {

        //Anonymous class which implements interface "filter"
        Filter filter = new Filter() {
            @Override
            public boolean isSuitable(String str, String substr) {
                if (str == null) return false;
                else return str.contains(substr.toLowerCase());
            }
        };

        if (chars == null || chars.equals("")) return storage.iterator();
        else return getIterator(filter,chars);
}

    @Override
    public Iterator<String> getStringsStartingWith(String begin) {

        Filter filter = new Filter() {
            @Override
            public boolean isSuitable(String str, String begin) {
                if (str == null) return false;
                else return str.startsWith(begin.toLowerCase());
            }
        };

        if (begin == null || begin.equals("")) return storage.iterator();
        else return getIterator(filter,begin);
    }

    @Override
    public Iterator<String> getStringsByNumberFormat(String format) {

        Filter filter = new Filter() {
            //"format" is something like "####-###", where '#' is any possible digit,'-' - any sign
            @Override
            public boolean isSuitable(String str,String format) {
                if (str == null) return false;
                if (str.length() !=  format.length()) return false;
                for(int i = 0;i<str.length();++i){
                    if((format.charAt(i) != '#') || (!Character.isDigit(str.charAt(i))))
                        if (Character.toLowerCase(format.charAt(i)) != str.charAt(i)) return false;
                }
                return true;
            }
        };

        if (format == null || format.equals("")) return storage.iterator();
        else return getIterator(filter,format);

    }


    @Override
    public Iterator<String> getStringsByPattern(String pattern) {

        Filter filter = new Filter() {

            private LinkedHashSet<String> patternStrings;

            private LinkedHashSet<String> getStringsfromPattern(String pattern){
                LinkedHashSet<String> patternStrings = new LinkedHashSet<String>();
                StringBuilder temp = new StringBuilder();
                for(int i = 0;i<pattern.length();++i){

                    if (pattern.charAt(i) == '*'){
                        patternStrings.add(temp.toString());
                        temp.setLength(0);
                    }
                    else{
                        temp.append(Character.toLowerCase(pattern.charAt(i)));
                        if (i == pattern.length() - 1) patternStrings.add(temp.toString());//if reached the end
                    }
                }
                patternStrings.remove("");
                return patternStrings;
            }

            public boolean isSuitable(String str, String pattern){
                if (str == null) return false;
                if (patternStrings == null) patternStrings = getStringsfromPattern(pattern);
                Iterator<String> it = patternStrings.iterator();
                if (!patternStrings.isEmpty()){
                    String cur =  it.next();
                    if (!(str.contains(cur))) return false;
                    for( ;it.hasNext(); ){
                        String next = it.next();
                        if (!(str.contains(next)) || (str.lastIndexOf(next) < str.indexOf(cur))) return false;
                        else cur = next;
                    }
                    return !((!((pattern.startsWith("*")) || (str.startsWith(patternStrings.iterator().next())))) || (!((pattern.endsWith("*")) || (str.endsWith(cur)))));
                } else return pattern.contains("*");//was '*' or was nothing

            }
        };

        if (pattern == null || pattern.equals("")) return storage.iterator();
        else return getIterator(filter,pattern);

    }
}