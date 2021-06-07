package service;

import model.Chromosome;
import model.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeneticCross {

    private final Random generator = new Random();

    public void geneticCross(List<Chromosome> chromosomeList, double probability, List<Item> itemList, int weightMax) {
        List<Chromosome> newChromosomeList = new ArrayList<>();
        int amount = chromosomeList.size();
        for (int i = 0; i < amount * probability; i++) {
            List<Integer> parent1 = new ArrayList<>(chromosomeList.get(generator.nextInt(amount)).getBackpack());
            List<Integer> parent2 = new ArrayList<>(chromosomeList.get(generator.nextInt(amount)).getBackpack());

            for (int j = 0; j < 2; j++) {
                newChromosomeList.add(new Chromosome(getChild(parent1, parent2), itemList, weightMax));
            }
        }
        chromosomeList.addAll(newChromosomeList);
    }

    private List<Integer> getChild(List<Integer> parent1, List<Integer> parent2) {
        List<Integer> child1 = createGen(parent1, generator.nextInt(parent1.size()));
        List<Integer> child2 = createGen(parent2, generator.nextInt(parent2.size()));

        for (Integer x : child1) {
            child2.remove(x);
        }
        child1.addAll(child2);

        return child1;
    }

    private List<Integer> createGen(List<Integer> backpack, int amountGensCutOut) {
        List<Integer> newBackpack = new ArrayList<>();
        for (int j = 0; j < amountGensCutOut; j++) {
            int itemId;
            do {
                itemId = backpack.get(generator.nextInt(backpack.size()));
            } while (newBackpack.contains(itemId));
            newBackpack.add(itemId);
        }
        return newBackpack;
    }


}
