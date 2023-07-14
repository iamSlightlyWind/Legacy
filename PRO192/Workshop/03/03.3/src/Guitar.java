public class Guitar {
    /*
     * Declare fields with access modifier as private: String serialNumber, int
     * price,
     * String builder, String model, String backWood, String topWood
     */
    private String serialNumber;
    private int price;
    private String builder, model, backWood, topWood;

    public Guitar() {
    }

    public Guitar(String serialNumber, int price, String builder, String model, String backWood, String topWood) {
        this.serialNumber = serialNumber;
        this.price = price;
        this.builder = builder;
        this.model = model;
        this.backWood = backWood;
        this.topWood = topWood;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        if (!serialNumber.isEmpty()) {
            this.serialNumber = serialNumber;
        }
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        if (price > 0) {
            this.price = price;
        }
    }

    public String getBuilder() {
        return builder;
    }

    public void setBuilder(String builder) {
        if (!builder.isEmpty()) {
            this.builder = builder;
        }
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        if (!model.isEmpty()) {
            this.model = model;
        }
    }

    public String getBackWood() {
        return backWood;
    }

    public void setBackWood(String backWood) {
        if (!backWood.isEmpty()) {
            this.backWood = backWood;
        }
    }

    public String getTopWood() {
        return topWood;
    }

    public void setTopWood(String topWood) {
        if (!topWood.isEmpty()) {
            this.topWood = topWood;
        }
    }

    public void createSound() {
        System.out.println("Serial Number: " + getSerialNumber());
        System.out.println("Price: " + getPrice());
        System.out.println("Builder: " + getBuilder());
        System.out.println("Model: " + getModel());
        System.out.println("Back Wood: " + getBackWood());
        System.out.println("Top Wood: " + getTopWood());
    }
}
