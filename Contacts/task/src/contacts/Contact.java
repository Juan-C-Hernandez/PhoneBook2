package contacts;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Contact implements Serializable {
    private String phoneNumber;
    private final LocalDateTime creationTime;
    private LocalDateTime lastModTime;

    public Contact() {
        this.phoneNumber = "";
        creationTime = lastModTime = LocalDateTime.now();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        String regex = "^\\+?([\\da-zA-Z][ -]?)?(\\([\\da-zA-Z]{2,}\\)([ -][\\da-zA-Z]{2,})*" +
                "|[\\da-zA-Z]{2,}([ -]\\([\\da-zA-Z]{2,}\\))?([ -][\\da-zA-Z]{2,})*)?$";
        Matcher matcher = Pattern.compile(regex).matcher(phoneNumber);
        updateModTime();
        this.phoneNumber = matcher.matches() ? phoneNumber : "";
    }

    public boolean hasNumber() {
        return phoneNumber != null && !phoneNumber.equals("");
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public LocalDateTime getLastModTime() {
        return lastModTime;
    }

    public void updateModTime() {
        this.lastModTime = LocalDateTime.now();
    }

    public abstract String[] getFields();

    public abstract String getFieldValue(String field);

    public abstract void setField(String field, String value);

    @Override
    public String toString() {
        String ln = System.lineSeparator();
        StringBuilder sb = new StringBuilder();
        sb.append("Number: ").append(hasNumber() ? getPhoneNumber() : "[no number]").append(ln);
        sb.append("Time created: ").append(getCreationTime()).append(ln);
        sb.append("Time last edit: ").append(getLastModTime());
        return sb.toString();
    }

    public abstract String getIntro();

    public abstract String query();
}
