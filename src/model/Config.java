package model;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;

public class Config {

    //Create variables for each of the JSON objects.

    //Database settings
    private static String host;
    private static String port;
    private static String username;
    private static String password;
    private static String dbname;

    //Server settings
    private static int serverPort;

    //Security
    private static String hashingSalt;
    private static String encryptionkey;
    private static String JWTSecret;

    // Create init-method to read from the config.json.dist file
    // and parse it to the variables in the class.
    public static void init() throws IOException {

        //Initialize imported Java-class JSONParser as jsonParser object.
        JSONParser jsonParser = new JSONParser();

        try {

            //Initialize imported Java-class FileReader as json object
            //with the specific path to the .json file.
            FileReader json = new FileReader("src/config.json");

            //Initialize Object class as json, parsed by jsonParsed.
            Object obj = jsonParser.parse(json);

            //Instantiate JSONObject class as jsonObject equal to obj object.
            JSONObject jsonObject = (JSONObject) obj;

            //Use set-methods for defining static variables from json-file.
            setHost((String) jsonObject.get("host"));
            setPort((String) jsonObject.get("port"));
            setUsername((String) jsonObject.get("username"));
            setPassword((String) jsonObject.get("password"));
            setDbname((String) jsonObject.get("dbname"));

            setServerPort(Integer.parseInt(jsonObject.get("serverport").toString()));

            setEncryptionkey((String) jsonObject.get("encryptionkey"));
            setHashingSalt((String) jsonObject.get("hashingSalt"));
            setJWTSecret((String) jsonObject.get("jwtsecret"));



        } catch (IOException e) {
            e.printStackTrace();
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
    }

    //Created getters and setters for each of the variables.
    public static String getDbname() {
        return dbname;
    }
    public static String getHost() {
        return host;
    }
    public static String getPassword() {
        return password;
    }
    public static String getPort() {
        return port;
    }
    public static String getUsername() {
        return username;
    }
    public static String getEncryptionkey() {
        return encryptionkey;
    }
    public static String getHashingSalt() {
        return hashingSalt;
    }
    public static String getJWTSecret() {return JWTSecret;}

    public static void setDbname(String dbname) {
        Config.dbname = dbname;
    }

    public static void setHost(String host) {
        Config.host = host;
    }

    public static void setPassword(String password) {
        Config.password = password;
    }

    public static void setPort(String port) {
        Config.port = port;
    }

    public static void setUsername(String username) {
        Config.username = username;
    }

    public static void setEncryptionkey(String encryptionkey) {
        Config.encryptionkey = encryptionkey;
    }

    public static void setHashingSalt(String hashingalt) {
        Config.hashingSalt = hashingSalt;
    }

    public static void setServerPort(int serverPort) {
        Config.serverPort = serverPort;
    }

    public static int getServerPort() {
        return serverPort;
    }

    public static void setJWTSecret(String JWTSecret) {Config.JWTSecret = JWTSecret;}
}
