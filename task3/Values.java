public class Values {

    public Values(int Id, String Value){
        this.Id = Id;
        this.Value = Value;
    }
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    int Id;

    public void setValue(String value) {
        Value = value;
    }

    public String getValue() {
        return Value;
    }

    String Value;
}
