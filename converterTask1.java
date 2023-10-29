import java.util.Scanner;

public class converterTask1 {
    //Conversion from Celsius To Ferenheit:
    public static double converterCTF(double value) {
        double answer=value * 1.8;
        answer=answer + 32;
        return answer;
    }

    //Conversion from Ferenheit Tp Celsius:
    public static double converterFTC(double value) {
        double answer=value - 32;
        answer=(answer * 5) / 9;
        return answer;
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
        System.out.println("WELCOME TO TEMPERATURE CONVERSION SYSTEM\n");
        Scanner input=new Scanner(System.in);

        System.out.println("\nPRESS 1 for Conversion from Celsius to Ferenheit \nPress 2 for Conversion from Ferenheit to Celsius\nEnter Choice: ");
        int choice=input.nextInt();

        while (choice < 1 || choice > 2) {
            System.out.println("\nERROR KINDLY ENTER FROM GIVEN CHOICES");
            System.out.println("\nPRESS 1 for Conversion from Celsius to Ferenheit\nPress 2 for Conversion from Ferenheit to Celsius\nEnter Choice: ");
            choice=input.nextInt();
        }

        if (choice == 1) {
            System.out.println("\nYOU ARE CONVERTING FROM CELSIUS TO FERENHEIT");
            System.out.println("\nEnter Value: ");
            String value=input.next();
            boolean check=Check(value);
            while (!check) {
                System.out.println("\nEnter Value: ");
                value=input.next();
                check=Check(value);
            }
            double Value=Double.parseDouble(value);
            double answer=converterCTF(Value);
            System.out.println("\n\nTemperature in Celsius:" + Value);
            System.out.println("Temperature After Conversion in Ferenheit:" + answer);
        } else if (choice == 2) {
            System.out.println("\nYOU ARE CONVERTING FROM FERENHEIT TO CELSIUS");
            System.out.println("\nEnter Value: ");
            String value=input.next();
            boolean check=Check(value);
            while (!check) {
                System.out.println("\nEnter Value: ");
                value=input.next();
                check=Check(value);
            }
            double Value=Double.parseDouble(value);
            double answer=converterFTC(Value);
            System.out.println("\n\nTemperature in Ferenheit:" + Value);
            System.out.println("Temperature After Conversion in Celsius:" + answer);
        }
        input.close();
        return;
    }
}
