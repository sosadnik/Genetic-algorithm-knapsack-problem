package service;

import model.Chromosome;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Selection {

    public List<Chromosome> sortGeneration(List<Chromosome> generation) {
        generation.forEach(Chromosome::sort);
        return generation.stream()
                .sorted(Comparator.comparing(Chromosome::getProfitability).reversed())
                .collect(Collectors.toList());
    }

    public List<Chromosome> removeTheWeakest(List<Chromosome> generation, int targetNumber) {
        if (generation.size() > targetNumber) {
            generation.subList(targetNumber, generation.size()).clear();
        }
        return generation;
    }
}
