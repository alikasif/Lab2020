package lld;

import java.util.PriorityQueue;

enum Direction {
    IDLE,
    UP,
    DOWN
}

class ElevatorHandler {
    public static void goToFloor(AutomatedElevator elevator, int floor){

        System.err.println("*** Call for "+floor+" floor");

        if(elevator.currentfloor == floor){
            System.out.println("Lift is already at floor...Door Opening");
        }

        if(elevator.direction == Direction.UP){
            if(floor < elevator.currentfloor ){
                elevator.queueDown.add(floor);
            }
            else{
                elevator.queueUp.add(floor);
            }
        }
        else if(elevator.direction == Direction.DOWN){
            if( floor < elevator.currentfloor ){
                elevator.queueDown.add(floor);
            }
            else{
                elevator.queueUp.add(floor);
            }
        }
        else{          // Lift is stationary
            if( floor < elevator.currentfloor ){
                elevator.queueDown.add(floor);
            }
            else if(floor > elevator.currentfloor){
                elevator.queueUp.add(floor);
            }
        }
    }
}

class AutomatedElevator {

    private static AutomatedElevator _elevator;
    int startFloor;
    int endFloor;
    int currentfloor;
    int targetFloor;
    Direction direction;
    PriorityQueue<Integer> queueUp;
    PriorityQueue<Integer> queueDown;

    private AutomatedElevator(){
        this.currentfloor = 0;
        this.startFloor = 0;
        this.endFloor = 10;
        this.targetFloor = 0;
        this.direction = Direction.IDLE;
        this.queueDown = new PriorityQueue<>((a,b)->(b-a));      // 4 3 2
        this.queueUp = new PriorityQueue<>((a,b)->(a-b));        // 5 7 8

    }

    public synchronized static AutomatedElevator getElevator()
    {
        if ( _elevator == null ) {
            _elevator = new AutomatedElevator();
        }
        return _elevator;
    }

}

interface IElevator {

    public void startMovingUp(AutomatedElevator e);

    public void startMovingDown(AutomatedElevator e);

    public boolean isApproachingFloor(AutomatedElevator e, int floor);
}

class ElevatorServer implements Runnable, IElevator {

    final static AutomatedElevator elevator = AutomatedElevator.getElevator();

    /**
     * Method is continuously serving the lift request
     */
    @Override
    public void run() {
        while (true) {
            try {
                // sleep for 5 seconds
                try { Thread.sleep( 2000 ); } catch ( Exception e ) { }

                // Check for request
                // updates an new target location
                // Based on direction it will update the next upcoming floor
                if(elevator.direction == Direction.UP){
                    if(!elevator.queueUp.isEmpty()){
                        elevator.targetFloor = elevator.queueUp.peek();
                    }
                }else if(elevator.direction == Direction.DOWN){
                    if(!elevator.queueDown.isEmpty()){
                        elevator.targetFloor = elevator.queueDown.peek();
                    }
                }else{ // idle case
                    if(!elevator.queueUp.isEmpty()){
                        elevator.targetFloor = elevator.queueUp.peek();
                    }else if(!elevator.queueDown.isEmpty()){
                        elevator.targetFloor = elevator.queueDown.peek();
                    }
                }

                // When the target is set, it wil update the direction
                // This will help elevator to know where lift is going
                // and help goTOFloor to which queue to add the
                // upcoming request in

                if(elevator.currentfloor < elevator.targetFloor){
                    elevator.direction = Direction.UP;
                }else if(elevator.currentfloor > elevator.targetFloor){
                    elevator.direction = Direction.DOWN;
                }else{
                    elevator.direction = Direction.IDLE;
                }


                // below code will check am i on target floor
                if(elevator.queueDown.isEmpty() && elevator.queueUp.isEmpty()){
                    System.out.println("Lift is Stationary "+elevator.currentfloor);
                }else{
                    // handle up
                    if(elevator.currentfloor < elevator.targetFloor){
                        startMovingUp(elevator);
                        elevator.currentfloor++;
                    }
                    else if(elevator.currentfloor > elevator.targetFloor){
                        startMovingDown(elevator);
                        elevator.currentfloor--;
                    }
                    // Reached to Floor
                    if(elevator.currentfloor == elevator.targetFloor){
                        if(!elevator.queueDown.isEmpty() &&
                                elevator.queueDown.peek() == elevator.targetFloor)
                        {
                            elevator.queueDown.poll();		// request served
                        }
                        else if(!elevator.queueUp.isEmpty() &&
                                elevator.queueUp.peek() == elevator.targetFloor)
                        {
                            elevator.queueUp.poll();		// request served
                        }
                        System.out.println("*** Elevator Reached at "+elevator.targetFloor+" ***\nDoor Opening...\n");
                    }
//
//					System.out.println( "Elevator{" +
//							" \n currentfloor=	" + elevator.currentfloor +
//							",\n targetFloor=	" + elevator.targetFloor +
//							",\n direction=		" + elevator.direction +
//							",\n queueUp=		" + elevator.queueUp +
//							",\n queueDown=		" + elevator.queueDown +
//							"\n");

                    System.out.println("Elevator at "+elevator.currentfloor +" and going "+elevator.direction);
                }
            } catch (Exception e) {
                System.out.println( e ) ;
            }
        }
    }


    @Override
    public void startMovingUp(AutomatedElevator e) {

    }

    @Override
    public void startMovingDown(AutomatedElevator e) {

    }

    @Override
    public boolean isApproachingFloor(AutomatedElevator e, int floor) {
        if((e.currentfloor < floor && e.direction == Direction.UP) ||
                (e.currentfloor > floor && e.direction == Direction.DOWN)){
            return true;
        }
        return false;
    }
}

