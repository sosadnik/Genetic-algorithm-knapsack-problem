package service;

import model.Chromosome;
import model.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeneticMutation {

    private final Random generator = new Random();

    public void geneticDeletion(List<Chromosome> chromosomeList, double probability, List<Item> itemList, int weightMax) {
        List<Chromosome> newChromosomeList = new ArrayList<>();
        int amount = chromosomeList.size();

        for (int i = 0; i < amount * probability; i++) {
            List<Integer> randomGen = new ArrayList<>(chromosomeList.get(generator.nextInt(amount)).getBackpack());
            if (randomGen.size() != 0) {
                randomGen.remove(generator.nextInt(randomGen.size()));
                newChromosomeList.add(new Chromosome(randomGen, itemList, weightMax));
            } else i--;
        }

        chromosomeList.addAll(newChromosomeList);
    }

    public void changeGen(List<Chromosome> chromosomeList, double probability, List<Item> itemList, int weightMax) {
        List<Chromosome> newChromosomeList = new ArrayList<>();
        int amount = chromosomeList.size();

        for (int i = 0; i < amount * probability; i++) {
            List<Integer> randomGen = new ArrayList<>(chromosomeList.get(generator.nextInt(amount)).getBackpack());
            if (randomGen.size() != 0) {
                randomGen.remove(generator.nextInt(randomGen.size()));
                addGen(itemList, randomGen);

                newChromosomeList.add(new Chromosome(randomGen, itemList, weightMax));
            } else i--;
        }
        chromosomeList.addAll(newChromosomeList);
    }

    private void addGen(List<Item> itemList, List<Integer> randomGen) {
        int itemId;
        do {
            itemId = generator.nextInt(itemList.size());
        } while (randomGen.contains(itemId));
        randomGen.add(itemId);
    }


}
