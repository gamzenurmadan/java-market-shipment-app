package box;

public class MassBox extends Box {
    private double maxMass;
    private double currentMass;
    private static final Integer UNIT_COST = 3;

    public MassBox() {
        super();
        this.maxMass = -1;
        this.currentMass = -1;
    }

    public MassBox(String boxCode, int maxVolume, String serialNumber, double mass, int currentMass) {
        super(boxCode, maxVolume, serialNumber, UNIT_COST * maxVolume, 0);
        this.maxMass = mass;
        this.currentMass = currentMass;
    }

    public double getMaxMass() {
        return this.maxMass;
    }

    public void setMaxMass(double mass) {
        this.maxMass = mass;
    }

    public double getCurrentMass() {
        return this.currentMass;
    }

    public void setCurrentMass(double currentMass) {
        this.currentMass = currentMass;
    }

    public boolean equals(Object o) {
        if (o == null)
            return false;
        if ((getClass() != o.getClass())) {
            return false;
        }
        MassBox massBox = (MassBox) o;
        return (super.equals(massBox) && this.maxMass == massBox.getMaxMass());
    }

    public void printProductionStatus() {
        System.out.print(this.getMaxVolume() + " liters of mass box has been produced with capacity of " + this.maxMass
                + " kg with the serial number " + this.getSerialNumber());
    }

}