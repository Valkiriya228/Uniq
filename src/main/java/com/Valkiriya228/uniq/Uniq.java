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


public class Uniq {

    private boolean isOutputFile = false;
    private boolean isCheckingRegistry = true;
    private boolean isOnlyUniq = false;
    private int numOfSymsSkipping = 0;
    private boolean isPrefixNeeded = false;
    private String inputFileName = "";
    private String outputFileName = "";

    Uniq(
            boolean isOutputFile,
            String outputFileName,
            boolean isCheckingRegistry,
            boolean isOnlyUniq,
            int numOfSymsSkipping,
            boolean isPrefixNeeded,
            String inputFileName) {
        if (isOutputFile) {
            this.isOutputFile = isOutputFile;
            this.outputFileName = outputFileName;
        }
        this.inputFileName = inputFileName;
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
        ArrayList<String> content = new ArrayList<>();
        ArrayList<String> outputList = new ArrayList<>();
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
                content.get(i).toLowerCase();
            }
        }




        if (isOnlyUniq) {
            for (int i = 0; i < content.size() - 1 ; i++) {
                if (!content.get(i).equals(content.get(i + 1))) {
                    outputList.add(content.get(i));
                }
            }
        }
        if (isPrefixNeeded) {
            int k = 0;
            for (int i = 0; i < content.size() - 1; i++) {
                if (content.get(i).equals(content.get(i + 1))) {
                    for (int j = i; j < content.size() - 1; j++) {
                        while (content.get(i).equals(content.get(j + 1))) {
                            k++;
                        }
                        outputList.add(k + content.get(j));
                    }
                } else  {
                    outputList.add(1 + content.get(i));
                }
            }
        }

        if (numOfSymsSkipping > 0) {
            for (int i = 0; i < content.size() - 1; i++) {
                for (int j = i + 1; j < content.size(); j++) {
                    if (content.get(i).length() == (content.get(j).length())) {
                        if (content.get(i).regionMatches(numOfSymsSkipping, content.get(j), numOfSymsSkipping,
                                content.get(i).length() - numOfSymsSkipping)) {
                            content.remove(content.get(j));
                        }
                    }
                }
                outputList.add(content.get(i));
            }
        } else {
            for (int i = 0; i < content.size() - 1; i++) {
                for (int j = i + 1; j < content.size(); j++) {
                    if (content.get(i).equals(content.get(j))) {
                        content.remove(content.get(j));
                    }
                }
                outputList.add(content.get(i));
            }
        }
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(new File(outputFileName)));

            if (outputFileName == "") for (String anOutputList : outputList) {
                System.out.println((anOutputList));
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


