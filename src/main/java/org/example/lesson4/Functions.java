package org.example.lesson4;
public class Functions {// проверка на полиндром
    //1
    //11
    //123321
    public boolean isPalindrome(String word) {//метод принимает на входи строку(слово), а возвращает слово без первого и последнего символа
        if (word.length() < 2) {// если длинна строки 1, то это полиндром автоматически-true, если нет, выходим-идем в следущий if
            return true;
        }

        if (word.charAt(0) != word.charAt(word.length() - 1)) {//сравниваем первый и последний символ, если не равны-выходим с false
            return false;
        }

        return isPalindrome(word.substring(1, word.length() - 1));//возвращает слово без первого и последнего символа
    }
    // Последовательность стеков: получается все методы висят еще в стеке пока у последнего не будет true, затем в обратном порядке они закрываеются с true
    //isPalindrome 1   - 123321 - true
    //isPalindrome 2   - 2332   - true
    //isPalindrome 3   - 33     - true
    //isPalindrome 4   - ""     - true
}
