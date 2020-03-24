import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by hug.
 */
public class Experiments {
    public static void experiment1() {
        BST<Integer> bst = new BST<>();
        List<Integer> xValue = new ArrayList<>();
        List<Double> yValueRandom = new ArrayList<>();
        List<Double> yValueOptimal = new ArrayList<>();
        Random random = new Random();

        while (bst.size() < 5000) {
            int key = random.nextInt(5000);
            if (!bst.contains(key)) {
                bst.add(key);
                double yRandom = bst.averageDepth();
                double yOptimal = ExperimentHelper.optimalAverageDepth(bst.size());
                int x = bst.size();
                xValue.add(x);
                yValueRandom.add(yRandom);
                yValueOptimal.add(yOptimal);
            }
        }


        XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("x label").yAxisTitle("y label").build();
        chart.addSeries("average depth of BST", xValue, yValueRandom);
        chart.addSeries("average depth of optimal BST", xValue, yValueOptimal);

        new SwingWrapper(chart).displayChart();

    }

    public static void experiment2() {
        BST<Integer> bst = new BST<>();
        while (bst.size() < 5000) {
            int key = RandomGenerator.getRandomInt(5000);
            if (!bst.contains(key)) {
                bst.add(key);
            }
        }

        List<Double> averageDepths = new ArrayList<>();
        List<Integer> numberOfOperations = new ArrayList<>();
        numberOfOperations.add(0);
        averageDepths.add(bst.averageDepth());
        for (int i = 1; i <= 3000; i++) {
            ExperimentHelper.randomDeleteItem(bst);
            ExperimentHelper.randomInsertItem(bst, 5000);
            double averageDepth = bst.averageDepth();
            averageDepths.add(averageDepth);
            numberOfOperations.add(i);
        }
        XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("x label").yAxisTitle("y label").build();
        chart.addSeries("average depth of BST", numberOfOperations, averageDepths);
        new SwingWrapper(chart).displayChart();


    }

    public static void experiment3() {
        BST<Integer> bst = new BST<>();
        while (bst.size() < 5000) {
            int key = RandomGenerator.getRandomInt(5000);
            if (!bst.contains(key)) {
                bst.add(key);
            }
        }

        List<Double> averageDepths = new ArrayList<>();
        List<Integer> numberOfOperations = new ArrayList<>();
        numberOfOperations.add(0);
        averageDepths.add(bst.averageDepth());

        for (int i = 1; i <= 3000; i++) {
            ExperimentHelper.randomDeleteItemPS(bst);
            ExperimentHelper.randomInsertItem(bst, 5000);
            double averageDepth = bst.averageDepth();
            averageDepths.add(averageDepth);
            numberOfOperations.add(i);
        }
        XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("x label").yAxisTitle("y label").build();
        chart.addSeries("average depth of BST", numberOfOperations, averageDepths);
        new SwingWrapper(chart).displayChart();
    }

    public static void main(String[] args) {
        experiment2();
    }
}
