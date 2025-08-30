package repo;


import model.Admin;
import java.util.ArrayList;
import java.util.List;

public class AdminSeeder {
    public static List<Admin> seedAdmins() {
        List<Admin> admins = new ArrayList<>();
        admins.add(new Admin("9342415006","admin123"));
        return admins;
    }
}

