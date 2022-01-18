public class BellArrayListTester {
    public static void main(String[] args) {
        BellArrayList arr = new BellArrayList();

        // test add methods
        System.out.println("ADD METHODS TEST:");
        arr.add("1");
        System.out.println(arr);

        arr.add("2");
        System.out.println(arr);

        arr.add("3");
        System.out.println(arr);

        arr.add(arr.size(), "4");
        System.out.println(arr);

        arr.add(0, "12");
        System.out.println(arr);

        System.out.println();

        // test size method
        System.out.println("SIZE METHOD TEST:");
        System.out.println("Size: " + arr.size());
        System.out.println();

        // test remove method
        System.out.println("REMOVE METHOD TEST:");
        arr.remove(1);
        System.out.println(arr);
        arr.remove(arr.size() - 1);
        System.out.println(arr);
        System.out.println("Size: " + arr.size());
        System.out.println();

        // test set method
        System.out.println("SET METHOD TEST:");

        arr.set(0, "DASJDHASJKDHJK");
        System.out.println(arr);
        System.out.println();

        // test errors
        System.out.println("OUT OF BOUNDS TESTS:");
        try {
            arr.set(-1, "this should throw an error");
        } catch(Exception e) {
            System.out.println(e);
        }

        try {
            arr.add(arr.size() + 1, "this should throw an error");
        } catch(Exception e) {
            System.out.println(e);
        }

        try {
            arr.remove(arr.size());
        } catch(Exception e) {
            System.out.println(e);
        }
    }
}
