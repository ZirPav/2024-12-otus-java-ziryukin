package homework;

import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class CustomerService {

    private final NavigableMap<Customer, String> customers = new TreeMap<>();

    public Map.Entry<Customer, String> getSmallest() {
        final Map.Entry<Customer, String> entry = customers.firstEntry();
        return Map.entry(
                new Customer(
                        entry.getKey().getId(),
                        entry.getKey().getName(),
                        entry.getKey().getScores()),
                entry.getValue());
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        final Map.Entry<Customer, String> entry = customers.higherEntry(customer);
        if (entry == null) {
            return null;
        }
        return Map.entry(
                new Customer(
                        entry.getKey().getId(),
                        entry.getKey().getName(),
                        entry.getKey().getScores()),
                entry.getValue());
    }

    public void add(Customer customer, String data) {
        customers.put(customer, data);
    }
}
