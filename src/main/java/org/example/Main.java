package org.example;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws TelegramApiException {
        try {
                         // Create a TelegramBotsApi instance
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);

            // Create an instance of your bot class
            OneDesicion_demo_bot1_bot bot = new OneDesicion_demo_bot1_bot();

            // Register your bot with the Telegram Bot API
            botsApi.registerBot(bot);

            // Print a message indicating successful bot startup
            System.out.println("Bot is up and running!");

    } catch (TelegramApiException e) {
        e.printStackTrace();
    }

   }
}