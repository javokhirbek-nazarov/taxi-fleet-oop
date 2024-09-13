package taxi.fleet.oop.dashboard;

public abstract class Dashboard {

    public Dashboard() {
    }

    public final void displayStatistics() {
        System.out.println("================== Dashboard Statistics: ================== \n");
        //Some other common logic for all dashboards

        System.out.println(getStatistics());

        System.out.println("\n================== End of Statistics: ================== \n");
        //Some other common logic for all dashboards
    }

    abstract String getStatistics();
}

