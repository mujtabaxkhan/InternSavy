import java.util.Scanner;

public class converterTask2 {
    //Currency Converter:
    public static double ConverterPKR(int choice, double value) {
        if (choice == 1) {//USD
            double answer=value * 0.0036;
            return answer;
        } else if (choice == 2) {//AED
            double answer=value * 0.0132;
            return answer;
        } else if (choice == 3) {//EUR
            double answer=value * 0.0034;
            return answer;
        }
        return value;
    }

    public static double ConverterUSD(int choice, double value) {
        if (choice == 1) {//PKR
            double answer=value * 276.88;
            return answer;
        } else if (choice == 2) {//AED
            double answer=value * 3.67;
            return answer;
        } else if (choice == 3) {//EUR
            double answer=value * 0.95;
            return answer;
        }
        return value;
    }

    public static double ConverterAED(int choice, double value) {
        if (choice == 1) {//PKR
            double answer=value * 76.27;
            return answer;
        } else if (choice == 2) {//USD
            double answer=value * 0.27;
            return answer;
        } else if (choice == 3) {//EUR
            double answer=value * 0.26;
            return answer;
        }
        return value;
    }

    public static double ConverterEUR(int choice, double value) {
        if (choice == 1) {//PKR
            double answer=value * 292.93;
            return answer;
        } else if (choice == 2) {//USD
            double answer=value * 1.06;
            return answer;
        } else if (choice == 3) {//AED
            double answer=value * 3.88;
            return answer;
        }
        return value;
    }

    public static boolean checkerChoice(int number) {
        if (number >= 1 && number <= 4) {
            return true;
        } else {
            return false;
        }
    }

    //Value Checker:
    public static boolean Check(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("\nERROR\nKindly enter in correct Format");
            return false;
        }
    }

    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        System.out.println("WELCOME TO CURRENCY CONVERSION:");
        System.out.println("\nKindly Select The Current Currency from the Following Options\nPress 1 for PKR\nPress 2 for USD\nPress 3 for AED\nPress 4 for EUR\nEnter Choice:");
        int choice=input.nextInt();
        while (!(checkerChoice(choice))) {
            System.out.println("\n\nERROR KINDLY ENTER VALID INPUT");
            System.out.println("\nKindly Select The Current Currency from the Following Options\nPress 1 for PKR\nPress 2 for USD\nPress 3 for AED\nPress 4 for EUR\nEnter Choice:");
            choice=input.nextInt();
        }

        System.out.println("\n\nEnter Your Value: ");
        String value=input.next();
        while (!Check(value)) {
            System.out.println("\n\nEnter Your Value: ");
            value=input.next();
        }

        if (choice == 1) {
            System.out.println("\nEnter the currency you want to convert to\nPress 1 for USD\nPress 2 for AED\nPress 3 for EUR");
            int conversionChoice=input.nextInt();
            while (conversionChoice < 1 || conversionChoice > 3) {
                System.out.println("\n\nINCORRECT INPUT: KINDLYT SELECT FROM GIVEN INPUT!");
                System.out.println("\nEnter the currency you want to convert to\nPress 1 for USD\nPress 2 for AED\nPress 3 for EUR");
                conversionChoice=input.nextInt();
            }
            double Value=Double.parseDouble(value);
            double answer=ConverterPKR(conversionChoice, Value);
            System.out.println("\nValue in PKR : " + Value);
            System.out.println("\nValue After Conversion: " + answer);
        } else if (choice == 2) {
            System.out.println("\nEnter the currency you want to convert to\nPress 1 for PKR\nPress 2 for AED\nPress 3 for EUR");
            int conversionChoice=input.nextInt();
            while (conversionChoice < 1 || conversionChoice > 3) {
                System.out.println("\n\nINCORRECT INPUT: KINDLYT SELECT FROM GIVEN INPUT!");
                System.out.println("\nEnter the currency you want to convert to\nPress 1 for PKR\nPress 2 for AED\nPress 3 for EUR");
                conversionChoice=input.nextInt();
            }
            double Value=Double.parseDouble(value);
            double answer=ConverterUSD(conversionChoice, Value);
            System.out.println("\nValue in USD : " + Value);
            System.out.println("\nValue After Conversion: " + answer);
        } else if (choice == 3) {
            System.out.println("\nEnter the currency you want to convert to\nPress 1 for PKR\nPress 2 for USD\nPress 3 for EUR");
            int conversionChoice=input.nextInt();
            while (conversionChoice < 1 || conversionChoice > 3) {
                System.out.println("\n\nINCORRECT INPUT: KINDLYT SELECT FROM GIVEN INPUT!");
                System.out.println("\nEnter the currency you want to convert to\nPress 1 for PKR\nPress 2 for USD\nPress 3 for EUR");
                conversionChoice=input.nextInt();
            }
            double Value=Double.parseDouble(value);
            double answer=ConverterAED(conversionChoice, Value);
            System.out.println("\nValue in AED : " + Value);
            System.out.println("\nValue After Conversion: " + answer);
        } else if (choice == 4) {
            System.out.println("\nEnter the currency you want to convert to\nPress 1 for PKR\nPress 2 for USD\nPress 3 for AED");
            int conversionChoice=input.nextInt();
            while (conversionChoice < 1 || conversionChoice > 3) {
                System.out.println("\n\nINCORRECT INPUT: KINDLYT SELECT FROM GIVEN INPUT!");
                System.out.println("\nEnter the currency you want to convert to\nPress 1 for PKR\nPress 2 for USD\nPress 3 for AED");
                conversionChoice=input.nextInt();
            }
            double Value=Double.parseDouble(value);
            double answer=ConverterEUR(conversionChoice, Value);
            System.out.println("\nValue in EUR : " + Value);
            System.out.println("\nValue After Conversion: " + answer);
        }
    }
}
