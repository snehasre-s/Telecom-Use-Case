package dao;

import exceptions.SubscriptionNotFoundException;
import model.Subscription;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SubscriptionDAOImpl implements SubscriptionDAO {

    private final List<Subscription> subscriptions = new ArrayList<>();
    private int lastId = 0;

    @Override
    public void addSubscription(Subscription subscription) {
        subscription.setSubscriptionId(++lastId);
        subscriptions.add(subscription);
    }

    @Override
    public Subscription getSubscriptionById(int subscriptionId) {
        return subscriptions.stream()
                .filter(s -> s.getSubscriptionId() == subscriptionId)
                .findFirst()
                .orElseThrow(() -> new SubscriptionNotFoundException("Subscription not found with id: " + subscriptionId));
    }

    @Override
    public List<Subscription> getSubscriptionsByCustomer(int customerId) {
        List<Subscription> slist = subscriptions.stream()
                .filter(s -> s.getCustomerId() == customerId)
                .collect(Collectors.toList());
        if (slist.isEmpty()) {
            throw new SubscriptionNotFoundException("No subscriptions found for customer: " + customerId);
        }
        return slist;
    }

    @Override
    public List<Subscription> getSubscriptionsByFamily(String familyId) {
        List<Subscription> list = subscriptions.stream()
                .filter(s -> familyId != null && familyId.equals(s.getFamilyId()))
                .collect(Collectors.toList());
        if (list.isEmpty()) {
            throw new SubscriptionNotFoundException("No subscriptions found for family: " + familyId);
        }
        return list;
    }

    @Override
    public void updateSubscription(Subscription subscription) {
        for (int i = 0; i < subscriptions.size(); i++) {
            if (subscriptions.get(i).getSubscriptionId() == subscription.getSubscriptionId()) {
                subscriptions.set(i, subscription);
                return;
            }
        }
        throw new SubscriptionNotFoundException("Cannot update. Subscription not found with id: " + subscription.getSubscriptionId());
    }

    @Override
    public void deleteSubscription(int subscriptionId) {
        boolean removed = subscriptions.removeIf(s -> s.getSubscriptionId() == subscriptionId);
        if (!removed) {
            throw new SubscriptionNotFoundException("Cannot delete. Subscription not found with id: " + subscriptionId);
        }
    }

    @Override
    public List<Subscription> getAllSubscriptions() {
        if (subscriptions.isEmpty()) {
            throw new SubscriptionNotFoundException("No subscriptions available");
        }
        return new ArrayList<>(subscriptions);
    }
}
