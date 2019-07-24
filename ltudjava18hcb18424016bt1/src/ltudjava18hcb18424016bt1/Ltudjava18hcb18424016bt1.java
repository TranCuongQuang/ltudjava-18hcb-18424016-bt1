/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ltudjava18hcb18424016bt1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 *
 * @author quang
 */
public class Ltudjava18hcb18424016bt1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // Tạo một đối tượng BufferedReader
        BufferedReader dataIn = new BufferedReader(new InputStreamReader(System.in));
        // Biến name

        String name = "";
        System.out.println("Please Enter Your Name:");

        // Tiến hành đọc từ bàn phím
        try {
            name = dataIn.readLine();
        } catch (IOException e) {
            System.out.println("Error!");
        }

        // hiển thị tên
        System.out.println("Hello " + name + "!");

        String csvFile = "../../input/" + name;
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        String Output = "database/" + name;
        try {
            PrintWriter pw = new PrintWriter(Output);
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                //String[] country = line.split(cvsSplitBy);
                //System.out.println("Country [code= " + country[4] + " , name=" + country[5] + "]");
                System.out.println(line);
                pw.close();
                try (FileWriter fw = new FileWriter(Output, true);
                        BufferedWriter bw = new BufferedWriter(fw);
                        PrintWriter out = new PrintWriter(bw)) {
                    out.println(line);
                } catch (IOException e) {
                }
            }
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                }
            }
        }
    }
}
