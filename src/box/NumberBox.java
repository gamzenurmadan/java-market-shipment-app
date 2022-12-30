package box;
public class NumberBox extends Box {
    private int maxNumberOfItems;
    private int currentNumberOfItems;
    private static final Integer UNIT_COST = 2;

    public NumberBox() {
        super();
        this.maxNumberOfItems = -1;
        this.currentNumberOfItems = 0;
    }

    public NumberBox(String boxCode, double maxVolume, String serialNumber, int numberOfItems, int currentNumberOfItems) {
        super(boxCode, maxVolume, serialNumber, UNIT_COST * maxVolume, 0);
        this.maxNumberOfItems = numberOfItems;
        this.currentNumberOfItems = currentNumberOfItems;
    }

    public int getMaxNumberOfItems() {
        return this.maxNumberOfItems;
    }

    public void setMaxNumberOfItems(int numberOfItems) {
        this.maxNumberOfItems = numberOfItems;
    }

    public int getCurrentNumberOfItems() {
        return this.currentNumberOfItems;
    }

    public void setCurrentNumberOfItems(int currentNumberOfItems) {
        this.currentNumberOfItems = currentNumberOfItems;
    }


    public void printProductionStatus() {
        System.out.print(this.getMaxVolume() + " liters of number box has been produced with capacity of " + this.maxNumberOfItems
                + " with the serial number " + this.getSerialNumber());
    }

    @Override
    public String toString(){
        return (super.toString() + " Number of Items: " + this.getMaxNumberOfItems());
    }

    public boolean equals(Object o) {
    if (o == null)
        return false;
    if ((getClass() != o.getClass())) {
        return false;
    }
    NumberBox numberBox = (NumberBox) o;
    return (super.equals(numberBox) && this.maxNumberOfItems == numberBox.getMaxNumberOfItems());
    }
 
}