import java.util.Random;

public class Main {
    public static void main(String[] args) {
        MyHashTable<String, MyCars> table = new MyHashTable<>();
        Random random = new Random();


        for (int i = 0; i < 10000; i++) {
            String clientId = "Client" + random.nextInt(5000);
            String[] carModels = {"Mercedes", "BMW", "Audi", "Toyota", "Honda"};
            String carModel = carModels[random.nextInt(carModels.length)];

            MyCars cars = new MyCars(clientId, carModel);
            table.put(clientId, cars);
        }


        for (int i = 0; i < table.getChainArrayLength(); i++) {
            StringBuilder bucketStr = new StringBuilder();
            if (table.getBucketValues(i).size() > 0) {
                bucketStr.append("Bucket ").append(i).append(": ");
                boolean isFirst = true;
                for (MyCars cars : table.getBucketValues(i)) {
                    if (!isFirst) {
                        bucketStr.append(", ");
                    }
                    bucketStr.append(cars);
                    isFirst = false;
                }
                System.out.println(bucketStr);
            }
        }
        BST<Integer, String> tree = new BST<>();

        tree.put(3, "Three");
        tree.put(1, "One");
        tree.put(5, "Five");
        tree.put(2, "Two");
        tree.delete(2);

        for (var entry : tree) {
            System.out.println("key is " + entry.getKey() + " and value is " + entry.getValue());
        }
    }
}