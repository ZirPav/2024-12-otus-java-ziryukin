package homework;

import java.util.Deque;
import java.util.LinkedList;

@SuppressWarnings("java:S1319")
public class CustomerReverseOrder {

    private final Deque<Customer> customers = new LinkedList<>();

    public void add(Customer customer) {
        customers.add(customer);
    }

    public Customer take() {
        final Customer last = customers.pollLast();
        return new Customer(last.getId(), last.getName(), last.getScores());
    }
}
