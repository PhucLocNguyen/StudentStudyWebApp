/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.File;
import javax.servlet.ServletContext;

/**
 *
 * @author ACER
 */
public class Constants {

    public static String GOOGLE_CLIENT_ID = "984983964678-k4gqt074vh0j025du4752bdurjngalqk.apps.googleusercontent.com";

    public static String GOOGLE_CLIENT_SECRET = "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";

    public static String GOOGLE_REDIRECT_URI = "http://localhost:8080/LoginGoogle/LoginGoogleHandler";

    public static String GOOGLE_LINK_GET_TOKEN = "https://accounts.google.com/o/oauth2/token";

    public static String GOOGLE_LINK_GET_USER_INFO = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=";

    public static String GOOGLE_GRANT_TYPE = "authorization_code";

    public static String IMAGE_FOLDER = "D:\\Download\\GithubInstaller\\TestingFolder\\web\\Assets\\img";

    public static String IMAGE_PERMALINK = "http://localhost:8080/LoginGoogle/files/";
}
