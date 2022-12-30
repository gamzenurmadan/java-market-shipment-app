package utils;

import java.util.ArrayList;
import java.util.List;

import container.Container;
import item.CountableItem;
import item.UncountableItem;
import box.MassBox;
import box.NumberBox;
import producible.Producible;

public class Command {
    private List<String> commandLine;
    private String serialNumberOfLoadingItemOrBox;
    private String serialNumberOfHolder;
    private String containerToBeShippedSerialNumber;
    private RevenueCommandType revenueCommandType;

    public Command(List<String> commandLine) {
        this.commandLine = new ArrayList<String>(commandLine);
    }

    public Producible getProducible() {
        switch (this.commandLine.get(1)) {
            case "M1":
                return new CountableItem("M1", Integer.valueOf(this.commandLine.get(2)),
                        this.commandLine.get(3), "Box of milk", 5, 11);
            case "W1":
                return new CountableItem("W1", Integer.valueOf(this.commandLine.get(2)),
                        this.commandLine.get(3), "Box of water", 1, 3);
            case "O1":
                return new CountableItem("O1", Integer.valueOf(this.commandLine.get(2)),
                        this.commandLine.get(3), "Box of oil", 20, 45);

            case "S1":
                return new UncountableItem("S1", Double.valueOf(this.commandLine.get(3)),
                        this.commandLine.get(4), Integer.valueOf(this.commandLine.get(2)), "Sugar", 13, 25);
            case "F1":
                return new UncountableItem("F1", Double.valueOf(this.commandLine.get(3)),
                        this.commandLine.get(4), Integer.valueOf(this.commandLine.get(2)), "Flour", 5, 12);
            case "P1":
                return new UncountableItem("P1", Double.valueOf(this.commandLine.get(3)),
                        this.commandLine.get(4), Integer.valueOf(this.commandLine.get(2)), "Pasta", 12, 28);
            case "R1":
                return new UncountableItem("R1", Double.valueOf(this.commandLine.get(3)),
                        this.commandLine.get(4), Integer.valueOf(this.commandLine.get(2)), "Rice", 16, 32);

            case "B1":
                return new NumberBox("B1", Double.valueOf(this.commandLine.get(3)), this.commandLine.get(4),
                        Integer.valueOf(this.commandLine.get(2)), 0);
            case "B2":
                return new MassBox("B2", Integer.valueOf(this.commandLine.get(3)), this.commandLine.get(4),
                        Double.valueOf(this.commandLine.get(2)), 0);

            case "C1":
                return new Container(this.commandLine.get(1), Integer.valueOf(this.commandLine.get(2)),
                        this.commandLine.get(3), 0);

            default:
                return null;

        }
    }

    public void parseAndSetLoadSerialNumbers() {
        this.serialNumberOfLoadingItemOrBox = commandLine.get(1);
        this.serialNumberOfHolder = commandLine.get(2);
    }

    public void parseShipCommand() {
        this.containerToBeShippedSerialNumber = commandLine.get(1);
    }

    public void parseRevenueCommand() {
        if(commandLine.get(1).equals("1")) {
            this.revenueCommandType = RevenueCommandType.UNEARNED;
        } else if (commandLine.get(1).equals("2")) {
            this.revenueCommandType = RevenueCommandType.EARNED;
        }
    }

    public String getContainerToBeShippedSerialNumber() {
        return this.containerToBeShippedSerialNumber;
    }

    public String getSerialNumberOfLoadingItemOrBox() {
        return this.serialNumberOfLoadingItemOrBox;
    }

    public String getSerialNumberOfHolder() {
        return this.serialNumberOfHolder;
    }

    public RevenueCommandType getReveneuCommandType() {
        return this.revenueCommandType;
    }

    public enum RevenueCommandType {
        UNEARNED,
        EARNED
    }
    
}