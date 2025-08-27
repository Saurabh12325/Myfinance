package com.myfinance.Myfinance.Service;

import com.myfinance.Myfinance.Entity.ProfileEntity;
import com.myfinance.Myfinance.Repository.ProfileRepository;
import com.myfinance.Myfinance.dto.ExpenseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {
    private final ProfileRepository profileRepository ;
    private final EmailService emailService ;
    private final ExpenseService expenseService ;

    @Value("${money.manage.frontend.url}")
    private String frontendUrl ;


//@Scheduled(cron = "0 0 22 * * *",zone = "IST")
    public void sendDailyIncomeExpenseRemainder(){
      log.info("Job started : sendDailyIncomeExpenseRemainder()");
    List<ProfileEntity> profiles = profileRepository.findAll();
       for(ProfileEntity profile:profiles){
           String body = "Hi " + profile.getFullName() + ",<br><br>"
                   + "This is a friendly remainder to add your income and expenses for today in MyFinance.<br><br>"
                   + "<a href = " + frontendUrl + "style = \" display:inline-block; padding:10px 20px; background-color: #4CAF50; Color:#fff; text-decoration: none; border-radius:5px;font-weight:bold\">Go to MyFinance Website</a>"
                   + "<br><br> Best regards, <br> Money Finance Team";
           emailService.sendEmail(profile.getEmail(), "Daily remainder :  Income and Expense Reminder", body);
           log.info("Job ended : sendDailyIncomeExpenseRemainder()");
       }

    }
    @Scheduled(cron = "0 * * * * *",zone = "IST")
    public void sendDailyExpenseSummary() {
        log.info("Job started : sendDailyExpenseSummary()");
        List<ProfileEntity> profiles = profileRepository.findAll();
        for (ProfileEntity profile : profiles) {
            List<ExpenseDto> todayExpenses = expenseService.getExpenseForUserDate(profile.getId(), LocalDate.now());
            if (!todayExpenses.isEmpty()) {
                StringBuilder table = new StringBuilder();

                // Table Header
                table.append("<table style=\"border-collapse:collapse;width:100%;font-family:Arial,sans-serif;\">")
                        .append("<thead>")
                        .append("<tr style=\"background-color:#4CAF50;color:#fff;text-align:left;\">")
                        .append("<th style=\"border:1px solid #ddd;padding:8px;\">Date</th>")
                        .append("<th style=\"border:1px solid #ddd;padding:8px;\">Category</th>")
                        .append("<th style=\"border:1px solid #ddd;padding:8px;\">Description</th>")
                        .append("<th style=\"border:1px solid #ddd;padding:8px;\">Amount</th>")
                        .append("</tr>")
                        .append("</thead>")
                        .append("<tbody>");

                // Table Rows
                for (ExpenseDto expense : todayExpenses) {
                    table.append("<tr>")
                            .append("<td style=\"border:1px solid #ddd;padding:8px;\">").append(expense.getDate()).append("</td>")
                            .append("<td style=\"border:1px solid #ddd;padding:8px;\">").append(expense.getCategoryId() != null ? expense.getCategoryName() : "").append("</td>")
                            .append("<td style=\"border:1px solid #ddd;padding:8px;\">").append(expense.getName()).append("</td>")
                            .append("<td style=\"border:1px solid #ddd;padding:8px;\">₹").append(expense.getAmount()).append("</td>")
                            .append("</tr>");
                }

                // Close table
                table.append("</tbody></table>");

                // Build email body
                String body = "Hi " + profile.getFullName() + ",<br><br>"
                        + "Here is your <b>daily expense summary</b> for today:<br><br>"
                        + table
                        + "<br><br>Best regards,<br>My Finance Team";

                emailService.sendEmail(profile.getEmail(), "Daily Expense Summary", body);
                log.info("Job ended : sendDailyExpenseSummary()");
            }
        }
    }


}


