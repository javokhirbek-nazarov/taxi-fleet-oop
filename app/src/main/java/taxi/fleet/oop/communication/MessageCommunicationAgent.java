package taxi.fleet.oop.communication;

public interface MessageCommunicationAgent {

    void setCommunicationStrategy(CommunicationStrategy strategy);

    void sendMessage(String message, MessageCommunicationAgent recipient);

    void receiveMessage(String message);

}