class Elevator {
    public static void main(String[] args) throws Exception {

        // Start the elevator server
        ElevatorServer server = new ElevatorServer();
        new Thread(server).start() ;

        // Get the instance of Elevator
        AutomatedElevator elevator = AutomatedElevator.getElevator();

        // Move Elevator
        ElevatorHandler.goToFloor(elevator, 2);
        ElevatorHandler.goToFloor(elevator, 5);
        ElevatorHandler.goToFloor(elevator, 9);

        Thread.sleep(6000);
        ElevatorHandler.goToFloor(elevator, 4);

        Thread.sleep(2000);
        ElevatorHandler.goToFloor(elevator, 2);
        ElevatorHandler.goToFloor(elevator, 3);
        ElevatorHandler.goToFloor(elevator, 3);
    }
}


































































/*

enum Direction {
    UP,
    DOWN,
    IDLE
}

enum Location {
    INSIDE_ELEVATOR,
    OUTSIDE_ELEVATOR
}

class Request {

    int currentFloor;
    int desiredFloor;
    Direction direction;
    Location location;

    public Request(int currentFloor, int desiredFloor, Direction direction, Location location) {
        this.currentFloor = currentFloor;
        this.desiredFloor = desiredFloor;
        this.direction = direction;
        this.location = location;
    }
}

public class Elevator {

    int currentFloor;
    Direction direction;
    PriorityQueue<Request> upQueue;
    PriorityQueue<Request> downQueue;

    public Elevator(int currentFloor) {
        this.currentFloor = currentFloor;

        this.direction = Direction.IDLE;

        // use default, which is a min heap
        upQueue = new PriorityQueue<>((a, b) -> a.desiredFloor - b.desiredFloor);

        // use a max heap
        downQueue =  new PriorityQueue<>((a, b) -> b.desiredFloor - a.desiredFloor);
    }

    public void sendUpRequest(Request upRequest) {
        // If the request is sent from out side of the elevator,
        // we need to stop at the current floor of the requester
        // to pick him up, and then go the the desired floor.
        if (upRequest.location == Location.OUTSIDE_ELEVATOR) {
            // Go pick up the requester who is outside of the elevator
            upQueue.offer(new Request(upRequest.currentFloor,
                    upRequest.currentFloor,
                    Direction.UP,
                    Location.OUTSIDE_ELEVATOR));

            System.out.println("Append up request going to floor " + upRequest.currentFloor + ".");
        }

        // Go to the desired floor
        upQueue.offer(upRequest);

        System.out.println("Append up request going to floor " + upRequest.desiredFloor + ".");
    }

    public void sendDownRequest(Request downRequest) {
        // Similar to the sendUpRequest logic
        if (downRequest.location == Location.OUTSIDE_ELEVATOR) {
            downQueue.offer(new Request(downRequest.currentFloor,
                    downRequest.currentFloor,
                    Direction.DOWN,
                    Location.OUTSIDE_ELEVATOR));

            System.out.println("Append down request going to floor " + downRequest.currentFloor + ".");
        }

        // Go to the desired floor
        downQueue.offer(downRequest);

        System.out.println("Append down request going to floor " + downRequest.desiredFloor + ".");
    }

    public void run() {
        while (!upQueue.isEmpty() || !downQueue.isEmpty()) {
            processRequests();
        }

        System.out.println("Finished all requests.");
        this.direction = Direction.IDLE;
    }

    private void processRequests() {
        if (this.direction == Direction.UP || this.direction == Direction.IDLE) {
            processUpRequest();
            processDownRequest();
        } else {
            processDownRequest();
            processUpRequest();
        }
    }

    private void processUpRequest() {
        while (!upQueue.isEmpty()) {
            Request upRequest = upQueue.poll();
            // Communicate with hardware
            this.currentFloor = upRequest.desiredFloor;
            System.out.println("Processing up requests. Elevator stopped at floor " + this.currentFloor + ".");
        }
        if (!downQueue.isEmpty()) {
            this.direction = Direction.DOWN;
        } else {
            this.direction = Direction.IDLE;
        }
    }

    private void processDownRequest() {
        while (!downQueue.isEmpty()) {
            Request downRequest = downQueue.poll();
            // Communicate with hardware
            this.currentFloor = downRequest.desiredFloor;
            System.out.println("Processing down requests. Elevator stopped at floor " + this.currentFloor + ".");
        }
        if (!upQueue.isEmpty()) {
            this.direction = Direction.UP;
        } else {
            this.direction = Direction.IDLE;
        }
    }

    public static void main(String[] args) {
        Elevator elevator = new Elevator(0);

        Request upRequest1 = new Request(elevator.currentFloor, 5, Direction.UP, Location.INSIDE_ELEVATOR);
        Request upRequest2 = new Request(elevator.currentFloor, 3, Direction.UP, Location.INSIDE_ELEVATOR);

        Request downRequest1 = new Request(elevator.currentFloor, 1, Direction.DOWN, Location.INSIDE_ELEVATOR);
        Request downRequest2 = new Request(elevator.currentFloor, 2, Direction.DOWN, Location.INSIDE_ELEVATOR);
        Request downRequest3 = new Request(4, 0, Direction.DOWN, Location.OUTSIDE_ELEVATOR);

        // Two people inside of the elevator pressed button to go up to floor 5 and 3.
        elevator.sendUpRequest(upRequest1);
        elevator.sendUpRequest(upRequest2);

        // One person outside of the elevator at floor 4 pressed button to go down to floor 0
        elevator.sendDownRequest(downRequest3);

        // Two person inside of the elevator pressed button to go down to floor 1 and 2.
        elevator.sendDownRequest(downRequest1);
        elevator.sendDownRequest(downRequest2);

        elevator.run();
    }
}
*/
