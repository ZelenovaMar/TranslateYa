package com.company;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Scanner;

class Translat{
    int code;
    String lang;
    String[] text;

    @Override
    public String toString() {
        return "Code:" + code + "\n" +
                "Text:" + text[0];
    }
}

public class Main {
    final static String API_KEY = "key=trnsl.1.1.20200401T150017Z.a28106115826ace8.30b4cb318dcd7f879376d241fe4c9f1d0e10f6df";
    final static String API_URL = "https://translate.yandex.net/api/v1.5/tr.json/translate?";
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите текст");
        ArrayList<String> inputText = new ArrayList<>();
        String readyText = "";

        String input;
        do {
            {
                input = scanner.nextLine();
                inputText.add(input);
            }

        } while (input.length() != 0);
        inputText.remove(inputText.size()-1);

        System.out.println("Введите языки (например en-ru)");
        String language = scanner.nextLine();
        String format = "&format=plain";

        for (String part: inputText) {
            readyText += part;
        }

        String encoded = URLEncoder.encode(readyText, "UTF-8");
        URL url = new URL(API_URL + API_KEY + "&text=" + encoded + "&lang=" +language + format );

        Gson gson = new Gson();
        Translat translater = gson.fromJson(new InputStreamReader(url.openStream()), Translat.class);
        System.out.println(translater.toString());
    }
}
