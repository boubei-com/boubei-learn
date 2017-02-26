package com.boubei.learn.jk;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.regex.Pattern;

public class DirList {
    public static void main(String[] args) {
        
        File path2 = new File("./jinpj"); //"."表示当前根目录
        path2.mkdir(); //创建目录
        try {
            path2.createNewFile();//创建文件
        } catch (IOException e) {
            e.printStackTrace();
        } 

        File pathq = new File("./webapp/pms/model/portal/portal1"); //"."表示当前根目录
        if(!pathq.exists()){
            pathq.mkdir(); 
        }
        
        File path = new File("./src"); // "."表示当前根目录
        String[] list;
        if (args.length == 0)
            list = path.list();
        else
            list = path.list(new DirFilter(args[0]));
        // Arrays.sort(list, new AlphabeticComparator());
        for (int i = 0; i < list.length; i++)
            System.out.println(list[i]);
    }
}

class DirFilter implements FilenameFilter {
    private Pattern pattern;

    public DirFilter(String regex) {
        pattern = Pattern.compile(regex);
    }

    public boolean accept(File dir, String name) {
        // Strip path information, search for regex:
        return pattern.matcher(new File(name).getName()).matches();
    }
}
