package org.example;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class CallDataRecord {
    private CallType callType;
    private TariffType tariffType;
    private String phoneNumber;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public CallDataRecord() {
    }

    public CallDataRecord(CallType callType, TariffType tariffType, String phoneNumber, LocalDateTime startDate, LocalDateTime endDate) {
        this.callType = callType;
        this.tariffType = tariffType;
        this.phoneNumber = phoneNumber;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public CallType getCallType() {
        return callType;
    }

    public void setCallType(CallType callType) {
        this.callType = callType;
    }

    public TariffType getTariffType() {
        return tariffType;
    }

    public void setTariffType(TariffType tariffType) {
        this.tariffType = tariffType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Duration  duration = Duration.between(startDate, endDate);
        return "|     " + callType.getCallTypeName() +
                "    | " + startDate.format(formatter) +
                " | " + endDate.format(formatter) +
                " | " + String.format("%02d:%02d:%02d", duration.getSeconds() / 3600,
                (duration.getSeconds() % 3600) / 60,
                (duration.getSeconds() % 60))
                ;
    }
}
