package taxi.fleet.oop.communication;

public class DirectCommunicationStrategy implements CommunicationStrategy {

    private static final DirectCommunicationStrategy INSTANCE = new DirectCommunicationStrategy();

    private DirectCommunicationStrategy() {
    }

    public static DirectCommunicationStrategy get() {
        return INSTANCE;
    }

    @Override
    public void sendMessage(String message, MessageCommunicationAgent recipient) {
        recipient.receiveMessage(message);
    }
}
