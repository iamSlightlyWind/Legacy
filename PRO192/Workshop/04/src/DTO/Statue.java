package DTO;

public class Statue extends Item{
    private int weight;
    private String colour;

    public Statue(){}

    public Statue(int value, String creator, int weight, String colour){
        super(value, creator);
        this.weight = weight;
        this.colour = colour;
    }

    public void inputStatue(){
        super.input();
        this.weight = Verify.getInt("weight");
        this.colour = Verify.getString("colour");
    }

    public void outputStatue(){
        super.output("Statue");
        System.out.println("Statue weight: " + weight);
        System.out.println("Statue colour: " + colour);
    }

    public void set(String attribute){
        switch (attribute){
            case "value":
                this.value = Verify.getInt("value");
                break;
            case "creator":
                this.creator = Verify.getString("creator");
                break;
            case "weight":
                this.weight = Verify.getInt("weight");
                break;
            case "colour":
                this.colour = Verify.getString("colour");
                break;
        }
    }

    public String get(String attribute){
        switch (attribute){
            case "value":
                return String.valueOf(this.value);
            case "creator":
                return this.creator;
            case "weight":
                return String.valueOf(this.weight);
            case "colour":
                return this.colour;
        }
        return null;
    }

}
