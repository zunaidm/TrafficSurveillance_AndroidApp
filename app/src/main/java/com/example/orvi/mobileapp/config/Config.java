package com.example.orvi.mobileapp.config;


public class Config {

    private static String ip = "192.168.0.101";
    //private static String ip = "127.0.0.1";

    private static String register = "http://"+ip+"/css/register.php";
    private static String login = "http://"+ip+"/css/login.php";
    private static String utils = "http://"+ip+"/css/jsons.php";


    public static String getRegister() {
        return register;
    }

    public static String getLogin() {
        return login;
    }

    public static String getUtils() {
        return utils;
    }
}
