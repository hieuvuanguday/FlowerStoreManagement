/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class Loader {

    private final static String SYSPATH = new File("").getAbsolutePath() + "\\src";

    /**
     * Initializes a path by appending it to the SYSPATH constant.
     *
     * @param path The relative path to initialize.
     * @return The absolute path.
     */
    private static String initPath(String path) {
        return SYSPATH + path;
    }

    /**
     * Reads data from a file and returns it as an ArrayList of strings.
     *
     * @param filePath The path of the file to be read.
     * @return An ArrayList of strings containing the data read from the file.
     */
    public static ArrayList<String> readFromFile(String filePath) {
        String path = initPath(filePath);
        File file = new File(path);
        ArrayList<String> dta = new ArrayList<>();
        try {
            try (FileInputStream fis = new FileInputStream(file); ObjectInputStream ois = new ObjectInputStream(fis)) {
                dta = (ArrayList<String>) ois.readObject();
                ois.close();
                fis.close();
            }
        } catch (IOException | ClassNotFoundException e) {
        }
        return dta;
    }

    /**
     * Writes the given ArrayList of strings to a file.
     *
     * @param filePath The path of the file to be written.
     * @param dta The ArrayList of strings to be written to the file.
     */
    public static void writeToFile(String filePath, ArrayList<String> dta) {
        String path = initPath(filePath);
        File file = new File(path);
        try {
            try (FileOutputStream fos = new FileOutputStream(file); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(dta);
                oos.close();
                fos.close();
            }
        } catch (IOException e) {
        }
    }
}
