package game;

import java.util.*;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.actions.TravelAction;
import game.actors.AlienBug;
import game.actors.HumanoidFigure;
import game.actors.Player;
import game.fruits.LargeFruit;
import game.grounds.*;
import game.scraps.*;
import game.spawners.AlienBugSpawner;
import game.spawners.HuntsmanSpiderSpawner;
import game.spawners.SuspiciousAstronautSpawner;
import game.trees.*;


/**
 * The main class to start the game.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 * Jin Ruo Yew
 */
public class Application {

    public static void main(String[] args) {

        World world = new World(new Display());

        FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(),
                new Wall(), new Floor(), new Puddle());

        List<String> map = Arrays.asList(
                "...~~~~.........~~~...........",
                "...~~~~.......................",
                "...~~~........................",
                "..............................",
                ".............#####............",
                ".............#___#...........~",
                ".............#___#..........~~",
                ".............##_##.........~~~",
                ".................~~........~~~",
                "................~~~~.......~~~",
                ".............~~~~~~~........~~",
                "......~.....~~~~~~~~.........~",
                ".....~~~...~~~~~~~~~..........",
                ".....~~~~~~~~~~~~~~~~........~",
                ".....~~~~~~~~~~~~~~~~~~~....~~");

        GameMap gameMap = new GameMap(groundFactory, map);
        world.addGameMap(gameMap);

        List<String> factoryMap = Arrays.asList(
                ".......",
                ".#####.",
                ".#___#.",
                ".#___#.",
                ".##_##.",
                ".......",
                ".......",
                ".......",
                ".......",
                ".......");

//         FOR TESTING
//        List<String> factoryMap = Arrays.asList(
//                ".#####.",
//                ".#####.",
//                ".##_##.",
//                ".##_##.",
//                ".##_##.",
//                ".##_##.",
//                ".##_##.",
//                ".##_##.",
//                ".##_##.",
//                ".##_##.");


        GameMap factoryGameMap = new GameMap(groundFactory, factoryMap);
        world.addGameMap(factoryGameMap);

        List<String> refactorioMap = Arrays.asList(
                "..........................~~~~",
                "..........................~~~~",
                "..........................~~~~",
                "~..........................~..",
                "~~...........#####............",
                "~~~..........#___#............",
                "~~~..........#___#............",
                "~~~..........##_##............",
                "~~~..................~~.......",
                "~~~~................~~~~......",
                "~~~~...............~~~~~......",
                "..~................~~~~.......",
                "....................~~........",
                ".............~~...............",
                "............~~~~..............");

        GameMap refactorioGameMap = new GameMap(groundFactory, refactorioMap);
        world.addGameMap(refactorioGameMap);


        for (String line : FancyMessage.TITLE.split("\n")) {
            new Display().println(line);
            try {
                Thread.sleep(200);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        //polymorphia
        gameMap.at(8, 7).addItem(new LargeBoltItem());
        gameMap.at(9, 7).addItem(new MetalSheetItem());
//        gameMap.at(10, 8).setGround(new SaplingTree());
        gameMap.at(5, 8).setGround(new Crater(new HuntsmanSpiderSpawner()));
        gameMap.at(15, 10).setGround(new Crater(new AlienBugSpawner()));
        gameMap.at(15, 14).setGround(new Crater(new SuspiciousAstronautSpawner()));
        gameMap.at(15, 9).addItem(new MetalPipeItem());
        gameMap.at(15, 9).addItem(new PotOfGoldItem());
        gameMap.at(14, 8).addItem(new JarOfPicklesItem());
        gameMap.at(14, 9).addItem(new JarOfPicklesItem());
        gameMap.at(14, 10).addItem(new JarOfPicklesItem());
        gameMap.at(16, 8).addItem(new MetalPipeItem());
        gameMap.at(16, 10).addItem(new LargeBoltItem());
        gameMap.at(14, 9).addItem(new LargeBoltItem());
        gameMap.at(13, 8).addItem(new LargeBoltItem());
        gameMap.at(15, 6).addItem(new MetalPipeItem());



        // Create computer terminals
        ComputerTerminal polymorphiaComputerTerminal = new ComputerTerminal();
        ComputerTerminal factoryComputerTerminal = new ComputerTerminal();
        ComputerTerminal refactorioComputerTerminal = new ComputerTerminal();

        //Add appropriate travel actions to each computer terminal
        polymorphiaComputerTerminal.addTravelAction(new TravelAction(factoryGameMap.at(3, 3), "Factory"));
        polymorphiaComputerTerminal.addTravelAction(new TravelAction(refactorioGameMap.at(15, 6), "Refactorio"));

        factoryComputerTerminal.addTravelAction(new TravelAction(refactorioGameMap.at(15, 6), "Refactorio"));
        factoryComputerTerminal.addTravelAction(new TravelAction(gameMap.at(15, 6), "Polymorphia"));

        refactorioComputerTerminal.addTravelAction(new TravelAction(gameMap.at(15, 6), "Polymorphia"));
        refactorioComputerTerminal.addTravelAction(new TravelAction(factoryGameMap.at(3, 3), "Factory"));

        // add the Computer terminals to the game maps
        gameMap.at(15, 5).setGround(polymorphiaComputerTerminal);
        factoryGameMap.at(3, 2).setGround(factoryComputerTerminal);
        refactorioGameMap.at(15, 5).setGround(refactorioComputerTerminal);

        // add AlienBug for testing travelling to location where another actor is on - won't travel
        refactorioGameMap.at(15, 6).addActor(new AlienBug());

        factoryGameMap.at(2,2).addActor(new HumanoidFigure());


        // Add Inheritree to the game maps using the factory
        TreeFactory factory = new TreeStageFactory();
        gameMap.at(1, 2).setGround(factory.createSprout());
        gameMap.at(3,5).setGround(factory.createMatureTree());
        factoryGameMap.at(2,5).setGround(factory.createSprout());
        refactorioGameMap.at(1,2).setGround(factory.createSaplingTree());
        refactorioGameMap.at(2,5).setGround(factory.createYoungTree());


        Player player = new Player("Intern", '@', 1);
        player.addBalance(300);
        world.addPlayer(player, gameMap.at(15, 6));
//        player.addItemToInventory(new ToiletPaperRoll());
//        player.addItemToInventory(new ToiletPaperRoll());
//        player.addItemToInventory(new ToiletPaperRoll());
//        player.addItemToInventory(new ToiletPaperRoll());
//        player.addItemToInventory(new ToiletPaperRoll());
//        player.addItemToInventory(new ToiletPaperRoll());
//        player.addItemToInventory(new ToiletPaperRoll());
//        player.addItemToInventory(new ToiletPaperRoll());
//        player.addItemToInventory(new ToiletPaperRoll());
//        player.addItemToInventory(new ToiletPaperRoll());
//        player.addItemToInventory(new PotOfGoldItem());
//        player.addItemToInventory(new LargeBoltItem());
//        player.addItemToInventory(new MetalSheetItem());
        player.addItemToInventory(new LargeFruit());
        player.addItemToInventory(new JarOfPicklesItem());
        player.addItemToInventory(new MetalPipeItem());


        world.run();

    }
}

