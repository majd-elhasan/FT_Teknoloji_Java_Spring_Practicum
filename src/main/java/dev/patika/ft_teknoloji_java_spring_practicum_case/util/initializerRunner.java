package dev.patika.ft_teknoloji_java_spring_practicum_case.util;

import dev.patika.ft_teknoloji_java_spring_practicum_case.entity.Product;
import dev.patika.ft_teknoloji_java_spring_practicum_case.entity.ProductComment;
import dev.patika.ft_teknoloji_java_spring_practicum_case.entity.User;
import dev.patika.ft_teknoloji_java_spring_practicum_case.repository.ProductCommentRepository;
import dev.patika.ft_teknoloji_java_spring_practicum_case.repository.ProductRepository;
import dev.patika.ft_teknoloji_java_spring_practicum_case.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Component
public class initializerRunner implements CommandLineRunner {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ProductCommentRepository commentRepository;

    public initializerRunner(ProductRepository productRepository,UserRepository userRepository,ProductCommentRepository commentRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public void run(String... args) {
        Random r = new Random();
        if(productRepository.findAll().isEmpty()) {
            List<String> product_names = Arrays.asList("domates", "muz", "hıyar", "yumurta", "maydanoz", "patates", "ıspanak", "portakal", "limon", "börek", "mantı", "bulgur", "pirinç", "mısır", "tavuk eti", "dana eti", "maraş dondurması", "ekmek", "bezelye", "bamya", "fasulye", "nane", "ceviz", "fıstık", "şampuan", "sabun", "domestos", "çamaşır suyu", "saç kremi", "pudding", "domates salçası", "biber salçası", "makarna", "noodle", "bakla", "nohut", "cips", "bisküvi");
            for (int i = 1; i <= 100; i++) {
                int nonExpirable = r.nextInt(10);
                Date exp=nonExpirable==7?null:getRandomDate();

                Product product = Product.builder()
                        .id(i)
                        .name(product_names.get(r.nextInt(product_names.size())))
                        .price((float)((int)(r.nextFloat() * 3000))/100)
                        .exp(exp)
                        .build();
                productRepository.save(product);
            }
        }
        if(userRepository.findAll().isEmpty()){
            List<String> user_names = Arrays.asList("Mecid", "Rıdvan", "Esra", "Ebru", "Muhammed", "Gökhan", "Menderes", "Özcan", "Leyla", "Pelin", "İsa", "Yemin", "Emine", "Razan", "Cemal", "Fatin","Fatma", "İbrahim", "Ahmet", "Ali", "Mahmud", "Ayza", "Sümeyye", "Selma", "Sami", "Şam", "Fuat", "Selwa", "Kamil", "Koray", "Abdullah", "Mickael", "John", "Boris", " jea yon", "Xiao xing", "zao", "Nancy");
            List<String> user_surnames = Arrays.asList("Elhasan", "Güngör", "Yıldız", "Tekin", "Maden", "Karakışla", "Çuhacı", "Dede", "Murat", "Camgöz", "Mardinli", "Han", "Abid", "Özçatalbaş", "Öztürk","Şeker", "Yayla", "Malçok", "Sekran", "Alper", "Kılıçer", "Şimşek", "Aydın", "Tüfekçi", "Şam", "Furkan", "Özsoy", "Demir", "Güney", "El muhammed", "Angelo", "Cena", "Khuri", "Kim", "li", "ming", "Doruk");

            for (int i = 1; i <= 20; i++) {
                String name = user_names.get(r.nextInt(user_names.size()));
                if(name.contains(" "))
                    name = name.replace(" ","_");
                List<String> emailProviders = Arrays.asList("Google","Yahoo","Outlook","AOL");
                int rename=2;
                String renameStr="";
                String email2Part = "@"+emailProviders.get(r.nextInt(emailProviders.size()))+".com";
                String email = name+renameStr+email2Part;
                while (userRepository.findByEmail(email).size()>0){
                    renameStr=""+rename++;
                    email = name+renameStr+email2Part;
                    if (userRepository.findByEmail(email).isEmpty())
                        break;
                }
                String TwoDigitDirectlyAfter5 = getTwoDigitPhoneCode();
                int phoneNumberAfter5__ = getRandomPhoneNumber();
                String combinedPhoneNumber = "05"+TwoDigitDirectlyAfter5+phoneNumberAfter5__;
                while (userRepository.findByPhoneNumber(combinedPhoneNumber).size()>0){
                    TwoDigitDirectlyAfter5 = getTwoDigitPhoneCode();
                    combinedPhoneNumber = "05"+TwoDigitDirectlyAfter5+phoneNumberAfter5__;
                    if (userRepository.findByPhoneNumber(combinedPhoneNumber).isEmpty())
                        break;
                }

                User user = User.builder()
                        .id(i)
                        .name(name)
                        .surname(user_surnames.get(r.nextInt(user_surnames.size())))
                        .email(email)
                        .phoneNumber(combinedPhoneNumber)
                        .build();
                userRepository.save(user);
            }
        }
        if(commentRepository.findAll().isEmpty()){
            List<String> comments = Arrays.asList("iyi","fena değil","mükemmel","idare eder","pahalı","kötü");
            int sizeOfProductsList = productRepository.findAll().size();
            int sizeOfUsersList = userRepository.findAll().size();
            for (int i = 1;i <200 ;i++) {
                Date randomDate;
                do {
                    randomDate = getRandomDate();
                }while (randomDate.after(new Date()));
                ProductComment comment = ProductComment.builder()
                        .id(i)
                        .comment(comments.get((int)(Math.random()*comments.size())))
                        .commentDate(getRandomDate())
                        .product(productRepository.findById((long)(Math.random()*sizeOfProductsList)+1).orElse(null))
                        .user(userRepository.findById((long)(Math.random()*sizeOfUsersList)+1).orElse(null))
                        .build();
                commentRepository.save(comment);
            }
        }
    }
    private String twoDigitsGenerator(int max,boolean zeroNotExist){
        String output;
        Random r = new Random();
        int randomNum = zeroNotExist?r.nextInt(max)+1:r.nextInt(max);
        output =randomNum<10?"0"+randomNum: String.valueOf(randomNum);
        return output;
    }
    private int getRandomPhoneNumber(){
        return (int) (Math.random()*(9999999 - 1111111)+ 1111111);
    }
    private String getTwoDigitPhoneCode(){
        List<String> twoDigits = Arrays.asList("01","05","06","07","30","31","32","33","33","35","36","37","38","39","41","42","43","44","45","46","47","48","49","52","53","54","55","59");
        return twoDigits.get((int)(Math.random()*twoDigits.size()));
    }
    private Date getRandomDate(){
        Random r = new Random();
        List<String> years = Arrays.asList("2019", "2020", "2021", "2022");
        String month=twoDigitsGenerator(12,true);
        String day=month.equals("02")?twoDigitsGenerator(28,true):
                month.equals("04")||month.equals("06")||month.equals("09")||month.equals("11")?twoDigitsGenerator(30 ,true):
                        twoDigitsGenerator(31,true);

        return Date.from(LocalDateTime.parse(
                years.get(r.nextInt(years.size()))
                        + "-" + month
                        + "-" + day
                        + "T" + twoDigitsGenerator(24,false)
                        + ":" + twoDigitsGenerator(60,false)
                        + ":" + twoDigitsGenerator(60,false)
        ).atZone(ZoneId.systemDefault()).toInstant());
    }
}
