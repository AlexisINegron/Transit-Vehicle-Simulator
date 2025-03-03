import java.util.*;

public class RecursionQuestion {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        sc.close();
        String[] inputs = input.split(" ");

        int[] inputInts = new int[inputs.length];
        for (int i = 0; i < inputs.length; i++) {
            int inputVal = Integer.parseInt(inputs[i]);
            inputInts[i] = inputVal;
        }

        Car[] cars = { new Car(2, 4, new int[] { 2, 2 }), new Car(2, 4, new int[] { 2, 3 }),
                new Car(2, 4, new int[] { 2, 2, 3 }), new Car(2, 4, new int[] { 2, 3, 3 }),
                new Car(2, 4, new int[] { 2, 4, 3 }), new Car(2, 4, new int[] { 2, 4, 0 }),
                new Car(2, 4, new int[] { 2, 4, 0 }), new Car(2, 4, new int[] { 2, 4, 0 }),
                new Car(2, 4, new int[] { 2, 4, 0 }), new Car(2, 4, new int[] { 3, 4, 5 }) };

        int index = binarySearch(cars, new Car(2, 4, inputInts));

        if (index == -1) {
            System.out.println("Not Found");
        } else {
            System.out.println("FOUND at " + index);
        }

        System.out.println("\n");
    }

    public static int binarySearch(Car[] cars, Car c) {
        System.out.println(String.format("Looking for %s", c.toString()));

        return binarySearchHelper(cars, c, 0, cars.length - 1);
    }

    private static int binarySearchHelper(Car[] cars, Car c, int start, int end) {
        int mid = start + (end - start) / 2;
        System.out.println(String.format("s=%d, e=%d, mid=%d", start, end, mid));

        if (start == end && start == mid) {
            return mid;
        }

        if (cars[mid].compareTo(c) == 0) {
            return mid;
        }

        if (end < start) {
            return -1;
        }

        if (start >= cars.length - 1) {
            return -1;
        }

        if (cars[mid].compareTo(c) == 1) {
            System.out.println("go left");
            return binarySearchHelper(cars, c, start, mid - 1);
        } else {
            System.out.println("go right");
            return binarySearchHelper(cars, c, mid + 1, end);
        }
    }
}
