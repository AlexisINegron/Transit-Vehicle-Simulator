public abstract class Vehicle {
    protected Person[][] personsOnBoard;

    protected int numberOfRows;

    protected int maxSeatsPerRow;

    protected int[] numSeatsPerRow;

    public Vehicle(int numRows, int numSeatsPerRow) {
        this.numberOfRows = numRows;
        this.maxSeatsPerRow = numSeatsPerRow;
        this.personsOnBoard = new Person[numberOfRows][numSeatsPerRow];
        this.numSeatsPerRow = new int[numRows];

        for (int i = 0; i < numRows; i++) {
            this.numSeatsPerRow[i] = numSeatsPerRow;
        }
    }

    public Vehicle(int[] numSeatsPerRow) {
        this.numberOfRows = numSeatsPerRow.length;
        this.numSeatsPerRow = new int[numSeatsPerRow.length];

        int maxSeatsRow = 0;
        for (int i = 0; i < numSeatsPerRow.length; i++) {
            this.numSeatsPerRow[i] = numSeatsPerRow[i];
            if (numSeatsPerRow[i] > maxSeatsRow) {
                maxSeatsRow = numSeatsPerRow[i];
            }
        }
        this.maxSeatsPerRow = maxSeatsRow;

        this.personsOnBoard = new Person[this.numberOfRows][];
        for (int i = 0; i < this.numberOfRows; i++) {
            this.personsOnBoard[i] = new Person[this.numSeatsPerRow[i]];
        }
    }

    public Vehicle(Person driver, int[] numSeatsPerRow) {
        this.numberOfRows = numSeatsPerRow.length;
        this.numSeatsPerRow = new int[numSeatsPerRow.length];

        int maxSeatsRow = 0;
        for (int i = 0; i < numSeatsPerRow.length; i++) {
            this.numSeatsPerRow[i] = numSeatsPerRow[i];
            if (numSeatsPerRow[i] > maxSeatsRow) {
                maxSeatsRow = numSeatsPerRow[i];
            }
        }

        this.maxSeatsPerRow = maxSeatsRow;

        this.personsOnBoard = new Person[this.numberOfRows][];
        for (int i = 0; i < this.numberOfRows; i++) {
            this.personsOnBoard[i] = new Person[this.numSeatsPerRow[i]];
        }

        if (this instanceof Bicycle) {
            this.personsOnBoard[0][0] = driver.clone();
        } else {
            if (driver.hasDriverLicense()) {
                this.personsOnBoard[0][0] = driver.clone();
            }
        }
    }

    public abstract boolean loadPassenger(Person p);

    public abstract int loadPassengers(Person[] peeps);

    public void setDriver(Person p) throws InvalidDriverException {
        if (!p.hasDriverLicense()) {
            throw new InvalidDriverException("Cannot set driver. Person does not have license.");
        }

        this.personsOnBoard[0][0] = p.clone();
    }

    public Person getDriver() {
        return this.personsOnBoard[0][0];
    }

    public boolean hasDriver() {
        if (this.personsOnBoard[0][0] == null) {
            return false;
        }

        return true;
    }

    public int getNumberOfAvailableSeats() {
        int availableSeats = 0;

        for (int row = 0; row < this.numberOfRows; row++) {
            for (int col = 0; col < this.numSeatsPerRow[row]; col++) {
                if (this.personsOnBoard[row][col] == null) {
                    availableSeats++;
                }
            }
        }

        return availableSeats;
    }

    public int getNumberOfAvailableSeatsInRow(int row) {
        int availableSeats = 0;

        if (row < 0 || row >= this.personsOnBoard.length) {
            return -1;
        }

        for (int col = 0; col < this.personsOnBoard[row].length; col++) {
            if (this.personsOnBoard[row][col] == null) {
                availableSeats++;
            }
        }

        return availableSeats;
    }

    public int getNumberOfPeopleOnBoard() {
        int peopleOnBoard = 0;

        for (int row = 0; row < this.numberOfRows; row++) {
            for (int col = 0; col < this.numSeatsPerRow[row]; col++) {
                if (this.personsOnBoard[row][col] != null) {
                    peopleOnBoard++;
                }
            }
        }

        return peopleOnBoard;
    }

    public int getNumberOfPeopleInRow(int row) {
        if (this instanceof Bicycle) {
            if (row == 0 && this.personsOnBoard[0][0] != null) {
                return 1;
            }

            return 0;
        }

        if (row == -1) {
            return 0;
        }

        int peopleInRow = 0;
        for (int col = 0; col < this.numSeatsPerRow[row]; col++) {
            if (this.personsOnBoard[row][col] != null) {
                peopleInRow++;
            }
        }

        return peopleInRow;
    }

    public Person getPersonInSeat(int row, int col) {
        if (row < 0 || col < 0) {
            return null;
        }

        if (row >= this.personsOnBoard.length || col >= this.personsOnBoard[row].length) {
            return null;
        }

        return this.personsOnBoard[row][col];
    }

    public int[] getLocationOfPersonInVehicle(Person p) {
        for (int row = 0; row < this.personsOnBoard.length; row++) {
            for (int col = 0; col < this.personsOnBoard[row].length; col++) {
                if (p.equals(this.personsOnBoard[row][col])) {
                    return new int[] { row, col };
                }
            }
        }

        return new int[] { -1, -1 };
    }

    public Person[] getPeopleInRow(int row) {
        if (this instanceof Bicycle) {
            if (row == 0 && this.personsOnBoard[0][0] != null) {
                return new Person[] { this.personsOnBoard[0][0] };
            }

            return null;
        }

        if (row < 0 || row > this.personsOnBoard.length) {
            return null;
        }

        int numPeopleInRow = this.getNumberOfPeopleInRow(row);
        if (numPeopleInRow == 0) {
            return null;
        }

        Person[] peopleInRow = new Person[numPeopleInRow];
        int amt = 0;

        for (int col = 0; col < this.personsOnBoard[row].length; col++) {
            if (this.personsOnBoard[row][col] != null) {
                peopleInRow[amt] = this.personsOnBoard[row][col].clone();
                amt++;
            }
        }

        return peopleInRow;
    }

    public Person[][] getPeopleOnBoard() {
        Person[][] peopleOnBoard = new Person[this.personsOnBoard.length][];

        for (int row = 0; row < this.personsOnBoard.length; row++) {
            peopleOnBoard[row] = new Person[this.personsOnBoard[row].length];

            for (int col = 0; col < this.personsOnBoard[row].length; col++) {
                if (this.personsOnBoard[row][col] == null) {
                    this.personsOnBoard[row][col] = null;
                } else {
                    peopleOnBoard[row][col] = this.personsOnBoard[row][col].clone();
                }
            }
        }

        return peopleOnBoard;
    }

    public boolean isPersonInVehicle(Person p) {
        for (int row = 0; row < this.personsOnBoard.length; row++) {
            for (int col = 0; col < this.personsOnBoard[row].length; col++) {
                if (p.equals(this.personsOnBoard[row][col])) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean isPersonDriver(Person p) {
        if (this.personsOnBoard[0][0] == null || p == null) {
            return false;
        }

        return p.equals(this.personsOnBoard[0][0]);
    }
}
