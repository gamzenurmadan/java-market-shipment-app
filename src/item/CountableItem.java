package item;
public class CountableItem extends Item {
    public CountableItem() {
        super();
    }

    public CountableItem(String itemCode, double volume, String serialNumber, String name, int cost, int price) {
        super(itemCode, volume, serialNumber, name, cost, price);
    }

    public CountableItem(CountableItem aCountableItem) {
        super(aCountableItem);
    }

    public String toString() {
        return (super.toString());
    }

    public boolean equals(Object object) {
        if (object == null)
            return false;
        if (getClass() != object.getClass())
            return false;
        CountableItem aCountableItem = (CountableItem) object;
        return super.equals(aCountableItem);
    }

    public CountableItem clone() {
        return new CountableItem(this);
    }

    public void printProductionStatus() {
        System.out.print(this.getVolume() + " liter of " + this.getName()
                + " has been produced with the serial number " + this.getSerialNumber());
    }

    @Override
    public double getCost() {
        return this.getVolume() * this.getUnitCost();
    }

    

}
