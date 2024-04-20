package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class OneDesicion_demo_bot1_bot extends TelegramLongPollingBot {
    private static final Logger logger = Logger.getLogger(OneDesicion_demo_bot1_bot.class.getName());

    static {
        try {
            // Set up file handler to save logs to a text file
            FileHandler fileHandler = new FileHandler("bot_logs.txt");
            logger.addHandler(fileHandler);

            // Set up formatter for the log entries
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);

            // Set logger level to INFO
            logger.setLevel(Level.INFO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "OneDesicion_demo_bot1_bot";
    }

    @Override
    public String getBotToken() {
        return "7050354231:AAHQhniBSHX9i5zGlqqN0MMKfZqeKnVAIyU";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            Message message = update.getMessage();
            String messageText = message.getText();
            long chatId = message.getChatId();
            String userFirstName = message.getChat().getFirstName();
            String userLastName = message.getChat().getLastName();
            long userId = message.getChat().getId();

            // Get current date and time
            Date now = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            // Log the received message with timestamp
            logger.info("[" + dateFormat.format(now) + "] Received message from " + userFirstName + " " + userLastName + " (id = " + userId + "): " + messageText);

            // Create Inline Keyboard Markup with a "Text to speech" button
            InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
            List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
            List<InlineKeyboardButton> row = new ArrayList<>();
            InlineKeyboardButton textToSpeechButton = new InlineKeyboardButton();
            textToSpeechButton.setText("Text to speech");
            textToSpeechButton.setCallbackData("text_to_speech");
            row.add(textToSpeechButton);
            keyboard.add(row);
            keyboardMarkup.setKeyboard(keyboard);

            // Prepare the response message with Inline Keyboard Markup
            SendMessage responseMessage = new SendMessage();
            responseMessage.setChatId(chatId);
            responseMessage.setText("Choose an option:");
            responseMessage.setReplyMarkup(keyboardMarkup);

            // Log the response message with timestamp
            logger.info("[" + dateFormat.format(now) + "] Sending response to " + userFirstName + " " + userLastName + " (id = " + userId + "): Choose an option");

            try {
                execute(responseMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}