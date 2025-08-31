package dao;

import model.Subscription;
import java.util.List;

public interface SubscriptionDAO {

    void addSubscription(Subscription subscription);

    Subscription getSubscriptionById(int subscriptionId);

    List<Subscription> getSubscriptionsByCustomer(int customerId);

    List<Subscription> getSubscriptionsByFamily(String familyId);

    void updateSubscription(Subscription subscription);

    void deleteSubscription(int subscriptionId);

    List<Subscription> getAllSubscriptions();
}
