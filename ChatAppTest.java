/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.mycompany.finalpoe;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.BeforeClass;
import org.junit.Test;

public class ChatAppTest {

    @BeforeClass
    public static void setup() {
    ChatApp.recipientList.clear();
    ChatApp.messageList.clear();
    ChatApp.flagList.clear();

    ChatApp.recipientList.add("+27834557896");
    ChatApp.messageList.add("Did you get the cake?");
    ChatApp.flagList.add("Sent");

    ChatApp.recipientList.add("+27838884567");
    ChatApp.messageList.add("Where are you? You are late! I have asked you to be on time.");
    ChatApp.flagList.add("Stored");

    ChatApp.recipientList.add("+27834484567");
    ChatApp.messageList.add("Yohoooo, I am at your gate.");
    ChatApp.flagList.add("Disregard");

    ChatApp.recipientList.add("0838884567");
    ChatApp.messageList.add("It is dinner time !");
    ChatApp.flagList.add("Sent");

    ChatApp.recipientList.add("+27838884567");
    ChatApp.messageList.add("Ok, I am leaving without you.");
    ChatApp.flagList.add("Stored");
}

@Test
public void testSentMessagesArePopulated() {
    List<String> expectedSent = Arrays.asList(
        "Did you get the cake?",
        "It is dinner time !"
    );

    List<String> actualSent = new ArrayList<>();
    for (int i = 0; i < ChatApp.flagList.size(); i++) {
        if (ChatApp.flagList.get(i).equalsIgnoreCase("Sent")) {
            actualSent.add(ChatApp.messageList.get(i));
        }
    }

    assertEquals(expectedSent, actualSent);
}

@Test
public void testLongestMessage() {
    String expected = "Where are you? You are late! I have asked you to be on time.";
    String longest = "";

    for (String msg : ChatApp.messageList) {
        if (msg.length() > longest.length()) {
            longest = msg;
        }
    }

    assertEquals(expected, longest);
}

@Test
public void testSearchByMessageID() {
    String developer = "0838884567";
    String expected = "It is dinner time !";

    for (int i = 0; i < ChatApp.recipientList.size(); i++) {
        if (ChatApp.recipientList.get(i).equals(developer)) {
            assertEquals(expected, ChatApp.messageList.get(i));
            return;
        }
    }

    fail("Developer message not found.");
}

@Test
public void testSearchMessageByRecipient() {
    String recipient = "+27838884567";
    List<String> expectedMessages = Arrays.asList(
        "Where are you? You are late! I have asked you to be on time.",
        "Ok, I am leaving without you."
    );

    List<String> actualMessages = new ArrayList<>();
    for (int i = 0; i < ChatApp.recipientList.size(); i++) {
        if (ChatApp.recipientList.get(i).equals(recipient)) {
            actualMessages.add(ChatApp.messageList.get(i));
        }
    }

    assertTrue(actualMessages.containsAll(expectedMessages));
}

@Test
public void testDeleteMessageByHash() {
    String targetMessage = "Where are you? You are late! I have asked you to be on time.";
    int indexToDelete = -1;

    for (int i = 0; i < ChatApp.messageList.size(); i++) {
        if (ChatApp.messageList.get(i).equals(targetMessage)) {
            indexToDelete = i;
            break;
        }
    }

    assertTrue(indexToDelete != -1);

    ChatApp.recipientList.remove(indexToDelete);
    ChatApp.messageList.remove(indexToDelete);
    ChatApp.flagList.remove(indexToDelete);

    assertFalse(ChatApp.messageList.contains(targetMessage));
}

class ChatApp {
    public static List<String> recipientList = new ArrayList<>();
    public static List<String> messageList = new ArrayList<>();
    public static List<String> flagList = new ArrayList<>();
}}
