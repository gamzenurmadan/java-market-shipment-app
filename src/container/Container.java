package container;

import java.util.ArrayList;
import java.util.List;

import box.Box;
import producible.Producible;

public class Container implements Producible {
    private String containerCode;
    private double maxVolume;
    private double currentVolume;
    private String serialNumber;
    private double cost;
    private static final int UNIT_COST = 1;
    private List<Box> boxList = new ArrayList<Box>();

    public Container() {
        this.containerCode = "";
        this.maxVolume = -1;
        this.serialNumber = "";
        this.cost = -1;
        this.currentVolume = 0;
    }

    public Container(String containerCode, double volume, String serialNumber, double currentVolume) {
        this.containerCode = containerCode;
        this.maxVolume = volume;
        this.serialNumber = serialNumber;
        this.cost = volume * UNIT_COST;
        this.currentVolume = currentVolume;
    }

    public Container(Container otherContainer) {
        this.containerCode = otherContainer.getContainerCode();
        this.maxVolume = otherContainer.getMaxVolume();
        this.serialNumber = otherContainer.getSerialNumber();
        this.boxList = otherContainer.getBoxList();
    }

    public String getContainerCode() {
        return this.containerCode;
    }

    public void setContainerCode(String containerCode) {
        this.containerCode = containerCode;
    }

    public double getMaxVolume() {
        return this.maxVolume;
    }

    public void setMaxVolume(double volume) {
        this.maxVolume = volume;
    }


    public double getCurrentVolume() {
        return this.currentVolume;
    }

    public void setCurrentVolume(double currentVolume) {
        this.currentVolume = currentVolume;
    }

    public String getSerialNumber() {
        return this.serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public double getCost() {
        return this.cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public List<Box> getBoxList() {
        return new ArrayList<Box>(this.boxList);
    }

    public void setBoxList(List<Box> box) {
        this.boxList = new ArrayList<Box>(box);
    }

    public Container clone() {
        return new Container(this);
    }

    public double calculateRevenue() {
        double totalRevenue = 0;
        for (Box box : this.boxList) {
            totalRevenue += box.calculateRevenue();
        }
        return totalRevenue;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if ((getClass() != o.getClass())) {
            return false;
        }
        Container container = (Container) o;
        return (this.serialNumber.equals(container.getSerialNumber()) && this.maxVolume == container.getMaxVolume()
                && this.serialNumber.equals(container.getSerialNumber()) && this.cost == container.getCost()
                && this.boxList.equals(container.getBoxList()));
    }

    public String toString() {
        return ("Container Code: " + this.containerCode + " Volume: " + this.maxVolume + " Serial Number: " +
                this.serialNumber);
    }

    public void printProductionStatus() {
        System.out.print(
                this.maxVolume + " liters of container has been produced with the serial number " + this.serialNumber);
    }

}
