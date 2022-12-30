package box;

import java.util.ArrayList;
import java.util.List;

import item.Item;
import producible.Producible;

public abstract class Box implements Producible {
    private String boxCode;
    private double maxVolume;
    private double currentVolume;
    private String serialNumber;
    private double cost;
    List<Item> itemList = new ArrayList<Item>();

    public Box() {
        this.boxCode = "";
        this.maxVolume = -1;
        this.serialNumber = "";
        this.cost = 0;
        this.currentVolume = -1;
    }

    public Box(String boxCode, double maxVolume, String serialNumber, double cost, int currentVolume) {
        this.boxCode = boxCode;
        this.maxVolume = maxVolume;
        this.serialNumber = serialNumber;
        this.cost = cost;
        this.currentVolume = currentVolume;
    }

    public String getBoxCode() {
        return this.boxCode;
    }

    public void setBoxCode(String boxCode) {
        this.boxCode = boxCode;
    }

    public double getMaxVolume() {
        return this.maxVolume;
    }

    public void setMaxVolume(double volume) {
        this.maxVolume = volume;
    }

    public String getSerialNumber() {
        return this.serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public List<Item> getItemList() {
        return new ArrayList<Item>(this.itemList);
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = new ArrayList<Item>(itemList);
    }

    public double getCost() {
        return this.cost;
    }

    public double getCurrentVolume() {
        return this.currentVolume;
    }

    public void setCurrentVolume(double currentVolume) {
        this.currentVolume = currentVolume;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double calculateRevenue() {
        double revenue = 0;
        for(Item item : this.itemList) {
            revenue += item.getPrice();
        }
        return revenue;
    }

    public abstract void printProductionStatus();

    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if ((getClass() != o.getClass())) {
            return false;
        }
        Box box = (Box) o;
        return (this.boxCode.equals(box.getBoxCode()) && this.maxVolume == box.getMaxVolume()
                && this.serialNumber.equals(box.getSerialNumber()) && this.cost == box.getCost()
                && this.itemList.equals(box.getItemList()));
    }

    public String toString() {
        return ("Box Code: " + this.boxCode + " Volume: " + this.maxVolume + " Serial Number: " +
                this.serialNumber);
    }

}