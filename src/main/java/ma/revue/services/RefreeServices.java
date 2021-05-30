package ma.revue.services;

import ma.revue.beans.Article;
import ma.revue.beans.Refree;
import ma.revue.beans.Review;
import ma.revue.beans.articleStatus.Accepte;
import ma.revue.dto.ArticleRefreeDTO;
import ma.revue.dto.ArticleVisitorDTO;
import ma.revue.dto.ReviewDTO;
import ma.revue.repositories.ReviewRepository;
import ma.revue.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
public class RefreeServices {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ReviewRepository refreeRepository;


    public void saveReviews(String email,Review review) throws Exception {
        if(review.getId()!=null)

            throw new Exception("Id Required");
        else
            reviewRepository.save(review);
    }

    public List<ReviewDTO> getEmptyReviews(String email){
        Refree refree= (Refree) userRepository.findByEmail(email);
        List<Review> reviews=reviewRepository.getEmptyReviews(refree);
        Type listType = new TypeToken<List<ReviewDTO>>() {}.getType();
        List<ReviewDTO> resultDtos = modelMapper.map(reviews, listType);
        return resultDtos;
    }

    public List<ReviewDTO> getNotEmptyReviews(String email){
        Refree refree= (Refree) userRepository.findByEmail(email);
        List<Review> reviews=reviewRepository.getNotEmptyReviews(refree);
        Type listType = new TypeToken<List<ReviewDTO>>() {}.getType();
        List<ReviewDTO> resultDtos = modelMapper.map(reviews, listType);
        return resultDtos;
    }

}
