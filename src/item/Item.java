package item;

import producible.Producible;

public abstract class Item implements Producible {
    private String itemCode;
    private double volume;
    private String serialNumber;
    private String name;
    private int unitCost;
    private double unitPrice;
    

    public Item() {
        this.itemCode = "";
        this.volume = -1;
        this.serialNumber = "";
        this.name = "";
    }

    public Item(String itemCode, double volume, String serialNumber, String name, int unitCost, int unitPrice) {
        this.itemCode = itemCode;
        this.volume = volume;
        this.serialNumber = serialNumber;
        this.name = name;
        this.unitCost = unitCost;
        this.unitPrice = unitPrice;
    }

    public Item(Item anItem) {
        this.itemCode = anItem.getItemCode();
        this.volume = anItem.getVolume();
        this.serialNumber = anItem.getSerialNumber();
    }

    public String getItemCode() {
        return this.itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public double getVolume() {
        return this.volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public String getSerialNumber() {
        return this.serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String toString() {
        return ("Name: "+this.name+" Item Code: " + this.itemCode + " Volume: " + this.volume + " Serial Number: " + this.serialNumber);
    }

    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (getClass() != object.getClass()) {
            return false;
        }
        Item anItem = (Item) object;
        return (this.itemCode.equals(anItem.getItemCode())
                && this.volume == (anItem.getVolume())
                && this.serialNumber.equals(anItem.getSerialNumber())
                && this.name.equals(anItem.getName())
                && (this.unitCost == anItem.getUnitCost())
                && (this.unitPrice == anItem.getUnitPrice()));
    }

    
    public abstract double getCost();


    public int getUnitCost() {
        return this.unitCost;
    }

    public void setUnitCost(int unitCost) {
        this.unitCost = unitCost;
    }

    public double getUnitPrice() {
        return this.unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
    
    public double getPrice() {
        return this.unitPrice * this.volume;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public abstract Item clone();
    public abstract void printProductionStatus();
}
