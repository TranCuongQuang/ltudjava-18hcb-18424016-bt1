package ltudjava18hcb18424016bt1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
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
        File directory = new File("database/");
        if (!directory.exists()) {
            directory.mkdir();
        }
        try (FileWriter fw = new FileWriter(Path, true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw)) {
            out.println(Data);
        } catch (IOException e) {
        }
    }

    public static void ReadData(String file, String refix) {
        BufferedReader br = null;
        String line = "";
        String Path = "database/" + file + ".txt";
        try {
            br = new BufferedReader(new FileReader(Path));
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] item = line.split(",");
                if (refix.equals("Class")) {
                    System.out.println("Thông tin sinh viên: [MSSV: " + item[0] + " , Họ tên: " + item[1] + " , Giới tính: " + item[2] + " , CMND: " + item[3] + "]");
                } else if (refix.equals("TKB")) {
                    System.out.println("Thông tin thời khóa biểu: [Mã môn học: " + item[0] + " , Tên môn học: " + item[1] + " , Phòng: " + item[2] + "]");
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
            } else {
                System.out.println("File không tồn tại.");
            }
        }
    }

    public static void ImportFile(BufferedReader dataIn, String refix) throws IOException {
        String input = "";
        String output = "";
        String Subject = "";
        System.out.print("Nhập tên file inport: ");
        input = dataIn.readLine();
        System.out.print("Nhập tên file lưu: ");
        output = dataIn.readLine();

        if (refix.equals("Score_")) {
            System.out.print("Nhập môn học: ");
            Subject = dataIn.readLine();
        }
        // Đọc và ghi file
        BufferedReader br = null;
        BufferedReader wr = null;
        String line = "";
        String Path = "database/" + refix + output + ".txt";
        if (refix.equals("Score_")) {
            Path = "database/" + refix + output + "_" + Subject + ".txt";
        }
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
                    String lineTrim = itemImport[0].trim();
                    if (lineTrim.equals(item[0].trim())) {
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
                    String lineTrim = item[0].trim();
                    if (lineTrim.equals(Key.trim())) {
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
        System.out.println("5: Xem danh sách lớp học.");
        System.out.println("6: Xem danh sách thời khóa biểu.");
        System.out.println("7: Import điểm môn học.");
        System.out.println("8: Xem bảng điểm.");
        System.out.println("9: Sửa điểm sinh viên.");

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
                    System.out.print("Nhập tên lớp: ");
                    ClassName = dataIn.readLine();
                    System.out.print("Nhập môn học: ");
                    Subject = dataIn.readLine();
                    System.out.print("Nhập MSSV: ");
                    StudentID = dataIn.readLine();
                    String file = "Class_" + ClassName + "_" + Subject;

                    BufferedReader reader = null;
                    BufferedWriter writer = null;
                    File inputFile = null;
                    File tempFile = null;
                    try {
                        inputFile = new File("database/" + file + ".txt");
                        tempFile = new File("database/" + file + "_Temp.txt");
                        reader = new BufferedReader(new FileReader(inputFile));
                        writer = new BufferedWriter(new FileWriter(tempFile));

                        String currentLine;
                        while ((currentLine = reader.readLine()) != null) {
                            String[] item = currentLine.split(",");
                            String lineToRemove = item[0].trim();
                            if (lineToRemove.equals(StudentID.trim())) {
                                continue;
                            }
                            writer.write(currentLine + System.getProperty("line.separator"));
                        }

                    } catch (FileNotFoundException e) {
                    } catch (IOException e) {
                    } finally {
                        if (reader != null) {
                            try {
                                writer.close();
                                reader.close();
                                //Xóa file tạm
                                if (!inputFile.delete()) {
                                    System.out.println("Could not delete file");
                                }
                                //Đổi tên file tạm thành file chính
                                if (!tempFile.renameTo(inputFile)) {
                                    System.out.println("Could not rename file");
                                }
                            } catch (IOException e) {
                            }
                        } else {
                            System.out.println("File không tồn tại.");
                        }
                    }
                } else {
                    System.out.println("Bạn không chọn đúng chức năng!");
                }
            } else {
                System.out.println("Bạn vừa nhập vào không phải số!");
            }
        } else if (function == 5) {
            int Choose = 2;
            System.out.print("Chọn chức năng (0: Xem danh sách lớp, 1: Xem danh sách lớp học môn học): ");
            // Tiến hành đọc từ bàn phím
            String strF = dataIn.readLine();
            Pattern pattern = Pattern.compile("\\d*");
            Matcher matcher = pattern.matcher(strF);
            if (matcher.matches()) {
                Choose = Integer.parseInt(strF);
                if (Choose == 0) {
                    System.out.print("Nhập tên lớp: ");
                    String ClassName = dataIn.readLine();
                    String file = "Class_" + ClassName;
                    ReadData(file, "Class");
                } else if (Choose == 1) {
                    System.out.print("Nhập tên lớp: ");
                    String ClassName = dataIn.readLine();
                    System.out.print("Nhập môn học: ");
                    String Subject = dataIn.readLine();
                    String file = "Class_" + ClassName + "_" + Subject;
                    ReadData(file, "Class");
                } else {
                    System.out.println("Bạn không chọn đúng chức năng!");
                }
            } else {
                System.out.println("Bạn vừa nhập vào không phải số!");
            }
        } else if (function == 6) {
            System.out.print("Nhập tên lớp: ");
            String ClassName = dataIn.readLine();
            String file = "TKB_" + ClassName;
            ReadData(file, "TKB");
        } else if (function == 7) {
            ImportFile(dataIn, "Score_");
        } else if (function == 8) {
            int Choose = 2;
            System.out.print("Chọn chức năng (0: Xem danh sách điểm, 1: Xem danh sách sinh viên đậu, rớt), 2: Xem tỉ lệ sinh viên đậu, rớt : ");
            // Tiến hành đọc từ bàn phím
            String strF = dataIn.readLine();
            Pattern pattern = Pattern.compile("\\d*");
            Matcher matcher = pattern.matcher(strF);
            if (matcher.matches()) {
                Choose = Integer.parseInt(strF);
                if (Choose == 0 || Choose == 1 || Choose == 2) {
                    System.out.print("Nhập tên lớp: ");
                    String ClassName = dataIn.readLine();
                    System.out.print("Nhập môn học: ");
                    String Subject = dataIn.readLine();
                    String file = "Score_" + ClassName + "_" + Subject;
                    BufferedReader br = null;
                    String line = "";
                    String Path = "database/" + file + ".txt";
                    try {
                        br = new BufferedReader(new FileReader(Path));
                        line = br.readLine();
                        int countPass = 0;
                        int countLose = 0;
                        int countTotal = 0;
                        while ((line = br.readLine()) != null) {
                            String[] item = line.split(",");
                            if (Choose == 0) {
                                System.out.println("Thông tin điểm sinh viên: [MSSV: " + item[0] + " , Họ tên: " + item[1] + " , Điểm giữa kỳ: " + item[2] + " , Điểm cuối kỳ: " + item[3] + " , Điểm khác: " + item[4] + " , Điểm tổng kết: " + item[5] + "]");
                            } else if (Choose == 1) {
                                if (Integer.parseInt(item[5]) >= 5) {
                                    System.out.println("Thông tin điểm sinh viên: [MSSV: " + item[0] + " , Họ tên: " + item[1] + " , Kết quả: Đậu]");
                                } else {
                                    System.out.println("Thông tin điểm sinh viên: [MSSV: " + item[0] + " , Họ tên: " + item[1] + " , Kết quả: Rớt]");
                                }
                            } else if (Choose == 2) {
                                countTotal = countTotal + 1;
                                if (Integer.parseInt(item[5]) >= 5) {
                                    countPass = countPass + 1;
                                } else {
                                    countLose = countLose + 1;
                                }
                            }
                        }
                        if (Choose == 2) {
                            int ratioPass = Math.round(((float) countPass / countTotal) * 100);
                            int ratioLose = 100 - ratioPass;
                            System.out.println("Số sinh viên đậu là " + countPass + " chiếm " + ratioPass + "%");
                            System.out.println("Số sinh viên rớt là " + countLose + " chiếm " + ratioLose + "%");
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
                            System.out.println("File không tồn tại.");
                        }
                    }
                } else {
                    System.out.println("Bạn không chọn đúng chức năng!");
                }
            } else {
                System.out.println("Bạn vừa nhập vào không phải số!");
            }
        } else if (function == 9) {

        } else {
            System.out.println("Chức năng không tồn tại. Vui lòng kiểm tra lại.");
        }

        int continueF = 0;

        System.out.print(
                "Thực hiện tiếp chương trình (1: Có, 0: Không): ");
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
