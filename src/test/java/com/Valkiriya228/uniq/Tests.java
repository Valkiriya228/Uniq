package com.Valkiriya228.uniq;
/*Вариант 10 — uniq
        Объединение последовательностей одинаковых идущих подряд строк в файле в одну:
        file задаёт имя входного файла. Если параметр отсутствует, следует считывать текст с консоли.
        Флаг -o ofile задаёт имя выходного файла. Если параметр отсутствует, следует выводить результаты на консоль.
        Флаг -i означает, что при сравнении строк следует не учитывать регистр символов.
        Флаг -s N означает, что при сравнении строк следует игнорировать первые N символов каждой строки.
        Выводить нужно первую строку.
        Флаг -u означает, что следует выводить в качестве результата только уникальные строки.
        Флаг -с означает, что перед каждой строкой вывода следует вывести количество строк, которые были заменены данной
         (т.е. если во входных данных было 2 одинаковые строки, в выходных данных должна быть одна с префиксом “2”).

        Command line: uniq [-i] [-u] [-c] [-s num] [-o ofile] [file]

        В случае, когда какое-нибудь из имён файлов указано неверно, следует выдать ошибку.

        Кроме самой программы, следует написать автоматические тесты к ней.*/

import org.junit.Test;

import java.io.File;
import java.io.IOException;


import static com.Valkiriya228.uniq.Main.main;
import static org.junit.Assert.assertEquals;

public class Tests {
    private static void assertFiles(String expectedFileName, String actualFileName) throws IOException {
        File expectedFile = new File(expectedFileName);
        File actualFile = new File(actualFileName);

        assertEquals(expectedFile.length(), actualFile.length());


    }

    @Test
    public void testmain() throws IOException {
        String[] args = {"-i", "-u", "-o", "build/libs/actual.txt", "build/libs/input1.txt"};
        main(args);
        assertFiles("build/libs/expected1.txt", "build/libs/actual.txt");
    }

}

