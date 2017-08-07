package ogloszenia.model;

import ogloszenia.repository.UserRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.*;

@Entity
public class Advertisement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //bedzie autoinkrementacja
    @Column(unique = true)  //jest to wartosc unikalna dla kolumny
    private Integer id;

    @Column(nullable = false)
    private String title;

    @Lob //tu bedzie odbierany strumien z "obrazkiem"
    private byte[] img;

    @JoinColumn(nullable = false)
    @ManyToOne(cascade = CascadeType.ALL)
    private User owner; //powiazanie z inna kolumna

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private LocalDate dateFrom;

    @Column(nullable = false)
    private LocalDate dateTo;

    @Column(nullable = false)
    private Boolean isActive;

    @Column(nullable = false)
    private Boolean isPremium;

    @Column(nullable = false)
    private String cityName;

    @Column(nullable = false)
    private Integer rating;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING) /*w baazaie danych zostanie zapisany string z nazwa kategorii*/
    private CATEGORY category;

    @Column(nullable = false)
    private Integer views;

    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "watchers_id")
    )
    private Set<User> watchers;

    @OneToMany(mappedBy = "advertisement")  //zawsze trzeba wskazac mapowanie OneToMany
    private Set<Conversation> conversations;

    @OneToMany(mappedBy = "advertisement")
    private Set<Image> images;

    public Advertisement() {
    }

    public Advertisement(String title, BigDecimal price, String description, String location, CATEGORY category) {
        this.title = title;
        this.text = description;
        this.price = price;
        this.cityName = location;

        this.category = category;
        this.isPremium = false;
        this.isActive = true;
        this.views = new Integer(0);
        this.dateFrom = LocalDate.now();
        this.dateTo = this.dateFrom.plusMonths(1);
        this.rating = new Integer(0);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    public Boolean getPremium() {
        return isPremium;
    }

    public void setPremium(Boolean premium) {
        isPremium = premium;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public CATEGORY getCategory() {
        return category;
    }

    public void setCategory(CATEGORY category) {
        this.category = category;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Set<User> getWatchers() {
        return watchers;
    }

    public void setWatchers(Set<User> watchers) {
        this.watchers = watchers;
    }

    public Set<Conversation> getConversations() {
        return conversations;
    }

    public void setConversations(Set<Conversation> conversations) {
        this.conversations = conversations;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }
}
