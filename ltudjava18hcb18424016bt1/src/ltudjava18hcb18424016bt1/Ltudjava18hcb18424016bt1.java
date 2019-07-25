package ltudjava18hcb18424016bt1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ltudjava18hcb18424016bt1 {

    public static void WriteData(String Path, String Data) {
        try (FileWriter fw = new FileWriter(Path, true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw)) {
            out.println(Data);
        } catch (IOException e) {
        }
    }

    public static int ChooseFunction() throws IOException {
        BufferedReader dataIn = new BufferedReader(new InputStreamReader(System.in));
        int function = 0;
        System.out.println("Chọn chức năng: ");
        System.out.println("1: Import danh sách sinh viên 1 lớp.");
        System.out.println("2: Thêm thủ công sinh viên.");
        System.out.println("3: Thêm, xóa thủ công sinh viên khỏi tkb lớp học.");

        // Tiến hành đọc từ bàn phím
        String strF = dataIn.readLine();
//        Pattern pattern = Pattern.compile("\\d*");
//        Matcher matcher = pattern.matcher(strF);
//        if (matcher.matches()) {
        function = Integer.parseInt(strF);
        System.out.println("Bạn đã chọn chức năng: " + function);
//            return function;
//        } else {
//            System.out.println("Bạn vừa nhập vào không phải số, vui lòng nhập lại !");
//            ChooseFunction();
//        }
        return function;

    }

    public static int Countiue() throws IOException {
        BufferedReader dataIn = new BufferedReader(new InputStreamReader(System.in));
        int continueF = 0;
        System.out.print("Thực hiện tiếp chương trình (1: Có, 0: Không): ");
        String strC = dataIn.readLine();
//        Pattern pattern = Pattern.compile("\\d*");
//        Matcher matcher = pattern.matcher(strC);
//        if (matcher.matches()) {
        continueF = Integer.parseInt(strC);
//        } else {
//            Countiue();
//        }
        return continueF;
    }

    public static void DQ() throws IOException {
        BufferedReader dataIn = new BufferedReader(new InputStreamReader(System.in));
        int function = ChooseFunction();
        if (function == 1) {
            String input = "";
            String output = "";
            System.out.print("Nhập tên file inport: ");
            input = dataIn.readLine();
            System.out.print("Nhập tên file lưu: ");
            output = dataIn.readLine();

            // Đọc và ghi file
            BufferedReader br = null;
            String line = "";
            String Path = "database/Class_" + output + ".txt";
            try {
                PrintWriter pw = new PrintWriter(Path);
                br = new BufferedReader(new FileReader(input));
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                    pw.close();
                    WriteData(Path, line);
                }
            } catch (FileNotFoundException e) {
            } catch (IOException e) {
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                    }
                } else {
                    System.out.println("Lớp học không tồn tại.");
                }
            }
        } else if (function == 2) {
            String ClassName = "";
            String StudentID = "";
            String Name = "";
            String Gender = "";
            String CardNumber = "";

            System.out.print("Nhập tên lớp: ");
            ClassName = dataIn.readLine();
            String Path = "database/Class_" + ClassName + ".txt";
            System.out.print("Nhập MSSV: ");
            StudentID = dataIn.readLine();
            System.out.print("Nhập tên SV: ");
            Name = dataIn.readLine();
            System.out.print("Nhập giới tính: ");
            Gender = dataIn.readLine();
            System.out.print("Nhập CMND: ");
            CardNumber = dataIn.readLine();

            BufferedReader br = null;
            String line = "";
            try {
                //PrintWriter pw = new PrintWriter(Path);
                br = new BufferedReader(new FileReader(Path));
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                    //pw.close();
                    //WriteData(Path, line);
                }
            } catch (FileNotFoundException e) {
            } catch (IOException e) {
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                    }
                } else {
                    System.out.println("Lớp học không tồn tại.");
                }
            }
        } else {
            System.out.print("Chức năng không tồn tại. Vui lòng kiểm tra lại.");
        }

        int continueF = Countiue();
        if (continueF == 1) {
            DQ();
        }
    }

    public static void main(String[] args) throws IOException {
        DQ();
    }
}
