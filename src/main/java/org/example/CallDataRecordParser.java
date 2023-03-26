package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CdrParser {
    private CallDataRecord callDataRecord;
    private ArrayList<CallDataRecord> listCallDataRecords = new ArrayList<>();

    public ArrayList<CallDataRecord> parse(ArrayList<String> fileLines) {
        for (String line : fileLines) {
            String[] oneCdr = parseLine(line);
            if (oneCdr.length < 5) {
                System.out.println("CallDataRecord must contained at least 5 fields!");
            }
            callDataRecord = new CallDataRecord();
            assignCdrValues(oneCdr);
            listCallDataRecords.add(callDataRecord);
        }
        return listCallDataRecords;
    }

    private String[] parseLine(String line) {
        String inputWithoutSpaces = line.replaceAll("\\s+", "");
        return inputWithoutSpaces.split(",");
    }

    private void assignCdrValues(String[] cdrFields) {
        for (String field : cdrFields) {
            assignCdrField(field);
        }
    }

    private void assignCdrField(String cdrField) {
        switch (cdrField.length()) {
            case 2:
                parseValue(cdrField);
                break;
            case 11:
                callDataRecord.setPhoneNumber(cdrField);
                break;
            case 14:
                parseDateTime(cdrField);
                break;
            default:
                System.out.println("Field: " + cdrField + " outside the contract.");
        }

    }

    private void parseValue(String cdrField) {


        if (cdrField.equals(CallType.INCOMING.getCallTypeName())||cdrField.equals(CallType.OUTGOING.getCallTypeName())) {
            CallType callType = CallType.getTypeFromString(cdrField);
            callDataRecord.setCallType(callType);
        }
        else if (cdrField.equals(TariffType.UNLIMITED.getTariffTypeName())||cdrField.equals(TariffType.REGULAR.getTariffTypeName())||cdrField.equals(TariffType.PER_MINUTE.getTariffTypeName()))
         {
             TariffType tariffType = TariffType.getTypeFromString(cdrField);
             callDataRecord.setTariffType(tariffType);
        }
    }


    private void parseDateTime(String cdrField) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

        if (callDataRecord.getStartDate() == null) {
            callDataRecord.setStartDate(LocalDateTime.parse(cdrField, formatter));
        } else if (callDataRecord.getEndDate() == null) {
            callDataRecord.setEndDate(LocalDateTime.parse(cdrField, formatter));
            swapDateIfNeeded(callDataRecord.getStartDate(), callDataRecord.getEndDate());
        }

    }

    private void swapDateIfNeeded(LocalDateTime callStart, LocalDateTime callEnd) {
        if (callStart.isAfter(callEnd)) {
            callDataRecord.setStartDate(callEnd);
            callDataRecord.setEndDate(callStart);
        }
    }
}
