public class Bus extends Car {
    public Bus(int[] numSeatsPerRow) {
        super(2, (numSeatsPerRow.length * 2) - 1, numSeatsPerRow);
    }

    public Bus(Person driver, int[] numSeatsPerRow) {
        super(2, (numSeatsPerRow.length * 2) - 1, driver, numSeatsPerRow);
    }

    public boolean canOpenDoor(Person p) {
        if (p == null) {
            return false;
        }

        if (this.isPersonDriver(p)) {
            return true;
        }

        if (p.getAge() > 5 && p.getHeight() > 40) {
            int lastPopulatedRow = -1;
            for (int i = 0; i < this.numberOfRows; i++) {
                if (this.getNumberOfPeopleInRow(i) > 0) {
                    lastPopulatedRow = i;
                }
            }

            int[] location = this.getLocationOfPersonInVehicle(p);
            if (location[0] == lastPopulatedRow && location[1] == this.getNumberOfPeopleInRow(location[0]) - 1) {
                return true;
            }
        }

        return false;
    }

    public boolean canOpenWindow(Person p) {
        if (p == null) {
            return false;
        }

        if (p.getAge() <= 5) {
            return false;
        }

        int[] location = this.getLocationOfPersonInVehicle(p);
        if (location[1] == 0 && p.getAge() > 2) {
            if (this.getNumWindows() < 2 * this.numberOfRows) {
                if ((this.getNumWindows() + 1) / 2 >= location[0] + 1) {
                    return true;
                }

                return false;
            }

            return true;
        }

        if (location[1] == this.numSeatsPerRow[location[0]] - 1 && p.getAge() > 2) {
            if (this.getNumWindows() < 2 * this.numberOfRows) {
                if ((this.getNumWindows() + 1) / 2 >= location[0] + 1) {
                    return true;
                }

                return false;
            }

            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return String.format("Bus is an extension of %s", super.toString());
    }

    @Override
    public boolean loadPassenger(Person p) {
        if (p == null) {
            return false;
        }

        for (int row = 0; row < this.numberOfRows; row++) {
            for (int col = 0; col < this.numSeatsPerRow[row]; col++) {
                if (this.personsOnBoard[row][col] == null) {
                    if (row == 0 && (p.getAge() < 5 || p.getHeight() < 36)) {
                        continue;
                    }

                    this.personsOnBoard[row][col] = p.clone();
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public int loadPassengers(Person[] peeps) {
        if (peeps == null) {
            return 0;
        }

        int successfulLoads = 0;

        for (int i = 0; i < peeps.length; i++) {
            if (this.loadPassenger(peeps[i])) {
                successfulLoads++;
            }
        }

        return successfulLoads;
    }

    @Override
    public String departure() {
        return String.format("%sThe Bus\n", super.departure());
    }

    @Override
    public String arrival() {
        return String.format("%sOf The Bus\n", super.arrival());
    }

    @Override
    public String doNotDisturbTheDriver() {
        return String.format("%sOn The Bus\n", super.doNotDisturbTheDriver());
    }
}
