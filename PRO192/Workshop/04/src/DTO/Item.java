package DTO;

public class Item {
    protected int value;
    protected String creator;

    public Item() {
    }

    public Item(int value, String creator) {
        this.value = value;
        this.creator = creator;
    }

    public void input() {
        this.value = Verify.getInt("value");
        this.creator = Verify.getString("creator");
    }

    public void output() {
        System.out.println("Item value: " + value);
        System.out.println("item creator: " + creator);
    }

    public void output(String itemType) {
        System.out.println(itemType + " value: " + value);
        System.out.println(itemType + " creator: " + creator);
    }
}
