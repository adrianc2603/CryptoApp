package code.model.api;

public interface OutputAPI {

    /**
     * Send data on a given cryptocurrency
     * @param reportData The information on a cryptocurrency to be sent as a report. May not be null or empty
     * @return A message indicating the report has been sent to the given phone number
     */
    String sendReport(String reportData);
}
