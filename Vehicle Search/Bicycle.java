public class Bicycle extends Vehicle implements Comparable<Bicycle> {
    static double ACCURACY_RANGE = 0.5;

    private double weight;

    public Bicycle() {
        super(1, 1);

        this.weight = 0;
    }

    public Bicycle(Person driver) {
        super(driver, new int[] { 1 });

        this.weight = 0;
    }

    public Bicycle(Person driver, double weight) {
        super(driver, new int[] { 1 });

        if (weight < 0) {
            this.weight = 0;
        } else {
            this.weight = weight;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (o == this) {
            return true;
        }

        if (!(o instanceof Bicycle)) {
            return false;
        }

        Bicycle other = (Bicycle) o;

        if (other.getWeight() == this.getWeight()) {
            return true;
        }

        if (Math.abs(other.getWeight() - this.getWeight()) <= Bicycle.ACCURACY_RANGE) {
            return true;
        }

        return false;
    }

    public double getWeight() {
        return this.weight;
    }

    public void setWeight(double w) {
        if (w < 0) {
            w = 0;
        }

        this.weight = w;
    }

    @Override
    public void setDriver(Person p) throws InvalidDriverException {
        if (p.getAge() < 3) {
            throw new InvalidDriverException("Bicycle driver cannot be younger than 3 years old.");
        }

        this.personsOnBoard[0][0] = p.clone();
    }

    @Override
    public String toString() {
        Person driver = this.getDriver();
        String driverName = "";

        if (driver != null) {
            driverName = driver.getName();
        }

        return "Bicycle [ rider= " + driverName + " | weight= " + this.weight + " ]";
    }

    public int compareTo(Bicycle b) {
        if (Math.abs(b.getWeight() - this.getWeight()) > Bicycle.ACCURACY_RANGE) {
            if (b.getWeight() > this.getWeight()) {
                return -1;
            }

            if (b.getWeight() < this.getWeight()) {
                return 1;
            }
        }

        return 0;
    }

    @Override
    public boolean loadPassenger(Person p) {
        return false;
    }

    @Override
    public int loadPassengers(Person[] peeps) {
        return 0;
    }
}
