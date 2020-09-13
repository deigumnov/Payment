package Common;

public class Check {

    // проверка корректности ввода номера телефона : ожидается ввод в формате 8(XXX)XXXX-XX-XX
    // необходимо вводить только цифры вместо символов X
    // все символы кроме цифр из строки удаляются..
    // далее если длина строки не равна 10 возвращается пустое значение
    public static String checkPhone(String phone) {
        String result = phone.replaceAll("[^0-9]", "");
        if (result.length() != 10) {
            result = null; // бизнес ошибка - телефон введен некорректно
        }
        return result;
    }

    // проверка на наличие не отрицательной суммы со значением до двух знаков
    // в противном случае возвращается ноль
    public static double checkSum(double sum) {
        double result = Math.max(sum, 0.0);
        result = Math.round(result*100);
        result = result/100;
        return result;
    }
}