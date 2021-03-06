package ma.revue.services;

import ma.revue.beans.*;
import ma.revue.beans.articleStatus.Accepte;
import ma.revue.beans.articleStatus.EnAttente;
import ma.revue.config.JwtTokenProvider;
import ma.revue.dto.ArticleVisitorDTO;
import ma.revue.dto.LoginRequestDTO;
import ma.revue.dto.LoginResponseDTO;
import ma.revue.dto.RegisterRequestDTO;
import ma.revue.repositories.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class VisitorServices {



    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    AuteurRepository auteurRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Value("${security.token_prefix}")
    private String TOKEN_PREFIX;

    @Autowired
    JwtTokenProvider tokenProvider;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ComiteRepository comiteRepository;
    @Autowired
    RefreeRepository refreeRepository;







    public ArticleVisitorDTO getArticle(Long id) throws Exception {
        Article optionalArticle=articleRepository.findByIdAndStatus(id,new Accepte());
        if(optionalArticle!=null)
            return modelMapper.map(optionalArticle,ArticleVisitorDTO.class);
        else
            throw new Exception("Aucun Article avec cet id");
    }

    public Page<ArticleVisitorDTO> getAllArticles(Integer pageNo, Integer pageSize,String sortBy){
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Article> articleList = articleRepository.findByStatus(new Accepte(),paging);
        Type listType = new TypeToken<Page<ArticleVisitorDTO>>() {}.getType();

        Page<ArticleVisitorDTO> resultDtos = modelMapper.map(articleList, listType);


        return resultDtos;
    }

    public LoginResponseDTO connexion(  LoginRequestDTO request){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = TOKEN_PREFIX + tokenProvider.generateToken(authentication);
        User user = (User) authentication.getPrincipal();
        LoginResponseDTO connexionResponse =new LoginResponseDTO(jwt, user);
        return connexionResponse;
    }

    public LoginResponseDTO register(RegisterRequestDTO registerRequestDTO){
        Auteur user= registerRequestDTO.toAuteur();
        String password=new String(user.getPassword()) ;
        String email=new String(user.getEmail());

        saveNewAuteur(user);
        System.out.print(password);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email,password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = TOKEN_PREFIX + tokenProvider.generateToken(authentication);
        User userConnected = (User) authentication.getPrincipal();
        LoginResponseDTO connexionResponse =new LoginResponseDTO(jwt, userConnected);
        return connexionResponse;
    }

    public void saveNewAuteur(Auteur user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        auteurRepository.save(user);
    }
    public void saveNewRefree(Refree user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        refreeRepository.save(user);
    }
    public void saveNewComite(Comite user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        comiteRepository.save(user);
    }

    public void initDatabase(){
        Refree refree1=new Refree(null,"refree1@revue.ma","A1234",true,new Profil(null,"0699887766","Ahmed","R1","Agadir , elhouda bp123111"),new Role(),null);
        Refree refree2=new Refree(null,"refree2@revue.ma","A1234",true,new Profil(null,"0699887766","Ahmed","R2","Agadir , elhouda bp123111"),new Role(),null);
        Refree refree3=new Refree(null,"refree3@revue.ma","A1234",true,new Profil(null,"0699887766","Ahmed","R3","Agadir , elhouda bp123111"),new Role(),null);

        Comite comite =new Comite(null,"comite@revue.ma","A1234",true,new Profil(null,"0699887766","Ahmed","C","Agadir , elhouda bp123111"),new Role());
        Auteur auteur =new Auteur(null,"auteur@revue.ma","A1234",true,new Profil(null,"0699887766","Ahmed","A","Agadir , elhouda bp123111"),new Role());
        Article article = new Article(null,"Balancing carbon storage under elevated CO2",auteur,null,"A global synthesis of experiments reveals that increases in plant biomass under conditions of " +
                "elevated CO2 mean that plants need to mine the soil for nutrients, which decreases soil???s ability to store carbon. In forests, elevated CO2 generally seems to greatly increase plant " +
                "biomass, but not soil carbon. In grasslands, by contrast, it causes " +
                "small changes in biomass and large increases in soil carbon.",new ArrayList<String>(Arrays.asList("nature", "co2", "forests")),null,null
                ,"Le CO2, gaz incolore, inerte et non toxique, est le principal gaz ?? effet de serre ?? l'??tat naturel, avec la vapeur d'eau. Sa dur??e de vie dans l'atmosph??re est d'environ 100 ans. Il est produit lorsque des compos??s carbon??s sont br??l??s et en pr??sence d'oxyg??ne.\n" +
                "\n" +
                "Ses sources naturelles sont tr??s nombreuses : ??ruptions volcaniques, respiration des plantes, des animaux et des hommes, incendies naturels de for??ts, d??composition de la mati??re organique morte de plantes et d'animaux???\n" +
                "Sous l'action de l'homme, le taux de CO2 dans l'atmosph??re augmente r??guli??rement et notamment de 30% au cours des deux derniers si??cles. En France, au cours des 20 derni??res ann??es, 70% ?? 90% des ??missions de dioxyde de carbone proviendraient de la combustion des carburants d'origine fossile." +
                " L'agriculture et la sylviculture contribueraient pour 12% des ??missions de dioxyde de carbone.\n" +
                "Le carbone subit en permanence des transferts entre ces diff??rents milieux. Une tr??s large proportion du CO2 est ainsi dissout dans les oc??ans. Les plantes absorbent le CO2 atmosph??rique par la photosynth??se et le transforment en compos??s carbon??s organiques (sucres, cellulose???)." +
                " ?? l'inverse, elles rejettent une petite quantit?? de CO2 dans l'atmosph??re lors de la respiration et ?? la mort : les microorganismes du sol d??composent la mati??re organique v??g??tale et lib??rent une partie du carbone de la plante dans l'atmosph??re, sous forme de CO2." +
                " L'autre partie du carbone est stock??e dans le sol.\n" +
                "\n" +
                "Le CO2 dispose d'un tr??s haut pouvoir de dissolution dans les muqueuses du corps humain et provoque chez l'homme de nombreuses r??actions d??s que sa concentration dans l'air inspir?? augmente." +
                " Pour des concentrations voisines de 0,1 %, il provoque une modification du rythme repiratoire chez les personnes fragiles ou ayant des insuffisances respiratoires , des niveaux sup??rieurs ?? 1000 ppm (0,1%) , en milieu clos peuvent provoquer des crises d'asthme.",null,null);
        saveNewAuteur(auteur);
        saveNewComite(comite);
        saveNewRefree(refree1);
        saveNewRefree(refree2);
        saveNewRefree(refree3);
        Article article1=articleRepository.save(article);
        article1.setStatus(new EnAttente());
        articleRepository.save(article1);
    }

}
