package ogloszenia.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.*;

@Entity
public class Advertisement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //bedzie autoinkrementacja
    @Column(unique = true)  //jest to wartosc unikalna dla kolumny
    Integer id;

    @Column(nullable = false)
    String title;

    @Lob //tu bedzie odbierany strumien z "obrazkiem"
    byte[] img;

    @JoinColumn(nullable = false)
    @ManyToOne
    User owner; //powiazanie z inna kolumna

    @Column(nullable = false)
    BigDecimal price;

    @Column(nullable = false)
    String text;

    @Column(nullable = false)
    LocalDate dateFrom;

    @Column(nullable = false)
    LocalDate dataTo;

    @Column(nullable = false)
    Boolean isActive;

    @Column(nullable = false)
    Boolean isPremium;

    @Column(nullable = false)
    String cityName;

    @Column(nullable = false)
    Integer rating;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    CATEGORY category;

    @Column(nullable = false)
    Integer views;

    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "watchers_id")
    )
    private Set<User> watchers;

    @OneToMany(mappedBy = "advertisementId")
    Set<Message> messages;

    @OneToMany(mappedBy = "advertisementId")
    Set<Image> images;

    public Advertisement() {
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

    public LocalDate getDataTo() {
        return dataTo;
    }

    public void setDataTo(LocalDate dataTo) {
        this.dataTo = dataTo;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
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

    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }
}
