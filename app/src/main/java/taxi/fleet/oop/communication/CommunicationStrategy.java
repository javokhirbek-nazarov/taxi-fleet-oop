package taxi.fleet.oop.communication;

public interface CommunicationStrategy {

    void sendMessage(String message, MessageCommunicationAgent recipient);
}

