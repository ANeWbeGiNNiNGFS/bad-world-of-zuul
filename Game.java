/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class Game 
{
    private Parser parser;

    private Room currentRoom;

        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {

        // create the rooms
        Room spawn = new Room("in the spawn room");
        Room spawnexit = new Room("in the middle of the gen1 building");
        Room firecaveEntrance = new Room("standing at the entrance to the fire cave");
        Room fireportal = new Room("infront of the fireportal");
        Room crazyplaceFire = new Room("in front of the firecrystal pedestal. ");
        Room generator1 = new Room("next to generator 1");
        Room trenches = new Room("inside the trenches of ww1");
        Room generator2 = new Room("beside generator 2");
        Room staircasetop = new Room("a top the staircase separating gen. 2 and gen. 3");
        Room generator3 = new Room("beside generator 3");
        Room entrancetoNML = new Room("trenches leading to no mans land");
        Room nomansland = new Room("No Mans Land");
        Room moundEntrance = new Room("in front of the entrance to the mound");
        Room mound = new Room("inside of the mound");
        Room generator5 = new Room("next to generator 5");
        Room generator4 = new Room("beside generator 4");
        Room church = new Room("inside the church");
        Room generator6 = new Room("next to generator 6");
        Room iceCaveEntrance = new Room("by the entrance to the ice cave");
        Room icePortal = new Room("in front of the ice portal");
        Room crazyplaceIce = new Room("in front of the ice crystal pedestal");
        Room amongussus = new Room("in the kitchen");



        
        // initialise room exits
        spawn.setExits("north", spawnexit);
        spawnexit.setExits("east", amongussus);
        amongussus.setExits("north west", fireportal);
        //firecaveEntrance.setExits(null, null, spawnexit, fireportal);
        fireportal.setExits("north east", spawn);
        /*crazyplaceFire.setExits(null,fireportal, null,null);
        generator1.setExits(null, trenches, null, spawnexit);
        trenches.setExits(null, generator2, null, generator1);
        generator2.setExits(staircasetop, null, null, trenches);
        staircasetop.setExits(entrancetoNML, null, generator2, generator3);
        generator3.setExits(null, staircasetop, null, null);
        entrancetoNML.setExits(nomansland, null, staircasetop, null);
        nomansland.setExits(null, generator5, entrancetoNML, moundEntrance);
        generator5.setExits(null, null, null, nomansland);
        moundEntrance.setExits(mound, nomansland, null, generator4);
        mound.setExits(church, null, moundEntrance, null);
        generator4.setExits(null, moundEntrance, null, null);
        church.setExits(generator6, null, mound, iceCaveEntrance);
        generator6.setExits(null, null, church, null);
        iceCaveEntrance.setExits(null, church, null, icePortal);
        icePortal.setExits(null, iceCaveEntrance, null, crazyplaceIce);
        crazyplaceIce.setExits(null, icePortal, null, null);*/

        currentRoom = spawn;  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play()
    {
        printWelcome();
        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        printLocationInfo();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command)
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the
     * command words.
     */
    private void printHelp()
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println("   go quit help");
    }

    /**
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();
        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);
        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            if(currentRoom.getExit("north") != null) {
            }
            if(currentRoom.getExit("east") != null) {
            }
            if(currentRoom.getExit("south") != null) {
            }
            if(currentRoom.getExit("west") != null) {
            }
            printLocationInfo();
        }
    }
    public void printLocationInfo(){
        System.out.println(currentRoom.getLongDescription());
        //System.out.println(currentRoom.getExitString());
        /*if(currentRoom.getExit("north") != null) {
            System.out.print("north ");
        }
        if(currentRoom.getExit("east") != null) {
            System.out.print("east ");
        }
        if(currentRoom.getExit("south") != null) {
            System.out.print("south ");
        }
        if(currentRoom.getExit("west") != null) {
            System.out.print("west ");
        }
        System.out.println();*/
    }

    /**
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command)
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
