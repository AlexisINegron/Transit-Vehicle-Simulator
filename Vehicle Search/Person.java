public class Person {
    private String name;

    private boolean hasDriverLicense;

    // years
    private int age;

    // inches
    private int height;

    public Person(String name, boolean hasDriverLicense, int age, int height) {
        this.name = name;
        this.hasDriverLicense = hasDriverLicense;
        this.age = age;
        this.height = height;
    }

    public String getName() {
        return this.name;
    }

    public boolean hasDriverLicense() {
        return this.hasDriverLicense;
    }

    public int getAge() {
        return this.age;
    }

    public int getHeight() {
        return this.height;
    }

    @Override
    public Person clone() {
        return new Person(this.name, this.hasDriverLicense, this.age, this.height);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        Person other = (Person) o;

        if (this.name.equals(other.getName()) && this.hasDriverLicense == other.hasDriverLicense()
                && this.age == other.getAge() && this.height == other.getHeight()) {
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return String.format("Person [name= %10s | age= %02d | height= %02d | %s]", this.name, this.age, this.height,
                (this.hasDriverLicense ? "has license" : "no license"));
    }
}
