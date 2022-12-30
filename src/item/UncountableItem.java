package item;

public class UncountableItem extends Item {
    private double mass;

    public UncountableItem() {
        super();
        this.mass = -1;
    }

    public UncountableItem(String itemCode, double volume, String serialNumber, double mass, String name, int cost, int price) {
        super(itemCode, volume, serialNumber, name, cost, price);
        this.mass = mass;
    }

    public UncountableItem(UncountableItem otherUncountableItem) {
        super(otherUncountableItem);
        this.mass = otherUncountableItem.getMass();
    }

    public double getMass() {
        return this.mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public String toString() {
        return (super.toString() + " Mass: " + this.mass);
    }

    public boolean equals(Object object) {
        if (object == null)
            return false;
        if (getClass() != object.getClass())
            return false;
        UncountableItem otherUncountableItem = (UncountableItem) object;
        return (super.equals(otherUncountableItem) && this.mass == otherUncountableItem.getMass());
    }

    public UncountableItem clone() {
        return new UncountableItem(this);
    }

    public double getCost() {
        return this.mass * this.getUnitCost();
    }

    public void printProductionStatus() {
        System.out.print(this.mass + " kilograms of " + this.getName()
                + " has been produced with the serial number " + this.getSerialNumber());
    }
}
