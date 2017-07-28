package ogloszenia.model;

import javax.persistence.*;

@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Integer id;

    @ManyToOne //wiele zdjec do jednego ogloszenia
    @JoinColumn(nullable = false)
    private Advertisement advertisementId;

    @Lob
    private byte[] img;

    public Image() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Advertisement getAdvertisementId() {
        return advertisementId;
    }

    public void setAdvertisementId(Advertisement advertisementId) {
        this.advertisementId = advertisementId;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }
}
