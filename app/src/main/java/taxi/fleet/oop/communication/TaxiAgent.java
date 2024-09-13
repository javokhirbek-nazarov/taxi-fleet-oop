package taxi.fleet.oop.communication;

import taxi.fleet.oop.taxi.Taxi;

public class TaxiAgent implements MessageCommunicationAgent {

    private final Taxi taxi;
    private CommunicationStrategy strategy;

    public TaxiAgent(Taxi taxi) {
        this.taxi = taxi;
        this.strategy = DirectCommunicationStrategy.get();
    }

    @Override
    public void setCommunicationStrategy(CommunicationStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public void sendMessage(String message, MessageCommunicationAgent recipient) {
        strategy.sendMessage(message, recipient);
    }

    @Override
    public void receiveMessage(String message) {
        System.out.println("Received message: \n" + message);
    }

    public Taxi getTaxi() {
        return taxi;
    }
}

