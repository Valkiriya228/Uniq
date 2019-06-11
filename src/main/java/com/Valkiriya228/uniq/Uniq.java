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

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Uniq {
    Scanner s = new Scanner(System.in);

    private boolean isCheckingRegistry;
    private boolean isOnlyUniq;
    private int numOfSymsSkipping;
    private boolean isPrefixNeeded;
    private String inputFileName = "";
    private String outputFileName = "";

    Uniq(
            String outputFileName,
            boolean isCheckingRegistry,
            boolean isOnlyUniq,
            int numOfSymsSkipping,
            boolean isPrefixNeeded,
            String inputFileName) {

        this.inputFileName = inputFileName;
        this.outputFileName = outputFileName;
        this.isCheckingRegistry = isCheckingRegistry;
        this.isOnlyUniq = isOnlyUniq;
        this.isPrefixNeeded = isPrefixNeeded;
        this.numOfSymsSkipping = numOfSymsSkipping;
    }

    Uniq(
            boolean isCheckingRegistry,
            boolean isOnlyUniq,
            int numOfSymsSkipping,
            boolean isPrefixNeeded,
            String inputFileName) {
        this.inputFileName = inputFileName;
        this.isCheckingRegistry = isCheckingRegistry;
        this.isOnlyUniq = isOnlyUniq;
        this.isPrefixNeeded = isPrefixNeeded;
        this.numOfSymsSkipping = numOfSymsSkipping;
    }

    public void uniq() throws IOException {
        List<String> content = new ArrayList<>();
        List<String> outputList = new ArrayList<>();
        try {
            String line;
            BufferedReader reader = new BufferedReader(new FileReader(new File(inputFileName)));
            while ((line = reader.readLine()) != null) {
                content.add(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        if (!isCheckingRegistry) {
            for (int i = 0; i < content.size(); i++) {
                content.set(i, content.get(i).toLowerCase());
            }
        }

        if (isOnlyUniq) {
            for (int i = 1; i < content.size() - 1; i++) {
                if (i == content.size() - 2) {
                    if (!content.get(i).equals(content.get(i + 1)))
                        outputList.add(content.get(i + 1));
                } else if (!content.get(0).equals(content.get(1)) && i == 1) {
                    outputList.add(content.get(0));
                } else if (!content.get(i).equals(content.get(i - 1)) &&
                        !content.get(i).equals(content.get(i + 1))) {
                    outputList.add(content.get(i));
                }
            }
        }
        if (isPrefixNeeded) {
            for (int i = 0; i < content.size() - 1; i++) {
                int k = 1;
                if (i == content.size() - 2) {
                    if (!content.get(i).equals(content.get(i + 1))) outputList.add(1 + content.get(i + 1));
                    break;
                }
                if (!content.get(i).equals(content.get(i + 1))) {
                    outputList.add(1 + content.get(i));
                } else while (content.get(i).equals(content.get(i + 1))) {
                    k++;
                    content.remove(i + 1);
                }
                outputList.add(k + content.get(i));
            }
        }
        if (numOfSymsSkipping > 0) {
            for (int i = 0; i < content.size() - 1; i++) {
                if (!content.get(i).substring(numOfSymsSkipping).equals(content.get(i + 1)
                        .substring(numOfSymsSkipping))) {
                    outputList.add(content.get(i));
                } else {
                    outputList.add(content.get(i));
                    for (int j = i; j < content.size() - 1; j++) {
                        if (content.get(j).substring(numOfSymsSkipping).equals(content.get(j + 1)
                                .substring(numOfSymsSkipping))) {
                            i = j + 1;
                        } else break;
                    }
                }
                if (i == content.size() - 2) {
                    if (!content.get(i).substring(numOfSymsSkipping).equals(content.get(i + 1)
                            .substring(numOfSymsSkipping))) outputList.add(content.get(i + 1));
                    break;
                }
            }
        }



        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(new File(outputFileName)));

            if (outputFileName.equals("")) {
                for (String anOutputList : outputList) {
                    System.out.println((anOutputList));
                }
            }
            else {
                for (String anOutputList : outputList) {
                    writer.write(anOutputList + "\n");
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


