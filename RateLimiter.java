package lld;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class RateLimiterException extends Exception {
    public RateLimiterException(String ErrorMessage){
        super(ErrorMessage);
    }
}

public class RateLimiter {
    private final HashMap<String, Integer> RequestCounter = new HashMap<>();
    private final HashMap<String, Integer> HandleTTL = new HashMap<>();
    private final int maxLimit;
    private final int ttl;
    private final ScheduledExecutorService cleanupScheduler;

    public RateLimiter(int maxlimit, int ttl) {
        this.maxLimit = maxlimit;
        this.ttl = ttl;
        cleanupScheduler = Executors.newScheduledThreadPool(1);
        cleanupScheduler.scheduleAtFixedRate(this::cleanupTTL, 0, 1, TimeUnit.SECONDS);
    }

    public void cleanupTTL(){
        for(Map.Entry<String, Integer> entry : new HashMap<>(HandleTTL).entrySet()){
            int TTLLeft = entry.getValue();
            String userIP = entry.getKey();
            if(TTLLeft < 10) {
                RequestCounter.put(userIP, 0);
                HandleTTL.remove(userIP);
            }
            else HandleTTL.put(userIP, TTLLeft-1);
        }
    }
    
    public boolean vaildateRequest(int currentNoOfRequests, int currentTTL, String userIP){
        if(currentNoOfRequests >= maxLimit && currentTTL >0 ) return false;
        return true;
    }

    public void updateTTL(String userIP, int currentNoOfRequests){
        this.HandleTTL.put(userIP, ttl);
    }

    public boolean limitRequest(String userIP) throws RateLimiterException {
        int currentNoOfRequests = RequestCounter.getOrDefault(userIP, 0);
        int ttl = this.HandleTTL.getOrDefault(userIP, 0);
        if(vaildateRequest(currentNoOfRequests, ttl, userIP)) throw new RateLimiterException("Maximum limit reached");
        this.RequestCounter.put(userIP, currentNoOfRequests+1);
        updateTTL(userIP, currentNoOfRequests+1);
        return true;

    }
}
