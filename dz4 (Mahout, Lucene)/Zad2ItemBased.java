package hr.fer.rovp.lab4.zad2;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.eval.RecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.eval.RMSRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.file.FileItemSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.common.RandomUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Zad2ItemBased {

    private static int USER_ID = 22;
    private static int NUM_OF_RECOMMENDATIONS = 10;
    private static double TRAIN_PERC = 0.5;
    private static double EVAL_PERC = 0.5;

    public static void main(String[] args) throws IOException, TasteException {
        RandomUtils.useTestSeed();

        DataModel model = new FileDataModel(new File("jester_ratings.dat"));
        //TODO uncomment if wanna see recomms for specific user
        //ItemSimilarity similarity = new FileItemSimilarity(new File("item_similarity.csv"));
        //Recommender recommender = new GenericItemBasedRecommender(model, similarity);
        //List<RecommendedItem> recommendations = recommender.recommend(USER_ID, NUM_OF_RECOMMENDATIONS);

        RecommenderEvaluator evaluator = new RMSRecommenderEvaluator();

        RecommenderBuilder builder = new RecommenderBuilder() {
            @Override
            public Recommender buildRecommender(DataModel model) {
                ItemSimilarity similarity = new FileItemSimilarity(new File("item_similarity.csv"));
                return new GenericItemBasedRecommender(model, similarity);
            }
        };

//        double score = evaluator.evaluate(builder,
//                null, model, TRAIN_PERC, EVAL_PERC);
//        System.out.println("Evaluation score: " + score);

        Recommender recommender = builder.buildRecommender(model);
        List<RecommendedItem> recommendations = recommender.recommend(22, 10);
        for (RecommendedItem recommendation : recommendations) {
            System.out.println(recommendation);
        }
    }
}
