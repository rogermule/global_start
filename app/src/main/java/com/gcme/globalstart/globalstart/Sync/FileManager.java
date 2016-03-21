package com.gcme.globalstart.globalstart.Sync;

import android.os.Environment;

import java.io.File;

/**
 * Created by BENGEOS on 3/18/16.
 */
public class FileManager {
    public FileManager(){
        File file = new File(Environment.getExternalStorageDirectory(),"GlobalStart");
        if(!file.isDirectory()){
            file.mkdir();
        }
        File Image_Folder = new File(file,"Images");
        if(!Image_Folder.isDirectory()){
            Image_Folder.mkdir();
        }
    }
    public boolean isExternalStorageReadable(){
        String str = Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(str) || !Environment.MEDIA_MOUNTED_READ_ONLY.equals(str)){
            return true;
        }
        return false;
    }
    public File Create_ImageFile(String ImageName){
        File file = new File(Environment.getExternalStorageDirectory(),"GlobalStart");
        if(isExternalStorageReadable()){
            File Image_Folder = new File(file,"Images");
            File ImageFile = new File(Image_Folder,ImageName);
            return ImageFile;
        }
        return null;
    }
}
