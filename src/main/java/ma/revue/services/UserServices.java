package ma.revue.services;

import ma.revue.beans.Article;
import ma.revue.beans.articleStatus.Accepte;
import ma.revue.dto.ArticleUserDTO;
import ma.revue.dto.ArticleVisitorDTO;
import ma.revue.repositories.ArticleRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServices {

    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    ModelMapper modelMapper;

    public ArticleUserDTO getArticle(Long id) throws Exception {
        Article optionalArticle=articleRepository.findByIdAndStatus(id,new Accepte());
        if(optionalArticle==null)
            return modelMapper.map(optionalArticle,ArticleUserDTO.class);
        else
            throw new Exception("Aucun Article avec cet id");
    }
    public Page<ArticleUserDTO> getAllArticles(Integer pageNo,
                                                  Integer pageSize,
                                                  String sortBy){
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Article> articleList = articleRepository.findByStatus(new Accepte(),paging);
        TypeToken<List<ArticleUserDTO>> typeToken = new TypeToken() {
        };
        return modelMapper.map(articleList,typeToken.getType());
    }
}
