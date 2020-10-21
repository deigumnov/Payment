package Common;

public class CheckPhone {
    // проверка корректности ввода номера телефона : ожидается ввод в формате X(XXX) XXXX-XX-XX
    // необходимо вводить только цифры вместо символов X
    // ввод дополнительных символов кроме X считается некорректным, первый символ должен быть равен 8
    public static <T> boolean checkPhone(T inputPhone) {
        boolean result = false;
        String phone = inputPhone.toString();
        if ((phone.startsWith("8") && phone.length() == 11) || (phone.startsWith("+8") && phone.length() == 12)) {
            result = true;
        }
        return result;
    }
}
