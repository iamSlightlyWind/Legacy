package DTO;

public class Painting extends Item {
    private int height, width;
    private boolean isWatercolour, isFramed;

    public Painting() {}

    public Painting(int value, String creator, int height, int width, boolean isWatercolour, boolean isFramed) {
        super(value, creator);
        this.height = height;
        this.width = width;
        this.isWatercolour = isWatercolour;
        this.isFramed = isFramed;
    }

    public void inputPainting() {
        super.input();
        this.height = Verify.getInt("height");
        this.width = Verify.getInt("width");
        this.isWatercolour = Verify.getBoolean("isWatercolour");
        this.isFramed = Verify.getBoolean("isFramed");
    }

    public void outputPainting() {
        super.output("Painting");
        System.out.println("Painting height: " + height);
        System.out.println("Painting width: " + width);
        System.out.println("Painting water coloured: " + isWatercolour);
        System.out.println("Painting framed: " + isFramed);
    }

    public void set(String attribute) {
        switch (attribute) {
            case "value":
                this.value = Verify.getInt("value");
                break;
            case "creator":
                this.creator = Verify.getString("creator");
                break;
            case "height":
                this.height = Verify.getInt("height");
                break;
            case "width":
                this.width = Verify.getInt("width");
                break;
            case "isWatercolour":
                this.isWatercolour = Verify.getBoolean("isWatercolour");
                break;
            case "isFramed":
                this.isFramed = Verify.getBoolean("isFramed");
                break;
        }
    }

    public String get(String attribute) {
        switch (attribute) {
            case "value":
                return String.valueOf(this.value);
            case "creator":
                return this.creator;
            case "height":
                return String.valueOf(this.height);
            case "width":
                return String.valueOf(this.width);
            case "isWatercolour":
                return String.valueOf(this.isWatercolour);
            case "isFramed":
                return String.valueOf(this.isFramed);
        }
        return null;
    }
}
