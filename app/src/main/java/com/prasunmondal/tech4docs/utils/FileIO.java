package com.prasunmondal.tech4docs.utils;

import android.content.Context;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileIO {
    public static void deleteObjectFromFile(String fileName) {
        try {
//            File file = new File(fileName);
//            System.out.println("Delete Detaisl: ");
//            System.out.println(file.getAbsolutePath());
//            System.out.println(file.exists());
//            file.delete();
//            System.err.println("Delete Success: ");

            fileName = "/" + fileName;
            System.out.println(fileName);

            File fdelete = new File(fileName);
            if (fdelete.exists()) {
                if (fdelete.delete()) {
                    System.out.println("file Deleted :" + fileName);
                } else {
                    System.out.println("file not Deleted :" + fileName);
                }
            } else {
                System.out.println("file Deleted not exist:");
            }
        } catch (Exception e) {
            System.err.println("Delete error: ");
            e.printStackTrace();
        }
    }

    public void WriteBytesToFile(Context context, String fileName, byte[] bytes) throws IOException {
        File path = context.getFilesDir();
        File file = new File(path, fileName);
        try {
            Applog.Companion.info("bytes", bytes, new Throwable());
            FileOutputStream fos = context.openFileOutput(file.getName(), Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.write(bytes);
            os.flush();
            fos.flush();
            os.close();
            fos.close();
        } catch (Exception e) {
            Applog.Companion.error("Error while writing bytes from file", e, new Throwable());
            throw e;
        }
    }

    public byte[] ReadBytesFromFile(Context context, String fileName) throws Exception {
        File path = context.getFilesDir();
        File file = new File(path, fileName);
        try {
//            File file = new File(fileName);
            int size = (int) file.length();
            Applog.Companion.info("size", size, new Throwable());
            byte[] bytes = new byte[size];
            try {
                BufferedInputStream buf = new BufferedInputStream(new FileInputStream(file));
                buf.read(bytes, 0, bytes.length);
                buf.close();
            } catch (FileNotFoundException e) {
                Applog.Companion.error("FileNotFoundException", e, new Throwable());
                e.printStackTrace();
                throw e;
            } catch (IOException e) {
                Applog.Companion.error("IOException", e, new Throwable());
                e.printStackTrace();
                throw e;
            }
            return bytes;
        } catch (Exception e) {
            Applog.Companion.error("Error while reading bytes from file", e, new Throwable());
            Applog.Companion.error("Parameter: context", context, new Throwable());
            Applog.Companion.error("Parameter: fileName", fileName, new Throwable());
            throw e;
        }
    }

    public boolean deleteFile(Context context, String fileName) throws Exception {
        File path = context.getFilesDir();
        File file = new File(path, fileName);
        return file.delete();
    }

    public void WriteObjectToFile(Context context, String fileName, Object object) {
        try {
            FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(object);
            os.close();
            fos.close();
        } catch (Exception e) {
            System.out.println("Error while writing object to file");
            System.out.println(e);
        }
    }

    public Object ReadObjectFromFile(Context context, String fileName) throws IOException, ClassNotFoundException {
        try {
            FileInputStream fis = context.openFileInput(fileName);
            ObjectInputStream is = new ObjectInputStream(fis);
            Object object = is.readObject();
            is.close();
            fis.close();
            return object;
        } catch (Exception e) {
            Applog.Companion.error("Error while reading object from file", new Throwable());
            throw e;
        }
    }
}
