package ma.revue.services;

import ma.revue.beans.Article;
import ma.revue.beans.articleStatus.Accepte;
import ma.revue.dto.ArticleVisitorDTO;
import ma.revue.repositories.ArticleRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class VisitorTest {

    @Mock
    ArticleRepository articleRepository;
    @Mock
    ModelMapper modelMapper;
    @InjectMocks
    VisitorServices visitorServices;
    AutoCloseable closeable;
    @BeforeEach
    public void init(){
        closeable= MockitoAnnotations.openMocks(this);
    }
    @AfterEach
    public void close() throws Exception {
        closeable.close();
    }
    @Test
    public void  testGetArticleFound() throws Exception {
        Long id=5l;
        Article article=new Article(id,new Accepte());

        given(articleRepository.findByIdAndStatus(anyLong(),any(Accepte.class))).willReturn(article);
        given(modelMapper.map(article,ArticleVisitorDTO.class)).willReturn(new ArticleVisitorDTO(id));
        ArticleVisitorDTO articleVisitorDTO= visitorServices.getArticle(id);
        assertEquals(articleVisitorDTO.getId(),id);

    }
    @Test
    public void  testGetArticleNotFound() throws Exception{
        Long id =5l;
        given(articleRepository.findByIdAndStatus(anyLong(),any(Accepte.class))).willReturn(null);
        Assertions.assertThrows(Exception.class, () -> visitorServices.getArticle(id),"Aucun Article avec cet id");
    }
}
