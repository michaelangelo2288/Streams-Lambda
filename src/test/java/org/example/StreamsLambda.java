package org.example;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class StreamsLambda {

    // lambda expression with stream() + map() w/ lambda exp + method referencing ::
    @Test
    public void lambdaMap() {
        String[] names = {"mike", "tran"};
        List<String> namesList = Arrays.asList(names);

        namesList.stream()
                .map(name -> "first/name: " + name)
                .forEach(System.out::println);
    }

    // lambda expression with stream() + filter() which is a Predicate functional interface
    @Test
    public void lambdaFilter() {
        Integer[] numbers = new Integer[8];
        Random random = new Random();

        for(int i=0; i < numbers.length; i++) {
            numbers[i] = random.nextInt(11);
        }

        List<Integer> numList = Arrays.asList(numbers);
        System.out.println("numList: " + numList);

        numList.stream()
                .map(x -> x * 1)
                .filter(x -> x == 10)
                .map(x -> "found processed x == 10: " + x)
                .forEach(System.out::println);
    }

    @Test
    public void lambaReduce() {
        DoubleStream ds = DoubleStream.of(2.3, 6.4, 4.4);
        double sum = ds.sum();
        System.out.println(sum);

        // no Identity specified in reduce()
        List<Double> doubleList = Arrays.asList(2.3, 6.4, 4.4);
        Optional<Double> doubleSum = doubleList.stream().reduce((x, y) -> x+y);
        doubleSum.ifPresent(System.out::println);

        // *** specified an Identity so returned reduced (product) value is type 'double'
        double doubleProduct = doubleList.stream().reduce(1.0, (x, y) -> x*y);
        System.out.println(doubleProduct);
    }

    // lambda expression with stream() + collect()
    @Test
    public void lambdaCollect() {
        Integer[] numbers = {11, 22, 66, 7, 8, 90, 11, 63};
        List<Integer> numList = Arrays.asList(numbers);

        numList = numList.stream()
                .map(x -> x * 1)
                .collect(Collectors.toList());

        numList.forEach(x -> System.out.println("processed and new collection list: " + x));
    }

    // lambda expression with IntStream, stream(), range(), mapToObj()
    // Iterate through collection via index
    @Test
    public void lambdaIntStreamRange() {
        Integer[] numbers = {11, 22, 66, 7, 8, 90, 11, 63};

        IntStream.range(0, numbers.length)
                .mapToObj(i -> String.format("numbers[%s]: %s", i, numbers[i]))
                .forEach(System.out::println);
    }

    // lambda expression - mapToInt + sum()
    @Test
    public void lambdaMapToSum() {
        List<Integer> numbersList = Arrays.asList(11, 22, 66, 7, 8, 90, 11, 63);    // sum = 278

        System.out.println("mapToInt: " + numbersList.stream().mapToInt(n -> n).sum());
        System.out.println("mapToDouble: " + numbersList.stream().mapToDouble(n -> n).sum());
        System.out.println("mapToLong: " + numbersList.stream().mapToLong(n -> n).sum());
    }

    // lambda expression - set variable via inside lambda expression
    @Test
    public void lambdaSetVariableInExpression() {
        String[] names = {"mike", "tran", "thanh", "cong"};
        final String[] name = new String[1];
        List<String> namesList = Arrays.asList(names);

        namesList.forEach(n -> {
            if(n.equals("thanh"))
                name[0] = n;
        });

        System.out.println(name[0]);
    }
}
