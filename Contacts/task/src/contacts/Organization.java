package contacts;

public class Organization extends Contact{
    private String name;
    private String address;

    public Organization() {
        super();
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
        updateModTime();
    }

    public void setName(String name) {
        this.name = name;
        updateModTime();
    }

    @Override
    public String[] getFields() {
        return new String[] {"name", "address", "number"};
    }

    @Override
    public String getFieldValue(String field) {
        switch (field) {
            case "name":
                return name;
            case "number":
                return getPhoneNumber();
            case "address":
                return address;
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
            case "number":
                setPhoneNumber(value);
                break;
            case "address":
                setAddress(value);
                break;
        }
    }

    @Override
    public String toString() {
        String ln = System.lineSeparator();
        StringBuilder sb = new StringBuilder();
        sb.append("Organization name: ").append(name).append(ln);
        sb.append("Address: ").append(address).append(ln);
        sb.append(super.toString());
        return sb.toString();
    }

    @Override
    public String query() {
        return name + " " + address + " " + getPhoneNumber();
    }

    @Override
    public String getIntro() {
        return name;
    }
}
