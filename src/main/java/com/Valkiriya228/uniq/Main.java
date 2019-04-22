package com.Valkiriya228.uniq;

import java.io.IOException;

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
public class Main {
    public static void main(String[] args) {

        boolean isOutputFile = false;
        boolean isCheckingRegistry = true;
        boolean isOnlyUniq = false;
        int numOfSymsSkipping = 0;
        boolean isPrefixNeeded = false;
        String inputFileName = "";
        String outputFileName = "";
        if (args.length == 0) {
            System.out.println("Объединение последовательностей одинаковых идущих подряд строк в файле в одну\n" +
                    "uniq [-i] [-u] [-c] [-s num] [-o ofile] [file]\n\n" +
                    "Параметры:\n" +
                    "Флаг -o ofile задаёт имя выходного файла. Если параметр отсутствует, следует выводить результаты на консоль.\n" +
                    "Флаг -i означает, что при сравнении строк следует не учитывать регистр символов.\n" +
                    "Флаг -s N означает, что при сравнении строк следует игнорировать первые N символов каждой строки.\n" +
                    "Выводить нужно первую строку.\n" +
                    "Флаг -u означает, что следует выводить в качестве результата только уникальные строки.\n" +
                    "Флаг -с означает, что перед каждой строкой вывода следует вывести количество строк, которые были заменены данной\n");
        } else {
            for (int i = 0; i < args.length; i++) {
                if (args[i].equals("-o")) {
                    isOutputFile = true;
                    outputFileName = args[i + 1];
                }
            }
            for (String arg : args) {
                if (arg.equals("-i")) {
                    isCheckingRegistry = false;
                }
            }
            for (int i = 0; i < args.length; i++) {
                if (args[i].equals("-s")) {
                     numOfSymsSkipping = Integer.parseInt(args[i + 1]);
                }
            }
            for (String arg : args) {
                if (arg.equals("-u")) {
                    isOnlyUniq = true;
                }
            }
            for (String arg : args) {
                if (arg.equals("-c")) {
                    isPrefixNeeded = true;
                }
            }

            inputFileName = args[args.length - 1];
        }
        Uniq worker = new Uniq(isOutputFile,
                outputFileName,
                isCheckingRegistry,
                isOnlyUniq,
                numOfSymsSkipping,
                isPrefixNeeded,
                inputFileName);
        try {
            worker.uniq();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}


