package Common;

public class Check {
    // проверка корректности ввода номера телефона : ожидается ввод в формате X(XXX) XXXX-XX-XX
    // необходимо вводить только цифры вместо символов X
    // ввод дополнительных символов кроме X считается некорректным, первый символ должен быть равен 8
    public static <T> boolean checkPhone(T inputPhone) {
        boolean result = true;
        String phone = inputPhone.toString();
        if (!phone.startsWith("8")) {
            result = false;
        }
        if (phone.length() != 11) {
            result = false;
        }
        return result;
    }

    // проверка на наличие не отрицательной суммы со значением до двух знаков
    public static boolean checkSum(double sum) {
        boolean result = true;
        if (sum <= 0.0) {
            result = false;
        }
        if (Math.round(sum*100)/100.0 != sum) {
            result = false;
        }
        return result;
    }
}