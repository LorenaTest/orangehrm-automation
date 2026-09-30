package Models;

public class Employee {

    private String firstName;
    private String middleName;
    private String lastName;
    private String employeeId;
    private String username;
    private String password;

    private PersonalDetails personalDetails;
    private CustomFields customFields;
    private AddAttachment addAttachment;

    public Employee(String firstName, String middleName, String lastName, String employeeId, String username, String password) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.employeeId = employeeId;
        this.username = username;
        this.password = password;
    }

    public Employee(String firstName, String middleName, String lastName, String employeeId, String username, String password, PersonalDetails personalDetails, CustomFields customFields, AddAttachment addAttachment) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.employeeId = employeeId;
        this.username = username;
        this.password = password;
        this.personalDetails = personalDetails;
        this.customFields = customFields;
        this.addAttachment = addAttachment;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PersonalDetails getPersonalDetails() {
        return personalDetails;
    }

    public void setPersonalDetails(PersonalDetails personalDetails) {
        this.personalDetails = personalDetails;
    }

    public CustomFields getCustomFields() {
        return customFields;
    }

    public void setCustomFields(CustomFields customFields) {
        this.customFields = customFields;
    }

    public AddAttachment getAddAttachment() {
        return addAttachment;
    }

    public void setAddAttachment(AddAttachment addAttachment) {
        this.addAttachment = addAttachment;
    }
}
