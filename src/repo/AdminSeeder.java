package repo;


import model.Admin;
import java.util.ArrayList;
import java.util.List;

public class AdminSeeder {
    public static List<Admin> seedAdmins() {
        List<Admin> admins = new ArrayList<>();
        admins.add(new Admin("7909181017","keertana"));
        admins.add(new Admin("7305632061","sneha"));
        admins.add(new Admin("8210840130","aditya"));
        admins.add(new Admin("7604813964","jey"));
        admins.add(new Admin("7826907186","harinisree"));
        return admins;
    }
}

