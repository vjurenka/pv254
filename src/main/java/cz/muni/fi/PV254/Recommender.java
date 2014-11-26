package cz.muni.fi.PV254;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.LogLikelihoodSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import javax.inject.Singleton;
import java.io.File;
import java.util.List;

/**
 * User: VJ
 * Date: 23. 11. 2014
 * Time: 17:58
 */
@Service
@Singleton
public class Recommender implements ResourceLoaderAware {

    private UserBasedRecommender recommender;

    public Recommender() {

    }

    private ResourceLoader resourceLoader;

    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public Resource getResource(String location) {
        return resourceLoader.getResource(location);
    }

    private void init() {
        try {
            Resource resource = getResource("classpath:blog-post-likes-formatted.csv");
            File file = resource.getFile();

            DataModel model = new FileDataModel(file);
            UserSimilarity similarity = new LogLikelihoodSimilarity(model);
            UserNeighborhood neighborhood = new NearestNUserNeighborhood(25, similarity, model);
            recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<RecommendedItem> recommend(int userId, int numItems) {
        try {
            if(recommender == null){
                init();
            }
            return recommender.recommend(userId, numItems);
        } catch (TasteException e) {
            return null;
        }
    }

    public static void main(String... args) throws Exception {
        //List<RecommendedItem> recommendations = recommender.recommend(62859, 10);

    }

}
