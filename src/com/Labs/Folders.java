package com.Labs;

import java.io.*;
import java.util.ArrayList;

public class Folders {

    public static ArrayList<File> FoldersInFolder = new ArrayList<>();
    public static ArrayList<File> Folders = new ArrayList<>();

    public void addFolder(File folder){
        Folders.add(folder);
    }

    public static void addFolderInFolder(File folder){
        FoldersInFolder.add(folder);
    }

    public static void moveToFolder(){
        Folders.clear();
        Folders.addAll(FoldersInFolder);
        FoldersInFolder.clear();
    }
}
