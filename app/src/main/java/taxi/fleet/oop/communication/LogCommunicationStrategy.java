package taxi.fleet.oop.communication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogCommunicationStrategy implements CommunicationStrategy {

    private static final Logger logger = LoggerFactory.getLogger(LogCommunicationStrategy.class);

    private final CommunicationStrategy wrappedStrategy;

    private LogCommunicationStrategy(CommunicationStrategy wrappedStrategy) {
        this.wrappedStrategy = wrappedStrategy;
    }

    public static LogCommunicationStrategy of(CommunicationStrategy communicationStrategy) {
        if (communicationStrategy == null) {
            throw new IllegalArgumentException("communicationStrategy can not be null");
        }
        return new LogCommunicationStrategy(communicationStrategy);
    }

    @Override
    public void sendMessage(String message, MessageCommunicationAgent recipient) {
        logger.info("Logging message: {}", message);
        wrappedStrategy.sendMessage(message, recipient);
    }
}

