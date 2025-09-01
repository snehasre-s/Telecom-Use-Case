package repo;


import model.Usage;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UsageSeeder {
    public static List<Usage> seedUsage() {
        List<Usage> usageRecords = new ArrayList<>();

        usageRecords.add(new Usage(1, 1, 1.5, 60, 20, false, false, LocalDateTime.now().minusDays(2)));
        usageRecords.add(new Usage(1, 2, 3.0, 120, 50, true, false, LocalDateTime.now().minusDays(1)));
        usageRecords.add(new Usage(2, 1, 0.5, 30, 5, false, true, LocalDateTime.now().minusHours(5)));
        usageRecords.add(new Usage(2, 2, 8.0, 200, 80, false, false, LocalDateTime.now().minusDays(3)));
        usageRecords.add(new Usage(3, 1 , 2.5, 90, 15, false, false, LocalDateTime.now()));

        return usageRecords;
    }
}

