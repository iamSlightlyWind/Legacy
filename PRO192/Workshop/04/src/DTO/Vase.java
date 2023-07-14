package DTO;

public class Vase extends Item {
    private int height;
    private String material;

    public Vase(){}

    public Vase(int value, String creator, int height, String material){
        super(value, creator);
        this.height = height;
        this.material = material;
    }

    public void inputVase(){
        super.input();
        this.height = Verify.getInt("height");
        this.material = Verify.getString("material");
    }

    public void outputVase(){
        super.output("Vase");
        System.out.println("Vase height: " + height);
        System.out.println("Vase material: " + material);
    }

    public void set(String attribute){
        switch (attribute){
            case "value":
                this.value = Verify.getInt("value");
                break;
            case "creator":
                this.creator = Verify.getString("creator");
                break;
            case "height":
                this.height = Verify.getInt("height");
                break;
            case "material":
                this.material = Verify.getString("material");
                break;
        }
    }

    public String get(String attribute){
        switch (attribute){
            case "value":
                return String.valueOf(this.value);
            case "creator":
                return this.creator;
            case "height":
                return String.valueOf(this.height);
            case "material":
                return this.material;
        }
        return null;
    }
}
