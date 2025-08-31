package service;

import model.Subscription;
import java.util.List;

public interface SubscriptionService {

    void createSubscription(Subscription subscription);

    Subscription fetchSubscriptionById(int subscriptionId);

    List<Subscription> fetchSubscriptionsByCustomer(int customerId);

    List<Subscription> fetchSubscriptionsByFamily(String familyId);

    void modifySubscription(Subscription subscription);

    void removeSubscription(int subscriptionId);

    List<Subscription> fetchAllSubscriptions();
}
