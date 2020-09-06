package contacts;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Person extends Contact{
    private String name;
    private String surname;
    private LocalDate birthDate;
    private char gender;

    public Person() {
        super();
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public char getGender() {
        return gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String date) {
        try {
            birthDate = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            birthDate = null;
        }
        updateModTime();
    }

    public void setName(String name) {
        this.name = name;
        updateModTime();
    }

    public void setSurname(String surname) {
        this.surname = surname;
        updateModTime();
    }

    public void setGender(char gender) {
        if (gender == 'M' || gender == 'F'){
            this.gender = gender;
        } else {
            this.gender = '\u0000';
        }
        updateModTime();
    }

    @Override
    public String[] getFields() {
        return new String[] {"name", "surname", "birthday", "gender", "number"};
    }

    @Override
    public String getFieldValue(String field) {
        switch (field) {
            case "name":
                return name;
            case "surname":
                return surname;
            case "number":
                return getPhoneNumber();
            case "birthday":
                return birthDate.toString();
            case "gender":
                return String.valueOf(gender);
            default:
                return null;
        }
    }

    @Override
    public void setField(String field, String value) {
        switch (field) {
            case "name":
                setName(value);
                break;
            case "surname":
                setSurname(value);
                break;
            case "number":
                setPhoneNumber(value);
                break;
            case "birthday":
                setBirthDate(value);
                break;
            case "gender":
                if (value.length() > 0) {
                    setGender(value.charAt(0));
                }
                break;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String ln = System.lineSeparator();
        sb.append("Name: ").append(getName()).append(ln);
        sb.append("Surname: ").append(getSurname()).append(ln);
        sb.append("Birth date: ").append(getBirthDate() == null ? "[no data]" : getBirthDate()).append(ln);
        sb.append(("Gender: ")).append(getGender() == '\u0000' ? "[no data]" : getGender());
        sb.append(ln).append(super.toString());
        return sb.toString();
    }

    @Override
    public String getIntro() {
        return name + " " + surname;
    }

    @Override
    public String query() {
        return name + " " + surname + " " + getPhoneNumber();
    }
}
