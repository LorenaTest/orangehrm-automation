package Models;

public class PersonalDetails {

    private String otherId;
    private String driversLicenseNumber;
    private String licenseExpiryDate;
    private String nationality;
    private String maritalStatus;
    private String dateOfBirth;
    private String gender;

    public PersonalDetails(String driversLicenseNumber, String licenseExpiryDate, String otherId, String nationality, String maritalStatus, String dateOfBirth, String gender) {
        this.driversLicenseNumber = driversLicenseNumber;
        this.licenseExpiryDate = licenseExpiryDate;
        this.otherId = otherId;
        this.nationality = nationality;
        this.maritalStatus = maritalStatus;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }

    public String getOtherId() {
        return otherId;
    }

    public void setOtherId(String otherId) {
        this.otherId = otherId;
    }

    public String getDriversLicenseNumber() {
        return driversLicenseNumber;
    }

    public void setDriversLicenseNumber(String driversLicenseNumber) {
        this.driversLicenseNumber = driversLicenseNumber;
    }

    public String getLicenseExpiryDate() {
        return licenseExpiryDate;
    }

    public void setLicenseExpiryDate(String licenseExpiryDate) {
        this.licenseExpiryDate = licenseExpiryDate;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
