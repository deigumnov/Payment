package Common;

import java.util.function.Predicate;

public class CheckPhone {
    // проверка корректности ввода номера телефона : ожидается ввод в формате X(XXX) XXXX-XX-XX
    // необходимо вводить только цифры вместо символов X
    // ввод дополнительных символов кроме X считается некорректным, первый символ должен быть равен 8
    public static <T> boolean checkPhone(T inputPhone) {
        boolean result = false;
        String phone = inputPhone.toString();
        if ((phone.startsWith("8") && phone.length() == 11)) {
            result = true;
        }
        return result;
    }
    public static Boolean checkPhone2(String inputPhone) {
        Predicate<String> isCorrect = x -> x.startsWith("+8") && x.length() == 12;
        return isCorrect.test(inputPhone);
    }
}
