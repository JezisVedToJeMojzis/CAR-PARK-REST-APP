package sk.stuba.fei.uim.vsa.pr2;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import sk.stuba.fei.uim.vsa.pr2.web.*;

import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/")
public class Project2Application extends Application{

    static final Set<Class<?>> appClasses = new HashSet<>();

    static {
        appClasses.add(CarParkResource.class);
        appClasses.add(CarParkFloorResource.class);
        appClasses.add(ParkingSpotResource.class);
        appClasses.add(ParkingSpotResource2.class);
        appClasses.add(CarResource.class);
        appClasses.add(UserResource.class);
        appClasses.add(ReservationResource.class);
        appClasses.add(CarTypeResource.class);
        appClasses.add(CORSFilter.class);
    }

    @Override
    public Set<Class<?>> getClasses() {
        return appClasses;
    }

}
