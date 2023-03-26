package org.example;

public enum CallType {
    INCOMING("01"),
    OUTGOING("02");
    private final String callTypeName;

    CallType(String callTypeName) {
        this.callTypeName = callTypeName;
    }

    public static CallType getTypeFromString(String type) {

        for (CallType callType : values()) {
            if (callType.getCallTypeName().equals(type)) {
                return callType;
            }
        }
        throw new IllegalArgumentException("");
    }

    public String getCallTypeName() {
        return callTypeName;
    }

    @Override
    public String toString() {
        return "CallType{" +
                "callTypeName='" + callTypeName + '\'' +
                '}';
    }
}
