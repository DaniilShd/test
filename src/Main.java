import javax.management.modelmbean.InvalidTargetObjectTypeException;
import java.io.IOException;
import java.util.Scanner;
import static java.lang.Integer.parseInt;


public class Main {

    public static String getStringConsole() {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите строку из трех элементов: ");
        return in.nextLine();
    }

    public static void checkingNumberElement(String[] array) {
        if (array.length >3) {
            try {
                throw new IOException();
            } catch (IOException e) {

                System.out.println("throws Exception //т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
                System.exit(0);
            }
        }
        if (array.length < 2) {
            try {
                throw new IOException();
            } catch (IOException e) {

                System.out.println("throws Exception //т.к. строка не является математической операцией");
                System.exit(0);
            }
        }
    }

    public static void checkingMathSign(String sign) {
        String[] arraySign = {"+", "-", "*", "/"};
        boolean isSign = false;
        for (String item : arraySign) {
            if (item.equals(sign)) {
                isSign = true;
                break;
            }
        }
        if (!isSign) {
            try {
                throw new InvalidTargetObjectTypeException();
            } catch (InvalidTargetObjectTypeException e) {
                System.out.println("Некорректная математическая операция");
            }
        }
    }

    public static Argument checkingTypeArguments(String[] array) {

        Argument argumentOne = null;
        Argument argumentTwo = null;

        if (isArabicNumber(array[0])) {
            argumentOne = Argument.isArabic;
        } else if(isRomanNumber(array[0])) {
            argumentOne = Argument.isRoman;
        }

        if (isArabicNumber(array[2])) {
            argumentTwo = Argument.isArabic;
        } else if(isRomanNumber(array[2])) {
            argumentTwo = Argument.isRoman;
        }

        if (argumentOne != argumentTwo) {
            try {
                throw new IOException();
            } catch (IOException e) {
                System.out.println("throws Exception //т.к. используются одновременно разные системы счисления");
                System.exit(0);
            }
        }
        if (argumentOne == Argument.isArabic & argumentTwo == Argument.isArabic) {
        return Argument.isArabic;
        } else  {
            return Argument.isRoman;
        }

    }

    public static String mathResult(Argument arguments, String[] array) {
        int argumentOne = 0;
        int argumentTwo = 0;
        int result = 0;

        if (arguments == Argument.isArabic) {
            argumentOne = parseInt(array[0]);
            argumentTwo = parseInt(array[2]);
        }

        if (arguments == Argument.isRoman) {
            argumentOne = convertRomanToArabic(array[0]);
            argumentTwo = convertRomanToArabic(array[2]);
        }

        switch (array[1]) {
            case "+" -> result = argumentOne + argumentTwo;
            case "*" -> result = argumentOne * argumentTwo;
            case "/" -> result = argumentOne / argumentTwo;
            case "-" -> result = argumentOne - argumentTwo;
        }

        if (arguments == Argument.isArabic) {
            return result+"";
        } else {
            if (result < 0) {
                try {
                    throw new IOException();
                } catch (IOException e) {
                    System.out.println("throws Exception //т.к. в римской системе нет отрицательных чисел");
                    System.exit(0);
                }
            }
            if (result == 0) {
                try {
                    throw new IOException();
                } catch (IOException e) {
                    System.out.println("throws Exception //т.к. в римской системе нет ноля");
                    System.exit(0);
                }
            }
            return convertArabicToRoman(result);
        }
        }

    public static int convertRomanToArabic (String str) {
        char[] chars = str.toCharArray();
        int resultArab;
        int[] arrayArab = new int[str.length()];
        for (var i = 0; i < str.length(); i++) {
            switch (chars[i]) {
                case 'I' -> arrayArab[i] = 1;
                case 'V' -> arrayArab[i] = 5;
                case 'X' -> arrayArab[i] = 10;
            }
        }
            resultArab = arrayArab[0];
        for(var i = 0; i<arrayArab.length - 1; i++) {
            if (arrayArab[1] > arrayArab[0]) {
                resultArab = arrayArab[1] - resultArab;
            } else {
                resultArab = arrayArab[1] + resultArab;
            }
        }
        return resultArab;
        }


    public static String convertArabicToRoman (int number) {
        String numberRoman = "";
        int number10 = number / 10;
        int number1 = number % 10;
        switch (number10) {
            case 1 -> numberRoman = "X";
            case 2 -> numberRoman = "XX";
            case 3 -> numberRoman = "XXX";
            case 4 -> numberRoman = "XL";
            case 5 -> numberRoman = "L";
            case 6 -> numberRoman = "LX";
            case 7 -> numberRoman = "LXX";
            case 8 -> numberRoman = "LXXX";
            case 9 -> numberRoman = "XC";
            case 10 -> numberRoman = "C";
        }

        numberRoman = switch (number1) {
            case 1 -> numberRoman + "I";
            case 2 -> numberRoman + "II";
            case 3 -> numberRoman + "III";
            case 4 -> numberRoman + "IV";
            case 5 -> numberRoman + "V";
            case 6 -> numberRoman + "VI";
            case 7 -> numberRoman + "VII";
            case 8 -> numberRoman + "VIII";
            case 9 -> numberRoman + "IX";
            default -> numberRoman;
        };

        return numberRoman;
    }


    public static Boolean isRomanNumber(String str) {

        switch (str) {
            case "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX" -> {
                return true;
            }
        }
        try {
            throw new IOException();
        } catch (IOException e) {
            System.out.println("Римское число не входит в данный диапазон I, II...IX, X");
            System.exit(0);
        }
        return false;
    }

    public static Boolean isArabicNumber(String str) {
        try {
            int intValue = parseInt(str);
            if (1 > intValue | intValue > 10) {
                try {
                    throw new IOException();
                } catch (IOException e) {
                    System.out.println("throws Exception //т.к. число меньше 1, либо больше 10");
                    System.exit(0);
                }
            }
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static String calc(String input) {
        String[] arrayOfArgument;

        MyString str = new MyString(input);
        arrayOfArgument = str.separateStringConsole();

        checkingNumberElement(arrayOfArgument);

        checkingMathSign(arrayOfArgument[1]);
        return mathResult(checkingTypeArguments(arrayOfArgument), arrayOfArgument);
    }

    public static void main(String[] args) {
        System.out.println(calc(getStringConsole()));
    }
}