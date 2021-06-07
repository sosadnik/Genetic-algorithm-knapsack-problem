package model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class Chromosome {
    private List<Integer> backpack;
    private final int value;
    private final int weight;
    private final double profitability;

    public Chromosome(List<Integer> backpack, List<Item> itemList, int weightMax) {
        this.backpack = backpack;
        this.value = setValue(itemList);
        this.weight = setWeight(itemList);
        this.profitability = setProfitability(itemList, weightMax);
    }


    @Override
    public String toString() {
        return "\nChromosome{" +
                "profitability=" + profitability +
                ", value=" + value +
                ", weight= " + weight +
                ", backpack=" + backpack +
                '}';
    }

    private double setProfitability(List<Item> itemList, int weightMax) {
        double profitability = 0;
        for (Integer itemId : this.backpack) {
            profitability += itemList.get(itemId).getValue();
        }
        if (this.weight > weightMax) {
            return profitability * 0.3;
        } else return value;
    }

    private int setWeight(List<Item> itemList) {
        int weight = 0;
        for (Integer itemId : this.backpack) {
            weight += itemList.get(itemId).getWeight();
        }
        return weight;

    }

    public int setValue(List<Item> itemList) {
        int value = 0;
        for (Integer itemId : this.backpack) {
            value += itemList.get(itemId).getValue();
        }
        return value;
    }

    public void sort() {
        this.backpack = this.backpack.stream().sorted().collect(Collectors.toList());
    }
}
