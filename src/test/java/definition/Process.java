package definition;

import groovy.json.internal.LazyMap;

public class Process {
    private static final String PROCESS_URL = "http://agapito-server.herokuapp.com/processos/";
    private static final LazyMap fieldsJson = new LazyMap();
    private static String lastProcess;
    private static String id;

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
    public static void setIdProcess(String idProcesso) {
        id = idProcesso;
    }
    public static String getIdProcess() {
        return id;
    }

}
