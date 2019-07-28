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

    public static void ImportFile(BufferedReader dataIn, String refix) throws IOException {
        String input = "";
        String output = "";
        System.out.print("Nhập tên file inport: ");
        input = dataIn.readLine();
        System.out.print("Nhập tên file lưu: ");
        output = dataIn.readLine();

        // Đọc và ghi file
        BufferedReader br = null;
        BufferedReader wr = null;
        String line = "";
        String Path = "database/" + refix + output + ".txt";
        try {
            br = new BufferedReader(new FileReader(input));
            wr = new BufferedReader(new FileReader(Path));
            String linewr = "";
            while ((line = br.readLine()) != null) {
                String[] itemImport = line.split(",");
                int Check = 0;
                wr = new BufferedReader(new FileReader(Path));
                while ((linewr = wr.readLine()) != null) {
                    String[] item = linewr.split(",");
                    // Kiểm tra trùng dữ liệu
                    if (itemImport[0].equals(item[0])) {
                        Check = 1;
                        break;
                    }
                }

                if (Check == 0) {
                    WriteData(Path, line);
                }
            }
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } finally {
            if (wr != null) {
                try {
                    wr.close();
                } catch (IOException e) {
                }
            } else {
                if (br != null) {
                    while ((line = br.readLine()) != null) {
                        //System.out.println(line);
                        WriteData(Path, line);
                    }
                }
            }
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                }
            } else {
                System.out.println("File không tồn tại.");
            }
        }

        if (refix == "TKB_") {
            BufferedReader readClass = null;
            BufferedReader readTKB = null;
            String PathClass = "database/Class_" + output + ".txt";
            String PathTKB = "database/TKB_" + output + ".txt";
            try {
                readTKB = new BufferedReader(new FileReader(PathTKB));
                String lineTKB = readTKB.readLine();
                String PathWrite = "";
                while ((lineTKB = readTKB.readLine()) != null) {
                    //System.out.println(lineTKB);
                    String[] recordTKB = lineTKB.split(",");
                    PathWrite = "database/Class_" + output + "_" + recordTKB[0] + ".txt";
                    readClass = new BufferedReader(new FileReader(PathClass));
                    String lineClass = "";
                    // Xóa trống file
                    PrintWriter pw = new PrintWriter(PathWrite);
                    pw.close();
                    while ((lineClass = readClass.readLine()) != null) {
                        //System.out.println(lineClass);
                        WriteData(PathWrite, lineClass);
                    }
                }
            } catch (FileNotFoundException e) {
            } catch (IOException e) {
            } finally {
                if (readClass != null && readTKB != null) {
                    try {
                        readClass.close();
                        readTKB.close();
                    } catch (IOException e) {
                    }
                } else {
                    System.out.println("File không tồn tại.");
                }
            }
        }
    }

    public static void WriteByKeyBoard(String file, String Title, String Data, String Key) {
        BufferedReader wr = null;
        String Path = "database/" + file + ".txt";

        try {
            wr = new BufferedReader(new FileReader(Path));
            String line = "";
            int Check = 0;
            if ((line = wr.readLine()) != null) {
                while ((line = wr.readLine()) != null) {
                    // System.out.println(line);
                    String[] item = line.split(",");
                    // Kiểm tra trùng dữ liệu
                    if (item[0].equals(Key)) {
                        Check = 1;
                        break;
                    }
                }

                if (Check == 0) {
                    WriteData(Path, Data);
                }
            } else {
                if (!Title.equals("")) {
                    WriteData(Path, Title);
                    WriteData(Path, Data);
                } else {
                    System.out.println("File không tồn tại.");
                }
            }

        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } finally {
            if (wr != null) {
                try {
                    wr.close();
                } catch (IOException e) {
                }
            } else {
                if (!Title.equals("")) {
                    WriteData(Path, Title);
                    WriteData(Path, Data);
                } else {
                    System.out.println("File không tồn tại.");
                }
            }
        }
    }

    public static int ChooseFunction() throws IOException {
        BufferedReader dataIn = new BufferedReader(new InputStreamReader(System.in));
        int function = 0;
        System.out.println("Chọn chức năng: ");
        System.out.println("1: Import danh sách sinh viên 1 lớp.");
        System.out.println("2: Thêm thủ công sinh viên.");
        System.out.println("3: Import thời khóa biểu 1 lớp.");
        System.out.println("4: Thêm, xóa sinh viên khỏi tkb lớp học.");

        // Tiến hành đọc từ bàn phím
        String strF = dataIn.readLine();
        Pattern pattern = Pattern.compile("\\d*");
        Matcher matcher = pattern.matcher(strF);
        if (matcher.matches()) {
            function = Integer.parseInt(strF);
            System.out.println("Bạn đã chọn chức năng: " + function);
        } else {
            System.out.println("Bạn vừa nhập vào không phải số!");
        }
        return function;

    }

    public static int Countiue() throws IOException {
        BufferedReader dataIn = new BufferedReader(new InputStreamReader(System.in));
        int continueF = 0;
        System.out.print("Thực hiện tiếp chương trình (1: Có, 0: Không): ");
        String strC = dataIn.readLine();
        Pattern pattern = Pattern.compile("\\d*");
        Matcher matcher = pattern.matcher(strC);
        if (matcher.matches()) {
            continueF = Integer.parseInt(strC);
        } else {
            System.out.println("Bạn vừa nhập vào không phải số, vui lòng nhập lại !");
            continueF = 1;
            Countiue();

        }
        return continueF;
    }

    public static void DQ() throws IOException {
        BufferedReader dataIn = new BufferedReader(new InputStreamReader(System.in));
        int function = ChooseFunction();
        if (function == 1) {
            ImportFile(dataIn, "Class_");
        } else if (function == 2) {
            String ClassName = "";
            String StudentID = "";
            String Name = "";
            String Gender = "";
            String CardNumber = "";

            System.out.print("Nhập tên lớp: ");
            ClassName = dataIn.readLine();
            System.out.print("Nhập MSSV: ");
            StudentID = dataIn.readLine();
            System.out.print("Nhập tên SV: ");
            Name = dataIn.readLine();
            System.out.print("Nhập giới tính: ");
            Gender = dataIn.readLine();
            System.out.print("Nhập CMND: ");
            CardNumber = dataIn.readLine();

            String Data = StudentID + "," + Name + "," + Gender + "," + CardNumber;
            String Tile = "MSSV,Họ tên,Giới tính,CMND";
            String file = "Class_" + ClassName;

            WriteByKeyBoard(file, Tile, Data, StudentID);
        } else if (function == 3) {
            ImportFile(dataIn, "TKB_");
        } else if (function == 4) {
            String ClassName = "";
            String Subject = "";
            String StudentID = "";
            String Name = "";
            String Gender = "";
            String CardNumber = "";
            int Choose = 2;
            System.out.print("Chọn chức năng (1: Thêm, 0: Xóa): ");

            // Tiến hành đọc từ bàn phím
            String strF = dataIn.readLine();
            Pattern pattern = Pattern.compile("\\d*");
            Matcher matcher = pattern.matcher(strF);
            if (matcher.matches()) {
                Choose = Integer.parseInt(strF);
                if (Choose == 1) {
                    System.out.print("Nhập tên lớp: ");
                    ClassName = dataIn.readLine();
                    System.out.print("Nhập môn học: ");
                    Subject = dataIn.readLine();
                    System.out.print("Nhập MSSV: ");
                    StudentID = dataIn.readLine();
                    System.out.print("Nhập tên SV: ");
                    Name = dataIn.readLine();
                    System.out.print("Nhập giới tính: ");
                    Gender = dataIn.readLine();
                    System.out.print("Nhập CMND: ");
                    CardNumber = dataIn.readLine();

                    String Data = StudentID + "," + Name + "," + Gender + "," + CardNumber;
                    String Tile = "";
                    String file = "Class_" + ClassName + "_" + Subject;

                    WriteByKeyBoard(file, Tile, Data, StudentID);
                } else if (Choose == 0) {

                } else {
                    System.out.println("Bạn không chọn đúng chức năng!");
                }
            } else {
                System.out.println("Bạn vừa nhập vào không phải số!");
            }
        } else {
            System.out.println("Chức năng không tồn tại. Vui lòng kiểm tra lại.");
        }

        int continueF = 0;
        System.out.print("Thực hiện tiếp chương trình (1: Có, 0: Không): ");
        String strC = dataIn.readLine();
        Pattern pattern = Pattern.compile("\\d*");
        Matcher matcher = pattern.matcher(strC);
        if (matcher.matches()) {
            continueF = Integer.parseInt(strC);
            if (continueF == 1) {
                DQ();
            }
        } else {
            System.out.println("Bạn vừa nhập vào không phải số, vui lòng nhập lại !");
        }
    }

    public static void main(String[] args) throws IOException {
        DQ();
    }
}
