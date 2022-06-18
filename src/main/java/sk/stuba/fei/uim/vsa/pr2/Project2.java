package sk.stuba.fei.uim.vsa.pr2;


import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.UriBuilder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import sk.stuba.fei.uim.vsa.pr2.service.CarParkService;

import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Project2 {


    public static final Logger LOGGER = Logger.getLogger(Project2.class.getName());
    public static final String BASE_URI = "http://localhost/";
    public static final int PORT = 8080;
    public static final Class<? extends Application> APPLICATION_CLASS = Project2Application.class; // TODO sem dosaď vlastnú triedu

    public static void main(String[] args) {
        try {
            final HttpServer server = startServer();
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    System.out.println("Shutting down the application...");
                    server.shutdownNow();
                    System.out.println("Exiting");
                } catch (Exception e) {
                    LOGGER.log(Level.SEVERE, null, e);
                }
            }));
            System.out.println("Last steps of setting up the application...");
            postStart();
            System.out.println(String.format("Application started.%nStop the application using CRL+C"));
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            LOGGER.log(Level.SEVERE, null, e);
        }
    }

    public static HttpServer startServer() {
        final ResourceConfig config = ResourceConfig.forApplicationClass(APPLICATION_CLASS);
        URI baseUri = UriBuilder.fromUri(BASE_URI).port(PORT).build();
        LOGGER.info("Starting Grizzly2 HTTP server...");
        LOGGER.info("Server listening on " + BASE_URI + ":" + PORT);
        return GrizzlyHttpServerFactory.createHttpServer(baseUri, config);
    }

    public static void postStart() {
        CarParkService service = new CarParkService();
        service.createCarPark("Aupark","Einteinova 20", 5);
        service.createCarPark("Ikea","Dolnozemska 46", 9);

        service.createCarParkFloor(1L,"1F");
        service.createCarParkFloor(2L,"1F");
//      //  service.createParkingSpot(2L,"1F","S1");
////        service.createParkingSpot(2L,"1F","S2");
////        service.createParkingSpot(1L,"2F","S1");
        service.createParkingSpot(1L,"1F","S1");
        service.createParkingSpot(1L,"1F","S2");
        service.createUser("Samo","Mojžiš","mail@mail.sk");
        service.createUser("Timo","Mojžiš","mail1@mail1.sk");
        service.createCar(1L,"Skoda","Fabia","Biela","BA696969");
        service.createCar(1L,"Wolks","Passat","Biela","KE4546456");
        service.createCar(2L,"BMW","X5","Biela","BB4546456");

//        service.createReservation(1L,1L);
//        service.createReservation(2L,3L);
//        service.createReservation(2L,1L);
//        service.createReservation(1L,3L);
//        service.createParkingSpot(1L,"1F","S2");

        service.createCarType("Diesel");
        service.createCarType("Nafta");
        // TODO sem napíš akékoľvek nastavenia, či volania, ktoré sa majú udiať ihneď po štarte
    }

}
