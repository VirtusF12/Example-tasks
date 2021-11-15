package Task4;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    /*
    Необходимо отсортировать csv-файл по первому полю, можно считать, что оно целочисленное. Первое поле (ключ) может иметь не уникальное значение. Длинны каждой из строк могут отличаться.
    Попробуйте привести два решения:

    - одно для случаев, когда данные файла целиком помещаются в оперативную память

    - и второе, когда размер файла на порядки превышает объем доступной оперативной памяти.

    Если можете (или знаете) -  оцените сложность предложенных решений в «big O» нотации.

    Пример файла:
    FID;SERIAL_NUM;MEMBER_CODE;ACCT_TYPE;OPENED_DT;ACCT_RTE_CDE;REPORTING_DT;CREDIT_LIMIT
    2000;2202099;4B01GG000001;9;04.06.2014;0;14.10.2014;100000
    1200;1200;9999SM333333;9;24.08.2006;13;10.03.2007;265485
    1600;1400;9999SM333333;10;01.01.1999;0;04.04.2007;70000
    1800;1600;9999SM333333;10;01.01.1999;0;04.04.2007;17010000
    1601;1401;9999SM333333;10;01.01.1999;0;04.04.2007;70000
    1602;1402;”0101BB;0101CC”;10;01.01.1999;0;04.04.2007;70000
    1603;1403;9999SM333333;10;01.01.1999;0;04.04.2007;70020
    1600;1400;9999SM333333;10;01.01.1999;0;04.04.2007;70000
    1601;1401;9999SM333333;10;01.01.1999;0;04.04.2007;70000
    1800;1600;9999SM333333;10;01.01.1999;0;04.04.2007;17010000

     */

    private static final long MAX_SIZE = 123412;

    private static ArrayList<String> getDataCSVFile(String path) {

        ArrayList<String> list = new ArrayList<>();

        try {

            File file = new File(path);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = "";

            while (line != null) {
                line = reader.readLine();
                if (line != null) {

                    if (getFreeMemory() > MAX_SIZE) {
                        list.add(line);
                    } else {
                        System.err.println("no free memory");
                        break;
                    }
                }

            }

        } catch (Exception e) {
            System.err.println(e);
            return null;
        }

        return list;
    }

    private static long getFreeMemory() {

        return (Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory());
    }

    public static void main(String[] args) throws IOException {

        String path = "E:\\Test\\src\\Task4\\csv.txt";
        ArrayList<String> list = getDataCSVFile(path);
        ArrayList<CSV> listCSV = new ArrayList<>();

        if (list != null) {

            for (String s : list) {

                String[] row = s.split(";");
                CSV csv = new CSV(Integer.parseInt(row[0]),row[1],row[2],row[3],row[4],row[5],row[6],row[7]);

                if (getFreeMemory() > MAX_SIZE) {
                    listCSV.add(csv);
                } else {
                    System.err.println("no free memory");
                    break;
                }

            }

            List sortedList = listCSV.stream()
                    .sorted(Comparator.comparingInt(CSV::getFID))
                    .collect(Collectors.toList());

            sortedList.forEach(System.out::println);

        } else {
            System.err.println("list == null");
        }
    }

    static class CSV {

        private int FID;
        private String SERIAL_NUM, MEMBER_CODE, ACCT_TYPE, OPENED_DT, ACCT_RTE_CDE, REPORTING_DT, CREDIT_LIMIT;

        public CSV(int FID, String SERIAL_NUM, String MEMBER_CODE, String ACCT_TYPE, String OPENED_DT, String ACCT_RTE_CDE, String REPORTING_DT, String CREDIT_LIMIT) {
            this.FID = FID;
            this.SERIAL_NUM = SERIAL_NUM;
            this.MEMBER_CODE = MEMBER_CODE;
            this.ACCT_TYPE = ACCT_TYPE;
            this.OPENED_DT = OPENED_DT;
            this.ACCT_RTE_CDE = ACCT_RTE_CDE;
            this.REPORTING_DT = REPORTING_DT;
            this.CREDIT_LIMIT = CREDIT_LIMIT;
        }

        public int getFID() {
            return FID;
        }

        @Override
        public String toString() {
            return "CSV{" +
                    "FID='" + FID + '\'' +
                    ", SERIAL_NUM=" + SERIAL_NUM +
                    ", MEMBER_CODE=" + MEMBER_CODE +
                    ", ACCT_TYPE=" + ACCT_TYPE +
                    ", OPENED_DT=" + OPENED_DT +
                    ", ACCT_RTE_CDE=" + ACCT_RTE_CDE +
                    ", REPORTING_DT=" + REPORTING_DT +
                    ", CREDIT_LIMIT=" + CREDIT_LIMIT +
                    '}';
        }
    }

}
