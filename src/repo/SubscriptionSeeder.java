package repo;

import model.Subscription;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionSeeder {

    private static int lastId = 0;  // keeps track of the last assigned subscriptionId
        public  static List<Subscription> subscriptions = new ArrayList<>();
    public static List<Subscription> seedSubscriptions() {

        subscriptions.add(new Subscription(
                ++lastId,
                1,
                101,
                null,
                LocalDate.of(2023, 1, 10),
                LocalDate.of(2024, 1, 10),
                false
        ));

        subscriptions.add(new Subscription(
                ++lastId,
                2,
                102,
                "FAMILY001",
                LocalDate.of(2023, 3, 1),
                LocalDate.of(2024, 3, 1),
                false
        ));

        subscriptions.add(new Subscription(
                ++lastId,
                3,
                103,
                "FAMILY001",
                LocalDate.of(2023, 5, 15),
                LocalDate.of(2024, 5, 15),
                false
        ));

        return subscriptions;
    }
    public static void addNewSubscription(int custId, int planId, String ffamId, LocalDate start, LocalDate end, boolean mnpStatus){
        subscriptions.add(
                new Subscription(
                ++lastId,
                custId,
                planId,
                ffamId,
                start,
                end,
                mnpStatus
                )
        );
    }
}
