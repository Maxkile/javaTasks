package ru.skillbench.tasks.javaapi.io;

import javax.sound.sampled.AudioFormat;
import java.beans.Encoder;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class WordFinderImpl implements WordFinder{

    private String text;
    private Stream<String> filtered_words;

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText(String text) {
        if (text == null) throw new IllegalArgumentException();
        this.text = text;
    }


    private String writeToString(InputStream inp) throws IOException {
        StringBuilder str_out = new StringBuilder();
        int c;
        while((c = inp.read()) != -1){
            str_out.append((char)c);
        }
        return str_out.toString();
    }

    @Override
    public void setInputStream(InputStream is) throws IOException {
        if (is == null) throw new IllegalArgumentException();
        BufferedInputStream buf_raw = new BufferedInputStream(is);
        text = writeToString(buf_raw);
    }


    @Override
    public void setFilePath(String filePath) throws IOException {
        if (filePath == null) throw new IllegalArgumentException();
        File file = new File(filePath);
        BufferedInputStream buf_raw = new BufferedInputStream(new FileInputStream(file));
        text = writeToString(buf_raw);
    }

    @Override
    public void setResource(String resourceName) throws IOException {
        if (resourceName == null) throw new IllegalArgumentException();
        InputStream resource_inp = this.getClass().getResourceAsStream(resourceName);
        BufferedInputStream buf_raw = new BufferedInputStream(resource_inp);
        text = writeToString(buf_raw);
    }

    @Override
    public Stream<String> findWordsStartWith(String begin) {
        if (text == null) throw new IllegalStateException();
        else{
            ArrayList<String> res = new ArrayList<String>();
            String[] res_split = text.split("[\\s\\r]+");
            ArrayList<String> words = new ArrayList<String>(Arrays.asList(res_split));
            //all words in out, now we can easily delete strings not starting with "begin"
            if ((begin != null) && !(begin.equals(""))){
                for(String word:words){
                    if ((word.startsWith(begin)) || (word.startsWith(begin.toLowerCase()))) res.add(word);
                }
                words.clear();
                for(String word:res){
                    words.add(word.toLowerCase());
                }
                filtered_words = words.stream();
                return words.stream();
            }
            else{
                for(String word:words){
                    res.add(word.toLowerCase());
                }
                filtered_words = res.stream();
                return res.stream();
            }
        }
    }


    @Override
    public void writeWords(OutputStream os) throws IOException {
        if (filtered_words == null) throw new IllegalStateException();
//      BufferedWriter bf_out = new BufferedWriter(new OutputStreamWriter(os,StandardCharsets.UTF_8));
        Stream<String> out = filtered_words.sorted();
        LinkedList<String> temp = new LinkedList<String>();
        out.forEach(temp::add);
        for(String elem:temp){
           writeWord(elem,os);
        }
    }

    private void writeWord(String word,OutputStream os) throws IOException {
        for(int i = 0; i <word.length();++i){
            os.write(word.charAt(i));
        }
        os.write('\u0020');
    }
}
