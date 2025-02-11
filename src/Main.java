import java.util.Scanner;

class Calc {

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        String input = scn.nextLine();
        System.out.println(calc(input));
    }

    public static String calc(String input) throws Exception {
        int a;
        int b;
        String sign;
        String total;
        boolean isRoman;
        String[] nums = input.split("[+\\-*/]");
        if (nums.length != 2)
            throw new Exception("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        sign = whatSign(input);

        if (Roman.isRoman(nums[0]) && Roman.isRoman(nums[1])) {
            a = Roman.toArab(nums[0]);
            b = Roman.toArab(nums[1]);
            isRoman = true;
        } else if (!Roman.isRoman(nums[0]) && !Roman.isRoman(nums[1])) {
            a = Integer.parseInt(nums[0]);
            b = Integer.parseInt(nums[1]);
            isRoman = false;
        } else {
            throw new Exception("Используются одновременно разные системы счисления");
        }
        if (a > 10 || b > 10) {
            throw new Exception("На вход числа от 1 до 10 включительно, не более.");
        }
        int arab = sumOfArab(a, b, sign);
        if (isRoman) {
            if (arab <= 0) {
                throw new Exception("В римской системе нет отрицательных чисел");
            }

            total = Roman.toRoman(arab);
        } else {
            total = String.valueOf(arab);
        }
        return total;
    }

    static String whatSign(String input) {
        if (input.contains("+")) return "+";
        else if (input.contains("-")) return "-";
        else if (input.contains("*")) return "*";
        else if (input.contains("/")) return "/";
        else return null;
    }

    static int sumOfArab(int a, int b, String sign) {

        if (sign.equals("+")) return a + b;
        else if (sign.equals("-")) return a - b;
        else if (sign.equals("*")) return a * b;
        else return a / b;
    }

}

class Roman {
    static String[] romanArr = new String[]{"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX"};


    public static boolean isRoman(String str) {
        for (int i = 0; i < romanArr.length; i++) {
            if (str.equals(romanArr[i])) {
                return true;
            }
        }
        return false;
    }

    public static int toArab(String roman) {
        for (int i = 0; i < romanArr.length; i++) {
            if (roman.equals(romanArr[i])) {
                return i;
            }
        }
        return -1;
    }

    public static String toRoman(int arabian) {
        return romanArr[arabian];
    }

}