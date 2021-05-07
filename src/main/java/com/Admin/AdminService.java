package com.Admin;

import com.AppUser.AppUser;
import com.AppUser.AppUserDao;
import com.ConsultingRoom.ConsultingRoom;
import com.Doctor.Doctor;
import com.Doctor.DoctorModel;
import com.ObjectResponse.ObjectResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static com.GlobalVariables.*;


@Service
public class AdminService {

    private AppUserDao appUserDao;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    public AdminDao adminDao;

    @Autowired
    public void AdminService(AppUserDao appUserDao, BCryptPasswordEncoder bCryptPasswordEncoder, AdminDao adminDao){
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.adminDao = adminDao;
        this.appUserDao = appUserDao;
    }

    public Boolean save(AdminModel adminModel) {
        AppUser appUser = new AppUser();
        if(appUserDao.findByEmail(adminModel.getEmail()) == null){
            appUser.setEmail(adminModel.getEmail());
            appUser.setPassword(bCryptPasswordEncoder.encode(adminModel.getPassword()));
            appUser.setFirst_name(adminModel.getFirst_name());
            appUser.setLast_name(adminModel.getLast_name());
            appUser.setStatus(APP_USER_ACTIVE);
            appUser.setRole(ADMIN);
            appUserDao.save(appUser);
            Admin admin = new Admin();
            admin.setAppUser(appUser);
            adminDao.save(admin);
            return true;
        } else {
            return false;
        }
    }
}
