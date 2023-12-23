import java.util.*;
import java.io.*;
class RegistrationManager {
    private Map<String, String> verificationCodes;

    public RegistrationManager() {
        this.verificationCodes = new HashMap<>();
    }

    public String generateVerificationCode() {
        Random random = new Random();
        return String.format("%04d", random.nextInt(10000));
    }

    public void addVerificationCode(String username, String code) {
        verificationCodes.put(username, code);
    }

    public boolean verifyCode(String username, String code) {
        String storedCode = verificationCodes.get(username);
        return storedCode != null && storedCode.equals(code);
    }
}