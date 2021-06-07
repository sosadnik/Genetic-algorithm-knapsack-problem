import model.Chromosome;
import model.Item;
import service.GeneticCross;
import service.GeneticMutation;
import service.Selection;

import java.util.*;

public class Main {
    private static final GeneticCross geneticCross = new GeneticCross();
    private static final GeneticMutation geneticMutation = new GeneticMutation();
    private static final Selection selection = new Selection();

    public static void main(String[] args) {
        int weightMax = 60;
        double probabilityCross = 0.8;
        double probabilityMutation = 0.1;
        int individuals = 20;
        int amountIteration = 1000;
        List<Item> itemList = getItemList();

        List<Chromosome> generation = firstGeneration(itemList, weightMax);
        generation = selection.sortGeneration(generation);
        System.out.println("firstGeneration\n" + generation);

        geneticAlgorithm(amountIteration,
                weightMax,
                probabilityCross,
                probabilityMutation,
                individuals,
                itemList,
                generation);
    }

    private static List<Item> getItemList() {
        List<Item> itemList = new ArrayList<>();
        itemList.add(new Item(4, 12));
        itemList.add(new Item(9, 6));
        itemList.add(new Item(12, 10));
        itemList.add(new Item(4, 3));
        itemList.add(new Item(6, 3));
        itemList.add(new Item(11, 18));
        itemList.add(new Item(8, 12));
        itemList.add(new Item(12, 4));
        itemList.add(new Item(6, 8));
        itemList.add(new Item(8, 6));
        return itemList;
    }

    private static void geneticAlgorithm(int amountIteration,
                                         int weightMax,
                                         double probabilityCross,
                                         double probabilityMutation,
                                         int individuals,
                                         List<Item> itemList,
                                         List<Chromosome> generation) {

        for (int i = 0; i < amountIteration; i++) {
            geneticCross.geneticCross(generation, probabilityCross, itemList, weightMax);

            geneticMutation.geneticDeletion(generation, probabilityMutation / 2, itemList, weightMax);

            geneticMutation.changeGen(generation, probabilityMutation / 2, itemList, weightMax);

            generation = selection.sortGeneration(generation);

            generation = selection.removeTheWeakest(generation, individuals);
            System.out.println(generation);
            System.out.println("^Generation nr: " + (i + 1) + "\n");
        }
    }

    public static List<Chromosome> firstGeneration(List<Item> itemList, int weightMax) {
        Random generator = new Random();
        List<Chromosome> chromosomeList = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            int numbersItem = generator.nextInt(itemList.size()) + 1;
            List<Integer> backpack = new ArrayList<>();

            for (int j = 0; j < numbersItem; j++) {
                int itemId;
                do {
                    itemId = generator.nextInt(itemList.size());
                } while (backpack.contains(itemId));
                backpack.add(itemId);
            }
            chromosomeList.add(new Chromosome(backpack, itemList, weightMax));
        }
        return chromosomeList;
    }

}
