import java.util.*;
import java.io.*;
class Logger {
    private BufferedWriter writer;

    public Logger(String fileName) throws IOException {
        this.writer = new BufferedWriter(new FileWriter(fileName, true));
    }

    public void logAction(User user, String action) throws IOException {
        String logMessage = user.getUsername() + " - " + new Date() + " - " + action;
        writer.write(logMessage);
        writer.newLine();
        writer.flush();
    }

    public void close() throws IOException {
        writer.close();
    }
}