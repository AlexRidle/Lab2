package com.Labs;

import java.io.*;
import java.util.Scanner;

public class Main {

    private static int counter = 1;

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        new File("LABDIR").mkdir();

        System.out.print("Enter source directory: ");
        String line = input.nextLine();
        String sourceFolder = line;

        String targetFolder = "LABDIR";

        File sFile = new File(sourceFolder);
        copyFilesAndCheckFolders(sFile);
        Folders.moveToFolder();

        while(!(Folders.Folders.isEmpty())){
            for(int i =0; i<Folders.Folders.size(); i++){
                copyFilesAndCheckFolders(Folders.Folders.get(i));
            }
            Folders.moveToFolder();
        }

    }

    private static void copyFileUsingStream(File source, File dest)  {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        }catch(Exception ex) {
            System.out.println("Unable to copy file:"+ex.getMessage());
        }
        finally {
            try {
                is.close();
                os.close();
            }catch(Exception ex) {}
        }
    }

    private static void copyFilesAndCheckFolders(File sFile){

        File[] sourceFiles = sFile.listFiles(new FilenameFilter() {

            @Override
            public boolean accept(File dir, String name) {
                if(name.endsWith(".txt")) { return true; } else { return false; }
            }

        });

        for(File fSource:sourceFiles) {

            String name = "var3file"+counter+".txt";
            File fTarget = new File(new File("LABDIR"), name);
            counter++;

            copyFileUsingStream(fSource,fTarget);

            try{
                BufferedWriter writer = new BufferedWriter(new FileWriter(fTarget, true));
                writer.write("\n</html></body>");
                writer.flush();
                writer.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        File[] sourceAllFiles = sFile.listFiles();

        for(File folder:sourceAllFiles){
            if(folder.isDirectory()){
                Folders.FoldersInFolder.add(folder);
            }
        }
    }
}

//в начало каждого файла вставить <html><body>, в конец </html></body>
//обработать в соответствии с вариантом
//1) удалить все пробелы
//2) все слова c "d" сделать подчеркнутыми
//3) все слова с "a" сделать жирным
//4) все слова с "e" удалить
//5) если слово с прописной сделать его подчеркнутым