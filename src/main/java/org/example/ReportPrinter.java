package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Printer {
    TariffCalculator calculator = new TariffCalculator();
    public void print(Map<String, List<CallDataRecord>> cdrReportsMap) {
        for (Map.Entry<String, List<CallDataRecord>> entry : cdrReportsMap.entrySet()) {
            String phoneNumber = entry.getKey();
            List<CallDataRecord> callDataRecordList = entry.getValue();
            String reportFileName = phoneNumber + ".txt";
            String directoryName = "src/main/java/org/example/reports/";
            File file = new File(directoryName + reportFileName);
            String lineTariff = "Tariff index: " + entry.getValue().get(0).getTariffType().getTariffTypeName()+"\n";
            String lineDelimeter = "----------------------------------------------------------------------------" + "\n";
            String linePhoneNumber = "Report for phone number " + entry.getKey() + ":" + "\n";
            String headers = "| Call Type |   Start Time        |     End Time        | Duration | Cost  |" + "\n";

            try (FileWriter fileWriter = new FileWriter(file)) {
                fileWriter.write(lineTariff);
                fileWriter.write(lineDelimeter);
                fileWriter.write(linePhoneNumber);
                fileWriter.write(lineDelimeter);
                fileWriter.write(headers);
                fileWriter.write(lineDelimeter);

                double totalCost = 100.0;

                for (CallDataRecord callDataRecord : callDataRecordList) {

                    double cost = calculator.calculateSubscriberByTariff(callDataRecord);
                    String costCdr = " |  " + cost + "  |";
                    fileWriter.write(callDataRecord.toString() + costCdr + "\n");
                    totalCost += cost;
                }
                fileWriter.write(lineDelimeter);
                fileWriter.write("|                                           Total Cost: |     "
                        + totalCost + " rubles |"
                        + "\n");
                fileWriter.write(lineDelimeter);


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
