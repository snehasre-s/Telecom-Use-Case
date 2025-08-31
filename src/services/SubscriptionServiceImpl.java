package services;

import dao.SubscriptionDAO;
import dao.SubscriptionDAOImpl;
import exceptions.SubscriptionNotFoundException;
import model.Subscription;

import java.util.List;

public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionDAO subscriptionDAO;

    public SubscriptionServiceImpl() {
        this.subscriptionDAO = new SubscriptionDAOImpl();
    }

    @Override
    public void createSubscription(Subscription subscription) {
        subscriptionDAO.addSubscription(subscription);
    }

    @Override
    public Subscription fetchSubscriptionById(int subscriptionId) {
        return subscriptionDAO.getSubscriptionById(subscriptionId);
    }

    @Override
    public List<Subscription> fetchSubscriptionsByCustomer(int customerId) {
        return subscriptionDAO.getSubscriptionsByCustomer(customerId);
    }

    @Override
    public List<Subscription> fetchSubscriptionsByFamily(String familyId) {
        return subscriptionDAO.getSubscriptionsByFamily(familyId);
    }

    @Override
    public void modifySubscription(Subscription subscription) {
        subscriptionDAO.updateSubscription(subscription);
    }

    @Override
    public void removeSubscription(int subscriptionId) {
        subscriptionDAO.deleteSubscription(subscriptionId);
    }

    @Override
    public List<Subscription> fetchAllSubscriptions() {
        return subscriptionDAO.getAllSubscriptions();
    }
}
