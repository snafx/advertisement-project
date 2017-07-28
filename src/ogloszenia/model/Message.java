package ogloszenia.model;


import javax.persistence.*;
import java.time.LocalDate;

/*
Wiadomosc
    id ogloszenia
    id nadawca
    id odbiorca
    tresc
    data
 */
@Entity
public class Message {

    @Id
    @Column(name="id", unique=true)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(nullable=false)
    private Advertisement advertisementId;

    @JoinColumn(nullable=false)
    @ManyToOne
    private User messageSender;

    @JoinColumn(nullable=false)
    @ManyToOne
    private User messageReceiver;

    @Column(nullable=false)
    private String messageContent;

    @Column(nullable=false)
    private LocalDate messageDate;

    public Message() {
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

    public User getMessageSender() {
        return messageSender;
    }

    public void setMessageSender(User messageSender) {
        this.messageSender = messageSender;
    }

    public User getMessageReceiver() {
        return messageReceiver;
    }

    public void setMessageReceiver(User messageReceiver) {
        this.messageReceiver = messageReceiver;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public LocalDate getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(LocalDate messageDate) {
        this.messageDate = messageDate;
    }
}
