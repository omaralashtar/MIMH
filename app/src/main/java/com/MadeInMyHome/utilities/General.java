package com.MadeInMyHome.utilities;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class General {
    public static void addToSharedPreference(Context context, String key, int value) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }
    public static void addToSharedPreference(Context context, String key, boolean value) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static void addToSharedPreference(Context context, String key, String value) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static void removeSharedPreference(Context context, String key) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(key);
        editor.apply();
    }

    public static void updateSharedPreference(Context context, String key, String value) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(key);
        editor.putString(key, value);
        editor.apply();
    }

    public static String getSharedPreference(Context context, String key) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, "");
    }

    public static String getToken(Context context) {
        return getSharedPreference(context, "token");
    }

    public static String emailMessage(String email,String code){
        return "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                "    <head>\n" +
                "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n" +
                "        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" +
                "        <title>Verify your email address</title>\n" +
                "        <style type=\"text/css\" rel=\"stylesheet\" media=\"all\">\n" +
                "            /* Base ------------------------------ */\n" +
                "            *:not(br):not(tr):not(html) {\n" +
                "                font-family: Arial, \"Helvetica Neue\", Helvetica, sans-serif;\n" +
                "                -webkit-box-sizing: border-box;\n" +
                "                box-sizing: border-box;\n" +
                "            }\n" +
                "            body {\n" +
                "                width: 100% !important;\n" +
                "                height: 100%;\n" +
                "                margin: 0;\n" +
                "                line-height: 1.4;\n" +
                "                background-color: #f5f7f9;\n" +
                "                color: #839197;\n" +
                "                -webkit-text-size-adjust: none;\n" +
                "            }\n" +
                "            a {\n" +
                "                color: #414ef9;\n" +
                "            }\n" +
                "            /* Layout ------------------------------ */\n" +
                "            .email-wrapper {\n" +
                "                width: 100%;\n" +
                "                margin: 0;\n" +
                "                padding: 0;\n" +
                "                background-color: #f5f7f9;\n" +
                "            }\n" +
                "            .email-content {\n" +
                "                width: 100%;\n" +
                "                margin: 0;\n" +
                "                padding: 0;\n" +
                "            }\n" +
                "            /* Masthead ----------------------- */\n" +
                "            .email-masthead {\n" +
                "                padding: 25px 0;\n" +
                "                text-align: center;\n" +
                "            }\n" +
                "            .email-masthead_logo {\n" +
                "                max-width: 400px;\n" +
                "                border: 0;\n" +
                "            }\n" +
                "            .email-masthead_name {\n" +
                "                font-size: 16px;\n" +
                "                font-weight: bold;\n" +
                "                color: #839197;\n" +
                "                text-decoration: none;\n" +
                "                text-shadow: 0 1px 0 white;\n" +
                "            }\n" +
                "            /* Body ------------------------------ */\n" +
                "            .email-body {\n" +
                "                width: 100%;\n" +
                "                margin: 0;\n" +
                "                padding: 0;\n" +
                "                border-top: 1px solid #e7eaec;\n" +
                "                border-bottom: 1px solid #e7eaec;\n" +
                "                background-color: #ffffff;\n" +
                "            }\n" +
                "            .email-body_inner {\n" +
                "                width: 570px;\n" +
                "                margin: 0 auto;\n" +
                "                padding: 0;\n" +
                "            }\n" +
                "            .email-footer {\n" +
                "                width: 570px;\n" +
                "                margin: 0 auto;\n" +
                "                padding: 0;\n" +
                "                text-align: center;\n" +
                "            }\n" +
                "            .email-footer p {\n" +
                "                color: #839197;\n" +
                "            }\n" +
                "            .body-action {\n" +
                "                width: 100%;\n" +
                "                margin: 30px auto;\n" +
                "                padding: 0;\n" +
                "                text-align: center;\n" +
                "            }\n" +
                "            .body-sub {\n" +
                "                margin-top: 25px;\n" +
                "                padding-top: 25px;\n" +
                "                border-top: 1px solid #e7eaec;\n" +
                "            }\n" +
                "            .content-cell {\n" +
                "                padding: 35px;\n" +
                "            }\n" +
                "            .align-right {\n" +
                "                text-align: right;\n" +
                "            }\n" +
                "            /* Type ------------------------------ */\n" +
                "            h1 {\n" +
                "                margin-top: 0;\n" +
                "                color: #292e31;\n" +
                "                font-size: 19px;\n" +
                "                font-weight: bold;\n" +
                "                text-align: left;\n" +
                "            }\n" +
                "            h2 {\n" +
                "                margin-top: 0;\n" +
                "                color: #292e31;\n" +
                "                font-size: 16px;\n" +
                "                font-weight: bold;\n" +
                "                text-align: left;\n" +
                "            }\n" +
                "            h3 {\n" +
                "                margin-top: 0;\n" +
                "                color: #292e31;\n" +
                "                font-size: 14px;\n" +
                "                font-weight: bold;\n" +
                "                text-align: left;\n" +
                "            }\n" +
                "            p {\n" +
                "                margin-top: 0;\n" +
                "                color: #839197;\n" +
                "                font-size: 16px;\n" +
                "                line-height: 1.5em;\n" +
                "                text-align: left;\n" +
                "            }\n" +
                "            p.sub {\n" +
                "                font-size: 12px;\n" +
                "            }\n" +
                "            p.center {\n" +
                "                text-align: center;\n" +
                "            }\n" +
                "            /* Buttons ------------------------------ */\n" +
                "            .button {\n" +
                "                display: inline-block;\n" +
                "                width: 200px;\n" +
                "                background-color: #414ef9;\n" +
                "                border-radius: 3px;\n" +
                "                color: #ffffff;\n" +
                "                font-size: 15px;\n" +
                "                line-height: 45px;\n" +
                "                text-align: center;\n" +
                "                text-decoration: none;\n" +
                "                -webkit-text-size-adjust: none;\n" +
                "                mso-hide: all;\n" +
                "            }\n" +
                "            .button--green {\n" +
                "                background-color: #28db67;\n" +
                "            }\n" +
                "            .button--red {\n" +
                "                background-color: #ff3665;\n" +
                "            }\n" +
                "            .button--blue {\n" +
                "                background-color: #414ef9;\n" +
                "            }\n" +
                "            /*Media Queries ------------------------------ */\n" +
                "            @media only screen and (max-width: 600px) {\n" +
                "                .email-body_inner,\n" +
                "                .email-footer {\n" +
                "                    width: 100% !important;\n" +
                "                }\n" +
                "            }\n" +
                "            @media only screen and (max-width: 500px) {\n" +
                "                .button {\n" +
                "                    width: 100% !important;\n" +
                "                }\n" +
                "            }\n" +
                "        </style>\n" +
                "    </head>\n" +
                "    <body>\n" +
                "        <table class=\"email-wrapper\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                "            <tr>\n" +
                "                <td align=\"center\">\n" +
                "                    <table class=\"email-content\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                "                        <!-- Logo -->\n" +
                "                        <tr>\n" +
                "                            <td class=\"email-masthead\">\n" +
                "                                <a class=\"email-masthead_name\">Made In My Home</a>\n" +
                "                            </td>\n" +
                "                        </tr>\n" +
                "                        <!-- Email Body -->\n" +
                "                        <tr>\n" +
                "                            <td class=\"email-body\" width=\"100%\">\n" +
                "                                <table class=\"email-body_inner\" align=\"center\" width=\"570\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                "                                    <!-- Body content -->\n" +
                "                                    <tr>\n" +
                "                                        <td class=\"content-cell\">\n" +
                "                                            <h1>Verify your email address</h1>\n" +
                "                                            <p>Hi "+email+".</p>\n" +
                "                                            <p>Thanks for signing up for Made In My Home! We're excited to have you as an early user.</p>\n" +
                "                                            <p>copy validation key and put it in application to active your account.</p>\n" +
                "                                            <!-- Action -->\n" +
                "                                            <table class=\"body-action\" align=\"center\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                "                                                <tr>\n" +
                "                                                    <td align=\"center\">\n" +
                "                                                        <div>\n" +
                "                                                            <p id=\"validateCode\" class=\"center\">"+code+"</p>\n" +
                "                                                        </div>\n" +
                "                                                    </td>\n" +
                "                                                </tr>\n" +
                "                                            </table>\n" +
                "                                            <p>\n" +
                "                                                Thanks,<br />\n" +
                "                                                The Made In My Home Team\n" +
                "                                            </p>\n" +
                "                                        </td>\n" +
                "                                    </tr>\n" +
                "                                </table>\n" +
                "                            </td>\n" +
                "                        </tr>\n" +
                "                    </table>\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "        </table>\n" +
                "    </body>\n" +
                "</html>";
    }

    public static String emailMessageForgetPassword(String email,String code){
        return "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                "    <head>\n" +
                "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n" +
                "        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" +
                "        <title>Verify your email address</title>\n" +
                "        <style type=\"text/css\" rel=\"stylesheet\" media=\"all\">\n" +
                "            /* Base ------------------------------ */\n" +
                "            *:not(br):not(tr):not(html) {\n" +
                "                font-family: Arial, \"Helvetica Neue\", Helvetica, sans-serif;\n" +
                "                -webkit-box-sizing: border-box;\n" +
                "                box-sizing: border-box;\n" +
                "            }\n" +
                "            body {\n" +
                "                width: 100% !important;\n" +
                "                height: 100%;\n" +
                "                margin: 0;\n" +
                "                line-height: 1.4;\n" +
                "                background-color: #f5f7f9;\n" +
                "                color: #839197;\n" +
                "                -webkit-text-size-adjust: none;\n" +
                "            }\n" +
                "            a {\n" +
                "                color: #414ef9;\n" +
                "            }\n" +
                "            /* Layout ------------------------------ */\n" +
                "            .email-wrapper {\n" +
                "                width: 100%;\n" +
                "                margin: 0;\n" +
                "                padding: 0;\n" +
                "                background-color: #f5f7f9;\n" +
                "            }\n" +
                "            .email-content {\n" +
                "                width: 100%;\n" +
                "                margin: 0;\n" +
                "                padding: 0;\n" +
                "            }\n" +
                "            /* Masthead ----------------------- */\n" +
                "            .email-masthead {\n" +
                "                padding: 25px 0;\n" +
                "                text-align: center;\n" +
                "            }\n" +
                "            .email-masthead_logo {\n" +
                "                max-width: 400px;\n" +
                "                border: 0;\n" +
                "            }\n" +
                "            .email-masthead_name {\n" +
                "                font-size: 16px;\n" +
                "                font-weight: bold;\n" +
                "                color: #839197;\n" +
                "                text-decoration: none;\n" +
                "                text-shadow: 0 1px 0 white;\n" +
                "            }\n" +
                "            /* Body ------------------------------ */\n" +
                "            .email-body {\n" +
                "                width: 100%;\n" +
                "                margin: 0;\n" +
                "                padding: 0;\n" +
                "                border-top: 1px solid #e7eaec;\n" +
                "                border-bottom: 1px solid #e7eaec;\n" +
                "                background-color: #ffffff;\n" +
                "            }\n" +
                "            .email-body_inner {\n" +
                "                width: 570px;\n" +
                "                margin: 0 auto;\n" +
                "                padding: 0;\n" +
                "            }\n" +
                "            .email-footer {\n" +
                "                width: 570px;\n" +
                "                margin: 0 auto;\n" +
                "                padding: 0;\n" +
                "                text-align: center;\n" +
                "            }\n" +
                "            .email-footer p {\n" +
                "                color: #839197;\n" +
                "            }\n" +
                "            .body-action {\n" +
                "                width: 100%;\n" +
                "                margin: 30px auto;\n" +
                "                padding: 0;\n" +
                "                text-align: center;\n" +
                "            }\n" +
                "            .body-sub {\n" +
                "                margin-top: 25px;\n" +
                "                padding-top: 25px;\n" +
                "                border-top: 1px solid #e7eaec;\n" +
                "            }\n" +
                "            .content-cell {\n" +
                "                padding: 35px;\n" +
                "            }\n" +
                "            .align-right {\n" +
                "                text-align: right;\n" +
                "            }\n" +
                "            /* Type ------------------------------ */\n" +
                "            h1 {\n" +
                "                margin-top: 0;\n" +
                "                color: #292e31;\n" +
                "                font-size: 19px;\n" +
                "                font-weight: bold;\n" +
                "                text-align: left;\n" +
                "            }\n" +
                "            h2 {\n" +
                "                margin-top: 0;\n" +
                "                color: #292e31;\n" +
                "                font-size: 16px;\n" +
                "                font-weight: bold;\n" +
                "                text-align: left;\n" +
                "            }\n" +
                "            h3 {\n" +
                "                margin-top: 0;\n" +
                "                color: #292e31;\n" +
                "                font-size: 14px;\n" +
                "                font-weight: bold;\n" +
                "                text-align: left;\n" +
                "            }\n" +
                "            p {\n" +
                "                margin-top: 0;\n" +
                "                color: #839197;\n" +
                "                font-size: 16px;\n" +
                "                line-height: 1.5em;\n" +
                "                text-align: left;\n" +
                "            }\n" +
                "            p.sub {\n" +
                "                font-size: 12px;\n" +
                "            }\n" +
                "            p.center {\n" +
                "                text-align: center;\n" +
                "            }\n" +
                "            /* Buttons ------------------------------ */\n" +
                "            .button {\n" +
                "                display: inline-block;\n" +
                "                width: 200px;\n" +
                "                background-color: #414ef9;\n" +
                "                border-radius: 3px;\n" +
                "                color: #ffffff;\n" +
                "                font-size: 15px;\n" +
                "                line-height: 45px;\n" +
                "                text-align: center;\n" +
                "                text-decoration: none;\n" +
                "                -webkit-text-size-adjust: none;\n" +
                "                mso-hide: all;\n" +
                "            }\n" +
                "            .button--green {\n" +
                "                background-color: #28db67;\n" +
                "            }\n" +
                "            .button--red {\n" +
                "                background-color: #ff3665;\n" +
                "            }\n" +
                "            .button--blue {\n" +
                "                background-color: #414ef9;\n" +
                "            }\n" +
                "            /*Media Queries ------------------------------ */\n" +
                "            @media only screen and (max-width: 600px) {\n" +
                "                .email-body_inner,\n" +
                "                .email-footer {\n" +
                "                    width: 100% !important;\n" +
                "                }\n" +
                "            }\n" +
                "            @media only screen and (max-width: 500px) {\n" +
                "                .button {\n" +
                "                    width: 100% !important;\n" +
                "                }\n" +
                "            }\n" +
                "        </style>\n" +
                "    </head>\n" +
                "    <body>\n" +
                "        <table class=\"email-wrapper\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                "            <tr>\n" +
                "                <td align=\"center\">\n" +
                "                    <table class=\"email-content\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                "                        <!-- Logo -->\n" +
                "                        <tr>\n" +
                "                            <td class=\"email-masthead\">\n" +
                "                                <a class=\"email-masthead_name\">Made In My Home</a>\n" +
                "                            </td>\n" +
                "                        </tr>\n" +
                "                        <!-- Email Body -->\n" +
                "                        <tr>\n" +
                "                            <td class=\"email-body\" width=\"100%\">\n" +
                "                                <table class=\"email-body_inner\" align=\"center\" width=\"570\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                "                                    <!-- Body content -->\n" +
                "                                    <tr>\n" +
                "                                        <td class=\"content-cell\">\n" +
                "                                            <h1>Forget Password</h1>\n" +
                "                                            <p>Hi "+email+".</p>\n" +
                "                                            <p>use the code below to reset your password.</p>\n" +
                "                                            <p>copy validation key and put it in application to reset your password.</p>\n" +
                "                                            <!-- Action -->\n" +
                "                                            <table class=\"body-action\" align=\"center\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                "                                                <tr>\n" +
                "                                                    <td align=\"center\">\n" +
                "                                                        <div>\n" +
                "                                                            <p id=\"validateCode\" class=\"center\">"+code+"</p>\n" +
                "                                                        </div>\n" +
                "                                                    </td>\n" +
                "                                                </tr>\n" +
                "                                            </table>\n" +
                "                                            <p>\n" +
                "                                                Thanks,<br />\n" +
                "                                                The Made In My Home Team\n" +
                "                                            </p>\n" +
                "                                        </td>\n" +
                "                                    </tr>\n" +
                "                                </table>\n" +
                "                            </td>\n" +
                "                        </tr>\n" +
                "                    </table>\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "        </table>\n" +
                "    </body>\n" +
                "</html>";
    }

    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public static boolean isMoreThan8(String pass) {
        String expression = ".{8,20}";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(pass);
        return matcher.matches();
    }

}
