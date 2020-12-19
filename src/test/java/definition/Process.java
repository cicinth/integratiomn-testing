package definition;

import groovy.json.internal.LazyMap;

public class Process {
    private static final String PROCESS_URL = "http://agapito-server.herokuapp.com/processos";
    private static LazyMap fieldsJson = new LazyMap();
    private static String lastProcess;

    public static void addFields(String field, String value) {
        fieldsJson.put(field, value);
    }

    public static void clearFields() {
        fieldsJson.clear();
    }
    public static LazyMap getFields() {
        return fieldsJson;
    }
    public static String getEndpoint() {
        return PROCESS_URL;
    }

    public static String getLastProcess() {
        return lastProcess;
    }
    public static void setLastProcess(String lastProcess) {
        Process.lastProcess = lastProcess;
    }


}
