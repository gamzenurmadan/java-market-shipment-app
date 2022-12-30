package company;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import box.Box;
import box.MassBox;
import box.NumberBox;
import container.Container;
import error.BoxAlreadyInContainerException;
import error.BoxOrContainerShippedException;
import error.BoxVolumeExceededException;
import error.ContainerCapacityException;
import error.IncompatibleTypeForMassBoxException;
import error.IncompatibleTypeForNumberBoxException;
import error.ItemAlreadyBoxedException;
import error.ItemPlacementOnContainerException;
import error.ItemPlacementOnLoadedBoxException;
import error.MassBoxCapacityException;
import error.NumberBoxCapacityException;
import error.RuleException;
import error.SameSerialNumberProductionException;
import item.CountableItem;
import item.Item;
import item.UncountableItem;
import utils.Command;
import producible.Producible;

public class Company {
    private double revenue = 0;
    List<Item> items = new ArrayList<>();
    List<Box> boxes = new ArrayList<>();
    List<Container> containers = new ArrayList<>();
    Map<String, Producible> producibleArchiveMap = new HashMap<>();

    public Company() {

    }

    public Company(int revenue) {
        this.revenue = revenue;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    public void produceAndShip(List<List<String>> commandList) {
        for (List<String> command : commandList) {
            if (command.size() > 0) { // skip empty lines in CSV file

                Command lcommand = new Command(command);
                switch (command.get(0)) {
                    case "1" -> {
                        Producible producible = lcommand.getProducible();
                        produce(producible);
                    }
                    case "2" -> {
                        lcommand.parseAndSetLoadSerialNumbers();
                        String itemToBeLoadedSerialNumber = lcommand.getSerialNumberOfLoadingItemOrBox();
                        String holderSerialNumber = lcommand.getSerialNumberOfHolder();
                        Item item = findItem(itemToBeLoadedSerialNumber);
                        loadItemIfExists(item, holderSerialNumber, itemToBeLoadedSerialNumber);
                    }
                    case "3" -> {
                        lcommand.parseShipCommand();
                        String containerToBeShippedSerialNumber = lcommand.getContainerToBeShippedSerialNumber();
                        Container containerToBeShipped = findContainer(containerToBeShippedSerialNumber);
                        shipContainer(containerToBeShipped, containerToBeShippedSerialNumber);
                    }
                    case "4" -> {
                        lcommand.parseRevenueCommand();
                        switch (lcommand.getReveneuCommandType()) {
                            case UNEARNED -> System.out.println(
                                    "Unearned revenue: " + roundRevenue(this.calculateUnearnedRevenue()) + "TL");
                            case EARNED -> System.out.println("Total revenue: " + roundRevenue(this.revenue) + "TL");
                        }
                    }
                    default -> {
                        System.err.println("No such command exists!");
                        break;
                    }
                }
            }
        }
    }

    private void shipContainer(Container containerToBeShipped, String containerToBeShippedSerialNumber) {
        if (isShipContainerValid(containerToBeShipped, containerToBeShippedSerialNumber)) {
            double revenueEarned = containerToBeShipped.calculateRevenue();
            System.out.print("Container " + containerToBeShipped.getSerialNumber() + " has been shipped!");
            this.revenue += revenueEarned;
            containers.remove(containerToBeShipped);
            printRevenue();
        }
    }

    private boolean isShipContainerValid(Container containerToBeShipped, String containerToBeShippedSerialNumber) {
        try {
            if (containerToBeShipped == null) {
                if (producibleArchiveMap.containsKey(containerToBeShippedSerialNumber)) {
                    throw new BoxOrContainerShippedException(
                            "Container with serial number " + containerToBeShippedSerialNumber + " cannot be shipped");
                }
                throw new Exception(
                        "Container " + containerToBeShippedSerialNumber + " is not created, cannot be shipped!");
            }
        } catch (BoxOrContainerShippedException e) {
            System.err.println(e.getMessage() + "(EX:" + e.getErrorCode() + " already shipped");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return true;
    }

    private void produceItem(Producible producible) throws SameSerialNumberProductionException {
        Item item = (Item) producible;
        if (producibleArchiveMap.containsKey(item.getSerialNumber())) {
            throw new SameSerialNumberProductionException(
                    "Item with serial number " + item.getSerialNumber() + " cannot be produced");
        }

        items.add(item);
        producibleArchiveMap.put(item.getSerialNumber(), item);
        item.printProductionStatus();

        double revenue = this.getRevenue();
        double newRevenue = revenue - item.getCost();
        this.setRevenue(newRevenue);

        printRevenue();
    }

    private void produceBox(Producible producible) throws SameSerialNumberProductionException {
        Box box = (Box) producible;
        if (producibleArchiveMap.containsKey(box.getSerialNumber())) {
            throw new SameSerialNumberProductionException(
                    "Box with serial number " + box.getSerialNumber() + " cannot be produced");
        }

        boxes.add(box);
        producibleArchiveMap.put(box.getSerialNumber(), box);
        box.printProductionStatus();

        double revenue = this.getRevenue();
        double newRevenue = revenue - box.getCost();
        this.setRevenue(newRevenue);

        printRevenue();
    }

    private void produceContainer(Producible producible) throws SameSerialNumberProductionException {
        Container container = (Container) producible;
        if (producibleArchiveMap.containsKey(container.getSerialNumber())) {
            throw new SameSerialNumberProductionException(
                    "Container with serial number " + container.getSerialNumber() + " cannot be produced");
        }

        containers.add(container);
        producibleArchiveMap.put(container.getSerialNumber(), container);
        container.printProductionStatus();

        double revenue = this.getRevenue();
        double newRevenue = revenue - container.getCost();
        this.setRevenue(newRevenue);

        printRevenue();
    }

    private void produce(Producible producible) {
        try {
            if (producible instanceof Item) {
                produceItem(producible);
            } else if (producible instanceof Box) {
                produceBox(producible);
            } else if (producible instanceof Container) {
                produceContainer(producible);
            }
        } catch (SameSerialNumberProductionException e) {
            System.err.println(e.getMessage() + " (EX: " + e.getErrorCode() + " existing serial number)");
        }
    }

    private Box findBox(String serialNumber) {
        Optional<Box> box = boxes.stream().filter(b -> b.getSerialNumber().equals(serialNumber)).findFirst();
        return box.orElse(null);
    }

    private Container findContainer(String serialNumber) {
        Optional<Container> container = this.containers.stream().filter(c -> c.getSerialNumber().equals(serialNumber))
                .findFirst();
        return container.orElse(null);
    }

    private Item findItem(String serialNumber) {
        Optional<Item> item = this.items.stream().filter(i -> i.getSerialNumber().equals(serialNumber))
                .findFirst();
        return item.orElse(null);
    }

    private void loadItem(Item item, Box box) throws RuleException {
        if (isLoadItemValid(item, box)) {
            if (item instanceof UncountableItem) {
                UncountableItem uncountableItem = (UncountableItem) item;
                MassBox massBox = (MassBox) box;
                if (isMaxMassNotExceeded(massBox, uncountableItem) && isMaxVolumeNotExceeded(box, uncountableItem)) {
                    addToBoxAndRemoveFromInventory(massBox, uncountableItem);
                    printLoadItem(uncountableItem, massBox);

                    massBox.setCurrentMass(massBox.getCurrentMass() + uncountableItem.getMass());
                    massBox.setCurrentVolume(massBox.getCurrentVolume() + uncountableItem.getVolume());

                }
            }

            if (item instanceof CountableItem) {
                CountableItem countableItem = (CountableItem) item;
                NumberBox numberBox = (NumberBox) box;
                if (isMaxNumberOfItemsNotExceeded(numberBox) && isMaxVolumeNotExceeded(numberBox, countableItem)) {
                    addToBoxAndRemoveFromInventory(numberBox, countableItem);
                    printLoadItem(countableItem, numberBox);

                    numberBox.setCurrentNumberOfItems(numberBox.getCurrentNumberOfItems() + 1);
                    numberBox.setCurrentVolume(numberBox.getCurrentVolume() + countableItem.getVolume());

                }
            }
        }

    }

    private void loadBox(Box box, Container container) throws RuleException {
        for (Container container1 : this.containers) {
            if (container1.getBoxList().contains(box)) {
                throw new BoxAlreadyInContainerException(box.getSerialNumber());
            }
        }

        if (isContainerVolumeNotExceeded(container, box)) {
            List<Box> tempBoxList = container.getBoxList();
            tempBoxList.add(box);
            container.setBoxList(tempBoxList);
            container.setCurrentVolume(container.getCurrentVolume() + box.getMaxVolume());

            boxes.remove(box);
            printLoadBox(box, container);
        }

    }

    private void printLoadItem(Item item, Box box) {
        System.out.println("Item " + item.getSerialNumber() + " has been placed to the box " + box.getSerialNumber());
    }

    private void printLoadBox(Box box, Container container) {
        System.out.println(
                "Box " + box.getSerialNumber() + " has been placed to the container " + container.getSerialNumber());
    }

    private boolean hasItem(Box box, Item item) {
        return (box.getItemList().contains(item));
    }

    private double calculateUnearnedRevenue() {
        double unearnedRevenue = 0;
        for (Container container : this.containers) {
            unearnedRevenue += container.calculateRevenue();
        }

        for (Box box : this.boxes) {
            unearnedRevenue += box.calculateRevenue();
        }

        for (Item item : this.items) {
            unearnedRevenue += item.getPrice();
        }

        return unearnedRevenue;
    }

    private boolean isItemLoaded(String itemSerialNumber) {
        if (isItemExistInBoxList(itemSerialNumber, this.boxes)) {
            return true;
        }

        for (Container container : this.containers) {
            List<Box> boxList = container.getBoxList();
            if (isItemExistInBoxList(itemSerialNumber, boxList)) {
                return true;
            }
        }
        return false;
    }

    private boolean isBoxLoaded(String boxSerialNumber) {
        for (Container container : this.containers) {
            List<Box> boxList = container.getBoxList();
            Optional<Box> box = boxList.stream()
                    .filter(b -> (b.getSerialNumber().equals(boxSerialNumber))).findFirst();
            if (box.isPresent())
                return true;
        }
        return false;
    }

    private boolean isItemExistInBoxList(String itemSerialNumber, List<Box> boxes) {
        for (Box box : boxes) {
            List<Item> itemList = box.getItemList();
            for (Item item2 : itemList) {
                if (item2.getSerialNumber().equals(itemSerialNumber)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void loadItemIfExists(Item item, String holderSerialNumber, String itemToBeLoadedSerialNumber) {
        try {
            if (item != null) {
                Box box1 = findBox(holderSerialNumber);

                if (box1 != null) {
                    try {
                        loadItem(item, box1);
                    } catch (RuleException e) {
                        System.err.println(e.getMessage());
                    }
                } else {
                    // holderSerialNumber can be a container serial no, find that no in
                    // containers -> if exists 7. madde
                    // 7. item: Check if item is being tried to be loaded to a container
                    Optional<Container> container = this.containers.stream()
                            .filter(c -> c.getSerialNumber().equals(holderSerialNumber)).findFirst();
                    if (container.isPresent()) { // container exists and item is being tried to load to it
                        throw new ItemPlacementOnContainerException(item.getSerialNumber());
                    }

                    // box could be loaded already -> find in containers -> if exists 8. madde
                    // 8. item: Check whether box is already loaded, find in containers.
                    for (Container container1 : this.containers) {
                        List<Box> boxList = container1.getBoxList();
                        Optional<Box> box3 = boxList.stream()
                                .filter(b -> b.getSerialNumber().equals(holderSerialNumber))
                                .findFirst();
                        if (box3.isPresent()) {
                            Box box = box3.get();
                            throw new ItemPlacementOnLoadedBoxException(item.getSerialNumber(), box.getSerialNumber());
                        }
                    }
                    // (items cannot be placed to a box that is in container)
                    // 10. item: Check if box is already shipped, check serial number archive for that
                    if (producibleArchiveMap.containsKey(holderSerialNumber)) {
                        throw new BoxOrContainerShippedException(
                                "Box with serial number " + holderSerialNumber + " has already been shipped!");
                    }

                    throw new Exception("There is no box with serial number " + holderSerialNumber);
                }
            } else {
                // item cannot be boxed again 9. madde
                // 9. item: Check if a boxed item is being tried to be boxed again
                for (Box box1 : this.boxes) {
                    List<Item> itemList = box1.getItemList();
                    Optional<Item> item1 = itemList.stream()
                            .filter(i -> i.getSerialNumber().equals(itemToBeLoadedSerialNumber)).findFirst();
                    if (item1.isPresent()) {
                        throw new ItemAlreadyBoxedException(itemToBeLoadedSerialNumber);
                    }
                }

                Box box2 = findBox(itemToBeLoadedSerialNumber);

                if (box2 != null) {
                    Container container = findContainer(holderSerialNumber);

                    if (container != null) {
                        try {
                            loadBox(box2, container);
                        } catch (Exception e) {
                            System.err.println(e.getMessage());
                        }
                    } else {
                        // container serial number exists in the map -> shipped container 10 item
                        if (this.producibleArchiveMap.containsKey(holderSerialNumber)) {
                            throw new BoxOrContainerShippedException(
                                    "Container with serial number " + holderSerialNumber + " has already been shipped");
                        }
                        throw new Exception(
                                "There is no container with serial number " + holderSerialNumber);
                    }
                } else {
                    if (isItemLoaded(itemToBeLoadedSerialNumber))
                        throw new ItemAlreadyBoxedException(itemToBeLoadedSerialNumber);
                    if (isBoxLoaded(itemToBeLoadedSerialNumber))
                        throw new BoxAlreadyInContainerException(itemToBeLoadedSerialNumber);
                    throw new Exception(
                            "There is no item with serial number " + itemToBeLoadedSerialNumber);
                }
            }
        } catch (BoxOrContainerShippedException e) {
            System.err.println(e.getMessage() + "(EX: " + e.getErrorCode());
        } catch (RuleException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private boolean isMaxNumberOfItemsNotExceeded(NumberBox numberBox) throws NumberBoxCapacityException {
        if (numberBox.getMaxNumberOfItems() >= numberBox.getCurrentNumberOfItems() + 1)
            return true;
        else
            throw new NumberBoxCapacityException(numberBox.getSerialNumber());
    }

    private boolean isMaxMassNotExceeded(MassBox massBox, UncountableItem uncountableItem)
            throws MassBoxCapacityException {
        if (massBox.getMaxMass() >= massBox.getCurrentMass() + uncountableItem.getMass())
            return true;
        else
            throw new MassBoxCapacityException(massBox.getSerialNumber());
    }

    private boolean isMaxVolumeNotExceeded(Box box, Item item) throws BoxVolumeExceededException {
        if (box.getMaxVolume() >= box.getCurrentVolume() + item.getVolume())
            return true;
        else
            throw new BoxVolumeExceededException(box.getSerialNumber());
    }

    private boolean isLoadItemValid(Item item, Box box) throws RuleException {
        if ((item instanceof CountableItem) && (box instanceof MassBox)) {
            throw new IncompatibleTypeForMassBoxException(item.getSerialNumber(), box.getSerialNumber());
        }

        if ((item instanceof UncountableItem) && (box instanceof NumberBox)) {
            throw new IncompatibleTypeForNumberBoxException(item.getSerialNumber(), box.getSerialNumber());
        }

        for (Box box1 : this.boxes) {
            if (hasItem(box1, item)) {
                throw new ItemAlreadyBoxedException(item.getSerialNumber());
            }
        }

        for (Container container : this.containers) {
            for (Box box2 : container.getBoxList()) {
                if (hasItem(box2, item)) {
                    throw new ItemAlreadyBoxedException(item.getSerialNumber());
                }
            }
        }

        return true;
    }

    private void addToBoxAndRemoveFromInventory(Box box, Item item) {
        List<Item> tempItemList = box.getItemList();
        tempItemList.add(item);
        box.setItemList(tempItemList);
        items.remove(item);
    }

    private boolean isContainerVolumeNotExceeded(Container container, Box box) throws ContainerCapacityException {
        if (container.getMaxVolume() >= container.getCurrentVolume() + box.getMaxVolume())
            return true;
        else
            throw new ContainerCapacityException(container.getSerialNumber());
    }

    private void printRevenue() {
        String indent = "\t\t\t";
        System.out.println(indent + "Revenue: " + roundRevenue(this.revenue) + "TL");
    }

    private String roundRevenue(double revenue) {
        DecimalFormat f = new DecimalFormat("##.00");
        return f.format(revenue);
    }
}