package com.job.talenMatch.service;

import com.job.talenMatch.model.Job;
import com.job.talenMatch.model.User;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SmsService {

    public static final String ACCOUNT_SID = "AC804bed9d2fb5f2aa1535fa7818ce42d3";
    public static final String AUTH_TOKEN = "fb5c7b5b3154384e55da8da4928ab1a0";

    // REPLACE THIS with your actual Twilio purchased number (e.g., +18XXXXXXXXX)
    public static final String TWILIO_PHONE_NUMBER = "+12183193355";

    public void sendSms(List<User> usersList, Job job){
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        for(User user : usersList){
            String toPhone = formatPhoneNumber(user.getPhoneNumber());
            String fromPhone = formatPhoneNumber(job.getRecruiter().getPhoneNumber());

            try {
                // TRIAL ACCOUNT LIMITATION:
                // 1. 'From' must be your Twilio number, NOT the recruiter's number.
                // 2. 'To' must be a verified number in your Twilio console.
                Message message = Message.creator(
                        new PhoneNumber(toPhone), 
                        new PhoneNumber(TWILIO_PHONE_NUMBER),
                        "Hi " + user.getUserName() + ", we found a new job for you: " +
                                job.getJobTitle() + " at " + job.getCompanyName() + ". Check it out!"
                ).create();

                System.out.println("SMS sent to " + toPhone + ": " + message.getSid());
            } catch (Exception e) {
                System.err.println("Failed to send SMS to " + toPhone + ": " + e.getMessage());
            }
        }
    }

    /**
     * Ensures phone number is in E.164 format (+91XXXXXXXXXX)
     */
    private String formatPhoneNumber(String phone) {
        if (phone == null || phone.trim().isEmpty()) return "";
        String cleanPhone = phone.replaceAll("[^0-9]", "");
        if (cleanPhone.length() == 10) return "+91" + cleanPhone;
        if (cleanPhone.length() == 12 && cleanPhone.startsWith("91")) return "+" + cleanPhone;
        return phone.startsWith("+") ? phone : "+" + phone;
    }
}
