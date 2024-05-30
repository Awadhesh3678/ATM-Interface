import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AdvancedCurrencyConverter {
    private static final Map<String, Double> RATES = new HashMap<>();

    static {
        RATES.put("USD", 1.0);
        RATES.put("EUR", 0.85);
        RATES.put("GBP", 0.75);
        RATES.put("JPY", 110.0);
        RATES.put("CNY", 6.75);
        RATES.put("INR", 0.98);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the amount you want to convert:");
        double amount = scanner.nextDouble();

        System.out.println("Enter the currency code of the original currency (e.g.,INR, USD, EUR, GBP, JPY, CNY):");
        String originalCurrency = scanner.next();

        System.out.println("Enter the currency code of the target currency (e.g.,INR, USD, EUR, GBP, JPY, CNY):");
        String targetCurrency = scanner.next();

        double originalRate = RATES.get(originalCurrency);
        double targetRate = RATES.get(targetCurrency);

        double convertedAmount = amount * targetRate / originalRate;
        System.out.printf("The converted amount is: %.2f %s%n", convertedAmount, targetCurrency);
    }
}
