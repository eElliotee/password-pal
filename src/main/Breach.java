package main;

import org.jsoup.Jsoup;

/**
 * Represents a single data breach returned by the Have I Been Pwned API.
 */
public class Breach {

    private String name;
    private String title;
    private String domain;
    private String breachDate;
    private long pwnCount;
    private String description;

    /**
     * Constructs a new Breach with details from the HIBP API.
     *
     * @param name        Internal name of the breach.
     * @param title       Display title of the breach.
     * @param domain      Primary domain affected by the breach.
     * @param breachDate  Date the breach occurred.
     * @param pwnCount    Number of accounts compromised.
     * @param description HTML description of the breach (stripped to plain text on access).
     */
    public Breach(String name, String title, String domain, String breachDate, long pwnCount, String description) {
        this.name        = name;
        this.title       = title;
        this.domain      = domain;
        this.breachDate  = breachDate;
        this.pwnCount    = pwnCount;
        this.description = description;
    }

    public String getName()      { return name; }
    public String getTitle()     { return title; }
    public String getDomain()    { return domain; }
    public String getBreachDate(){ return breachDate; }
    public long   getPwnCount()  { return pwnCount; }

    /** Returns the breach description with HTML tags stripped. */
    public String getDescription() {
        return Jsoup.parse(description).text();
    }

    @Override
    public String toString() {
        return "Name: " + name +
               "\nTitle: " + title +
               "\nDomain: " + domain +
               "\nBreach Date: " + breachDate +
               "\nPwn Count: " + pwnCount +
               "\nDescription: " + getDescription();
    }
}