package com.jwt.authentification.controllers;



import com.jwt.authentification.Domaine.User;
import com.jwt.authentification.Exception.ResourceNotFoundException;
import com.jwt.authentification.Password.Utility;
import com.jwt.authentification.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
@Slf4j
public class TestController {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UserService userService;

    @GetMapping("/forgot_password")
    public String showForgotPasswordForm() {
        return "forgot_password_form";
    }

    @PostMapping("/forgot_password")
    public String processForgotPassword(HttpServletRequest request, Model model) {
        String email = request.getParameter("email");
                //"rokiaayoubi@gmail.com";
                //request.getParameter("email");
        log.info("email : {}",email);
        String token = RandomString.make(30);
        log.info("token : {}",token);


        try {

            userService.updateResetPasswordToken(token, email);
            log.info("inside try");
            String resetPasswordLink = Utility.getSiteURL(request) + "/reset_password?token=" + token;
            sendEmail(email, resetPasswordLink);
            log.info("reset password link  {}",resetPasswordLink);
            model.addAttribute("message", "We have sent a reset password link to your email. Please check.");

        } catch (ResourceNotFoundException ex) {
            model.addAttribute("error", ex.getMessage());
        } catch (UnsupportedEncodingException | MessagingException e) {
            model.addAttribute("error", "Error while sending email");
        }

        return "forgot_password_form";
    }

    public void sendEmail(String recipientEmail, String link)
            throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("benwafaa959@gmail.com", "Rest Password");
        helper.setTo(recipientEmail);

        String subject = "Here's the link to reset your password";

        String content =
            "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                    "\n" +
                    "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                    "\n" +
                    "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                    "    <tbody><tr>\n" +
                    "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                    "        \n" +
                    "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                    "          <tbody><tr>\n" +
                    "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                    "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                    "                  <tbody><tr>\n" +
                    "                    <td style=\"padding-left:10px\">\n" +
                    "                  \n" +
                    "                    </td>\n" +
                    "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                    "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n" +
                    "                    </td>\n" +
                    "                  </tr>\n" +
                    "                </tbody></table>\n" +
                    "              </a>\n" +
                    "            </td>\n" +
                    "          </tr>\n" +
                    "        </tbody></table>\n" +
                    "        \n" +
                    "      </td>\n" +
                    "    </tr>\n" +
                    "  </tbody></table>\n" +
                    "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                    "    <tbody><tr>\n" +
                    "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                    "      <td>\n" +
                    "        \n" +
                    "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                    "                  <tbody><tr>\n" +
                    "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                    "                  </tr>\n" +
                    "                </tbody></table>\n" +
                    "        \n" +
                    "      </td>\n" +
                    "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                    "    </tr>\n" +
                    "  </tbody></table>\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                    "    <tbody><tr>\n" +
                    "      <td height=\"30\"><br></td>\n" +
                    "    </tr>\n" +
                    "    <tr>\n" +
                    "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                    "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                    "        \n" +
                    "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi "  + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering. Please click on the below link to activate your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Activate Now</a> </p></blockquote>\n Link will expire in 15 minutes. <p>See you soon</p>" +
                    "        \n" +
                    "      </td>\n" +
                    "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                    "    </tr>\n" +
                    "    <tr>\n" +
                    "      <td height=\"30\"><br></td>\n" +
                    "    </tr>\n" +
                    "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                    "\n" +
                    "</div></div>";
        helper.setSubject(subject);

        helper.setText(content, true);

        mailSender.send(message);
    }


    @GetMapping("/reset_password")
    public String showResetPasswordForm(@Param(value = "token") String token, Model model) {
        User user = userService.getByResetPasswordToken(token);
        model.addAttribute("token", token);

        if (user == null) {
            model.addAttribute("message", "Invalid Token");
            return "message";
        }

        return "reset_password_form";
    }

    @PostMapping("/reset_password")
    public String processResetPassword(HttpServletRequest request, Model model) {
        String token = request.getParameter("token");
        String password = request.getParameter("password");

        User user = userService.getByResetPasswordToken(token);
        model.addAttribute("title", "Reset your password");

        if (user == null) {
            model.addAttribute("message", "Invalid Token");
            return "message";
        } else {
            userService.updatePassword(user, password);

            model.addAttribute("message", "You have successfully changed your password.");
        }

        return "message";
    }




    //----------------------

    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }


    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String userAccess() {
        return "User Content.";
    }

    @GetMapping("/meb")
    @PreAuthorize("hasRole('MEMBER')")
    public String memberAccess() {
        return "Member Board.";
    }

    @GetMapping("/adh")
    @PreAuthorize("hasRole('ADHERENT')")
    public String sdherentAccess() {
        return "Adherent Board.";
    }

    @GetMapping("/par")
    @PreAuthorize("hasRole('PARTENAIRE')")
    public String partenaireAccess() {
        return "Partenaire Board.";
    }



    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }
}
