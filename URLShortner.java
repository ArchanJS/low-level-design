package lld;
import java.util.*;

public class URLShortner {
    private HashMap<String,String> URLStore;
    private String baseUrl;
    private int length;
    private static final String CHARACTERS="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final Random random;

    public URLShortner(String basrUrl, int length){
        URLStore = new HashMap<>();
        this.baseUrl=baseUrl;
        this.length=length;
        this.random=new Random();

    }
    public String encrypt(String url){
        String updatedUrl=baseUrl+'/';
        while(true){
            StringBuilder stringBuilderKey=new StringBuilder(this.length);
            for(int i=1;i<=this.length;i++){
                stringBuilderKey.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
            }
            String encryptedKey=stringBuilderKey.toString();
            if(URLStore.getOrDefault(encryptedKey,"NONE")=="NONE") {
                updatedUrl+=encryptedKey;
                URLStore.put(updatedUrl,url);
                return updatedUrl;
            }
        }
        
    }
    public String decrypt(String url){
        return URLStore.getOrDefault(url,"NONE");
    }
    public void printResult(String url){
        System.out.println(url);
    }
    public void shortenURL(String operation, String url) throws IllegalArgumentException {
        operation=operation.toLowerCase();
        switch(operation){
            case "encrypt":
            printResult(encrypt(url));
            break;
            case "decrypt":
            String result = decrypt(url);
            if(result == "NONE") throw new IllegalArgumentException("Invalid URL");
            else printResult(result);
            break;
            default:
            throw new IllegalArgumentException("Invalid operation");
        }
    }
}
