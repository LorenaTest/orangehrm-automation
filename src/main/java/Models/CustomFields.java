package Models;

import pages.SuccessToastModalPage;

public class CustomFields {

    private String bloodType;
    private String testField;

    public CustomFields(String testField, String bloodType) {
        this.testField = testField;
        this.bloodType = bloodType;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getTestField() {
        return testField;
    }

    public void setTestField(String testField) {
        this.testField = testField;
    }

}
