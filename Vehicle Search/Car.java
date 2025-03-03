public class Car extends Vehicle implements Comparable<Car>, Announcements {
    private int numDoors;

    private int numWindows;

    @Override
    public String departure() {
        return "All Aboard\n";
    }

    @Override
    public String arrival() {
        return "Everyone Out\n";
    }

    @Override
    public String doNotDisturbTheDriver() {
        return "No Backseat Driving\n";
    }

    @Override
    public boolean loadPassenger(Person p) {
        for (int row = 0; row < this.personsOnBoard.length; row++) {
            for (int col = 0; col < this.personsOnBoard[row].length; col++) {
                if (this.personsOnBoard[row][col] == null) {
                    if (row == 0 && (p.getAge() < 5 || p.getHeight() < 36)) {
                        continue;
                    }

                    this.personsOnBoard[row][col] = p;
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public int loadPassengers(Person[] peeps) {
        int successfulLoads = 0;

        for (int i = 0; i < peeps.length; i++) {
            if (this.loadPassenger(peeps[i])) {
                successfulLoads++;
            }
        }

        return successfulLoads;
    }

    public Car(int numDoors, int numWindows) {
        super(2, 2);

        this.numDoors = numDoors;
        this.numWindows = numWindows;
    }

    public Car(int numDoors, int numWindows, int numSeatsPerRow) {
        super(2, numSeatsPerRow);

        this.numDoors = numDoors;
        this.numWindows = numWindows;
    }

    public Car(int numDoors, int numWindows, int[] numSeatsPerRow) {
        super(numSeatsPerRow);

        this.numDoors = numDoors;
        this.numWindows = numWindows;
    }

    public Car(int numDoors, int numWindows, Person driver, int[] numSeatsPerRow) {
        super(driver, numSeatsPerRow);

        this.numDoors = numDoors;
        this.numWindows = numWindows;
    }

    public boolean canOpenDoor(Person p) {
        if (p == null) {
            return false;
        }

        int[] location = this.getLocationOfPersonInVehicle(p);

        if (location[1] == 0 && p.getAge() > 5) {
            if (this.numDoors < 2 * this.numberOfRows) {
                if (this.numDoors / 2 >= location[0] + 1) {
                    return true;
                }

                return false;
            }

            return true;
        }

        if (location[1] == this.numSeatsPerRow[location[0]] - 1 && p.getAge() > 5) {
            if (this.numDoors < 2 * this.numberOfRows) {
                if (this.numDoors / 2 >= location[0] + 1) {
                    return true;
                }

                return false;
            }

            return true;
        }

        return false;
    }

    public boolean canOpenWindow(Person p) {
        if (p == null) {
            return false;
        }

        int[] location = this.getLocationOfPersonInVehicle(p);

        if (location[1] == 0 && p.getAge() > 2) {
            if (this.numWindows < 2 * this.numberOfRows) {
                if (this.numWindows / 2 >= location[0] + 1) {
                    return true;
                }

                return false;
            }

            return true;
        }

        if (location[1] == this.numSeatsPerRow[location[0]] - 1 && p.getAge() > 2) {
            if (this.numWindows < 2 * this.numberOfRows) {
                if (this.numWindows / 2 >= location[0] + 1) {
                    return true;
                }

                return false;
            }

            return true;
        }

        return false;
    }

    public int getNumDoors() {
        return this.numDoors;
    }

    public int getNumWindows() {
        return this.numWindows;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (o == this) {
            return true;
        }

        if (!(o instanceof Car)) {
            return false;
        }

        Car other = (Car) o;

        if (other.getNumDoors() == this.numDoors && other.getNumWindows() == this.numWindows
                && other.numberOfRows == this.numberOfRows && other.maxSeatsPerRow == this.maxSeatsPerRow) {
            if (this.numSeatsPerRow.length != other.numSeatsPerRow.length) {
                return false;
            }

            for (int i = 0; i < this.numSeatsPerRow.length; i++) {
                if (this.numSeatsPerRow[i] != other.numSeatsPerRow[i]) {
                    return false;
                }
            }

            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        String seatsPerRow = "";
        for (int i = 0; i < this.numSeatsPerRow.length; i++) {
            seatsPerRow += this.numSeatsPerRow[i];

            if (i != this.numSeatsPerRow.length - 1) {
                seatsPerRow += ",";
            }
        }

        String peopleOnBoardStr = "";
        Person[][] peopleOnBoard = this.getPeopleOnBoard();
        for (int row = 0; row < peopleOnBoard.length; row++) {
            for (int col = 0; col < peopleOnBoard[row].length; col++) {
                if (peopleOnBoard[row][col] == null) {
                    continue;
                }

                peopleOnBoardStr += peopleOnBoard[row][col].getName();
                peopleOnBoardStr += ",";
            }
        }

        peopleOnBoardStr = peopleOnBoardStr.replaceAll(",$", "");

        return String.format(
                "Car: number of doors = %02d | number of windows = %02d | number of rows = %02d | seats per row = %s | names of people on board = [%s]\n",
                this.numDoors, this.numWindows, this.numberOfRows, "[" + seatsPerRow + "]", peopleOnBoardStr);
    }

    public int compareTo(Car c) {
        int selfTotalSeats = 0;
        int otherTotalSeats = 0;

        for (int i = 0; i < this.numSeatsPerRow.length; i++) {
            selfTotalSeats += this.numSeatsPerRow[i];
        }

        for (int i = 0; i < c.numSeatsPerRow.length; i++) {
            otherTotalSeats += c.numSeatsPerRow[i];
        }

        if (selfTotalSeats < otherTotalSeats) {
            return -1;
        }

        if (selfTotalSeats > otherTotalSeats) {
            return 1;
        }

        return 0;
    }
}
