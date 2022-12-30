package app;

import java.util.List;

import company.Company;
import utils.FileIO;
public class MarketShipmentApp {
    public static void main(String[] args) {
        List<List<String>> commandList = FileIO.readCommandsFromCSVFile("src/ExampleCommands.csv");
        Company company = new Company();
        company.produceAndShip(commandList);
    }
}